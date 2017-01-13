package ua.pp.darknsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.dao.UserDaoImpl;
import ua.pp.darknsoft.entity.User;
import ua.pp.darknsoft.validator.SignupValidator;
import ua.pp.darknsoft.validator.UpduserValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by Andrew on 12.01.2017.
 */
@Controller
public class SecurityController {
    @Autowired
    UserDaoImpl udi;
    @Autowired
    private SignupValidator signupValidator;
    @Autowired
    private UpduserValidator upduserValidator;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User myUser, Model model, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        //Принимает данные формы и обрабатывает их
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        signupValidator.validate(myUser, bindingResult);
        if (bindingResult.hasErrors()) {

            model.asMap().clear();
            redirectAttributes.addFlashAttribute("b", bindingResult);
            return rdrct + "/registration";
        } else {
            myUser.setPwd(passwordEncoder.encode(myUser.getPwd()));
            myUser.setDatereg(LocalDate.now());
            try {
                udi.createUser(myUser);
            } catch (Exception e) {
               // RedirectAttributes redirectAttributes1=null;
               redirectAttributes.addFlashAttribute("error",e);
                return rdrct + "/message?error=3";
            }
            String congrad = "<h1>Поздравляем! Осталось ввойти под своим логином/паролем</h1>";
            redirectAttributes.addFlashAttribute("error",congrad);
            return rdrct + "/message";
        }


    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createUserForm(@ModelAttribute User myUser, Model model) {

        BindingResult bindingResult = (BindingResult) model.asMap().get("b");
        model.addAttribute("command", myUser);
        model.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "registration";
    }

    @RequestMapping(value = "/editprofile",method = RequestMethod.GET)
    public String editProfile(User myUser, Model model) {
        myUser.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        BindingResult bindingResult = (BindingResult) model.asMap().get("b");
        model.addAttribute("command", myUser);
        model.addAttribute(BindingResult.class.getName() + ".command", bindingResult);
        return "editprofile";
    }
    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(value = "/editprofilepost",method = RequestMethod.POST)
    public String editprofilepost(@ModelAttribute User myUser,HttpServletRequest httpServletRequest, Model model, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        upduserValidator.validate(myUser, bindingResult);
        if(bindingResult.hasErrors()){
            model.asMap().clear();
            redirectAttributes.addFlashAttribute("b", bindingResult);
            return rdrct+"/editprofile";
        }
        try {
            myUser.setPwd(passwordEncoder.encode(myUser.getPwd()));
            udi.updateUser(myUser);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e);
            return "redirect:/message";
        }
        return rdrct+"/my_office";
    }
    //------------------------------------------------------------------------------------------------------------------
}
