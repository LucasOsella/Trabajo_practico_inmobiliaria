package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.models.Pagos;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolderPago> {

    private List<Pagos> listadoPagos;
    private Context context;
    private LayoutInflater li;

    public PagosAdapter(List<Pagos> listadoPagos, Context context, LayoutInflater li) {
        this.context = context;
        this.listadoPagos = listadoPagos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPago onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.pagos_carview, parent, false);
        return new ViewHolderPago(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPago holder, int position) {

        Pagos pagoActual = listadoPagos.get(position);
        holder.tvCodigoPago1.setText("Codigo de pago: "+pagoActual.getIdPago()+"");
        holder.tvCodigoContrato.setText("Codigo de Contrato: "+pagoActual.getIdContrato()+"");
        holder.tvDetalle.setText("Detalle: "+pagoActual.getDetalle());
        holder.tvImportePago.setText("Importe: "+pagoActual.getMonto());
        holder.tvFechaPago.setText("Fecha de pago: "+pagoActual.getFechaPago());

    }

    @Override
    public int getItemCount() {
        return listadoPagos.size();
    }

    // 4. Nuevo ViewHolder con los IDs del layout de pago
    public class ViewHolderPago extends RecyclerView.ViewHolder {
        TextView tvCodigoPago1, tvDetalle, tvCodigoContrato, tvImportePago, tvFechaPago;
        CardView cardView;

        public ViewHolderPago(@NonNull View itemView) {
            super(itemView);
            // Mapeo de IDs del item_pago.xml
            tvCodigoPago1 = itemView.findViewById(R.id.tvCodigoPago);
            tvCodigoContrato = itemView.findViewById(R.id.tvCodigoContrato1);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            cardView = itemView.findViewById(R.id.idCardPago); // Usamos el ID del CardView del nuevo layout
        }
    }
}