package SistemasOp;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Procesos extends Stage
{
    private Scene escena;
    private VBox vbGeneral;
    private GridPane gdpVista;
    private Stage stage;

    //captura de datos
    private TextField txtNoCliente;
    private TextField txtTiempoLlegada;
    private TextField txtTiempoServicio;
    private Button btnLlegada;
    private HBox hbDatos;

    //vista de la tabla de datos
    private TableColumn <TextField,String> tcNoCliente;
    private ObservableList <TableColumn> oblTabla;
    private TableView <ObservableList>  tbvTabla;



    public void CrearUI()
    {

        //espacios de memoria
        gdpVista = new GridPane();
        cuadrados();

        //casilla de los datos
        txtNoCliente = new TextField();
        txtTiempoLlegada = new TextField();
        txtTiempoServicio = new TextField();
        btnLlegada = new Button("Llegada");
        hbDatos = new HBox(txtNoCliente, txtTiempoLlegada, txtTiempoServicio, btnLlegada);

        //tabla
        oblTabla.add(tcNoCliente);
        tbvTabla = new TableView<ObservableList>();
        tbvTabla.setItems(oblTabla);


        //pantalla principal
        vbGeneral = new VBox(gdpVista, hbDatos, tbvTabla);
        escena = new Scene(vbGeneral,500,700);
        escena.getStylesheets().add(getClass().getResource("/style/prosc.css").toExternalForm());
        stage = new Stage();
    }

    public Procesos()
    {
        CrearUI();
        this.setTitle("Procesos");
        this.setScene(escena);
        stage.setMaximized(true);
        this.show();

    }

    private void cuadrados()
    {
        for (int i = 0; i < 5; i++)
        {
            Rectangle proceso = new Rectangle(50,50);
            proceso.setFill(Color.RED);
            gdpVista.add(proceso,i,0);
        }
    }


}
