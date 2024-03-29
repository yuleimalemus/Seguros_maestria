package com.seguros.seguros.controllers;

import com.seguros.seguros.models.Clientes;
import com.seguros.seguros.models.Corredor;
import com.seguros.seguros.repositories.ClientesRepository;
import com.seguros.seguros.repositories.CorredorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private CorredorRepository corredorRepository;

    @GetMapping(value = "/login")
    public String authForm(Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute("corredor", new Corredor());
        return "auth/login";
    }

    @PostMapping(value = "/auth/login")
    public String loginCorredor(@Valid Corredor corredor, BindingResult result, Model model,
                              RedirectAttributes flash, SessionStatus sessionStatus, HttpSession httpSession) {

        Optional<Corredor> userCorredor = this.corredorRepository
                .findByCcCorredorAndContraseña(corredor.getCcCorredor(), corredor.getContraseña());

        if(!userCorredor.isPresent()) {
            return "redirect:/login";
        }

        httpSession.setAttribute("currentUser", userCorredor.get());
        return "redirect:/clientes";
    }

    @GetMapping(value = "/salir")
    public String salir(@Valid Corredor corredor, BindingResult result, Model model,
                                RedirectAttributes flash, SessionStatus sessionStatus, HttpSession httpSession) {
        httpSession.removeAttribute("currentUser");
        return "redirect:/login";

    }


}

