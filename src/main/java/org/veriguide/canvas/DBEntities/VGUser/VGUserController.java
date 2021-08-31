package org.veriguide.canvas.DBEntities.VGUser;

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
