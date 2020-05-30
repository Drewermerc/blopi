package com.blopi.api.auth.application.userdetails;

import com.blopi.api.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long                                   serialVersionUID = 1L;
    private final        String                                 Username;
    private final        String                                 email;
    @JsonIgnore
    private final        String                                 password;
    private final        Collection<? extends GrantedAuthority> authorities;
    private              long                                   id;

    public UserDetailsImpl(String Username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.Username = Username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                                                 .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                 .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return Objects.equals(id, userDetailsImpl.id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
