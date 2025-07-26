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
public class SalaDePesas {

    private String[] idsSocios = new String[75]; //arreglo para guardar ids de socios
    private boolean[] reservas = new boolean[75]; //arreglo que indica si hay reserva
    private int contadorReservas = 0; //lleva la cuenta de las reservas hechas

    public void menuSala(Socio socio) { //menu principal de sala de pesas
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Sala de Pesas - Menú\n"
                    + "1) Reservar espacio\n"
                    + "2) Ver reservas actuales\n"
                    + "3) Liberar reserva\n"
                    + "4) Volver al menú principal"
            );
            if (opcion == null || opcion.equals("4")) {
                break; //salir del menu
            }

            switch (opcion) {
                case "1":
                    reservar(socio); //hace una reserva
                    break;
                case "2":
                    mostrarReservasSala(); //muestra las reservas actuales
                    break;
                case "3":
                    liberarReserva(); //libera una reserva por id
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida"); //opcion no valida
            }
        }
    }

    private void reservar(Socio socio) { //reserva espacio para socio
        if (contadorReservas >= idsSocios.length) {
            JOptionPane.showMessageDialog(null, "La sala de pesas está llena."); //no hay espacio
            return;
        }
        idsSocios[contadorReservas] = socio.getId(); //guarda id del socio
        reservas[contadorReservas] = true; //marca que hay reserva
        contadorReservas++; //aumenta el contador
        JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId());
    }

    public void mostrarReservasSala() { //muestra todas las reservas
        if (contadorReservas == 0) {
            JOptionPane.showMessageDialog(null, "No se han hecho reservas.");
            return;
        }
        StringBuilder sb = new StringBuilder("Reservas actuales en Sala de Pesas:\n");
        for (int i = 0; i < contadorReservas; i++) {
            sb.append("- ").append(idsSocios[i]).append("\n"); //agrega id a la lista
        }
        JOptionPane.showMessageDialog(null, sb.toString()); //muestra la lista
    }

    public void liberarReserva() { //libera una reserva por id
        if (contadorReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para liberar.");
            return;
        }
        String id = JOptionPane.showInputDialog("Ingrese ID para liberar reserva:");
        if (id == null) {
            return; //cancelado
        }

        boolean encontrado = false; //variable para saber si se encontro el id
        for (int i = 0; i < contadorReservas; i++) {
            if (idsSocios[i].equals(id)) {
                idsSocios[i] = idsSocios[contadorReservas - 1]; //mueve el ultimo al lugar borrado
                reservas[i] = reservas[contadorReservas - 1];
                idsSocios[contadorReservas - 1] = null; //borra el ultimo
                reservas[contadorReservas - 1] = false;
                contadorReservas--; //reduce el contador
                encontrado = true; //se encontro el id
                break;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Reserva liberada correctamente."); //liberacion exitosa
        } else {
            JOptionPane.showMessageDialog(null, "ID no encontrado."); //no existe ese id
        }
    }
}
