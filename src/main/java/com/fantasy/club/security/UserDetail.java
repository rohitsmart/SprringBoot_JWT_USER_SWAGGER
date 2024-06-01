package com.fantasy.club.security;

import com.fantasy.club.entity.User;
import com.fantasy.club.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetail {


    private final UserRepository userRepository;

    public User getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.
                    findByUsername(userDetails.getUsername()).orElseThrow();
        }
        catch (Exception e)
        {
        throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}