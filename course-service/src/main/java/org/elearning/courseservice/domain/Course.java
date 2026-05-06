package org.elearning.courseservice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Getter
public class Course {
    private final CourseId courseId;
    private String title;
    private String description;
    private final List<CourseSection> courseSections;
    private boolean isDisabled;

    private Course(CourseId courseId, String title, String description, boolean isDisabled, List<CourseSection> courseSections) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.isDisabled = isDisabled;
        this.courseSections = new ArrayList<>(courseSections);
    }

    public static Course create(String title, String description, boolean isDisabled, List<CourseSection> courseSections) {
        validateInputs(title, description, isDisabled, courseSections);
        validateBusinessRules(courseSections);

        return new Course(CourseId.generate(), title, description, isDisabled, courseSections);
    }

    public void addLessonToSection(CourseSectionId sectionId, CourseChapter courseChapter) {
        final CourseSection section = courseSections.stream().filter(courseSection -> courseSection.getCourseSectionId().equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleViolationException("Section introuvable"));
        section.addChapter(courseChapter);
    }

    public void addCourseSection(CourseSection courseSection) {
        Objects.requireNonNull(courseSection, "La section ne doit pas etre nulle");
        if (!courseSections.contains(courseSection)) {
            courseSections.add(courseSection);
        }
    }


    public int getCourseProgression() {
        return courseSections.stream().mapToInt(CourseSection::getSectionProgressionInPercent).sum();
    }

    private int getNumberOfCourses() {
        return courseSections.stream().mapToInt(CourseSection::getNumberOfCourses).sum();
    }

    public int getCourseDuration() {
        return courseSections.stream().mapToInt(CourseSection::getCourseDuration).sum();
    }

    private static void validateBusinessRules(List<CourseSection> sections) {
        if (sections == null || sections.isEmpty()) {
            throw new BusinessRuleViolationException("Le cours doit contenir au moins une section");
        }

        for (CourseSection section : sections) {
            if (section.getNumberOfCourses() <= 0) {
                throw new BusinessRuleViolationException(
                        String.format("La section %d doit contenir des chapitres", section.getSectionNumber())
                );
            }
        }
    }

    public List<CourseSection> getCourseSections() {
        return Collections.unmodifiableList(courseSections);
    }

    private static void validateInputs(String title, String description, boolean isDisabled, List<CourseSection> courseSections) {

    }

    public void validateChapter(CourseSectionId courseSectionId, CourseChapterId courseChapterId) {
        CourseSection section = courseSections.stream().filter(courseSection -> courseSection.getCourseSectionId().equals(courseSectionId)).findFirst().orElseThrow();
        CourseChapter chapter = section.getChapter(courseChapterId);
        chapter.markAsClosed();
    }
}
