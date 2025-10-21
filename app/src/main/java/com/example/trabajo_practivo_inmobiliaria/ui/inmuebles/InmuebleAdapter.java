package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import static com.example.trabajo_practivo_inmobiliaria.request.ApiClient.URLBASE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {
    private List<Inmueble> inmuebles;
    private Context context;

    public InmuebleAdapter(List<Inmueble> inmuebles, Context context) {
        this.inmuebles = inmuebles;
        this.context = context;
    }
    public class InmuebleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccion, tvPrecio;
        private ImageView foto;
        private CardView cardView;
        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            foto = itemView.findViewById(R.id.ivFotoInm);
            cardView=itemView.findViewById(R.id.idCard);
        }
    }

    @NonNull
    @Override
    public InmuebleAdapter.InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble_cardview, parent, false);
        return new InmuebleAdapter.InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.InmuebleViewHolder holder, int position) {

        Inmueble inmuebleActual = inmuebles.get(position);
        holder.tvDireccion.setText(inmuebleActual.getDireccion());
        holder.tvPrecio.setText(String.valueOf(inmuebleActual.getValor()));
        Glide.with(context)
                .load(URLBASE + inmuebleActual.getImagen())
                .placeholder(R.drawable.inmueble)
                .error("null")
                .into(holder.foto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("inmueble",inmuebleActual);
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_main)
                .navigate(R.id.detallleInmuebleFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


}
