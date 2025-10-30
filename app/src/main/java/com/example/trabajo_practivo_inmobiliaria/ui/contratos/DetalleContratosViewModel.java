package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Contratos;

public class DetalleContratosViewModel extends AndroidViewModel {
    private MutableLiveData<Contratos> mContratos= new MutableLiveData<>();
    public DetalleContratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contratos> getMcontratos(){
        return mContratos;
    }

    public void obtenerContratos(Bundle bundle){
        Contratos contrato = (Contratos) bundle.getSerializable("contrato");
        if (contrato != null){
            Toast.makeText(getApplication(), "Contrato encontrado", Toast.LENGTH_SHORT).show();
            mContratos.postValue(contrato);
        }else{
            Toast.makeText(getApplication(), "No se encontro el contrato", Toast.LENGTH_SHORT).show();
        }
    }
}