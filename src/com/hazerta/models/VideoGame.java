package com.hazerta.models;

import java.util.Objects;

public class VideoGame {

    private int id;
    private String nombre;
    private String genero;
    private double valoracion;
    private boolean jugado;


    public VideoGame() {
    }

    public VideoGame(int id, String nombre, String genero, double valoracion, boolean jugado) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.valoracion = valoracion;
        this.jugado = jugado;

    }

    public VideoGame(String nombre, String genero, double valoracion, boolean jugado) {
        this.nombre = nombre;
        this.genero = genero;
        this.valoracion = valoracion;
        this.jugado = jugado;

    }

    //llamamos constructor anterior
    /*public Videogame(String nombre, String genero, boolean jugado) {
        this(nombre, genero, 0.0, jugado);
    }*/
    //generamos getters and setters de las diferentes constantes
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.genero);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.valoracion) ^ (Double.doubleToLongBits(this.valoracion) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VideoGame other = (VideoGame) obj;
        if (Double.doubleToLongBits(this.valoracion) != Double.doubleToLongBits(other.valoracion)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.genero, other.genero);
    }

   
    
    //constructor para visualizar los resultados obtenidos
    @Override
    public String toString() {
        return "Nombre del videojuego: " + nombre.toUpperCase() + "\n\tID= " + id + ""
                + "\n\tGenero= " + genero + "\n\tValoracion= " + valoracion + "\n\tJugado= " + jugado;
    }
}
