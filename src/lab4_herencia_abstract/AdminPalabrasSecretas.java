/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_herencia_abstract;

import java.util.Random;

/**
 *
 * @author Mario
 */
public class AdminPalabrasSecretas {
     private String[] palabras;
    private int count;
    private Random random;
    
    public AdminPalabrasSecretas() {
        palabras = new String[10];
        count = 0;
        random = new Random();
    }
    
    public void agregarPalabra(String palabra) {
        if(palabra != null && !palabra.isEmpty()) {
            if(count >= palabras.length) {
                String[] nuevo = new String[palabras.length * 2];
                for(int i = 0; i < palabras.length; i++){
                    nuevo[i] = palabras[i];
                }
                palabras = nuevo;
            }
            palabras[count] = palabra;
            count++;
        }
    }
    
    public String seleccionarPalabraAlAzar() {
        if(count == 0) {
            return "default";
        }
        int indice = random.nextInt(count);
        return palabras[indice];
    }
    
    public int getCantidadPalabras() {
        return count;
    }
}
