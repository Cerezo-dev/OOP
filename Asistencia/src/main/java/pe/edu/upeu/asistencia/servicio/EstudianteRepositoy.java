package pe.edu.upeu.asistencia.servicio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.modelo.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoy {
    protected List<Estudiante> estudiantes = new ArrayList<>();

    public List<Estudiante> findAll() {
        estudiantes.add(new Estudiante(
                new SimpleStringProperty("Juan"),
                new SimpleBooleanProperty (true)

        ));
        return estudiantes;
    };
}
