package com.bosonit.formacion.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {
    private int counter = 1;
    @GetMapping("/greeting")
    public User greeting(@RequestParam(defaultValue = "World") String name){
        return new User(counter++, name);
    }
    @PostMapping("/json")
    public String json(@RequestBody String json){
        return json;
    }
    @GetMapping("/user/{id}")
    public int returnId(@PathVariable("id") int id){
        return id;
    }
    @PutMapping("/post")
    public HashMap returnMap(@RequestParam HashMap<String, String> hashMap){
        return hashMap;
    }
}
