package com.workshop3_pre.workshop3_pre;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop3PreApplication {


    
    //mvn clean package spring-boot:run -Dspring-boot.run.arguments="--dataDir=opt/tmp/data"
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Workshop3PreApplication.class, args);
		
              

}
}



