package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.DB.MySQL;
import com.bullet.bulletjournal.DB.TareasDAO;
import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class BulletController implements Initializable {
    @FXML
    ListView<Tarea> tablaTareas;

    TareasDAO TareDAO = new TareasDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Tarea> tareas;
        tareas = TareDAO.fetchTareas();
        tablaTareas.setItems(tareas);
        tablaTareas.setCellFactory(TareaListView -> new TareaCell());
    }
}