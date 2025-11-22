/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author USER
 */
public class ListaInvestigaciones {
    public NodoInvestigacion pfirst;
    public NodoInvestigacion plast;
    
    public ListaInvestigaciones(){
        pfirst = null;
        plast = null;
    }
    
    public boolean esVacio(){
        return pfirst == null;
    }
    
    public boolean Buscar(String elem){
        NodoInvestigacion pNodo = pfirst;
        while (pNodo != null){
            if (pNodo.info.equals(elem)){
                return true;
            }
            pNodo = pNodo.pnext;
        }
        return false;
    }
    
    public void insertar(String elem){
        if (esVacio()){
            pfirst = new NodoInvestigacion(elem);
            plast = pfirst;
        } else {
            if (!(Buscar(elem))){
                NodoInvestigacion pNodo = new NodoInvestigacion(elem);
                plast.pnext = pNodo;
                plast = pNodo;
            }            
        }
    }
    
    public int contar_Investigaciones(){
        int resultado = 0;
        NodoInvestigacion pNodo = pfirst;
        while(pNodo!= null){
            resultado += 1;
            pNodo = pNodo.pnext;
        }
        return resultado;
    }
}
