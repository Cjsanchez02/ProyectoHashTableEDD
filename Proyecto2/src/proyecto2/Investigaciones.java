/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author USER
 */
public class Investigaciones {
    String titulo;
    String autores;
    String resumen;
    String keywords;
    
    public Investigaciones() {
        this.titulo = "";
        this.autores = "";
        this.resumen = "";
        this.keywords = "";
    }

    public Investigaciones(String titulo) {
        this.titulo = titulo;
        this.autores = "";
        this.resumen = "";
        this.keywords = "";
    }
}
