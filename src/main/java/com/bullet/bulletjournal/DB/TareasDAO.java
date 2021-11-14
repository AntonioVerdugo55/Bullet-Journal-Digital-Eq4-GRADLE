package com.bullet.bulletjournal.DB;

import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TareasDAO {

    Connection conn;
    public TareasDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Tarea> fetchTareas()
    {
        ObservableList<Tarea> response = FXCollections.observableArrayList();
        try{
            String query = "SELECT * FROM tareas where completada = false order by fecha;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Tarea quitapon;
            while(rs.next())
            {
                quitapon = new Tarea(rs.getInt("id_tarea"), rs.getString("tarea"), rs.getString("descripcion"),
                                    rs.getDate("fecha"), rs.getBoolean("completada"), rs.getInt("sticker"), rs.getString("color"));
                response.add(quitapon);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Oops! Something went wrong");
        }
        return response;
    }
}

