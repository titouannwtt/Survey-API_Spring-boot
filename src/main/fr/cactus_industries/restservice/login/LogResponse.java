package fr.cactus_industries.restservice.login;

public abstract class LogResponse {
    
    protected String status;
    
    
    public static LogResponse tryLogin(String user, String pass){
        // Try connection (Mock for now)
        if(user.equals("MockUser") && pass.equals("MockMotDePasse")){
            return new LogIn(LoggedTokenInfo.getToken(user));
        }
        
        return new LogFail();
        
    }
    
    public static LogResponse tryRenew(String token) {
        LoggedTokenInfo tokenInfo = MockToken.getFromToken(token);
        if(tokenInfo != null){
            return new LogIn(LoggedTokenInfo.renewToken(tokenInfo));
        }
        return new LogFail("Invalid token or expired.");
    }
    
    public abstract String getStatus();
}
