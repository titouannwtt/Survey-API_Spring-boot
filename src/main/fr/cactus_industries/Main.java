package fr.cactus_industries;

import fr.cactus_industries.restservice.Database;
import fr.cactus_industries.restservice.RestServiceApplication;

import java.sql.Connection;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Starting service...");

        Connection con = Database.getDBConnection(); // Demande de la connexion BDD afin de l'initialiser
        if(con != null)
            System.out.println("Connection to the Database established.");
        else
            System.out.println("Connection to the Database could not be established for the moment.");
        
        System.out.println("Deploying REST API...");
        RestServiceApplication.main(args);
        
    }
    
}
