package br.ufac.productmanager.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufac.productmanager.model.User;

public class UserProfile implements UserDetails {

    private User user;

    public UserProfile(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Talvez seja possivel usar o papel do Usuario criado pelo professor
        //como o role do nosso projeto.

        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getUsername());
        return Arrays.asList(auth);
        
        // return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isActive();
    }
    
}
