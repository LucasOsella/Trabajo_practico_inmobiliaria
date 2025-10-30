package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentDetalleContratosBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Contratos;

public class DetalleContratosFragment extends Fragment {

    private DetalleContratosViewModel mViewModel;
    private FragmentDetalleContratosBinding binding;


    public static DetalleContratosFragment newInstance() {
        return new DetalleContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleContratosViewModel.class);
        binding = FragmentDetalleContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getMcontratos().observe(getViewLifecycleOwner(), new Observer<Contratos>() {
            @Override
            public void onChanged(Contratos contratos) {
                binding.tvCodigoContrato.setText(contratos.getIdContrato()+"");
                binding.tvFechaInicio.setText(contratos.getFechaInicio());
                binding.tvFechaFin.setText(contratos.getFechaFinalizacion());
                binding.tvMonto.setText(contratos.getMontoAlquiler()+"");
                binding.tvInquilino.setText(contratos.getInquilino().getNombre());
                binding.tvInmueble.setText(contratos.getInmueble().getDireccion());

            }
        });
        mViewModel.obtenerContratos(getArguments());


        binding.btnPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.getMcontratos().observe(getViewLifecycleOwner(), new Observer<Contratos>() {
                    @Override
                    public void onChanged(Contratos contratos) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("idContrato", contratos.getIdContrato());
                        Navigation.findNavController(v).navigate(R.id.pagosFragment, bundle);
                    }
                });
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}