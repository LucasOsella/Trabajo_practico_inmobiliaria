package com.example.trabajo_practivo_inmobiliaria.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<MapaActual> mMap = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMmap(){
        return mMap;
    }

    public void cargarMapa(){
     MapaActual mapaActual= new MapaActual();
     mMap.setValue(mapaActual);
    }
    public class MapaActual implements OnMapReadyCallback {
        private LatLng ubicacion  = new LatLng(-33.049808359775255, -65.62110136104178);
        public void onMapReady(@NonNull GoogleMap googleMap){
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(ubicacion)
                    .title("Inmobiliaria Clever");
            googleMap.addMarker(markerOptions);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraPosition cameraPosition = CameraPosition.builder()
                    .target(ubicacion)
                    .zoom(18)
                    .bearing(0)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);
        }
    }

}