package com.application.configuration;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.application.common.ErrorMessage;
import com.application.controller.FileAnalyzerController;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApplicationConfig {
	
	private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(FileAnalyzerController fileAnalyzerController) {
		return args -> {
			try {
				// Check if command line arguments are provided
				if (args.length == 0) {
					throw new IllegalArgumentException(ErrorMessage.INVALIDARGUMENTS_MSG);
				}
				
				// Call analyzeFiles method in the controller to process files
				fileAnalyzerController.analyzeFiles(args);
			} catch (IllegalArgumentException e) {
				// Catch and log IllegalArgumentException
				logger.error("IllegalArgumentException in FileAnalyzerApplication : {}", e.getMessage());
				System.exit(1);
			} catch (IOException e) {
				// Catch and log IOException when reading or accessing 'indexing_rules.json'
				logger.error("Error reading or accessing file 'indexing_rules.json': {}", e.getMessage());
				System.exit(1);
			} catch (Exception e) {
				// Catch and log any other exceptions
				logger.error("Error running FileAnalyzerApplication: {} ", e.getMessage());
				System.exit(1);
			}
		};
	}
}
