package org.elearning.courseservice.domain;

import java.util.UUID;

public record CourseId(UUID id) {
    public static CourseId  generate() {
        return new CourseId(UUID.randomUUID());
    }
}
