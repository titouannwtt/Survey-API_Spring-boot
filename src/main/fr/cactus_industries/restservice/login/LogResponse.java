package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.FailResponse;
import fr.cactus_industries.restservice.Response;

public abstract class LogResponse {
    
    protected String status;
    
    
    public static Response tryLogin(String user, String pass){
        // Try connection
        LoggedTokenInfo tokenInfo = LogIn.login(user, pass);
        if(tokenInfo != null){ // If tokenInfo is null it means the account doesn't exist with this pass
            return new LogInResponse(tokenInfo);
        }
        
        return new FailResponse(FailResponse.Reason.BADLOGIN);
    }
    
    public static Response tryRenew(String token) {
        LoggedTokenInfo tokenInfo = TokenHandler.getIDFromToken(token);
        if(tokenInfo != null){
            return new LogInResponse(LoggedTokenInfo.renewToken(tokenInfo));
        }
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
    }
    
    public static Response testToken(String token){
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if(tokenInfo != null){
            return new LogInResponse(tokenInfo);
        }
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
    }
    
    public abstract String getStatus();
}
