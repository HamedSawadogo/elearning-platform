package org.elearning.courseservice.domain;

import java.util.List;

public class BusinessRuleViolationException extends RuntimeException {
    private List<String> messages;
    public BusinessRuleViolationException(String message) {
        super(message);
    }

    public BusinessRuleViolationException(List<String> messages) {
        super(String.join(",", messages));
        this.messages = messages;
    }
}
