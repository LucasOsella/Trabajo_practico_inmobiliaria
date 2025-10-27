package com.example.trabajo_practivo_inmobiliaria.login;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentHomeBinding;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentLogoutBinding;

import java.util.function.ObjIntConsumer;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
//    private FragmentHomeBinding binding;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar salida")
                .setMessage("¿Deseás cerrar la sesión?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.logout();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                        navController.navigate(R.id.nav_home);
                    }
                })

                .show();

        return null;
    }

}