package co.edu.unicauca.saberpro.service;

import java.util.List;

import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.ValidadorPipeline;
import co.edu.unicauca.saberpro.repository.IPreguntaRepository;

public class PreguntaServiceImpl implements IPreguntaService {

    private final IPreguntaRepository repository;
    private final ValidadorPipeline pipeline;

    public PreguntaServiceImpl(
            IPreguntaRepository repository,
            ValidadorPipeline pipeline) {

        this.repository = repository;
        this.pipeline = pipeline;
    }
    // ERROR INTENCIONAL 3: Firma del método no coincide con la interfaz IPreguntaService
    // La interfaz define 'registrarPregunta(Pregunta p)' pero aquí escribieron 'guardarPregunta'
    //public boolean guardarPregunta(Pregunta pregunta) {
    //if (pipeline.ejecutar(pregunta)) {
    //return repository.guardar(pregunta);
    //}
    //return false;
    //}
    //Corrección 3:  El método debe llamarse registrarPregunta para coincidir con IPreguntaService.
    
    @Override
    public boolean registrarPregunta(Pregunta pregunta) {

        if (pipeline.ejecutar(pregunta)) {
            return repository.guardar(pregunta);
        }

        return false;
    }

    @Override
    public List<Pregunta> obtenerTodas() {
        return repository.listar();
    }

    //Estudiante 8: Registra una pregunta solo si el usuario tiene un rol autorizado.
    @Override
    public boolean registrarPreguntaComo(
        Pregunta pregunta,
        String rolUsuario) {

        if ("Autor".equals(rolUsuario)
                || "Administrador".equals(rolUsuario)) {

            return registrarPregunta(pregunta);
        }

        System.out.println(
            "No tiene autorización para registrar preguntas."
        );

    return false;
    }
}