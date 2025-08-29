package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.modelo.Estudiante;

import java.util.List;

public interface EstudianteServicioI {

    //CRUD
    void save(Estudiante estudiante); //C create
    List<Estudiante> findAll();//R report
    Estudiante update(Estudiante estudiante, int index); //U update
    void delete(int index); //D delete

    Estudiante findById(int index);
}
