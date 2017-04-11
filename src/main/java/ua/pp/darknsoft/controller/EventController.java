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
import ua.pp.darknsoft.validator.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Autowired
    PrecautionValidator precautionValidator;
    @Autowired
    SanctionValidator sanctionValidator;
    @Autowired
    LawSuitsValidator lawSuitsValidator;
    @Autowired
    PunismentArticlesValidator punismentArticlesValidator;
    @Autowired
    OffenseArticlesValidator offenseArticlesValidator;

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
            redirectAttributes.addFlashAttribute("event", checkEventSupplemented);
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
            //redirectAttributes.addFlashAttribute("event", checkEventDao.createEventSupplemented(checkEventSupplemented));
            checkEventSupplemented = checkEventDao.createEventSupplemented(checkEventSupplemented);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id="+checkEventSupplemented.getId();
    }

    //--------------ADD CHECK GOODS------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcheckgoods", method = RequestMethod.GET)
    public String addCheckGoods(Model uiModel, RedirectAttributes redirectAttributes,
                                HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Основні групи товарів");
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
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", checkingGroupOfGoods);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addcheckgoods";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcheckgoodspost", method = RequestMethod.POST)
    public String addCheckGoodsPost(@ModelAttribute CheckingGroupOfGoods checkingGroupOfGoods, RedirectAttributes redirectAttributes,
                                    HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        try {
            if (!checkingGroupOfGoods.getGoods_catalog_link().equals("0"))
                checkEventDao.createCheckingGroupOfGoods(checkingGroupOfGoods);
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
    @RequestMapping(value = "/addoffencearticles", method = RequestMethod.GET)
    public String addOffenceArticles(Model uiModel, RedirectAttributes redirectAttributes,
                                     HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Статті правопорушень");
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
        offenseArticles.setCheck_event_link(checkEvent.getId());
        uiModel.addAttribute("checkEvent", checkEvent);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command",offenseArticles);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addarticles";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addoffencearticlespost", method = RequestMethod.POST)
    public String addOffenceArticlesPost(@ModelAttribute OffenseArticles offenseArticles, RedirectAttributes redirectAttributes,
                                         HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        offenseArticlesValidator.validate(offenseArticles, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = null;
                if((Long)offenseArticles.getCheck_event_link() != null)
                    event = checkEventDao.getCheckEventById(offenseArticles.getCheck_event_link());
                if (event.isEmpty()) return myRdrct(httpServletRequest) + "/addoffencearticles";
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", "/addoffenseArticlespost - offenseArticlesArticlesValidator.validate<br />" + ex);
                return myRdrct(httpServletRequest) + "/message";
            }

            return myRdrct(httpServletRequest) + "/addoffencearticles";
        }
        try {
            if(!offenseArticles.getArticles_law_link().isEmpty())
            checkEventDao.createOffenseArticles(offenseArticles);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(offenseArticles.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/addoffencearticles";
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------MASTER OF PUNISHMENTS---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addprecautions", method = RequestMethod.GET)
    public String addPrecautions(Model uiModel, RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest) {
        Precaution precaution = new Precaution();
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        try {
            Map<Integer, String> precaution_cat = new HashMap<>();
            for (PrecautionCatalog item : catalogDao.getPrecautionCatalog()) {
                precaution_cat.put(item.getId(), item.getName());
            }
            uiModel.addAttribute("precautioncatalog", precaution_cat);
            precaution.setCheck_event_link(checkEvent.getId());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addprecautions - catalogDao.getPrecautionCatalog() <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        uiModel.addAttribute("title", "Прийняті заходи");
        uiModel.addAttribute("checkEvent", checkEvent);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", precaution);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addprecautions";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addprecautionspost", method = RequestMethod.POST)
    public String addPrecautionsPost(@ModelAttribute Precaution precaution, Model uiModel,
                                     HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                     BindingResult bindingResult) {
        precautionValidator.validate(precaution, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = checkEventDao.getCheckEventById(precaution.getCheck_event_link());
                if (event.isEmpty()) return myRdrct(httpServletRequest) + "/addprecautions";
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", "/addprecautionspost - precautionValidator.validate <br />" + ex);
                return myRdrct(httpServletRequest) + "/message";
            }
            return myRdrct(httpServletRequest) + "/addprecautions";
        }
        try {

            checkEventDao.createPrecaution(precaution);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(precaution.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addprecautionspost - checkEventDao.createPrecaution(precaution) <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/addprecautions";
    }

    //---next form
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addpunishmentarticles", method = RequestMethod.GET)
    public String addPunismentArticles(Model uiModel, RedirectAttributes redirectAttributes,
                                       HttpServletRequest httpServletRequest) {
        PunishmentArticles punishmentArticles = new PunishmentArticles();
        try {
            uiModel.addAttribute("articlesTop", catalogDao.getArticleTop());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        uiModel.addAttribute("title", "Статті покарання");
        uiModel.addAttribute("checkEvent", checkEvent);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        punishmentArticles.setCheck_event_link(checkEvent.getId());
        uiModel.addAttribute("command", punishmentArticles);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addpunishmentarticles";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addpunishmentarticlespost", method = RequestMethod.POST)
    public String addPunismentArticlesPost(@ModelAttribute PunishmentArticles punishmentArticles, HttpServletRequest httpServletRequest,
                                           RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        punismentArticlesValidator.validate(punishmentArticles, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = null;
                if((Long)punishmentArticles.getCheck_event_link() != null)
                 event = checkEventDao.getCheckEventById(punishmentArticles.getCheck_event_link());
                if (event.isEmpty()) return myRdrct(httpServletRequest) + "/addpunishmentarticles";
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", "/addaddpunishmentarticlespost - punismentArticlesValidator.validate<br />" + ex);
                return myRdrct(httpServletRequest) + "/message";
            }

            return myRdrct(httpServletRequest) + "/addpunishmentarticles";
        }
        try {
            if(!punishmentArticles.getArticles_law_link().isEmpty())
            checkEventDao.createPunishmentArticlesByCheckEventLink(punishmentArticles);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(punishmentArticles.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addaddpunishmentarticlespost - createPunishmentArticles<br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        if(punishmentArticles.isAdditionalinformation())return myRdrct(httpServletRequest) + "/addsanctions";
            else
        return myRdrct(httpServletRequest) + "/addpunishmentarticles";
    }

    //---NEXT PAGE--
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addsanctions", method = RequestMethod.GET)
    public String addSanctions(Model uiModel, RedirectAttributes redirectAttributes,
                               HttpServletRequest httpServletRequest) {
        SanctionSupplemented sanction = new SanctionSupplemented();

        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        try {
            sanction.setPunishmentArticlesList(checkEventDao.getPunishmentArticlesByCheckEventLink(checkEvent.getId()));
            uiModel.addAttribute("testSanction",checkEventDao.getSanctionEventByCheckEventLink(checkEvent.getId()));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addsanctions <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("title", "Статті та тип санкцій");
        uiModel.addAttribute("checkEvent", checkEvent);
        sanction.setCheck_event_link(checkEvent.getId());
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", sanction);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addsanctions";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addsanctionspost", method = RequestMethod.POST)
    public String addSanctionsPost(@ModelAttribute Sanction sanction, Model uiModel,
                                   HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                   BindingResult bindingResult) {
        sanctionValidator.validate(sanction, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = checkEventDao.getCheckEventById(sanction.getCheck_event_link());
                if (event.isEmpty()) return myRdrct(httpServletRequest) + "/addsanctions";
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", "/addsanctionspost - sanctionValidator.validate<br />" + ex);
                return myRdrct(httpServletRequest) + "/message";
            }
            return myRdrct(httpServletRequest) + "/addsanctions";
        }
        try {
            checkEventDao.createSanction(sanction);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(sanction.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addsanctionspost - checkEventDao.getCheckEventById <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/show_event?id="+sanction.getCheck_event_link()+"#tabs-3";
    }

    //---NEXT PAGE--
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addlawsuits", method = RequestMethod.GET)
    public String addLawSuits(Model uiModel, RedirectAttributes redirectAttributes,
                              HttpServletRequest httpServletRequest) {
        Lawsuits lawsuits = new Lawsuits();
        Map<Integer,String> select = new HashMap<>();


        try{
            for (LawsuitsResultCatalog items: catalogDao.getLawsuitsResultCatalog()
                 ) {
                select.put(items.getId(),items.getName());
            }
            uiModel.addAttribute("select",select);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", "/addsanctionspost - checkEventDao.getCheckEventById <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        lawsuits.setCheck_event_link(checkEvent.getId());
        uiModel.addAttribute("title", "Інформація щодо судових позовів");
        uiModel.addAttribute("checkEvent", checkEvent);
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", lawsuits);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addlawsuits";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addlawsuitspost", method = RequestMethod.POST)
    public String addLawSuitsPost(@ModelAttribute Lawsuits lawsuits, Model uiModel,
                                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult) {
        lawSuitsValidator.validate(lawsuits, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = checkEventDao.getCheckEventById(lawsuits.getCheck_event_link());
                //if (event.isEmpty()) return myRdrct(httpServletRequest) + "/addlawsuits";
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", ex);
                return myRdrct(httpServletRequest) + "/message";
            }

            return myRdrct(httpServletRequest) + "/addlawsuits";
        }
        try {
            checkEventDao.createLawsuits(lawsuits);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(lawsuits.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id="+lawsuits.getCheck_event_link()+"#tabs-4";
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
            //uiModel.addAttribute("",checkEventDao);
            uiModel.addAttribute("offensearticles",checkEventDao.getOffenseArticlesByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("precaution",checkEventDao.getPrecautionByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("punishmentarticles",checkEventDao.getPunishmentArticlesByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("testSanction",checkEventDao.getSanctionEventByCheckEventLink(checkEventSupplemented.getId()));
            uiModel.addAttribute("lawsuits",checkEventDao.getLawsuitsByCheckEventLink(checkEventSupplemented.getId()));
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
                return myRdrct(httpServletRequest) + "/addprecautions";
            case 5:
                return myRdrct(httpServletRequest) + "/addpunishmentarticles";
            case 6:
                return myRdrct(httpServletRequest) + "/addlawsuits";
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
