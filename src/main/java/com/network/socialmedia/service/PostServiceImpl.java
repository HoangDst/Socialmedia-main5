package com.network.socialmedia.service;

import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.User;
import com.network.socialmedia.data.request.detailPage.PostRequest;
import com.network.socialmedia.data.response.detailPage.PostResponse;
import com.network.socialmedia.data.response.detailPage.UserResponse;
import com.network.socialmedia.repository.post.IPostRepository;
import com.network.socialmedia.repository.user.IUserRepository;
import com.network.socialmedia.service.IPostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
    private final IPostRepository postRepository;

    private final IUserRepository userRepository;

    public PostServiceImpl(IPostRepository postRepository,
                           IUserRepository userRepository){
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }
 @Override
    public PostResponse insert(PostRequest request){
        Post post =toPojo(request);
        Post newPost=postRepository.insert(post);
        User owner=userRepository.getById(newPost.getUserId());
        return toPostResponse(newPost,owner);
 }
    private PostResponse toPostResponse(Post newPost, User owner) {
        PostResponse postResponse = new PostResponse()
                .setTitle(newPost.getTitle())
                .setDesc(newPost.getQuestion());
        UserResponse userResponse = new UserResponse()
                .setUserName(owner.getName())
                .setUserAvatar(owner.getAvatar());
        postResponse.setUserResponse(userResponse);
        return postResponse;
    }
    private static Post toPojo(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setQuestion(request.getDesc());
        post.setUserId(request.getUserid());
        return post;
    }

    @Override
    public com.network.socialmedia.data.response.detailPage.PostResponse update(PostRequest request, Integer postId) {
        return null;
    }

    @Override
    public PostResponse get(PostRequest request, Integer id) {
        return null;
    }
}
