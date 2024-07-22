package com.application.controller;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.application.common.ErrorMessage;
import com.application.common.JsonFields;
import com.application.common.OperationType;
import com.application.service.FileIndexingService;
import com.application.utilities.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileAnalyzerController {
	
	private static final Logger logger = LogManager.getLogger(FileAnalyzerController.class);
	
	private final FileIndexingService fileIndexingService;
	private final ObjectMapper objectMapper;
	private final JsonValidator jsonValidator;
	
	@Value("${indexing.rules.file.path}")
    private String indexingRulesFilePath;
	
	@Autowired
	public FileAnalyzerController(FileIndexingService fileIndexingService, ObjectMapper objectMapper,
			JsonValidator jsonValidator) {
		this.fileIndexingService = fileIndexingService;
		this.objectMapper = objectMapper;
		this.jsonValidator = jsonValidator;
	}

	public void analyzeFiles(String[] filePathInput) throws IOException, IllegalArgumentException {
		// Read JSON rules from file
		JsonNode rootNode = objectMapper.readTree(new File(indexingRulesFilePath));
		JsonNode rulesNode = rootNode.get(JsonFields.RULES);
		
		// Validate rules node
		if (rulesNode == null || !rulesNode.isArray()) {
			throw new IllegalArgumentException(ErrorMessage.INVALIDRULESNODES_ERRMSG);
		}
		
		// Process each rule
		for (JsonNode ruleNode : rulesNode) {
			// Validate JSON fields
			if (!jsonValidator.validateRuleNode(ruleNode)) {
				throw new IllegalArgumentException(ErrorMessage.INVALIDJSONFIELDS_MSG);
			}
			
			// Extract rule details
			String name = ruleNode.get(JsonFields.NAME).asText();
			String type = ruleNode.get(JsonFields.TYPE).asText();
			String pattern = ruleNode.get(JsonFields.PATTERN).asText();

			OperationType operationType = OperationType.valueOf(type.toUpperCase());
			
			// Process each file path based on operation type
			for (String filePath : filePathInput) {
				switch (operationType) {
				case COUNT:
					fileIndexingService.countMatches(name, pattern, filePath);
					break;
				case LIST:
					fileIndexingService.listMatches(name, pattern, filePath);
					break;
				default:
					// Handle unknown operation type
					throw new IllegalArgumentException(ErrorMessage.INVALIDOPERATIONTYPE_MSG + operationType);
				}
			}
			
			logger.info("##################################################");
		}
	}
}
