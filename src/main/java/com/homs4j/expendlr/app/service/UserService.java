package com.homs4j.expendlr.app.service;

import com.homs4j.expendlr.app.dto.user.PostUser;
import com.homs4j.expendlr.app.dto.user.PutUser;
import com.homs4j.expendlr.app.dto.user.UserDTO;
import com.homs4j.expendlr.app.exception.EntityNotCreatedException;
import com.homs4j.expendlr.app.exception.EntityNotFoundException;
import com.homs4j.expendlr.app.exception.EntityNotUpdatedException;
import com.homs4j.expendlr.app.mapper.dtomapper.UserDTOMapper;
import com.homs4j.expendlr.app.model.User;
import com.homs4j.expendlr.app.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserDTO findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userDTOMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Requested user information not found"));
    }

    public UserDTO saveUser(PostUser userDTO) {
        String id = UUID.randomUUID().toString();
        User newUser = userDTOMapper.toUser(userDTO);
        newUser.setUserId(id);
        newUser.setPassword("encodedPassword");//TODO: Implement password encryption
        Optional<User> saved = userRepository.save(newUser);
        return saved.map(userDTOMapper::toDto)
                .orElseThrow(()-> new EntityNotCreatedException("Failed to save new user information"));
    }

    public UserDTO updateUser(PutUser userDTO, String id) {
        User userToUpdate = userDTOMapper.toUser(userDTO);
        userToUpdate.setUserId(id);
        Optional<User> updatedUser = userRepository.update(userToUpdate);
        return updatedUser.map(userDTOMapper::toDto)
                .orElseThrow(()-> new EntityNotUpdatedException("Failed to update user information"));
    }

    public int deleteUser(String id) {
        int result = userRepository.delete(id);
        if(result <= 0) throw  new EntityNotFoundException("Failed to delete user");
        return result;
    }

}
