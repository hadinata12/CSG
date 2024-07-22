package com.application.utilities;

import com.application.common.JsonFields;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JsonValidatorTest {

    @Mock
    private JsonNode mockJsonNode;
    
//    private JsonValidator jsonValidator;
//    private ObjectMapper objectMapper;
    
//    @Before
//    public void setUp() {
//    	objectMapper = new ObjectMapper();
//        jsonValidator = new JsonValidator();
//        mockJsonNode = mock(JsonNode.class); // Menginisialisasi mock JsonNode
//    }
    
    @Test
    public void testValidateRuleNode_ValidRuleNode_ReturnsTrue() {
        // Prepare a valid JsonNode
        ObjectNode ruleNode = JsonNodeFactory.instance.objectNode();
        ruleNode.put(JsonFields.NAME, "TestName");
        ruleNode.put(JsonFields.TYPE, "TestType");
        ruleNode.put(JsonFields.PATTERN, "TestPattern");

        // Mock behavior of mockJsonNode
        when(mockJsonNode.get(JsonFields.NAME)).thenReturn(ruleNode.get(JsonFields.NAME));
        when(mockJsonNode.get(JsonFields.TYPE)).thenReturn(ruleNode.get(JsonFields.TYPE));
        when(mockJsonNode.get(JsonFields.PATTERN)).thenReturn(ruleNode.get(JsonFields.PATTERN));

        JsonValidator jsonValidator = new JsonValidator();
        // Call validateRuleNode method
        boolean isValid = jsonValidator.validateRuleNode(mockJsonNode);

        // Assert that the validation result is true
        assertTrue(isValid);
    }

    @Test
    public void testValidateRuleNode_NullRuleNode_ReturnsFalse() {
        // Mock behavior of mockJsonNode to return null
        when(mockJsonNode.get(JsonFields.NAME)).thenReturn(null);
        when(mockJsonNode.get(JsonFields.TYPE)).thenReturn(null);
        when(mockJsonNode.get(JsonFields.PATTERN)).thenReturn(null);

        // Create an instance of JsonValidator
        JsonValidator jsonValidator = new JsonValidator();

        // Call validateRuleNode method
        boolean isValid = jsonValidator.validateRuleNode(mockJsonNode);

        // Assert that the validation result is false
        assertFalse(isValid);
    }

    @Test
    public void testValidateRuleNode_MissingFieldInRuleNode_ReturnsFalse() {
        // Prepare a JsonNode with missing fields
        ObjectNode ruleNode = JsonNodeFactory.instance.objectNode();
        ruleNode.put(JsonFields.NAME, "WrongName");
        // Missing typeNode and patternNode

        // Mock behavior of mockJsonNode
        when(mockJsonNode.get(JsonFields.NAME)).thenReturn(ruleNode.get(JsonFields.NAME));
        when(mockJsonNode.get(JsonFields.TYPE)).thenReturn(null); // Simulate missing typeNode
        when(mockJsonNode.get(JsonFields.PATTERN)).thenReturn(null); // Simulate missing patternNode

        // Create an instance of JsonValidator
        JsonValidator jsonValidator = new JsonValidator();

        // Call validateRuleNode method
        boolean isValid = jsonValidator.validateRuleNode(mockJsonNode);

        // Assert that the validation result is false
        assertFalse(isValid);
    }
}
