package fr.cactus_industries.restservice.login;

public class LogFail extends LogResponse {
    
    private String reason;
    
    public LogFail(){
        status = "FAILURE";
        reason = "Bad login";
    }
    
    public LogFail(String reason){
        status = "FAILURE";
        this.reason = reason;
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    public String getReason() {
        return reason;
    }
}
