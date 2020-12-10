package fr.cactus_industries.query;

import java.util.ArrayList;
import java.util.List;

public class ListSondage {
    
    private ArrayList<Sondage> sondages = new ArrayList<>();
    
    public ListSondage(List<Sondage> sondageList){
        sondages.addAll(sondageList);
    }
    
    public ListSondage(Sondage sondageList){
        sondages.add(sondageList);
    }
    
    public ListSondage(){}
    
    public void add(Sondage sondage){
        sondages.add(sondage);
    }
    
    public List<Sondage> getSondages(){
        return sondages;
    }
    
}
