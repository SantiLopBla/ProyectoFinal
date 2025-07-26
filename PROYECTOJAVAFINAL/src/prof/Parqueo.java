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
public class Parqueo {

    private char[][] planta1 = new char[4][5]; //planta 1 con 4 filas y 5 columnas
    private char[][] planta2 = new char[5][5]; //planta 2 con 5 filas y 5 columnas
    private char[][] planta3 = new char[6][5]; //planta 3 con 6 filas y 5 columnas

    public Parqueo() { //constructor que inicializa las plantas
        inicializarPlantas(); //llama metodo para llenar cada planta
    }

    private void inicializarPlantas() { //inicializa cada planta con espacios especiales
        inicializarPlanta(planta1, 3, 2); //planta 1 con 3 espacios para discapacitados y 2 para entrenadores
        inicializarPlanta(planta2, 3, 2); //planta 2 igual
        inicializarPlanta(planta3, 3, 2); //planta 3 igual
    }

    private void inicializarPlanta(char[][] planta, int discapacitados, int entrenadores) { //llena la matriz
        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                planta[i][j] = 'L'; //l = libre
            }
        }
        for (int i = 0; i < discapacitados; i++) {
            planta[0][i] = 'D'; //d = espacio para discapacitados
        }
        for (int i = 0; i < entrenadores; i++) {
            planta[0][planta[0].length - 1 - i] = 'E'; //e = espacio para entrenador senior
        }
    }

    public void menuParqueo(Socio socio) { //menu principal del modulo parqueo
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                    "Parqueo - Menú\n"
                    + "1. Ver estado del parqueo\n"
                    + "2. Reservar espacio\n"
                    + "3. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) {
                break; //salir del menu
            }
            switch (opcion) {
                case "1":
                    mostrarParqueo(); //ver parqueo
                    break;
                case "2":
                    reservarEspacio(socio); //reservar espacio
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida"); //opcion no valida
            }
        } while (true);
    }

    public void mostrarParqueo() { //muestra todas las plantas
        String mensaje = "Estado del parqueo:\n\n";
        mensaje += "Planta 1:\n" + mostrarPlanta(planta1);
        mensaje += "Planta 2:\n" + mostrarPlanta(planta2);
        mensaje += "Planta 3:\n" + mostrarPlanta(planta3);
        JOptionPane.showMessageDialog(null, mensaje); //muestra el resultado final
    }

    private String mostrarPlanta(char[][] planta) { //convierte la matriz a texto
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                sb.append("[").append(planta[i][j]).append("]");
            }
            sb.append("\n");
        }
        return sb.toString(); //devuelve todo el formato
    }

    public void reservarEspacio(Socio socio) { //reserva un espacio si esta activo
        if (!socio.isActivo()) {
            JOptionPane.showMessageDialog(null, "Su membresía no está activa."); //no puede reservar
            return;
        }

        int plantaSeleccionada = -1;
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "Ingrese la planta para reservar espacio (1, 2 o 3):"
            );
            if (input == null) {
                return; //cancela
            }

            try {
                plantaSeleccionada = Integer.parseInt(input); //convierte a numero
                if (plantaSeleccionada < 1 || plantaSeleccionada > 3) {
                    JOptionPane.showMessageDialog(null, "Planta inválida, intente de nuevo."); //fuera de rango
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, ingrese un número."); //no digito un numero
            }
        }

        char[][] planta = plantaSeleccionada == 1 ? planta1
                : plantaSeleccionada == 2 ? planta2 : planta3; //selecciona la matriz segun la planta

        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                if (planta[i][j] == 'L') {
                    planta[i][j] = 'O'; //o = ocupado
                    JOptionPane.showMessageDialog(null, "Espacio reservado en planta " + plantaSeleccionada + " posición [" + i + "," + j + "]");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No hay espacios libres en esta planta.");
    }

    public void liberarEspacio() { //libera un espacio reservado
        int plantaSeleccionada = -1;
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese la planta (1, 2 o 3) para liberar espacio:");
            if (input == null) {
                return; //cancela
            }
            try {
                plantaSeleccionada = Integer.parseInt(input);
                if (plantaSeleccionada < 1 || plantaSeleccionada > 3) {
                    JOptionPane.showMessageDialog(null, "Planta inválida, intente de nuevo."); //fuera de rango
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, ingrese un número."); //no digito un numero
            }
        }

        char[][] planta = plantaSeleccionada == 1 ? planta1
                : plantaSeleccionada == 2 ? planta2 : planta3; //selecciona la planta
        JOptionPane.showMessageDialog(null, mostrarPlanta(planta)); //muestra la planta seleccionada
        String posicionInput = JOptionPane.showInputDialog("Ingrese la posición a liberar (fila,columna) ej: 0,3");
        if (posicionInput == null) {
            return; //cancela
        }

        try {
            String[] parts = posicionInput.split(","); //separa fila y columna
            int fila = Integer.parseInt(parts[0].trim());
            int columna = Integer.parseInt(parts[1].trim());

            if (fila < 0 || fila >= planta.length || columna < 0 || columna >= planta[0].length) {
                JOptionPane.showMessageDialog(null, "Posición inválida."); //fuera de limites
                return;
            }

            if (planta[fila][columna] == 'O') {
                planta[fila][columna] = 'L'; //lo cambia a libre
                JOptionPane.showMessageDialog(null, "Espacio liberado correctamente."); //exito
            } else {
                JOptionPane.showMessageDialog(null, "El espacio no está ocupado o no puede ser liberado."); //no estaba ocupado
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato inválido."); //fallo al interpretar la entrada
        }
    }
}
