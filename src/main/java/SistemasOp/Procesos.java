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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Procesos extends Stage {

    private int contador;
    private Scene escena;
    private VBox vbGeneral;
    private GridPane gdpVista;
    private Stage stage;
    private Button boton1,boton2,boton3,boton4,boton5;
    private Color colorRojo=Color.RED;
    private Color colorVerde=Color.GREEN;

    //captura de datos
    private TextField txtNoCliente;
    private TextField txtTiempoLlegada;
    private TextField txtTiempoServicio;
    private Button btnLlegada, btnSalida;
    private HBox hbDatos;

    //vista de la tabla de datos
    private TableView <String[]> tvTablaCapturas,tvTiempos;
    private ObservableList<String[]> oblDatos, oblTiempos;
    private TableColumn<String[],String> tcCliente;
    private TableColumn<String[],String> tcLlegada;
    private TableColumn<String[],String> tcServicio;
    private TableColumn<String[],String> tcTiempoServicio;
    private TableColumn<String[],String> tcTiempoTermina;
    private HBox hbTablas;


    public void CrearUI() {

        //espacios de memoria
        boton1 = new Button("VACIO");
        boton2 = new Button("VACIO");
        boton3 = new Button("VACIO");
        boton4 = new Button("VACIO");
        boton5 = new Button("VACIO");
        boton1.setPrefSize(100,100);
        boton2.setPrefSize(100,100);
        boton3.setPrefSize(100,100);
        boton4.setPrefSize(100,100);
        boton5.setPrefSize(100,100);
        boton1.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
        boton2.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
        boton3.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
        boton4.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
        boton5.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
        boton1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null,null)));
        boton2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null,null)));
        boton3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null,null)));
        boton4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null,null)));
        boton5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null,null)));
        gdpVista = new GridPane();
        cuadrados();

        //casilla de los datos
        txtNoCliente = new TextField();
        txtTiempoLlegada = new TextField();
        txtTiempoServicio = new TextField();
        btnLlegada = new Button("Llegada");
        btnSalida = new Button("Salida");
        btnLlegada.setOnAction(event -> agregarDatos(btnLlegada));
        btnSalida.setOnAction(event -> agregarTiempos(btnSalida));
        hbDatos = new HBox(txtNoCliente, txtTiempoLlegada, txtTiempoServicio, btnLlegada, btnSalida);

        //tabla Captura de datos

        tvTablaCapturas = new TableView();
        tvTiempos = new TableView();
        oblDatos = FXCollections.observableArrayList();
        oblTiempos = FXCollections.observableArrayList();
        tcCliente = new TableColumn("No Cliente");
        tcLlegada = new TableColumn("Tiempo Llegada");
        tcServicio = new TableColumn("Tiempo Servicio");
        tcTiempoServicio = new TableColumn("Tiempo Inicio Servicio");
        tcTiempoTermina = new TableColumn("Tiempo Termino Servicio");
        tcCliente.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[0]);
        });
        tcLlegada.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[1]);
        });
        tcServicio.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[2]);
        });
        tcTiempoServicio.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[0]);
        });
        tcTiempoTermina.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[1]);
        });
        tvTablaCapturas.getColumns().addAll(tcCliente, tcLlegada, tcServicio);
        tvTablaCapturas.setItems(oblDatos);
        //tabla calculo de tiempos
        tvTiempos.getColumns().addAll(tcTiempoServicio, tcTiempoTermina);
        tvTiempos.setItems(oblTiempos);
        hbTablas = new HBox(tvTablaCapturas, tvTiempos);
        //calcular operaciones de tiempos y agregarlos


        //pantalla principal
        vbGeneral = new VBox(gdpVista, hbDatos, hbTablas);
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
        gdpVista.add(boton1,0,0);
        gdpVista.add(boton2,1,0);
        gdpVista.add(boton3,2,0);
        gdpVista.add(boton4,3,0);
        gdpVista.add(boton5,4,0);

    }

    public void agregarDatos(Button btnLlegada)
    {
        if (txtNoCliente.getText().equals("") && txtTiempoLlegada.getText().equals("") && txtTiempoServicio.getText().equals("")){

        }else {
            String noCliente = txtNoCliente.getText();
            String tiempoLlegada = txtTiempoLlegada.getText();
            String tiempoServicio = txtTiempoServicio.getText();

            //Agregar Datos a la Tabla
            String[] nuevaFila = { noCliente, tiempoLlegada, tiempoServicio };
            oblDatos.add(nuevaFila);
            if(boton1.getText().equals("VACIO"))
            {
                boton1.setText("OCUPADO");
                boton1.setBackground(new Background(new BackgroundFill(colorVerde, null,null)));
            } else if (boton2.getText().equals("VACIO")) {
                boton2.setText("OCUPADO");
                boton2.setBackground(new Background(new BackgroundFill(colorVerde, null,null)));

            } else if (boton3.getText().equals("VACIO")) {
                boton3.setText("OCUPADO");
                boton3.setBackground(new Background(new BackgroundFill(colorVerde, null,null)));
            } else if (boton4.getText().equals("VACIO")) {
                boton4.setText("OCUPADO");
                boton4.setBackground(new Background(new BackgroundFill(colorVerde, null,null)));
            } else if (boton5.getText().equals("VACIO")) {
                boton5.setText("OCUPADO");
                boton5.setBackground(new Background(new BackgroundFill(colorVerde, null,null)));
            }
            // Limpiar campos
            txtNoCliente.clear();
            txtTiempoLlegada.clear();
            txtTiempoServicio.clear();
        }


    }
    public void agregarTiempos(Button Salida)
    {
        int termino;
        int boton=1;
        if (tcTiempoTermina.getCellData(contador-1)==null)
        {
            termino=Integer.parseInt(tcLlegada.getCellData(contador))+Integer.parseInt(tcServicio.getCellData(contador));
            String[] nuevaFila = {tcLlegada.getCellData(contador),String.valueOf(termino)};
            oblTiempos.add(nuevaFila);
            contador++;
            if(boton1.getText().equals("OCUPADO"))
            {
                boton1.setText("VACIO");
                boton1.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton2.getText().equals("OCUPADO")) {
                boton2.setText("VACIO");
                boton2.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));

            } else if (boton3.getText().equals("OCUPADO")) {
                boton3.setText("VACIO");
                boton3.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton4.getText().equals("OCUPADO")) {
                boton4.setText("VACIO");
                boton4.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton5.getText().equals("OCUPADO")) {
                boton5.setText("VACIO");
                boton5.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            }

        }
        else if(!tcTiempoTermina.getCellData(contador-1).isEmpty())
        {
            termino=Integer.parseInt(tcTiempoTermina.getCellData(contador-1))+Integer.parseInt(tcServicio.getCellData(contador));
            String[] nuevaFila = {tcTiempoTermina.getCellData(contador-1),String.valueOf(termino)};
            oblTiempos.add(nuevaFila);
            contador++;
            if(boton1.getText().equals("OCUPADO"))
            {
                boton1.setText("VACIO");
                boton1.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton2.getText().equals("OCUPADO")) {
                boton2.setText("VACIO");
                boton2.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));

            } else if (boton3.getText().equals("OCUPADO")) {
                boton3.setText("VACIO");
                boton3.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton4.getText().equals("OCUPADO")) {
                boton4.setText("VACIO");
                boton4.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            } else if (boton5.getText().equals("OCUPADO")) {
                boton5.setText("VACIO");
                boton5.setBackground(new Background(new BackgroundFill(colorRojo, null,null)));
            }
        }

    }
}