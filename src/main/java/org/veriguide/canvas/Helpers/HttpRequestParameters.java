package org.veriguide.canvas.Helpers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

@Service
public class HttpRequestParameters {

    protected SecretKey secretKey = Keys.hmacShaKeyFor("6TimessecretKeysecretKeysecretKeysecretKeysecretKeysecretKey".getBytes());

    public void print(HttpServletRequest httpServletRequest){
        Enumeration<String> params = httpServletRequest.getParameterNames();
        System.out.println("invoked POST");
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            System.out.println("Parameter Name:" + paramName + ", Value:" + httpServletRequest.getParameter(paramName));
        }
    }

    public String createJWT(HttpServletRequest httpServletRequest){
//        String prefix ="Bearer "; //bearer token
        Enumeration<String> params = httpServletRequest.getParameterNames();

        HashMap<String, String> claims = new HashMap<>();

        System.out.println("creating JWT");
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            claims.put(paramName, httpServletRequest.getParameter(paramName));
        }


        String token = Jwts.builder()
                .setSubject(httpServletRequest.getParameter("tool_consumer_instance_name"))
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1l)))
                .signWith(secretKey)
                .compact();

        //System.out.println(token);
        return token;
    }


}
