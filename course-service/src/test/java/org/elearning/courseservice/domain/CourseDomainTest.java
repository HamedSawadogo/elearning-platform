package org.elearning.courseservice.domain;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseDomainTest {
    CourseSection courseSection;

    @BeforeEach
    void setUp() {
        courseSection = new CourseSection(new CourseSectionId(UUID.randomUUID()), "Maitrisez les bases de l'injection de dépendences", 1,
                List.of(
                        CourseChapter.create(1, "Comprendre l'injection de dépendences", 1, null, null),
                        CourseChapter.create(1, "Comprendre Les base de l'inversion de controle", 2, null, null)
                ));

    }

    @Test
    @DisplayName("doit créer un cours avec toute les informations valides")
    void when_create_new_course_with_one_section_and_one_chapter_should_be_created() {

        Course course = Course.create("Apprendre Spring boot", "Devenez un expert en java Spring boot", false, List.of(courseSection));

        assertThat(course).isNotNull();
        assertThat(course.getCourseId()).isNotNull();
        assertThat(course.getCourseDuration()).isEqualTo(2);
        assertThat(course.getDescription()).isEqualTo("Devenez un expert en java Spring boot");
        assertThat(course.getTile()).isEqualTo("Apprendre Spring boot");
    }

    @Test
    @DisplayName("doit retourner le progression actuel apres avoir validé un cours 1 cours sur 2")
    void when_calculate_course_progression_for_valid_course_should_passed() {
        Course course = Course.create("Apprendre Spring boot",
                "Devenez un expert en java Spring boot",
                false,
                List.of(courseSection)
        );
        var chapterId = courseSection.getChapters().get(0).getCourseChapterId();
        course.validateChapter(courseSection.getCourseSectionId(), chapterId);
        assertThat(course.getCourseProgression()).isEqualTo(50);
    }


    @Test
    @DisplayName("creation d'une cours sans chapitre doit échouer")
    void when_create_new_course_without_section_and_one_chapter_should_failed() {

        assertThatThrownBy(() -> {
            Course.create("Apprendre Spring boot", "Devenez un expert en java Spring boot", false, List.of());

        }, " La section 1 doit contenir des cours").isExactlyInstanceOf(BusinessRuleViolationException.class);

    }
}
