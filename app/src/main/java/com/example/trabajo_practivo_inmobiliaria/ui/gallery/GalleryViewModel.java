package com.example.trabajo_practivo_inmobiliaria.ui.gallery;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.models.Propietario;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> mPropietario = new MutableLiveData<>();
    private MutableLiveData<Boolean> mEstado = new MutableLiveData<>();
    private MutableLiveData<String> mPalabra = new MutableLiveData<>();

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getmPalabra() {
        return mPalabra;
    }

    public LiveData<Boolean> getmEstado() {
        return mEstado;
    }

    public LiveData<Propietario> getmPropietario(){
        return mPropietario;
    }

    public void boton(String btn, String nombre,String apellido, String dni, String email, String telefono){
        if (btn.equalsIgnoreCase("Editar")){
            mEstado.setValue(true);
            mPalabra.setValue("Guardar");
        }else{
            if (btn.equalsIgnoreCase("Guardar")){
                mEstado.setValue(false);
                mPalabra.setValue("Editar");
            }
            //validar campos
            Propietario p = new Propietario();
            p.setId(mPropietario.getValue().getId());
            p.setApellido(apellido);
            p.setNombre(nombre);
            p.setDni(dni);
            p.setEmail(email);
            p.setTelefono(telefono);
            p.setClave(null);

            String token =ApiClient.leerToken(getApplication());
            Call<Propietario> llamda= ApiClient.getApiInmobiliario().actualizarPropietario("Bearer "+token,p);
            llamda.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplication(), "Propietario agregado con exito", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplication(), "Error al guardar el propietario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            });



        }
    }

    public void leerPropietario(){
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        Call<Propietario> llamada = api.obtenerPropietario("Bearer " + token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    mPropietario.postValue(response.body());
                }else{
                    Toast.makeText(getApplication(), "No se puedo recuperar el perfil ", Toast.LENGTH_SHORT).show();
                    Log.d("Error response", response.message());
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "No se puedo conectar con el servidor ", Toast.LENGTH_SHORT).show();
                Log.d("Error ", t.getMessage());

            }
        });

    }
}