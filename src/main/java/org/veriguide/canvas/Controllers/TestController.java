package org.veriguide.canvas.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@Controller
@RequestMapping("/")
public class TestController {



    @GetMapping
    public String helloGet(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model){

        try {
            model.addAttribute("name", name);
            return "helloGet";
        } catch (Exception e){
            return "default";
        }
    }

    @PostMapping
    public String helloPost(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        try {

//            String authHeader = httpServletRequest.getHeader("Authorization");
//            System.out.println(authHeader);
//            no authHeader, token sent in parameters
            String username = httpServletRequest.getParameter("tool_consumer_instance_name");
            model.addAttribute("username", username);

            if(httpServletRequest.getParameter("roles").toLowerCase().contains("instructor")){
                model.addAttribute("instructor", "You are an instructor, " + username);
            }
            Enumeration<String> params = httpServletRequest.getParameterNames();
            System.out.println("invoked POST");
            while(params.hasMoreElements()){
                String paramName = params.nextElement();
                System.out.println("Parameter Name:" + paramName + ", Value:" + httpServletRequest.getParameter(paramName));
            }

            String data = "dummydata";

            Cookie cookie = new Cookie("CanvasVeriguideIntegration", data);

            /**
             * unsafe
             */
            cookie.setPath("/");
            cookie.setSecure(false);
            cookie.setDomain("");
            //1hr validity
            cookie.setMaxAge(60*60);


            //store in redis--
            httpServletResponse.addCookie(cookie);


            return "helloPost";

        } catch (Exception e){
            e.printStackTrace();
            return "default";
        }
    }


}
