package com.example.AgileBoard.security;

import com.example.AgileBoard.model.UserDto;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * We construct the user object of our application
 */
public class ContextUser extends User {

    private UserDto userDto;

    public ContextUser(UserDto userDto) {
        //receive the userName and password
        super(userDto.getUserName(),
                userDto.getPassword(),
                // do not check if account if expired or else
                //TODO test in the future
                true,
                true,
                true,
                true,
                ImmutableSet.of(new SimpleGrantedAuthority("create")));

        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
