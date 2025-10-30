package com.example.trabajo_practivo_inmobiliaria.ui.inquilinos;

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
import com.example.trabajo_practivo_inmobiliaria.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolderInquilino> {
    private List<Inmueble> inmuebles;
    private Context context;

    public InquilinoAdapter(List<Inmueble> inmuebles, Context context) {
        this.inmuebles = inmuebles;
        this.context = context;
    }

    public class ViewHolderInquilino extends RecyclerView.ViewHolder {
        private TextView tvDireccion;
        private ImageView foto;
        private CardView cardView;

        public ViewHolderInquilino(View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            foto = itemView.findViewById(R.id.ivFotoInm);
            cardView=itemView.findViewById(R.id.idCardContrato);

        }
    }
    @NonNull
    @Override
    public InquilinoAdapter.ViewHolderInquilino onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contrato_cardview, parent, false);
        return new InquilinoAdapter.ViewHolderInquilino(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInquilino holder, int position) {
        Inmueble inmuebleActual = inmuebles.get(position);
        holder.tvDireccion.setText(inmuebleActual.getDireccion());
        Glide.with(context)
                .load(URLBASE + inmuebleActual.getImagen())
                .placeholder(R.drawable.inmueble)
                .error("null")
                .into(holder.foto);
        Glide.with(context)
                .load(URLBASE + inmuebleActual.getImagen())
                .placeholder(R.drawable.inmueble)
                .error("null")
                .into(holder.foto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("inmuebleID",inmuebleActual.getIdInmueble());
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_main)
                        .navigate(R.id.detalleInquilinoFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }
}
