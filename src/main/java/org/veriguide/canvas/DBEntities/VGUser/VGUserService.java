package org.veriguide.canvas.DBEntities.VGUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class VGUserService {

    @Autowired
    private VGUserRepository vgUserRepository;

    //todo: implement check by user_id

    //checking by user_id
    public Boolean checkIfVGUserExistsByUserId(String userId){
        if(vgUserRepository.findVGUserByUserId(userId).isPresent()){
            return true;
        }
        return false;
    }


    public void createVGUser(String userName, String roleString, String userId){
        VGUserRole role = VGUserRole.USER;
        Boolean isAdmin = false;

        if(roleString.contains("Instructor")){
            role = VGUserRole.INSTRUCTOR;
        }

        else if(roleString.contains("Student")){
            role = VGUserRole.STUDENT;
        }

        if(roleString.contains("Admin")){
           isAdmin = true;
        }

        System.out.println("saving user");
        vgUserRepository.save(new VGUser(role, userName, LocalDateTime.now(), isAdmin, userId));

    }



}