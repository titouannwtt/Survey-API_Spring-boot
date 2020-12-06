package fr.cactus_industries.restservice.login;

public class LogIn extends LogResponse {
    
    private String token;
    private long expiration;
    
    public LogIn(LoggedTokenInfo tokenInfo){
        this.token = tokenInfo.getToken();
        this.expiration = tokenInfo.getExpiration();
        this.status = "OK";
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
