package fr.cactus_industries.restservice.login;

public class LogFailResponse extends LogResponse {
    
    private String reason;
    
    public LogFailResponse(){
        status = "FAILURE";
        reason = "Bad login";
    }
    
    public LogFailResponse(String reason){
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
