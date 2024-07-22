package com.application.service;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import static org.mockito.Mockito.*;

class FileIndexingServiceImplTest {
    
	@Before
	private void before() {
		System.err.println("sdfsd");
	}
	
   @Test
    void countMatches_NoIOException() throws IOException {
        // Test data
        String ruleName = "TestRule";
        String pattern = "\\d+";
        String filePath = "C:\\Documents\\sample.txt";

        FileIndexingServiceImpl xxx = mock(FileIndexingServiceImpl.class);
        xxx.countMatches(ruleName, pattern, filePath);
     
        verify(xxx, times(1)).countMatches(ruleName, pattern, filePath);
    }
   
   @Test
   void listMatches_NoIOException() throws IOException {
       // Test data
       String ruleName = "TestRule";
       String pattern = "\\d+";
       String filePath = "C:\\Documents\\sample.txt";

       FileIndexingServiceImpl xxx2 = mock(FileIndexingServiceImpl.class);
       xxx2.listMatches(ruleName, pattern, filePath);
    
       verify(xxx2, times(1)).listMatches(ruleName, pattern, filePath);
   }

  
}
