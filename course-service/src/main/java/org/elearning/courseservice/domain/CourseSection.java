package org.elearning.courseservice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class CourseSection {
    private final CourseSectionId courseSectionId;
    private String title;
    private int sectionNumber;
    private List<CourseChapter> chapters;


    CourseSection(CourseSectionId courseSectionId, String title, int sectionNumber, List<CourseChapter> chapters) {
        this.courseSectionId = courseSectionId;
        this.title = title;
        this.sectionNumber = sectionNumber;
        this.chapters = chapters;
    }


    public void addChapter(CourseChapter courseChapter) {
        if (courseChapter != null) {
            chapters.add(courseChapter);
        }
    }

    public int getCourseDuration() {
        return chapters.stream().map(CourseChapter::getDurationInHours).reduce(0, Integer::sum);
    }

    public CourseChapter getChapter(CourseChapterId courseChapterId) {
        return chapters.stream().filter(courseChapter -> courseChapter.getCourseChapterId().equals(courseChapterId)).findFirst()
                .orElseThrow();
    }

    public int getNumberOfCourses() {
        return chapters.size();
    }

    public int getSectionProgressionInPercent() {
        return (int)((getNumberOfChapterClosed() / (float)this.getNumberOfCourses()) * 100);
    }

    private int getNumberOfChapterClosed() {
        return Math.toIntExact(chapters.stream().filter(CourseChapter::isClosed).count());
    }
}
