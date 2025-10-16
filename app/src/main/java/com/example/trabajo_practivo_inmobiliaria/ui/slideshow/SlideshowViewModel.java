package com.example.trabajo_practivo_inmobiliaria.ui.slideshow;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mInmuebles = new MutableLiveData<>();
    public SlideshowViewModel(@NonNull Application application) {
        super(application);
        obtenerInmuebles();
    }

    public LiveData<List<Inmueble>> getmInmuebles() {
        return mInmuebles;
    }

    public void obtenerInmuebles(){
        ApiClient.InmoServicio api= ApiClient.getApiInmobiliario() ;
        String toke = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerInmuebles("Bearer " + toke);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    mInmuebles.postValue(response.body());
                    Toast.makeText(getApplication(), "Inmuebles obtenidos con exito", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), "No se pudo obtener los inmuebles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}