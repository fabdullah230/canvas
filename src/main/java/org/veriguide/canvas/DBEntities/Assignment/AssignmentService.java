package org.veriguide.canvas.DBEntities.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public boolean checkIfAssignmentExistsByAssignmentId(String assignmentId) {
        if (assignmentRepository.findAssignmentByAssignmentId(assignmentId).isPresent()) {
            return true;
        }
        return false;
    }

    @Transactional
    public void createAssignment(String courseName, String courseUUID, String courseCode, String creatorId, String assignmentId, String assignmentTitle, int assignmentSubmissionAllowedAttempts){
        assignmentRepository.save(new Assignment(courseName, courseUUID, courseCode, creatorId, assignmentId, assignmentTitle, assignmentSubmissionAllowedAttempts));
        System.out.println("saving assignment");
    }

}
