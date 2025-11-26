/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import javax.swing.JOptionPane;

/**
 *
 * @author Antho
 */
public class HashTable {

    protected int Tamano_arreglo;
    protected ListaHash[] Resumenes;

    public HashTable(int Tamano_arreglo) {
        this.Tamano_arreglo = Tamano_arreglo;
        this.Resumenes = new ListaHash[Tamano_arreglo];
    }

    public void AgregarResumen(Investigaciones resumen) {
        int indice = this.Hash(resumen.titulo);
        try {
            this.Resumenes[indice].Insertar(resumen);

        } catch (Exception e) {
            this.Resumenes[indice] = new ListaHash();
            this.Resumenes[indice].Insertar(resumen);
        }

    }

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
    
        public Investigaciones[] BuscarPalabraClave(String palabra_clave) {
        int indice = this.Hash(palabra_clave);
        try {
            return this.Resumenes[indice].BuscarPalabraClaveHash(palabra_clave);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontrÃ³ un resumen por ese tÃ­tulo");
            return null;
        }
    }
    

    public Investigaciones BuscarResumen(String titulo) {
        int indice = this.Hash(titulo);

        try {
            return this.Resumenes[indice].BuscarTitulo(titulo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontrÃ³ un resumen por ese tÃ­tulo");
            return null;
        }
    }

    public int Hash(String titulo) {
        int hash = 0;
        for (int i = 0; i < titulo.length(); i++) {
            hash += titulo.charAt(i);
        }
        return hash % this.Tamano_arreglo;
    }

    public int HashKeys(String keys) {
        int hash = 0;
        for (int i = 0; i < keys.length(); i++) {
            hash += keys.charAt(i);
        }
        return hash % this.Tamano_arreglo;
    }

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
