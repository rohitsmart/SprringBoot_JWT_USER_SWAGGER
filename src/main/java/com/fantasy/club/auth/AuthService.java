package com.fantasy.club.auth;

import com.fantasy.club.entity.User;
import com.fantasy.club.entity.UserRole;
import com.fantasy.club.repository.UserRepository;
import com.fantasy.club.security.AppProperties;
import com.fantasy.club.security.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AppProperties appProperties;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        try {
                String username = request.getEmail();
                String password = request.getPassword();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        password
                );
                authentication = authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                User user = (User) authentication.getPrincipal();
                String bearerToken = jwtService.generateToken(user);
                return LoginResponse.builder()
                            .token(bearerToken)
                            .build();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password.");
        } catch (Exception e) {
            throw new RuntimeException("Error caught inside login: " + e.getLocalizedMessage());
        }
    }

    @PostConstruct
    public void ensureAdminExists() {
        Optional<User> existingAdmin = userRepository.findByRole(UserRole.ADMIN);
        if (existingAdmin.isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(UserRole.ADMIN)
                    .active(true)
                    .accountVerified(true)
                    .build();
            userRepository.save(admin);
        }
    }
}