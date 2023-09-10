package com.bs.WaterMeterAPI.Controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/hello")
    @CrossOrigin(origins = "http://localhost:8000")
    public String getHello() {
        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.toString();
        
    }
}
