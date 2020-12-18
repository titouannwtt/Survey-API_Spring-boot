package fr.cactus_industries.restservice;

import fr.cactus_industries.restservice.login.LogResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {
    
    @GetMapping("/LogIn")
    public Response LogIn(@RequestParam(value="user", defaultValue = "") String user,
                             @RequestParam(value = "pass", defaultValue = "") String pass){
        return LogResponse.tryLogin(user, pass);
    }
    
    @GetMapping("/AccountInfo")
    public Response AccountInfo(@RequestParam(value="token", defaultValue = "") String token){
        return LogResponse.getAccountInfo(token);
    }
    
    
    @GetMapping("/Renew")
    public Response Renew(@RequestParam(value = "token", defaultValue = "") String token){
        
        return LogResponse.tryRenew(token);
    }

    @GetMapping("/Register")
    public Response Register(@RequestParam(value = "mail", defaultValue = "") String mail,
                             @RequestParam(value = "user", defaultValue = "") String user,
                             @RequestParam(value = "pass", defaultValue = "") String pass){

        return LogResponse.register(mail, user, pass);
    }
    
    @GetMapping("/AccountUpdate")
    public Response AccountUpdate(@RequestParam(value = "token", defaultValue = "") String token,
                                  @RequestParam(value = "mail", defaultValue = "") String mail,
                                  @RequestParam(value = "user", defaultValue = "") String user,
                                  @RequestParam(value = "pass", defaultValue = "") String pass,
                                  @RequestParam(value = "newpass", defaultValue = "") String newPass){
        return LogResponse.changeAccountInfo(token, mail, user, pass, newPass);
    }
    
    @GetMapping("/TestToken")
    public Response TestToken(@RequestParam(value = "token", defaultValue = "") String token){
    
        return LogResponse.testToken(token);
    }
    
}
