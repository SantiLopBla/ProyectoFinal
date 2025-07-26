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
public class ClasesGrupales {

    static ClaseGrupal[] clases = new ClaseGrupal[6]; //Son 3 en la mañana y 3 en la noche
    static boolean precargado = false;

    static class ClaseGrupal {

        String nombre, horario;
        int capacidad, cupos;

        public ClaseGrupal(String nombre, String horario, int capacidad) {
            this.nombre = nombre;
            this.horario = horario;
            this.capacidad = capacidad;
            this.cupos = capacidad;
        }

        public boolean reservar() {
            if (cupos > 0) {
                cupos--;
                return true;
            }
            return false;
        }

        public void modificar(String nombre, String horario, int capacidad) {
            int dif = capacidad - this.capacidad;
            this.nombre = nombre;
            this.horario = horario;
            this.capacidad = capacidad;
            this.cupos += dif;
            if (cupos > capacidad) {
                cupos = capacidad;
            }
        }

        public String resumen() {
            return nombre + " (" + horario + ") - Cupos: " + cupos;
        }
    }

    static void precargar() {
        clases[0] = new ClaseGrupal("Yoga", "mañana", 10);
        clases[1] = new ClaseGrupal("Crossfit", "mañana", 10);
        clases[2] = new ClaseGrupal("Funcional", "mañana", 10);
        clases[3] = new ClaseGrupal("Pilates", "noche", 10);
        clases[4] = new ClaseGrupal("Zumba", "noche", 10);
        clases[5] = new ClaseGrupal("Cardio Dance", "noche", 10);
    }

    public static void iniciar() {
        if (!precargado) {
            precargar();
            precargado = true;
        }

        String op;
        do {
            op = JOptionPane.showInputDialog("Clases Grupales: \n1) Ver las clases\n2) Reservar clase\n3) Modificar clase\n4) Volver");
            if (op == null || op.equals("4")) {
                break;
            }

            switch (op) {
                case "1":
                    ver();
                    break;
                case "2":
                    reservar();
                    break;
                case "3":
                    modificar();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (true);
    }

    static void ver() {
        String msg = "Clases disponibles:\n";
        for (int i = 0; i < clases.length; i++) {
            msg += i + ". " + clases[i].resumen() + "\n";
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    static void reservar() {
        ver();
        String id = JOptionPane.showInputDialog("ID de socio:");
        int num = Integer.parseInt(JOptionPane.showInputDialog("Clase a reservar:"));
        if (num >= 0 && num < clases.length) {
            if (clases[num].reservar()) {
                JOptionPane.showMessageDialog(null, "Reserva hecha para socio " + id);
            } else {
                JOptionPane.showMessageDialog(null, "Clase llena.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Clase inválida.");
        }
    }

    static void modificar() {
        ver();
        int num = Integer.parseInt(JOptionPane.showInputDialog("Clase a modificar:"));
        if (num >= 0 && num < clases.length) {
            String n = JOptionPane.showInputDialog("Nombre:");
            String h = JOptionPane.showInputDialog("Horario:");
            int c = Integer.parseInt(JOptionPane.showInputDialog("Capacidad:"));
            clases[num].modificar(n, h, c);
            JOptionPane.showMessageDialog(null, "Modificado.");
        } else {
            JOptionPane.showMessageDialog(null, "Clase inválida.");
        }
    }
}
