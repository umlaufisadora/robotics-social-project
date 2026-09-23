package br.com.srobots_satlas.mini_s.auth.decorator;

import br.com.srobots_satlas.mini_s.user.entity.User;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SecurityUser implements UserDetails
{
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getAuthority()));
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public @Nullable String getPassword()
    {
        return user.getPassword();
    }

    // ------------------------- DEIXAR TRUE POR ENQUANTO -------------------------

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
