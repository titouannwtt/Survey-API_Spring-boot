package fr.cactus_industries.restservice.login;

public abstract class LogResponse {
    
    protected String status;
    
    
    public static LogResponse tryLogin(String user, String pass){
        // Try connection
        LoggedTokenInfo tokenInfo = LogIn.login(user, pass);
        if(tokenInfo != null){ // If tokenInfo is null it means the account doesn't exist with this pass
            return new LogInResponse(tokenInfo);
        }
        
        return new LogFailResponse();
    }
    
    public static LogResponse tryRenew(String token) {
        LoggedTokenInfo tokenInfo = TokenHandler.getIDFromToken(token);
        if(tokenInfo != null){
            return new LogInResponse(LoggedTokenInfo.renewToken(tokenInfo));
        }
        return new LogFailResponse("Invalid token or expired.");
    }
    
    public static LogResponse testToken(String token){
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if(tokenInfo != null){
            return new LogInResponse(tokenInfo);
        }
        return new LogFailResponse("Wrong token or expired.");
    }
    
    public abstract String getStatus();
}
