package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.HelloApplication;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddTareaController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            btnAceptar.setOnAction(e -> {
                try {
                    insertarTarea();
                }
                catch (IOException ex) {
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
    }

   public void insertarTarea() throws IOException
    {
        Tarea tareado = new Tarea();
        tareado.setId_tarea(0);
        tareado.setCompletada(false);
        tareado.setTarea(txtTitulo.getText());
        tareado.setDescripcion(txtDescripcion.getText());

        LocalDate localDate = dateFecha.getValue();
        Date date = java.sql.Date.valueOf(localDate);
        tareado.setFecha(date);

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

        TareDAO.insertTarea(tareado);
        retornar();
    }

    public void retornar() throws IOException
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/BulletView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setResizable(false);
        stage.setTitle("Agregar Tarea");
        stage.setScene(scene);
        stage.show();
    }

}
