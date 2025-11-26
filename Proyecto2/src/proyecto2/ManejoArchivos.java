/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author elva
 */
public class ManejoArchivos {
    File selectedArchivo;
    
    public ManejoArchivos(){
        this.selectedArchivo = null;
    }
    
    /** Se va a ofrecer una ventana al usuario para que eliga el archivo de texto
     * que va a montar y basandose en este, se va a inicializar el grafo y se
     * va a retornar este mismo.
     * @return Grafo inicializado
     */
    public void subir_archivo(HashTable algo){
        try{
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(null);
            File archivo = file.getSelectedFile();
            this.selectedArchivo = archivo;
            if(archivo != null){
                int aux = 0;
                String tit = "";
                FileReader archivo_2 = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivo_2);
                String currentLine;
                while((currentLine = lee.readLine())!= null){
                    if(aux == 0 && !currentLine.equalsIgnoreCase("autores")){
                        Investigaciones inv = new Investigaciones();
                        inv.titulo = currentLine;
                        algo.AgregarResumen(inv);
                        tit = currentLine;
                    }
                    if(currentLine.equalsIgnoreCase("autores")){
                        aux = 1;
                    }
                    if(currentLine.equalsIgnoreCase("resumen")){
                        aux = 2;
                    }
                    if((aux == 1) && (!currentLine.equalsIgnoreCase("autores") && (!currentLine.isEmpty()))){
                        if(algo.BuscarResumen(tit).autores.isBlank()){
                            algo.BuscarResumen(tit).autores = currentLine;
                        }
                        algo.BuscarResumen(tit).autores = algo.BuscarResumen(tit).autores + ", " + currentLine;
                    }
                    if((aux == 2) && (!currentLine.equalsIgnoreCase("resumen") && (!currentLine.isEmpty()))){
                        algo.BuscarResumen(tit).resumen = currentLine + " ";
                        System.out.println(algo.BuscarResumen(tit).resumen);
                    }
                    if((currentLine.startsWith("Palabras claves:") && (!currentLine.isEmpty()))){
                        String [] aux2 = currentLine.split("[.\\:]");
                        algo.BuscarResumen(tit).keywords = aux2[1];
                        algo.BuscarResumen(tit).contar_keywords();
                        System.out.println(algo.BuscarResumen(tit).resumen);
                        System.out.println("hola");
                        
                        System.out.println(algo.BuscarResumen(tit).mostrarcomp());
                    }
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }
    }
    
    /** Se va a recibir la tabla hash y se va a guardar un archivo con la 
     * informaci&oacute;n de la sesi&oacute;n en la carpeta que el usuario seleccione.
     * @param algo tabla hash modificada
     */
    public void actualizar_archivo(HashTable algo){
        try{
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(null);
            FileWriter archivo2 = new FileWriter(file.getSelectedFile());
            BufferedWriter escribe = new BufferedWriter(archivo2);
            for(ListaHash cosa: algo.Resumenes){
                NodoHash aux = cosa.pFirst;
                while(aux != null){
                escribe.write(aux.data.mostrar() + "\f");
                aux = aux.pNext;
                }
            }
            escribe.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }
    }
    
    /** Se carga un archivo que consista de varia investigaciones, en otras palabras
     * una sesi&oacute;n previa.
     * 
     * @param algo tabla hash
     */
    public void subir_archivocomp(HashTable algo){
        try{
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(null);
            File archivo = file.getSelectedFile();
            this.selectedArchivo = archivo;
            if(archivo != null){
                int aux = 0;
                String tit = "";
                FileReader archivo_2 = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivo_2);
                String currentLine;
                while((currentLine = lee.readLine())!= null){
                    if(aux == 0){
                        Investigaciones inv = new Investigaciones();
                        inv.autores = currentLine;
                        algo.AgregarResumen(inv);
                        tit = currentLine;
                    }
                    if(currentLine.equalsIgnoreCase("autores")){
                        aux = 1;
                    }
                    if(currentLine.equalsIgnoreCase("resumen")){
                        aux = 2;
                    }
                    if((aux == 1) && (!currentLine.equalsIgnoreCase("autores") && (!currentLine.isEmpty()))){
                        if(algo.BuscarResumen(tit).autores.isBlank()){
                            algo.BuscarResumen(tit).autores = currentLine;
                        }
                        algo.BuscarResumen(tit).autores = algo.BuscarResumen(tit).autores + ", " + currentLine;
                    }
                    if((aux == 2) && (!currentLine.equalsIgnoreCase("resumen") && (!currentLine.isEmpty()))){
                        algo.BuscarResumen(tit).resumen = currentLine + " ";
                    }
                    if((currentLine.startsWith("Palabras claves:") && (!currentLine.isEmpty()))){
                        String [] aux2 = currentLine.split("[.\\:]");
                        algo.BuscarResumen(tit).keywords = aux2[1];
                        algo.BuscarResumen(tit).contar_keywords();
                        aux = 0;
                    }
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }
    }
}

