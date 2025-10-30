package com.example.trabajo_practivo_inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentDetalleInquilinoBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mViewModel;
    private FragmentDetalleInquilinoBinding binding;


    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMinquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigoInquilino.setText(inquilino.getIdInquilino());
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvDni.setText(inquilino.getDni());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvEmail.setText(inquilino.getEmail());

            }
        });
        mViewModel.obtenerInquilino(getArguments());
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}