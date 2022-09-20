package com.securityjwt.JwtAuthentication.Service;

import com.securityjwt.JwtAuthentication.Document.UserInfo;
import com.securityjwt.JwtAuthentication.Exception.GlobalException;
import com.securityjwt.JwtAuthentication.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws GlobalException {
        Optional<UserInfo> userInfo=userInfoRepository.findById(username);
        if(userInfo.isEmpty())
            throw new GlobalException("User ID not found in database..");
        return new User(userInfo.get().getEmailId(),userInfo.get().getPassword(),new LinkedList<>());
    }
}
