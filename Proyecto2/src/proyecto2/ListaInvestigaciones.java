/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * ListaInvestigaciones es una lista con apuntador al primer y último elemento
 * Sirve para guardar títulos de Investigaciones
 * @author USER
 */
public class ListaInvestigaciones {
    public NodoInvestigacion pfirst;
    public NodoInvestigacion plast;
    
    public ListaInvestigaciones(){
        pfirst = null;
        plast = null;
    }
    
    // Verifica si está vacía la lista
    public boolean esVacio(){
        return pfirst == null;
    }
    
    // Realiza una búsqueda iterativa en la lista
    public boolean Buscar(String elem){
        NodoInvestigacion pNodo = pfirst;
        while (pNodo != null){
            if (pNodo.info.equals(elem)){
                return true;
            }
            pNodo = pNodo.pnext;
        }
        return false;
    }
    
    //Inserta al último elemento de la lista
    public void insertar(String elem){
        if (esVacio()){
            pfirst = new NodoInvestigacion(elem);
            plast = pfirst;
        } else {
            if (!(Buscar(elem))){
                NodoInvestigacion pNodo = new NodoInvestigacion(elem);
                plast.pnext = pNodo;
                plast = pNodo;
            }            
        }
    }
    
    // Cuenta la cantidad de Investigaciones que tengas la lista
    public int contar_Investigaciones(){
        int resultado = 0;
        NodoInvestigacion pNodo = pfirst;
        while(pNodo!= null){
            resultado += 1;
            pNodo = pNodo.pnext;
        }
        return resultado;
    }
    
    public String mostrarfreqkeyw(HashTable algo, String info){
        String texto = "";
        NodoInvestigacion aux = this.pfirst;
        while(aux != null){
            Investigaciones aux2 = algo.BuscarResumen(aux.info);
            String [] aux3 = aux2.keywords.split(",");
            String [] aux4 = aux2.freqkeywords.split(",");
            int cont = 0;
            while(aux3[cont] != info){
                cont += 1;
            }
            texto += "Aparece en: " + aux2.titulo + " " + aux4[cont] + ".\n";
            aux = aux.pnext;
        }
        return texto;
    }
}
