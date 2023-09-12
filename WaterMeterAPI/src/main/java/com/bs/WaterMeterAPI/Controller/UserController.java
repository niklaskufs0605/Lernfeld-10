package com.bs.WaterMeterAPI.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.WaterMeterAPI.Database.Operations.InsertOperations;
import com.bs.WaterMeterAPI.Database.Operations.SelectOperations;
import com.bs.WaterMeterAPI.Models.User;
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

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User data) {
        InsertOperations.insertUser(data.getEmail(), data.getPassword(), UUID.randomUUID().toString());
        String response = "Daten empfangen.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/isUserValid")
    public ResponseEntity<String> isUserValid(@RequestBody User data){
        String response = String.valueOf(SelectOperations.isUserValid(data.getEmail(), data.getPassword()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getUUIDbyEmail")
    public ResponseEntity<String> getUUIDbyEmail(@RequestBody User data){
        String response = SelectOperations.getUUIDByEmail(data.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
