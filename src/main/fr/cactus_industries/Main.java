package fr.cactus_industries;

import fr.cactus_industries.restservice.RestServiceApplication;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Starting service...");
        
        // BDD Zone
        
        System.out.println("Deploying REST API...");
        RestServiceApplication.main(args);
        
    }
    
}
