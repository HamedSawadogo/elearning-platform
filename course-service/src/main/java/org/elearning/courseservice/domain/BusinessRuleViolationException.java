package org.elearning.courseservice.domain;

public class BusinessRuleViolationException extends RuntimeException {
  public BusinessRuleViolationException(String message) {
    super(message);
  }
}
