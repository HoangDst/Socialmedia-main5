package com.example.data.mapper;

import com.example.data.request.UserSignInRequest;
import com.example.data.response.UserResponse;
import com.hm.social.tables.pojos.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "account", target = "userAccount")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "avatar", target = "userAvatar")
    UserResponse toDTO(User user);

    @Mapping(target = "name", source = "userName")
    @Mapping(target = "account", source = "userAccount")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "avatar", source = "userAvatar")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "authority", source = "authority")
    User toEnity(UserSignInRequest userSignInRequest);
}
