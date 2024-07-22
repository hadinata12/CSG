package com.application.main;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.application")
public class FileAnalyzerApplication {
	
	public static void main(String[] args) {
        new SpringApplicationBuilder(FileAnalyzerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
