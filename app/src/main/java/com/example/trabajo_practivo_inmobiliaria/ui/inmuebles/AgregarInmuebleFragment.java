package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private AgregarInmuebleViewModel mViewModel;
    private FragmentAgregarInmuebleBinding binding;
    private ActivityResultLauncher<Intent> arl;
    private Intent intent;


    public static AgregarInmuebleFragment newInstance() {
        return new AgregarInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);
        View root = binding.getRoot();
        abrirGaleria();
        binding.btnAgregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.launch(intent);
            }

            });

        mViewModel.getmUri().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.imageView3.setImageURI(uri);
            }
        });

        binding.btnAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = binding.etDireccion.getText().toString();
                String precio = binding.etPrecio.getText().toString();
                String uso = binding.etUso.getText().toString();
                String tipo = binding.etTipo.getText().toString();
                String superficie = binding.etSuperficie.getText().toString();
                String ambientes = binding.etAmbientes.getText().toString();
                boolean diponible = binding.rbDisponible.isChecked();
                mViewModel.agregarInmueble(direccion,uso,ambientes,superficie,tipo,precio,diponible);
            }
        });



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("AgregarInmuebleFragment", "Result: " + result);
                mViewModel.recibirFoto(result);

            }
        });
    }

}