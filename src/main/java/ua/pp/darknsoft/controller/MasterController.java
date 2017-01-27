package ua.pp.darknsoft.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrew on 27.01.2017.
 */
@Controller
public class MasterController {
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------MASTER OF INDIVIDUAL ENTERPRENEUR-----------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/contact")
    public String addContact(Model uiModel){
        uiModel.addAttribute("title","Введіть дані Фізичної особи");
        return "contact";
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

}
