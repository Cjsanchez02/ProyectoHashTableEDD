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
public class ArbolAVL1 {
    public NodoArbol raiz;
    public boolean esAutores; 
    public Locale localeES = new Locale("es", "ES");
    public Collator collator = Collator.getInstance(localeES);
    
    // EsAutores sirve para determinar si el árbol es de autores o palabras clave
    // dado que el Collator debe diferenciar los acentos dependiendo si son palabras clave o autores
    public ArbolAVL1(boolean estado){
        raiz = null;
        esAutores = estado;
        if (esAutores) {
            collator.setStrength(Collator.TERTIARY);  
        } else {
            collator.setStrength(Collator.PRIMARY);
        }
    }
    
    public void insertarAutor(String elem, String inves){
        raiz = insertarAVL(raiz, elem, inves);
    }
    
    public void insertarPalabraClave(String elem, String inves){
        raiz = insertarAVL(raiz, elem, inves);
    }
    
    public NodoArbol insertarAVL(NodoArbol pNodo, String elem, String investigacion){
        if (pNodo == null){
            pNodo = new NodoArbol(elem);
            pNodo.lista.insertar(investigacion);
            return pNodo;
        }
        
        if (collator.compare(elem, pNodo.info) < 0){
            pNodo.izq = insertarAVL(pNodo.izq, elem, investigacion);
        }
        else if (collator.compare(elem, pNodo.info) > 0){
            pNodo.dec = insertarAVL(pNodo.dec, elem, investigacion);
        } else {
            pNodo.lista.insertar(investigacion);
            return pNodo;
        }
        
        pNodo.altura = 1 + 
                mayor(getAltura(pNodo.izq), getAltura(pNodo.dec));
        
        int factor = getFactorEquilibrio(pNodo);
        
        if (factor > 1 && pNodo.izq != null && collator.compare(elem, pNodo.izq.info) < 0){
            return giroDerecha(pNodo);
        }
        
        if (factor < -1 && pNodo.dec != null && collator.compare(elem, pNodo.dec.info) > 0){
            return giroIzquierda(pNodo);
        }
        
        if (factor > 1 && pNodo.izq != null && collator.compare(elem, pNodo.izq.info) > 0){
            pNodo.izq = giroIzquierda(pNodo.izq);
            return giroDerecha(pNodo);
        }
        
        if (factor < -1 && pNodo.dec != null && collator.compare(elem, pNodo.dec.info) < 0){
            pNodo.dec = giroDerecha(pNodo.dec);
            return giroIzquierda(pNodo);
        }
        
        return pNodo;
    }
    
    public NodoArbol Buscar(NodoArbol pNodo, String elem){
        if (pNodo == null){
            return null;
        } else if (collator.compare(elem, pNodo.info) < 0){
            return Buscar(pNodo.izq, elem);
        } else if (collator.compare(elem, pNodo.info) > 0){
            return Buscar(pNodo.dec, elem);
        } else {
            return pNodo;
        }
    }
    
    
    
    
    private NodoArbol giroDerecha(NodoArbol nodoActual) {
        NodoArbol nuevaRaiz = nodoActual.izq;
        NodoArbol temp = nuevaRaiz.dec; // El hijo derecho de la nueva raíz (que estorba)

        // 1. La vieja raíz (nodoActual) baja a ser el hijo DERECHO de la nueva raíz
        nuevaRaiz.dec = nodoActual; 
    
    // 2. El subárbol 'temp' pasa a ser el hijo IZQUIERDO de la vieja raíz
        nodoActual.izq = temp;

    // Actualiza alturas (esto estaba bien, pero depende de los punteros correctos)
        nodoActual.altura = mayor(getAltura(nodoActual.izq), getAltura(nodoActual.dec)) + 1;
        nuevaRaiz.altura = mayor(getAltura(nuevaRaiz.izq), getAltura(nuevaRaiz.dec)) + 1;

        return nuevaRaiz;

    }
 
    // Rotar hacia la izquierda
    private NodoArbol giroIzquierda(NodoArbol nodoActual) {
        NodoArbol nuevaRaiz = nodoActual.dec;
        NodoArbol temp = nuevaRaiz.izq;
 
        // Se realiza la rotacion
        nuevaRaiz.izq = nodoActual;
        nodoActual.dec = temp;
 
        // Actualiza alturas
        nodoActual.altura = mayor(getAltura(nodoActual.izq), getAltura(nodoActual.dec)) + 1;
        nuevaRaiz.altura = mayor(getAltura(nuevaRaiz.izq), getAltura(nuevaRaiz.dec)) + 1;
        
        return nuevaRaiz;
    }

    
    private int getAltura(NodoArbol nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
 
        //Notar que no es necesario recorrer el arbol para conocer la altura del nodo
        //debido a que en las rotaciones se incluye la actualizacion de las alturas respectivas
        return nodoActual.altura;
    }
    
    // Devuelve el mayor entre dos numeros
    private int mayor(int a, int b) {
        return (a > b) ? a : b;
    }
    
    // Obtiene el factor de equilibrio de un nodo
    private int getFactorEquilibrio(NodoArbol nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
 
        return getAltura(nodoActual.izq) - getAltura(nodoActual.dec);
    }
    
    private NodoArbol getNodoConValorMaximo(NodoArbol node) {
        NodoArbol current = node;
        
        while (current.dec != null){
           current = current.dec;
        }
        
        return current;
    }
    
    public void imprimirInOrder() {
        inOrder(raiz);
    }
    
    private void inOrder(NodoArbol focusNode) {
        if (focusNode != null) {
            inOrder(focusNode.izq);
            System.out.print(focusNode);
            inOrder(focusNode.dec);
        }
    }
}
