/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4_herencia_abstract;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class Lab4_Herencia_Abstract {

    /**
     * @param args the command line arguments
     */
   private static AdminPalabrasSecretas admin = new AdminPalabrasSecretas();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                crearMenuPrincipal();
            }
        });
    }
    
    private static void crearMenuPrincipal() {
        JFrame menuFrame = new JFrame("Menú del Ahorcado");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(300, 200);
        menuFrame.setLayout(new GridLayout(4, 1));
        
        JLabel etiquetaTitulo = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        JButton botonJuegoFijo = new JButton("Juego Fijo");
        JButton botonJuegoAzar = new JButton("Juego Azar");
        JButton botonAgregarPalabras = new JButton("Agregar Palabras Secretas");
        
        botonJuegoFijo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // Ahora se crea sin pasar palabra fija, ya que selecciona una de 4 aleatorias.
                JuegoAhorcado juegoFijo = new JuegoAhorcadoFijo();
                juegoFijo.jugar();
            }
        });
        
        botonJuegoAzar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(admin.getCantidadPalabras() == 0) {
                    JOptionPane.showMessageDialog(null, "No se han agregado palabras. Se usará la palabra predeterminada.");
                }
                JuegoAhorcado juegoAzar = new JuegoAhorcadoAzar(admin);
                juegoAzar.jugar();
            }
        });
        
        botonAgregarPalabras.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String palabra;
                do {
                    palabra = JOptionPane.showInputDialog(menuFrame, "Ingrese una palabra (Cancelar para terminar):");
                    if(palabra != null && !palabra.trim().isEmpty()){
                        admin.agregarPalabra(palabra.trim());
                    }
                } while(palabra != null && !palabra.trim().isEmpty());
            }
        });
        
        menuFrame.add(etiquetaTitulo);
        menuFrame.add(botonJuegoFijo);
        menuFrame.add(botonJuegoAzar);
        menuFrame.add(botonAgregarPalabras);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }
}
