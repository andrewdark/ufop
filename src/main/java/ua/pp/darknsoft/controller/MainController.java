package ua.pp.darknsoft.controller;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.ContactDao;
import ua.pp.darknsoft.dao.ContactDaoImpl;
import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.LocationCatalog;
import ua.pp.darknsoft.entity.LocationType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 10.01.2017.
 */
@Controller
public class MainController {

    @Autowired
    ContactDao contactDao;
    @Autowired
    CatalogDao catalogDao;

    @RequestMapping(value = "/")
    public String main(){
        return "default";
    }
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------VIEWS LIST----------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/viewslisti", method = RequestMethod.GET)
    public String viewsListi(Model uiModel){
        List<Contact> ufop = contactDao.selectContact();
        String link="/individual_enterpreneur";
        uiModel.addAttribute("ufop",ufop);
        uiModel.addAttribute("viewmore",link);
        return "viewslist_individual";
    }
    @RequestMapping(value = "/viewslistu", method = RequestMethod.GET)
    public String viewsListu(Model uiModel){
        List<String> uop = new ArrayList<String>();
        String link="/legal_entity";
        uop.add("one"); uop.add("two"); uop.add("three");
        uiModel.addAttribute("uop",uop);
        uiModel.addAttribute("viewmore",link);
        return "viewslist_entity";
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
        return "individual_enterpreneur";
    }
    @RequestMapping(value = "/legal_entity")
    public String legal_entity(){
        return "legal_entity";
    }
    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------SERVICE----------------------------------------------------------------
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