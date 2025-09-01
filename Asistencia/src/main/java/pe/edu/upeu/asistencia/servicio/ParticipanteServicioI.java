package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.modelo.Participante;

import java.util.List;

public interface ParticipanteServicioI {

    //CRUD
    void save(Participante participante); //C create
    List<Participante> findAll();//R report
    Participante update(Participante participante, int index); //U update
    void delete(int index); //D delete

    Participante findById(int index);
}
