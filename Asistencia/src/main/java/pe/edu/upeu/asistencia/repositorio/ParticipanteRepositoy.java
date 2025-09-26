package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.conexion.ConnDB;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ParticipanteRepositoy {
    protected List<Participante> participantes = null;

    Connection con = ConnDB.getConexion();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Participante> findAll() {

        participantes = new ArrayList<>();
        try {
            pst=con.prepareStatement("SELECT * FROM participante");
            rs=pst.executeQuery();
            while(rs.next()){
                Participante p = new Participante();
                p.setDni(new SimpleStringProperty(rs.getString("dni")));
                p.setNombre(new SimpleStringProperty(rs.getString("nombre")));
                p.setApellidos(new SimpleStringProperty(rs.getString("apellidos")));
                p.setCarrera(Carrera.valueOf(rs.getString("carrera")));
                p.setTipoParticipante(TipoParticipante.valueOf(rs.getString("tipo_participante")));
                participantes.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return participantes;
    };
}
