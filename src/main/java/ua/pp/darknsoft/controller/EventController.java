package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.dao.CommercialObjectDao;
import ua.pp.darknsoft.entity.CheckEventSupplemented;
import ua.pp.darknsoft.entity.CheckingCommObj;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.Ufop;

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
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------MASTER CHECK EVENT-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addevent",method = RequestMethod.GET)
    public String addEvent(Model uiModel,RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title","Створення нової перевірки");
        CheckEventSupplemented checkEventSupplemented = new CheckEventSupplemented();
Ufop ufop = new Ufop();
        //CheckingCommObj co1 = new CheckingCommObj(),co2 = new CheckingCommObj(),co3 = new CheckingCommObj();
        //co1.setComm_obj_link(1); co2.setComm_obj_link(2); co3.setComm_obj_link(3);
        List<CheckingCommObj> checkingCommObjs_list = new LinkedList<>();
               checkEventSupplemented.setCommobj_list(checkingCommObjs_list);
        try {
            ufop = (Ufop) uiModel.asMap().get("ufop");
            if(ufop.getId()>0){
                List<CommercialObject> comobj_list = commercialObjectDao.getCommObjByUfop_link(ufop.getId());
                uiModel.addAttribute("comobj_list",comobj_list);
                for(CommercialObject items: comobj_list){
                    checkingCommObjs_list.add(new CheckingCommObj()) ;
                    checkingCommObjs_list.get(checkingCommObjs_list.size() - 1).setComm_obj_link(items.getId());
                }
                checkEventSupplemented.setUfop_link(ufop.getId());
            }
            else {
                redirectAttributes.addFlashAttribute("ex","Виберіть суб'єкта господарювання");
                return myRdrct(httpServletRequest)+"/message";
            }
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex",ex);
            return myRdrct(httpServletRequest)+"/message";
        }
        uiModel.addAttribute("command",checkEventSupplemented);
        return "addevent";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/addeventsupplementedpost",method = RequestMethod.POST)
    public String addEventSupplementedPost(@ModelAttribute CheckEventSupplemented checkEventSupplemented, Model uiModel,
                                           HttpServletRequest httpServletRequest,RedirectAttributes redirectAttributes) {
        try {

            for(int i=0;i<checkEventSupplemented.getCommobj_list().size();i++){
                if(checkEventSupplemented.getCommobj_list().get(i).isChecking()){

                    //checkEventSupplemented.getCommobj_list().get(i).setComm_obj_link(10);
                }
                else {
                    checkEventSupplemented.getCommobj_list().remove(i);
                }
            }

            checkEventSupplemented.setCreator_link(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            redirectAttributes.addFlashAttribute("event",checkEventDao.createEventSupplemented(checkEventSupplemented));
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex",ex);
            return myRdrct(httpServletRequest)+"/message";
        }
        return myRdrct(httpServletRequest)+"/show_event";
    }


    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------SHOW EVENT DETAIL-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/show_event",method = RequestMethod.GET)
    public String showEvent(@RequestParam(defaultValue = "1") String id, Model uiModel, RedirectAttributes redirectAttributes,
                            HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title","Створення нової перевірки");
        CheckEventSupplemented checkEventSupplemented = new CheckEventSupplemented();
        try {
            checkEventSupplemented = (CheckEventSupplemented)uiModel.asMap().get("event");
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex",ex);
            return myRdrct(httpServletRequest)+"/message";
        }
        uiModel.addAttribute("event",checkEventSupplemented);
        return "show_event";
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------STATIC METHOD-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private static String myRdrct(HttpServletRequest httpServletRequest){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }
}
