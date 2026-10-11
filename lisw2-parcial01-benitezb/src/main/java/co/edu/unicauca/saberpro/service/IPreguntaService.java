package co.edu.unicauca.saberpro.service;

import java.util.List;

import co.edu.unicauca.saberpro.model.Pregunta;

public interface IPreguntaService {

    boolean registrarPregunta(Pregunta pregunta);

    // Estudiante 8: Registra una pregunta solo si el usuario tiene un rol autorizado.
    boolean registrarPreguntaComo(
        Pregunta pregunta,
        String rolUsuario);

    List<Pregunta> obtenerTodas();
}