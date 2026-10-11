package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import co.edu.unicauca.saberpro.model.*;
import co.edu.unicauca.saberpro.pipeline.*;
import co.edu.unicauca.saberpro.repository.*;
import co.edu.unicauca.saberpro.service.*;

public class PreguntaServiceTest {

    @Test
    public void autorPuedeRegistrarPregunta() {

        IPreguntaRepository repository =
                new PreguntaRepositoryImpl();

        ValidadorPipeline pipeline =
                new ValidadorPipeline();

        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());

        IPreguntaService service =
                new PreguntaServiceImpl(repository, pipeline);

        Pregunta pregunta = new Pregunta(
                1,
                "¿Qué es Pipes and Filters?",
                Arrays.asList(
                        "A. Un patrón",
                        "B. Una base de datos",
                        "C. Un lenguaje",
                        "D. Un sistema operativo"
                ),
                "A. Un patrón",
                "Borrador"
        );

        boolean resultado =
                service.registrarPreguntaComo(
                        pregunta,
                        "Autor"
                );

        assertTrue(resultado);
    }

    @Test
    public void administradorPuedeRegistrarPregunta() {

        IPreguntaRepository repository =
                new PreguntaRepositoryImpl();

        ValidadorPipeline pipeline =
                new ValidadorPipeline();

        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());

        IPreguntaService service =
                new PreguntaServiceImpl(repository, pipeline);

        Pregunta pregunta = new Pregunta(
                2,
                "¿Qué es SOLID?",
                Arrays.asList(
                        "A. Principios de diseño",
                        "B. Una base de datos",
                        "C. Un lenguaje",
                        "D. Un sistema operativo"
                ),
                "A. Principios de diseño",
                "Borrador"
        );

        boolean resultado =
                service.registrarPreguntaComo(
                        pregunta,
                        "Administrador"
                );

        assertTrue(resultado);
    }

    @Test
    public void estudianteNoPuedeRegistrarPregunta() {

        IPreguntaRepository repository =
                new PreguntaRepositoryImpl();

        ValidadorPipeline pipeline =
                new ValidadorPipeline();

        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());

        IPreguntaService service =
                new PreguntaServiceImpl(repository, pipeline);

        Pregunta pregunta = new Pregunta(
                3,
                "¿Qué es una interfaz?",
                Arrays.asList(
                        "A. Un contrato",
                        "B. Una base de datos",
                        "C. Un archivo",
                        "D. Un servidor"
                ),
                "A. Un contrato",
                "Borrador"
        );

        boolean resultado =
                service.registrarPreguntaComo(
                        pregunta,
                        "Estudiante"
                );

        assertFalse(resultado);
    }
}