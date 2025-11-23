/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author USER
 */
public class NodoInvestigacion {
    public NodoInvestigacion pnext;
    public String info;
    
    public NodoInvestigacion(String elem){
        info = elem;
        pnext = null;
    }
}
