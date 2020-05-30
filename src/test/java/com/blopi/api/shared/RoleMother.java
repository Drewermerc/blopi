package com.blopi.api.shared;

import com.blopi.api.user.domain.role.Role;
import com.blopi.api.user.domain.role.RoleType;

import java.util.HashSet;
import java.util.Set;

public final class RoleMother {
    public static Role admin() {
        return new Role(RoleType.ROLE_ADMIN);
    }

    public static Role editor() {
        return new Role(RoleType.ROLE_EDITOR);
    }

    public static Set<Role> createEditor() {
        Set<Role> roles = new HashSet<>();
        roles.add(editor());
        return roles;
    }

    public static Set<Role> createAdmin() {
        Set<Role> roles = new HashSet<>();
        roles.add(admin());
        return roles;
    }

    public static Set<Role> createFull() {
        Set<Role> roles = new HashSet<>();
        roles.add(admin());
        roles.add(editor());
        return roles;
    }
}
