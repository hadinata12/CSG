package com.application.utilities;

import org.springframework.stereotype.Component;

import com.application.common.JsonFields;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class JsonValidator {
	 public boolean validateRuleNode(JsonNode ruleNode) {
		 if (ruleNode == null) {
	            return false;
	        }

	        JsonNode nameNode = ruleNode.get(JsonFields.NAME);
	        JsonNode typeNode = ruleNode.get(JsonFields.TYPE);
	        JsonNode patternNode = ruleNode.get(JsonFields.PATTERN);

	        return nameNode != null && !nameNode.isNull() && !nameNode.asText().isEmpty()
	                && typeNode != null && !typeNode.isNull() && !typeNode.asText().isEmpty()
	                && patternNode != null && !patternNode.isNull() && !patternNode.asText().isEmpty();
	    }
	 
}
