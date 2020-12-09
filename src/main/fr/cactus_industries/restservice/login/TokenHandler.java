package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TokenHandler {
    
    public static LoggedTokenInfo getIDFromToken(String token){
        
        String query = "SELECT id, expiration from TOKENS where token=\""+token+"\";";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        // CONNEXION
        try (Statement stmt = con.createStatement()){
            // RECUPERATION DES DONNEES
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return new LoggedTokenInfo(rs.getInt("id"), token, rs.getLong("expiration"));
            }
            
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // PAS DE TOKEN ASSOCIE
        return null;
    }
    
    
    public static LoggedTokenInfo getTokenFromID(int ID){
        
        String query = "SELECT token, expiration from TOKENS where id='"+ID+"';";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        // CONNEXION
        try (Statement stmt = con.createStatement()){
            // RECUPERATION DES DONNEES
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return new LoggedTokenInfo(ID, rs.getString("token"), rs.getLong("expiration"));
            }
            
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // PAS D'ID ASSOCIE
        return null;
    }
    
    public static boolean deleteToken(LoggedTokenInfo tokenInfo){
        
        String query = "DELETE FROM TOKENS WHERE token='"+tokenInfo.getToken()+"';";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        // CONNEXION
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) != 0) // DELETE TOKEN ROW
                return true; // SUCCESSFUL DELETION
            
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        
        return false;
    }
    
    
    public static boolean putTokenInDB(LoggedTokenInfo tokenInfo){
        
        String query = "INSERT INTO TOKENS (token, expiration, id) VALUES ('"
                +tokenInfo.getToken()+"', '"+ tokenInfo.getExpiration()+"', '"+tokenInfo.getID()+"');";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        // CONNEXION
        try (Statement stmt = con.createStatement()){
            // EXECUTION DE L'INSERT
            if(stmt.executeUpdate(query) != 0)
                return true;
            
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // RIEN N'A ETE INSERE
        return false;
    }
    
}
