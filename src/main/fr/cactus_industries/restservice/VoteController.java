package fr.cactus_industries.restservice;

import java.util.List;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.query.Proposition;
import fr.cactus_industries.query.Vote;
import fr.cactus_industries.restservice.survey.Survey;
import fr.cactus_industries.restservice.survey.PropositionRDV;
import fr.cactus_industries.restservice.login.LogIn;
import fr.cactus_industries.restservice.login.LoggedTokenInfo;
import fr.cactus_industries.restservice.survey.Voting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    @GetMapping("/vote/add")
    public Response add(@RequestParam(value="associatedProposition", defaultValue = "") int associatedProposition,
                        @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        Proposition proposition = PropositionRDV.getPropositionById(associatedProposition);
        if(proposition==null) {
            return new FailResponse(FailResponse.Reason.INVALIDIDOFPROPOSITION);
        }
        if (tokenInfo == null) {
            return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
        } else {
            int associatedUser = tokenInfo.getID();
            if (associatedUser==0) {
                return new FailResponse(FailResponse.Reason.NOAUTHOR);
            }
            else if(!Survey.checkAlreadyVote(associatedProposition, associatedUser)){
                int result = Voting.addVote(associatedProposition, associatedUser);
                if(result==-1) {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
                else {
                    return new Response("OK");
                }
            }
            else {
                return new FailResponse(FailResponse.Reason.YOUHAVEALREADYVOTED);
            }
        }
    }

    @GetMapping("/vote/listOfVotesForProposition")
    public List<Vote> listOfVotesForProposition(@RequestParam(value="associatedProposition", defaultValue = "") int associatedProposition) {
        List<Vote> list = Voting.getVotesOfProposition(associatedProposition);
        return list;
    }

    @GetMapping("/vote/remove")
    public Response remove(@RequestParam(value="id", defaultValue = "") int id,
                        @RequestParam(value="token", defaultValue = "") String token) {
        LoggedTokenInfo tokenInfo = LogIn.login(token);
        Vote vote = Voting.getVoteById(id);
        if(vote==null) {
            return new FailResponse(FailResponse.Reason.INVALIDIDOFVOTE);
        }
        if (tokenInfo == null) {
            return new FailResponse(FailResponse.Reason.INVALIDTOKEN);
        } else {
            if(vote.getAssociatedUser() == tokenInfo.getID()) {
                return new FailResponse(FailResponse.Reason.NOAUTHOR);
            }
            else {
                int result = Voting.removeVote(vote.getId());
                if(result==-1) {
                    return new FailResponse(FailResponse.Reason.GENERIC);
                }
                else {
                    return new Response("OK");
                }
            }
        }
    }

}
