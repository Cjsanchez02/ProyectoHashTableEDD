/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author USER
 */
public class Nodo {
    public String info;
    public Nodo izq;
    public Nodo dec;
    public int altura;
    
    public Nodo(String elem){
        info = elem;
        izq = null;
        dec = null;
        altura = 0;
    }
    
}
