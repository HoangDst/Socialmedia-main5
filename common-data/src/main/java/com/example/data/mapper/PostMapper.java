package com.example.data.mapper;

import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.Topic;
import com.example.data.request.PostRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface PostMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "userOwnerId", target = "userOwnerId")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "voteCount")
    @Mapping(ignore = true, target = "commentCount")
    @Mapping(source = "topicId", target = "topicRelatedId")
    Post toEnity(PostRequest postRequest);



    @Mapping(target = "title", source = "post.title")
    @Mapping(target = "content", source = "post.content")
    @Mapping(target = "createdAt", source = "post.createdAt")
    @Mapping(target = "updatedAt", source = "post.updatedAt")
    @Mapping(target = "deletedAt", source = "post.deletedAt")
    @Mapping(target = "numComment", source = "post.commentCount")
    @Mapping(target = "topic", source = "topic.content")
    @Mapping(target = "userResponse", source = "userResponse")
    PostResponse toDTO(Post post, Topic topic, UserResponse userResponse);




}
