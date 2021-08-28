package org.veriguide.canvas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.veriguide.canvas.Cookie.CookieData;
import org.veriguide.canvas.Cookie.CookieDataRepository;
import org.veriguide.canvas.Helpers.HttpRequestParameters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class LTILaunchController {

    private final CookieDataRepository cookieDataRepository;
    private HttpRequestParameters httpRequestParameters;

    @Autowired
    public LTILaunchController(CookieDataRepository cookieDataRepository, HttpRequestParameters httpRequestParameters) {
        this.cookieDataRepository = cookieDataRepository;
        this.httpRequestParameters = httpRequestParameters;
    }

    @PostMapping
    public String initialRedirect(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        try {

            String username = httpServletRequest.getParameter("tool_consumer_instance_name");
            model.addAttribute("username", username);

            if(httpServletRequest.getParameter("roles").toLowerCase().contains("instructor")){
                model.addAttribute("instructor", "You are an instructor, " + username);
            }

            //httpRequestParameters.print(httpServletRequest);
            //createJWTtoken - bearer token
            String data = httpRequestParameters.createJWT(httpServletRequest);

            Cookie cookie = new Cookie("CanvasVeriguideIntegration", data);

            //unsafe
            cookie.setPath("/");
            cookie.setSecure(false);
            cookie.setDomain("");

            //1hr validity
            cookie.setMaxAge(60*60);

            //store in redis
            cookieDataRepository.save(new CookieData(data));
            //checking to see if cookie saved, else will throw exception and send status 500
            Optional<CookieData> c = Optional.ofNullable(cookieDataRepository.findByData(data));

            httpServletResponse.addCookie(cookie);
            model.addAttribute("data", data);

            return "initialRedirect";

        } catch (Exception e){
            e.printStackTrace();
            return "default";
        }
    }


}
