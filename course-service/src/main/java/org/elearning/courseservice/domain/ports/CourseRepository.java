package org.elearning.courseservice.domain.ports;

import org.elearning.courseservice.domain.Course;

public interface CourseRepository {
    Course save(Course course);
}
