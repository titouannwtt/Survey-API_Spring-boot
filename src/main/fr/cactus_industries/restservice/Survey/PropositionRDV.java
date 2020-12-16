package fr.cactus_industries.restservice.survey;

import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.query.Vote;
import fr.cactus_industries.restservice.Database;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PropositionRDV {

    public static Proposition getPropositionById(int id) {
        String query = "select associatedSurvey, lieu, date from RDV where id="+id+";";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        //CONNEXION
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                List<Vote> votelist = Voting.getVotesOfProposition(id);
                Proposition proposition = new Proposition(id, rs.getInt("associatedSurvey"), rs.getString("lieu"), rs.getString("date"), votelist);
                return proposition;
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Proposition> getListOfPropositionsBySondageId(int associatedSurvey) {
        String query = "select id, associatedSurvey, lieu, date from RDV where associatedSurvey='"+associatedSurvey+"';";

        Connection con = Database.getDBConnection();
        List<Proposition> listOfPropositions = new ArrayList<>();
        int idProposition;
        if(con != null)
            //CONNEXION
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    idProposition = rs.getInt("id");
                    List<Vote> votelist = Voting.getVotesOfProposition(idProposition);
                    Proposition proposition = new Proposition(idProposition, rs.getInt("associatedSurvey"), rs.getString("lieu"), rs.getString("date"), votelist);
                    //System.out.println("Proposition ID : "+proposition.getId());
                    //System.out.println("Sondage ID of Proposition : "+proposition.getAssociatedId());
                    listOfPropositions.add(proposition);
                }
                rs.close();
                return listOfPropositions;
            }
            //EN CAS D'ERREUR
            catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
    
    public static int addProposition(int associatedSurvey, String date, String lieu){
        String query = "INSERT INTO RDV (associatedSurvey, date, lieu) VALUES ('"+associatedSurvey+"', '"+date+"', '"+lieu+"');";
        
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
    
    
    public static int removeProposition(int id) {
        String query = "DELETE FROM RDV WHERE id='"+id+"';";
        
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
