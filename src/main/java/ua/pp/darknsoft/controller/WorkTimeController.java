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
import ua.pp.darknsoft.entity.OverUser;
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



    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------WORK TIME MODULE---------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/myworktime", method = RequestMethod.GET)
    public String myWorkTime(@ModelAttribute WorkTime myWorkTime, Model uiModel) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        short absent = 0, present = 1;
        try {
            List<WorkTime> wtList = workTimeDao.getMyWorkWorkTimeDESC(user, 1);
            List<CauseCatalog> ccListAbsent;
            List<CauseCatalog> ccListPresent;
            Map<Integer, String> causes = new HashMap<>();
            uiModel.addAttribute("myWorkTime", workTimeDao.getMyWorkWorkTimeASC(user, 25));

            if (wtList.isEmpty()) {
                uiModel.addAttribute("p_or_e", false);
                ccListPresent = catalogDao.getCauseCatalogByType(present);
                for (CauseCatalog cc : ccListPresent) {
                    causes.put(cc.getId(), cc.getName());
                }
                uiModel.addAttribute("causes", causes);
            } else {
                if (wtList.get(0).getType_of_action() == 0) {
                    uiModel.addAttribute("p_or_e", false);
                    ccListPresent = catalogDao.getCauseCatalogByType(present);
                    for (CauseCatalog cc : ccListPresent) {
                        causes.put(cc.getId(), cc.getName());
                    }
                    uiModel.addAttribute("causes", causes);

                } else {
                    uiModel.addAttribute("p_or_e", true);
                    ccListAbsent = catalogDao.getCauseCatalogByType(absent);
                    for (CauseCatalog cc : ccListAbsent) {
                        causes.put(cc.getId(), cc.getName());
                    }
                    uiModel.addAttribute("causes", causes);
                }
            }

        } catch (Exception ex) {
            uiModel.addAttribute("ex", ex);
            return "message";
        }
        uiModel.addAttribute("command", myWorkTime);
        return "myworktime";
    }

    @RequestMapping(value = "/myworktimepost", method = RequestMethod.POST)
    public String myWorkTimepost(@ModelAttribute WorkTime myWorkTime, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes, Model uiModel) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        try {

            workTimeDao.setMyWorkWorkTime(user, myWorkTime.getCause_link());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        return rdrct + "/myworktime";
    }

    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------ACCEPTING WORK TIME------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptworktime", method = RequestMethod.GET)
    public String acceptingWorkTime(@RequestParam(defaultValue = "") String date, @ModelAttribute WorkTime myWorkTime, Model uiModel, HttpServletRequest httpServletRequest,
                                    RedirectAttributes redirectAttributes) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String ld_f, ld_l;
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        try {
            ld_f = dtf.format(LocalDate.parse(date));
            ld_l = ld_f;
        } catch (Exception ex) {
            ld_f = dtf.format(localDate);
            ld_l = dtf.format(localDate);
        }

        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        try {
            uiModel.addAttribute("mySubdivision", userDao.getUserStructureNameByUserName(user));
            uiModel.addAttribute("worktime", workTimeDao.getMySlavesWorkTimeDesc(user, ld_f + " 00:00:00.00001", ld_l + " 23:59:59.00001", null));
            uiModel.addAttribute("searchdate", ld_f);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        return "acceptworktime";
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptingWorkTimepost", method = RequestMethod.POST)
    public String acceptingWorkTimepost(@RequestParam long id, @RequestParam Boolean accept, HttpServletRequest httpServletRequest,
                                        RedirectAttributes redirectAttributes, Model uiModel) {
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
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        return rdrct + "/acceptworktime";
    }

    // next
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/showmysubordinates", method = RequestMethod.GET)
    public String showMySubordinates(Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;

        try {
            uiModel.addAttribute("structure", catalogDao.getMyStructureByMyStatus(user));
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return rdrct + "/message";
        }
        return "showmysubordinates";
    }

    //NEXT PAGE WT_DETAIL
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptwt_detail", method = RequestMethod.GET)
    public String acceptWTDetail(@RequestParam(defaultValue = "") String id,
                                 @RequestParam(defaultValue = "0001-01-01") String datestart,
                                 @RequestParam(defaultValue = "0001-01-01") String datestop,
                                 @ModelAttribute WorkTime myWorkTime, Model uiModel, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes) {
        WorkTime workTime = new WorkTime();

        if (datestart.equals("0001-01-01")) datestart = LocalDate.now().toString();
        if (datestop.equals("0001-01-01")) datestop = LocalDate.now().plusDays(1).toString();
        String user = SecurityContextHolder.getContext().getAuthentication().getName().toString();

        try {
            workTime.setUser_link(Integer.parseInt(id));
            workTime.setS_user_accepted_link(user);
            workTime.setUser_name(userDao.getUserNameById(Integer.parseInt(id)));
            Map<Integer, String> causes = new HashMap<>();
            List<WorkTime> wtList = workTimeDao.getMyWorkWorkTimeDESC(workTime.getUser_name(),1);
            if (wtList.isEmpty()) {
                uiModel.addAttribute("p_or_e", false);
            } else {
                if (wtList.get(0).getType_of_action() == 0) {
                    uiModel.addAttribute("p_or_e", false);
                } else {
                    uiModel.addAttribute("p_or_e", true);
                    List<CauseCatalog>ccListAbsent = catalogDao.getCauseCatalogByType((short)2);
                    for (CauseCatalog cc : ccListAbsent) {
                        causes.put(cc.getId(), cc.getName());
                    }
                    uiModel.addAttribute("causes", causes);
                }
            }
            uiModel.addAttribute("worktime", workTimeDao.getWTUserDetail(Integer.parseInt(id), datestart, datestop));
            uiModel.addAttribute("wt_user", "id: " + id + " За період від: " + datestart + " до: " + datestop);
            uiModel.addAttribute("title", "РОБОЧИЙ ЧАС КОРИСТУВАЧА "+workTime.getUser_name());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        uiModel.addAttribute("command",workTime);
        return "acceptwt_detail";
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @RequestMapping(value = "/acceptwt_detailpost", method = RequestMethod.POST)
    public String acceptWTDetailpost(@ModelAttribute WorkTime workTime, HttpServletRequest httpServletRequest,
                                     RedirectAttributes redirectAttributes, Model uiModel) {

        try {
            workTime.setS_user_accepted_link(SecurityContextHolder.getContext().getAuthentication().getName().toString());
            List<WorkTime> wtList = workTimeDao.getMyWorkWorkTimeDESC(workTime.getUser_name(),1);
            if (wtList.isEmpty()) {
                return myRdrct(httpServletRequest) + "/acceptwt_detail?id="+workTime.getUser_link();
            } else {
                if (wtList.get(0).getType_of_action() == 0) {
                    redirectAttributes.addFlashAttribute("ex", "Користувач пішов з роботи самостійно");
                    return myRdrct(httpServletRequest) + "/message";
                } else {
                  workTimeDao.setWorkTimeUserByBoss(workTime);
                }
            }
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", "METHOD: acceptWTDetailpost for user:"+workTime.getUser_name()+"<br />" + ex);
            return myRdrct(httpServletRequest) + "/message";
        }
        return myRdrct(httpServletRequest) + "/acceptwt_detail?id="+workTime.getUser_link();
    }

    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------AJAX HELPER--------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')")
    @ResponseBody
    @RequestMapping(value = "/ajax_getUsersByStructureLink", produces = {"application/json; charset=UTF-8"})
    public String ajax_getUsersByStructureLink(@RequestParam String param1) {
        String html = "<div class=\"whiteblock\">";
        try {
            String status = "";
            List<OverUser> userList = userDao.getUsersByStructureLink(param1);
            for (int i = 0; i <= userList.size() - 1; i++) {
                if (userList.get(i).getWorkTime().getType_of_action() == 0) {
                    html = html + "<font color=\"red\"> Користувач: " + userList.get(i).getCt_ln() + " " + userList.get(i).getCt_fn() + " - відсутній з " + userList.get(i).getWorkTime().getDatereg().toString().split("\\.")[0] + " по причині: " + userList.get(i).getWorkTime().getS_cause_link() + "</font><br />";
                }
                if (userList.get(i).getWorkTime().getType_of_action() == 1) {
                    html = html + "<font color=\"green\"> Користувач: " + userList.get(i).getCt_ln() + " " + userList.get(i).getCt_fn() + " - присутній з " + userList.get(i).getWorkTime().getDatereg().toString().split("\\.")[0] + " по причині: " + userList.get(i).getWorkTime().getS_cause_link() + "</font><br />";
                }
                if (userList.get(i).getWorkTime().getType_of_action() == 2) {
                    html = html + "<font color=\"gray\"> Користувач: " + userList.get(i).getCt_ln() + " " + userList.get(i).getCt_fn() + " - місце знаходження не встановлено по причині: " + userList.get(i).getWorkTime().getS_cause_link() + "</font><br />";
                }

            }
        } catch (Exception ex) {
            html = html + "Помилка: " + ex;
        }

        html = html + "</div>";
        return html;
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------- SERVICE -----------------------
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
