package com.example.trabajo_practivo_inmobiliaria.login;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajo_practivo_inmobiliaria.MainActivity;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String mail, String clave){
        ApiClient.InmoServicio api= ApiClient.getApiInmobiliario();
        Call<String>Llamada=api.login(mail, clave);
        Llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    String token=response.body();
                    ApiClient.guardarToken(getApplication(),token);
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                    Log.d("Salida", "Ingreso");
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Login", "Error de red" + t.getMessage());

            }
        });
    }
}