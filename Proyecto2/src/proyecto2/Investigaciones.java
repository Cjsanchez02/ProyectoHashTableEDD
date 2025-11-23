/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Elva
 */
public class Investigaciones {
    String titulo;
    String autores;
    String resumen;
    String keywords;
    String freqkeywords;
    
    public Investigaciones() {
        this.titulo = "";
        this.autores = "";
        this.resumen = "";
        this.keywords = "";
        this.freqkeywords = "";
    }

    public Investigaciones(String titulo) {
        this.titulo = titulo;
        this.autores = "";
        this.resumen = "";
        this.keywords = "";
        this.freqkeywords = "";
    }
    
    public void contar_keywords(){
        String [] auxkeyword = this.keywords.split(",");
        String [] auxresu = this.resumen.split("[,\\.\\:\\;]");
        for(String s: auxkeyword){
            int cont = 0;
            for(String s2: auxresu){
                if(s.equalsIgnoreCase(s2)){
                    cont += 1;
                }
            }
            this.freqkeywords += Integer.toString(cont) + ", ";
        }
    }
    
    public String mostrar(){
        String texto = "";
        texto = this.titulo + "\n";
        texto += "Autores:\n";
        String [] aux = this.autores.split(",");
        for(String s: aux){
            texto += texto + s.trim() + "\n";
        }
        texto += "\nResumen\n" + this.resumen + "\n";
        texto += "\nPalabras claves: " + this.keywords + "\n";
        return texto;
    }
}
