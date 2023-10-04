package com.example.service;


import com.example.ICommentRepo;
import com.example.IPostRepo;
import com.example.ITopicRepo;
import com.example.IUserRepo;
import com.example.data.mapper.PostMapper;
import com.example.data.mapper.UserMapper;
import com.example.data.response.PostResponse;
import com.example.data.response.UserResponse;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.Topic;
import com.hm.social.tables.pojos.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class HomePageServiceImpl implements IHomePageService {
    private IPostRepo postRepo;
    private IUserRepo userRepo;
    private ITopicRepo iTopicRepo;
    private ICommentRepo commentRepo;
    private UserMapper userMapper;
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
        Map<Integer, User> userMap = getUserMap(postList);
        // topic
        return postList.stream()
                .map(post -> {
                    User user = userMap.getOrDefault(post.getUserId(), null);
                    UserResponse userResponse = userMapper.toDTO(user);
                    Topic topic = iTopicRepo.getTopicById(post.getTopicId());
                    return postMapper.toDTO(post, topic, userResponse);
                })
                .collect(Collectors.toList());
    }

    private Map<Integer, User> getUserMap(List<Post> postList) {
        List<Integer> userIds = postList.stream()
                .map(Post::getUserId)
                .collect(Collectors.toList());
        List<User> users = userRepo.getUserByIds(userIds);
        return users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
