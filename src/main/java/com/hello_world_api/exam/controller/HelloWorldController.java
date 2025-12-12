package com.hello_world_api.exam.controller;


import com.hello_world_api.exam.model.ErrorResponse;
import com.hello_world_api.exam.model.HelloResponse;
import com.hello_world_api.exam.service.HelloWorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    private final HelloWorldService service;

    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> sayHello(@RequestParam(value = "name", required = false) String name) {

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }

        boolean valid = service.isFirstHalf(name.charAt(0));

        if (!valid) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }

        String formatted = name.substring(0, 1).toUpperCase() + name.substring(1);
        return ResponseEntity.ok(new HelloResponse("Hello " + formatted));
    }
}
