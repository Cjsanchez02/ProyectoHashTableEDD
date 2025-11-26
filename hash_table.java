package proyecto_estructuras_2;

import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Clase hash_table
 * Implementación de una estructura de datos Hash Table.
 * Esta implementación gestiona las colisiones mediante encadenamiento separado,
 * utilizando un arreglo de listas enlazadas de tipo {ListaHash}. Cada índice del arreglo
 * representa un "bucket"(es decir, guarda una lista) donde se almacenan los resúmenes que comparten el mismo índice hash.
 *
 * @author Anthony Goncalves
 * @version 1.0
 */
public class hash_table {
    protected int Tamano_arreglo;
    protected ListaHash[] Resumenes;

    
    
    
    /**
     * Construtor de la clase hash_table
     * Inicializa una nueva instancia de la tabla Hash con una capacidad definida.
     * Este constructor reserva el espacio en memoria para el arreglo de "buckets",
     * determinando así el tamaño físico de la tabla.

     *Al inicializar el arreglo con {new ListaHash[Tamano_arreglo]},
     * todas las posiciones comienzan siendo {null}. Las listas enlazadas individuales
     * deben instanciarse posteriormente cuando se inserte el primer elemento en cada posición.
     *
     * @param Tamano_arreglo La cantidad total de buckets (casilleros) que tendrá la tabla.
     * Este valor define el rango de índices posibles [0 a Tamano_arreglo - 1].
     * 
     * * @author Anthony Goncalves
     */
    public hash_table(int Tamano_arreglo) {
        this.Tamano_arreglo = Tamano_arreglo;
        this.Resumenes = new ListaHash[Tamano_arreglo];
    }

    
    
    
    /**
     * Agrega un nuevo objeto {Resumen} a la tabla hash.
     * Este método realiza los siguientes pasos:
     * Calcula el índice de almacenamiento (hash) basándose en el título del resumen.
     * Intenta insertar el resumen en la lista (bucket) correspondiente a ese índice.
     * Si el bucket está vacío (es nulo), inicializa una nueva {ListaHash} y luego inserta el elemento.
     *
     * @param resumen El objeto {Resumen} que se desea almacenar.
     * 
     * @author Anthony Goncalves
     */
    public void AgregarResumen(Resumen resumen) {
        int indice = this.Hash(resumen.titulo);
        try {
            this.Resumenes[indice].Insertar(resumen);

        } catch (Exception e) {
            this.Resumenes[indice] = new ListaHash();
            this.Resumenes[indice].Insertar(resumen);
        }

    }

    
    
    
    
    /**
     * Método AgregrarPalabrasClaves
     * 
     * Indexa un resumen en la tabla hash basándose en sus palabras claves.

     * Este método descompone la cadena de palabras claves del resumen (separadas por comas),
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
    public void AgregrarPalabrasClaves(Resumen resumen) {
        String claves[] = resumen.palabras_claves.split(",");
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
     * Función BuscarPalabraClave
     * 
     * Busca y recupera todos los resúmenes asociados a una palabra clave específica.
     * 
     * El funcionamiento es el siguiente:
     * Calcula el índice (hash) de la palabra clave para localizar el "bucket" correcto.
     * Accede a la lista enlazada ({ListaHash}) en esa posición.
     * Delega la búsqueda a la lista para obtener todos los resúmenes que coincidan.
     * 
     * 
     * @param palabra_clave La palabra o término que se desea buscar.
     * @return Un arreglo de objetos {Resumen} asociados a dicha palabra clave.
     * Retornará {null} o un arreglo vacío si no se encuentran coincidencias
     * (dependiendo de la implementación de tu lista).
     * 
     * @author Anthony Goncalves
     */
        public Resumen[] BuscarPalabraClave(String palabra_clave) {
        int indice = this.Hash(palabra_clave);
            
        if (this.Resumenes[indice] == null) {
            return null;
        }

            return this.Resumenes[indice].Buscar_Palabra_Clave_Hash(palabra_clave);

    }
    

        
        
        
    /**
     * Función BuscarResumen
     * 
     * Busca y recupera un {Resumen} específico utilizando su título como clave de búsqueda.
     * 
     * El proceso de búsqueda sigue la lógica de la tabla hash:
     * Calcula el índice hash basándose en el título.
     * Accede al "bucket" correspondiente en el arreglo.
     * Delega la búsqueda a la lista enlazada ({@link ListaHash}) interna.
     * 
     * Esta función maneja las colisiones o ausencias mediante un bloque {try-catch}.
     * Si el resumen no existe o el bucket está vacío, se captura la excepción, se muestra
     * un mensaje visual al usuario y se retorna {null}.
     *
     * @param titulo El título exacto del resumen que se desea buscar.
     * @return El objeto {Resumen} encontrado, o {null} si no existe en la tabla.
     * 
     * @author Anthony Goncalves
     */    
    public Resumen BuscarResumen(String titulo) {
        int indice = this.Hash(titulo);

        try {
            return this.Resumenes[indice].BuscarTitulo(titulo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontró un resumen por ese título");
            return null;
        }
    }
    
    
    
    
    /**
     * Función Hash
     * 
     * 
     * Función de dispersión, calcula el índice para un título dado.
     * 
     * Esta implementación utiliza un algoritmo de Hash Aditivo
     * 
     * Recorre cada carácter del título.
     * Suma los valores numéricos (ASCII/Unicode) de todos los caracteres.
     * Aplica el operador módulo ({@code %}) con el tamaño del arreglo para asegurar
     * que el índice resultante esté dentro de los límites válidos [0 a Tamano_arreglo - 1].
     * 
     *
     * @param titulo La cadena de texto (título) a la cual se le generará el hash.
     * @return Un número entero que representa el índice del arreglo donde se debe almacenar o buscar el resumen.
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
     * Función HashKeys
     * 
     * Función de dispersión (Hash Function) utilizada específicamente para las palabras claves.
     * 
     * Al igual que la función principal, utiliza un algoritmo de Hash Aditivo
     * (suma de valores ASCII) para determinar en qué bucket debe buscarse o almacenarse
     * la referencia asociada a esta palabra clave.
     * 
     *
     * @param keys La palabra clave individual (ej. "tecnología") a hashear.
     * @return El índice calculado dentro del rango válido [0 a Tamano_arreglo - 1].
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
     * Genera un reporte en texto con los títulos de todos los resúmenes almacenados en la tabla.
     *
     * Este método realiza un recorrido secuencial (lineal) por todo el arreglo de la tabla hash.
     * En cada posición (bucket), si existe una lista enlazada, extrae los títulos contenidos
     * en ella y los concatena al resultado final.
     * 
     * Las posiciones vacías (null) del arreglo son ignoradas mediante un bloque
     * {try-catch}, evitando así interrupciones en la ejecución.
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
                recorrido += this.Resumenes[i].Mostrar_Titulos_Lista_Hash() + "\n";
            } catch (Exception e) {

            }
        }
        return recorrido;
    }

}
