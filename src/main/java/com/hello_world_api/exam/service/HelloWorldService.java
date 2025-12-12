package com.hello_world_api.exam.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public boolean isFirstHalf(char c) {
        char ch = Character.toLowerCase(c);
        return ch >= 'a' && ch <= 'm';
    }
}
