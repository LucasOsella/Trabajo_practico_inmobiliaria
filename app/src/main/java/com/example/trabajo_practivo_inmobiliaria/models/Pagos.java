package com.example.trabajo_practivo_inmobiliaria.models;

import java.io.Serializable;

public class Pagos implements Serializable {
    private int idPago;
    private String fecha;
    private double monto;
    private boolean estado;
    private int idContrato;

}
