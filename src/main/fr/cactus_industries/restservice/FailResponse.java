package fr.cactus_industries.restservice;

public class FailResponse extends Response {

    public enum Reason {
        GENERIC("ERROR", "An error occurred (no more info)"),
        DUPLICATELOGIN("ERROR", "Login already exist"),
        BADLOGIN("BAD LOGIN", "Wrong User or Password"),
        INVALIDMAIL("INVALID MAIL","The mail entered is not a valid mail"),
        INVALIDUSER("INVALID USER","The username entered doesn't match the requirements"),
        INVALIDPASS("INVALID PASS","The pass entered doesn't match the requirements"),
        INVALIDTOKEN("TOKEN INVALID", "Token has expired or doesn't exist"),
        INVALIDIDOFSONDAGE("INVALID SONDAGE ID", "ID of sondage not found"),
        INVALIDIDOFPROPOSITION("INVALID PROPOSITION ID", "ID of proposition not found"),
        INVALIDIDOFVOTE("INVALID VOTE ID", "ID of vote not found"),
        YOUARENOTTHEAUTHOR("PERMISSION DENIED", "You are not the author of this sondage, you can't modified it"),
        NOAUTHOR("NO AUTHOR", "Author not found"),
        YOUHAVEALREADYVOTED("ALREADY VOTED", "You can't vote because you have already voted for this sondage");

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
