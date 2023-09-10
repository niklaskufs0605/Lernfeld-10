package com.bs.WaterMeterAPI.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String uuid;
}
