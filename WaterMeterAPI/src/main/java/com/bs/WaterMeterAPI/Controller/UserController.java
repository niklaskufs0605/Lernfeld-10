package com.bs.WaterMeterAPI.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.WaterMeterAPI.Database.Operations.SelectOperations;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/getMails")
    @CrossOrigin(origins = "http://localhost:8000")
    public String getUserMails() {
        List<String> mails = SelectOperations.getUserMails();
        Gson gson = new Gson();
        return gson.toJson(mails);
    }
}
