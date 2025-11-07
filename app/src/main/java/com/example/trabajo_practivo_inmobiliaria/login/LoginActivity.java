package com.example.trabajo_practivo_inmobiliaria.login;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
private LoginViewModel mv;
private ActivityLoginBinding binding;

private SensorManager sensorManager;
private Sensor acelerometro;
private boolean isInitialized = false;
private boolean sensorActivo = false;
private long lastShakeTime = 0;
private long lastTime = 0;
private float shakeThreshold = 2000f;
private float lastX, lastY, lastZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        new android.os.Handler().postDelayed(() -> sensorActivo = true, 2000);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mv.getMerror().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvError.setText(s.toString());
            }
        });

        binding.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=binding.etEmail.getText().toString();
                String pass=binding.etContrasenia.getText().toString();
                mv.login(user,pass);
//                binding.tvError.setText(mv.getMerror().getValue());
            }
        });

        mv.getAbrirLlamada().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                abrirLlamada(s);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event == null || !sensorActivo) return;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (!isInitialized) {
            lastX = x;
            lastY = y;
            lastZ = z;
            isInitialized = true;
            return;
        }

        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) > 100) {
            long diffTime = currentTime - lastTime;
            lastTime = currentTime;

            float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;

            if (speed > shakeThreshold && (currentTime - lastShakeTime) > 2000) {
                lastShakeTime = currentTime;
                mv.onShakeDetected();
            }

            lastX = x;
            lastY = y;
            lastZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private void abrirLlamada(String numero) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numero));
        startActivity(intent);
    }
}