package com.blopi.api.auth.application.signup;

import com.blopi.api.auth.domain.request.SignUpRequest;
import com.blopi.api.user.domain.User;
import com.blopi.api.user.domain.UserRepository;
import com.blopi.api.user.domain.role.Role;
import com.blopi.api.user.domain.role.RoleRepository;
import com.blopi.api.user.domain.role.RoleType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SignUpImpl implements SignUp {
    UserRepository  userRepository;
    RoleRepository  roleRepository;
    PasswordEncoder passwordEncoder;

    public SignUpImpl(UserRepository userRepository,
                      RoleRepository roleRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String existUser(SignUpRequest signUpRequest) {
        if ( userRepository.existsByUsername(signUpRequest.getUsername()) ) {
            return "username";
        }
        if ( userRepository.existsByEmail(signUpRequest.getEmail()) ) {
            return "email";
        }
        return "ok";
    }

    @Override
    public User createNewUser(SignUpRequest signUpRequest) {
        return new User(signUpRequest.getUsername(),
                        signUpRequest.getEmail(),
                        passwordEncoder.encode(signUpRequest.getPassword()));
    }

    @Override
    public void addRole(SignUpRequest signUpRequest, User user) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role>   roles    = new HashSet<>();

        if ( strRoles == null ) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_EDITOR)
                                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch ( role.toUpperCase() ) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                                       .orElseThrow(() -> new RuntimeException(
                                                               "Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "EDITOR":
                        Role userRole = roleRepository.findByName(RoleType.ROLE_EDITOR)
                                                      .orElseThrow(() -> new RuntimeException(
                                                              "Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}
