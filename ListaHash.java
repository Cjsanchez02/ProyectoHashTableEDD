/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuras_2;

/**
 *
 * @author Antho
 */
public class ListaHash {

    private NodoHash pFirst;
    private int Longitud;

    public ListaHash() {
        this.pFirst = null;
        this.Longitud = 0;
    }

    public int Tamano() {
        return this.Longitud;
    }

    public boolean EsVacio() {
        return pFirst == null;
    }

    public NodoHash Primero() {
        return this.pFirst;
    }

    public NodoHash Ultimo() {
        return null;
    }

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
    
    public Resumen[] Buscar_Palabra_Clave_Hash(String palabra_clave) {
        Resumen arreglo_resumenes[] = new Resumen [20];
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
