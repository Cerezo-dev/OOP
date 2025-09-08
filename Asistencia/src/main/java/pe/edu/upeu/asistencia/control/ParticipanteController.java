package pe.edu.upeu.asistencia.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class ParticipanteController {

    @FXML
    private ComboBox<Carrera> cbxCarrera;

    @FXML
    private ComboBox<TipoParticipante> cbxTipoParticipante;

    @FXML
    private TextField txtNombres, txtApellidos, txtDni;

    @FXML
    private TableView<Participante> tableRegPart;
    ObservableList<Participante> participantes;


    @Autowired
    ParticipanteServicioI ps;
    TableColumn<Participante, String> dniCol, nombreCol, apellidoCol, carreraCol, tipoParticipanteCol;
    TableColumn<Participante, Void> opcCol;

    int indexEdit=-1;
    @FXML
    public void initialize() {
        cbxCarrera.getItems().setAll(Carrera.values());
        cbxTipoParticipante.getItems().setAll(TipoParticipante.values());
        cbxCarrera.getSelectionModel().select(Carrera.GENERAL);

        Carrera carrera=cbxCarrera.getSelectionModel().getSelectedItem();
        System.out.println(carrera);
        definirColumnas();
        listarParticipantes();
    }

    public void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        cbxCarrera.getSelectionModel().clearSelection();
        cbxTipoParticipante.getSelectionModel().clearSelection();
    }

    @FXML
    public void registrarParticipante(){
        Participante participante = new Participante()
                ;
        participante.setDni(new SimpleStringProperty(txtDni.getText()));


        participante.setNombre(new SimpleStringProperty(txtNombres.getText()));
        participante.setApellidos(new SimpleStringProperty(txtApellidos.getText()));
        participante.setCarrera(cbxCarrera.getSelectionModel().getSelectedItem());
        participante.setTipoParticipante(cbxTipoParticipante.getSelectionModel().getSelectedItem());


        if (indexEdit==-1){
            ps.save(participante);
        }else{
            ps.update(participante, indexEdit);
            indexEdit=-1;
        }

        limpiarFormulario();
        listarParticipantes();
    }

    public void definirColumnas() {
        dniCol = new TableColumn<>("DNI");
        nombreCol = new TableColumn<>("Nombre");
        apellidoCol = new TableColumn<>("Apellido");
        carreraCol = new TableColumn<>("Carrera");
        tipoParticipanteCol = new TableColumn<>("Tipo Participante");
        opcCol = new TableColumn<>("Opciones");
        tableRegPart.getColumns().addAll(dniCol, nombreCol, apellidoCol, carreraCol, tipoParticipanteCol, opcCol);
    }

    public void listarParticipantes(){
        dniCol.setCellValueFactory(cellData -> cellData.getValue().getDni());
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        apellidoCol.setCellValueFactory(cellData -> cellData.getValue().getApellidos());
        carreraCol.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCarrera().toString())
        );
        agregarAccionesButton();
        participantes = FXCollections.observableArrayList(ps.findAll());
        tableRegPart.setItems(participantes);

    }

    public void eliminarParticipante(int index){
        ps.delete(index);
        listarParticipantes();

    }

    public void editarParticipante(Participante participante,int index){

        txtDni.setText(participante.getDni().getValue());
        txtNombres.setText(participante.getNombre().getValue());
        txtApellidos.setText(participante.getApellidos().getValue());
        cbxTipoParticipante.getSelectionModel().select(participante.getTipoParticipante());
        cbxCarrera.getSelectionModel().select(participante.getCarrera());
        indexEdit=index;
    }

    public void agregarAccionesButton(){
        Callback<TableColumn<Participante, Void>, TableCell<Participante, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button btnEdit = new Button("Editar");
            private final Button bntDelete = new Button("Eliminar");
            {
                btnEdit.setOnAction(event -> {
                    Participante participante = getTableView().getItems().get(getIndex());
                    editarParticipante(participante, getIndex());

                });
                bntDelete.setOnAction(event -> {
                    eliminarParticipante(getIndex());
                });

            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(btnEdit, bntDelete);
                    hbox.setSpacing(10);
                    setGraphic(hbox);
                }
            }
        };
        opcCol.setCellFactory(cellFactory);
    }
}

