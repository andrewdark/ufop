package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.AdminDao;
import ua.pp.darknsoft.dao.CatalogDao;
import ua.pp.darknsoft.dao.UserDao;
import ua.pp.darknsoft.entity.Role;
import ua.pp.darknsoft.entity.StructureCatalog;
import ua.pp.darknsoft.entity.User;

import static ua.pp.darknsoft.support.StaticMethod.*;

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
    @Autowired
    AdminDao adminDao;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/adduser_to_strorgtlb", method = RequestMethod.GET)
    public String addUserToStrOrg(Model uiModel, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("title", "Редагування користувача");
        try {
            List<StructureCatalog> structureList = catalogDao.getMyStructureByMyStatus(SecurityContextHolder.getContext().getAuthentication().getName().toString().toLowerCase());
            uiModel.addAttribute("structureList", structureList);
            List<Role> roleList = catalogDao.getAllRole();
            uiModel.addAttribute("roleList", roleList);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("ex", ex);
            return myRdrct(httpServletRequest) + "/message";
        }

        return "adduser_to_strorgtlb";
    }

    //---------------------------------------------AJAX FUNC------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    @ResponseBody
    public List<User> ajaxTest(@RequestParam String term, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]")) {
            List<User> records = userDao.getUsersByUserNameLike(term + "%");
            return records;
        } else return null;

    }

    @RequestMapping(value = "/ajax_start_user", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String ajax_select_loc(@RequestParam String id, @RequestParam String structure_link, @RequestParam String role_link,
                                  @RequestParam Boolean en,@RequestParam String email, Model uiModel) {
        String html = "hello world";
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMINISTRATOR]")) {
            try {
                if (id.length() > 0) {
                    User newUser = new User();
                    newUser.setId(Integer.parseInt(id));
                    newUser.setRole_name(role_link);
                    newUser.setStructure_link(structure_link);
                    newUser.setEmail(email);
                    newUser.setEnabled(en);
                    adminDao.updateUser(newUser);

                    html = "<font color=\"green\">SUCCESS</font> " + "Id: " + id + " Structure_TreeMark: " + structure_link + " RoleId: " + role_link;
                } else {
                    html = "Користувача не знайдено ";
                }

            } catch (Exception e) {
                html = "<font color=\"red\">ERROR: </font>" + e.getMessage();
            }


            return html;
        } else return "НЕМАЄ ДОЗВОЛУ НА ОТРИМАННЯ ЦІЄЇ ІНФОРМАЦІЇ";

    }


}
