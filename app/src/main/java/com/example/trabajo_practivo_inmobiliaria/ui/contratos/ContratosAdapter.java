package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

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
import com.example.trabajo_practivo_inmobiliaria.models.*;
import com.example.trabajo_practivo_inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.*;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ContratosViewHolder> {

    private List<Contratos> contratos;
    private Context context;


    public ContratosAdapter(List<Contratos> contratos, Context context) {
        this.contratos = contratos;
        this.context = context;
    }
    public class ContratosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccion;
        private ImageView foto;
        private CardView cardView;

        public ContratosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            foto = itemView.findViewById(R.id.ivFotoInm);
            cardView = itemView.findViewById(R.id.idCardContrato);
        }
    }

    @NonNull
    @Override
    public ContratosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contrato_cardview, parent, false);
        return new ContratosAdapter.ContratosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratosViewHolder holder, int position) {
        Contratos contratoActual = contratos.get(position);
        holder.tvDireccion.setText(contratoActual.getInmueble().getDireccion());
        Glide.with(context)
                .load(URLBASE + contratoActual.getInmueble().getImagen())
                .placeholder(R.drawable.inmueble)
                .error("null")
                .into(holder.foto);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("contrato", contratoActual);
//                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_main)
//                        .navigate(R.id.detalleContratoFragment, bundle);
//            }
//    }

    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }
}

