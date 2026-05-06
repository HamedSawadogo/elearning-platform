package org.elearning.courseservice.domain.valueobjects;

public record Video(String url,
                    String path,
                    byte[] content,
                    int duration) {
}
