package com.adepuu.fintrackv2.auth.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adepuu.fintrackv2.auth.entity.UserProvider;

@Repository
public class UserProviderRepository {
  private final JdbcTemplate jdbcTemplate;

  public UserProviderRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Can insert without provider, but not with provider
  public void saveUserProvider(UserProvider userProvider) {
    String sql = "INSERT INTO user_providers (user_id, provider_user_id) VALUES (?, ?)";
    jdbcTemplate.update(conn -> {
      var ps = conn.prepareStatement(sql);
      ps.setInt(1, userProvider.getUserId());
      ps.setString(2, userProvider.getProviderUserId());
      return ps;
    });
  }

  // Failed to insert because of enum, why????
  // public void saveUserProvider(UserProvider userProvider) {
  //   String sql = "INSERT INTO user_providers (user_id, provider, provider_user_id) VALUES (?, ?, ?)";
  //   jdbcTemplate.update(conn -> {
  //     var ps = conn.prepareStatement(sql);
  //     ps.setInt(1, userProvider.getUserId());
  //     ps.setString(2, userProvider.getProvider().name().toUpperCase());
  //     ps.setString(3, userProvider.getProviderUserId());
  //     return ps;
  //   });
  // }
}
