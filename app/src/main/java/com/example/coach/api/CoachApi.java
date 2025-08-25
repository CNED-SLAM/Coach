package com.example.coach.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe d'accès à l'API
 */
public class CoachApi {
    // adresse locale de la machine
//    private static final String API_URL = "http://192.168.0.109/rest_coach/";
    // 10.0.2.2 = localhost de la machine hôte quand on est dans l'émulateur Android
    private static final String API_URL = "http://10.0.2.2/rest_coach/";

    private static Retrofit retrofit = null;

    /**
     * objet gson pour la conversion en json et le configure pour le format de dates
     */
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    /**
     * Construit l'objet unique qui permet d'accéder à l'api
     * @return retrofit
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            // crée l'objet d'accès à l'api
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL) // renseigne l'url de l'api
                    .addConverterFactory(GsonConverterFactory.create(gson)) // ajoute le convertisseur json
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        return gson;
    }
}
