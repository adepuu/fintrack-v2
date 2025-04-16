package com.adepuu.fintrackv2.auth.repository;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adepuu.fintrackv2.auth.entity.User;

@Repository
public class UserRepository {
  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Integer countUsersWithSameEmail(String email) {
    String sql = "SELECT COUNT(*) as count FROM users WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, Integer.class, email);
  }

  public Integer saveUser(User user) {
    String sql = "INSERT INTO users (name, email, password, pin, email_verified, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
    return jdbcTemplate.queryForObject(sql, Integer.class, new Object[] {
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getPin(),
        user.getEmailVerified(),
        user.getCreatedAt(),
        user.getUpdatedAt()
    });
  }

  public Optional<User> findUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";
    return jdbcTemplate.query(sql, new UserRowmapper(), email).stream().findFirst();
  }
}
