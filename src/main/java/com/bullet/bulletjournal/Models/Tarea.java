package com.bullet.bulletjournal.Models;

import java.util.Calendar;
import java.util.Date;

public class Tarea {
    public int id_tarea;
    public String tarea;
    public String descripcion;
    public Date fecha;
    public boolean completada;
    public int sticker;
    public String color;

    public Tarea()
    {
    }



    public Tarea(int id_tarea, String tarea, String descripcion, Date fecha, boolean completada, int sticker, String color)
    {

        this.id_tarea = id_tarea;
        this.tarea = tarea;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completada = completada;
        this.sticker = sticker;
        this.color = color;

    }



    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getSticker() {
        return sticker;
    }

    public void setSticker(int sticker) {
        this.sticker = sticker;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
