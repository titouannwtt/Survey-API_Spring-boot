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
                    int idOfSondage = Survey.createSurvey(nom, description, authorId, sondagePrive);
                    if(idOfSondage<0) {
                        Request request = new Request(1, "Erreur lors de l'appel à la base de donnees");
                        return request;
                    }
                    else {

                        //On créé un sondage (pour le moment on ne s'en sert pas)
                        Sondage sondage = new Sondage(idOfSondage, nom, description, authorId, sondagePrive);
                        System.out.println(""+sondage);

                        Request request = new Request(0, "Sondage " + nom + " créé avec succès.");
                        return request;
                    }
                }
                else {
                    Request request = new Request(1, "prive doit etre compris entre 0 et 1 pour definir si le sondage est prive ou non");
                    return request;
                }
            }
        }
    }

    /*@GetMapping("/sondage/listOfMySurveys")
    public Survey[] create(@RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        //Survey[] surveys = new Survey()[];
        /*if (tokenInfo == null) {
            Request request = new Request(1, "Token invalide");
            return request;
        } else {*/
            //int authorId = tokenInfo.getID();
           /* if (authorId==0) {
                Request request = new Request(1, "Auteur introuvable");
                return request;
            }
            else {*/
                //if((Survey.listOfMySurveys(authorId)==0)) {







               /* }
                else {
                    Request request = new Request(0, "Sondage supprimé avec succès.");
                    return request;
                }*/
           /* }
        }*/
    //}

    @GetMapping("/sondage/delete")
    public Request delete(@RequestParam(value="id", defaultValue = "") int id,
                          @RequestParam(value="token", defaultValue = "") String token) {
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
                if((Survey.deleteSurvey(id, authorId)==0)) {
                    Request request = new Request(1, "Erreur lors de l'appel à la base de donnees");
                    return request;
                }
                else {
                    Request request = new Request(0, "Sondage supprimé avec succès.");
                    return request;
                }
            }
        }
    }

}
