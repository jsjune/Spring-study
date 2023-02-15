package com.example.redisprac.caching;

import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    HashMap<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        session.setAttribute("name",name);
//        sessionMap.put(session.getId(), name);
        return "saved";
    }

    @GetMapping("/myName")
    public String myName(HttpSession session) {
        String myName = (String)session.getAttribute("name");
//        String myName = sessionMap.get(session.getId());
        return myName;
    }
}
