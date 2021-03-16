package ru.skb.tech.test.springhibernatesecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skb.tech.test.springhibernatesecurity.dto.SkbUser;
import ru.skb.tech.test.springhibernatesecurity.services.messageservice.Recorder;
import ru.skb.tech.test.springhibernatesecurity.services.userservice.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final static Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService<SkbUser> userService;

    @Autowired
    private Recorder<SkbUser> recorder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        final StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }


    @RequestMapping("/registrationForm")
    public String registrationForm(Model model) {
        model.addAttribute("skbUser", new SkbUser());
        return "registration-form";
    }

    @RequestMapping("/register/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("skbUser") SkbUser skbUser, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            LOG.warn("Ошибки валидации:\n{}", bindingResult.toString());
            return "registration-form";
        }

        final SkbUser existingUser = userService.findByUserLogin(skbUser.getLogin());
        if (existingUser != null) {
            LOG.warn("Пользователь с таким логином уже существует:\n{}", existingUser);
            model.addAttribute("skbUser", new SkbUser());
            model.addAttribute("registrationError", "Пользователь с таким логином уже существует");
            return "registration-form";
        }

        //запись в бд
        userService.save(skbUser);

        //подготовка на отправку в шину
        recorder.record(skbUser);

        model.addAttribute("email", skbUser.getEmail());

        return "registration-confirmation";
    }
}
