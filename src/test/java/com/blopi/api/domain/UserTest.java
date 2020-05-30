package com.blopi.api.domain;


import com.blopi.api.shared.UserMother;
import com.blopi.api.user.domain.User;
import com.blopi.api.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void storeUser() {
        User user = UserMother.random();

        User stored = userRepository.save(user);

        assertThat(stored).isNotNull();
        assertThat(stored).isEqualTo(user);
    }

    @Test
    public void findUserByName() {
        User user = UserMother.random();

        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername(user.getUsername());

        assertThat(found).isNotNull();
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }
}
