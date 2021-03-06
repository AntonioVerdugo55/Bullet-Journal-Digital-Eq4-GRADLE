package com.bullet.bulletjournal.DB;

import com.bullet.bulletjournal.Models.Tarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

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
                         rs.getDate("fecha"), rs.getBoolean("completada"), rs.getInt("sticker"), rs.getString("color")
                        ,rs.getInt("id_categoria"));
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
    public Tarea fetchTareasWithId(int id)
    {
        Tarea quitapon=new Tarea();
        try{
            String query = "SELECT * FROM tareas where id_tarea = "+id+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                quitapon = new Tarea(rs.getInt("id_tarea"), rs.getString("tarea"), rs.getString("descripcion"),
                        rs.getDate("fecha"), rs.getBoolean("completada"), rs.getInt("sticker"), rs.getString("color")
                        ,rs.getInt("id_categoria"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Oops! Something went wrong");
        }
        return quitapon;
    }

    public ObservableList<String> fetchCompletadas()
    {
        ObservableList<String> response = FXCollections.observableArrayList();
        try {
            String query = "SELECT tarea FROM tareas WHERE completada = true ORDER BY fecha";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                response.add(rs.getString("tarea"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("iwi");
        }
        return response;
    }

    public void insertTarea(Tarea tarea)
    {
        try {
            String query = "INSERT INTO tareas(tarea, descripcion, fecha, sticker, color, id_categoria) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, tarea.getTarea());
            st.setString(2, tarea.getDescripcion());
            st.setDate(3, (Date) tarea.getFecha());
            st.setInt(4, tarea.getSticker());
            st.setString(5, tarea.getColor());
            st.setInt(6,tarea.getId_categoria());
            st.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("??");
        }
    }

    public void deleteTarea(int id)
    {
        try{
            String query = "DELETE FROM tareas WHERE id_tarea = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(":'u");
        }
    }

    public void updateCompleTarea(int id)
    {
        try{
            String query = "UPDATE tareas SET completada = true WHERE id_tarea = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(":'u");
        }
    }
    public void updateTarea(int id, String titulo, String descripcion, Date fecha, Integer sticker, String color, int id_categoria)
    {
        try{
            String query = "UPDATE tareas SET tarea= ?,descripcion=?,fecha=?,sticker=?,color=?,id_categoria=? WHERE id_tarea = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,titulo);
            st.setString(2,descripcion);
            st.setDate(3,fecha);
            st.setInt(4,sticker);
            st.setString(5,color);
            st.setInt(6, id_categoria);
            st.setInt(7, id);
            st.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println(":'u");
        }
    }

    public ObservableList<Tarea> fetchTareasCat(int id)
    {
        ObservableList<Tarea> response = FXCollections.observableArrayList();
        try{
            String query = "SELECT * FROM tareas where completada = false AND id_categoria="+id+" order by fecha;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Tarea quitapon;
            while(rs.next())
            {
                quitapon = new Tarea(rs.getInt("id_tarea"), rs.getString("tarea"), rs.getString("descripcion"),
                        rs.getDate("fecha"), rs.getBoolean("completada"), rs.getInt("sticker"), rs.getString("color")
                        ,rs.getInt("id_categoria"));
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

