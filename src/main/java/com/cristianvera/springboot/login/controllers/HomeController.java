package com.cristianvera.springboot.login.controllers;

import com.cristianvera.springboot.login.models.entity.SuperUsuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/","/home"})
    public String home(Model model, Authentication authentication) {
        SuperUsuario usuario = (SuperUsuario) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Sistema Educativo");
        return "home";
    }
}
