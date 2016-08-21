package blog.controllers;

import blog.forms.LoginForm;
import blog.forms.RegisterForm;
import blog.services.NotificationService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/users/register")
    public String login(LoginForm loginForm) {
        return "users/register";
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "users/register";
        }
        String fullName=registerForm.getFullName();
        String password = registerForm.getPassword();
        String username = registerForm.getUsername();
        System.out.println( fullName+" "+username);

        userService.register(username, password, fullName);


        notifyService.addInfoMessage("Registration successful");
        return "redirect:/";
    }
}
