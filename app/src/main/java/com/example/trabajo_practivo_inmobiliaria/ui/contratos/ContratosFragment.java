package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentContratosBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Contratos;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mViewModel;
    private FragmentContratosBinding binding;


    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getmContratos().observe(getViewLifecycleOwner(), new Observer<List<Contratos>>() {
            @Override
            public void onChanged(List<Contratos> contratos) {
                ContratosAdapter contratoAdapter = new ContratosAdapter(contratos, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                RecyclerView recyclerView = binding.listaContratos;
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(contratoAdapter);

            }
        });

        mViewModel.obtenerInmueblesConContratos();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}