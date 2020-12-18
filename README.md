# RESTProject
Projet d'API REST, 4 ème année  cycle ingénieur ESIEA

# BASE URL : https://apiweb.cactus-industries.fr/ProgWeb/

# Exemple d'utilisation : 
Inscription :
- https://apiweb.cactus-industries.fr/ProgWeb/Register?mail=etudiantTest@esiea.fr&user=etudiantEsiea&pass=Esiea2020

Connexion :
- https://apiweb.cactus-industries.fr/ProgWeb/LogIn?user=etudiantEsiea&pass=Esiea2020
On recupère le token que nous donne l'API



#Liste des requêtes API disponibles : 

==========================================

CONNEXION/COMPTE:

 - /LogIn \<user\> \<pass\>
 - /Renew \<token\>
 - /TestToken \<token\>
 - /Register \<mail\> \<user\> \<pass\>
 - /AccountInfo \<token\>
 
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
