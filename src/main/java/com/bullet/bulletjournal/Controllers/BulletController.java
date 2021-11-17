package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.HelloApplication;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BulletController implements Initializable {
    @FXML
    ListView<Tarea> tablaTareas;

    @FXML
    ListView<String> tablaTareasComple;

    @FXML
    Button btnMenos,btnComple, btnAgregar;

    int id;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    TareasDAO TareDAO = new TareasDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Tarea> tareas;
        ObservableList<String> completadas;
        tareas = TareDAO.fetchTareas();
        completadas = TareDAO.fetchCompletadas();
        tablaTareas.setItems(tareas);
        tablaTareasComple.setItems(completadas);
        tablaTareas.setCellFactory(TareaListView -> new TareaCell());
        btnMenos.setOnAction(handlerBorrar);
        btnComple.setOnAction(e -> CompleteTarea());
        btnAgregar.setOnAction(e -> {
            try {
                agregarTarea();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    EventHandler<ActionEvent> handlerBorrar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Vas a borrar prro");
            alert.setContentText("Tas seguro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                TareaSelected();
                deleteTarea();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    };

    public void TareaSelected() {
        if(tablaTareas.getSelectionModel().getSelectedItem() != null)
        {
            Tarea srv = tablaTareas.getSelectionModel().getSelectedItem();
            id=srv.getId_tarea();
        }
    }

    public void deleteTarea() {
        TareaSelected();
        TareDAO.deleteTarea(id);
        refreshTable();
    }

    public void CompleteTarea() {
        TareaSelected();
        TareDAO.updateCompleTarea(id);
        refreshTable();
    }

    //Refresca la tabla
    public void refreshTable()
    {
        tablaTareas.setItems(TareDAO.fetchTareas());
        tablaTareasComple.setItems(TareDAO.fetchCompletadas());
    }

    public void agregarTarea() throws IOException
    {
        Stage stage = (Stage) btnMenos.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/AddTareaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setResizable(false);
        stage.setTitle("Agregar Tarea");
        stage.setScene(scene);
        stage.show();
    }
}