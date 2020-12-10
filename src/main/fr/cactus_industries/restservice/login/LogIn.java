package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Handle Login and Register
public class LogIn {

    public static LoggedTokenInfo login(String User, String pass){
        
        String query = "SELECT id FROM USERS where login='"+User+"' AND pass='"+pass+"';";
        
        Connection con = Database.getDBConnection();
        
        if(con != null)
        // CONNEXION
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return LoggedTokenInfo.getToken(rs.getInt("id"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        
        return null;
    }
    
    // Renvoie les infos associés à un token si il existe et s'il est encore valide, sinon renvoie null
    public static LoggedTokenInfo login(String token){
        LoggedTokenInfo tokenInfo = TokenHandler.getIDFromToken(token);
        
        if(tokenInfo != null)
            if(tokenInfo.isValid())
                return tokenInfo;
            else
                TokenHandler.deleteToken(tokenInfo);
        
        return null;
    }

}
