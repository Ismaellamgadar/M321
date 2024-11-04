package com.ch.tbz.iam.entity;

import lombok.Getter;

@Getter
public class UserCredentials {
    private final String username;
    private final String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
