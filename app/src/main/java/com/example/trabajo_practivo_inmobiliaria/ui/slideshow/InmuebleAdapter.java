package com.example.trabajo_practivo_inmobiliaria.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            foto = itemView.findViewById(R.id.ivFotoInm);
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
        String urlBase= "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net";
        Inmueble inmuebleActual = inmuebles.get(position);
        holder.tvDireccion.setText(inmuebleActual.getDireccion());
        holder.tvPrecio.setText(String.valueOf(inmuebleActual.getValor()));
        Glide.with(context)
                .load(urlBase + inmuebleActual.getImagen())
                .placeholder(R.drawable.inmueble)
                .error("null")
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


}
