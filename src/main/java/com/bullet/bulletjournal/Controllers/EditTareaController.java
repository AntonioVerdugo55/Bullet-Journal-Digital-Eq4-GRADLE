package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.HelloApplication;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EditTareaController implements Initializable {

    @FXML
    Button btnCancelar, btnAceptar;
    @FXML
    TextField txtTitulo;
    @FXML
    TextArea txtDescripcion;
    @FXML
    ComboBox cmbSticker;
    @FXML
    DatePicker dateFecha;
    @FXML
    ColorPicker colorPicker;

    TareasDAO TareDAO = new TareasDAO(MySQL.getConnection());
    String colorHex;
    Tarea tarea=new Tarea();
    int ident;
    String dir="src/datoweon/wea.dat";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ident = controllers.EasyBufferInt.readLine(dir);
        } catch (IOException o) {
            o.printStackTrace();
        }
        btnAceptar.setOnAction(e -> {

            try {
                insertarTarea();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Mori");
            }
        });
        btnCancelar.setOnAction(e -> {
            try {
                retornar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        try {
            insertarDatos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertarDatos() throws IOException
    {
        Tarea tarea=TareDAO.fetchTareasWithId(ident);
        txtTitulo.setText(tarea.getTarea());
        txtDescripcion.setText(tarea.getDescripcion());

        Date date;
        date=tarea.getFecha();
        dateFecha.setValue(java.sql.Date.valueOf(String.valueOf(date)).toLocalDate());

        switch (tarea.getSticker())
        {
            case 0:
                cmbSticker.setValue("Nada");
                break;
            case 1:
                cmbSticker.setValue("Warning");
                break;
            case 2:
                cmbSticker.setValue("Pencil");
                break;
            case 3:
                cmbSticker.setValue("Equis");
                break;
            case 4:
                cmbSticker.setValue("Estrella");
                break;
        }
        Color c = Color.web(tarea.getColor(),1.0);
        colorPicker.setValue(c);
    }

    public void insertarTarea() throws IOException
    {
        if(txtTitulo.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan datos obligatorios por llenar");
            alert.setContentText("Asegúrate de llenar los campos obligatorios (*)");
            alert.showAndWait();
        }
        else
        {
            Tarea tareado = new Tarea();
            tareado.setId_tarea(0);
            tareado.setCompletada(false);
            tareado.setTarea(txtTitulo.getText());
            tareado.setDescripcion(txtDescripcion.getText());

            LocalDate localDate = dateFecha.getValue();
            Date date1 = java.sql.Date.valueOf(localDate);
            tareado.setFecha(date1);
            java.sql.Date sqlDate = new java.sql.Date(tareado.getFecha().getTime());

            switch (cmbSticker.getSelectionModel().getSelectedItem().toString())
            {
                case "Nada":
                    tareado.setSticker(0);
                    break;
                case "Warning":
                    tareado.setSticker(1);
                    break;
                case "Pencil":
                    tareado.setSticker(2);
                    break;
                case "Equis":
                    tareado.setSticker(3);
                    break;
                case "Estrella":
                    tareado.setSticker(4);
                    break;
            }

            String hex3 = Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6).toUpperCase();
            tareado.setColor(hex3);

            TareDAO.updateTarea(ident,tareado.getTarea(),tareado.getDescripcion(),sqlDate,tareado.getSticker(),tareado.getColor());
            retornar();
        }
    }

    public void retornar() throws IOException
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/BulletView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setResizable(false);
        stage.setTitle("Bullet Journal");
        stage.setScene(scene);
        stage.show();
    }

}
