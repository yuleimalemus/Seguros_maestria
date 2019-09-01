package com.seguros.seguros.controllers;

import com.seguros.seguros.models.*;
import com.seguros.seguros.repositories.*;
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
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/polizas")
@SessionAttributes(value = {"poliza"})
public class PolizaController {

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private TipoPolizaRepository tipoPolizaRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private PartesRepository partesRepository;

    @GetMapping(value = "")
    public String polizas(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Polizas");
        model.addAttribute("polizas", this.polizaRepository.findAll());
        //TODO: crear templates de polizas
        return "poliza/listPolizas";
    }

    @GetMapping(value = "/form")
    public String polizasForm(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Crear Poliza");

        Parte radio = new Parte();
        radio.setCodigoParte("1");
        radio.setValor(0);
        radio.setDescripcion("Radio");

        Parte aire = new Parte();
        aire.setCodigoParte("2");
        aire.setValor(0);
        aire.setDescripcion("Aire Acondicionado");

        Parte rines = new Parte();
        rines.setCodigoParte("3");
        rines.setValor(0);
        rines.setDescripcion("Rines");

        Poliza poliza = new Poliza();
        List<Parte> partes = new ArrayList<Parte>();
        partes.add(radio);
        partes.add(aire);
        partes.add(rines);

        poliza.setPartes(partes);

        Corredor corredor = (Corredor)(httpSession.getAttribute("currentUser"));
        poliza.setCodigoCorredor(corredor.getCodCorredor());
        model.addAttribute("tipos_poliza", this.tipoPolizaRepository.findAllByClase("1"));
        model.addAttribute("clientes", this.clientesRepository.findAll());
        model.addAttribute("poliza", poliza);
        return "poliza/formPoliza";
    }

    @PostMapping(value = "/create")
    public String savePoliza(@Valid Poliza poliza, BindingResult result, Model model,
                              RedirectAttributes flash, SessionStatus sessionStatus, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        Optional<Poliza> checkPoliza = this.polizaRepository.findByNumeroPoliza(poliza.getNumeroPoliza());
        if(checkPoliza.isPresent()) {
            flash.addFlashAttribute("danger", "Una poliza con el numero: " + poliza.getNumeroPoliza() + " ya existe");
            return "redirect:/polizas/form";
        }

        Optional<Poliza> checkPolizaTipo = this.polizaRepository.findByPlacaAndTipo(poliza.getPlaca(), poliza.getTipo());
        if(checkPolizaTipo.isPresent()) {
            String polizaTipo = poliza.getTipo().equals("1") ? "SOAT" : "Todo Riesgo";
            flash.addFlashAttribute("danger", "Una poliza con la placa: " + poliza.getPlaca() + " y de tipo: " + polizaTipo + " ya existe");
            return "redirect:/polizas/form";
        }

        System.out.println(poliza.toString());

        Float valorFinal = new Float(0.0);
        List<ParteAsegurada> partesAseguradas = new ArrayList<>();
        Vehiculo vehiculo = this.vehiculoRepository.findByPlaca(poliza.getPlaca()).get();
        if (poliza.getTipo().equals("1")) {
            valorFinal = (float) (vehiculo.getVr_comercial() * 0.01);
        } else {
            Integer sumaPartes = new Integer(0);
            for(Parte parte: poliza.getPartes()) {
                ParteAsegurada nuevaParte = new ParteAsegurada();
                nuevaParte.setCodParte(parte.getCodigoParte());
                nuevaParte.setNumeroPoliza(poliza.getNumeroPoliza());
                nuevaParte.setPlaca(poliza.getPlaca());
                nuevaParte.setValorAsegurado(parte.getValor());

                partesAseguradas.add(nuevaParte);

                sumaPartes += parte.getValor();
            }

            Clientes cliente = this.clientesRepository.findByCc(new Long(poliza.getCcCliente())).get();
            float porcentaje = 0.0f;
            if(cliente.getEstadoCivil().equals(2)) {
                System.out.println("Estado civil: " + cliente.getEstadoCivil());
                porcentaje = 0.07f;

            } else if(cliente.getEdad() > 60) {
                porcentaje = 0.04f;

            } else if(cliente.getEdad() > 45 && cliente.getEdad() < 60) {
                porcentaje = 0.06f;
            }
            else{
                porcentaje =0.05f;
            }
            valorFinal = (float)(vehiculo.getVr_comercial() * porcentaje);
            valorFinal = (float)(valorFinal + sumaPartes * 0.10);
        }

        // valor final
        poliza.setValor_pagar((float)valorFinal);
        System.out.println(valorFinal.toString());

        // guardando poliza
        this.polizaRepository.save(poliza);

        if(partesAseguradas.size() > 0) {
            System.out.println("Numero de partes: " + partesAseguradas.size());
            this.partesRepository.saveAll(partesAseguradas);
        }

        System.out.println(poliza);
        return "redirect:/polizas";
    }
}

