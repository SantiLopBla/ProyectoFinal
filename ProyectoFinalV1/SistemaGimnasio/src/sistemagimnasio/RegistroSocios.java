/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagimnasio;

import javax.swing.JOptionPane;

/**
 *
 * @author sanlo
 */
public class RegistroSocios {
    private Socio[] socios;
    private int cantidad;

    public RegistroSocios() {
        socios = new Socio[75]; // hasta 75 socios máximo
        cantidad = 0;
    }

    public void iniciar() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                "Registro de socios: \n\n" +
                "1) Agregar socio\n" +
                "2) Ver socios registrados\n" +
                "3) Volver al menú principal"
            );

            if (opcion == null || opcion.equals("3")) break;

            switch (opcion) {
                case "1":
                    registrarSocio();
                    break;
                case "2":
                    mostrarSocios();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (true);
    }

    private void registrarSocio() {
        if (cantidad >= socios.length) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más socios.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del socio:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre no válido.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿La membresía está activa?");
        boolean activa = (confirmacion == JOptionPane.YES_OPTION);

        socios[cantidad] = new Socio(cantidad, nombre.trim(), activa);
        JOptionPane.showMessageDialog(null, "Socio registrado con ID: " + cantidad);
        cantidad++;
    }

    private void mostrarSocios() {
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(null, "No hay socios registrados.");
            return;
        }

        String lista = "Socios Registrados:\n";
        for (int i = 0; i < cantidad; i++) {
            Socio s = socios[i];
            lista += "ID: " + s.id + " - " + s.nombre + " - " +
                     (s.membresiaActiva ? "Activo" : "Inactivo") + "\n";
        }
        JOptionPane.showMessageDialog(null, lista);
    }

    // Devuelve el arreglo si otras clases lo necesitan
    public Socio[] getSocios() {
        return socios;
    }
}
