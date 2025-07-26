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
public class Parqueo {

    String[][] g1 = new String[4][5];
    String[][] g2 = new String[5][5];
    String[][] g3 = new String[6][5];

    public Parqueo() {
        llenar(g1, "G1");
        llenar(g2, "G2");
        llenar(g3, "G3");
    }

    public void iniciar() {
        String opcion = "";
        while (!opcion.equals("3")) {
            opcion = JOptionPane.showInputDialog(
                "PARQUEO\n\n" +
                "1. Ver parqueos\n" +
                "2. Reservar espacio\n" +
                "3. Volver"
            );

            if (opcion == null) return;

            if (opcion.equals("1")) {
                ver(g1, "G1");
                ver(g2, "G2");
                ver(g3, "G3");
            } else if (opcion.equals("2")) {
                reservar();
            } else if (!opcion.equals("3")) {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public void llenar(String[][] parqueo, String nombre) {
        for (int i = 0; i < parqueo.length; i++) {
            for (int j = 0; j < parqueo[0].length; j++) {
                parqueo[i][j] = "L";
            }
        }

        // Discapacitados (última fila, 3 columnas)
        int f = parqueo.length - 1;
        parqueo[f][0] = "D";
        parqueo[f][1] = "D";
        parqueo[f][2] = "D";

        // Entrenadores
        if (nombre.equals("G1")) {
            parqueo[0][0] = "E";
            parqueo[3][4] = "E";
        } else if (nombre.equals("G2")) {
            parqueo[1][1] = "E";
            parqueo[4][4] = "E";
        } else if (nombre.equals("G3")) {
            parqueo[2][2] = "E";
            parqueo[5][4] = "E";
        }
    }

    public void ver(String[][] parqueo, String nombre) {
        String texto = "Nivel " + nombre + ":\n";
        texto += "    1  2  3  4  5\n";
        for (int i = 0; i < parqueo.length; i++) {
            texto += (i + 1) + "  ";
            for (int j = 0; j < parqueo[0].length; j++) {
                texto += parqueo[i][j] + "  ";
            }
            texto += "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    public void reservar() {
        String nivel = JOptionPane.showInputDialog("Digite el nivel que quiere ingresar,(1 = G1, 2 = G2, 3 = G3)");

        if (nivel == null) return;

        String[][] parqueo = null;
        if (nivel.equals("1")) {
            parqueo = g1;
        } else if (nivel.equals("2")) {
            parqueo = g2;
        } else if (nivel.equals("3")) {
            parqueo = g3;
        } else {
            JOptionPane.showMessageDialog(null, "Nivel inválido.");
            return;
        }

        ver(parqueo, "Seleccionado");

        String filaStr = JOptionPane.showInputDialog("Digite el número de fila (1 a " + parqueo.length + "):");
        String colStr = JOptionPane.showInputDialog("Digite el número de columna (1 a 5):");

        if (filaStr == null || colStr == null) return;

        int fila = Integer.parseInt(filaStr) - 1;
        int col = Integer.parseInt(colStr) - 1;

        if (fila < 0 || fila >= parqueo.length || col < 0 || col >= parqueo[0].length) {
            JOptionPane.showMessageDialog(null, "Posición inválida.");
            return;
        }

        String espacio = parqueo[fila][col];

        if (espacio.equals("L")) {
            parqueo[fila][col] = "O";
            JOptionPane.showMessageDialog(null, "Se ha hecho la reserva");
        } else {
            JOptionPane.showMessageDialog(null, "No disponible. Es: " + espacio);
        }
    }
}
