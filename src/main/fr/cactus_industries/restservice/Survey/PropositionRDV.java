package fr.cactus_industries.restservice.Survey;

import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.restservice.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropositionRDV {

    public static Proposition getPropositionById(int id) {
        String query = "select associatedSurvey, lieu, date from RDV where id="+id+";";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                //RECUPERATION DES DONNEES
                int associatedSurvey = rs.getInt("associatedSurvey");
                String lieu = rs.getString("lieu");
                String date = rs.getString("date");
                
                //CREATION LOCAL DU SONDAGE
                Proposition proposition = new Proposition(id, associatedSurvey, lieu, date);
                return proposition;
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static int createProposition(int associatedSurvey, String date, String lieu){
        String query = "INSERT INTO RDV (associatedSurvey, date, lieu) VALUES ('"+associatedSurvey+"', '"+date+"', '"+lieu+"'), Statement.RETURN_GENERATED_KEYS;";
        
        Connection con = Database.getDBConnection();
        
        if(con != null) {
            //CONNEXION
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(query);
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                long id = rs.getLong(1);
                return (int) id;
            }
            //EN CAS D'ERREUR
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        else {
            return -1;
        }
    }
    
    
    public static int deleteSurvey(int id, int authorId) {
        //String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('1', 'SondageTest', 'CeciEstUnTest', '2', '0');";
        
        String query = "DELETE FROM SONDAGES WHERE id='"+id+"' AND authorId='"+authorId+"';";
        
        Connection con = Database.getDBConnection();

        if(con != null) {
            //CONNEXION
            try (Statement stmt = con.createStatement()) {
                if((stmt.executeUpdate(query))==0) {
                    return 0;
                } else {
                    return -1;
                }
            }
            //EN CAS D'ERREUR
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        else {
            return -1;
        }
    }

}
