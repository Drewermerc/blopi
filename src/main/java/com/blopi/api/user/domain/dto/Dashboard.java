package com.blopi.api.user.domain.dto;


import com.blopi.api.user.domain.role.Role;

import java.io.Serializable;
import java.util.Set;

final public class Dashboard implements Serializable {
    private String    userName;
    private String    email;
    private Set<Role> roles;

    public Dashboard() {
    }

    public Dashboard(Builder builder) {
        this.userName = builder.userName;
        this.email = builder.email;
        this.roles = builder.roles;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public static class Builder {
        private String    userName;
        private String    email;
        private Set<Role> roles;

        public Builder() {
        }

        public Builder userName(String userNAme) {
            this.userName = userNAme;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Dashboard build() {
            return new Dashboard(this);
        }
    }
}
