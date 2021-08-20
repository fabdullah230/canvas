package org.veriguide.canvas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.veriguide.canvas.Cookie.CookieData;
import org.veriguide.canvas.Cookie.CookieDataRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(path = "/cookie")
public class CookieValidationController {

    private final CookieDataRepository cookieDataRepository;

    @Autowired
    public CookieValidationController(CookieDataRepository cookieDataRepository) {
        this.cookieDataRepository = cookieDataRepository;
    }

    @GetMapping
    public String cookieValidation(HttpServletRequest httpServletRequest) throws Exception {
        String data = httpServletRequest.getHeader("data");
        System.out.println(data);
        Optional<CookieData> c = Optional.ofNullable(cookieDataRepository.findByData(data));
        if(c.isPresent()){
            //System.out.println("cookie available with data " + data);
            return data;
        }
        else{
            throw new Exception("Cookie couldn't be verified");
        }


    }

}
