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
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.CommobjValidator;
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
    @Autowired
    UfopDao ufopDao;
    @Autowired
    CommobjValidator commobjValidator;


    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------- MASTER OF UFOP -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/searchufop", method = RequestMethod.GET)
    public String searchUfop(@ModelAttribute Ufop ufop, Model uiModel) {
        uiModel.addAttribute("title", "Перевірка наявності суб'єкта господарювання");

        uiModel.addAttribute("command", ufop);
        uiModel.addAttribute("nextButtonLink", "/addcommobj");
        return "searchufop";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/searchufoppost", method = RequestMethod.POST)
    public String searchUfoppost(@ModelAttribute Ufop ufop, Model uiModel, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        try {
            ufop = ufopDao.searchUfopByCode(ufop.getUfop_code()).get(0);
            redirectAttributes.addFlashAttribute("ufop", ufop);
            return rdrct + "/addcommobj";
        } catch (IndexOutOfBoundsException ex) {
            redirectAttributes.addFlashAttribute("ufop", ufop);
            return rdrct + "/addufop";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }


    }

    //next form COMM OBJ
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addcommobj", method = RequestMethod.GET)
    public String addLegalentity(@ModelAttribute CommercialObject co, Model uiModel, RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Додайте дані про комерційний об'єкт");
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        Ufop ufop;

        try {
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
            Map<Integer, String> itemsType = new HashMap<>();
            for (CommercialObjectType items : commercialObjectDao.getCommObjType()) {
                itemsType.put(items.getId(), items.getName());
            }
            uiModel.addAttribute("it", itemsType);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex + " locationTop");
            return rdrct + "/message";
        }
        ufop = (Ufop) uiModel.asMap().get("ufop");
        try {

            if (ufop == null) {

                co = (CommercialObject) uiModel.asMap().get("co");
                ufop = ufopDao.searchUfopById(co.getUfop_link()).get(0);
                uiModel.addAttribute("ufop", ufop);
            } else {
                co.setUfop_link(ufop.getId());
            }

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("contact", co);
        uiModel.addAttribute("test1", "ufop link: " + co.getUfop_link());

        uiModel.addAttribute("command", co);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);

        return "addcommobj";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addcommobjpost", method = RequestMethod.POST)
    public String addCommobjpost(@ModelAttribute CommercialObject co, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes, Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        redirectAttributes.addFlashAttribute("co", co);
        commobjValidator.validate(co, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("co", co);
            return rdrct + "/addcommobj";
        }
        if (co.isAdditionalinformation()) {
            co.setId(777); //TEST TEST TEST
            redirectAttributes.addFlashAttribute("co_id", co);
            return rdrct + "/addgoods";
        } else return rdrct + "/searchufop";
    }

    //ADD GROUP OF GOODS
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addgoods", method = RequestMethod.GET)
    public String addGoods(@ModelAttribute GoodsOfCommObj goodsOfCommObj, Model uiModel, RedirectAttributes redirectAttributes,
                           HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        CommercialObject co = (CommercialObject) uiModel.asMap().get("co");
        if (co != null) goodsOfCommObj.setComm_obj_link(co.getId());
        uiModel.addAttribute("title", "Додайте Основні групи товарів");
        try{
            uiModel.addAttribute("goodsTop", catalogDao.getGoodsTop());
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex",ex);
            return rdrct + "/message";
        }

        uiModel.addAttribute("command", goodsOfCommObj);
        uiModel.addAttribute("nextButtonLink", "/addcommobj");
        return "addgoods";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addgoodspost", method = RequestMethod.POST)
    public String addGoodspost(@ModelAttribute GoodsOfCommObj goodsOfCommObj, HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        try {

            goodsOfCommObj.setId(goodsOfCommObj.getGoods_catalog_link()); //TEST TEST TEST
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        redirectAttributes.addFlashAttribute("goodsOfCommObj",goodsOfCommObj);
        return rdrct + "/addgoods";
    }

    //next form UFOP
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addufop", method = RequestMethod.GET)
    public String addLegalentitypost(@ModelAttribute Ufop ufop, RedirectAttributes redirectAttributes,
                                     HttpServletRequest httpServletRequest, Model uiModel) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        try {
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        uiModel.addAttribute("title", "Інформація про суб'єкт господарювання");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b");
        uiModel.addAttribute("command", ufop);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addufop";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addufoppost", method = RequestMethod.POST)
    public String addufoppost(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                              Model uiModel, BindingResult bindingResult) {

        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        ufopValidator.validate(ufop, bindingResult);
        if (bindingResult.hasErrors()) {

            uiModel.asMap().clear();
            redirectAttributes.addFlashAttribute("b", bindingResult);
            return rdrct + "/addufop";
        }
        try {
            redirectAttributes.addFlashAttribute("ufop", ufopDao.createUfop(ufop));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        if (ufop.isAdditionalinformation()) return rdrct + "/addkved";
        else return rdrct + "/addcommobj";
    }

    //--ADD KVED-------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkved", method = RequestMethod.GET)
    public String addKvedUfop(Model uiModel) {
        try {
            uiModel.addAttribute("kvedTop1", kvedDao.getKvedCatalogTop());
        } catch (Exception ex) {
        }

        uiModel.addAttribute("title", "Додайте КВЕДи ");
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

        return rdrct + "/addcontact";
    }

    //--next step add CONTACT-------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontact", method = RequestMethod.GET)
    public String addContact(@ModelAttribute Contact contact, Model uiModel) {
        uiModel.addAttribute("title", "Створення нового контакту");
        uiModel.addAttribute("command", contact);
        return "addcontact";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontactpost", method = RequestMethod.POST)
    public String addContactpost(@ModelAttribute Contact contact, Model uiModel, HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;


        return "/";
    }

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
    @RequestMapping(value = "/ajax_select_goods", produces = {"application/json; charset=UTF-8"})
    public String ajax_select_goods(@RequestParam(defaultValue = "1") String treemark, @RequestParam(defaultValue = "2") String nlevel, Model uiModel) {
        String html = "hello world";
        String option = "<option disabled>Виберіть групу товарів</option><option value=\"\"></option>";
        int level = 0;
        List<BasicGroupOfGoodsCatalog> downkved = catalogDao.getGoodsByTreemark(treemark, Integer.parseInt(nlevel));
        try {
            level = Integer.parseInt(nlevel);

            for (int i = 0; i <= downkved.size() - 1; i++) {

                option = option + "<option value=\"" + downkved.get(i).getTreemark() + "\">" + downkved.get(i).getDegree_of_a_risk_link() + "-" + downkved.get(i).getName() + "</option>";
            }

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);

            return "Error: " + ex;
        }

        html = "<select id=\"my_selecttop" + (level) + "\" onchange=\"loopgoodsdown(" + (level + 1) + ")\">" + option + "</select>";

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
    //---вставка формы создания UFOP в коммерческий объект---


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
