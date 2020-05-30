package com.blopi.api.user.application;

import com.blopi.api.auth.architecture.jwt.JwtUtils;
import com.blopi.api.auth.domain.response.MessageResponse;
import com.blopi.api.user.domain.User;
import com.blopi.api.user.domain.UserRepository;
import com.blopi.api.user.domain.dto.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils       jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> getDashboard(String userName, String token) {

        if ( jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", "")).equals(userName) ) {
            User user = userRepository.findByUsername(userName).get();
            return ResponseEntity.ok(new Dashboard.Builder().email(user.getEmail())
                                                            .userName(user.getUsername())
                                                            .roles(user.getRoles())
                                                            .build());
        }

        return ResponseEntity.badRequest()
                             .body(
                                     new MessageResponse("Error: user have not permissions for this dashboard")
                             );
    }
}
