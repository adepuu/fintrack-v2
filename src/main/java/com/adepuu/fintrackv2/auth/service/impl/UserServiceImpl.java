package com.adepuu.fintrackv2.auth.service.impl;

import com.adepuu.fintrackv2.auth.dto.LoginResponse;
import com.adepuu.fintrackv2.auth.entity.LoginProviders;
import com.adepuu.fintrackv2.auth.entity.Token;
import com.adepuu.fintrackv2.auth.entity.User;
import com.adepuu.fintrackv2.auth.entity.UserProvider;
import com.adepuu.fintrackv2.auth.exceptions.DuplicateUserException;
import com.adepuu.fintrackv2.auth.exceptions.LoginFailedException;
import com.adepuu.fintrackv2.auth.repository.UserProviderRepository;
import com.adepuu.fintrackv2.auth.repository.UserRepository;
import com.adepuu.fintrackv2.auth.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserProviderRepository userProviderRepository;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserProviderRepository userProviderRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userProviderRepository = userProviderRepository;
  }

  // 1. Fetch users with same email from the database and count them
  // 2. If count != 0, throw DuplicateUserException
  // 3. If count == 0, hash the password and pin
  // 4. Save the user to the database
  // 5. Return the saved user
  @Override
  @Transactional
  public User registerUser(User request) {
    Integer userWithSameEmail = userRepository.countUsersWithSameEmail(request.getEmail());
    if (userWithSameEmail != 0) {
      throw new DuplicateUserException("User with this email already exists");
    }

    request.setPassword(passwordEncoder.encode(request.getPassword()));
    request.setPin(passwordEncoder.encode(request.getPin()));
    request.setEmailVerified(false);

    LocalDateTime now = LocalDateTime.now();
    request.setUpdatedAt(now);
    request.setCreatedAt(now);

    Integer newlyInsertedUserID = userRepository.saveUser(request);
    request.setId(newlyInsertedUserID);

    UserProvider userProvider = new UserProvider();
    userProvider.setUserId(newlyInsertedUserID);
    userProvider.setProvider(LoginProviders.LOCAL);
    userProvider.setProviderUserId(newlyInsertedUserID.toString());

    userProviderRepository.saveUserProvider(userProvider);
    return request;
  }

  // 1. Fetch the user with the given email from the database
  // 2. If user is not found, throw LoginFailedException
  // 3. If user is found, check if the password matches the hashed password in the
  // database
  // 4. If password matches, return the user
  @Override
  public LoginResponse loginUser(String email, String password) {
    Optional<User> user = userRepository.findUserByEmail(email);
    if (user.isEmpty()) {
      throw new LoginFailedException("Invalid email or password");
    }

    User foundUser = user.get();
    if (!passwordEncoder.matches(password, foundUser.getPassword())) {
      throw new LoginFailedException("Invalid email or password");
    }

    return LoginResponse.builder()
        .accessToken(Token.builder().value("some.jwt.token").tokenType("Bearer").build())
        .refreshToken(Token.builder().value("some.jwt.token").tokenType("Bearer").build())
        .build();
  }
}
