package org.elearning.courseservice.domain.entities;

import org.elearning.courseservice.domain.CourseChapterId;
import org.elearning.courseservice.domain.CourseResource;
import org.elearning.courseservice.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class CourseChapter {
    private final CourseChapterId courseChapterId;
    private int durationInHours;
    private String title;
    private int chapterNumber;
    private boolean isClosed = false;
    private Video video;
    private List<CourseResource> courseResources = new ArrayList<>();

    public CourseChapter(CourseChapterId courseChapterId) {
        this.courseChapterId = courseChapterId;
    }
}
