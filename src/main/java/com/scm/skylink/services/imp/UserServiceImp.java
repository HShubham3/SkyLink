package com.scm.skylink.services.imp;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.UserDto;
import com.scm.skylink.entities.User;
import com.scm.skylink.repositories.UserRepo;
import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    @Override
    public UserDto createNewUser(UserDto userDto) {

        UUID uuid = UUID.randomUUID();
        userDto.setUserId(uuid.toString());
        System.out.println(userDto);
        User user = modelMapper.map(userDto, User.class);
        System.out.println(user);
        return modelMapper.map(userRepo.save(user), UserDto.class);
    }

}
