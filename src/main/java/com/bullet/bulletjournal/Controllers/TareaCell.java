package com.bullet.bulletjournal.Controllers;

import com.bullet.bulletjournal.Models.Tarea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class TareaCell extends ListCell<Tarea> {
    @FXML
    AnchorPane fullCell;
    @FXML
    ImageView tareaSticker;
    @FXML
    Label tareaTitulo, tareaDescripcion, tareaFecha, tareaCategoria;
    FXMLLoader mLLoader;
    @Override
    protected void updateItem(Tarea tarea, boolean empty){
        super.updateItem(tarea, empty);
        if (empty || tarea == null)
        {
            setGraphic(null);
            setText(null);
        }
        else
        {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(this.getClass().getResource("/com/bullet/bulletjournal/ListCells/TareaCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fullCell.setStyle("-fx-background-color: #" + tarea.getColor());
            tareaSticker.setImage(new Image(String.valueOf(this.getClass().getResource("/com/bullet/bulletjournal/Images/" + tarea.getSticker() + ".png"))));
            tareaTitulo.setText(tarea.getTarea());
            tareaDescripcion.setText(tarea.getDescripcion());
            tareaFecha.setText(tarea.getFecha().toString());
            switch (tarea.getId_categoria()) {
                case 1 -> tareaCategoria.setText("Tarea");
                case 2 -> tareaCategoria.setText("Evento");
                case 3 -> tareaCategoria.setText("Nota");
                case 4 -> tareaCategoria.setText("Urgente");
                default -> tareaCategoria.setText("e");
            }
            setText(null);
            setGraphic(fullCell);
        }
    }
}
