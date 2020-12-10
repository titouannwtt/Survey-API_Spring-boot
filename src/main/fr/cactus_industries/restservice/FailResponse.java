package fr.cactus_industries.restservice;

public class FailResponse extends Response {

    public enum Reason {
        GENERIC("ERROR", "An error occurred (no more info)"),
        BADLOGIN("BAD LOGIN", "Wrong User or Password"),
        INVALIDTOKEN("TOKEN INVALID", "Token has expired or doesn't exist"),
        NOAUTHOR("NO AUTHOR", "Author not found");

        private String status;
        private String message;

        Reason(String status, String message){
            this.status = status;
            this.message = message;
        }

        public String getStatus(){
            return status;
        }

        public String getMessage(){
            return message;
        }
    }

    private String reason;

    public FailResponse(){
        super(Reason.GENERIC.getStatus());
        reason = Reason.GENERIC.getMessage();
    }

    public FailResponse(Reason reason){
        super(reason.getStatus());
        this.reason = reason.getMessage();
    }

    public String getReason() {
        return reason;
    }
}
