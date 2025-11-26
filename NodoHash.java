/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuras_2;

/**
 * Clase NodoHash
 * 
 * Representa un nodo individual dentro de las listas enlazadas de la Tabla Hash.
 *
 * Esta clase actúa como un contenedor elemental para la resolución de colisiones.
 * Cada nodo almacena la información de un {Resumen} y mantiene una referencia
 * al siguiente nodo en la secuencia, permitiendo así que múltiples resúmenes coexistan
 * en el mismo índice de la tabla hash.
 * 
 *
 * @author Anthoby Goncalves
 */
public class NodoHash {
    protected NodoHash pNext;
    protected Resumen data;

    
    /**
     * Constructor que inicializa un nuevo nodo con los datos de un resumen.
     * 
     * Al crear el nodo, el puntero al siguiente elemento ({ pNext}) se establece
     * explícitamente en {null}, indicando que este nodo es independiente
     * o es el último de la lista al momento de su creación.
     *
     * @param data El objeto {Resumen} que será almacenado como carga útil en este nodo.
     * 
     * @author Anthoby Goncalves
     */
    public NodoHash(Resumen data) {
        this.pNext = null;
        this.data = data;
    }
    
    
    
}
