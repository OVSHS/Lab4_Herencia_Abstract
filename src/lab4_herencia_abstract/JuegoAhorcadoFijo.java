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
 * @author Usuario
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private JFrame frame;
    private JLabel labelPalabra;
    private JLabel labelIntentos;
    private JTextField textField;
    private JButton buttonEnviar;
    private JLabel labelMensaje;

    public JuegoAhorcadoFijo(String palabraFija) {
        palabraSecreta = palabraFija.toLowerCase();
        inicializarPalabraSecreta();
    }

    @Override
    public void inicializarPalabraSecreta() {
        StringBuilder datos = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (Character.isLetter(palabraSecreta.charAt(i))) {
                datos.append("_");
            } else {
                datos.append(palabraSecreta.charAt(i));
            }
        }
        palabraActual = datos.toString();
    }

    @Override
    public void jugar() {
        frame = new JFrame("Ahorcado Fijo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1));
        labelPalabra = new JLabel("Palabra: " + palabraActual, SwingConstants.CENTER);
        labelIntentos = new JLabel("Intentos restantes: " + intentos, SwingConstants.CENTER);
        textField = new JTextField();
        buttonEnviar = new JButton("Enviar");
        labelMensaje = new JLabel("", SwingConstants.CENTER);
        buttonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String entrada = textField.getText().toLowerCase();
                if (entrada.length() > 0) {
                    char letra = entrada.charAt(0);
                    if (verificarLetra(letra)) {
                        actualizarPalabraActual(letra);
                        labelMensaje.setText("Letra correcta");
                    } else {
                        intentos--;
                        labelMensaje.setText("Letra incorrecta");
                    }
                    labelPalabra.setText("Palabra: " + palabraActual);
                    labelIntentos.setText("Intentos restantes: " + intentos);
                    textField.setText("");
                    if (hasGanado()) {
                        labelMensaje.setText("Ganaste la palabra era: " + palabraSecreta);
                        buttonEnviar.setEnabled(false);
                    } else if (intentos == 0) {
                        labelMensaje.setText("Perdiste la palabra era: " + palabraSecreta);
                        buttonEnviar.setEnabled(false);
                    }
                }
            }
        });
        frame.add(labelPalabra);
        frame.add(labelIntentos);
        frame.add(textField);
        frame.add(buttonEnviar);
        frame.add(labelMensaje);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        StringBuilder datos = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                datos.append(letra);
            } else {
                datos.append(palabraActual.charAt(i));
            }
        }
        palabraActual = datos.toString();
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
