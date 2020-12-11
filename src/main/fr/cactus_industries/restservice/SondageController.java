package fr.cactus_industries.restservice;

import fr.cactus_industries.query.ListSondage;
import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.restservice.survey.Survey;
import fr.cactus_industries.restservice.login.LogIn;
import fr.cactus_industries.restservice.login.LoggedTokenInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SondageController {

    @GetMapping("/sondage/create")
    public Response create(@RequestParam(value="nom", defaultValue = "") String nom,
                          @RequestParam(value="description", defaultValue = "") String description,
                          @RequestParam(value="token", defaultValue = "") String token,
                          @RequestParam(value = "prive", defaultValue = "") int sondagePrive) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if (tokenInfo == null) {
            return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
        } else {
            int authorId = tokenInfo.getID();
            if (authorId==0) {
                return new FailResponse(FailResponse.Reason.NOAUTHOR);
            }
            else {
                if(sondagePrive==0 || sondagePrive==1) {
                    int idOfSondage = Survey.createSurvey(nom, description, authorId, sondagePrive);
                    if(idOfSondage<0) {
                        return new FailResponse(FailResponse.Reason.GENERIC);
                    }
                    else {

                        //On créé un sondage (pour le moment on ne s'en sert pas)
                        Sondage sondage = new Sondage(idOfSondage, nom, description, authorId, sondagePrive);
                        System.out.println(""+sondage);

                        return new Response("OK");
                    }
                }
                else {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
            }
        }
    }

    @GetMapping("/sondage/listOfMySurveys")
    public Response listOfMySurveys(@RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        ListSondage listSondage = Survey.getSurveysFromUser(tokenInfo.getID());
        if(listSondage != null)
            return new MultipleResponse<>(listSondage.getSondages());
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
    }

    @GetMapping("/sondage/getAllPublicSurveys")
    public Response getAllPublicSurveys(/*@RequestParam(value="token", defaultValue = "") String token*/) {
        //LoggedTokenInfo tokenInfo = LogIn.login(token);
        ListSondage listSondage = Survey.getAllPublicSurveys();
        if(listSondage != null)
            return new MultipleResponse<>(listSondage.getSondages());
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
    }

    @GetMapping("/sondage/getPublicSurveyById")
    public Sondage getPublicSurveyById(@RequestParam(value="id", defaultValue = "") int id) {
        Sondage sondage = Survey.getSurveyById(id);
        if(sondage.isSondagePrive()==1) {
            return null;
        }
        return sondage;
    }

    @GetMapping("/sondage/getSurveyById")
    public Sondage getSurveyById(@RequestParam(value="id", defaultValue = "") int id,
                                       @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        Sondage sondage = Survey.getSurveyById(id);
        if (tokenInfo == null) {
            return null;
        } else {
            if (sondage.getAuthorId() != tokenInfo.getID()) {
                return null;
            } else {
                return sondage;
            }
        }
    }

    @GetMapping("/sondage/delete")
    public Response delete(@RequestParam(value="id", defaultValue = "") int id,
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
                if((Survey.deleteSurvey(id, authorId)==0)) {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
                else {
                    return new Response("OK");
                }
            }
        }
    }

}
