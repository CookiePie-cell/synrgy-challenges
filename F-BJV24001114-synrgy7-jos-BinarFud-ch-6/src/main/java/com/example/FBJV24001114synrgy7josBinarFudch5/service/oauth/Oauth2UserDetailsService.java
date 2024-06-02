package com.example.FBJV24001114synrgy7josBinarFudch5.service.oauth;


import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.AuthUser;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.oauth.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Oauth2UserDetailsService implements UserDetailsService{

    @Autowired
    private UserAuthRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = userRepository.findOneByUsername(s);
        if (authUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s is not found", s));
        }

        return authUser.get();
    }

    @CacheEvict("oauth_username")
    public void clearCache(String s) {
    }
}
