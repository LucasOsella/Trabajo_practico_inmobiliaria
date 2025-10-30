package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentPagosBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Pagos;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel mViewModel;
    private FragmentPagosBinding binding;


    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       mViewModel.getMpagos().observe(getViewLifecycleOwner(), new Observer<List<Pagos>>() {
           @Override
           public void onChanged(List<Pagos> pagos) {
               PagosAdapter adapter = new PagosAdapter(pagos, getContext(), getLayoutInflater());
               GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
               binding.lista2.setLayoutManager(layoutManager);
               binding.lista2.setAdapter(adapter);
           }
       });
       mViewModel.obtenerPagos(getArguments());




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
    }

}