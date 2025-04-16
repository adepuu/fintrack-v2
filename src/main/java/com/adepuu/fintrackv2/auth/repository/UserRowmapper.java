package com.adepuu.fintrackv2.auth.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.adepuu.fintrackv2.auth.entity.User;

public class UserRowmapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return User.builder()
        .id(rs.getInt("id"))
        .name(rs.getString("name"))
        .email(rs.getString("email"))
        .password(rs.getString("password"))
        .pin(rs.getString("pin"))
        .emailVerified(rs.getBoolean("email_verified"))
        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
        .deletedAt(rs.getTimestamp("deleted_at") != null ? rs.getTimestamp("deleted_at").toLocalDateTime() : null)
        .build();
  }
}
