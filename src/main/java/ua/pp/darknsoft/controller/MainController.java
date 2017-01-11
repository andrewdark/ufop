package ua.pp.darknsoft.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrew on 10.01.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String main(){
        return "default";
    }

    @RequestMapping(value = "/my_office")
    public String my_officen(){
        return "my_office";
    }

    @RequestMapping(value = "/registration")
    public String registration(){
        return "registration";
    }


}