/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuras_2;

/**
 * Clase ListaHash
 * 
 * Implementación de una lista enlazada simple para el manejo de colisiones en la Tabla Hash.
 * 
 * Esta clase representa el contenido de un "bucket". Cuando la función hash asigna
 * el mismo índice a múltiples resúmenes, estos se encadenan secuencialmente en esta lista.
 * 
 *
 * @author Anthony Goncalves
 */
public class ListaHash {
    private NodoHash pFirst;
    private int Longitud;

    
    /**
     * Constructor predeterminado que inicializa una lista enlazada vacía.
     *
     * Establece el puntero inicial ({@code pFirst}) en {@code null} y el contador
     * de longitud en 0, preparando la estructura para recibir su primer {@link Resumen}.
     *
     * @author Anthony Goncalves
     */
    public ListaHash() {
        this.pFirst = null;
        this.Longitud = 0;
    }
    
    
    /**
     * Función Tamano
     * 
     * Obtiene la cantidad de elementos almacenados actualmente en esta lista.
     * Este método devuelve el valor del contador interno { Longitud}.
     *
     * @return Un entero con el número total de nodos en la lista.
     * 
     * @author Anthony Goncalves
     */
    public int Tamano() {
        return this.Longitud;
    }

    
    /**
     * Función EsVacio
     * 
     * Verifica si la lista de colisiones no contiene ningún elemento.
     * Comprueba si el puntero principal {pFirst} es nulo.
     *
     * @return {true} si la lista está vacía (sin nodos), {false} si tiene al menos un resumen.
     * 
     * @author Anthony Goncalves
     */
    public boolean EsVacio() {
        return pFirst == null;
    }

    
    /**
     * Función Primero
     * 
     * Obtiene una referencia al primer nodo (cabeza) de la lista enlazada.
     * Este método permite acceder al inicio de la cadena de colisiones. 
     *
     * @return El objeto {NodoHash} que encabeza la lista, o {null} si la lista está vacía.
     * 
     * @author Anthony Goncalves
     */
    public NodoHash Primero() {
        return this.pFirst;
    }

    
    /**
     * Función Ultimo
     * 
     * Obtiene la referencia al último nodo de la lista enlazada.
     *
     * @return El último objeto {NodoHash} de la lista.
     * (Actualmente siempre retorna {@code null}).
     * 
     * @author Anthony Goncalves
     */
    public NodoHash Ultimo() {
        return null;
    }

    
    /**
     * Función Final
     * 
     * Localiza y devuelve el último nodo de la lista enlazada.
     *
     * @return El último objeto {NodoHash} de la cadena, o {null} si la lista está vacía.
     * 
     * @author Anthony Goncalves
     */
    public NodoHash Final() {
        if (pFirst == null) {
            return null;
        } else {
            NodoHash pAux = this.Primero();
            while (pAux.pNext != null) {
                pAux = pAux.pNext;
            }
            return pAux;
        }
    }

    
    /**
     * Método Insertar
     * 
     * Inserta un nuevo Resumen al final de la lista de colisiones.
     * 
     * Busca el último nodo actual de la lista llamando al método {Final()}.
     * Crea un nuevo nodo con el resumen dado.
     * Si la lista estaba vacía, el nuevo nodo se convierte en el primero ({@code pFirst}).
     * Si la lista ya tenía elementos, enlaza el antiguo último nodo con el nuevo nodo.
     * Incrementa el contador de longitud.
     *
     * @param resumen El objeto {Resumen} que se desea almacenar en este bucket.
     * 
     * @author Anthony Goncalves
     */
    public void Insertar(Resumen resumen) {
        NodoHash pValor = this.Final();
        NodoHash pNew = new NodoHash(resumen);
        if (pFirst == null) {
            this.pFirst = pNew;
            this.Longitud++;
        }
        if (pValor != null) {
            pNew.pNext = pValor.pNext;
            pValor.pNext = pNew;
            this.Longitud++;
        }
    }

    
    /**
     * Función BuscarTitulo
     * 
     * Busca un Resumen específico dentro de esta lista de colisiones comparando el título exacto.
     * 
     * Este método recorre secuencialmente la lista enlazada ({NodoHash}) iniciando desde
     * la cabeza ({pFirst}). En cada nodo, compara el título almacenado con el título buscado.
     * 
     *
     * @param titulo El título del documento que se desea buscar.
     * @return El objeto {@Resumen} si se encuentra una coincidencia, o {null}
     * si la lista está vacía o si se llega al final sin encontrarlo.
     * 
     * @author Anthony Goncalves
     */
    public Resumen BuscarTitulo(String titulo) {
        NodoHash pAux = pFirst;

        if (pFirst == null) {
            return null;
        }
        while (pAux != null && !pAux.data.titulo.equals(titulo)) {
            pAux = pAux.pNext;
        }
        if (pAux != null) {
            return pAux.data;
        } else {
            return null;
        }
    }

    
    /**
     * Función Mostrar_Titulos_Lista_Hash
     * 
     * Genera una cadena de texto con los títulos de todos los resúmenes en esta lista.
     * 
     * Recorre la lista enlazada desde el primer nodo y concatena los títulos
     * separados por un punto y espacio. Es utilizado para visualizar el contenido
     * de un "bucket" específico en la interfaz o consola.
     * 
     *
     * @return Una cadena (String) con los títulos concatenados.
     * Si la lista está vacía, retorna un mensaje indicándolo.
     * 
     * @author Anthony Goncalves
     */
    public String Mostrar_Titulos_Lista_Hash() {
        NodoHash pAux;
        String cadena = "";
        if (this.EsVacio()) {
            return "La lista esta vacia";
        } else {
            pAux = this.Primero();
            while (pAux != this.Ultimo()) {
                cadena = cadena + pAux.data.titulo + ". ";
                pAux = pAux.pNext;
            }
            return cadena;
        }
    }
    
    
    /**
     * Funcion Buscar_Palabra_Clave_Hash
     * 
     * Busca y recopila todos los resúmenes dentro de esta lista que contengan una palabra clave específica.
     * Este método recorre la lista enlazada completa. Para cada nodo, verifica si el campo
     * de palabras claves incluye el término buscado. Si hay coincidencia, agrega el resumen
     * a un arreglo de resultados.
     *
     * @param palabra_clave La palabra que se desea buscar dentro de los metadatos del resumen.
     * @return Un arreglo de objetos {Resumen} con las coincidencias encontradas.
     * Retorna {null} si la lista estaba vacía inicialmente.
     * 
     * @author Anthony Goncalves
     */
    public Resumen[] Buscar_Palabra_Clave_Hash(String palabra_clave) {
        Resumen arreglo_resumenes[] = new Resumen [100];
        NodoHash pAux = pFirst;
        int indice = 0;
        
        if (pFirst == null) {
            return null;
        }
        while (pAux != null) {
            
            if(pAux.data.palabras_claves.contains(palabra_clave)){
                arreglo_resumenes[indice] = pAux.data;
                indice ++;
            }
            pAux = pAux.pNext;
        }
       return arreglo_resumenes;
    }
    
 

}
