package org.elearning.courseservice.domain.valueobjects;

public record CourseResource(String url,
                             String path,
                             byte[] content) {
}
