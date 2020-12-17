package fr.cactus_industries;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("home","Bienvenue sur le super site de création de sondages !");
        return "home";
    }

}
