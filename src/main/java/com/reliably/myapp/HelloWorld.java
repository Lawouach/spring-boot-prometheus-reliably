package com.reliably.myapp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
public class HelloWorld {

	@GetMapping("/")
	public String index() {
        float pivot = ThreadLocalRandom.current().nextFloat();

        // fake failing handler
        if (pivot >= 0.99) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // fake latency
        try {
            TimeUnit.MILLISECONDS.sleep((int)(pivot * 1000));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    
		return "Greetings from Spring Boot!";
	}

}