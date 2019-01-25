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

import java.util.List;
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
  public AppUser save(AppUser user) {
      return userRepository.save(user);
  }

  @Override
  public AppUser createDefaultUser(RegistrationForm regForm) throws IllegalArgumentException {
    if (userExists(regForm.getEmail(), regForm.getUsername())) {
      throw new IllegalArgumentException("User already exists");
    }
    String encodedPassword = passwordEncoder.encode(regForm.getPassword());
    AppUser newUser = new AppUser(
                            regForm.getUsername(),
                            encodedPassword,
                            true, regForm.getEmail());

    newUser.addAuthority(getDefaultAuthority());

    return userRepository.save(newUser);
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

  public List<AppUser> allUsers() {
    return userRepository.findAll();
  }

  private boolean userExists(String email, String username) {
    for (int i = 0; i < allUsers().size(); i++) {
      if (allUsers().get(i).getEmail().equals(email) || allUsers().get(i).getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }
}
