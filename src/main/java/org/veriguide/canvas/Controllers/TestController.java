package org.veriguide.canvas.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@Controller
@RequestMapping("/")
public class TestController {



    @GetMapping
    public String helloGet(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model, HttpServletRequest httpServletRequest){

        try {
            model.addAttribute("name", name);
            return "helloGet";
        } catch (Exception e){
            return "default";
        }
    }

    @PostMapping
    public String helloPost(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model,  HttpServletRequest httpServletRequest){

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
            model.addAttribute("name", name);



            return "helloPost";

        } catch (Exception e){
            return "default";
        }
    }


}
