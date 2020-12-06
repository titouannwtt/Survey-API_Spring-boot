package fr.cactus_industries.restservice.login;

import org.springframework.util.DigestUtils;

import java.util.Date;

public class LoggedTokenInfo {
    
    private static final long EXPIRATION_TIME = 300_000; // En ms
    private static final long EXPIRATION_HYSTERESIS = 30_000; // En ms
    
    private final String ID;
    private final String token;
    private final long expiration;
    
    private LoggedTokenInfo(String ID){
        long time = new Date().getTime();
        this.ID = ID;
        token = DigestUtils.md5DigestAsHex((time + ID).getBytes());
        expiration = time + EXPIRATION_TIME;
        MockToken.putToken(this);
    }
    
    // Outils pour générer les tokens pour un compte dont l'authentification a été validé précédemment.
    // Génère un nouveau token si inexistant, renvoit le token actif en cours ou renouvelle le token.
    public static LoggedTokenInfo getToken(String ID){
        LoggedTokenInfo tokenInfo = MockToken.getFromID(ID);
        if(tokenInfo != null){
            if(tokenInfo.expiration < new Date().getTime() + EXPIRATION_HYSTERESIS){
                tokenInfo = new LoggedTokenInfo(ID);
                MockToken.replaceToken(tokenInfo);
            }
        } else {
            tokenInfo = new LoggedTokenInfo(ID);
            MockToken.putToken(tokenInfo);
        }
        return tokenInfo;
    }
    
    public static LoggedTokenInfo renewToken(LoggedTokenInfo tokenInfo){
        LoggedTokenInfo newTokenInfo = new LoggedTokenInfo(tokenInfo.ID);
        MockToken.replaceToken(newTokenInfo);
        return newTokenInfo;
    }
    
    public boolean isValid(){
        return new Date().getTime() < expiration;
    }
    
    public String getID() {
        return ID;
    }
    
    public String getToken() {
        return token;
    }
    
    public long getExpiration() {
        return expiration;
    }
}
