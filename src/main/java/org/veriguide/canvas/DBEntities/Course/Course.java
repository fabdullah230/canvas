package org.veriguide.canvas.DBEntities.Course;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table
public class Course {



    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;

    //from LTI
    private String courseName;  //  context_title
    @Column(unique = true)
    private String courseUUID;    //  custom_course_id
    private String courseCode;  //  course code
    private String creatorId;   //  user_id of creator

    public Course() {
    }

    public Course(String courseName, String courseUUID, String courseCode, String creatorId) {
        this.createdAt = LocalDateTime.now();
        this.courseName = courseName;
        this.courseUUID = courseUUID;
        this.courseCode = courseCode;
        this.creatorId = creatorId;
    }


    //boilerplate
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", courseName='" + courseName + '\'' +
                ", courseUUID='" + courseUUID + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseUUID() {
        return courseUUID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCreatorId() {
        return creatorId;
    }
}
