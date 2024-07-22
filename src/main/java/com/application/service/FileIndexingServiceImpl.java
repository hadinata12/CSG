package com.application.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.application.common.ErrorMessage;

@Service
public class FileIndexingServiceImpl implements FileIndexingService {
	
	private static final Logger logger = LogManager.getLogger(FileIndexingServiceImpl.class);
	
	/**
	 * Counts the number of matches of a specified pattern in a file.
	 *
	 * @param ruleName   The name of the rule being applied.
	 * @param pattern    The regex pattern to match.
	 * @param filePath   The path to the file to be analyzed.
	 */
	
	@Override
	public void countMatches(String ruleName, String pattern, String filePath) {
		int count = 0;
		    Pattern regex = Pattern.compile(pattern);

		    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            Matcher matcher = regex.matcher(line);
		            while (matcher.find()) {
		                count++;
		            }
		        }
		        
		        logger.info("Rule '{}' for file '{}': Found {} matches.", ruleName, filePath, count);
		    } catch (IOException e) {
		    	logger.error(ErrorMessage.FILE_ACCESS_ERROR_MESSAGE_FORMAT, filePath, ruleName, e.getMessage());
		    }
		    
		   
	}

	/**
	 * Lists all occurrences of a specified pattern in a file.
	 *
	 * @param ruleName   The name of the rule being applied.
	 * @param pattern    The regex pattern to match.
	 * @param filePath   The path to the file to be analyzed.
	 */
	
	@Override
	public void listMatches(String ruleName, String pattern, String filePath) {
		 Pattern regex = Pattern.compile(pattern);

		    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		        String line;
		        logger.info("Rule '{}' for file '{}': Words matching pattern '{}':", ruleName, filePath, pattern);
		     
		        while ((line = reader.readLine()) != null) {
		            Matcher matcher = regex.matcher(line);
		            while (matcher.find()) {
		            	logger.info(matcher.group());
		            }
		        }
		    } catch (IOException e) {
		    	logger.error(ErrorMessage.FILE_ACCESS_ERROR_MESSAGE_FORMAT, filePath, ruleName, e.getMessage());
		    }
		
	}

}
