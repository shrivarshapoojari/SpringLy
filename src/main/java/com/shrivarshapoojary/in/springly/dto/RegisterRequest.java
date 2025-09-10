package com.shrivarshapoojary.in.springly.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private Set<String> role;
    private String password;
}
