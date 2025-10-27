package com.example.trabajo_practivo_inmobiliaria.login;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.util.Log;
import android.widget.Toast;

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
    private MutableLiveData <String> mError = new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMerror(){
        return mError;
    }
    public void login(String mail, String clave){
        if(mail.isEmpty()||clave.isEmpty()){
            mError.postValue("Debe completar todos los campos");
            return;
        }
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
                    Toast.makeText(getApplication(), "Bienvenido "+mail, Toast.LENGTH_SHORT).show();
                }else{
                    mError.postValue("Error en el login. Usuario o Contraseña incorrecto");
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mError.postValue("Error de red");
                Log.d("Login", "Error de red" + t.getMessage());
            }
        });
    }
}