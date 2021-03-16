package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.UserRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

@Controller
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String login(@RequestParam(required = false) String error, Model model) {
        if ("true".equals(error)) {
            model.addAttribute("errorLogin", "INVALID LOGIN OR PASSWORD, PLREAS TRY AGAIN.");
            return "login";
        }
        return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam String userName, @RequestParam String password) {

        Optional<User> byLogin = userRepository.findByUserName(userName);
        if (byLogin.isPresent()) {
            if (byLogin.get().getPassword().equals(password)) {
                if (byLogin.get().getBranch().equals("HEAD")) {
                    return "redirect:admin/";
                } else {
                    long id = byLogin.get().getId();
                    return "redirect:worker/" + id;
                }
            }
        }
        return "redirect:?error=true";
    }

}
