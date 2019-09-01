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
public class SalirController {

    @Autowired
    private CorredorRepository corredorRepository;

    @GetMapping(value = "/salir")
    public String authForm(Model model) {

        model.addAttribute("title", "Salir");
        model.addAttribute("corredor", null);
        return "auth/login";
    }

    @PostMapping(value = "/salir")
    public String loginCorredor(@Valid Corredor corredor, BindingResult result, Model model,
                                RedirectAttributes flash, SessionStatus sessionStatus, HttpSession httpSession) {

        httpSession.setAttribute("currentUser",null){

            return "redirect:/login";
        }

    }

}
