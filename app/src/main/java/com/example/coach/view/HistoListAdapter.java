package com.example.coach.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.api.ICallbackApi;
import com.example.coach.model.Profil;
import com.example.coach.presenter.HistoPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapter pour gérer la liste
 */
public class HistoListAdapter extends RecyclerView.Adapter<HistoListAdapter.ViewHolder> {

    private List<Profil> profils;
    private Context context;
    private IOnProfilClickListener listener;

    /**
     * Constructeur : valorise les propriétés privées
     * @param profils
     * @param context
     * @param listener
     */
    public HistoListAdapter(List<Profil> profils, Context context, IOnProfilClickListener listener) {
        this.profils = profils;
        this.context = context;
        this.listener = listener;
    }

    /**
     * Construction de la ligne
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return ligne de la liste créée
     */
    @NonNull
    @Override
    public HistoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_histo, parent, false);
        return new HistoListAdapter.ViewHolder(view);
    }

    /**
     * Remplissage de la ligne.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull HistoListAdapter.ViewHolder holder, int position) {
        // récupération de l'img pour l'affichage
        Double img = profils.get(position).getImg();
        String img1desimal = String.format("%.01f", img);
        holder.txtListIMG.setText(img1desimal);
        // récupération de la date pour l'affichage
        Date dateMesure = profils.get(position).getDateMesure();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String dateFormatee = sdf.format(dateMesure);
        holder.txtListDate.setText(dateFormatee);
    }

    /**
     *
     * @return nombre de lignes de la liste
     */
    @Override
    public int getItemCount() {
        return profils.size();
    }

    /**
     * Classe contenant la structure de la ligne (les objets graphiques)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtListDate;
        public final TextView txtListIMG;
        public final ImageButton btnListSuppr;
        private HistoPresenter presenter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListDate = (TextView) itemView.findViewById(R.id.txtListDate);
            txtListIMG = (TextView) itemView.findViewById(R.id.txtListIMG);
            btnListSuppr = (ImageButton) itemView.findViewById(R.id.btnListSuppr);
            init();
        }
        private void init(){
            presenter = new HistoPresenter((HistoActivity)context);
            btnListSuppr.setOnClickListener(v -> btnListSuppr_clic());
            txtListDate.setOnClickListener(v -> txtListDateOrImg_clic());
            txtListIMG.setOnClickListener(v -> txtListDateOrImg_clic());
        }
        private void btnListSuppr_clic(){
            int position = getBindingAdapterPosition();
            presenter.supprProfil(profils.get(position), new ICallbackApi<Void>() {
                @Override
                public void onSuccess(Void result) {
                    profils.remove(position);
                    notifyItemRemoved(position);
                }
                @Override
                public void onError() {

                }
            });
        }
        private void txtListDateOrImg_clic(){
            if (listener != null){
                int position = getBindingAdapterPosition();
                listener.onProfilClick(profils.get(position));
            }
        }
    }

    public interface IOnProfilClickListener {
        void onProfilClick(Profil profil);
    }

}
