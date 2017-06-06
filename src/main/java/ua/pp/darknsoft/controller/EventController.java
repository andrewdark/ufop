package ua.pp.darknsoft.controller;

import javafx.util.converter.BigDecimalStringConverter;
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
import java.math.BigDecimal;
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
    CheckingGoodsValidator checkingGoodsValidator;
    @Autowired
    PrecautionValidator precautionValidator;
    @Autowired
    SanctionValidator sanctionValidator;
    @Autowired
    LawSuitsValidator lawSuitsValidator;
    @Autowired
    OffenseArticlesValidator offenseArticlesValidator;
    @Autowired
    InspectorsValidator inspectorsValidator;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------MASTER CHECK EVENT-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String addEvent(Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Створення нової перевірки");
        CheckEventSupplemented checkEventSupplemented = new CheckEventSupplemented();
        Ufop ufop = (Ufop) uiModel.asMap().get("ufop");
        uiModel.addAttribute("actionlink", "/addeventsupplementedpost");
        uiModel.addAttribute("buttonvalue", "Створити перевірку");
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
            for (Iterator<CheckingCommObj> iterator = checkEventSupplemented.getCommobj_list().iterator(); iterator.hasNext(); ) {
                if (iterator.next().isChecking() == false) iterator.remove();
            }

            checkEventSupplemented.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            //redirectAttributes.addFlashAttribute("event", checkEventDao.createEventSupplemented(checkEventSupplemented));
            checkEventSupplemented = checkEventDao.createEventSupplemented(checkEventSupplemented);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id=" + checkEventSupplemented.getId();
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
            uiModel.addAttribute("checkingGoods", checkEventDao.getCheckingGroupOfGoodsByCheckEventLink(checkEvent.getId()));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method: addCheckGoods() <br />" + ex);
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
        checkingGoodsValidator.validate(checkingGroupOfGoods, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(checkingGroupOfGoods.getCheck_event_link()).get(0));
            return myRdrct(httpServletRequest) + "/addcheckgoods";
        }
        try {
            if (!checkingGroupOfGoods.getGoods_catalog_link().isEmpty())
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
            redirectAttributes.addFlashAttribute("ex", "Method: addCheckGoodsPost() <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }


    }

    //--------------ADD CHECK COMM OBJ------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcheckcommobj", method = RequestMethod.GET)
    public String addCheckCommObj(Model uiModel, RedirectAttributes redirectAttributes,
                                  HttpServletRequest httpServletRequest) {
        CheckEventSupplemented checkEventSupplemented = (CheckEventSupplemented) uiModel.asMap().get("event");
        List<CheckingCommObj> checkingCommObjList_e = new LinkedList<>();
        List<CheckingCommObj> checkingCommObjList_d = new LinkedList<>();
        try {
            List<CommercialObject> ufopCommObjList = commercialObjectDao.getCommObjByUfop_link(checkEventSupplemented.getUfop_link());
            List<CheckingCommObj> eventCommObjList = checkEventDao.getCheckingCommercialObjectByEventLink(checkEventSupplemented.getId());


            for (ListIterator<CommercialObject> iterator = ufopCommObjList.listIterator(); iterator.hasNext(); ) {
                CommercialObject obj = iterator.next();
                for (CheckingCommObj list2 : eventCommObjList) {
                    if (obj.getId() == list2.getComm_obj_link()) {
                        iterator.remove();
                    }
                }
            }

            for (CommercialObject list1 : ufopCommObjList
                    ) {

                checkingCommObjList_e.add(new CheckingCommObj());
                checkingCommObjList_e.get(checkingCommObjList_e.size() - 1).setCheck_event_link(checkEventSupplemented.getId());
                checkingCommObjList_e.get(checkingCommObjList_e.size() - 1).setComm_obj_link(list1.getId());
                checkingCommObjList_e.get(checkingCommObjList_e.size() - 1).setComm_obj_name(list1.getObj_name());

            }


            uiModel.addAttribute("checkingCommObjList_e", checkingCommObjList_e);
            uiModel.addAttribute("checkingCommObjList_d", eventCommObjList);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        checkEventSupplemented.setCommobj_list(checkingCommObjList_e);

        uiModel.addAttribute("checkEvent", checkEventSupplemented);
        uiModel.addAttribute("title", "Додавання нових комерційних об'єктів");
        uiModel.addAttribute("actionlink", "/addcheckcommobjpost");
        uiModel.addAttribute("buttonvalue", "Записати зміни");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", checkEventSupplemented);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addcheckcommobj";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addcheckcommobjpost", method = RequestMethod.POST)
    public String addCheckCommObjPost(@ModelAttribute CheckEventSupplemented checkEventSupplemented, RedirectAttributes redirectAttributes,
                                      HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        try {
            for (int i = 0; i < checkEventSupplemented.getCommobj_list().size(); i++) {
                if (checkEventSupplemented.getCommobj_list().get(i).isChecking()) {
                    checkEventDao.createCheckingCommObj(checkEventSupplemented.getCommobj_list().get(i));
                }
            }

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/show_event?id=" + checkEventSupplemented.getId();
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
            uiModel.addAttribute("offenseArticles_list", checkEventDao.getOffenseArticlesByCheckEventLink(checkEvent.getId()));
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
        uiModel.addAttribute("command", offenseArticles);
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
                if ((Long) offenseArticles.getCheck_event_link() != null)
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
            if (!offenseArticles.getArticles_law_link().isEmpty())
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
            uiModel.addAttribute("precaution_list", checkEventDao.getPrecautionByCheckEventLink(checkEvent.getId()));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addprecautions - catalogDao.getPrecautionCatalog() <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        uiModel.addAttribute("title", "Прийняті заходи");
        uiModel.addAttribute("actionlink", "/addprecautionspost");
        uiModel.addAttribute("buttonvalue", "Додати");
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


    //---NEXT PAGE--
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addsanctions", method = RequestMethod.GET)
    public String addSanctions(Model uiModel, RedirectAttributes redirectAttributes,
                               HttpServletRequest httpServletRequest) {
        Sanction sanction = new Sanction();

        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        try {
            uiModel.addAttribute("articlesTop", catalogDao.getArticleTop());

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addsanctions <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("title", "Статті та тип санкцій");
        uiModel.addAttribute("checkEvent", checkEvent);
        uiModel.addAttribute("actionlink", "/addsanctionspost");
        uiModel.addAttribute("buttonvalue", "Додати");
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
            sanction.setCharged_amount(BigDecimal.valueOf(Double.parseDouble(sanction.getCharged_amount_str())));
            sanction.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            checkEventDao.createSanction(sanction);
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(sanction.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "/addsanctionspost - checkEventDao.getCheckEventById <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/show_event?id=" + sanction.getCheck_event_link() + "#tabs-3";
    }

    //---NEXT PAGE--
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addlawsuits", method = RequestMethod.GET)
    public String addLawSuits(Model uiModel, RedirectAttributes redirectAttributes,
                              HttpServletRequest httpServletRequest) {
        Lawsuits lawsuits = new Lawsuits();
        Map<Integer, String> select = new HashMap<>();


        try {
            for (LawsuitsResultCatalog items : catalogDao.getLawsuitsResultCatalog()
                    ) {
                select.put(items.getId(), items.getName());
            }
            uiModel.addAttribute("select", select);
        } catch (Exception ex) {
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
        return myRdrct(httpServletRequest) + "/show_event?id=" + lawsuits.getCheck_event_link() + "#tabs-4";
    }
    //add inspectors
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addinspectors", method = RequestMethod.GET)
    public String addInspectors(Model uiModel, RedirectAttributes redirectAttributes,
                              HttpServletRequest httpServletRequest) {
        Inspectors inspectors = new Inspectors();
        CheckEventSupplemented checkEvent = (CheckEventSupplemented) uiModel.asMap().get("event");
        if (checkEvent == null) {
            redirectAttributes.addFlashAttribute("ex", "Виберіть перевірку");
            return myRdrct(httpServletRequest) + "/message";
        }
        inspectors.setCheck_event_link(checkEvent.getId());
        inspectors.setCreator_name(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
        try{
            Map<Integer,String> inspectorsList = new HashMap<>();
            for (User items:catalogDao.getInspectorsBySelectorStructureLink(inspectors.getCreator_name())
                 ) {
            inspectorsList.put(items.getId(),items.getUsername());
            }
            uiModel.addAttribute("inspectorsList",inspectorsList);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        uiModel.addAttribute("checkEvent", checkEvent);
        uiModel.addAttribute("title", "Співробітники, що перевіряли");
        uiModel.addAttribute("actionlink", "/addinspectorspost");
        uiModel.addAttribute("buttonvalue", "Додати");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", inspectors);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addinspectors";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addinspectorspost", method = RequestMethod.POST)
    public String addInspectorsPost(@ModelAttribute Inspectors inspectors, Model uiModel,
                                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult) {

        inspectorsValidator.validate(inspectors, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            try {
                List<CheckEventSupplemented> event = null;
                if ((Long) inspectors.getCheck_event_link() != null)
                    event = checkEventDao.getCheckEventById(inspectors.getCheck_event_link());
                redirectAttributes.addFlashAttribute("event", event.get(0));
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("ex", "/addInspectors - inspectorsValidator.validate<br />" + ex);
                return myRdrct(httpServletRequest) + "/message";
            }

            return myRdrct(httpServletRequest) + "/addinspectors";
        }
        try {
            if(inspectors.getUser_link()>0){
                checkEventDao.createInspectors(inspectors);
            }
            else {
                redirectAttributes.addFlashAttribute("ex", "Check_event_link: " + inspectors.getCheck_event_link()+"<br /> User_link: " + inspectors.getUser_link()
                + " <br /> Creator_name: " + inspectors.getCreator_name());
                return myRdrct(httpServletRequest) + "/message";
            }
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(inspectors.getCheck_event_link()).get(0));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id=" + inspectors.getCheck_event_link() + "#tabs-5";

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
                return myRdrct(httpServletRequest) + "/addcheckcommobj";
            case 4:
                return myRdrct(httpServletRequest) + "/addprecautions";
            case 5:
                return myRdrct(httpServletRequest) + "/addsanctions";
            case 6:
                return myRdrct(httpServletRequest) + "/addlawsuits";
            case 7:
                return myRdrct(httpServletRequest) + "/addinspectors";
            default:
                return myRdrct(httpServletRequest) + "/show_event?id=" + checkEventSupplemented.getId();
        }


    }

    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------EDIT EVENT METHOD-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "editeventsupplemented", method = RequestMethod.GET)
    public String editEvent(@RequestParam(defaultValue = "0") String id, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        CheckEventSupplemented checkEventSupplemented = (CheckEventSupplemented) uiModel.asMap().get("event");

        try {

            if (checkEventSupplemented == null && !id.equals("0")) {

                checkEventSupplemented = checkEventDao.getCheckEventById(Long.parseLong(id)).get(0);

//                uiModel.addAttribute("ex", "Такої перевірки не знайдено");
//                return "message";
            }
            checkEventSupplemented.setCommobj_list(checkEventDao.getCheckingCommercialObjectByEventLink(Long.parseLong(id)));
            uiModel.addAttribute("comobj_list1", checkEventSupplemented.getCommobj_list());

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
        uiModel.addAttribute("title", "Редагування перевірки id = " + checkEventSupplemented.getId());
        uiModel.addAttribute("actionlink", "/editeventsupplementedpost");
        uiModel.addAttribute("buttonvalue", "Записати зміни");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", checkEventSupplemented);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addevent";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/editeventsupplementedpost", method = RequestMethod.POST)
    public String editEventSupplementedPost(@ModelAttribute CheckEventSupplemented checkEventSupplemented, Model uiModel,
                                            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                            BindingResult bindingResult) {

        eventValidator.validate(checkEventSupplemented, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("event", checkEventSupplemented);
            return myRdrct(httpServletRequest) + "/editeventsupplemented?id=" + checkEventSupplemented.getId();
        }
        try {

            checkEventSupplemented.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            checkEventSupplemented = checkEventDao.editEvent(checkEventSupplemented);
            List<CheckingCommObj> commObjList_db = checkEventDao.getCheckingCommercialObjectByEventLink(checkEventSupplemented.getId());
            for (int i = 0; i < checkEventSupplemented.getCommobj_list().size(); i++) {
                //checkEventSupplemented.getCommobj_list().get(i).setCheck_event_link(checkEventSupplemented.getId());
                for (int j = 0; j < commObjList_db.size(); j++) {

                    if (checkEventSupplemented.getCommobj_list().get(i).getComm_obj_link() == commObjList_db.get(j).getComm_obj_link()) { //поиск одинаковых
                        if (checkEventSupplemented.getCommobj_list().get(i).isChecking() != commObjList_db.get(j).isChecking()) {

                            checkEventDao.updateCheckingCommObjById(checkEventSupplemented.getCommobj_list().get(i));
                            break;
                        }
                    } else {
                        if (checkEventSupplemented.getCommobj_list().get(i).isChecking()) {

                        }
                    }
                }

            }

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id=" + checkEventSupplemented.getId();
    }

    // EDIT PRECAUTION
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "editprecaution", method = RequestMethod.GET)
    public String editPrecaution(@RequestParam(defaultValue = "0") String id, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        Precaution precaution = (Precaution) uiModel.asMap().get("precaution");

        try {
            if (precaution == null && !id.equals("0")) {
                List<Precaution> precaution_list = checkEventDao.getPrecautionById(Long.parseLong(id));
                precaution = precaution_list.get(0);
                uiModel.addAttribute("precaution_list", precaution_list);
            }
            uiModel.addAttribute("checkEvent", checkEventDao.getCheckEventById(precaution.getCheck_event_link()).get(0));
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
        uiModel.addAttribute("title", "Редагування прийнятого заходу id = " + precaution.getId());
        uiModel.addAttribute("actionlink", "/editprecautionpost");
        uiModel.addAttribute("buttonvalue", "Записати зміни");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", precaution);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addprecautions";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/editprecautionpost", method = RequestMethod.POST)
    public String editPrecautionPost(@ModelAttribute Precaution precaution, Model uiModel,
                                     HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                     BindingResult bindingResult) {

        //precautionValidator.validate(precaution, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("precaution", precaution);
            return myRdrct(httpServletRequest) + "/editprecaution?id=" + precaution.getId();
        }
        try {
            checkEventDao.updatePrecautionDate(precaution);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id=" + precaution.getCheck_event_link() + "#tabs-3";
    }

    //edit Sanction
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "editsanction", method = RequestMethod.GET)
    public String editSanction(@RequestParam(defaultValue = "0") String id, Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        Sanction sanction = (Sanction) uiModel.asMap().get("sanction");

        try {

            if (sanction == null && !id.equals("0")) {
                sanction = checkEventDao.getSanctionById(Long.parseLong(id)).get(0);

            }
            uiModel.addAttribute("checkEvent", checkEventDao.getCheckEventById(sanction.getCheck_event_link()).get(0));
            sanction.setArticles_law_link(sanction.getArticles_law_caption());
            sanction.setCharged_amount_str(sanction.getCharged_amount().toString());
            //uiModel.addAttribute("sanction", sanction);

        } catch (IndexOutOfBoundsException ex) {
            uiModel.addAttribute("ex", "Такої перевірки не знайдено");
            return "message";
        } catch (NumberFormatException ex) {
            uiModel.addAttribute("ex", "Не вірна вказівка на перевірку");
            return "message";
        } catch (Exception ex) {
            uiModel.addAttribute("ex", "METHOD: editsanction <br />"+ex);
            return "message";
        }
        uiModel.addAttribute("title", "Редагування санкції");
        uiModel.addAttribute("actionlink", "/editsanctionpost");
        uiModel.addAttribute("buttonvalue", "Записати зміни");
        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute("command", sanction);
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "addsanctions";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/editsanctionpost", method = RequestMethod.POST)
    public String editSanctionPost(@ModelAttribute Sanction sanction, Model uiModel,
                                   HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                   BindingResult bindingResult) {

        sanctionValidator.validate(sanction, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("b1", bindingResult);
            redirectAttributes.addFlashAttribute("sanction", sanction);
            return myRdrct(httpServletRequest) + "/editsanction?id="+sanction.getId();
        }
        try {
            checkEventDao.updateSanctionById(sanction);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "METHOD:editsanctionpost <br/>"+ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id=" + sanction.getCheck_event_link() + "#tabs-3";
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------DELETE EVENT METHOD----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deletecheckinggroupofgoods", method = RequestMethod.GET)
    public String deleteCheckingGroupOfGoods(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "0") String EventId,
                                             HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        try {
            checkEventDao.deleteCheckingGroupOfGoods(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(Long.parseLong(EventId)).get(0));
        } catch (Exception ex) {
            String error = "Method: deleteCheckingGroupOfGoods.<br /> String id = " + id + " String EventId=" + EventId + "<br />" + ex;
            redirectAttributes.addFlashAttribute("ex", error);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/addcheckgoods";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deleteoffensearticles", method = RequestMethod.GET)
    public String deleteOffenseArticles(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "0") String EventId,
                                        HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        try {
            checkEventDao.deleteOffenseArticles(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(Long.parseLong(EventId)).get(0));
        } catch (Exception ex) {
            String error = "Method: deleteOffenseArticles.<br /> String id = " + id + " String EventId=" + EventId + "<br />" + ex;
            redirectAttributes.addFlashAttribute("ex", error);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/addoffencearticles";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deleteprecaution", method = RequestMethod.GET)
    public String deletePrecaution(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "0") String EventId,
                                   HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        try {
            checkEventDao.deletePrecaution(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(Long.parseLong(EventId)).get(0));
        } catch (Exception ex) {
            String error = "Method: deletePrecaution.<br /> String id = " + id + " String EventId=" + EventId + "<br />" + ex;
            redirectAttributes.addFlashAttribute("ex", error);
            return myRdrct(httpServletRequest) + "/message";
        }

        return myRdrct(httpServletRequest) + "/addprecautions";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deletesanction", method = RequestMethod.GET)
    public String deleteSanction(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "0") String EventId,
                                 HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        try {
            checkEventDao.deleteSanctionById(Long.parseLong(id));
            redirectAttributes.addFlashAttribute("event", checkEventDao.getCheckEventById(Long.parseLong(EventId)).get(0));
        } catch (Exception ex) {
            String error = "Method: deleteSanction.<br /> String id = " + id + " String EventId=" + EventId + "<br />" + ex;
            redirectAttributes.addFlashAttribute("ex", error);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/show_event?id="+EventId+"#tabs-3";
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
