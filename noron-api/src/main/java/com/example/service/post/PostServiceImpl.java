package com.example.service.post;

import com.example.data.mapper.PostMapper;
import com.example.data.response.PostResponse;
import com.example.post.IPostRepository;
import com.hm.social.tables.pojos.Post;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
    private final PostMapper postMapper;
    private final IPostRepository postRepository;

    public PostServiceImpl(PostMapper postMapper,
                           IPostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    @SneakyThrows
    @Override
    public PostResponse getById(Integer postId) {
        Post post = postRepository.findById(postId);
        return postMapper.toResponse(post, null, null);
    }
}
