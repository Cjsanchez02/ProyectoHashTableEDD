/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Clase NodoHash
 * 
 * Representa un nodo individual dentro de las listas enlazadas de la Tabla Hash.
 *
 * Esta clase act&uacute;a como un contenedor elemental para la resoluci&oacute;n de colisiones.
 * Cada nodo almacena la informaci&oacute;n de un {Resumen} y mantiene una referencia
 * al siguiente nodo en la secuencia, permitiendo as&iacute; que m&uacute;ltiples res&uacute;menes coexistan
 * en el mismo &iacute;ndice de la tabla hash.
 * 
 *
 * @author Anthoby Goncalves
 */
public class NodoHash {
    NodoHash pNext;
    Investigaciones data;

    
    /**
     * Constructor que inicializa un nuevo nodo con los datos de un resumen.
     * 
     * Al crear el nodo, el puntero al siguiente elemento ({ pNext}) se establece
     * expl&iacute;citamente en {null}, indicando que este nodo es independiente
     * o es el &uacute;ltimo de la lista al momento de su creaci&oacute;n.
     *
     * @param data El objeto {Resumen} que ser&aacute; almacenado como carga &uacutetil en este nodo.
     * 
     * @author Anthoby Goncalves
     */
    public NodoHash(Investigaciones data) {
        this.data = data;
        this.pNext = null;
    }
    
}
