/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
                int valorcaja = 0;
                FileReader archivo_2 = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivo_2);
                String currentLine;
                while((currentLine = lee.readLine())!= null){
                    if(aux == 0){
                        Investigaciones inv = new Investigaciones();
                        valorcaja = algo.hash(currentLine);
                        algo.conjunto[valorcaja] = inv;
                    }
                    if(currentLine.equalsIgnoreCase("autores")){
                        aux = 1;
                    }
                    if(currentLine.equalsIgnoreCase("resumen")){
                        aux = 2;
                    }
                    if((aux == 1) && (!currentLine.equalsIgnoreCase("autores") && (!currentLine.isEmpty()))){
                        if(algo.conjunto[valorcaja].autores.isBlank()){
                            algo.conjunto[valorcaja].autores = currentLine;
                        }
                        algo.conjunto[valorcaja].autores = algo.conjunto[valorcaja].autores + ", " + currentLine;
                    }
                    if((aux == 2) && (!currentLine.equalsIgnoreCase("resumen") && (!currentLine.isEmpty()))){
                        algo.conjunto[valorcaja].resumen = currentLine + " ";
                    }
                    if((currentLine.startsWith("Palabras claves:") && (!currentLine.isEmpty()))){
                        String [] aux2 = currentLine.split("[.\\:]");
                        algo.conjunto[valorcaja].keywords = aux2[1];
                        algo.conjunto[valorcaja].contar_keywords();
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
            for(Investigaciones inv: algo.conjunto){
                escribe.write(inv.mostrar());
            }
            escribe.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }
    }
}

