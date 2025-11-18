/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.text.Collator;
import java.util.Locale;
/**
 *
 * @author USER
 */
public class arbolAVL {
    public Nodo raiz;
    public int altura_arbol;
    
    public arbolAVL(){
        raiz = null;
        altura_arbol = 0;
    }
    
    public void insertar(String elem){
        raiz = insertarAVL(raiz, elem);
    }
    
    public Nodo insertarAVL(Nodo pNodo, String elem){
        if (pNodo == null){
            pNodo = new Nodo(elem);
        }
        return pNodo;
    }
}
