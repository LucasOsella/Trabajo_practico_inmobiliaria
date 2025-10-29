package com.example.trabajo_practivo_inmobiliaria.ui.contratos;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Contratos;
import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contratos>> mContratos = new MutableLiveData<>();

    private MutableLiveData<String> mError = new MutableLiveData<>();
    private int idInmueble;
    private Inmueble inmueble;
    private List<Inmueble> inmuebles;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        obtenerInmueblesConContratos();
    }

    public LiveData<String> getmError() {
        return mError;
    }

    public LiveData<List<Contratos>> getmContratos() {
        return mContratos;
    }

    public void obtenerInmueblesConContratos() {
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<List<Inmueble>> llamada = api.obtenerInmueblesVigentes("Bearer " + token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    List<Inmueble> inmuebles = response.body();
                    for (Inmueble inmueble : inmuebles) {
                        Log.d("InmueblesID", "onResponse: " + inmueble.getIdInmueble() + "");
                        obtenerContratos(inmueble.getIdInmueble());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("Error", "onFailure:");

            }
        });

    }
    public void obtenerContratos(int idInmueble) {
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<Contratos> llamada = api.obtenerContratosPorInmuebles("Bearer " + token, idInmueble);
        llamada.enqueue(new Callback<Contratos>() {
            @Override
            public void onResponse(Call<Contratos> call, Response<Contratos> response) {
                if (response.isSuccessful()) {
                    List<Contratos> listaActual = mContratos.getValue();
                    //tenia un problema y la ia me recomendo esto y funciono.
                    if (listaActual == null) listaActual = new ArrayList<>();
                    listaActual.add(response.body());
                    mContratos.postValue(listaActual);
                }
                else {
                    Toast.makeText(getApplication(), "No se pudo obtener los contratos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contratos> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

}