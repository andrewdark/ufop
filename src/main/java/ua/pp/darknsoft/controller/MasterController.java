package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.ContactDao;
import ua.pp.darknsoft.dao.IndividualEntrepreneurDao;
import ua.pp.darknsoft.dao.UserDao;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.ContactValidator;
import ua.pp.darknsoft.validator.IndividualEnterpreneurValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 27.01.2017.
 */
@Controller
public class MasterController {
    @Autowired
    ContactValidator contactValidator;
    @Autowired
    IndividualEnterpreneurValidator individualEnterpreneurValidator;
    @Autowired
    CatalogDao catalogDao;
    @Autowired
    ContactDao contactDao;
    @Autowired
    UserDao userDao;
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------MASTER OF INDIVIDUAL ENTERPRENEUR-----------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/contact")
    public String addContact(@ModelAttribute Contact myContact, Model uiModel){
        uiModel.addAttribute("title","Введіть дані Фізичної особи");

        try{
            uiModel.addAttribute("locationTop",catalogDao.getLocationTop());

        }catch (Exception ex){
            uiModel.addAttribute("ex",ex);
        }
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", myContact);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "contact";
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addContactpost", method = RequestMethod.POST)
    public String addContactpost(@ModelAttribute Contact contact, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                 Model uiModel, BindingResult bindingResult){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Contact sendContact = new Contact();

        contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/contact";
        }
        try {

            contact.setOwner(SecurityContextHolder.getContext().getAuthentication().getName().toString());
            sendContact.setId(contactDao.insert(contact)); //send owner of the individual entrepreneur information
            sendContact.setRntc(contact.getRntc());
            sendContact.setSeries_of_passport(contact.getSeries_of_passport());
            sendContact.setNumber_of_passport(contact.getNumber_of_passport());
        }catch (Exception ex){
            uiModel.addAttribute("ex",ex);
            sendContact.setId(0L);
            return "message";
        }


        redirectAttributes.addFlashAttribute("sendContact",sendContact);
        return rdrct+"/individualentrepreneur";
    }
    //next form
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/individualentrepreneur", method = RequestMethod.GET)
    public String addIndividualentrepreneur(@ModelAttribute IndividualEnterpreneur individualEnterpreneur, Model uiModel){
        uiModel.addAttribute("title","Введіть дані стосовно ФОП");
       //Contact idcontact=(Contact)uiModel.asMap().get("sendContact");


        Map<Integer,String> risk = new LinkedHashMap<Integer,String>(); //select on the view
        risk.put(1,"Висока");risk.put(2,"Середня");risk.put(3,"Низька");
        uiModel.addAttribute("risk",risk);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b2");
        uiModel.addAttribute("command", individualEnterpreneur);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "individualentrepreneur";
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/individualentrepreneurpost", method = RequestMethod.POST)
    public String individualentrepreneurpost(@ModelAttribute IndividualEnterpreneur individualEnterpreneur, HttpServletRequest httpServletRequest,
                                             RedirectAttributes redirectAttributes,Model uiModel, BindingResult bindingResult){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;


        individualEnterpreneurValidator.validate(individualEnterpreneur, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b2", bindingResult);
            return rdrct + "/individualentrepreneur";
        }
        try {

           //write in to the base
        }catch (Exception ex){
            uiModel.addAttribute("ex",ex);
            return "message";
        }

        return rdrct+"/kved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/kved")
    public String addKved(Model uiModel){
        uiModel.addAttribute("title","Додайте КВЕД(можна декілька)");
        return "kved";
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/commercialobject")
    public String addCommercialobject(Model uiModel){
        uiModel.addAttribute("title","Додайте дані про комерційний об'єкт");
        return "commercialobject";
    }
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------MASTER OF ENTITY ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/entitycommercialobject")
    public String addEntitycommercialobject(Model uiModel){
        uiModel.addAttribute("title","Введіть дані стосовно юридичної особи");
        return "commercialobject";
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/entitycontact")
    public String addEntitycontact(Model uiModel){
        uiModel.addAttribute("title","Введіть дані контактної особи");
        return "entitycontact";
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/legalentity")
    public String addLegalentity(Model uiModel){
        uiModel.addAttribute("title","Додайте дані про комерційний об'єкт");
        return "legalentity";
    }
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------AJAX HELPER---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/ajax_select_loc", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax_select_loc(@RequestParam(defaultValue = "0") String x, Model uiModel) {
        int level = 0;
        String html = "";
        List<LocationType> allLoctype = catalogDao.getLocationType();
        try {


        } catch (Exception e) {
            return "Error: " + e;
        }


        return html + "DO SMTH";
    }
    @ResponseBody
    @RequestMapping(value = "ajax_add_kved", produces = {"application/json; charset=UTF-8"})
    public String ajax_add_kved(@RequestParam String param1,@RequestParam String param2){

        return param2;
    }
    @ResponseBody
    @RequestMapping(value = "ajax_add_commercialobject", produces = {"application/json; charset=UTF-8"})
    public String ajax_add_commercialobject(@RequestParam String param1,@RequestParam String param2){

        return param2;
    }
}
