package com.bs.WaterMeterAPI.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String uuid;

    public User(String email, String password){
    this.email = email;
    this.password = password;
    }

    public User(String email){
        this.email = email;
    }
}


