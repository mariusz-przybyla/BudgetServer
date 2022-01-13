package BudzetServer.BudzetServer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;


@Entity
public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(User user)
    {
        this.setId(user.getId());
        this.setLogin(user.getLogin());
        this.setPassword(user.getPassword());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getUsername()
    {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
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
