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

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Procesos extends Stage {

    private int contador;
    private Scene escena;
    private VBox vbGeneral;
    private GridPane gdpVista;
    private Stage stage;
    private Button boton1,boton2,boton3,boton4,boton5;
    private Color colorRojo=Color.RED;
    private Color colorVerde=Color.GREEN;
    private int clientes=0;
    private int menor;
    private String indexAnt = "";
    private int indice;
    private int selec;
    private LinkedList <Integer> valorestcServicio=new LinkedList<>();

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
    private TableColumn<String[],String> tcTiempoEspera;
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

        //elegir opcion de salida
        Object[] opciones = {"FIFO", "SALIDA MENOR"};
        selec = JOptionPane.showOptionDialog(null, "ELIGE LA OPCION DE SALIDA DE DATOS", "Opciones",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        //casilla de los datos
        txtNoCliente = new TextField();
        txtTiempoLlegada = new TextField();
        txtTiempoServicio = new TextField();
        btnLlegada = new Button("Llegada");
        btnSalida = new Button("Salida");
        btnLlegada.setOnAction(event -> agregarDatos(btnLlegada));
        if(selec==0){
            btnSalida.setOnAction(event -> agregarTiempos(btnSalida));
        } else if (selec==1) {
            btnSalida.setOnAction(event -> agregarTiemposMenor(btnSalida));
        }
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
        tcTiempoEspera = new TableColumn("Tiempo Espera");
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
        tcTiempoEspera.setCellValueFactory(param -> {
            return new javafx.beans.property.SimpleStringProperty(param.getValue()[2]);
        });
        tvTablaCapturas.getColumns().addAll(tcCliente, tcLlegada, tcServicio);
        tvTablaCapturas.setItems(oblDatos);
        //tabla calculo de tiempos
        tvTiempos.getColumns().addAll(tcTiempoServicio, tcTiempoTermina, tcTiempoEspera);
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
            valorestcServicio.add(Integer.parseInt(tiempoServicio));

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
            clientes++;
            tvTiempos.getItems().add(new String[]{"","",""});


        }

    }
    public void agregarTiempos(Button Salida)
    {
        int termino;
        int espera;
        if (tcTiempoTermina.getCellData(contador-1)==null)
        {
            termino=Integer.parseInt(tcLlegada.getCellData(contador))+Integer.parseInt(tcServicio.getCellData(contador));
            espera=0;
            String[] nuevaFila = {tcLlegada.getCellData(contador),String.valueOf(termino),String.valueOf(espera)};
            oblTiempos.set(contador,nuevaFila);
            contador++;
            if(tvTablaCapturas.getItems().size()<=5)
            {
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
        else if(!tcTiempoTermina.getCellData(contador-1).isEmpty())
        {
            if(Integer.parseInt(tcLlegada.getCellData(contador))<=Integer.parseInt(tcTiempoTermina.getCellData(contador-1)))
            {
                termino=Integer.parseInt(tcTiempoTermina.getCellData(contador-1))+Integer.parseInt(tcServicio.getCellData(contador));
                if(Integer.parseInt(tcTiempoTermina.getCellData(contador-1))-Integer.parseInt(tcLlegada.getCellData(contador))<=0){
                    espera=0;
                } else {
                    espera=Integer.parseInt(tcTiempoTermina.getCellData(contador-1))-Integer.parseInt(tcLlegada.getCellData(contador));
                }
                String []filacal = {tcTiempoTermina.getCellData(contador - 1), String.valueOf(termino),String.valueOf(espera)};
                oblTiempos.set(contador,filacal);
            }
            else if (Integer.parseInt(tcLlegada.getCellData(contador))>Integer.parseInt(tcTiempoTermina.getCellData(contador-1)))
            {
                termino=Integer.parseInt(tcLlegada.getCellData(contador))+Integer.parseInt(tcServicio.getCellData(contador));
                if(Integer.parseInt(tcTiempoTermina.getCellData(contador-1))-Integer.parseInt(tcLlegada.getCellData(contador))<=0){
                    espera=0;
                } else {
                    espera=Integer.parseInt(tcTiempoTermina.getCellData(contador-1))-Integer.parseInt(tcLlegada.getCellData(contador));
                }
                String []filacal = {tcLlegada.getCellData(contador), String.valueOf(termino), String.valueOf(espera)};
                oblTiempos.set(contador,filacal);
            }
            contador++;
            if (clientes<=6) {
                if (boton1.getText().equals("OCUPADO")) {
                    boton1.setText("VACIO");
                    boton1.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
                } else if (boton2.getText().equals("OCUPADO")) {
                    boton2.setText("VACIO");
                    boton2.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));

                } else if (boton3.getText().equals("OCUPADO")) {
                    boton3.setText("VACIO");
                    boton3.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
                } else if (boton4.getText().equals("OCUPADO")) {
                    boton4.setText("VACIO");
                    boton4.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
                } else if (boton5.getText().equals("OCUPADO")) {
                    boton5.setText("VACIO");
                    boton5.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
                }
            }
            clientes--;
        }
    }

    private void agregarTiemposMenor(Button Salida){
        int termino;
        int espera;
        int inicio;
        if(clientes==0){
            return;
        }
        if (indexAnt.isEmpty()) {

            indexAnt = String.valueOf(0);
            termino = Integer.parseInt(tcLlegada.getCellData(0)) + Integer.parseInt(tcServicio.getCellData(0));
            espera = 0;
            String[] nuevaFila = {tcLlegada.getCellData(0), String.valueOf(termino), String.valueOf(espera)};
            valorestcServicio.set(0,100);
            oblTiempos.remove(0);
            oblTiempos.add(0, nuevaFila);;
        } else if (!indexAnt.isEmpty()) {
            buscarMenor();
            if (Integer.parseInt(tcLlegada.getCellData(indice)) >= Integer.parseInt(tcTiempoTermina.getCellData(Integer.parseInt(indexAnt)))) {
                termino = Integer.parseInt(tcLlegada.getCellData(indice)) + Integer.parseInt(tcServicio.getCellData(indice));
                espera = 0;
                inicio = Integer.parseInt(tcLlegada.getCellData(indice));
                String[] nuevaFila = {String.valueOf(inicio), String.valueOf(termino), String.valueOf(espera)};
                oblTiempos.set(indice, nuevaFila);
                indexAnt = String.valueOf(indice);
            } else if (Integer.parseInt(tcLlegada.getCellData(indice)) < Integer.parseInt(tcTiempoTermina.getCellData(Integer.parseInt(indexAnt)))) {
                termino = Integer.parseInt(tcTiempoTermina.getCellData(Integer.parseInt(indexAnt))) + Integer.parseInt(tcServicio.getCellData(indice));
                espera = Integer.parseInt(tcTiempoTermina.getCellData(Integer.parseInt(indexAnt))) - Integer.parseInt(tcLlegada.getCellData(indice));
                inicio = Integer.parseInt(tcTiempoTermina.getCellData(Integer.parseInt(indexAnt)));
                String[] nuevaFila = {String.valueOf(inicio), String.valueOf(termino), String.valueOf(espera)};
                oblTiempos.set(indice, nuevaFila);
                indexAnt = String.valueOf(indice);
            }


        }
        if (clientes<=6) {
            if (boton1.getText().equals("OCUPADO")) {
                boton1.setText("VACIO");
                boton1.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
            } else if (boton2.getText().equals("OCUPADO")) {
                boton2.setText("VACIO");
                boton2.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));

            } else if (boton3.getText().equals("OCUPADO")) {
                boton3.setText("VACIO");
                boton3.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
            } else if (boton4.getText().equals("OCUPADO")) {
                boton4.setText("VACIO");
                boton4.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
            } else if (boton5.getText().equals("OCUPADO")) {
                boton5.setText("VACIO");
                boton5.setBackground(new Background(new BackgroundFill(colorRojo, null, null)));
            }
        }
        clientes--;
        System.out.println(clientes);
    }

    private void buscarMenor(){
        if(clientes==0){
            return;
        }
        menor=valorestcServicio.get(0);
        int salir=0;
        while (salir < valorestcServicio.size()){
            int valor1 = valorestcServicio.get(salir);
            if(valor1 < menor){
                menor=valor1;
            }
            salir++;
        }
        if (valorestcServicio.contains(menor)){
            indice = valorestcServicio.indexOf(menor);
            valorestcServicio.set(indice,100);
        }
    }

}