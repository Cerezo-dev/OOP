package pe.edu.upeu.asistencia.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.VBox; // Descomenta si usas la opción del ToolBar
// import javafx.scene.layout.ToolBar; // Descomenta si usas la opción del ToolBar
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class MainguiController {

    @FXML
    private BorderPane bp;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TabPane tabPane;

    @FXML
    private Menu menu1; //menu2 = new Menu("Cambiar Estilo"); //opcion original

    @FXML
    private MenuItem menuItem1, menuItem2, menuItemC;

    //opciones originales
    //private ComboBox<String> comboBoxEstilo =  new ComboBox<>();
    //private CustomMenuItem customMenuEstilo = new CustomMenuItem(comboBoxEstilo);

    @Autowired
    protected ApplicationContext context;

    //2da opcion
    private ComboBox<String> comboBoxEstilo;

    @FXML
    public void initialize() {

        // --- OPCIÓN ACTUAL: ComboBox dentro de CustomMenuItem ---
        // En Linux, el ComboBox dentro de un menú puede perder el foco y cerrar el menú automáticamente.
        // Esto es por cómo JavaFX maneja los eventos de foco en sistemas basados en GTK.

        //Tollbar opcion
        ToolBar toolBar = new ToolBar();
        ComboBox<String> comboBoxEstiloBarra = new ComboBox<>();
        comboBoxEstiloBarra.getItems().addAll("Estilo por Defecto", "Estilo Oscuro", "Estilo Azul", "Estilo Verde", "Estilo Rosado");
        comboBoxEstiloBarra.setOnAction(event -> cambiarEstiloBarra(comboBoxEstiloBarra.getValue()));
        toolBar.getItems().add(new Label("Estilo:"));
        toolBar.getItems().add(comboBoxEstiloBarra);

        //opcion original
        //menu2.getItems().add(new SeparatorMenuItem());
        //menu2.getItems().add(customMenuEstilo);
        //menuBar.getMenus().add(menu2);

        VBox topBar = new VBox();
        topBar.getChildren().addAll(menuBar, toolBar);
        bp.setTop(topBar);


        // --- OPCIÓN 1: Usar MenuItem por cada estilo (más compatible en Linux) ---
        /*
        // Esta opción evita el problema de foco en Linux, ya que los MenuItem son simples y no pierden el foco.
        Menu menuEstilos = new Menu("Cambiar Estilo");
        MenuItem estiloDefecto = new MenuItem("Estilo por Defecto");
        MenuItem estiloOscuro = new MenuItem("Estilo Oscuro");
        MenuItem estiloAzul = new MenuItem("Estilo Azul");
        MenuItem estiloVerde = new MenuItem("Estilo Verde");
        MenuItem estiloRosado = new MenuItem("Estilo Rosado");

        estiloDefecto.setOnAction(e -> aplicarEstilo("defecto"));
        estiloOscuro.setOnAction(e -> aplicarEstilo("oscuro"));
        estiloAzul.setOnAction(e -> aplicarEstilo("azul"));
        estiloVerde.setOnAction(e -> aplicarEstilo("verde"));
        estiloRosado.setOnAction(e -> aplicarEstilo("rosado"));

        menuEstilos.getItems().addAll(estiloDefecto, estiloOscuro, estiloAzul, estiloVerde, estiloRosado);
        menuBar.getMenus().add(menuEstilos);

        // Método alternativo para aplicar el estilo:
        // private void aplicarEstilo(String estilo) {
        //     Scene scene = bp.getScene();
        //     scene.getStylesheets().clear();
        //     switch (estilo) {
        //         case "oscuro":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-oscuro.css").toExternalForm()); break;
        //         case "azul":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-azul.css").toExternalForm()); break;
        //         case "verde":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-verde.css").toExternalForm()); break;
        //         case "rosado":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-rosado.css").toExternalForm()); break;
        //         default: break;
        //     }
        // }
        */

        // --- OPCIÓN 2: ComboBox en ToolBar fuera del menú (solución más estable en todos los sistemas) ---
        /*
        // El ComboBox fuera del menú no sufre el problema de foco en Linux, ya que no está dentro de un menú desplegable.
        ToolBar toolBar = new ToolBar();
        ComboBox<String> comboBoxEstiloBarra = new ComboBox<>();
        comboBoxEstiloBarra.getItems().addAll("Estilo por Defecto", "Estilo Oscuro", "Estilo Azul", "Estilo Verde", "Estilo Rosado");
        comboBoxEstiloBarra.setOnAction(event -> cambiarEstiloBarra(comboBoxEstiloBarra.getValue()));
        toolBar.getItems().add(new Label("Estilo:"));
        toolBar.getItems().add(comboBoxEstiloBarra);

        // Coloca el ToolBar junto al MenuBar en la parte superior
        VBox topBar = new VBox();
        topBar.getChildren().addAll(menuBar, toolBar);
        bp.setTop(topBar);

        // Método alternativo para cambiar el estilo:
        // private void cambiarEstiloBarra(String estilo) {
        //     Scene scene = bp.getScene();
        //     scene.getStylesheets().clear();
        //     switch (estilo) {
        //         case "Estilo Oscuro":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-oscuro.css").toExternalForm()); break;
        //         case "Estilo Azul":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-azul.css").toExternalForm()); break;
        //         case "Estilo Verde":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-verde.css").toExternalForm()); break;
        //         case "Estilo Rosado":
        //             scene.getStylesheets().add(getClass().getResource("/css/estilo-rosado.css").toExternalForm()); break;
        //         default: break;
        //     }
        // }
        */

        MenuListener menuListener = new MenuListener();
        MenuItemListener menuItemLister = new MenuItemListener();
        menuItem1.setOnAction(menuItemLister::handle);
        menuItem2.setOnAction(menuItemLister::handle);
        menuItemC.setOnAction(menuItemLister::handle);
    }

    private void cambiarEstiloBarra (String estilo) {
         Scene scene = bp.getScene();
         scene.getStylesheets().clear();
         switch (estilo) {
             case "Estilo Oscuro":
                 scene.getStylesheets().add(getClass().getResource("/css/estilo-oscuro.css").toExternalForm()); break;
             case "Estilo Azul":
                 scene.getStylesheets().add(getClass().getResource("/css/estilo-azul.css").toExternalForm()); break;
             case "Estilo Verde":
                 scene.getStylesheets().add(getClass().getResource("/css/estilo-verde.css").toExternalForm()); break;
             case "Estilo Rosado":
                 scene.getStylesheets().add(getClass().getResource("/css/estilo-rosado.css").toExternalForm()); break;
             default: break;
         }
    }

    class MenuItemListener {
        Map<String, String[]> menuConfig=Map.of(
                "menuItem1", new String []{"/fxml/main_asistencia.fxml" , "Gestion Asistencia", "T"},
                "menuItem2", new String []{"/fxml/main_participante.fxml" , "Gestion Participante", "T"},
                "menuItemC", new String []{"/fxml/login.fxml" , "Salir", "C"}
        );

        public void handle(ActionEvent e) {
            String id=((MenuItem)e.getSource()).getId();
            if(menuConfig.containsKey(id)) {
                String[] items=menuConfig.get(id);
                if(items[2].equals("C")) {
                    Platform.exit();
                    System.exit(0);
                } else {
                    abrirArchivoArchivofxml(items[0],items[1]);
                }
            }
        }
    }

    private void abrirArchivoArchivofxml(String rutaArchivo, String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaArchivo));
            fxmlLoader.setControllerFactory(context::getBean);
            Parent root = fxmlLoader.load();

            ScrollPane scrollPane = new ScrollPane(root);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            Tab newTab = new Tab(titulo, scrollPane);
            tabPane.getTabs().clear();
            tabPane.getTabs().add(newTab);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class MenuListener {
        public void menuSelected(Event e) {

        }
    }
}
