package org.veriguide.canvas.DBEntities.VGUser;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resttest")
public class VGUserController {

    @Autowired
    private VGUserRepository vgUserRepository;

    @GetMapping
    public List<VGUser> findAll() {

        List<VGUser> test = vgUserRepository.findAll();

        for (VGUser v : test){
            System.out.println(v);
        }


        return test;
    }
}
