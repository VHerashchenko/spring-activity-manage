package com.vh.activitymanage.controller;

import com.vh.activitymanage.model.dto.UserDTO;
import com.vh.activitymanage.model.entity.User;
import com.vh.activitymanage.service.SecurityService;
import com.vh.activitymanage.service.UserService;
import com.vh.activitymanage.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService regFormService;

    private final UserValidator noteValidator;

    private final ModelMapper mapper;

    private final SecurityService securityService;

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/")
    public String mainPage(){
        return "registration/index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("noteForm", new UserDTO());

        return "registration/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("noteForm") UserDTO userForm, BindingResult bindingResult) {
        noteValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }

        User user = mapper.map(userForm, User.class);

        regFormService.save(user);

        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "error.login");

        if (logout != null)
            model.addAttribute("message", "logged.out");

        return "registration/login";
    }
}