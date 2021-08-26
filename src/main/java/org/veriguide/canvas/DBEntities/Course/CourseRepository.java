package org.veriguide.canvas.DBEntities.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseById(Long id);
    Optional<Course> findCourseByCourseUUID(String courseUUID);
    Optional<Course> findCourseByCourseName(String courseName);
    Optional<Course> findCourseByCourseCode(String courseCode);

    @Override
    List<Course> findAll();
}
