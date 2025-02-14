/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_herencia_abstract;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Maria Gabriela
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    private JFrame frame;
    private JLabel etiquetaPalabra;
    private JLabel etiquetaIntentos;
    private JTextField campoTexto;
    private JButton botonEnviar;
    private JLabel etiquetaMensaje;
    
    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin) {
        palabraSecreta = admin.seleccionarPalabraAlAzar().toLowerCase();
        inicializarPalabraSecreta();
    }
    
    @Override
    public void inicializarPalabraSecreta() {
        StringBuilder datos = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if(Character.isLetter(palabraSecreta.charAt(i))) {
                datos.append("_");
            } else {
                datos.append(palabraSecreta.charAt(i));
            }
        }
        palabraActual = datos.toString();
    }
    
    @Override
    public void jugar() {
        frame = new JFrame("Ahorcado Azar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,200);
        frame.setLayout(new GridLayout(5,1));
        etiquetaPalabra = new JLabel("Palabra: " + palabraActual, SwingConstants.CENTER);
        etiquetaIntentos = new JLabel("Intentos restantes: " + intentos, SwingConstants.CENTER);
        campoTexto = new JTextField();
        botonEnviar = new JButton("Enviar");
        etiquetaMensaje = new JLabel("", SwingConstants.CENTER);
        
        botonEnviar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String entrada = campoTexto.getText().toLowerCase();
                if(entrada.length() > 0) {
                    char letra = entrada.charAt(0);
                    if(verificarLetra(letra)) {
                        actualizarPalabraActual(letra);
                        etiquetaMensaje.setText("La letra ingresada es correcta");
                    } else {
                        intentos--;
                        etiquetaMensaje.setText("La letra ingresada no es correcta");
                    }
                    etiquetaPalabra.setText("Palabra: " + palabraActual);
                    etiquetaIntentos.setText("Intentos restantes: " + intentos);
                    campoTexto.setText("");
                    if(hasGanado()) {
                        etiquetaMensaje.setText("Felicidades, la palabra era: " + palabraSecreta);
                        botonEnviar.setEnabled(false);
                    } else if(intentos == 0) {
                        etiquetaMensaje.setText(" Has perdido, la palabra era: " + palabraSecreta);
                        botonEnviar.setEnabled(false);
                    }
                }
            }
        });
        
        frame.add(etiquetaPalabra);
        frame.add(etiquetaIntentos);
        frame.add(campoTexto);
        frame.add(botonEnviar);
        frame.add(etiquetaMensaje);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actualizarPalabraActual(char letra) {
        StringBuilder acumular = new StringBuilder();
        for (int index = 0; index < palabraSecreta.length(); index++) {
            if(palabraSecreta.charAt(index) == letra) {
                acumular.append(letra);
            } else {
                acumular.append(palabraActual.charAt(index));
            }
        }
        palabraActual = acumular.toString();
    }
    
    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }
    
    @Override
    public boolean hasGanado() {
        return !palabraActual.contains("_");
    }






}
