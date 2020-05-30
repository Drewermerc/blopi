package com.blopi.api.auth.application;

import com.blopi.api.auth.application.signup.SignUpImpl;
import com.blopi.api.auth.domain.request.SignUpRequest;
import com.blopi.api.shared.SignUpRequestMother;
import com.blopi.api.user.domain.User;
import com.blopi.api.user.domain.UserRepository;
import com.blopi.api.user.domain.role.Role;
import com.blopi.api.user.domain.role.RoleRepository;
import com.blopi.api.user.domain.role.RoleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class SignUpImplTest {

    @Mock
    private UserRepository  userRepository;
    @Mock
    private RoleRepository  roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private SignUpImpl      signUp;


    @Test
    public void verifiedIfUserExist() {
        SignUpRequest  request    = SignUpRequestMother.random();
        String         response   = signUp.existUser(request);
        User           newUser    = signUp.createNewUser(request);
        Optional<Role> adminRole  = Optional.of(new Role(RoleType.ROLE_ADMIN));
        Optional<Role> editorRole = Optional.of(new Role(RoleType.ROLE_EDITOR));

        Mockito.when(roleRepository.findByName(RoleType.ROLE_ADMIN))
               .thenReturn(adminRole);
        Mockito.when(roleRepository.findByName(RoleType.ROLE_EDITOR))
               .thenReturn(editorRole);

        signUp.addRole(request, newUser);
    }
}
