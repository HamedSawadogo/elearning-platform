package org.elearning.courseservice.domain;

import java.util.UUID;

record CourseChapterId(UUID id) {
    public static CourseChapterId  generate() {
        return new CourseChapterId(UUID.randomUUID());
    }
}
