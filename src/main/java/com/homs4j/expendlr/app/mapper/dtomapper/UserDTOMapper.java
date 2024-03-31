package com.homs4j.expendlr.app.mapper.dtomapper;

import com.homs4j.expendlr.app.dto.user.PostUser;
import com.homs4j.expendlr.app.dto.user.PutUser;
import com.homs4j.expendlr.app.dto.user.UserDTO;
import com.homs4j.expendlr.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public User toUser(UserDTO dto) {
        return User.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public User toUser(PostUser dto) {
        return User.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhone())
                .password(dto.getPassword())
                .build();
    }

    public User toUser(PutUser dto) {
        return User.builder()
                .name(dto.getName())
                .lastName(dto.getLastname())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhone())
                .build();
    }

}
