package com.adepuu.fintrackv2.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("users")
public class User {
  @Id
  private Integer id;

  @Column("name")
  private String name;

  @Column("email")
  private String email;

  @Column("password")
  private String password;

  @Column("pin")
  private String pin;

  @Column("email_verified")
  private Boolean emailVerified;

  @Column("created_at")
  private LocalDateTime createdAt;

  @Column("updated_at")
  private LocalDateTime updatedAt;

  @Column("deleted_at")
  private LocalDateTime deletedAt;
}
