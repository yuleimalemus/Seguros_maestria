package com.seguros.seguros.controllers;

import com.seguros.seguros.models.Clientes;
import com.seguros.seguros.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
@SessionAttributes(value = {"client"})
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping(value = "")
    public String clients(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Clientes");
        model.addAttribute("clientes", this.clientesRepository.findAll());
        return "client/listClients";
    }

    @GetMapping(value = "/form")
    public String clientesForm(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Crear Cliente");
        model.addAttribute("client", new Clientes());
        return "client/formClient";
    }

    @PostMapping(value = "/create")
    public String saveCliente(@Valid Clientes client, BindingResult result, Model model,
                              RedirectAttributes flash, SessionStatus sessionStatus, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        Optional<Clientes> checkCliente = this.clientesRepository.findByCc(client.getCc());

        if(checkCliente.isPresent()) {
            flash.addFlashAttribute("danger", "El cliente ya existe");
            return "redirect:/clientes/form";
        }

        System.out.println(client.toString());
        this.clientesRepository.save(client);
        return "redirect:/clientes";
    }
}

