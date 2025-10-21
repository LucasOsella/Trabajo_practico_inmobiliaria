package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallleInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mImueble= new MutableLiveData<>();
    public DetallleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }
     public LiveData<Inmueble> getMinmueble(){
        return mImueble;
     }

     public void obetenerInbmuebles(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if (inmueble != null){
            mImueble.setValue(inmueble);
        }else{
            Toast.makeText(getApplication(), "No se encontro el inmueble", Toast.LENGTH_SHORT).show();
        }

     }

     public void actualizarInmueble(Boolean estado){
        Inmueble inmueble = new Inmueble();
        inmueble.setDisponible(estado);
        inmueble.setIdInmueble(mImueble.getValue().getIdInmueble());
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> llamada = ApiClient.getApiInmobiliario().actualizarInmueble("Bearer " + token, inmueble);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplication(), "Inmueble actualizado con exito", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(), "Error al actualizar el inmueble", Toast.LENGTH_SHORT).show();
                    Log.d("Error response", response.message());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
     }

}