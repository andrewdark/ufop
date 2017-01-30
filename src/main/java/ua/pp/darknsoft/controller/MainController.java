package ua.pp.darknsoft.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrew on 10.01.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String main(){
        return "default";
    }
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------HEADER NAV MENU ----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(){
        return "message";
    }

    @RequestMapping(value = "/my_office")
    public String my_officen(){
        return "my_office";
    }

    @RequestMapping(value = "/catalog")
    public String catalog(){
        return "catalog";
    }

    @RequestMapping(value = "/search")
    public String search(){
        return "search";
    }

    @RequestMapping(value = "/chat")
    public String chat(){
        return "chat";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------LEFT MENU---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/individual_enterpreneur")
    public String individual_enterpreneur(){
        return "IndividualEnterpreneur";
    }
    @RequestMapping(value = "/legal_entity")
    public String legal_entity(){
        return "legal_entity";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------SERVICE-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}