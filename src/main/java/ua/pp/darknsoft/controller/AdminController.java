package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.CatalogDao;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andrew on 28.02.2017.
 */
@Controller
public class AdminController {
    @Autowired
    CatalogDao catalogDao;


    @RequestMapping(value = "/adduser_to_strorgtlb", method = RequestMethod.GET)
    public String addUserToStrOrg( Model uiModel) {

        BindingResult bindingResult = (BindingResult) uiModel.asMap().get("b1");
        uiModel.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        uiModel.addAttribute("command",null);

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
    public List<String> ajaxTest(@RequestParam(defaultValue ="term",value = "term") String term) {
        List<String> records = new LinkedList<>();
        //List<>
//        User u1 = new User();
//        u1.setId(1); u1.setUsername("dark1");
//        User u2 = new User();
//        u2.setId(2); u2.setUsername("dark2");
//        records.add(u1);
//        records.add(u2);
records.add("String 1"+term);
records.add("String 2"+term);
        return records;
    }
}
