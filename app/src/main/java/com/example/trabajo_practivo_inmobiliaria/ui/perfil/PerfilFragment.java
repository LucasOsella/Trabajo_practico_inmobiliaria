package com.example.trabajo_practivo_inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentGalleryBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private PerfilViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv=new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getmPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etApellido.setText(propietario.getApellido());
                binding.etNombre.setText(propietario.getNombre());
                binding.etDni.setText(propietario.getDni());
                binding.etEmailPerfil.setText(propietario.getEmail());
                binding.etTelefono.setText(propietario.getTelefono());
            }
        });
        mv.getmEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDni.setEnabled(aBoolean);
                binding.etEmailPerfil.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });

        mv.getmPalabra().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnEditar.setText(s.toString());
            }
        });

        mv.leerPropietario();

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre,apellido,dni,email,telefono;
                nombre=binding.etNombre.getText().toString();
                apellido=binding.etApellido.getText().toString();
                dni=binding.etDni.getText().toString();
                email=binding.etEmailPerfil.getText().toString();
                telefono=binding.etTelefono.getText().toString();
                mv.boton(binding.btnEditar.getText().toString(),nombre,apellido,dni,email,telefono);
            }
        });

        mv.getmError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrorPerfil.setText(s.toString());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}