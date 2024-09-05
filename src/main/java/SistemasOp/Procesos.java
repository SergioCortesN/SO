package SistemasOp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Procesos extends Stage {


    private Scene escena;
    private VBox vbGeneral;
    private GridPane gdpVista;
    private Stage stage;
    private Rectangle proceso;
    //captura de datos
    private TextField txtNoCliente;
    private TextField txtTiempoLlegada;
    private TextField txtTiempoServicio;
    private Button btnLlegada;
    private HBox hbDatos;

    //vista de la tabla de datos
    private TableView <String[]> tvTabla;
    private ObservableList<String[]> oblDatos;
    private TableColumn<String[],String> tcCliente;
    private TableColumn<String[],String> tcLlegada;
    private TableColumn<String[],String> tcServicio;


    public void CrearUI() {

        //espacios de memoria
        gdpVista = new GridPane();
        cuadrados();

        //casilla de los datos
        txtNoCliente = new TextField();
        txtTiempoLlegada = new TextField();
        txtTiempoServicio = new TextField();
        btnLlegada = new Button("Llegada");
        btnLlegada.setOnAction(event -> agregarDatos(btnLlegada));
        hbDatos = new HBox(txtNoCliente, txtTiempoLlegada, txtTiempoServicio, btnLlegada);

        //tabla
        Group cl = new Group();
        tvTabla = new TableView();
        oblDatos = FXCollections.observableArrayList();
        tcCliente = new TableColumn("No Cliente");
        tcLlegada = new TableColumn("Tiempo Llegada");
        tcServicio = new TableColumn("Tiempo Servicio");
        tcCliente.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[0]);
        });
        tcLlegada.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[1]);
        });
        tcServicio.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[2]);
        });
        tvTabla.getColumns().addAll(tcCliente, tcLlegada, tcServicio);
        tvTabla.setItems(oblDatos);

        //pantalla principal
        vbGeneral = new VBox(gdpVista, hbDatos, tvTabla);
        escena = new Scene(vbGeneral, 500, 700);
        escena.getStylesheets().add(getClass().getResource("/style/prosc.css").toExternalForm());
        stage = new Stage();
    }

    public Procesos() {
        CrearUI();
        setTitle("Procesos");
        setScene(escena);
        stage.setMaximized(true);
        this.show();

    }

    private void cuadrados() {
        for (int i = 0; i < 5; i++) {

            proceso = new Rectangle(50, 50);
            proceso.setId("font-esp");
            gdpVista.add(proceso, i, 0);

        }


    }

    public void agregarDatos(Button btnLlegada)
    {
        String noCliente = txtNoCliente.getText();
        String tiempoLlegada = txtTiempoLlegada.getText();
        String tiempoServicio = txtTiempoServicio.getText();

        // Crear un arreglo de String que representa una fila
        String[] nuevaFila = { noCliente, tiempoLlegada, tiempoServicio };
        oblDatos.add(nuevaFila);

        // Limpiar campos
        txtNoCliente.clear();
        txtTiempoLlegada.clear();
        txtTiempoServicio.clear();


    }
}
