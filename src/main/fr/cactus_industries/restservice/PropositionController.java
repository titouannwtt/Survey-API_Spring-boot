package fr.cactus_industries.restservice;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.restservice.Survey.Survey;
import fr.cactus_industries.restservice.Survey.PropositionRDV;
import fr.cactus_industries.restservice.login.LogIn;
import fr.cactus_industries.restservice.login.LoggedTokenInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropositionController {

    @GetMapping("/proposition/add")
    public Request add(@RequestParam(value="associatedSurvey", defaultValue = "") int associatedSurvey,
                          @RequestParam(value="lieu", defaultValue = "") String lieu,
                          @RequestParam(value="date", defaultValue = "") String date,
                          @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        Sondage sondage = Survey.getSurveyById(associatedSurvey);
        if(sondage==null) {
            Request request = new Request(1, "Aucun sondage ne possede l'ID "+associatedSurvey+".");
            return request;
        }
        if (tokenInfo == null) {
            Request request = new Request(1, "Token invalide");
            return request;
        } else {
            int authorId = tokenInfo.getID();
            if (authorId==0) {
                Request request = new Request(1, "Auteur introuvable");
                return request;
            }
            else if(sondage.getAuthorId()!=authorId){
                Request request = new Request(1, "Vous n'etes pas l'auteur du sondage "+sondage.getNom()+". Vous n'etes pas autorise a ajouter des propositions");
                return request;
            }
            else {
                int result = PropositionRDV.addProposition(sondage.getId(), date, lieu);
                Request request;
                if(result<0) {
                    request = new Request(1, "Erreur lors de l'appel à la base de donnees");
                }
                else {
                    request = new Request(0, "Proposition : " + lieu + " a " + date + " creee avec succes.");
                }
                return request;
            }
        }
    }

    @GetMapping("/proposition/listOfPropositionsForSondage")
    public List<Proposition> listOfPropositionsForSondage(@RequestParam(value="associatedSurvey", defaultValue = "") int associatedSurvey) {
        List<Proposition> list = PropositionRDV.getListOfPropositionsBySondageId(associatedSurvey);
        return list;
    }

    @GetMapping("/proposition/remove")
    public Request remove(@RequestParam(value="id", defaultValue = "") int id,
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
