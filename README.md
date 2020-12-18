# RESTProject
Projet d'API REST, 4 ème année  cycle ingénieur ESIEA

Titouan Wattelet

Clément Azan

# BASE URL : https://apiweb.cactus-industries.fr/ProgWeb/

------------------------------------------------------------

# ACCES BDD pour consultation :

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
 - Res: {"status"=str,"token"=str,"expiration"=long}
 - /Renew \<token\>
 - Res: {"status"=str,"token"=str,"expiration"=long}
 - /TestToken \<token\>
 - Res: {"status"=str,"token"=str,"expiration"=long}
 - /Register \<mail\> \<user\> \<pass\>
 - Res: {"status"=str}
 - /AccountInfo \<token\>
 - Res: {"status"=str,"mail"=str,"login"=str}
 - /AccountUpdate \<token\> \<pass\> \[mail\] \[user\] \[newPass\]
 - Res: {"status"=str}
 
==========================================

SONDAGE :

 - /sondage/create \<nom\> \<description\> \<token\> \<prive\>
 - Res: {"status"=str}
 - /sondage/delete \<id\> \<token\>
 - Res: {"status"=str}
 - /sondage/getPublicSurveyById \<id\>
 - Res: {"id"=int,"nom"=str,"description"=str,"authorId"=int,"sondagePrive"=int,"propositionList"=\[{"id"=int,"associatedId"=int,"lieu"=str,"date"=str,"votelist"=\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]},...]}
 - /sondage/getMySurveyById <id> \<token\>
 - Res: {"id"=int,"nom"=str,"description"=str,"authorId"=int,"sondagePrive"=int,"propositionList"=\[{"id"=int,"associatedId"=int,"lieu"=str,"date"=str,"votelist"=\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]},...]}
 - /sondage/listOfMySurveys \<token\>
 - Res: {"status"=str,"list"=\[{"id"=int,"nom"=str,"description"=str,"authorId"=int,"sondagePrive"=int,"propositionList"=\[{"id"=int,"associatedId"=int,"lieu"=str,"date"=str,"votelist"=\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]},...\]}
 - /sondage/getAllPublicSurveys
 - Res: {"status"=str,"list"=\[{"id"=int,"nom"=str,"description"=str,"authorId"=int,"sondagePrive"=int,"propositionList"=\[{"id"=int,"associatedId"=int,"lieu"=str,"date"=str,"votelist"=\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]},...\]}
 
===========================================

PROPOSITION DE VOTE:

 - /proposition/add \<associatedSurvey\> \<lieu\> \<date\> \<token\>
 - Res: {"status"=str}
 - /proposition/remove \<id\> \<token\>
 - Res: {"status"=str}
 - /proposition/listOfPropositionsForSondage \<associatedSurvey\>
 - Res: {"status"=str,"list"=\[{"id"=int,"associatedId"=int,"lieu"=str,"date"=str,"votelist"=\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]},...\]}
 
===========================================

VOTE:
 - /vote/add \<associatedProposition\> \<token\>
 - Res: {"status"=str}
 - /vote/remove \<id\> \<token\>
 - Res: {"status"=str}
 - /vote/listOfVotesForProposition \<associatedProposition\>
 - Res: {\[{"id"=int,"associatedProposition"=int,"associatedUser"=int},...\]}

# Les Erreurs

Générique:
 - ERROR: An error occurred (no more info)
 - TOKEN INVALID: Token has expired or doesn't exist
 - NO AUTHOR: Author not found

==========================================
 
Connexion/Compte:
 - ERROR: Login already exist
 - BAD LOGIN: Wrong User or Password
 - ACCOUNT NOT FOUND: This account hasn't been found
 - INVALID MAIL: The mail entered is not a valid mail
 - INVALID USER: The username entered doesn't match the requirements
 - INVALID PASS: The pass entered doesn't match the requirements
 - NO CHANGE: No change requested
 
==========================================

Sondage:
 - INVALID SONDAGE ID: ID of sondage not found
 - PERMISSION DENIED: You are not the author of this sondage, you can't modified it
 
==========================================

Proposition vote:
 - INVALID PROPOSITION ID: ID of proposition not found

==========================================

Vote:
 - INVALID VOTE ID: ID of vote not found
 - ALREADY VOTED: You can't vote because you have already voted for this sondage
 
