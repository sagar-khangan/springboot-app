package com.att.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import com.att.api.AppController.*;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "API Start!" );
        SpringApplication.run(AppController.class, args);
    }
}
