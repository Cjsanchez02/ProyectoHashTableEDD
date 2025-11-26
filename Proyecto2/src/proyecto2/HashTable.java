/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import javax.swing.JOptionPane;

/** Clase hash_table
 * Implementaci&oacute;n de una estructura de datos Hash Table.
 * Esta implementaci&oacute;n gestiona las colisiones mediante encadenamiento separado,
 * utilizando un arreglo de listas enlazadas de tipo {ListaHash}. Cada &iacute;ndice
 * del arreglo representa un "bucket" (es decir, guarda una lista) donde se almacenan
 * los res&uacute;menes que comparten el mismo &iacute;ndice hash.
 * @author Antho
 */
public class HashTable {

    protected int Tamano_arreglo;
    protected ListaHash[] Resumenes;

    /**
     * Construtor de la clase hash_table
     * Inicializa una nueva instancia de la tabla Hash con una capacidad definida.
     * Este constructor reserva el espacio en memoria para el arreglo de "buckets",
     * determinando as&iacute; el tamaño f&iacute;sico de la tabla.

     *Al inicializar el arreglo con {new ListaHash[Tamano_arreglo]},
     * todas las posiciones comienzan siendo {null}. Las listas enlazadas individuales
     * deben instanciarse posteriormente cuando se inserte el primer elemento en cada posici&oacute;n.
     *
     * @param Tamano_arreglo La cantidad total de buckets (casilleros) que tendr&aacute; la tabla.
     * Este valor define el rango de &iacute;ndices posibles [0 a Tamano_arreglo - 1].
     * 
     * * @author Anthony Goncalves
     */
    public HashTable(int Tamano_arreglo) {
        this.Tamano_arreglo = Tamano_arreglo;
        this.Resumenes = new ListaHash[Tamano_arreglo];
    }

    
    /**
     * Agrega un nuevo objeto {Resumen} a la tabla hash.
     * Este m&eacute;todo realiza los siguientes pasos:
     * Calcula el &iacute;ndice de almacenamiento (hash) bas&aacute;ndose en el t&iacute;tulo del resumen.
     * Intenta insertar el resumen en la lista (bucket) correspondiente a ese &iacute;ndice.
     * Si el bucket est&aacute; vac&iacute;o (es nulo), inicializa una nueva {ListaHash} y luego inserta el elemento.
     *
     * @param resumen El objeto {Resumen} que se desea almacenar.
     * 
     * @author Anthony Goncalves
     */
    public void AgregarResumen(Investigaciones resumen) {
        int indice = this.Hash(resumen.titulo);
        try {
            this.Resumenes[indice].Insertar(resumen);

        } catch (Exception e) {
            this.Resumenes[indice] = new ListaHash();
            this.Resumenes[indice].Insertar(resumen);
        }

    }

    /**
     * M&eacute;todo AgregrarPalabrasClaves
     * 
     * Indexa un resumen en la tabla hash bas&aacute;ndose en sus palabras claves.

     * Este m&eacute;todo descompone la cadena de palabras claves del resumen (separadas por comas),
     * calcula el hash para cada una de ellas individualmente e inserta la referencia del
     * resumen en cada una de las posiciones resultantes.
     * 
     * Esto permite que el mismo documento pueda ser encontrado buscando por cualquiera
     * de sus palabras claves.
     * 
     *
     * @param resumen El objeto {Resumen} que contiene las palabras claves a indexar.
     * Se asume que la propiedad {palabras_claves} usa comas como separador.
     * 
     * @author Anthony Goncalves
     */
    public void AgregrarPalabrasClaves(Investigaciones resumen) {
        String claves[] = resumen.keywords.split(",");
        for (int i = 0; i < claves.length; i++) {
            int indice = this.HashKeys(claves[i]);

            try {
                this.Resumenes[indice].Insertar(resumen);

            } catch (Exception e) {
                this.Resumenes[indice] = new ListaHash();
                this.Resumenes[indice].Insertar(resumen);
            }
        }

    }
    
    /**
     * Funci&oacute;n BuscarPalabraClave
     * 
     * Busca y recupera todos los res&uacute;menes asociados a una palabra clave espec&iacute;fica.
     * 
     * El funcionamiento es el siguiente:
     * Calcula el &iacute;ndice (hash) de la palabra clave para localizar el "bucket" correcto.
     * Accede a la lista enlazada ({ListaHash}) en esa posición.
     * Delega la b&uacute;squeda a la lista para obtener todos los res&uacute;menes que coincidan.
     * 
     * 
     * @param palabra_clave La palabra o t&eacute;rmino que se desea buscar.
     * @return Un arreglo de objetos {Resumen} asociados a dicha palabra clave.
     * Retornar&aacute; {null} o un arreglo vacío si no se encuentran coincidencias
     * (dependiendo de la implementación de tu lista).
     * 
     * @author Anthony Goncalves
     */
    public Investigaciones[] BuscarPalabraClave(String palabra_clave) {
        int indice = this.Hash(palabra_clave);
        try {
            return this.Resumenes[indice].BuscarPalabraClaveHash(palabra_clave);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontrÃ³ un resumen por ese tÃ­tulo");
            return null;
        }
    }
    

     /**
     * Funci&oacute;n BuscarResumen
     * 
     * Busca y recupera un {Resumen} espec&iacute;fico utilizando su t&iacute;tulo como clave de b&uacute;squeda.
     * 
     * El proceso de b&uacute;squeda sigue la l&oacute;gica de la tabla hash:
     * Calcula el &iacute;ndice hash bas&aacute;ndose en el t&iacute;tulo.
     * Accede al "bucket" correspondiente en el arreglo.
     * Delega la b&uacute;squeda a la lista enlazada ({@link ListaHash}) interna.
     * 
     * Esta funci&oacute;n maneja las colisiones o ausencias mediante un bloque {try-catch}.
     * Si el resumen no existe o el bucket est&aacute; vac&iacute;o, se captura la excepci&oacute;n, se muestra
     * un mensaje visual al usuario y se retorna {null}.
     *
     * @param titulo El t&iacute;tulo exacto del resumen que se desea buscar.
     * @return El objeto {Resumen} encontrado, o {null} si no existe en la tabla.
     * 
     * @author Anthony Goncalves
     */    
    public Investigaciones BuscarResumen(String titulo) {
        int indice = this.Hash(titulo);

        try {
            return this.Resumenes[indice].BuscarTitulo(titulo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontrÃ³ un resumen por ese tÃ­tulo");
            return null;
        }
    }

    /**
     * Funci&oacute;n Hash
     * 
     * 
     * Funci&oacute;n de dispersi&oacute;n, calcula el &iacute;ndice para un t&iacute;tulo dado.
     * 
     * Esta implementaci&oacute;n utiliza un algoritmo de Hash Aditivo
     * 
     * Recorre cada car&aacute;cter del t&iacute;tulo.
     * Suma los valores num&eacute;ricos (ASCII/Unicode) de todos los caracteres.
     * Aplica el operador m&oacute;dulo ({@code %}) con el tamaño del arreglo para asegurar
     * que el &iacute;ndice resultante est&eacute; dentro de los l&iacute;mites v&aacute;lidos [0 a Tamano_arreglo - 1].
     * 
     *
     * @param titulo La cadena de texto (t&iacute;tulo) a la cual se le generar&aacute; el hash.
     * @return Un n&uacute;mero entero que representa el &iacute;ndice del arreglo donde se debe almacenar o buscar el resumen.
     * 
     * @author Anthony Goncalves
     */
    public int Hash(String titulo) {
        int hash = 0;
        for (int i = 0; i < titulo.length(); i++) {
            hash += titulo.charAt(i);
        }
        return hash % this.Tamano_arreglo;
    }

    /**
     * Funci&oacute;n HashKeys
     * 
     * Funci&oacute;n de dispersi&oacute;n (Hash Function) utilizada espec&iacute;ficamente para las palabras claves.
     * 
     * Al igual que la funci&oacute;n principal, utiliza un algoritmo de Hash Aditivo
     * (suma de valores ASCII) para determinar en qu&eacute; bucket debe buscarse o almacenarse
     * la referencia asociada a esta palabra clave.
     * 
     *
     * @param keys La palabra clave individual (ej. "tecnolog&iacute;a") a hashear.
     * @return El &iacute;ndice calculado dentro del rango v&aacute;lido [0 a Tamano_arreglo - 1].
     * 
     * @author Anthony Goncalves
     */
    public int HashKeys(String keys) {
        int hash = 0;
        for (int i = 0; i < keys.length(); i++) {
            hash += keys.charAt(i);
        }
        return hash % this.Tamano_arreglo;
    }

    /**
     * Funcion MostrarTitulos
     * 
     * Genera un reporte en texto con los t&iacute;tulos de todos los res&uacute;menes almacenados en la tabla.
     *
     * Este m&eacute;todo realiza un recorrido secuencial (lineal) por todo el arreglo de la tabla hash.
     * En cada posici&oacute;n (bucket), si existe una lista enlazada, extrae los t&iacute;tulos contenidos
     * en ella y los concatena al resultado final.
     * 
     * Las posiciones vac&iacute;as (null) del arreglo son ignoradas mediante un bloque
     * {try-catch}, evitando as&iacute; interrupciones en la ejecuci&oacute;n.
     * 
     *
     * @return Una cadena de texto (String) con todos los títulos separados por saltos de línea.
     * 
     * @author Anthony Goncalves
     */
    public String MostrarTitulos() {
        String recorrido = "";

        for (int i = 0; i < this.Resumenes.length; i++) {
            try {
                recorrido += this.Resumenes[i].MostrarTitulosListaHash() + " ";
            } catch (Exception e) {

            }
        }
        return recorrido;
    }
    
    /** Se recorre la tabla hash y se crea un &aacute;rbol con los autores.
     * 
     * @return 
     */
    public ArbolAVL creaArbolAutores(){
        ArbolAVL aux = new ArbolAVL(true);
        for(ListaHash s: this.Resumenes){
            NodoHash aux2 = s.pFirst;
            while(aux2 != null){
                String [] aux3 = aux2.data.autores.split(",");
                for(String s2: aux3){
                    aux.insertarAutor(s2, aux2.data.titulo);
                }
            }
        }
        return aux;
    }
    
    /** Se recorre la tabla hash y se crea un &aacute;rbol con las palabras claves.
     * 
     * @return 
     */
    public ArbolAVL creaArbolKeywords(){
        ArbolAVL aux = new ArbolAVL(false);
        for(ListaHash s: this.Resumenes){
            NodoHash aux2 = s.pFirst;
            while(aux2 != null){
                String [] aux3 = aux2.data.keywords.split(",");
                for(String s2: aux3){
                    aux.insertarAutor(s2, aux2.data.titulo);
                }
            }
        }
        return aux;
    }
}
