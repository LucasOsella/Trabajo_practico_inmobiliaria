package com.example.trabajo_practivo_inmobiliaria.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentSlideshowBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;

import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private SlideshowViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv= new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getmInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleAdapter inmuebleAdapter= new InmuebleAdapter(inmuebles,getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                RecyclerView recyclerView = binding.listaInmuebles;
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(inmuebleAdapter);
            }
        });
        //mv.obtenerInmuebles();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}