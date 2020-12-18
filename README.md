# RESTProject
Projet d'API REST, 4 ème année  cycle ingénieur ESIEA

# BASE URL : https://apiweb.cactus-industries.fr/ProgWeb/

------------------------------------------------------------

#ACCES BDD pour consultation :

 - Page phpmyadmin : http://cerenity.net/phpmyadmin/sql.php?db=basededonnees
 - user : progweb
 - mdp : Esiea2020 

------------------------------------------------------------

# Exemple d'utilisation : 
Consultation des sondages existants :
- https://apiweb.cactus-industries.fr/ProgWeb/sondage/getAllPublicSurveys

Inscription :
- https://apiweb.cactus-industries.fr/ProgWeb/Register?mail=etudiantTest@esiea.fr&user=etudiantEsiea&pass=Esiea2020

Connexion :
- https://apiweb.cactus-industries.fr/ProgWeb/LogIn?user=etudiantEsiea&pass=Esiea2020
-> On recupère le token que nous renvoie l'API

On créé un sondage :
- https://apiweb.cactus-industries.fr/ProgWeb/sondage/create?nom=SondageEsiea&description=SondagePourLesEtudiantsDeLEsiea&prive=0&token=MON-TOKEN-OBTENU-LORS-DU-LOGIN

On consulte la liste de nos sondages :
- https://apiweb.cactus-industries.fr/ProgWeb/sondage/listOfMySurveys?token=MON-TOKEN-OBTENU-LORS-DU-LOGIN
-> On recupère l'ID de notre sondage

On ajoute des éléments de réponse à notre sondage (pour que les utilisateurs puissent voter) : 
- https://apiweb.cactus-industries.fr/ProgWeb/proposition/add?associatedSurvey=ID-DE-MON-SONDAGE&lieu=Paris&date=8Mars2021&token=MON-TOKEN-OBTENU-LORS-DU-LOGIN
- https://apiweb.cactus-industries.fr/ProgWeb/proposition/add?associatedSurvey=ID-DE-MON-SONDAGE&lieu=Lyon&date=2Aout2021&token=MON-TOKEN-OBTENU-LORS-DU-LOGIN
- https://apiweb.cactus-industries.fr/ProgWeb/proposition/add?associatedSurvey=ID-DE-MON-SONDAGE&lieu=Grenoble&date=29Mai2021&token=MON-TOKEN-OBTENU-LORS-DU-LOGIN

On consulte la liste des propositions de réponses de notre sondage : 
- https://apiweb.cactus-industries.fr/ProgWeb/proposition/listOfPropositionsForSondage?associatedSurvey=ID-DE-MON-SONDAGE
-> On note un ID de proposition de réponse pour lequel on veut voter

On vote pour une proposition de réponse dans un sondage :
- https://apiweb.cactus-industries.fr/ProgWeb/vote/add?associatedProposition=ID-DE-MA-PROPOSITION&token=MON-TOKEN-OBTENU-LORS-DU-LOGIN

On retourne consulter la liste des sondages existants, on constate que notre sondage, ainsi que nos propositions de réponses et notre vote ont bien été ajoutés :
- https://apiweb.cactus-industries.fr/ProgWeb/sondage/getAllPublicSurveys

Une page html a été mise en place pour afficher la liste des sondages :
https://apiweb.cactus-industries.fr/test.html

---------------------------------------------------

# Liste des requêtes API disponibles : 

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

