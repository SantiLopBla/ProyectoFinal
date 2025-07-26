/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prof;

import javax.swing.JOptionPane;

/**
 *
 * @author emmac
 */
public class Socio {

    private String id; //guarda el id del socio
    private boolean activo; //indica si el socio esta activo

    public Socio(String id) {
        this.id = id;
        this.activo = true;
    }

    public String getId() {
        return id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public static Socio registrarSocio() { //metodo para registrar nuevo socio
        while (true) {
            String id = JOptionPane.showInputDialog("Ingrese su ID de socio (ejemplo: S001):"); //pide el id al usuario
            if (id == null || id.trim().isEmpty()) { //Limpia lo ingresado 
                JOptionPane.showMessageDialog(null, "ID invalido, intente de nuevo."); //si esta vacio o nulo, muestra mensaje
                continue; //vuelve a pedir el id
            }
            return new Socio(id.trim()); //crea y devuelve el nuevo socio
        }
    }
}
