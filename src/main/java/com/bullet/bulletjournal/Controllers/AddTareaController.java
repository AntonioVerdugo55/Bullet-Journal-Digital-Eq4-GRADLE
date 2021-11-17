package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.HelloApplication;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTareaController implements Initializable {

    @FXML
    Button btnCancelar, btnAceptar;

    TareasDAO TareDAO = new TareasDAO(MySQL.getConnection());

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
        tareado.setTarea( /* Aqui pones el texto del textbox Tarea */);
        tareado.setDescripcion(/* Aqui pones el texto del textfield Descripcion */);
        tareado.setFecha(/* Aqui pones la fecha del Date Picker*/);
        tareado.setSticker(/* Aqui pones el valor del combobox Sticker */);
        tareado.setColor(/* Aqui pones el texto del textbox Color */);
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
