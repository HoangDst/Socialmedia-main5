package com.example.data.mapper;

import com.example.data.request.ShortCommentRequest;
import com.example.data.response.ShortCommentResponse;
import com.example.data.response.UserResponse;
import com.hm.social.tables.pojos.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface ShortCommentMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "userOwnerId", target = "commentuserId")
    @Mapping(ignore = true, target = "postuserId")
    @Mapping(ignore = true, target = "numVote")
    @Mapping(ignore = true, target = "numReply")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "updatedAt")
    Comment toEnity(ShortCommentRequest shortCommentResquest);

    @Mapping(target = "content", source = "comment.content")
    @Mapping(target = "numVote", source = "comment.numVote")
    @Mapping(target = "numComment", source = "comment.numReply")
    @Mapping(target = "userResponse", source = "userResponse")
    @Mapping(source = "comment.createdAt", target = "createdAt")
    @Mapping(source = "comment.deletedAt", target = "deletedAt")
    @Mapping(source = "comment.updatedAt", target = "updatedAt")
    ShortCommentResponse toDTO(Comment comment, UserResponse userResponse);
}
