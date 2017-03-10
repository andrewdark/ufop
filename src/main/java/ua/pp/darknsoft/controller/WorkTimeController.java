package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.UserDao;
import ua.pp.darknsoft.dao.WorkTimeDao;
import ua.pp.darknsoft.entity.CauseCatalog;
import ua.pp.darknsoft.entity.User;
import ua.pp.darknsoft.entity.WorkTime;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 02.03.2017.
 */
@Controller
public class WorkTimeController {
    @Autowired
    CatalogDao catalogDao;
    @Autowired
    WorkTimeDao workTimeDao;
    @Autowired
    UserDao userDao;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
 String searchdate=dtf.format(localDate);



    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------WORK TIME MODULE---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/myworktime", method = RequestMethod.GET)
    public String myWorkTime(@ModelAttribute WorkTime myWorkTime, Model uiModel){
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        short absent = 0, present = 1;
        try{
            List<WorkTime> wtList = workTimeDao.getMyWorkWorkTimeDESC(user,1);
            List<CauseCatalog> ccListAbsent;
            List<CauseCatalog> ccListPresent;
            Map<Integer,String> causes = new HashMap<>();
            uiModel.addAttribute("myWorkTime",workTimeDao.getMyWorkWorkTimeASC(user,null));

            if(wtList.isEmpty()){
                uiModel.addAttribute("p_or_e",false);
                ccListPresent = catalogDao.getCauseCatalogByType(present);
                for (CauseCatalog cc : ccListPresent){
                    causes.put(cc.getId(),cc.getName());
                }
                uiModel.addAttribute("causes",causes);
            }else {
                if(wtList.get(0).getType_of_action()==0){
                    uiModel.addAttribute("p_or_e",false);
                    ccListPresent = catalogDao.getCauseCatalogByType(present);
                    for (CauseCatalog cc : ccListPresent){
                        causes.put(cc.getId(),cc.getName());
                    }
                    uiModel.addAttribute("causes",causes);

                }else{
                    uiModel.addAttribute("p_or_e",true);
                    ccListAbsent = catalogDao.getCauseCatalogByType(absent);
                    for (CauseCatalog cc : ccListAbsent){
                        causes.put(cc.getId(),cc.getName());
                    }
                    uiModel.addAttribute("causes",causes);
                }
            }

        }catch (Exception ex){
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        uiModel.addAttribute("command",myWorkTime);
        return "myworktime";
    }
    @RequestMapping(value = "/myworktimepost", method = RequestMethod.POST)
    public String myWorkTimepost(@ModelAttribute WorkTime myWorkTime,HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes, Model uiModel){
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        try {

            workTimeDao.setMyWorkWorkTime(user,myWorkTime.getCause_link());
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct +"/message";
        }
        return rdrct + "/myworktime";
    }
    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------ACCEPTING WORK TIME------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptworktime", method = RequestMethod.GET)
    public String acceptingWorkTime(@RequestParam(defaultValue = "") String date,@ModelAttribute WorkTime myWorkTime, Model uiModel, HttpServletRequest httpServletRequest,
                                    RedirectAttributes redirectAttributes){
        String ld_f,ld_l;
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
       try{
           ld_f = dtf.format(LocalDate.parse(date));
           ld_l = ld_f;
       }catch (Exception ex){
           ld_f = dtf.format(localDate);
           ld_l = dtf.format(localDate);
       }

        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        try {
            uiModel.addAttribute("mySubdivision",userDao.getUserStructureNameByUserName(user));
            uiModel.addAttribute("worktime",workTimeDao.getMySlavesWorkTimeDesc(user,ld_f+" 00:00:00.00001",ld_l+" 23:59:59.00001",null));
            uiModel.addAttribute("searchdate", ld_f);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct +"/message";
        }
        return "acceptworktime";
    }
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptingWorkTimepost", method = RequestMethod.POST)
    public String acceptingWorkTimepost(@RequestParam long id, @RequestParam Boolean accept, HttpServletRequest httpServletRequest,
                                        RedirectAttributes redirectAttributes, Model uiModel){
        WorkTime myWorkTime = new WorkTime();
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        myWorkTime.setUser_accepted_link(userDao.getUserIdByUserName(user.toLowerCase()));
        try {
            myWorkTime.setId(id);
            myWorkTime.setAccepted(accept);
            myWorkTime.setUser_accepted_link(userDao.getUserIdByUserName(user.toLowerCase()));
            workTimeDao.acceptWorkTime(myWorkTime);
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct +"/message";
        }
        return rdrct + "/acceptworktime";
    }
    // next
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/showmysubordinates", method = RequestMethod.GET)
    public String showMySubordinates(Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes){
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        try{
            uiModel.addAttribute("structure",catalogDao.getMyStructureByMyStatus(user));
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct +"/message";
        }
        return "showmysubordinates";
    }
    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------AJAX HELPER--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(value = "/ajax_getUsersByStructureLink", produces = {"application/json; charset=UTF-8"})
    public String ajax_getUsersByStructureLink(@RequestParam String param1) {
        String html="<div class=\"whiteblock\">";
        try{
            List<User> userList = userDao.getUsersByStructureLink(param1);
            for (User items: userList
                 ) {
                html=html+items.getUsername();
            }
        }catch (Exception ex){
            html=html+"Помилка: "+ex;
        }

        html=html+"</div>";
        return html;
    }
}
