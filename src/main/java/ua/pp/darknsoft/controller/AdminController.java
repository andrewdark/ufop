package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.UserDao;
import ua.pp.darknsoft.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andrew on 28.02.2017.
 */
@Controller
public class AdminController {
    @Autowired
    CatalogDao catalogDao;
    @Autowired
    UserDao userDao;


    @RequestMapping(value = "/adduser_to_strorgtlb", method = RequestMethod.GET)
    public String addUserToStrOrg(Model uiModel) {

        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        uiModel.addAttribute("command", null);

        return "adduser_to_strorgtlb";
    }

    @RequestMapping(value = "/adduser_to_strorgtlbpost", method = RequestMethod.POST)
    public String addUserToStrOrgpost(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                      Model uiModel, BindingResult bindingResult) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
//        structureOrganizationValidator.validate(structureOrganization, bindingResult);
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("b1", bindingResult);
//            return rdrct + "/adduser_to_strorgtlb";
//        }

        return rdrct + "/adduser_to_strorgtlb";
    }
    //---------------------------------------------AJAX FUNC------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    @ResponseBody
    public List<String> ajaxTest(@RequestParam(defaultValue = "term", value = "term") String term) {
        List<User> userList;
        List<String> records = new ArrayList<>();
        try{
            userList=userDao.getUsersByUserNameLike("%"+term+"%");
            for (User items: userList
                 ) {
                records.add(items.getUsername());
            }
        }catch (Exception ex){

        }


//        records.add("String 1 +" + term);
//        records.add("String 2 +" + term);
        return records;
    }
    @RequestMapping(value = "/ajax_start_user", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax_select_loc(@RequestParam(defaultValue = "NAN") String treemark, @RequestParam(defaultValue = "0") String username, Model uiModel) {
        String html = "hello world";
        String option = "<option disabled>Виберіть населений пункт</option><option value=\"\"></option>";
        List<User> userList;
        try {
            if(!username.equals("0")){
                userList = userDao.getUsersByUserNameLike(username);
                html = "" + userList.get(0).getId() +" "+userList.get(0).getUsername() + " " + userList.get(0).getEmail();
            }else {
                html = "User not found";
            }

        } catch (Exception e) {
            return "Error: " + e;
        }



        return html;
    }


}
