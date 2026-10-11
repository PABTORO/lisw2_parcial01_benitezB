package co.edu.unicauca.saberpro;

import java.util.Arrays;

import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.FiltroEnunciado;
import co.edu.unicauca.saberpro.pipeline.FiltroOpciones;
import co.edu.unicauca.saberpro.pipeline.ValidadorPipeline;
import co.edu.unicauca.saberpro.repository.IPreguntaRepository;
import co.edu.unicauca.saberpro.repository.PreguntaRepositoryImpl;
import co.edu.unicauca.saberpro.service.IPreguntaService;
import co.edu.unicauca.saberpro.service.PreguntaServiceImpl;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== BANCO DE PREGUNTAS SABER PRO ===");

        // Configuración de dependencias (DIP)
        IPreguntaRepository repository = new PreguntaRepositoryImpl();
        ValidadorPipeline pipeline = new ValidadorPipeline(); 
        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());

        // ERROR INTENCIONAL 4: Se intenta pasar 'null' en lugar del pipeline al servicio
        //IPreguntaService service = new PreguntaServiceImpl(repository, null)
        // Corrección 4: Se pasa el pipeline correctamente al servicio
        IPreguntaService service = new PreguntaServiceImpl(repository, pipeline);

        // Pregunta de prueba
        Pregunta p1 = new Pregunta(
                1,
                "¿Cuál es el objetivo principal del patrón Pipes and Filters?",
                Arrays.asList(
                        "A. Dividir proceso en etapas",
                        "B. Crear BD",
                        "C. Usar MVC",
                        "D. Ninguna"
                ),
                "A. Dividir proceso en etapas",
                "Borrador"
        );

        //boolean exito = service.registrarPregunta(p1);
        // Estudiante 8: Se registra la pregunta con un rol autorizado
        boolean exito = service.registrarPreguntaComo(p1, "Autor");
        
        if (exito) {

            System.out.println(
                    "Pregunta registrada con éxito. Total en banco: " +
                    service.obtenerTodas().size()
            );

        } else {

            //System.out.println(
            //        "No se pudo registrar la pregunta debido a fallos en el pipeline."
            //);
            // Estudiante 8: Mensaje de error más específico para el caso de rol no autorizado
            System.out.println(
                "No se pudo registrar la pregunta: el usuario no está autorizado o la pregunta no superó las validaciones."
            );
        }
    }
}