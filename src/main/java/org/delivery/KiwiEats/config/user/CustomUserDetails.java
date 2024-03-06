package org.delivery.KiwiEats.config.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.delivery.KiwiEats.entities.roles.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
  private final String username;
  private final String password;
  private final List<GrantedAuthority> authorities;

  public CustomUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities =
        user.getRoles().stream()
            .map((role) -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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
