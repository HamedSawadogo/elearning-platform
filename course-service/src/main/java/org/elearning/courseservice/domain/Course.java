package org.elearning.courseservice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Course {
    private final CourseId courseId;
    private String tile;
    private String description;
    private List<CourseSection> courseSections = new ArrayList<>();
    private boolean isDisabled;

    private Course(CourseId courseId) {
        this.courseId = courseId;
    }

    public static Course create(String tile, String description, boolean isDisabled, List<CourseSection> courseSections) {
        validateInputs(tile, description, isDisabled, courseSections);

        Course course = new Course(CourseId.generate());
        course.courseSections = List.copyOf(courseSections);
        course.description = description;
        course.isDisabled = isDisabled;
        course.tile = tile;

        validateBusinessRules(courseSections);
        return course;
    }

    public void addLessonToSection(CourseSectionId sectionId, CourseChapter courseChapter) {
        final CourseSection section = courseSections.stream().filter(courseSection -> courseSection.getCourseSectionId().equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleViolationException("Section introuvable"));
        section.addChapter(courseChapter);
    }

    public void addCourseSection(CourseSection courseSection) {
        if (courseSection != null && !(courseSections.contains(courseSection))) {
            courseSections.add(courseSection);
        }
    }


    public int getCourseProgression() {
        return courseSections.stream().map(CourseSection::getSectionProgressionInPercent).reduce(0, Integer::sum);
    }

    private int getNumberOfCourses() {
        return courseSections.stream().map(CourseSection::getNumberOfCourses).reduce(0, Integer::sum);
    }

    public int getCourseDuration() {
        return courseSections.stream().map(CourseSection::getCourseDuration).reduce(0, Integer::sum);
    }

    private static void validateBusinessRules(List<CourseSection> sections) {
        List<String> failluresMessages = new ArrayList<>();
        if (sections == null || sections.isEmpty()) {
            failluresMessages.add("Le cours doit contenir des sections");
        } else {
            sections.forEach(courseSection -> {
                if (courseSection.getNumberOfCourses() <= 0) {
                    failluresMessages.add(String.format("La section %d doit contenir des cours", courseSection.getSectionNumber()));
                }
            });
        }

        if (!failluresMessages.isEmpty()) {
            throw new BusinessRuleViolationException(failluresMessages);
        }
    }

    private static void validateInputs(String tile, String description, boolean isDisabled, List<CourseSection> courseSections) {

    }



    public void validateChapter(CourseSectionId courseSectionId, CourseChapterId courseChapterId) {
        CourseSection section = courseSections.stream().filter(courseSection -> courseSection.getCourseSectionId().equals(courseSectionId)).findFirst().orElseThrow();
        CourseChapter chapter = section.getChapter(courseChapterId);
        chapter.markAsClosed();
    }
}
