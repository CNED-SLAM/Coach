package com.example.coach.api;

import com.example.coach.model.Profil;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface contenant les signatures des méthodes pour échanger avec l'API
 */
public interface IRequestApi {
    /**
     * Envoi en POST pour ajouter un profil
     * le nom de la table ("profil") est ajouté à l'url
     * le body contiendra "champs" en clé et profilJson en valeur
     * FormUrlEncoded permet d'avoir ces informations sous cette forme dans le body de l'url
     * @param profilJson profil au format json (à envoyer)
     * @return objet Call permettant d'exécuter la requête (la réponse étant le nombre de lignes ajoutées)
     */
    @FormUrlEncoded
    @POST("profil")
    Call<ResponseApi<Integer>> creerProfil(@Field("champs") String profilJson);

    /**
     * Envoi en GET pour récupérer la liste des profils
     * le nom de la table ("profil") est ajouté à l'url
     * @return objet Call permettant d'exécuter la requête
     *         (la réponse au format ApirResponse dont la partie result
     *         contiendra une liste de profils)
     */
    @GET("profil")
    Call<ResponseApi<List<Profil>>> getProfils();

    /**
     * Envoi en DELETE pour supprimer un profil
     * le nom de la table ("profil") est ajouté à l'url
     * ainsi que le paramètre "champs" en clé et profilJson en valeur
     * @param profilJson profil au format json (à envoyer)
     * @return objet Call permettant d'exécuter la requête (la réponse étant le nombre de lignes supprimées)
     */
    @DELETE("profil/{champs}")
    Call<ResponseApi<Integer>> supprProfil(@Path(value = "champs", encoded = true) String profilJson);
}
