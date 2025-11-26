/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Clase ListaHash
 * 
 * Implementaci&oacute;n de una lista enlazada simple para el manejo de colisiones en la Tabla Hash.
 * 
 * Esta clase representa el contenido de un "bucket". Cuando la funci&oacute;n hash asigna
 * el mismo &iacute;ndice a m&uacute;ltiples res&uacute;menes, estos se encadenan secuencialmente en esta lista.
 * 
 *
 * @author Anthony Goncalves
 */
public class ListaHash {
    NodoHash pFirst;
    int longitud;
    
      /**
     * Constructor predeterminado que inicializa una lista enlazada vac&iacute;a.
     *
     * Establece el puntero inicial ({@code pFirst}) en {@code null} y el contador
     * de longitud en 0, preparando la estructura para recibir su primer {@link Resumen}.
     *
     * @author Anthony Goncalves
     */
    public ListaHash(){
        this.longitud = 0;
        this.pFirst = null;
    }
    
    /**
     * Funci&oacute;n Tamano
     * 
     * Obtiene la cantidad de elementos almacenados actualmente en esta lista.
     * Este m&eacute;todo devuelve el valor del contador interno { Longitud}.
     *
     * @return Un entero con el n&uacute;mero total de nodos en la lista.
     * 
     * @author Anthony Goncalves
     */
    public int Tamano() {
        return this.longitud;
    }
    
    /**
     * Funci&oacute;n EsVacio
     * 
     * Verifica si la lista de colisiones no contiene ning&uacute;n elemento.
     * Comprueba si el puntero principal {pFirst} es nulo.
     *
     * @return {true} si la lista est&aacute; vac&iacute;a (sin nodos), {false} si tiene al menos un resumen.
     * 
     * @author Anthony Goncalves
     */
    public boolean EsVacio(){
        return pFirst == null;
    }
    
    /**
     * Funci&oacute;n Final
     * 
     * Localiza y devuelve el &uacute;ltimo nodo de la lista enlazada.
     *
     * @return El &uacute;ltimo objeto {NodoHash} de la cadena, o {null} si la lista est&aacute; vac&iacute;a.
     * 
     * @author Anthony Goncalves
     */
    public NodoHash Final(){
        if(pFirst == null){
            return null;
        }else{
            NodoHash pAux = this.pFirst;
            while(pAux.pNext != null){
                pAux = pAux.pNext;
            }
            return pAux;
        }
    }
    
    /**
     * M&eacute;todo Insertar
     * 
     * Inserta un nuevo Resumen al final de la lista de colisiones.
     * 
     * Busca el &uacute;ltimo nodo actual de la lista llamando al m&eacute;todo {Final()}.
     * Crea un nuevo nodo con el resumen dado.
     * Si la lista estaba vac&iacute;a, el nuevo nodo se convierte en el primero ({@code pFirst}).
     * Si la lista ya ten&iacute;a elementos, enlaza el antiguo &uacute;ltimo nodo con el nuevo nodo.
     * Incrementa el contador de longitud.
     *
     * @param resumen El objeto {Resumen} que se desea almacenar en este bucket.
     * 
     * @author Anthony Goncalves
     */
    public void Insertar(Investigaciones inv){
        NodoHash pValor = this.Final();
        NodoHash pNew = new NodoHash(inv);
        if(this.pFirst == null){
            this.pFirst = pNew;
            this.longitud++;
        }
        if(pValor != null){
            pNew.pNext = pValor.pNext;
            pValor.pNext = pNew;
            this.longitud++;
        }
    }
    
    /**
     * Funci&oacute;n BuscarTitulo
     * 
     * Busca un Resumen espec&iacute;fico dentro de esta lista de colisiones comparando el t&iacute;tulo exacto.
     * 
     * Este m&eacute;todo recorre secuencialmente la lista enlazada ({NodoHash}) iniciando desde
     * la cabeza ({pFirst}). En cada nodo, compara el t&iacute;tulo almacenado con el t&iacute;tulo buscado.
     * 
     *
     * @param titulo El t&iacute;tulo del documento que se desea buscar.
     * @return El objeto {@Resumen} si se encuentra una coincidencia, o {null}
     * si la lista est&acute; vac&iacute;a o si se llega al final sin encontrarlo.
     * 
     * @author Anthony Goncalves
     */
    public Investigaciones BuscarTitulo(String titulo){
        NodoHash pAux = this.pFirst;
        if(this.pFirst == null){
            return null;
        }
        while(pAux != null && !pAux.data.titulo.equalsIgnoreCase(titulo)){
            pAux = pAux.pNext;
        }
        if(pAux != null){
            return pAux.data;
        }else{
            return null;
        }
    }
    
    /**
     * Funci&oacute;n Mostrar_Titulos_Lista_Hash
     * 
     * Genera una cadena de texto con los t&iacute;tulos de todos los res&uacute;menes en esta lista.
     * 
     * Recorre la lista enlazada desde el primer nodo y concatena los t&iacute;tulos
     * separados por un punto y espacio. Es utilizado para visualizar el contenido
     * de un "bucket" espec&iacute;fico en la interfaz o consola.
     * 
     *
     * @return Una cadena (String) con los t&iacute;tulos concatenados.
     * Si la lista est&aacute; vac&iacute;a, retorna un mensaje indic&aacute;ndolo.
     * 
     * @author Anthony Goncalves
     */
    public String MostrarTitulosListaHash(){
        NodoHash pAux;
        String cadena = "";
        if(this.EsVacio()){
            return "La lista esta vacía";
        }else{
            pAux = this.pFirst;
            while(pAux != null){
                cadena += pAux.data.titulo + ". ";
                pAux = pAux.pNext;
            }
            return cadena;
        }
    }
    
    /**
     * Funcion Buscar_Palabra_Clave_Hash
     * 
     * Busca y recopila todos los res&uacute;menes dentro de esta lista que contengan una palabra clave espec&iacute;fica.
     * Este m&eacute;todo recorre la lista enlazada completa. Para cada nodo, verifica si el campo
     * de palabras claves incluye el t&eacute;rmino buscado. Si hay coincidencia, agrega el resumen
     * a un arreglo de resultados.
     *
     * @param palabra_clave La palabra que se desea buscar dentro de los metadatos del resumen.
     * @return Un arreglo de objetos {Resumen} con las coincidencias encontradas.
     * Retorna {null} si la lista estaba vac&iacute;a inicialmente.
     * 
     * @author Anthony Goncalves
     */
    public Investigaciones[] BuscarPalabraClaveHash(String palabra_clave){
        Investigaciones arregloResumen[] = new Investigaciones [20];
        NodoHash pAux = pFirst;
        int indice = 0;
        if(this.pFirst == null){
            return null;
        }
        while(pAux != null){
            if(pAux.data.keywords.contains(palabra_clave)){
                arregloResumen[indice] = pAux.data;
                indice ++;
            }
            pAux = pAux.pNext;
        }
        return arregloResumen;
    }
}
