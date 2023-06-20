package com.example.service;


import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.Topic;
import com.hm.social.tables.pojos.User;
import com.example.ICommentRepo;
import com.example.IPostRepo;
import com.example.ITopicRepo;
import com.example.IUserRepo;
import com.example.data.mapper.PostMapper;
import com.example.data.mapper.UserMapper;
import com.example.data.mapper.UserMapperImpl;
import com.example.data.response.PostResponse;
import com.example.data.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HomePageServiceImpl implements IHomePageService {
    private IPostRepo postRepo;
    private IUserRepo userRepo;
    private ITopicRepo iTopicRepo;
    private ICommentRepo commentRepo;
    private UserMapperImpl userMapper;
    private PostMapper postMapper;


    public HomePageServiceImpl(IPostRepo postRepo, IUserRepo userRepo,
                               ITopicRepo iTopicRepo, ICommentRepo commentRepo,
                               UserMapper userMapper, PostMapper postMapper) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.iTopicRepo = iTopicRepo;
        this.commentRepo = commentRepo;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostResponse> getAllPostByCommon() {
        List<Post> postList = postRepo.getAllPostsByCommon();
        List<PostResponse> postResponseList = new ArrayList<>();
        for (Post post : postList) {
            User user = userRepo.getUserById(post.getUserId());
            UserResponse userResponse = userMapper.toDTO(user);
            Topic topic = iTopicRepo.getTopicById(post.getTopicId());
            postResponseList.add(postMapper.toDTO(post, topic, userResponse));
        }
        return postResponseList;
    }

    @Override
    public List<PostResponse> getAllPostByNewest() {
        List<Post> postList = postRepo.getAllPostByDate();
        List<PostResponse> postResponseList = new ArrayList<>();
        for (Post post : postList) {
            User user = userRepo.getUserById(post.getUserId());
            UserResponse userResponse = userMapper.toDTO(user);
            Topic topic = iTopicRepo.getTopicById(post.getTopicId());
            postResponseList.add(postMapper.toDTO(post, topic, userResponse));
        }
        return postResponseList;
    }
}
