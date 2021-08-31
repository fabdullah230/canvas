package org.veriguide.canvas.Controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.veriguide.canvas.Cookie.CookieData;
import org.veriguide.canvas.Cookie.CookieDataRepository;
import org.veriguide.canvas.DBEntities.Assignment.AssignmentService;
import org.veriguide.canvas.DBEntities.Course.CourseService;
import org.veriguide.canvas.DBEntities.VGUser.VGUserService;
import org.veriguide.canvas.Helpers.CreateEntitiesIfNotAvailable;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping(path = "/cookie")
public class CookieValidationController {

    private final CookieDataRepository cookieDataRepository;
    private VGUserService vgUserService;
    private CourseService courseService;
    private AssignmentService assignmentService;
    private CreateEntitiesIfNotAvailable createEntitiesIfNotAvailable;

    //todo: this needs to be moved to application.properties
    protected SecretKey secretKey = Keys.hmacShaKeyFor("6TimessecretKeysecretKeysecretKeysecretKeysecretKeysecretKey".getBytes());


    @Autowired
    public CookieValidationController(CreateEntitiesIfNotAvailable createEntitiesIfNotAvailable, AssignmentService assignmentService, CookieDataRepository cookieDataRepository, VGUserService vgUserService, CourseService courseService) {
        this.cookieDataRepository = cookieDataRepository;
        this.vgUserService = vgUserService;
        this.courseService = courseService;
        this.assignmentService = assignmentService;
        this.createEntitiesIfNotAvailable = createEntitiesIfNotAvailable;
    }

    @GetMapping
    public String cookieValidation(HttpServletRequest httpServletRequest) throws Exception {

        String data = httpServletRequest.getHeader("data");
        //checking to see if cookie saved, else will throw exception and send status 500
        Optional<CookieData> c = Optional.ofNullable(cookieDataRepository.findByData(data));
        if(c.isPresent()){

            //deal with creating user, course, assignment
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(data);
            Claims body = claimsJws.getBody();

            //USER
            createEntitiesIfNotAvailable.checkVGUser(body);

            //COURSE
            createEntitiesIfNotAvailable.checkCourse(body);

            //ASSIGNMENT
            createEntitiesIfNotAvailable.checkAssignment(body);

            return data;
        }
        else{
            throw new Exception("Cookie couldn't be verified");
        }


    }

}
