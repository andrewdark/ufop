package ua.pp.darknsoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrew on 16.01.2017.
 */
@Controller
public class CatalogController {

    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "addlocation", method = RequestMethod.GET)
    public String addlocation(){
        return "addlocation";
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "viewlocation", method = RequestMethod.GET)
    public String viewlocation(){
        return "viewlocation";
    }
}
