package fr.cactus_industries.restservice.Survey;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.restservice.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Survey {
    
    public static int getNumberOfSurvey() {
        int result = -1;
        //REQUETE
        String query = "SELECT COUNT(*) FROM SONDAGES";
        Connection con = Database.getDBConnection();
        
        if(con !=null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            //RECUPERATION DES DONNEES (ici : première ligne, contenant le nombre de sondages créés)
            if (rs.next()) {
                result= rs.getInt(1);
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            result=-2;
            e.printStackTrace();
        }
        //ON RETOURNE LE NOMBRE DE SONDAGES DANS LA BDD
        return result;
    }
    
    
    public static Sondage getSurveyById(int id) {
        String query = "select nom, description, authorId, sondagePrive from Users where id="+id+";";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                //RECUPERATION DES DONNEES
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int authorId = rs.getInt("authorId");
                int sondagePrive = rs.getInt("sondagePrive");
                
                //CREATION LOCAL DU SONDAGE
                Sondage sondageSuccess = new Sondage(id, nom, description, authorId, sondagePrive);
                return sondageSuccess;
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //ON RETOURNE LE NOMBRE DE SONDAGES DANS LA BDD
    }
    
    
    public static int createSurvey(String nom, String description, int authorId, int sondagePrive){
        int id = getNumberOfSurvey();
        //String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('1', 'SondageTest', 'CeciEstUnTest', '2', '0');";
        
        String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('"+id+"', '"+nom+"', '"+description+"', '"+authorId+"', '"+sondagePrive+"');";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    
    public static void deleteSondage(int id) throws SQLException {
        //String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('1', 'SondageTest', 'CeciEstUnTest', '2', '0');";
        
        String query = "DELETE FROM SONDAGES WHERE '"+id+"';";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
