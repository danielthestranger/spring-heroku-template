package com.greenfoxacademy.springherokutemplate.service.security;

import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import com.greenfoxacademy.springherokutemplate.model.security.Authority;
import com.greenfoxacademy.springherokutemplate.model.security.KnownAuthorities;
import com.greenfoxacademy.springherokutemplate.repository.security.AppUserRepository;
import com.greenfoxacademy.springherokutemplate.repository.security.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

  private AppUserRepository userRepository;
  private AuthorityRepository authorityRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public AppUserServiceImpl(AppUserRepository userRepository,
                            AuthorityRepository authorityRepository,
                            PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.authorityRepository = authorityRepository;
      this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AppUser createDefaultUser(RegistrationForm regForm) {
    String encodedPassword = passwordEncoder.encode(regForm.getPassword());
    AppUser newUser = new AppUser(
                            regForm.getUsername(),
                            encodedPassword,
                            true);

    //TODO validate: user doesn't already exist

    newUser.addAuthority(getDefaultAuthority());

    return userRepository.save(newUser);
  }

  @Override
  public AppUser fromPrincipal(Principal principal) {
    String username = principal.getName();
    Optional<AppUser> user = userRepository.findByUserName(username);

    //TODO handle user not found

    return user.get();
  }

  private Authority getDefaultAuthority() {
    Optional<Authority> defaultAuthority = authorityRepository.findByAuthority(KnownAuthorities.ROLE_USER);

    if (!defaultAuthority.isPresent())
    {
      authorityRepository.save(new Authority(KnownAuthorities.ROLE_USER));
      return authorityRepository.findByAuthority(KnownAuthorities.ROLE_USER).get();
    }

    return defaultAuthority.get();
  }
}
