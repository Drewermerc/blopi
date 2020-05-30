package com.blopi.api.domain.role;

import com.blopi.api.user.domain.role.Role;
import com.blopi.api.user.domain.role.RoleRepository;
import com.blopi.api.user.domain.role.RoleType;
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
public class RoleTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void StoreRoleAdmin() {
        Role role = new Role(RoleType.ROLE_EDITOR);

        Role stored = roleRepository.save(role);

        assertThat(stored.getName()).isEqualTo(RoleType.ROLE_EDITOR);
    }

    @Test
    public void findRoleByName() {
        Optional<Role> found = roleRepository.findByName(RoleType.ROLE_EDITOR);
        assertThat(found.get().getName()).isEqualTo(RoleType.ROLE_EDITOR);
    }
}
