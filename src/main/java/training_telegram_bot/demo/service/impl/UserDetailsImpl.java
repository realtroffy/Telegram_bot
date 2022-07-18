package training_telegram_bot.demo.service.impl;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import training_telegram_bot.demo.model.UserCredential;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private final UserCredential userCredential;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return userCredential.getPassword();
  }

  @Override
  public String getUsername() {
    return userCredential.getUsername();
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
