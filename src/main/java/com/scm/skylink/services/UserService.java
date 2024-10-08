package com.scm.skylink.services;

import com.scm.skylink.dto.UserDto;

public interface UserService {

    UserDto createNewUser(UserDto userDto);

    UserDto getUserByEmail(String username);

    UserDto updateUserDetails(String userId, UserDto userDto);

}
