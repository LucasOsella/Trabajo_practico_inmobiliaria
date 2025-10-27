package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentSlideshowBinding;
import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private InmuebleViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv= new ViewModelProvider(this).get(InmuebleViewModel.class);
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
        mv.obtenerInmuebles();

        binding.fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_nav_slideshow_to_agregarInmuebleFragment);

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