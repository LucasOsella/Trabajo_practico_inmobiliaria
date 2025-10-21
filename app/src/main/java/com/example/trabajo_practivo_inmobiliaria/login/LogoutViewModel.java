package com.example.trabajo_practivo_inmobiliaria.login;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

public class LogoutViewModel extends AndroidViewModel {

    public LogoutViewModel(@NonNull Application application) {
        super(application);
    }



    public void logout (){
        ApiClient.guardarToken(getApplication(),null);
        Intent intent = new Intent(getApplication(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
        Toast.makeText(getApplication(), "Logout exitoso", Toast.LENGTH_SHORT).show();
    }
}