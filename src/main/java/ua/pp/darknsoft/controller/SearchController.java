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
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.CheckEvent;
import ua.pp.darknsoft.entity.Ufop;

import javax.servlet.http.HttpServletRequest;
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
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Ufop> ufop;
        try{
            ufop = setLastEvent(ufopDao.getUfopByCreatorLink(total, pageid1, Integer.parseInt(id)));
        }catch (Exception ex){
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
            pageid1 = (pageid1 - 1) * total + 1;
        }
        List<Ufop> ufop;
        try{
            if(Integer.parseInt(id)==1) ufop = setLastEvent(ufopDao.getUfopByWithoutEvent(total, pageid1));
            else ufop = setLastEvent(ufopDao.getUfopByWithoutEventForOneYear(total, pageid1));

        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", "Method:viewsListWithoutEvent <br />" + ex);
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
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH Check event--------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------SEARCH Commercial object--------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

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

    private List<Ufop> setLastEvent(List<Ufop> ufopList){
        for (int i = 0; i < ufopList.size(); i++) {
            try {
                CheckEvent temp = checkEventDao.getLastCheckEventByUfopLink(ufopList.get(i).getId()).get(0);
                ufopList.get(i).setLast_event(temp.getEvent_date_end() + " " + temp.getStructure_catalog_name());
            }catch ( java.lang.IndexOutOfBoundsException ex){
                ufopList.get(i).setLast_event("Не перевірявся");
            }
            catch (Exception ex) {
                ufopList.get(i).setLast_event("" + ex);
            }
        }
        return ufopList;
    }
}