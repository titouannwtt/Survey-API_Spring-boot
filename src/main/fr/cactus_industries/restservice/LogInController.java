package fr.cactus_industries.restservice;

import fr.cactus_industries.restservice.login.LogResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {
    
    @GetMapping("/LogIn")
    public LogResponse LogIn(@RequestParam(value="user", defaultValue = "") String user,
                             @RequestParam(value = "pass", defaultValue = "") String pass){
        return LogResponse.tryLogin(user, pass);
    }
    
    @GetMapping("/Renew")
    public LogResponse Renew(@RequestParam(value = "token", defaultValue = "") String token){
        
        return LogResponse.tryRenew(token);
    }
    
}
