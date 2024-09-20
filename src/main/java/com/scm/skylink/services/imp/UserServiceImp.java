package com.scm.skylink.services.imp;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.skylink.dto.UserDto;
import com.scm.skylink.entities.Helper;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.exceptions.ResourceNotFoundException;
import com.scm.skylink.helper.AppConstants;
import com.scm.skylink.repositories.UserRepo;
import com.scm.skylink.services.EmailService;
import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

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
        String eamilToken = UUID.randomUUID().toString();
        user.setEmailToken(eamilToken);
        UserDto user1 = modelMapper.map(userRepo.save(user), UserDto.class);
        emailService.sendEmail(user1.getEmail(), "Verify email ! SkyLink email verification mail.",
                Helper.getLinkForEmailVerification(eamilToken));
        return user1;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with this mail id:"));

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUserDetails(String userId, UserDto userDto) {
        UserEntity user = isExixtByUserId(userId);

        user.setName(userDto.getName());
        user.setProfilePic(userDto.getProfilePic());

        return modelMapper.map(userRepo.save(user), UserDto.class);
    }

    public UserEntity isExixtByUserId(String userId) {

        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

    }

}
