package ua.lemekh.webapp.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lemekh.webapp.mailEvent.OnRegistrationCompleteEvent;
import ua.lemekh.webapp.model.User;
import ua.lemekh.webapp.model.UserInformation;
import ua.lemekh.webapp.model.VerificationToken;
import ua.lemekh.webapp.service.UserInformationService;
import ua.lemekh.webapp.service.UserService;
import ua.lemekh.webapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

   @Autowired
   private UserInformationService userInformationService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "addInformation", method = RequestMethod.GET)
    public String showAdd(Model model){
        model.addAttribute("UserInformation", new UserInformation());
        return "addInformation";
    }

    @RequestMapping(value = "addInformation", method = RequestMethod.POST)
    public String addInformation(@ModelAttribute("UserInformation")UserInformation userInformation, Model model){
        userInformation.setId(userService.getCurrentUser().getId());
        userInformationService.addInformation(userInformation);
        return "redirect:/addInformation";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        UserInformation userInformation = new UserInformation();
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userForm.setUserInformation(userInformation);
        userService.save(userForm);

        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(userForm, request.getLocale(), getAppUrl(request)));

        //securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        model.addAttribute("user", userService.getCurrentUser());
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }



    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            ( Model model, @RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/welcome" ;
        }

        User user = verificationToken.getUser();
        User user2 = userService.findByUsername(user.getUsername());
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/welcome" ;
        }

        user2.setEnabled(true);
        userService.saveVerificationUser(user2);
        return "redirect:/login";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
