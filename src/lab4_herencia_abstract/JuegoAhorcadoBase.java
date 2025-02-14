/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_herencia_abstract;

/**
 *
 * @author Mario
 */
public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
   
     protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;

    public JuegoAhorcadoBase(String palabraSecreta, int intentos) {
        this.palabraSecreta = palabraSecreta;
        this.intentos = intentos;
        this.palabraActual = inicializarPalabraActual();
    }

    private String inicializarPalabraActual() {
        return palabraSecreta.replaceAll(".", "_");
    }

    public abstract void actualizarPalabraActual(char letra);
    public abstract boolean verificarLetra(char letra);
    public abstract boolean hasGanado();
}
