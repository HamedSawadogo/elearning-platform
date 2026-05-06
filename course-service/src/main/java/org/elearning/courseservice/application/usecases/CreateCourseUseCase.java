package org.elearning.courseservice.application.usecases;


import org.elearning.courseservice.domain.ports.CourseRepository;

public class CreateCourseUseCase {
  private final CourseRepository courseRepository;

    public CreateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void handle(CreateCourseCommand command) {

    }
}
