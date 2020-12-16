# RESTProject
Projet d'API REST, 4 ème année  cycle ingénieur ESIEA

#Liste des requêtes API disponibles : 

==========================================

CONNEXION:

 - /LogIn \<user\> \<pass\>
 - /Renew \<token\>
 - /TestToken \<token\>
 
==========================================

SONDAGE :

 - /sondage/create \<nom\> \<description\> \<token\> \<prive\>
 - /sondage/delete \<id\> \<token\>
 - /sondage/getPublicSurveyById \<id\>
 - /sondage/getMySurveyById <id> \<token\>
 - /sondage/listOfMySurveys \<token\>
 - /sondage/getAllPublicSurveys
 
===========================================

PROPOSITION DE VOTE:

 - /proposition/add \<associatedSurvey\> \<lieu\> \<date\> \<token\>
 - /proposition/remove \<id\> \<token\>
 - /proposition/listOfPropositionsForSondage \<associatedSurvey\>
 
===========================================

VOTE:
 - /vote/add \<associatedProposition\> \<token\>
 - /vote/remove \<id\> \<token\>
 - /vote/listOfVotesForProposition \<associatedProposition\>
 
==========================================
