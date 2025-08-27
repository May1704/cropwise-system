package com.cropwise.cw_system.dtos.user;

import com.cropwise.cw_system.models.User;


public class UserMapper {

    public static User dtoToEntity (UserRequest dtoUserRequest){
        return new User(dtoUserRequest.name(),dtoUserRequest.email(),dtoUserRequest.password());
    }

   public static UserResponse entityToDto (User user){
        return new UserResponse(user.getName(), user.getEmail());
   }
}
