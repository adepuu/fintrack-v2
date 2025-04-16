package com.adepuu.fintrackv2.auth.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private String value;
    private String tokenType = "Bearer";
}
