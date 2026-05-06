package org.elearning.courseservice.domain.entities;

import org.elearning.courseservice.domain.CourseSectionId;

import java.util.ArrayList;
import java.util.List;

public class CourseSection {
    private final CourseSectionId courseSectionId;
    private String title;
    private int sectionNumber;
    private List<CourseChapter> chapters = new ArrayList<>();

    public CourseSection(CourseSectionId courseSectionId) {
        this.courseSectionId = courseSectionId;
    }
}
