package org.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funko implements Serializable {//serializable sirve para poder introducir datos en un fichero
    public static final String COMMA_DELIMITER ="," ; //para saber como se separan los atributos del CSV
    private static final long serialVersionUID = 100L;// es como una id, para controlar la compatibilidad entre diferentes versiones de una clase serializable.

    private String COD;
    private String nombre;
    private String modelo;
    private double precio;
    private LocalDate fecha; //

    public Funko(String descripcion) { //le meto un String que en la main es lo que va a leer del csv cuando creo un nuevo funko
        String[] valores = descripcion.split(COMMA_DELIMITER); //array que guarda los datos del csv separados por una coma
        this.COD = valores[0];
        this.nombre = valores[1];
        this.modelo = valores[2];
        this.precio = Double.parseDouble(valores[3]); //para q recoja el dato como double
        this.fecha = LocalDate.parse(valores[4]); //lo mismo pero con localdate
    }

    public String getCOD() {
        return COD;
    }

    public void setCOD(String COD) {
        this.COD = COD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() { return precio; }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaEspanyol() {
        // Definir un patrón de formato personalizado para "día/mes/año"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formatter); // Formatear el LocalDate como una cadena en el formato deseado

        return fechaFormateada; }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) { this.fecha = LocalDate.parse(fecha); }

    public String toString() {
        return "Funko "
                + " -> Nombre: " + getNombre()
                + "\n Código: " + getCOD()
                + "\n Modelo: " +  getModelo()
                + "\n Precio: " + getPrecio() +"€"
                + "\n Fecha lanzamiento: " + getFechaEspanyol();
    }

}