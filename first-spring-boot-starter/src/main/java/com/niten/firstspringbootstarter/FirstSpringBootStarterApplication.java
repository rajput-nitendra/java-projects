package com.niten.firstspringbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstSpringBootStarterApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FirstSpringBootStarterApplication.class, args);

        final Alien bean = context.getBean(Alien.class);
        bean.code();
    }

}
