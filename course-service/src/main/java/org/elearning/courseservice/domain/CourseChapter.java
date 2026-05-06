package org.elearning.courseservice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class CourseChapter {
    private final CourseChapterId courseChapterId;
    private int durationInHours;
    private String title;
    private int chapterNumber;
    private boolean isClosed = false;
    private Video video;
    private List<CourseResource> courseResources = new ArrayList<>();

    CourseChapter(CourseChapterId courseChapterId) {
        this.courseChapterId = courseChapterId;
    }

    public static CourseChapter create(int durationInHours, String title, int chapterNumber, Video video, List<CourseResource> courseResources) {

        validateInputs(durationInHours, title, chapterNumber, video, courseResources);

        CourseChapter courseChapter = new CourseChapter(CourseChapterId.generate());
        courseChapter.chapterNumber = chapterNumber;
        courseChapter.durationInHours = durationInHours;
        courseChapter.isClosed = false;
        courseChapter.video = video;
        courseChapter.courseResources = courseResources;

        validateBusinessRules(durationInHours, title, chapterNumber, video, courseResources);
        return courseChapter;
    }

    private static void validateBusinessRules(int durationInHours, String title, int chapterNumber, Video video, List<CourseResource> courseResources) {

    }

    private static void validateInputs(int durationInHours, String title, int chapterNumber, Video video, List<CourseResource> courseResources) {

    }

    public void markAsClosed() {
        this.isClosed = !this.isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CourseChapter that)) return false;
        return durationInHours == that.durationInHours && chapterNumber == that.chapterNumber && isClosed == that.isClosed && Objects.equals(courseChapterId, that.courseChapterId) && Objects.equals(title, that.title) && Objects.equals(video, that.video) && Objects.equals(courseResources, that.courseResources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseChapterId, durationInHours, title, chapterNumber, isClosed, video, courseResources);
    }
}
