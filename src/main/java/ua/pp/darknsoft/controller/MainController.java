package ua.pp.darknsoft.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.apache.commons.lang.ObjectUtils;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    InfoDao infoDao;
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

        int total = 10;
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
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Ufop> ufop = new LinkedList<Ufop>();

        try {
            ufop = setLastEvent(ufopDao.getEntrepreneurByPaginator(total, pageid1, (short) 0));
        } catch (Exception ex) {

        }

        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewslisti");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("total_page", "NAN");

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
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Ufop> ufop = new LinkedList<Ufop>();
        try{
            ufop = setLastEvent(ufopDao.getEntrepreneurByPaginator(total, pageid1, (short) 1));
        }catch (Exception ex){

        }
        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewsliste");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("total_page", "NAN");

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

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/search")
    public String search(Model uiModel, RedirectAttributes redirectAttributes,
                         HttpServletRequest httpServletRequest) {
        try{
            Map<Integer,String> inspectorsList = new HashMap<>();
            for (User items:catalogDao.getInspectorsBySelectorStructureLink(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase())
                    ) {
                inspectorsList.put(items.getId(),items.getUsername());
            }
            uiModel.addAttribute("inspectorsList",inspectorsList);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }

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
            if (ufop.getA_place_of_reg().length() > 0) {
                uiModel.addAttribute("fulladdress", catalogDao.getParentLocationByTreemark(ufop.getA_place_of_reg()));
            }
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
        switch (ufop.getUfop_nav()) {
            case 1:
                return rdrct + "/addcommobj";
            case 2:
                try {
                    List<CommercialObject> commercialObjectList = commercialObjectDao.getCommObjByUfop_link(ufop.getId());
                    if (commercialObjectList.isEmpty()) {
                        String msg = "Неможливо створити перевірку, тому що у вибраного суб'єкта господарювання немає жодного об'єкта здійснення торгівлі" +
                                "<br /> <a href = \"/show_ufop/?id=" + ufop.getId() + "#tabs-2\">Повернутись до суб'єкта</a>";
                        redirectAttributes.addFlashAttribute("ex", msg);
                        return rdrct + "/message";
                    } else
                        return rdrct + "/addevent";
                } catch (Exception ex) {
                    redirectAttributes.addFlashAttribute("ex", ex);
                    return rdrct + "/message";
                }

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

    @RequestMapping(value = "/viewslistsearch/{pageid}", method = RequestMethod.GET)
    public String viewsListSearch(@PathVariable int pageid, @RequestParam(defaultValue = "1") String stext,
                                  Model uiModel, RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Ufop> ufop = new LinkedList<>();
        try{
            if (stext.length() > 2) {
                ufop = setLastEvent(ufopDao.getUfopByPaginatorMultiple(total, pageid1, stext));
            }
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListSearch <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewslistsearch");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("getparam", "?stext=" + stext);
        uiModel.addAttribute("total_page", "NAN");

        uiModel.addAttribute("title_name", "Власник");
        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
    }

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
    //------------------------------------------SEARCH BLOCK--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------INFO BLOCK--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/article_info", method = RequestMethod.GET)
    public String articleInfo(@RequestParam(defaultValue="1") String id, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        uiModel.addAttribute("title", "Відомості про статтю");
        try {
            uiModel.addAttribute("articles", infoDao.getArticleById(id).get(0));
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такої статті не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на статтю");
            return "message";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:articleInfo <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return "article_info";
    }
    @RequestMapping(value = "/contact_info", method = RequestMethod.GET)
    public String contactInfo(@RequestParam(defaultValue="1") String id, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        uiModel.addAttribute("title", "Відомості про контакт");
        try {
            Contact contact = contactDao.getContactById(Long.parseLong(id)).get(0);
            uiModel.addAttribute("contact", contact);
            if (contact.getA_stay_address().length() > 0) {
                uiModel.addAttribute("fulladdress", catalogDao.getParentLocationByTreemark(contact.getA_stay_address()));
            }
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такого контакту не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на контакт");
            return "message";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:contactInfo <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return "contact_info";
    }
    @RequestMapping(value = "/ufop_info/{id}", method = RequestMethod.GET)
    public String ufopInfo(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        uiModel.addAttribute("title", "Відомості про суб'єкта господарювання");
        try {
            Ufop ufop =  ufopDao.searchUfopById(Long.parseLong(id)).get(0);
            uiModel.addAttribute("ufop",ufop);
            if (ufop.getA_place_of_reg().length() > 0) {
                uiModel.addAttribute("fulladdress", catalogDao.getParentLocationByTreemark(ufop.getA_place_of_reg()));
            }
            uiModel.addAttribute("co_list", commercialObjectDao.getCommObjByUfop_link(ufop.getId()));
            uiModel.addAttribute("kveds", kvedDao.getKvedsByUfopLink(Long.parseLong(id)));
            uiModel.addAttribute("ci_list", contactDao.getContactByOrganizationLink(Long.parseLong(id)));
            uiModel.addAttribute("checkEventList", checkEventDao.getCheckEventByUfopLink(ufop.getId()));
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такої статті не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на статтю");
            return "message";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:articleInfo <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return "ufop_info";
    }
    @RequestMapping(value = "/event_info/{id}", method = RequestMethod.GET)
    public String eventInfo(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        uiModel.addAttribute("title", "Відомості про перевірку");
        try {
            CheckEventSupplemented checkEventSupplemented = checkEventDao.getCheckEventById(Long.parseLong(id)).get(0);
            List<Sanction> sanctionList = checkEventDao.getSanctionEventByCheckEventLink(checkEventSupplemented.getId());
            BigDecimal sum = new BigDecimal(0.00);
            for (Sanction items : sanctionList) {
                sum = sum.add(items.getCharged_amount());
            }
            checkEventSupplemented.setCommobj_list(checkEventDao.getCheckingCommercialObjectByEventLink(Long.parseLong(id)));
            checkEventSupplemented.setGroupofgoods_list(checkEventDao.getCheckingGroupOfGoodsByCheckEventLink(Long.parseLong(id)));
            uiModel.addAttribute("inspectorsList",checkEventDao.getInspectorsByCheckEventLink(Long.parseLong(id)));
            uiModel.addAttribute("offensearticles", checkEventDao.getOffenseArticlesByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("precaution", checkEventDao.getPrecautionByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("sum", sum);
            uiModel.addAttribute("testSanction", sanctionList);
            uiModel.addAttribute("lawsuits", checkEventDao.getLawsuitsByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("event",checkEventSupplemented);
            uiModel.addAttribute("ufop",ufopDao.searchUfopById(checkEventSupplemented.getUfop_link()).get(0));
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такої перевірки не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на перевірку");
            return "message";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:articleInfo <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return "event_info";
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

    private static String myRdrct(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }

    private List<Ufop> setLastEvent(List<Ufop> ufopList){
        for (int i = 0; i < ufopList.size(); i++) {
            try {
                CheckEvent temp = checkEventDao.getLastCheckEventByUfopLink(ufopList.get(i).getId()).get(0);
                ufopList.get(i).setLast_event(temp.getEvent_date_end() + " " + temp.getStructure_catalog_name());
            }catch ( java.lang.IndexOutOfBoundsException ex){
                ufopList.get(i).setLast_event("Не перевірявся");
            }
            catch (Exception ex) {
                ufopList.get(i).setLast_event("" + ex);
            }
        }
        return ufopList;
    }

}