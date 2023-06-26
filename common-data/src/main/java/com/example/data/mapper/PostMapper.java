package com.example.data.mapper;

import com.example.data.request.PostRequest;
import com.example.data.response.PostResponse;
import com.example.data.response.UserResponse;
import com.hm.social.tables.pojos.Post;
import com.hm.social.tables.pojos.Topic;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PostMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "userOwnerId", target = "userId")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "question")
    @Mapping(ignore = true, target = "numComment")
    @Mapping(source = "topicId", target = "topicId")
    Post toEnity(PostRequest postRequest);


    PostResponse toDTO(Post post, @Context Topic topic, @Context UserResponse userResponse);

    PostResponse toResponse(Post post, @Context Topic topic, @Context UserResponse userResponse);


    @AfterMapping
    default void afterMapping(@MappingTarget PostResponse response,
                              Post post,
                              @Context Topic topic, @Context UserResponse userResponse) {
        //
        if (topic != null) {
            response.setTopic(topic.getDesc());
        }
    }

}
