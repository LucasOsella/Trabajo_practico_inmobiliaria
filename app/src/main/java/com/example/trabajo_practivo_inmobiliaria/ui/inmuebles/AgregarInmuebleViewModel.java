package com.example.trabajo_practivo_inmobiliaria.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajo_practivo_inmobiliaria.models.Inmueble;
import com.example.trabajo_practivo_inmobiliaria.request.ApiClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Uri> mUri = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Uri> getmUri() {
        return mUri;
    }

    public LiveData<String> getmError() {
        return mError;
    }


    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            mUri.setValue(uri);
        }
    }

    public void agregarInmueble(String direccion, String uso, String ambientes, String superficie, String tipo,String precio, boolean disponible){
       int superficieInt = 0;
        int ambientesInt = 0;
        double precioD = 0;
        try {
            superficieInt = Integer.parseInt(superficie);
            ambientesInt = Integer.parseInt(ambientes);
            precioD= Double.parseDouble(precio);

        } catch (NumberFormatException e) {
            //mError.postValue("Los campos superficie, ambientes y precio deben ser numeros");
            Toast.makeText(getApplication(), "Los campos superficie, ambientes y precio deben ser numeros", Toast.LENGTH_SHORT).show();
            return;
        }
        mError.postValue("");
        if (direccion.isEmpty() || uso.isEmpty() || ambientes.isEmpty() || superficie.isEmpty() || tipo.isEmpty() || precio.isEmpty()){
            //mError.postValue("Debe completar todos los campos");
            Toast.makeText(getApplication(), "Debe completar todo los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mUri.getValue() == null){
            Toast.makeText(getApplication(),"Debe cargar una imagen", Toast.LENGTH_SHORT).show();
            //mError.postValue("Debe cargar una imagen")
            return;
        }
        mError.postValue("");

        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion(direccion);
        inmueble.setUso(uso);
        inmueble.setAmbientes(ambientesInt);
        inmueble.setSuperficie(superficieInt);
        inmueble.setTipo(tipo);
        inmueble.setValor(precioD);
        inmueble.setDisponible(disponible);
        //inmueble.setImagen(mUri.getValue().toString());
        byte[] imagen = transformarImagen();
        String inmuebleJson = new Gson().toJson(inmueble);
        RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);
        MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> llamada = api.CargarInmueble("Bearer " + token, imagenPart, inmuebleBody);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Inmueble cargado con exito", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private byte[] transformarImagen() {
        try {
            Uri uri = mUri.getValue();  //lo puedo usar porque estoy en viewmodel
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (
                FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }
    }

}


