/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * @author USER
 * @String contiene la información del Nodo
 * @altura sirve para que el arbol AVL lo balanceé
 * @ListaInvestigaciones contendrá la lista en donde 
 * aparezca cada palabra clave o autor
 * @author USER
 */
public class NodoArbol {
    public String info;
    public NodoArbol izq;
    public NodoArbol dec;
    public int altura;
    public ListaInvestigaciones lista;
    
    public NodoArbol(String elem){
        info = elem;
        izq = null;
        dec = null;
        altura = 1;
        lista = new ListaInvestigaciones();
    }
    
}
