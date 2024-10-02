package com.copypay.service;

import com.copypay.model.User;
import com.copypay.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPw())
                .authorities("ROLE_USER")
                .build();
    }
}