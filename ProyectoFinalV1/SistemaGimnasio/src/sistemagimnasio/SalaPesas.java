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
public class SalaPesas {

    String[] listado = new String[50];
    int contador = 0;

    public SalaPesas() {
    }

    public void iniciar() {
        String opcion;

        do {
            opcion = JOptionPane.showInputDialog("SALA DE PESAS\n\n1. Registrar ingreso\n2. Ver registros\n3. Volver");

            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    if (contador < listado.length) {
                        listado[contador] = JOptionPane.showInputDialog("Digite el ID del socio:");
                        contador++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Límite de registros alcanzado.");
                    }
                    break;
                case "2":
                    String texto = "IDs registrados:\n";
                    for (int i = 0; i < contador; i++) {
                        texto += (i + 1) + ". " + listado[i] + "\n";
                    }
                    JOptionPane.showMessageDialog(null, texto);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (true);
    }
}
