package com.liferay.avsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Leonardo Barros
 */
@SpringBootApplication
@EnableJpaAuditing
public class App {
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
}