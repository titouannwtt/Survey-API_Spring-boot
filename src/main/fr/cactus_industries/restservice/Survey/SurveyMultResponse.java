package fr.cactus_industries.restservice.Survey;

import fr.cactus_industries.query.ListSondage;
import fr.cactus_industries.restservice.Response;

public class SurveyMultResponse extends Response {
    
    private ListSondage listSondage;
    
    public SurveyMultResponse(ListSondage listSondage){
        super("OK");
        this.listSondage = listSondage;
    }
    
    public ListSondage getListSondage() {
        return listSondage;
    }
}
