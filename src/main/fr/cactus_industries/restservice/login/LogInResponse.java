package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.Response;

public class LogInResponse extends Response {
    
    private String token;
    private long expiration;
    
    public LogInResponse(LoggedTokenInfo tokenInfo){
        super("OK");
        this.token = tokenInfo.getToken();
        this.expiration = tokenInfo.getExpiration();
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    public String getToken() {
        return token;
    }
    
    public long getExpiration() {
        return expiration;
    }
}
