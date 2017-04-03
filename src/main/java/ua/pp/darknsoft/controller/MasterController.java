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
import ua.pp.darknsoft.validator.CommobjValidator;
import ua.pp.darknsoft.validator.ContactValidator;
import ua.pp.darknsoft.validator.GoodsOfCommObjValidator;
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
    ContactDao contactDao;
    @Autowired
    UfopValidator ufopValidator;
    @Autowired
    UfopDao ufopDao;
    @Autowired
    CommobjValidator commobjValidator;
    @Autowired
    GoodsOfCommObjValidator goodsOfCommObjValidator;
    @Autowired
    ContactValidator contactValidator;


    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------- MASTER OF UFOP -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/searchufop", method = RequestMethod.GET)
    public String searchUfop(@ModelAttribute Ufop ufop, Model uiModel) {
        uiModel.addAttribute("title", "Перевірка наявності суб'єкта господарювання");

        uiModel.addAttribute("command", ufop);
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
            //redirectAttributes.addFlashAttribute("ufop", ufop);
            return rdrct + "/show_ufop?id=" + ufop.getId() + "#tabs-2";
        } catch (IndexOutOfBoundsException ex) {
            ufop.setNav(1);
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
        co.setNav(1);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        uiModel.addAttribute("actionlink", "/addcommobjpost");
        uiModel.addAttribute("buttonvalue", "Записати");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("contact", co);
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
        try {
            co.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toLowerCase());
            redirectAttributes.addFlashAttribute("co", commercialObjectDao.createCommObj(co));

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex + "<br />commercialObjectDao.createCommObj(co)");
            return rdrct + "/message";
        }
        if (co.isAdditionalinformation()) {

            //co_id
            return rdrct + "/addgoods";
        } else return rdrct + "/show_ufop?id="+co.getUfop_link()+"#tabs-2";
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
        if (co != null) {
            goodsOfCommObj.setComm_obj_link(co.getId());
        } else {
            try {
                if (goodsOfCommObj.getComm_obj_link() > 0)
                    co = commercialObjectDao.getCommObjById(goodsOfCommObj.getComm_obj_link()).get(0);
                uiModel.addAttribute("co", co);
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", ex);
                return rdrct + "/message";
            }
        }

        uiModel.addAttribute("title", "Додайте Основні групи товарів");
        try {
            uiModel.addAttribute("goodsTop", catalogDao.getGoodsTop());

            if (co != null) uiModel.addAttribute("goods_list",commercialObjectDao.getCommObjGoodsByCommObjlink(co.getId())) ;
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", goodsOfCommObj);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addgoods";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addgoodspost", method = RequestMethod.POST)
    public String addGoodspost(@ModelAttribute GoodsOfCommObj goodsOfCommObj, HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        goodsOfCommObjValidator.validate(goodsOfCommObj, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("goodsOfCommObj", goodsOfCommObj);
            return rdrct + "/addgoods";
        }
        try {

            commercialObjectDao.addGoodsToCommObj(goodsOfCommObj);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        redirectAttributes.addFlashAttribute("goodsOfCommObj", goodsOfCommObj);
        return rdrct + "/addgoods";
    }

    //next form UFOP
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addufop", method = RequestMethod.GET)
    public String addUfop(RedirectAttributes redirectAttributes,
                                     HttpServletRequest httpServletRequest, Model uiModel) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Ufop ufop = (Ufop) uiModel.asMap().get("ufop");
        if(ufop == null) ufop = new Ufop();
        if(ufop.getNav()!=1)ufop.setNav(2);
        try {
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
            uiModel.addAttribute("actionlink", "/addufoppost");
            uiModel.addAttribute("buttonvalue", "Записати");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "addUfopPost"+ex);
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
    public String addUfopPost(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
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
            redirectAttributes.addFlashAttribute("ufop", ufop);
            return rdrct + "/addufop";
        }
        try {
            ufop= ufopDao.createUfop(ufop);
            redirectAttributes.addFlashAttribute("ufop",ufop);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        if (ufop.isAdditionalinformation()) return rdrct + "/addcontact";
        else {
            switch (ufop.getNav()){
                case 1:return rdrct + "/show_ufop?id="+ufop.getId()+"#tabs-2";
                default:return rdrct + "/show_ufop?id="+ufop.getId()+"#tabs-1";
            }


        }
    }

    //--ADD KVED-------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkved", method = RequestMethod.GET)
    public String addKvedUfop(@ModelAttribute KvedsUfop kvedsUfop, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Ufop ufop = (Ufop) uiModel.asMap().get("ufop");
        if (ufop != null) kvedsUfop.setUfop_link(ufop.getId());
        else {
            try {
                if (kvedsUfop.getUfop_link() > 0) {
                    ufop = ufopDao.searchUfopById(kvedsUfop.getUfop_link()).get(0);
                    uiModel.addAttribute("ufop", ufop);

                }
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", ex);
                return rdrct + "/message";
            }
        }

        try {
            uiModel.addAttribute("kvedTop", kvedDao.getKvedCatalogTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }

        uiModel.addAttribute("title", "Додайте КВЕДи ");
        uiModel.addAttribute("nextstep", 0);
        uiModel.addAttribute("command", kvedsUfop);
        uiModel.addAttribute("command_ufop", ufop);

        return "addkved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkvedpost", method = RequestMethod.POST)
    public String addKvedUfopPost(@ModelAttribute KvedsUfop kvedsUfop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
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
            kvedsUfop.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName());
            kvedDao.createEntrepreneursKveds(kvedsUfop);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "addkvedpost: " + ex);
            return rdrct + "/message";
        }

        kvedsUfop.setKved_catalog_link("");
        redirectAttributes.addFlashAttribute("kvedsUfop", kvedsUfop);
        return rdrct + "/addkved";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addkved_add_commobj", method = RequestMethod.POST)
    public String addKved_add_commobj(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                      Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        redirectAttributes.addFlashAttribute("ufop", ufop);
        return rdrct + "/addcommobj";
    }

    //--next step add CONTACT-------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontact", method = RequestMethod.GET)
    public String addContact(@ModelAttribute Contact contact, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        Ufop ufop = (Ufop) uiModel.asMap().get("ufop");
        if (ufop != null) contact.setOrganization(ufop.getId());
        else {
            try {
                if (contact.getOrganization() > 0) {
                    ufop = ufopDao.searchUfopById(contact.getOrganization()).get(0);
                    uiModel.addAttribute("ufop", ufop);

                }
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", ex);
                return rdrct + "/message";
            }
        }
        uiModel.addAttribute("title", "Створення нового контакту");
        try {
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        uiModel.addAttribute("form_action_url", "/addcontactpost");
        uiModel.addAttribute("nextstep", 0);

        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", contact);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        uiModel.addAttribute("command_ufop", ufop);
        return "addcontact";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcontactpost", method = RequestMethod.POST)
    public String addContactpost(@ModelAttribute Contact contact, Model uiModel, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("contact", contact);
            return rdrct + "/addcontact";
        }
        try {
            contact.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName());
            contactDao.insert(contact);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        contact.setFirst_name("");
        contact.setLast_name("");
        contact.setPatronymic_name("");
        contact.setA_stay_address("");
        contact.setF_stay_address("");
        contact.setB_stay_address("");
        contact.setN_stay_address("");
        contact.setBirthday("");
        contact.setEmail("");
        contact.setTel("");
        contact.setFax("");
        contact.setRntc("");
        contact.setPosition("");
        redirectAttributes.addFlashAttribute("contact", contact);

        return rdrct + "/addcontact";
    }

    @RequestMapping(value = "/addcontactpost_add_kved")
    public String addcontactpost_add_kved(@ModelAttribute Ufop ufop, Model uiModel, HttpServletRequest httpServletRequest,
                                          RedirectAttributes redirectAttributes) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        redirectAttributes.addFlashAttribute("ufop", ufop);
        return rdrct + "/addkved";
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------EDIT UFOP-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "edit_ufop")
    public String editUfopGet(@RequestParam(defaultValue = "1") String id, Model uiModel) {
        uiModel.addAttribute("title", "Редагування суб'єкта господарювання");
        uiModel.addAttribute("actionlink", "/editufoppost");
        uiModel.addAttribute("buttonvalue", "редагувати");
        try {
            Ufop ufop = ufopDao.searchUfopById(Long.parseLong(id)).get(0);
            ufop.setNav(0);
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
            uiModel.addAttribute("ufop", ufop);
            //uiModel.addAttribute("command_ufop", ufop);
            BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
            uiModel.addAttribute("command", ufop);
            uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такого підприємця не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на підприємця");
            return "message";
        } catch (Exception ex) {
            uiModel.addAttribute("ex", "editUfopGet"+ex);
            return "message";
        }

        return "addufop";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "editufoppost")
    public String editUfopPost(@ModelAttribute Ufop ufop, HttpServletRequest httpServletRequest,
                               RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        ufopValidator.validate(ufop, bindingResult);
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("ufop", ufop);
            return myRdrct(httpServletRequest) + "/edit_ufop?id=" + ufop.getId();
        }
        try {
            ufopDao.editUfop(ufop);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "editufoppost"+ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_ufop?id=" + ufop.getId();
    }
    //next form
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "edit_commobj",method = RequestMethod.GET)
    public String editCommObjGet(@RequestParam(defaultValue = "1") String id, Model uiModel) {
        uiModel.addAttribute("title", "Редагування комерційного об'єкта");
        uiModel.addAttribute("actionlink", "/editcommobjpost");
        uiModel.addAttribute("buttonvalue", "редагувати");

        try {
            CommercialObject co = commercialObjectDao.getCommObjById(Long.parseLong(id)).get(0);
            co.setNav(0);
            co.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString());
            uiModel.addAttribute("locationTop", catalogDao.getLocationTop());
            Map<Integer, String> itemsType = new HashMap<>();
            for (CommercialObjectType items : commercialObjectDao.getCommObjType()) {
                itemsType.put(items.getId(), items.getName());
            }
            uiModel.addAttribute("it", itemsType);
            uiModel.addAttribute("co", co);
            BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
            uiModel.addAttribute("command", co);
            uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такого комерційного об'єкта не існує");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на комерційний об'єкт");
            return "message";
        } catch (Exception ex) {
            uiModel.addAttribute("ex", "editCommObjGet"+ex);
            return "message";
        }

        return "addcommobj";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "editcommobjpost",method = RequestMethod.POST)
    public String editCommObjPost(@ModelAttribute CommercialObject co, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        commobjValidator.validate(co, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("co", co);
            return myRdrct(httpServletRequest) + "/edit_commobj?id="+co.getId();
        }
        try {
            commercialObjectDao.updateCommObj(co);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "editCommObjPost "+ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        if (co.isAdditionalinformation()){
            redirectAttributes.addFlashAttribute("co", co);
            return myRdrct(httpServletRequest) + "/addgoods";
        }
        else return myRdrct(httpServletRequest) + "/show_ufop?id=" + co.getUfop_link()+"#tabs-2";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------DELETE UFOP-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "deletegoods",method = RequestMethod.GET)
    public String deleteGoodsPost(@RequestParam(defaultValue = "0") String id, HttpServletRequest httpServletRequest,
                                  RedirectAttributes redirectAttributes) {
        try{
            redirectAttributes.addFlashAttribute("co",commercialObjectDao.getCommObjById(Long.parseLong(id)).get(0));
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", "deleteGoodsPost <br />"+ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/addgoods";
    }
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

                option = option + "<option value=\"" + downkved.get(i).getId() + "\">" + downkved.get(i).getDegree_of_a_risk_link() + "-" + downkved.get(i).getName() + "</option>";
            }

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);

            return "Error: " + ex;
        }

        html = "<select id=\"my_selecttop" + (level) + "\" onchange=\"loopgoodsdown(" + (level + 1) + ")\">" + option + "</select>";

        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax_select_articles", produces = {"application/json; charset=UTF-8"})
    public String ajax_select_articles(@RequestParam(defaultValue = "1") String treemark, @RequestParam(defaultValue = "2") String nlevel, Model uiModel) {
        String html = "hello world";
        String option = "<option disabled>Виберіть статтю закону</option><option value=\"\"></option>";
        int level = 0;
        List<ArticlesLawCatalog> downarticles = catalogDao.getArticlesByTreemark(treemark, Integer.parseInt(nlevel));
        try {
            level = Integer.parseInt(nlevel);

            for (int i = 0; i <= downarticles.size() - 1; i++) {

                option = option + "<option value=\"" + downarticles.get(i).getId() + "\">" + downarticles.get(i).getCaption() + "</option>";
            }

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);

            return "Error: " + ex;
        }

        html = "<select id=\"my_selecttop" + (level) + "\" onchange=\"looparticlesdown(" + (level + 1) + ")\">" + option + "</select>";

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

    private static String myRdrct(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }

}
