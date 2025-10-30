package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Pagos;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pagos>> mPagos = new MutableLiveData<>();

    public PagosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Pagos>> getMpagos(){
        return mPagos;
    }

    public void obtenerPagos(Bundle bundle){
        int idContrato = bundle.getInt("idContrato");
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Pagos>> llamada = api.obtenerPagosPorContrato("Bearer " + token, idContrato);
        llamada.enqueue(new Callback<List<Pagos>>() {
            @Override
            public void onResponse(Call<List<Pagos>> call, Response<List<Pagos>> response) {
                if (response.isSuccessful()) {
                    mPagos.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener pagos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pagos>> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener pagos: " + t.getMessage(), t);
                mPagos.setValue(null);
            }
        });

    }


}