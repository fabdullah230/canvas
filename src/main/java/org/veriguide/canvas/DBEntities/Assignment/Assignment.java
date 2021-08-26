package org.veriguide.canvas.DBEntities.Assignment;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class Assignment {

    @Id
    @SequenceGenerator(
            name = "assignment_sequence",
            sequenceName = "assignment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assignment_sequence"
    )
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;

    //from LTI
    private String courseName;  //  context_title
    private String courseUUID;    //  custom_course_id
    private String courseCode;  //  course code
    private String creatorId;   //  user_id of creator


    @Column(unique = true)
    private String assignmentId;    //ext_lti_assignment_id
    private String assignmentTitle; //custom_canvas_assignment_title
    private int assignmentSubmissionAllowedAttempts;    //custom_assignment_submission_attempt_allowed

    public Assignment() {
    }

    public Assignment(String courseName, String courseUUID, String courseCode, String creatorId, String assignmentId, String assignmentTitle, int assignmentSubmissionAllowedAttempts) {
        this.createdAt = LocalDateTime.now();
        this.courseName = courseName;
        this.courseUUID = courseUUID;
        this.courseCode = courseCode;
        this.creatorId = creatorId;
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.assignmentSubmissionAllowedAttempts = assignmentSubmissionAllowedAttempts;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", courseName='" + courseName + '\'' +
                ", courseUUID=" + courseUUID +
                ", courseCode='" + courseCode + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", assignmentId='" + assignmentId + '\'' +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", assignmentSubmissionAllowedAttempts=" + assignmentSubmissionAllowedAttempts +
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

    public String getAssignmentId() {
        return assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public int getAssignmentSubmissionAllowedAttempts() {
        return assignmentSubmissionAllowedAttempts;
    }
}
