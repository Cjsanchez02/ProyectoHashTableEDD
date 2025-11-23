package proyecto_estructuras_2;

import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Antho
 */
public class hash_table {

    protected int Tamano_arreglo;
    protected ListaHash[] Resumenes;

    public hash_table(int Tamano_arreglo) {
        this.Tamano_arreglo = Tamano_arreglo;
        this.Resumenes = new ListaHash[Tamano_arreglo];
    }

    public void AgregarResumen(Resumen resumen) {
        int indice = this.Hash(resumen.titulo);
        try {
            this.Resumenes[indice].Insertar(resumen);

        } catch (Exception e) {
            this.Resumenes[indice] = new ListaHash();
            this.Resumenes[indice].Insertar(resumen);
        }

    }

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
    
        public Resumen[] BuscarPalabraClave(String palabra_clave) {
        int indice = this.Hash(palabra_clave);

//        try {
            return this.Resumenes[indice].Buscar_Palabra_Clave_Hash(palabra_clave);
//
//        } //catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se encontró un resumen por ese título");
            //return null;
        //}
    }
    

    public Resumen BuscarResumen(String titulo) {
        int indice = this.Hash(titulo);

        try {
            return this.Resumenes[indice].BuscarTitulo(titulo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontró un resumen por ese título");
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
                recorrido += this.Resumenes[i].Mostrar_Titulos_Lista_Hash() + "\n";
            } catch (Exception e) {

            }
        }
        return recorrido;
    }

}
