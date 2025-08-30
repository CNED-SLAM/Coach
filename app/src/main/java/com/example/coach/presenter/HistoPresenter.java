package com.example.coach.presenter;

import com.example.coach.api.CoachApi;
import com.example.coach.api.HelperApi;
import com.example.coach.api.ICallbackApi;
import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;

import java.util.Collections;
import java.util.List;

/**
 * 'presenter dédié' à la vue qui affiche la liste des profils
 */
public class HistoPresenter {
    private IHistoView vue;

    /**
     * Constructeur : valorise la propriété qui permet d'accéder à la vue
     * @param vue
     */
    public HistoPresenter(IHistoView vue) {
        this.vue = vue;
    }

    /**
     * Récupère les profils de la BDD distante et les envoie à la vue
     */
    public void chargerProfils() {
        // sollicite l'api et récupère la réponse
        HelperApi.call(HelperApi.getApi().getProfils(), new ICallbackApi<List<Profil>>(){
            @Override
            public void onSuccess(List<Profil> result) {
                if(result != null){
                    List<Profil> profils = result;
                    if (profils != null && !profils.isEmpty()) {
                        Collections.sort(profils, (p1, p2) -> p2.getDateMesure().compareTo(p1.getDateMesure()));
                        vue.afficherListe(profils);
                    }else{
                        vue.afficherMessage("échec chargement profils");
                    }
                }else{
                    vue.afficherMessage("échec chargement profils");
                }
            }
            @Override
            public void onError() {
                vue.afficherMessage("échec chargement profils");
            }
        });
    }

    /**
     * Supprime dans la BDD (et la liste) le profil reçu en paramètre
     * @param profil
     * @param callback
     */
    public void supprProfil (Profil profil, ICallbackApi<Void> callback){
        // Convertit le profil en JSON
        String profilJson = CoachApi.getGson().toJson(profil);
        // sollicite l'api et récupère la réponse
        HelperApi.call(HelperApi.getApi().supprProfil(profilJson), new ICallbackApi<Integer>(){
            @Override
            public void onSuccess(Integer result) {
                if (result == 1) {
                    callback.onSuccess(null);
                    vue.afficherMessage("profil supprimé");
                }else{
                    vue.afficherMessage("échec supression profil");
                }
            }
            @Override
            public void onError() {
                vue.afficherMessage("échec suppression profil");
            }
        });
    }
}
