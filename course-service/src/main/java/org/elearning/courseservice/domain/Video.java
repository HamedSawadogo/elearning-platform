package org.elearning.courseservice.domain;

public record Video(String url,
                    String path,
                    byte[] content,
                    int duration) {
}
