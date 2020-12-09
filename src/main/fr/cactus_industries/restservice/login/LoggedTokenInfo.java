package fr.cactus_industries.restservice.login;

import org.springframework.util.DigestUtils;

import java.util.Date;

public class LoggedTokenInfo {
    
    private static final long EXPIRATION_TIME = 300_000; // En ms
    private static final long EXPIRATION_HYSTERESIS = 30_000; // En ms
    
    private final int ID;
    private final String token;
    private final long expiration;
    
    public LoggedTokenInfo(int ID, String token, long expiration){
        this.ID = ID;
        this.token = token;
        this.expiration = expiration;
    }
    
    // Génération d'un nouveau token et ajout dans la DB
    private LoggedTokenInfo(int ID){
        long time = new Date().getTime();
        this.ID = ID;
        token = DigestUtils.md5DigestAsHex((""+time + ID).getBytes());
        expiration = time + EXPIRATION_TIME;
        TokenHandler.putTokenInDB(this);
    }
    
    // Outils pour générer les tokens pour un compte dont l'authentification a été validé précédemment.
    // Génère un nouveau token si inexistant, renvoit le token actif en cours ou renouvelle le token.
    public static LoggedTokenInfo getToken(int ID){
        LoggedTokenInfo tokenInfo = TokenHandler.getTokenFromID(ID);
        if(tokenInfo != null){
            if(tokenInfo.expiration < new Date().getTime() + EXPIRATION_HYSTERESIS){
                if(TokenHandler.deleteToken(tokenInfo)){
                    System.out.println("Old Token has not been deleted ("+tokenInfo.getToken()+")");
                }
                tokenInfo = new LoggedTokenInfo(ID);
            }
        } else {
            tokenInfo = new LoggedTokenInfo(ID);
        }
        return tokenInfo;
    }
    
    public static LoggedTokenInfo renewToken(LoggedTokenInfo tokenInfo){
        if(TokenHandler.deleteToken(tokenInfo)){
            System.out.println("Old Token has not been deleted ("+tokenInfo.getToken()+")");
        }
        LoggedTokenInfo newTokenInfo = new LoggedTokenInfo(tokenInfo.ID);
        return newTokenInfo;
    }
    
    public boolean isValid(){
        return new Date().getTime() < expiration;
    }
    
    public int getID() {
        return ID;
    }
    
    public String getToken() {
        return token;
    }
    
    public long getExpiration() {
        return expiration;
    }
}
