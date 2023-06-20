package com.bosonit.formacion.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "World") String name){
        return new User(1, name).toString();
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
