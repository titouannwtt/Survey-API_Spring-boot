package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.FailResponse;
import fr.cactus_industries.restservice.Response;

public class LogResponse {
    
    public static Response tryLogin(String user, String pass){
        // Try connection
        LoggedTokenInfo tokenInfo = LogIn.login(user, pass);
        if(tokenInfo != null){ // If tokenInfo is null it means the account doesn't exist with this pass
            return new LogInResponse(tokenInfo);
        }
        
        return new FailResponse(FailResponse.Reason.BADLOGIN);
    }

    public static Response getAccountInfo(String token){
        LoggedTokenInfo tokenInfo = TokenHandler.getIDFromToken(token);
        if(tokenInfo != null){
            return LogIn.getAccountInfo(tokenInfo.getID());
        }
        
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
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

    public static Response register(String mail, String user, String pass) {
        System.out.println("mail: "+mail+"\nuser: "+user+"\npass: "+pass);
        return LogIn.register(mail, user, pass);
    }
    
    public static Response changeAccountInfo(String token, String mail, String user, String pass, String newPass) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        if(tokenInfo != null){
            if(mail.equals("") && user.equals("") && newPass.equals(""))
                return new FailResponse(FailResponse.Reason.NOCHANGE);
            return LogIn.changeAccountInfo(tokenInfo.getID(), pass, mail, user, newPass);
        }
        
        return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
    }
}
