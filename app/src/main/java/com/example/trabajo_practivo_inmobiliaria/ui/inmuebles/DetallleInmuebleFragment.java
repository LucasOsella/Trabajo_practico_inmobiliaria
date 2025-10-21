package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentDetallleInmuebleBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

public class DetallleInmuebleFragment extends Fragment {

    private DetallleInmuebleViewModel mViewModel;
    private FragmentDetallleInmuebleBinding binding;

    public static DetallleInmuebleFragment newInstance() {
        return new DetallleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallleInmuebleBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(DetallleInmuebleViewModel.class);

        mViewModel.getMinmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvIdInmueble.setText(inmueble.getIdInmueble()+"");
                binding.tvDireccionI.setText(inmueble.getDireccion());
                binding.tvUsoI.setText(inmueble.getUso());
                binding.tvAmbientesI.setText(inmueble.getAmbientes()+"");
                binding.tvLatitudI.setText(inmueble.getLatitud()+"");
                binding.tvLongitudI.setText(inmueble.getLongitud()+"");
                binding.tvValorI.setText(inmueble.getValor()+"");
                binding.checkDisponible.setChecked(inmueble.isDisponible());
                Glide.with(requireContext())
                        .load(ApiClient.URLBASE + inmueble.getImagen())
                        .placeholder(R.drawable.inmueble)
                        .error("null")
                        .into(binding.imgInmuebleD);
            }
        });


        mViewModel.obetenerInbmuebles(getArguments());

        binding.checkDisponible.setOnClickListener(v ->{
            mViewModel.actualizarInmueble(binding.checkDisponible.isChecked());
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}