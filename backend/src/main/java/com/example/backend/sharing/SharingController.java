package com.example.backend.sharing;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SharingController {
    @GetMapping("/sharing")
    public String index(){
        return "Hello world 2";
    }
}
