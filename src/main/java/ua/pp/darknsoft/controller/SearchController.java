package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.dao.CommercialObjectDao;
import ua.pp.darknsoft.dao.SearchUniversalDao;
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.CheckEvent;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.Ufop;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 10.07.2017.
 */
@Controller
public class SearchController {
    @Autowired
    UfopDao ufopDao;
    @Autowired
    CheckEventDao checkEventDao;
    @Autowired
    SearchUniversalDao searchUniversalDao;
    @Autowired
    CommercialObjectDao commercialObjectDao;

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH UFOP---------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/viewslistbycreator/{pageid}", method = RequestMethod.GET)
    public String viewsListByCreatorLink(@PathVariable int pageid, @RequestParam(defaultValue = "1") String id, Model uiModel,
                                         RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total;
        }
        List<Ufop> ufop;
        try {
            ufop = setLastEvent(ufopDao.getUfopByCreatorLink(total, pageid1, Integer.parseInt(id)));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListByCreatorLink <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewslistbycreator");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("id", id);
        uiModel.addAttribute("total_page", "NAN");

        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
    }

    // NEXT 1
    @RequestMapping(value = "/viewslistwithoutevent/{pageid}", method = RequestMethod.GET)
    public String viewsListWithoutEvent(@PathVariable int pageid, @RequestParam(defaultValue = "1") String id, Model uiModel,
                                        RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total;
        }
        List<Ufop> ufop;
        try {
            if (Integer.parseInt(id) == 1) ufop = setLastEvent(ufopDao.getUfopByWithoutEvent(total, pageid1));
            else ufop = setLastEvent(ufopDao.getUfopByWithoutEventForOneYear(total, pageid1));

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListWithoutEvent <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewslistwithoutevent");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("id", id);
        uiModel.addAttribute("total_page", "NAN");

        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
    }

    //NEXT2
    @RequestMapping(value = "/viewslistbyunitandtime/{pageid}", method = RequestMethod.GET)
    public String viewsListByUnitAndTime(@PathVariable int pageid, @RequestParam(defaultValue = "1") String id, @RequestParam(defaultValue = "0") String id1,
                                         Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        String utime = "0 days";
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total;
        }
        List<Ufop> ufop = new ArrayList<Ufop>();
        try {
            if (Integer.parseInt(id1) == 1) utime = "1 days";
            if (Integer.parseInt(id1) == 2) utime = "7 days";
            if (Integer.parseInt(id1) == 3) utime = "31 days";
            if (Integer.parseInt(id1) == 4) utime = "93 days";
            if (Integer.parseInt(id1) == 5) utime = "360 days";
            if (Integer.parseInt(id1) == 6) utime = "3600 days";

            ufop = setLastEvent(ufopDao.getUfopByUnitAndTime(total, pageid1, id, utime));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListByUnitAndTime <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("u_size", ufop.size());
        uiModel.addAttribute("viewslistu", "viewslistbyunitandtime");
        uiModel.addAttribute("ufop", ufop);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("id", id);
        uiModel.addAttribute("id1", id1);
        uiModel.addAttribute("total_page", "NAN");

        if (ufop.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_ufop";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH Check event--------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/viewslistevent/{pageid}", method = RequestMethod.GET)
    public String viewsListEvent(@PathVariable int pageid, @RequestParam(defaultValue = "1") String param0,
                                 @RequestParam(defaultValue = "1980-01-01") String param1, @RequestParam(defaultValue = "2999-01-01") String param2,
                                 @RequestParam(defaultValue = "-1") String param3, @RequestParam(defaultValue = "-1") String param4,
                                 Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total;
        }
        List<CheckEvent> eventList;
        try {
            CheckEvent checkEvent = new CheckEvent();
            checkEvent.setStructure_catalog_link(Integer.parseInt(param0));
            checkEvent.setEvent_date_begin(param1);
            checkEvent.setEvent_date_end(param2);
            checkEvent.setCheck_type(Integer.parseInt(param3));
            checkEvent.setCheck_violation(Integer.parseInt(param4));
            eventList = searchUniversalDao.checkEventList(checkEvent, total, pageid1);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListEvent <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        //uiModel.addAttribute("u_size", ufop.size());
        String test = " " + param0 + " " + param1 + " " + param2 + " " + param3 + " " + param4;
        uiModel.addAttribute("test", test);
        uiModel.addAttribute("viewslistlink", "viewslistevent");
        uiModel.addAttribute("eventList", eventList);
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("param0", param0);
        uiModel.addAttribute("param1", param1);
        uiModel.addAttribute("param2", param2);
        uiModel.addAttribute("param3", param3);
        uiModel.addAttribute("param4", param4);
        uiModel.addAttribute("total_page", "NAN");

        if (eventList.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_event";
    }

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH Commercial object--------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/viewslistcommobj/{pageid}", method = RequestMethod.GET)
    public String viewsListCommObj(@PathVariable int pageid, @RequestParam(defaultValue = "1") String param0,
                                   @RequestParam(defaultValue = "-1") String param1, @RequestParam(defaultValue = "-1") String param2,
                                   @RequestParam(defaultValue = "-1") String param3,@RequestParam(defaultValue = "-1") String param4,
                                   Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        // param0 = a_place_of_reg - String
        // param1 = degree_risk_link - int
        // param2 = obj_type - int
        // param3 = goods_catalog_link - String
        if (pageid <= 0) {
            uiModel.addAttribute("ex", "Не вірна сторінка");
            return "message";
        }
        int total = 10;
        int pageid1 = pageid;
        if (pageid == 1) {
            pageid1 = 0;
        } else {
            pageid1 = (pageid1 - 1) * total;
        }
        List<CommercialObject> comobjList = null;
        try {
            CommercialObject comobj = new CommercialObject();
            comobj.setA_place_of_reg(param0);
            comobj.setDegree_risk_link(Integer.parseInt(param1));
            comobj.setObj_type(Integer.parseInt(param2));
            if(param3.equals("0")) comobjList = commercialObjectDao.getCommObjByAdressWithOutGroupOfGoods(comobj,total,pageid1);
            else comobjList = commercialObjectDao.getCommObjByAdressAndGroupOfGoods(comobj,param3,total,pageid1);
            uiModel.addAttribute("comobjList",comobjList);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListEvent <br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }


        uiModel.addAttribute("viewslistlink", "viewslistcommobj");
        uiModel.addAttribute("page_id", pageid);
        uiModel.addAttribute("param0", param0);
        uiModel.addAttribute("param1", param1);
        uiModel.addAttribute("param2", param2);
        uiModel.addAttribute("param3", param3);
        uiModel.addAttribute("param4", param4);
        uiModel.addAttribute("total_page", "NAN");

        if (comobjList.isEmpty()) {
            uiModel.addAttribute("ex", "Нажаль, немає жодного запису");
        }
        return "viewslist_commobj";
    }
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH People-------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------SERVICE----------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private static String myRdrct(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }

    private List<Ufop> setLastEvent(List<Ufop> ufopList) {
        if (ufopList.isEmpty()) return ufopList;
        for (int i = 0; i < ufopList.size(); i++) {
            try {
                CheckEvent temp = checkEventDao.getLastCheckEventByUfopLink(ufopList.get(i).getId()).get(0);
                ufopList.get(i).setLast_event(temp.getEvent_date_end() + " " + temp.getStructure_catalog_name());
            } catch (java.lang.IndexOutOfBoundsException ex) {
                ufopList.get(i).setLast_event("Не перевірявся");
            } catch (Exception ex) {
                ufopList.get(i).setLast_event("setLastEvent " + ex);
            }
        }
        return ufopList;
    }
}