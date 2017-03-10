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
import ua.pp.darknsoft.dao.CommercialObjectDao;
import ua.pp.darknsoft.dao.KvedDao;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.UfopValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andrew on 27.01.2017.
 */
@Controller
public class MasterController {
    @Autowired
    KvedDao kvedDao;
    @Autowired
    CatalogDao catalogDao;
    @Autowired
    CommercialObjectDao commercialObjectDao;
    @Autowired
    UfopValidator ufopValidator;


    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------- MASTER OF ENTITY ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addcommobj", method = RequestMethod.GET)
    public String addLegalentity(Model uiModel) {
        uiModel.addAttribute("title", "Додайте дані про комерційний об'єкт");
        uiModel.addAttribute("nextButtonLink", "/addcommobj");
        return "addcommobj";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/legalentitypost", method = RequestMethod.POST)
    public String addLegalentitypost(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                     Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Contact sendContact = new Contact();

        ufopValidator.validate(ufop, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/addLegalentity";
        }
        try {

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }


        redirectAttributes.addFlashAttribute("sendContact", sendContact);
        return rdrct + "/addkved";
    }

    //--next step-------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkved", method = RequestMethod.GET)
    public String addKvedUfop(Model uiModel) {
        uiModel.addAttribute("title", "Додайте дані про комерційний об'єкт");
        uiModel.addAttribute("nextButtonLink", "/");
        uiModel.addAttribute("addButtonLink", "/");
        return "addkved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkvedpost", method = RequestMethod.POST)
    public String addKvedUfopPost(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                  Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Contact sendContact = new Contact();

//        entityValidator.validate(entity, bindingResult);
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("b1", bindingResult);
//            return rdrct + "/addLegalentity";
//        }
        try {

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }


        redirectAttributes.addFlashAttribute("sendContact", sendContact);
        return rdrct + "/entitykved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkvedpost_nextstep", method = RequestMethod.POST)
    public String addKvedUfopPost_NextStep(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                           Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Contact sendContact = new Contact();

        ufopValidator.validate(ufop, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            return rdrct + "/addLegalentity";
        }
        try {

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }


        redirectAttributes.addFlashAttribute("sendContact", sendContact);
        return rdrct + "/addcontact";
    }
    //--next step-------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------COMM OBJ WORK SHEET-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------AJAX HELPER---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/ajax_select_loc", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax_select_loc(@RequestParam(defaultValue = "15") String treemark, @RequestParam(defaultValue = "2") String nlevel, Model uiModel) {
        String html = "hello world";
        String option = "<option disabled>Виберіть населений пункт</option><option value=\"\"></option>";
        int level = 0;
        try {
            level = Integer.parseInt(nlevel);
            List<LocationCatalog> downloc = catalogDao.getLocationByTreemark(treemark, Integer.parseInt(nlevel));
            for (int i = 0; i <= downloc.size() - 1; i++) {
                option = option + "<option value=\"" + downloc.get(i).getLtree() + "\">" + downloc.get(i).getName() + " - " + downloc.get(i).getStype().toLowerCase() + "</option>";
            }

        } catch (Exception e) {
            return "Error: " + e;
        }

        html = "<select id=\"my_selecttop" + (level) + "\" onchange=\"looplocationdown(" + (level + 1) + ")\">" + option + "</select>";

        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_select_kved", produces = {"application/json; charset=UTF-8"})
    public String ajax_select_kved(@RequestParam(defaultValue = "1") String treemark, @RequestParam(defaultValue = "2") String nlevel, Model uiModel) {
        String html = "hello world";
        String option = "<option disabled>Виберіть Квед</option><option value=\"\"></option>";
        int level = 0;
        List<KvedCatalog> downkved = kvedDao.getKvedCatalogByTreemark(treemark, Integer.parseInt(nlevel));
        try {
            level = Integer.parseInt(nlevel);

            for (int i = 0; i <= downkved.size() - 1; i++) {

                option = option + "<option value=\"" + downkved.get(i).getTreemark() + "\">" + downkved.get(i).getLabel() + "-" + downkved.get(i).getName() + "</option>";
            }

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);

            return "Error: " + ex;
        }

        html = "<select id=\"my_selecttop" + (level) + "\" onchange=\"loopkveddown(" + (level + 1) + ")\">" + option + "</select>";

        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_add_kved", produces = {"application/json; charset=UTF-8"})
    public String ajax_add_kved(@RequestParam String param1, @RequestParam String param2) {
        KvedsUfop kvedsUfop = new KvedsUfop();
        if (param1.isEmpty()) return "Error: ID ФОП is Empty";
        if (param2.isEmpty()) return "Error: КВЕД is Empty";
        try {
            kvedsUfop.setUfop_link(Long.parseLong(param1));
            kvedsUfop.setKved_catalog_link(param2);
            kvedsUfop.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            kvedDao.createEntrepreneursKveds(kvedsUfop);
        } catch (Exception ex) {
            return "Error: " + ex;
        }
        return "added p1:" + param1 + " p2:" + param2;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_add_co", produces = {"application/json; charset=UTF-8"})
    public String ajax_add_co(@RequestParam String p1, @RequestParam String p2, @RequestParam String p3, @RequestParam String p4, @RequestParam String p5) {

        if (p1.isEmpty()) return "Error: ID ФОП is Empty";
        if (p2.isEmpty()) return "Error: Адреса is Empty";
        CommercialObject commercialObject = new CommercialObject();
        try {
            commercialObject.setUfop_link(Long.parseLong(p1));
            commercialObject.setA_place_of_reg(p2);
            commercialObject.setN_place_of_reg(p3);
            commercialObject.setObj_type(Integer.parseInt(p4));
            commercialObject.setObj_name(p5);
            commercialObject.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());

            commercialObjectDao.createCommObj(commercialObject);
        } catch (Exception ex) {
            return "Error: " + ex;
        }
        return "added p1:" + p1 + " p2:" + p2;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_select_commercialobject", produces = {"application/json; charset=UTF-8"})
    public String ajax_add_commercialobject(@RequestParam String param1, @RequestParam String param2) {

        return param2;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_load_commercialobject", produces = {"application/json; charset=UTF-8"})
    public String ajax_load_commercialobject() {

        return "/addcommobj";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------PROGRAMMING TEST----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/test")
    public String test(Model uiModel) {


        try {

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        return "test";
    }


}
