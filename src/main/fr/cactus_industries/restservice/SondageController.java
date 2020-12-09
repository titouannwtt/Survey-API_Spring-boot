package fr.cactus_industries.restservice;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.restservice.Survey.Survey;
import fr.cactus_industries.restservice.login.LogIn;
import fr.cactus_industries.restservice.login.LoggedTokenInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.cactus_industries.query.Sondage;

@RestController
public class SondageController {

    @GetMapping("/sondage/create")
    public Request create(@RequestParam(value="nom", defaultValue = "") String nom,
                          @RequestParam(value="description", defaultValue = "") String description,
                          @RequestParam(value="token", defaultValue = "") String token,
                          @RequestParam(value = "prive", defaultValue = "") int sondagePrive) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if (tokenInfo == null) {
            Request request = new Request(1, "Token invalide");
            return request;
        } else {
            int authorId = tokenInfo.getID();
            if (authorId==0) {
                Request request = new Request(1, "Auteur introuvable");
                return request;
            }
            else {
                if(sondagePrive==0 || sondagePrive==1) {
                    Survey.createSurvey(nom, description, authorId, sondagePrive);
                    Request request = new Request(0, "Sondage "+nom+" créé avec succès.");
                    return request;
                }
                else {
                    Request request = new Request(1, "prive doit etre compris entre 0 et 1 pour definir si le sondage est prive ou non");
                    return request;
                }
            }
        }
    }

    /*
    @GetMapping("/sondage/delete")
    public void delete(@RequestParam(value="id", defaultValue = "") int id) {
        try {
            db.deleteSondage(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/
}
