package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.*;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.ContactValidator;
import ua.pp.darknsoft.validator.IndividualEnterpreneurValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    IndividualEntrepreneurDao individualEntrepreneurDao;
    @Autowired
    UserDao userDao;
    @Autowired
    KvedDao kvedDao;
    @Autowired
    CommercialObjectDao commercialObjectDao;

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------MASTER OF INDIVIDUAL ENTERPRENEUR-----------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/contact")
    public String addContact(@ModelAttribute Contact myContact, Model uiModel) {
        uiModel.addAttribute("title", "Введіть дані Фізичної особи");

        try {
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
        }
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", myContact);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "contact";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addContactpost", method = RequestMethod.POST)
    public String addContactpost(@ModelAttribute Contact contact, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                 Model uiModel, BindingResult bindingResult) {
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

            contact.setOwner(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            sendContact.setId(contactDao.insert(contact)); //send owner of the individual entrepreneur information
            sendContact.setRntc(contact.getRntc());
            sendContact.setSeries_of_passport(contact.getSeries_of_passport());
            sendContact.setNumber_of_passport(contact.getNumber_of_passport());
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            sendContact.setId(0L);
            return "message";
        }


        redirectAttributes.addFlashAttribute("sendContact", sendContact);
        return rdrct + "/individualentrepreneur";
    }

    //next form
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/individualentrepreneur", method = RequestMethod.GET)
    public String addIndividualentrepreneur(@ModelAttribute IndividualEntrepreneur individualEntrepreneur, Model uiModel) {
        uiModel.addAttribute("title", "Введіть дані стосовно ФОП");
        //Contact idcontact=(Contact)uiModel.asMap().get("sendContact");


        Map<Integer, String> risk = new LinkedHashMap<Integer, String>(); //select on the view
        risk.put(1, "Висока");
        risk.put(2, "Середня");
        risk.put(3, "Низька");
        uiModel.addAttribute("risk", risk);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b2");
        uiModel.addAttribute("command", individualEntrepreneur);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "individualentrepreneur";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/individualentrepreneurpost", method = RequestMethod.POST)
    public String individualentrepreneurpost(@ModelAttribute IndividualEntrepreneur individualEntrepreneur, HttpServletRequest httpServletRequest,
                                             RedirectAttributes redirectAttributes, Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        IndividualEntrepreneur sendIE = new IndividualEntrepreneur();

        individualEnterpreneurValidator.validate(individualEntrepreneur, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b2", bindingResult);
            return rdrct + "/individualentrepreneur";
        }
        try {
            individualEntrepreneur.setOwner(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            sendIE.setId(individualEntrepreneurDao.insertIE(individualEntrepreneur));

            //write in to the base
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        redirectAttributes.addFlashAttribute("sendIE", sendIE);
        return rdrct + "/kved";
    }

    //next form
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/kved", method = RequestMethod.GET)
    public String addKved(Model uiModel) {
        uiModel.addAttribute("title", "Додайте КВЕД(можна декілька)");
        try {
            List<KvedCatalog> kvedTop1 = kvedDao.getKvedTop();
            uiModel.addAttribute("kvedTop1", kvedTop1);
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }

        return "kved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/kvedpost", method = RequestMethod.POST)
    public String kvedpost(@RequestParam String ufop_id, HttpServletRequest httpServletRequest,
                           RedirectAttributes redirectAttributes, Model uiModel) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        redirectAttributes.addFlashAttribute("ufop_id", ufop_id);
        return rdrct + "/commercialobject";
    }

    //next form
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/commercialobject")
    public String addCommercialobject(Model uiModel) {
        Map<Integer, String> select = new HashMap<Integer, String>();
        uiModel.addAttribute("title", "Додайте дані про комерційний об'єкт");
        try {
            List<CommercialObjectType> co = new LinkedList<CommercialObjectType>();
            co = commercialObjectDao.getCommObjType();
            uiModel.addAttribute("co", co);
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        return "commercialobject";
    }

    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------- MASTER OF ENTITY ---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/entitycommercialobject")
    public String addEntitycommercialobject(Model uiModel) {
        uiModel.addAttribute("title", "Введіть дані стосовно юридичної особи");
        return "commercialobject";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/entitycontact")
    public String addEntitycontact(Model uiModel) {
        uiModel.addAttribute("title", "Введіть дані контактної особи");
        return "entitycontact";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/legalentity")
    public String addLegalentity(Model uiModel) {
        uiModel.addAttribute("title", "Додайте дані про комерційний об'єкт");
        return "legalentity";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------AJAX HELPER---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/ajax_select_loc", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax_select_loc(@RequestParam(defaultValue = "15") String treemark, @RequestParam(defaultValue = "2") String nlevel, Model uiModel) {

        String html = "hello world";
        String option = "<option disabled>Виберіть населений пункт</option>";
        int level = 0;
        try {
            level = Integer.parseInt(nlevel);
            List<LocationCatalog> downloc = catalogDao.getLocationByTreemark(treemark, Integer.parseInt(nlevel));
            for (int i = 0; i <= downloc.size() - 1; i++) {
                option = option + "<option value=\"" + downloc.get(i).getLtree() + "\">" + downloc.get(i).getName() + "</option>";
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
        String option = "<option disabled>Виберіть Квед</option>";
        int level = 0;
        List<KvedCatalog> downkved = kvedDao.getKvedByTreemark(treemark, Integer.parseInt(nlevel));
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
        EntrepreneursKveds entrepreneursKveds = new EntrepreneursKveds();
        if (param1.isEmpty()) return "Error: ID ФОП is Empty";
        if (param2.isEmpty()) return "Error: КВЕД is Empty";
        try {
            entrepreneursKveds.setEntrepreneur_link(Long.parseLong(param1));
            entrepreneursKveds.setKved_catalog_link(param2);
            entrepreneursKveds.setOwner(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            kvedDao.createEntrepreneursKveds(entrepreneursKveds);
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
        EntrepreneurCommercialObject entrepreneurCommercialObject = new EntrepreneurCommercialObject();
        try {
            entrepreneurCommercialObject.setUfop_link(Long.parseLong(p1));
            entrepreneurCommercialObject.setA_obj_location(p2);
            entrepreneurCommercialObject.setN_obj_location(p3);
            entrepreneurCommercialObject.setObj_type(Integer.parseInt(p4));
            entrepreneurCommercialObject.setObj_name(p5);
            entrepreneurCommercialObject.setOwner(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());

            commercialObjectDao.createCommObj(entrepreneurCommercialObject);
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

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------PROGRAMMING TEST----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/test")
    public String test(Model uiModel) {


        try {
            List<KvedCatalog> downkved = kvedDao.getKvedByTreemark("G", 3);
            uiModel.addAttribute("test", downkved);
            uiModel.addAttribute("test1", downkved.size());
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        return "test";
    }

}
