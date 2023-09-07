# Application Coach
Application Android écrite en java, sous Android Studio version Flamengo (avec l'API 16 configurée).
## Fonctionnalités et construction étape par étape
Le but de cette application est de calculer l’IMG (Indice Masse Grasse) d’une personne, en fonction de son poids, sa taille, son âge et son sexe.<br>
Au final, l'application démarre sur un menu permettant soit d'aller à la fenêtre de calcul dz l'IMG, soit d'aller à la fenêtre d'affichage de l'historique des IMG précédemment calculés.<br>
Les différents commits montrent la création de l'application étape par étape.
### Commit "S1 : construction de la première interface graphique"
La fenêtre du calcul de l'IMG est construite mais non fonctionnelle.
![img1](https://github.com/CNED-SLAM/Coach/assets/100127886/e1f8c8c9-c95b-4409-aaae-006e9b02f0eb)
### Commit "S2 : MVC, test et code de la première interface graphique"
L'application est structurée en MVC et le code de la fenêtre du calcul de l'IMG est ajouté.<br>
Le calcul est opérationnel et une classe de test est écrite pour le vérifier à travers un test unitaire.
### Commit "S3_1 : sauvegarde par sérialisation"
Les informations saisies (poids, taille, âge, sexe) sont mémorisées dans un objet du type Profil (classe métier) qui est aussi sérialisé afin de récupérer son contenu lors d'un prochain lancement de l'application.
### Commit "S3_2 : sauvegarde dans SQLite"
Une autre méthode d'enregistrement de l'information est utilisée, avec la création d'une BDD locale (intégrée dans le mobile) au format SQLite.
### Commit "S4 : sauvegarde dans la BDD distante MySQL"
Une dernière méthode d'enregistrement de l'information est utilisée, avec la création d'une BDD distante au format MySQL, manipulée par une page PHP, elle-même sollicitée par l'application mobile.
### Commit "S5 : menu et liste 'adapter' interactive"
Les différents calculs d'IMG (profils) étant mémorisés dans la BDD, avec leur date de calcul, ils sont récupérés dès l'ouverture de l'application et peuvent être affichés dans une nouvelle fenêtre contenant l'historique des profils :<br>
![img2](https://github.com/CNED-SLAM/Coach/assets/100127886/9beda982-cc1f-4d61-9e4f-d37d3c689ab1)
<br>Dans cette fenêtre, il est possible de supprimer un profil mémorisé (en cliquant sur la croix) ou de réafficher le détail d'un profil (en cliquant sur le reste de la ligne) ce qui a pour conséquence de retourner sur la fenêtre du calcul, mais avec les informations préremplies.<br>
L'application démarre maintenant sur une fenêtre "menu" présentant 2 choix : aller à la fenêtre du calcul ou à la fenêtre de l'historique.<br>
![img3](https://github.com/CNED-SLAM/Coach/assets/100127886/18790e35-093d-40ff-94fe-0fde7edf86fb)
### Commit "Version exploitant une API REST" sur la branche "apirest"
Cette version de l'application repart de la version précédente, mais cette fois l'accès à la BDD se fait à travers une API REST. Une nouvelle classe a été créée et la classe AccesDistant légèrement modifiée.
### Commit "Correction dans Manifest pour que l'application marche sur la version Giraffe" sur la branche "apirest"
Effectivement la ligne suivante a été ajoutée dans AndroidManifest pour que l'accès à l'API fonctionne sur la version Giraffe d'Android Studio (version supérieure à Flamengo, initialement utilisée pour construire cette application) :
<pre><code>android:usesCleartextTraffic="true"</code></pre>
### Commit "Ajout du zip contenant le code de l'api rest" sur la branche "apirest"
Le fichier contient le code de l'api, écrit en PHP.
### Commit "Ajout du zip contenant le code côté serveur" sur master
Le fichier contient le dossier "coach" avec les pages PHP et le script SQL pour créer la BDD au format MySQL.
## Installation
Il est possible de tester l'application étape par étape (commit par commit) dans le cadre de la création d'un TP, ou de tester directement la version finale, sachant qu'il y a 2 versions : avec ou sans l'utilisation de l'API REST.<br>
Dans les 2 cas, les outils suivants doivent être installés :<br>
. Android Studio (si la version est plus récente que Giraffe, il est possible qu'il y ait des surprises).<br>
. Un serveur local Apache/MySQL/PHP (par exemple wamp).
### Version sans l'API REST
Récupérez le zip du dernier commit de la branche principale (master).<br>
Dézippez le fichier et ouvrez l'application avec Android Studio.<br>
Dézippez le fichier "coach.zip" et copiez le dossier "coach" dans le dossier "www" (ou équivalent) de votre serveur local Apache.<br>
Dans ce dossier "coach", récupérez "coach.sql" et utilisez le script pour créer la BDD coach dans MySQL.<br>
Dans l'application Android, classe AccesDistant, mettez votre adresse IP locale dans la constante SERVERADDR.<br>
Testez l'application (émulateur utilisé : Pixel 3e API 30).
### Version avec l'API REST
Récupérez le zip du dernier commit de la branche "apirest".<br>
Dézippez le fichier et ouvrez l'application avec Android Studio.<br>
Dézippez le fichier "rest_coach.zip" et copiez le dossier "rest_coach" dans le dossier "www" (ou équivalent) de votre serveur local Apache.<br>
Dans la branche principale, dernier commit, récupérez le fichier "coach.zip" qui contient, entre autres, le fichier "coach.sql" : utilisez le script pour créer la BDD coach dans MySQL.<br>
Dans l'application Android, classe AccesDistant, mettez votre adresse IP locale dans la constante SERVERADDR.<br>
Testez l'application (émulateur utilisé : Pixel 3e API 30).
