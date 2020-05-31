package com.cristianvera.springboot.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model, RedirectAttributes redirectAttributes, Principal principal, @RequestParam(value = "", required = false) String error) {
        if(principal != null) {
            redirectAttributes.addFlashAttribute("info", "Ya se ha iniciado sesión anteriormente.");
            return "redirect:/login";
        }

        if(error != null) {
            model.addAttribute("error", "El usuario y/o contraseña no existen.");
        }

        model.addAttribute("titulo", "Inicio de sesión");
        return "login";
    }

}
