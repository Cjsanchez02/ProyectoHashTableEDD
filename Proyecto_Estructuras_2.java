/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_estructuras_2;

/**
 *
 * @author Antho
 */
public class Proyecto_Estructuras_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Resumen resumen1 = new Resumen("Andres", "autores", "cuerpo", "carros,motos,pan");
        Resumen resumen2 = new Resumen("David", "autor", "c", "palabras");
        Resumen resumen3 = new Resumen("Juan", "autores", "cuerpo", "pan,vasos,lapiz");
        Resumen resumen4 = new Resumen("Carlos", "autor", "c", "silla,vasos");
        Resumen resumen5 = new Resumen("Pepe", "autores", "cuerpo", "canilla ");
        Resumen resumen6 = new Resumen("Jose", "autor", "c", "carros,vasos,sofa,pan");

        //
        hash_table hash = new hash_table(20);
        hash_table keys = new hash_table(60);

        hash.AgregarResumen(resumen1);
        hash.AgregarResumen(resumen2);
        hash.AgregarResumen(resumen3);
        hash.AgregarResumen(resumen4);
        hash.AgregarResumen(resumen5);
        hash.AgregarResumen(resumen6);

        keys.AgregrarPalabrasClaves(resumen1);
        keys.AgregrarPalabrasClaves(resumen2);
        keys.AgregrarPalabrasClaves(resumen3);
        keys.AgregrarPalabrasClaves(resumen4);
        keys.AgregrarPalabrasClaves(resumen5);
        keys.AgregrarPalabrasClaves(resumen6);

        //System.out.println(keys.MostrarTitulos());
        //Resumen respuesta = hash.BuscarResumen("carros");
//        System.out.println(respuesta.titulo);
//        System.out.println(respuesta.autores);
//        System.out.println(respuesta.cuerpo);
//        System.out.println(respuesta.palabras_claves);
        //Resumen[] claves = keys.BuscarPalabraClave("pan");


        Resumen[] claves = keys.BuscarPalabraClave("pas");
        if (claves != null) {
            for (int i = 0; i < claves.length; i++) {
                if (claves[i] != null) {
                    System.out.println(claves[i].titulo);
                }
            }
        } else {
            System.out.println("La palabra no fue encontrada.");
        }

    }

}
