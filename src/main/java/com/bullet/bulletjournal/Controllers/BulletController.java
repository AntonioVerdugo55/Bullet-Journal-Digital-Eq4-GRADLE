package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BulletController implements Initializable {
    @FXML
    ListView<Tarea> tablaTareas;

    @FXML
    TableView tablaTareasComple;

    @FXML
    Button btnMenos,btnComple;

    int id;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    TareasDAO TareDAO = new TareasDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Tarea> tareas;
        tareas = TareDAO.fetchTareas();
        tablaTareas.setItems(tareas);
        tablaTareas.setCellFactory(TareaListView -> new TareaCell());
        btnMenos.setOnAction(handlerBorrar);

        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Vas a borrar prro");
        alert.setContentText("Tas seguro?");
    }

    EventHandler<ActionEvent> handlerBorrar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
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
        TareDAO.deleteTarea(id);
        refreshTable();
    }

    public void CompleteTarea() {
        TareDAO.updateCompleTarea(id);
        refreshTable();
    }

    //Refresca la tabla
    public void refreshTable()
    {
        tablaTareas.setItems(TareDAO.fetchTareas());
    }
}