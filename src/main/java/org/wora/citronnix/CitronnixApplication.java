package org.wora.citronnix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitronnixApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitronnixApplication.class, args);
        System.out.println("Citronnix started");
    }

}
