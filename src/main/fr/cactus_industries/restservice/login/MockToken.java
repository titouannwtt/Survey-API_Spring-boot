package fr.cactus_industries.restservice.login;

import java.util.Hashtable;

public class MockToken {
    
    private static Hashtable<String, LoggedTokenInfo> token_ID = new Hashtable<>(); // Token -> Infos
    private static Hashtable<String, LoggedTokenInfo> ID_token = new Hashtable<>(); // ID -> Infos
    
    public static boolean putToken(LoggedTokenInfo token){
        if(token_ID.containsKey(token.getToken()) || ID_token.containsKey(token.getID())) // Un token est déjà existant pour ce compte
            return false;
        
        token_ID.put(token.getToken(), token);
        ID_token.put(token.getID(), token);
        
        return true;
    }
    
    public static void replaceToken(LoggedTokenInfo token){
        token_ID.put(token.getToken(), token);
        ID_token.put(token.getID(), token);
    }
    
    // Renvoie LoggedTokenInfo si le token existe, dans le cas où le token existe mais n'est plus valide, le token est supprimé
    public static LoggedTokenInfo getFromToken(String token){
        LoggedTokenInfo tokenInfo = token_ID.get(token);
        if(tokenInfo == null)
            return null;
        if(tokenInfo.isValid())
            return tokenInfo;
        else {
            token_ID.remove(tokenInfo.getToken());
            ID_token.remove(tokenInfo.getID());
            return null;
        }
    }
    
    public static LoggedTokenInfo getFromID(String ID){
        return ID_token.get(ID);
    }
    
}
