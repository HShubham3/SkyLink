package com.scm.skylink.services.imp;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.UserDto;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.helper.AppConstants;
import com.scm.skylink.repositories.UserRepo;
import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createNewUser(UserDto userDto) {

        UUID uuid = UUID.randomUUID();
        userDto.setUserId(uuid.toString());
        System.out.println(userDto);
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        // System.out.println(user);
        String pwd = passwordEncoder.encode(user.getPwd());
        user.setPwd(pwd);
        user.setRoles(AppConstants.ROLE_USER);
        return modelMapper.map(userRepo.save(user), UserDto.class);
    }

}
