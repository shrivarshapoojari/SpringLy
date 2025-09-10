package com.shrivarshapoojary.in.springly.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private String email;
    private String username;
    private String password;
    private String role ="ROLE_USER";
}
