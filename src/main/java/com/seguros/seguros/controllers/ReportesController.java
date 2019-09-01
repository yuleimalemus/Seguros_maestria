package com.seguros.seguros.controllers;

import com.seguros.seguros.models.*;
import com.seguros.seguros.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

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

    // REPORTES

    @GetMapping(value = "/vehiculos_asegurados_corredor")
    public String vehiculosAseguradosCorredor(Model model, HttpSession httpSession) {

        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        Corredor corredor = (Corredor)httpSession.getAttribute("currentUser");

        Iterable<Poliza> polizas = this.polizaRepository.findDistinctByCodigoCorredor(corredor.getCodCorredor());
        System.out.println(polizas.toString());
        List<String> placas = new ArrayList<>();

        polizas.forEach(poliza -> {
            placas.add(poliza.getPlaca());
        });

        // vehiculos asegurados del corredor actual
        List<Vehiculo> vehiculosAsegurados = this.vehiculoRepository.findAllByPlacaIn(placas);

        model.addAttribute("title", "Vehiculos Asegurados del Corredor");
        model.addAttribute("vehiculos", vehiculosAsegurados);
        return "vehiculo/listVehiculo";
    }

    @GetMapping(value = "/vehiculos_cliente_form")
    public String vehiculosClienteForm(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        Iterable<Clientes> clientes = this.clientesRepository.findAll();
        model.addAttribute("title", "Vehiculos Por Cliente");
        model.addAttribute("clientes", clientes);
        return "reportes/vehiculosCliente";
    }

    @GetMapping(value = "/comisiones_corredor")
    public String comisionesCorredor(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        Corredor corredor = (Corredor)httpSession.getAttribute("currentUser");
        Iterable<Poliza> polizas = this.polizaRepository.findDistinctByCodigoCorredor(corredor.getCodCorredor());
        HashMap<String, Float> comisionesMap = new HashMap<>();
        List<String> placas = new ArrayList<>();

        polizas.forEach(poliza -> {
            if(comisionesMap.containsKey(poliza.getPlaca())) {
                comisionesMap.replace(poliza.getPlaca(), comisionesMap.get(poliza.getPlaca()) + poliza.getValor_pagar());
            } else {
                comisionesMap.put(poliza.getPlaca(), poliza.getValor_pagar());
            }
            placas.add(poliza.getPlaca());
        });

        List<VehiculoPoliza> vehiculosFinal = new ArrayList<>();
        List<Vehiculo> vehiculos = this.vehiculoRepository.findAllByPlacaIn(placas);
        Float comisionTotal = new Float(0);
        for (Vehiculo vehiculo : vehiculos) {
            Float total = comisionesMap.get(vehiculo.getPlaca());
            VehiculoPoliza vp = new VehiculoPoliza();
            vp.setVehiculo(vehiculo);
            vp.setTotal(total);
            comisionTotal += (total * 0.15f);
            vehiculosFinal.add(vp);
        }
        System.out.println(vehiculosFinal.toString());
        model.addAttribute("title", "Comisiones del corredor");
        model.addAttribute("vehiculos", vehiculosFinal);
        model.addAttribute("comisionTotal", comisionTotal);
        return "reportes/comisionesCorredor";
    }

    @GetMapping(value = "/vehiculos_cliente/{cc}")
    @ResponseBody
    public Iterable<VehiculoPoliza> vehiculosCliente(Model model, HttpSession httpSession, @PathVariable("cc") Integer cc) {
        return this.getVehiculosClienteMonto(cc);
    }


    private Iterable<VehiculoPoliza> getVehiculosClienteMonto(Integer cc) {
        Iterable<Poliza> polizas = this.polizaRepository.findAllByCcCliente(cc);
        System.out.println(polizas.toString());

        HashMap<String, List<Poliza>> polizasMap = new HashMap<>();

        polizas.forEach(poliza -> {
            if(polizasMap.containsKey(poliza.getPlaca())) {
                List<Poliza> valores = polizasMap.get(poliza.getPlaca());
                valores.add(poliza);
                polizasMap.replace(poliza.getPlaca(), valores);

            } else {
                List<Poliza> valores = new ArrayList<>();
                valores.add(poliza);
                polizasMap.put(poliza.getPlaca(), valores);
            }
        });

        System.out.println(polizasMap.toString());

        List<VehiculoPoliza> vehiculosFinal = new ArrayList<>();
        Iterable<Vehiculo> vehiculosCLiente = this.vehiculoRepository.findAllByClienteCC(cc);
        vehiculosCLiente.forEach(vehiculo -> {
            if(polizasMap.containsKey(vehiculo.getPlaca())) {
                vehiculosFinal.add(new VehiculoPoliza(vehiculo, polizasMap.get(vehiculo.getPlaca())));
            }

        });

        return vehiculosFinal;
    }
}






class VehiculoPoliza {
    private Vehiculo vehiculo;
    private Float soat;
    private Float todoRiesgo;
    private Float total;

    public VehiculoPoliza(){}
    public VehiculoPoliza(Vehiculo vehiculo, List<Poliza> polizas) {
        this.vehiculo = vehiculo;

        polizas.forEach(poliza -> {
            if(poliza.getTipo().equals("1")) {
                this.soat = poliza.getValor_pagar();
            } else {
                this.todoRiesgo = poliza.getValor_pagar();
            }
        });
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Float getSoat() {
        return soat;
    }

    public void setSoat(Float soat) {
        this.soat = soat;
    }

    public Float getTodoRiesgo() {
        return todoRiesgo;
    }

    public void setTodoRiesgo(Float todoRiesgo) {
        this.todoRiesgo = todoRiesgo;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VehiculoPoliza{" +
                "vehiculo=" + vehiculo.toString() +
                ", soat=" + soat +
                ", todoRiesgo=" + todoRiesgo +
                ", total=" + total +
                '}';
    }
}

