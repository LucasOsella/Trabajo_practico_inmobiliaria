package com.example.trabajo_practivo_inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.LongDef;
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

public class InquilinosViewModel extends AndroidViewModel {
    public MutableLiveData<List<Inmueble>> mInquilinos = new MutableLiveData<>();


    public InquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getmInquilinos() {
        return mInquilinos;
    }


    public void obtenerInquilinos(){
        ApiClient.InmoServicio api= ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerInmueblesVigentes("Bearer " + token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    mInquilinos.postValue(response.body());
                    Toast.makeText(getApplication(), "Inmuebles recuperados con exito", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("Error", "onResponse: "+response.message());
                    Toast.makeText(getApplication(), "No se puedo recuperar los inmuebles ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("Error", "onFailure: "+t.getMessage());
                Toast.makeText(getApplication(), "No se puedo conectar con el servidor ", Toast.LENGTH_SHORT).show();
            }
        });



    }
}