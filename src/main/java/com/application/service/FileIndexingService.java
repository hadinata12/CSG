package com.application.service;

public interface FileIndexingService {
	 void countMatches(String ruleName, String pattern, String filePath);
	 void listMatches(String ruleName, String pattern, String filePath);
}
