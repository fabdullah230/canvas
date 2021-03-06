package org.veriguide.canvas.DBEntities.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Boolean checkIfCourseExistsByCourseUUID(String uuid){
        if(courseRepository.findCourseByCourseUUID(uuid).isPresent()){
            return true;
        }
        return false;
    }

    @Transactional
    public void createCourse(String courseName, String courseUUID, String courseCode, String creatorId){
        System.out.println("Saving course");
        courseRepository.save(new Course(courseName, courseUUID, courseCode, creatorId));
    }



}
