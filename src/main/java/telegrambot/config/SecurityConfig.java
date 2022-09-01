package telegrambot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import telegrambot.service.UserVisitService;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  public static final int DEFAULT_COUNT_ENTITY_ON_PAGE = 20;

  private final UserVisitService userVisitService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf()
        .ignoringAntMatchers("/update")
        .and()
        .authorizeHttpRequests()
        .antMatchers("/update", "/login", "/error", "/css/**", "/js/**", "/webjars/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/process_login")
        .defaultSuccessUrl(
            "/users?page=" + userVisitService.countAllUserWriteBot() / DEFAULT_COUNT_ENTITY_ON_PAGE,
            true)
        .failureUrl("/login?error")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout");

    return http.build();
  }
}
