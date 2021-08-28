package org.veriguide.canvas.Helpers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.veriguide.canvas.DBEntities.Assignment.AssignmentService;
import org.veriguide.canvas.DBEntities.Course.CourseService;
import org.veriguide.canvas.DBEntities.VGUser.VGUserService;

@Service
public class CreateEntitiesIfNotAvailable {

    private VGUserService vgUserService;
    private CourseService courseService;
    private AssignmentService assignmentService;


    @Autowired
    public CreateEntitiesIfNotAvailable(VGUserService vgUserService, CourseService courseService, AssignmentService assignmentService) {
        this.vgUserService = vgUserService;
        this.courseService = courseService;
        this.assignmentService = assignmentService;
    }

    public void checkVGUser(Claims body){
        String userName = (String)body.get("tool_consumer_instance_name");
        String roleString = (String) body.get("roles");
        String userId = (String) body.get("user_id");

        if(!vgUserService.checkIfVGUserExistsByUserId(userId)){
            System.out.println("User does not exist");
            vgUserService.createVGUser(userName, roleString, userId);
        }
    }

    public void checkCourse(Claims body){
        String courseName = (String) body.get("context_title");
        String courseCode = (String) body.get("context_label");
        String courseUUID = (String) body.get("custom_course_uuid");
        String userId = (String) body.get("user_id");

        if(!courseService.checkIfCourseExistsByCourseUUID(courseUUID)){
            System.out.println("Course does not exist");
            courseService.createCourse(courseName, courseUUID, courseCode, userId);
        }
    }

    public void checkAssignment(Claims body){
        String assignmentId = (String) body.get("ext_lti_assignment_id");
        String assignmentTitle = (String) body.get("custom_canvas_assignment_title");
        String courseName = (String) body.get("context_title");
        String courseCode = (String) body.get("context_label");
        String courseUUID = (String) body.get("custom_course_uuid");
        String userId = (String) body.get("user_id");

        //by default, if no submission limit, then limit is set at 99
        int assignmentSubmissionAllowedAttempts = 99;
        try {
            assignmentSubmissionAllowedAttempts = Integer.parseInt((String) body.get("custom_assignment_submission_attempt_allowed"));
        } catch (Exception e){}

        //todo: make this 1 line
        if(!assignmentService.checkIfAssignmentExistsByAssignmentId(assignmentId)){
            System.out.println("Assignment does not exist");
            assignmentService.createAssignment(courseName, courseUUID, courseCode, userId, assignmentId, assignmentTitle, assignmentSubmissionAllowedAttempts);
        }

    }




}
