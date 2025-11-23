/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Antho
 */
public class ListaHash {
    NodoHash pFirst;
    int longitud;
    
    public ListaHash(){
        this.longitud = 0;
        this.pFirst = null;
    }
    
    public boolean EsVacio(){
        return pFirst == null;
    }
    
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
