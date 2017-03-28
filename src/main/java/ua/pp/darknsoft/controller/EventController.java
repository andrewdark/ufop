package ua.pp.darknsoft.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.dao.CommercialObjectDao;
import ua.pp.darknsoft.entity.*;
import ua.pp.darknsoft.validator.EventValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
@Controller
public class EventController {
    @Autowired
    CommercialObjectDao commercialObjectDao;
    @Autowired
    CheckEventDao checkEventDao;
    @Autowired
    CatalogDao catalogDao;
    @Autowired
    EventValidator eventValidator;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------MASTER CHECK EVENT-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String addEvent(Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Створення нової перевірки");
        CheckEventSupplemented checkEventSupplemented = new CheckEventSupplemented();
        Ufop ufop = (Ufop) uiModel.asMap().get("ufop");

        List<CheckingCommObj> checkingCommObjs_list = new LinkedList<>();
        checkEventSupplemented.setCommobj_list(checkingCommObjs_list);
        try {

            if (ufop == null) {
                ufop = new Ufop();
                checkEventSupplemented = (CheckEventSupplemented) uiModel.asMap().get("event");
                ufop.setId(checkEventSupplemented.getUfop_link());
            }
            if (ufop.getId() > 0) {
                List<CommercialObject> comobj_list = commercialObjectDao.getCommObjByUfop_link(ufop.getId());
                uiModel.addAttribute("comobj_list", comobj_list);
                for (CommercialObject items : comobj_list) {
                    checkingCommObjs_list.add(new CheckingCommObj());
                    checkingCommObjs_list.get(checkingCommObjs_list.size() - 1).setComm_obj_link(items.getId());
                }
                checkEventSupplemented.setUfop_link(ufop.getId());
            } else {
                redirectAttributes.addFlashAttribute("ex", "Виберіть суб'єкта господарювання");
                return myRdrct(httpServletRequest) + "/message";
            }


        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", checkEventSupplemented);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addevent";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addeventsupplementedpost", method = RequestMethod.POST)
    public String addEventSupplementedPost(@ModelAttribute CheckEventSupplemented checkEventSupplemented, Model uiModel,
                                           HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                           BindingResult bindingResult) {

        eventValidator.validate(checkEventSupplemented, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("event",checkEventSupplemented);
            return myRdrct(httpServletRequest) + "/addevent";
        }
        try {

            for (int i = 0; i < checkEventSupplemented.getCommobj_list().size(); i++) {
                if (checkEventSupplemented.getCommobj_list().get(i).isChecking()) {

                    //checkEventSupplemented.getCommobj_list().get(i).setComm_obj_link(10);
                } else {
                    checkEventSupplemented.getCommobj_list().remove(i);
                }
            }

            checkEventSupplemented.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            redirectAttributes.addFlashAttribute("event", checkEventDao.createEventSupplemented(checkEventSupplemented));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/addcheckgoods";
    }

    //--------------ADD CHECK GOODS------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "addcheckgoods", method = RequestMethod.GET)
    public String addCheckGoods(Model uiModel, RedirectAttributes redirectAttributes,
                                HttpServletRequest httpServletRequest) {
        CheckingGroupOfGoods checkingGroupOfGoods = new CheckingGroupOfGoods();
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");

        if (checkEvent == null) {
            redirectAttributes.addFlashAttribute("ex", "Виберіть перевірку");
            return myRdrct(httpServletRequest) + "/message";
        }
        try {
            uiModel.addAttribute("goodsTop", catalogDao.getGoodsTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("checkEvent", checkEvent);
        checkingGroupOfGoods.setCheck_event_link(checkEvent.getId());
        if (checkEvent.getCheck_violation() == 1) checkingGroupOfGoods.setNav(1);
        else checkingGroupOfGoods.setNav(2);
        uiModel.addAttribute("command", checkingGroupOfGoods);
        return "addcheckgoods";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "addcheckgoodspost", method = RequestMethod.POST)
    public String addCheckGoodsPost(@ModelAttribute CheckingGroupOfGoods checkingGroupOfGoods, RedirectAttributes redirectAttributes,
                                    HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        try {
            if(checkingGroupOfGoods.getGoods_catalog_link()!=0) checkEventDao.createCheckingGroupOfGoods(checkingGroupOfGoods);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(checkingGroupOfGoods.getCheck_event_link()).get(0));
            if (checkingGroupOfGoods.isAdditionalinformation()) {
                switch (checkingGroupOfGoods.getNav()) {
                    case 1:
                        return myRdrct(httpServletRequest) + "/addoffencearticles";
                    case 2:
                        return myRdrct(httpServletRequest) + "/show_event";
                    default:
                        return myRdrct(httpServletRequest) + "/addcheckgoods";
                }

            } else return myRdrct(httpServletRequest) + "/addcheckgoods";

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }


    }

    //-------ADD ARTICLES --------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "addoffencearticles", method = RequestMethod.GET)
    public String addOffenceArticles(Model uiModel, RedirectAttributes redirectAttributes,
                                     HttpServletRequest httpServletRequest) {
        OffenseArticles offenseArticles = new OffenseArticles();
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        try {
            uiModel.addAttribute("articlesTop", catalogDao.getArticleTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        if (checkEvent == null) {
            redirectAttributes.addFlashAttribute("ex", "Виберіть перевірку");
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("checkEvent", checkEvent);
        uiModel.addAttribute("command", offenseArticles);
        return "addarticles";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "addoffencearticlespost", method = RequestMethod.POST)
    public String addOffenceArticlesPost(@ModelAttribute CheckingGroupOfGoods checkingGroupOfGoods, RedirectAttributes redirectAttributes,
                                         HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        try {
            //do insert into database
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------SHOW EVENT DETAIL-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/show_event", method = RequestMethod.GET)
    public String showEvent(@RequestParam(defaultValue = "1") String id, Model uiModel, RedirectAttributes redirectAttributes,
                            HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Створення нової перевірки");
        CheckEventSupplemented checkEventSupplemented = (CheckEventSupplemented) uiModel.asMap().get("event");
        try {
            if (checkEventSupplemented == null) {
                checkEventSupplemented = checkEventDao.getCheckEventById(Long.parseLong(id)).get(0);
            }
        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такої перевірки не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "не вірна вказівка на перевірку");
            return "message";
        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        uiModel.addAttribute("event", checkEventSupplemented);
        uiModel.addAttribute("command", checkEventSupplemented);
        return "show_event";
    }

    @RequestMapping(value = "/show_eventpost", method = RequestMethod.POST)
    public String showEventPost(@ModelAttribute CheckEventSupplemented checkEventSupplemented, RedirectAttributes redirectAttributes,
                                HttpServletRequest httpServletRequest) {

        redirectAttributes.addFlashAttribute("event", checkEventSupplemented);
        switch (checkEventSupplemented.getNav()) {
            case 0:
                return myRdrct(httpServletRequest) + "/show_event";
            case 1:
                return myRdrct(httpServletRequest) + "/addcheckgoods";
            case 2:
                return myRdrct(httpServletRequest) + "/addoffencearticles";
            case 3:
                return myRdrct(httpServletRequest) + "/test";
            case 4:
                return myRdrct(httpServletRequest) + "/test";
            case 5:
                return myRdrct(httpServletRequest) + "/test";
            case 6:
                return myRdrct(httpServletRequest) + "/test";
            default:
                return myRdrct(httpServletRequest) + "/show_event?id=" + checkEventSupplemented.getId();
        }


    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------STATIC METHOD-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private static String myRdrct(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }
}
