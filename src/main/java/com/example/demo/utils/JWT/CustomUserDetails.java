package com.example.demo.utils.JWT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import lombok.Getter;

public class CustomUserDetails implements UserDetails {

    @Getter
    private final User user;
    private final Role role;
    private final java.util.Collection<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.user = user;

        // Pre-calculate roles and permissions while session is likely active (in
        // UserDetailsService)
        // KEPAKE
        this.role = user.getRole();

        // Pre-calculate authorities using role name (avoid null collection)
        String roleName = (role != null && role.getName() != null) ? role.getName() : "USER";
        this.authorities = java.util.List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
    }

    @Override
    public java.util.Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getPerson().getFirstName();
    }

    public Role getRole() {
        return role;
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
        return !Boolean.TRUE.equals(user == null);
    }
}
