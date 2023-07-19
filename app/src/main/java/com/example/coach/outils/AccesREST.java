package com.example.coach.outils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.AsyncTask;
import android.util.Log;

public class AccesREST extends AsyncTask<String, Integer, Long>{

    // propriétés
    public String ret=""; // information retournée par le serveur
    public AsyncResponse delegate=null; // gestion du retour asynchrone
    private String parametres = ""; // paramètres à envoyer en POST au serveur
    private String requestMethod = "GET";

    /**
     * Constructeur : ne fait rien
     */
    public AccesREST(){
        super();
    }

    /**
     * Construction de la chaîne de paramètres à envoyer en POST au serveur
     * @param valeur
     */
    public void addParam(String valeur) {
        try {
            if (parametres.equals("")) {
                // premier paramètre
                parametres = URLEncoder.encode(valeur, "UTF-8");
            }else{
                // paramètres suivants (séparés par /)
                parametres += "/" + URLEncoder.encode(valeur, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter sur requestMethod
     * @param requestMethod
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Méthode appelée par la méthode execute
     * permet d'envoyer au serveur une liste de paramètres
     * @param urls contient l'adresse du serveur dans la case 0 de urls
     * @return null
     */
    @Override
    protected Long doInBackground(String... urls) {
        // pour éliminer certaines erreurs
        System.setProperty("http.keepAlive", "false");
        // objets pour gérer la connexion et la lecture
        BufferedReader reader = null;
        HttpURLConnection connexion = null;

        try {
            // création de l'url à partir de l'adresse reçu en paramètre, dans urls[0]
            URL url = new URL(urls[0]+parametres);
            // ouverture de la connexion
            connexion = (HttpURLConnection) url.openConnection();
            // choix de la méthode POST pour l'envoi des paramètres
            connexion.setRequestMethod(requestMethod);
            // Récupération du retour du serveur
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            ret = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // fermeture des canaux de réception
            try{
                reader.close();
            }catch(Exception e){}
        }
        return null;
    }

    /**
     * Sur le retour du serveur, envoi l'information retournée à processFinish
     * @param result
     */
    @Override
    protected void onPostExecute(Long result) {
        // ret contient l'information récupérée
        delegate.processFinish(this.ret.toString());
    }

}
