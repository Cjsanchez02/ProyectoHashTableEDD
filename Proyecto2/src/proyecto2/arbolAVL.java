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
public class ArbolAVL {
    public NodoArbol raiz;
    public boolean esAutores; 
    public Locale localeES = new Locale("es", "ES");
    public Collator collator = Collator.getInstance(localeES);
    
    // EsAutores sirve para determinar si el árbol es de autores o palabras clave
    // dado que el Collator debe diferenciar los acentos dependiendo si son palabras clave o autores
    // 
    public ArbolAVL(boolean estado){
        raiz = null;
        esAutores = estado;
        if (esAutores) {
            // Collator.TERTIARY distinga entre acentos, mayúsculas y minúsculas
            collator.setStrength(Collator.TERTIARY);  
        } else {
            // Collator.PRIMARY hace que la comparación por collator tome igual a, á y A.
            collator.setStrength(Collator.PRIMARY);
        }
    }
    
    // Función para insertar autores
    public void insertarAutor(String elem, String inves){
        raiz = insertarAVL(raiz, elem, inves);
    }
    
    // Función para insertar palabras clave
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
        } else { // Si el nodo existe significa que hay varias investigaciones asociadas a esa palabra
            pNodo.lista.insertar(investigacion);
            return pNodo;
        }
        
        // Se actualiza la altura del Nodo
        pNodo.altura = 1 + 
                mayor(getAltura(pNodo.izq), getAltura(pNodo.dec));
        
        
        // Se obtiene el factor de equilibrio
        int factor = getFactorEquilibrio(pNodo);
        
        // Caso Rotacion Simple a la derecha
        /*
                Elva (2)
              /
            Christian (1)
          /
        Anthony (0)
        */
        
        if (factor > 1 && pNodo.izq != null && collator.compare(elem, pNodo.izq.info) < 0){
            return giroDerecha(pNodo);
        }
        
        // Caso Rotacion Simple Izquierda
        /*
        Anthony (-2)
          \
           Christian (-1)
              \
               Elva (0)
        */
        if (factor < -1 && pNodo.dec != null && collator.compare(elem, pNodo.dec.info) > 0){
            return giroIzquierda(pNodo);
        }
        
        // Caso Rotacion Doble Izquierda-Derecha
        /*
               Elva (2)
              /
            Anthony (-1)
              \
               Christian (0)
        */
        
        if (factor > 1 && pNodo.izq != null && collator.compare(elem, pNodo.izq.info) > 0){
            pNodo.izq = giroIzquierda(pNodo.izq);
            return giroDerecha(pNodo);
        }
        
        // Caso Rotacion Doble Derecha-Izquierda
        /*
        Anthony (-2)
          \
           Elva (1)
          /
         Christian (0)
        */

        if (factor < -1 && pNodo.dec != null && collator.compare(elem, pNodo.dec.info) < 0){
            pNodo.dec = giroDerecha(pNodo.dec);
            return giroIzquierda(pNodo);
        }
        
        return pNodo;
    }
    
    // Búsqueda recursiva en el árbol AVL
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
    
    // Retorna la info de todo el AVL en InOrden, para que esté ordenado alfabéticamente
    public String imprimirInOrden() {
        return inOrder(raiz).trim();
    }

    private String inOrder(NodoArbol nodo) {
        if (nodo == null) {
            return "";
        }

        String izquierda = inOrder(nodo.izq);
        String actual = nodo.info;
        String derecha = inOrder(nodo.dec);

        String resultado = "";

        if (!izquierda.isEmpty()) {
            resultado += izquierda + ", ";
        }

        resultado += actual;

        if (!derecha.isEmpty()) {
            resultado += ", " + derecha;
        }

        return resultado;
    }
}
