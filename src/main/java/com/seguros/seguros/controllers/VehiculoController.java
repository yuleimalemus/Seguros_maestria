package com.seguros.seguros.controllers;

import com.seguros.seguros.models.Clientes;
import com.seguros.seguros.models.Vehiculo;
import com.seguros.seguros.repositories.ClientesRepository;
import com.seguros.seguros.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehiculos")
@SessionAttributes(value = {"client"})
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping(value = "")
    public String vehiculos(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Vehiculos");
        model.addAttribute("vehiculos", this.vehiculoRepository.findAll());
        return "vehiculo/listVehiculo";
    }


    @GetMapping(value = "/form")
    public String vehiculosForm(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Crear Vehiculo");
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("clientes", this.clientesRepository.findAll());

        return "vehiculo/formVehiculo";
    }

    @PostMapping(value = "/create")
    public String saveVehiculo(@Valid Vehiculo vehiculo, BindingResult result, Model model,
                               RedirectAttributes flash, SessionStatus sessionStatus) {

        Optional<Vehiculo> checkVehiculo = this.vehiculoRepository.findByPlaca(vehiculo.getPlaca());

        if(checkVehiculo.isPresent()) {
            flash.addFlashAttribute("danger", "El vehiculo ya existe");
        }

        this.vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }



    @GetMapping(value = "/cliente/{cc}")
    @ResponseBody
    public List<Vehiculo> vehiculosByClienteCc(@PathVariable("cc") Integer cc) {
        return this.vehiculoRepository.findAllByClienteCC(cc);
    }
}
