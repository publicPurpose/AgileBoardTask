package com.example.AgileBoard.security;

import com.example.AgileBoard.model.UserDto;
import com.example.AgileBoard.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Customize security settings
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        checkNotNull(username);

        if (isEmpty(username)) {
            throw new UsernameNotFoundException("UserName can not be empty");
        }

        UserDto user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found or not exist at all");
        }
        return new ContextUser(user);
    }
}
