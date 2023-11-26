package com.example.demo.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String login;

    private String password;
}