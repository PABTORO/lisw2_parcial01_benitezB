package co.edu.unicauca.saberpro.model;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private int id;
    private String enunciado;
    private List<String> opciones; // Debe contener 4 opciones
    private String respuestaCorrecta;
    private String estado; // "Borrador", "Publicada"

    public Pregunta(int id, String enunciado, List<String> opciones, String
respuestaCorrecta, String estado) {
        this.id = id;
        this.enunciado = enunciado;
        //ERROR INTENCIONAL 1: Si opciones llega nulo o vacío, debe garantizarse la inicialización
        //this.opciones = opciones;
        //Corrección 1: Se garantiza que la lista de opciones se inicialice como una lista vacía
        if (opciones == null || opciones.isEmpty()) {
            this.opciones = new ArrayList<>();
        } else {
            this.opciones = opciones;
        }

        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getEnunciado() { return enunciado; }
    public List<String> getOpciones() { return opciones; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}