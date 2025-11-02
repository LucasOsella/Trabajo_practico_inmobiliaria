package com.example.trabajo_practivo_inmobiliaria.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentCambiarContraseniaBinding;

public class CambiarContraseniaFragment extends Fragment {

    private CambiarContraseniaViewModel mViewModel;
    private FragmentCambiarContraseniaBinding binding;


    public static CambiarContraseniaFragment newInstance() {
        return new CambiarContraseniaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CambiarContraseniaViewModel.class);
        binding = FragmentCambiarContraseniaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getMerror().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvErrorPass.setText(s.toString());
            }
        });

        mViewModel.getMexito().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    NavController navController = NavHostFragment.findNavController(CambiarContraseniaFragment.this);
                    navController.popBackStack();
                }else{
                    mViewModel.getMerror().observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            binding.tvErrorPass.setText(s.toString());
                        }
                    });
                }
            }
        });
       binding.btnGuardarContrasena.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String passActual = binding.etContrasenaActual.getText().toString();
               String passNueva = binding.etContrasenaNueva.getText().toString();
               mViewModel.cambiarContrasenia(passActual,passNueva);
           }
       });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}