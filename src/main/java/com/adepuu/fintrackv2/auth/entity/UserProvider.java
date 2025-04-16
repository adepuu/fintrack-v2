package com.adepuu.fintrackv2.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("user_providers")
public class UserProvider {
  @Id
  private Integer id;

  @Column("user_id")
  private Integer userId;

  @Column("provider")
  private LoginProviders provider;

  @Column("provider_user_id")
  private String providerUserId;

  @Column("provider_access_token")
  private String providerAccessToken;

  @Column("provider_refresh_token")
  private String providerRefreshToken;

  @Column("provider_expires_at")
  private LocalDateTime providerExpiresAt;

  @Column("created_at")
  private LocalDateTime createdAt;

  @Column("updated_at")
  private LocalDateTime updatedAt;

  @Column("deleted_at")
  private LocalDateTime deletedAt;
}
