package fr.cactus_industries.restservice;

import java.util.List;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.restservice.survey.Survey;
import fr.cactus_industries.restservice.survey.PropositionRDV;
import fr.cactus_industries.restservice.login.LogIn;
import fr.cactus_industries.restservice.login.LoggedTokenInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropositionController {

    @GetMapping("/proposition/add")
    public Response add(@RequestParam(value="associatedSurvey", defaultValue = "") int associatedSurvey,
                          @RequestParam(value="lieu", defaultValue = "") String lieu,
                          @RequestParam(value="date", defaultValue = "") String date,
                          @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        Sondage sondage = Survey.getSurveyById(associatedSurvey);
        if(sondage==null) {
            return new FailResponse(FailResponse.Reason.INVALIDIDOFSONDAGE);
        }
        if (tokenInfo == null) {
            return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
        } else {
            int authorId = tokenInfo.getID();
            if (authorId==0) {
                return new FailResponse(FailResponse.Reason.NOAUTHOR);
            }
            else if(sondage.getAuthorId()!=authorId){
                return new FailResponse(FailResponse.Reason.YOUARENOTTHEAUTHOR);
            }
            else {
                int result = PropositionRDV.addProposition(sondage.getId(), date, lieu);
                if(result<0) {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
                else {
                    return new Response("OK");
                }
            }
        }
    }

    @GetMapping("/proposition/listOfPropositionsForSondage")
    public Response listOfPropositionsForSondage(@RequestParam(value="associatedSurvey", defaultValue = "") int associatedSurvey) {
        return new MultipleResponse<>(PropositionRDV.getListOfPropositionsBySondageId(associatedSurvey));
    }

    @GetMapping("/proposition/remove")
    public Response remove(@RequestParam(value="id", defaultValue = "") int id,
                          @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if (tokenInfo == null) {
            return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
        } else {
            int authorId = tokenInfo.getID();
            if (authorId==0) {
                return new FailResponse(FailResponse.Reason.NOAUTHOR);
            }
            else {
                if((PropositionRDV.removeProposition(id)==0)) {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
                else {
                    return new Response("OK");
                }
            }
        }
    }

}
