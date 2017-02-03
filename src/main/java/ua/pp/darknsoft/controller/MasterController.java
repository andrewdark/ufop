package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.LocationType;
import ua.pp.darknsoft.validator.ContactValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Andrew on 27.01.2017.
 */
@Controller
public class MasterController {
    @Autowired
    ContactValidator contactValidator;
    @Autowired
    CatalogDao catalogDao;
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

        contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/contact";
        }
        return rdrct+"/individualentrepreneur";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/individualentrepreneur")
    public String addIndividualentrepreneur(Model uiModel){
        uiModel.addAttribute("title","Введіть дані стосовно ФОП");
        return "individualentrepreneur";
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
}
