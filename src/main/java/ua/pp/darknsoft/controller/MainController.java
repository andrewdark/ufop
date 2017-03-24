package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.*;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.Ufop;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
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

    @Autowired
    UfopDao ufopDao;
    @Autowired
    KvedDao kvedDao;
    @Autowired
    CommercialObjectDao commercialObjectDao;

    @RequestMapping(value = "/")
    public String main() {
        return "default";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------VIEWS LIST----------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/viewslistcontact/{pageid}", method = RequestMethod.GET)
    public String viewsListContact(@PathVariable int pageid, Model uiModel) {

        int total = 5;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Contact> ufop = contactDao.getContact(total, pageid1);
        String link = "/individual_enterpreneur";
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("viewmore", link);
        uiModel.addAttribute("page_id", pageid);
        return "viewslist_contact";
    }

    @RequestMapping(value = "/viewslisti/{pageid}", method = RequestMethod.GET)
    public String viewsListi(@PathVariable int pageid, Model uiModel) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 5;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total + 1;
        }
//        List<IndividualEntrepreneur> ufop = new LinkedList<IndividualEntrepreneur>();
//        ufop = individualEntrepreneurDao.getEntrepreneur(total,pageid1);
//
//        uiModel.addAttribute("u_size",ufop.size());
//
//        uiModel.addAttribute("ufop",ufop);
//        uiModel.addAttribute("page_id",pageid);
//        uiModel.addAttribute("total_page","t");
//        if(ufop.isEmpty()){ uiModel.addAttribute("ex","Нажаль, немає жодного запису"); }
        return "viewslist_individual";
    }

    @RequestMapping(value = "/viewslistu", method = RequestMethod.GET)
    public String viewsListu(Model uiModel) {
        List<String> uop = new ArrayList<String>();
        String link = "/legal_entity";
        uop.add("one");
        uop.add("two");
        uop.add("three");
        uiModel.addAttribute("uop", uop);
        uiModel.addAttribute("viewmore", link);
        return "viewslist_entity";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------HEADER NAV MENU ----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message() {
        return "message";
    }

    @RequestMapping(value = "/my_office")
    public String my_officen() {
        return "my_office";
    }

    @RequestMapping(value = "/catalog")
    public String catalog() {
        return "catalog";
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/chat")
    public String chat() {
        return "chat";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SHOW DETAILS--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/show_ufop")
    public String showEntrepreneur(@RequestParam(defaultValue = "1") String id, Model uiModel) {


        try {
            Ufop ufop = ufopDao.searchUfopById(Long.parseLong(id)).get(0);
            uiModel.addAttribute("ufop",ufop);
            uiModel.addAttribute("command_ufop", ufop);

            List<CommercialObject> co = commercialObjectDao.getCommObjEntrepreneurByUfop_link(ufop.getId());
// List<EntrepreneurCommercialObject> co = commercialObjectDao.getCommObjEntrepreneurByUfop_link(ie.getId());
//
//            if(ie.getA_place_of_reg().length()>0) {
//                uiModel.addAttribute("fulladdress",catalogDao.getParentLocationByTreemark(ie.getA_place_of_reg()));
//            }
//            if(ie.getContact().getA_stay_address().length()>0) {
//                uiModel.addAttribute("stayaddress",catalogDao.getParentLocationByTreemark(ie.getContact().getA_stay_address()));
//            }
//
//
//            uiModel.addAttribute("kveds",kvedDao.getEntrepreneursKvedsByEntrepreneurLink(Long.parseLong(id)));
//            uiModel.addAttribute("ie",ie);
//            uiModel.addAttribute("co",co);
//            uiModel.addAttribute("ci","contactInformation about:");
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такого підприємця не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на підприємця");
            return "message";
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        try {

        } catch (IndexOutOfBoundsException ex) {

        }

        return "show_ufop";
    }
    @RequestMapping(value = "/show_ufop_create_commobj")
    public String show_ufop_create_commobj(@ModelAttribute Ufop ufop, Model uiModel, HttpServletRequest httpServletRequest,
                                           RedirectAttributes redirectAttributes){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        redirectAttributes.addFlashAttribute("ufop", ufop);
        return rdrct + "/addcommobj";
    }

    @RequestMapping(value = "/userinfo")
    public String showUserInfo(@RequestParam(defaultValue = "NO") String name, Model uiModel) {
        if (name.equals("NO")) {

        } else {
            try {
                uiModel.addAttribute("contact", contactDao.getContactByName(name));
            } catch (IndexOutOfBoundsException ex) {
                uiModel.addAttribute("ex", "Нажаль, користувача з таким іменем не знайдено");
                return "message";
            } catch (Exception ex) {
                uiModel.addAttribute("ex", ex);
                return "message";
            }
        }

        return "userinfo";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------LEFT MENU---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/legal_entity")
    public String legal_entity() {
        return "legal_entity";
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------SERVICE----------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

}