package dev.ulman.dmsapi.security;

import dev.ulman.dmsapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.user.getPermissionsList().forEach(p ->{
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });
        this.user.getRolesList().forEach(p ->{
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //not implementen in model
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//not implementen in model
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//not implementen in model
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActive();
    }
}
