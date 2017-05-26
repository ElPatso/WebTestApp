package ua.lemekh.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lemekh.webapp.model.User;
import ua.lemekh.webapp.service.UserService;

/**
 * Created by Ostap on 22.05.2017.
 */
@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "userslist", method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("userslist", userService.findAll());
        return "userslist";
    }

    @RequestMapping(value = "/remove/{username}")
    public String removeUser(@PathVariable("username") String username){
        userService.deleteByUsername(username);
        return "redirect:/userslist";
    }
}
