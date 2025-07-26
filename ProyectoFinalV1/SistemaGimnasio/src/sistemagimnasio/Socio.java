/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagimnasio;

/**
 *
 * @author sanlo
 */
public class Socio {

    int id;
    String nombre;
    boolean membresiaActiva;

    public Socio(int id, String nombre, boolean membresiaActiva) {
        this.id = id;
        this.nombre = nombre;
        this.membresiaActiva = membresiaActiva;
    }

    public Socio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isMembresiaActiva() {
        return membresiaActiva;
    }

    public void setMembresiaActiva(boolean membresiaActiva) {
        this.membresiaActiva = membresiaActiva;
    }

}
