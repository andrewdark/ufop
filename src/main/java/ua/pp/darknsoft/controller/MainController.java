package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.*;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.ContactValidator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    CheckEventDao checkEventDao;
    @Autowired
    UfopDao ufopDao;
    @Autowired
    KvedDao kvedDao;
    @Autowired
    CommercialObjectDao commercialObjectDao;
    @Autowired
    ContactValidator contactValidator;

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
        List<Ufop> ufop = new LinkedList<Ufop>();
        ufop = ufopDao.getEntrepreneurByPaginator(total, pageid1, (short) 0);

        uiModel.addAttribute("u_size", ufop.size());

        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("total_page", "t");
        uiModel.addAttribute("title_name", "Власник");
        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
    }

    @RequestMapping(value = "/viewsliste/{pageid}", method = RequestMethod.GET)
    public String viewsListe(@PathVariable int pageid, Model uiModel) {

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
        List<Ufop> ufop = new LinkedList<Ufop>();
        ufop = ufopDao.getEntrepreneurByPaginator(total, pageid1, (short) 1);

        uiModel.addAttribute("u_size", ufop.size());

        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("total_page", "t");
        uiModel.addAttribute("title_name", "Назва підприємства");
        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
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

            uiModel.addAttribute("ufop", ufop);
            uiModel.addAttribute("command_ufop", ufop);
            //            if(ie.getA_place_of_reg().length()>0) {
//                uiModel.addAttribute("fulladdress",catalogDao.getParentLocationByTreemark(ie.getA_place_of_reg()));
//            }
//            if(ie.getContact().getA_stay_address().length()>0) {
//                uiModel.addAttribute("stayaddress",catalogDao.getParentLocationByTreemark(ie.getContact().getA_stay_address()));
//            }
//
//

//           uiModel.addAttribute("ie",ie);

            uiModel.addAttribute("co_list", commercialObjectDao.getCommObjByUfop_link(ufop.getId()));
            uiModel.addAttribute("kveds", kvedDao.getKvedsByUfopLink(Long.parseLong(id)));
            uiModel.addAttribute("ci_list", contactDao.getContactByOrganizationLink(Long.parseLong(id)));
            uiModel.addAttribute("checkEventList", checkEventDao.getCheckEventByUfopLink(ufop.getId()));
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


        return "show_ufop";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/show_ufop_create_new")
    public String show_ufop_create_commobj(@ModelAttribute Ufop ufop, Model uiModel, HttpServletRequest httpServletRequest,
                                           RedirectAttributes redirectAttributes) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        redirectAttributes.addFlashAttribute("ufop", ufop);
        ufop.setAdditionalinformation(false);
        switch (ufop.getNav()) {
            case 1:
                return rdrct + "/addcommobj";
            case 2:
                return rdrct + "/addevent";
            case 3:
                return rdrct + "/addkved";
            case 4:
                return rdrct + "/addcontact";
            default:
                return rdrct + "/";
        }

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
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontact_1")
    public String legal_entity(@ModelAttribute Contact contact, Model uiModel) {
        uiModel.addAttribute("form_action_url", "/addcontact_1post");
        uiModel.addAttribute("nextstep", 1);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", contact);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addcontact";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontact_1post", method = RequestMethod.POST)
    public String addcontact_1post(@ModelAttribute Contact contact, Model uiModel, RedirectAttributes redirectAttributes,
                                   HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/addcontact_1";
        }
        return "addcontact_1";
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