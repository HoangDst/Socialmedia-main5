package com.example.service;

import com.example.data.request.PostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.social.tables.pojos.Comment;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.Topic;
import com.hm.social.tables.pojos.User;
import com.example.ICommentRepo;
import com.example.IPostRepo;
import com.example.ITopicRepo;
import com.example.IUserRepo;
import com.example.data.mapper.*;
import com.example.data.request.ShortCommentRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.ShortCommentResponse;
import com.example.data.response.UserResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DetailPageServiceImpl implements IDetailPageService {
    private IPostRepo postRepo;
    private ITopicRepo topicRepo;
    private IUserRepo userRepo;
    private ICommentRepo commentRepo;
    private PostMapperImpl postMapper;
    private UserMapperImpl userMapper;
    private ShortCommentMapperImpl shortCommentMapper;

    public DetailPageServiceImpl(IPostRepo postRepo, ITopicRepo topicRepo, IUserRepo userRepo,
                                 ICommentRepo commentRepo, PostMapperImpl postMapper, UserMapperImpl userMapper,
                                 ShortCommentMapperImpl shortCommentMapper) {
        this.postRepo = postRepo;
        this.topicRepo = topicRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
        this.shortCommentMapper = shortCommentMapper;
    }

    @Override
    public PostResponse getDetailPostById(Integer id) {
        Post post = postRepo.getPostById(id);
        Topic topic = topicRepo.getTopicById(post.getTopicId());
        User user = userRepo.getUserById(post.getUserId());
        UserResponse userResponse = userMapper.toDTO(user);
        PostResponse postResponse = postMapper.toDTO(post, topic,userResponse);
        return postResponse;
    }

    @Override
    public List<ShortCommentResponse> getAllShortCommentResponseByDate(Integer postId) {
        List<Comment> commentList = commentRepo.getAllCommentByDate(postId);
        List<ShortCommentResponse> shortCommentResponseList= new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userRepo.getUserById(comment.getCommentuserId());
            UserResponse userResponse = userMapper.toDTO(user);
            shortCommentResponseList.add(shortCommentMapper.toDTO(comment, userResponse));
        }
        return shortCommentResponseList;

    }

    @Override
    public List<ShortCommentResponse> getAllShortCommentResponseByCommon(Integer postId) {
        List<Comment> commentList = commentRepo.getAllCommentsByCommon(postId);
        List<ShortCommentResponse> shortCommentResponseList= new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userRepo.getUserById(comment.getCommentuserId());
            UserResponse userResponse = userMapper.toDTO(user);
            shortCommentResponseList.add(shortCommentMapper.toDTO(comment, userResponse));
        }
        return shortCommentResponseList;
    }

    @Override
    public List<String> getRecommendPost(Integer topicId) {
        List<Post> postList = postRepo.getRandPostByTopic(topicId);
        List<String> postRecommend = new ArrayList<>();
        for(Post post: postList){
            postRecommend.add(post.getTitle());
        }
        return postRecommend;
    }

    @Override
    public ShortCommentResponse insertComment(ShortCommentRequest shortCommentResquest, Integer userOwnerId) {
       Comment comment= shortCommentMapper.toEnity(shortCommentResquest);
       User user = userRepo.getUserById(userOwnerId);
       UserResponse userResponse= userMapper.toDTO(user);
       return shortCommentMapper.toDTO(comment,userResponse);
    }
    @Override
    public PostResponse insertPost(PostRequest postRequest) {
        Post post =postMapper.toEnity(postRequest);
        Post post1=postRepo.insertPost(post);
        User user=userRepo.getUserById(post1.getUserId());
        UserResponse userResponse=userMapper.toDTO(user);
        Topic topic=topicRepo.getTopicById(post1.getTopicId());
        PostResponse postResponse=postMapper.toDTO(post1,topic,userResponse);
       return postResponse;
    }

}
