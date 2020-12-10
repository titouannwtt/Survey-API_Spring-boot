package fr.cactus_industries.restservice.survey;

import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.query.Vote;
import fr.cactus_industries.restservice.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Voting {

    public static Vote getVoteById(int idVote) {
        String query = "select id, associatedProposition, associatedUser from VOTES where id="+idVote+";";

        Connection con = Database.getDBConnection();

        if(con != null) {
            //CONNEXION
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    //RECUPERATION DES DONNEES
                    int id = rs.getInt("id");
                    int associatedProposition = rs.getInt("associatedProposition");
                    int associatedUser = rs.getInt("associatedUser");

                    //CREATION LOCAL DU SONDAGE
                    Vote vote = new Vote(id, associatedProposition, associatedUser);
                    return vote;
                }
            }
            //EN CAS D'ERREUR
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Vote> getVotesOfProposition(int idOfProposition) {
        String query = "select id, associatedProposition, associatedUser from VOTES where associatedProposition='"+idOfProposition+"';";

        Connection con = Database.getDBConnection();
        List<Vote> listOfVotes = new ArrayList<>();
        if(con != null)
            //CONNEXION
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vote vote = new Vote(rs.getInt("id"), rs.getInt("associatedProposition"), rs.getInt("associatedUser"));
                    listOfVotes.add(vote);
                }
                rs.close();
                return listOfVotes;
            }
            //EN CAS D'ERREUR
            catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static int addVote(int associatedProposition, int associatedUser){
        String query = "INSERT INTO VOTES (associatedProposition, associatedUser) VALUES ('"+associatedProposition+"', '"+associatedUser+"');";

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


    public static int removeVote(int id) {
        String query = "DELETE FROM VOTES WHERE id='"+id+"';";

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
