package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.WorkTimeDao;
import ua.pp.darknsoft.entity.CauseCatalog;
import ua.pp.darknsoft.entity.WorkTime;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
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
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        return rdrct + "/myworktime";
    }
    //------------------------------------------------------------------------------------------------------------------
}
