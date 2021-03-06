package br.com.rgrassi.events.security.user;

import br.com.rgrassi.events.modules.users.mongodb.entities.ApplicationUser;
import br.com.rgrassi.events.modules.users.mongodb.repositories.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
  private final ApplicationUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                    String.format("User '%s' not found", username)
            ));

    return new UserDetailsImpl(applicationUser);
  }
}
