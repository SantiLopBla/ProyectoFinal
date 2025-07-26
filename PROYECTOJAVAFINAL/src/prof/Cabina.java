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
public class Cabina {

    int numero = 1; //número de cabina
    private String[] horas = {"9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"}; //horarios disponibles
    private String[] reservas = new String[horas.length]; //arreglo para guardar quien reservo cada hora

    public Cabina() { //constructor de la clase
        for (int i = 0; i < reservas.length; i++) {
            reservas[i] = ""; //inicializa todas las reservas como vacías
        }
    }

    public void menuCabina(Socio socio) { //menú principal de la cabina
        while (true) { //se repite hasta que el usuario decida salir
            String opcion = JOptionPane.showInputDialog(
                    "Cabina - Menú\n"
                    + "1) Ver horarios disponibles\n"
                    + "2) Reservar horario\n"
                    + "3) Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) { //si elige 3 o cancela, se sale del menú
                break;
            }

            switch (opcion) { //elige qué hacer según la opción
                case "1":
                    mostrarHorarios(); //muestra horarios disponibles y ocupados
                    break;
                case "2":
                    reservarHorario(socio); //para reservar una hora
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida"); //mensaje si pone algo incorrecto
            }
        }
    }

    private void mostrarHorarios() { //muestra todos los horarios con su estado
        StringBuilder sb = new StringBuilder("Horarios de Cabina " + numero + ":\n"); //mensaje para mostrar
        int disponibles = 0; //contador de espacios libres
        for (int i = 0; i < horas.length; i++) {
            if (reservas[i].isEmpty()) {
                sb.append(horas[i]).append(" - Libre\n"); //si está libre, lo muestra como tal
                disponibles++;
            } else {
                sb.append(horas[i]).append(" - Reservadp por ").append(reservas[i]).append("\n"); //muestra quién reservó
            }
        }
        sb.append("\nTotal horarios libres: ").append(disponibles); //agrega el total de libres al mensaje
        JOptionPane.showMessageDialog(null, sb.toString()); //muestra todo
    }

    private void reservarHorario(Socio socio) { //reserva una hora si esta libre
        String hora = JOptionPane.showInputDialog("Ingrese hora a reservar (ejemplo: 9am, 2pm):"); //pide la hora
        if (hora == null) {
            return; //si cancela, no hace nada
        }
        hora = hora.trim().toLowerCase(); //quita espacios y pasa a minúscula

        int pos = -1; //posicion donde está esa hora
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equalsIgnoreCase(hora)) {
                pos = i; //guarda la posición de la hora
                break;
            }
        }

        if (pos == -1) { //si no se encuentra la hora
            JOptionPane.showMessageDialog(null, "Hora inválida.");
            return;
        }

        if (reservas[pos].isEmpty()) { //si no esta reservada
            reservas[pos] = socio.getId(); //guarda el ID del socio
            JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId() + " a las " + horas[pos]);
        } else {
            JOptionPane.showMessageDialog(null, "Hora ya reservada."); //si ya estaba reservada
        }
    }

    public void mostrarResumenReservas() { //muestra todas las reservas hechas
        StringBuilder sb = new StringBuilder("Resumen de reservas cabina " + numero + ":\n"); //mensaje
        boolean alguna = false; //sirve para saber si hay reservas
        for (int i = 0; i < horas.length; i++) {
            if (!reservas[i].isEmpty()) {
                sb.append(horas[i]).append(" - ").append(reservas[i]).append("\n"); //muestra hora y quién reservo
                alguna = true;
            }
        }
        if (!alguna) {
            sb.append("No hay reservas.\n"); //si no hay reservas
        }
        JOptionPane.showMessageDialog(null, sb.toString()); //muestra el resumen
    }

    public void liberarReserva() { //permite cancelar una reserva
        String hora = JOptionPane.showInputDialog("Ingrese la hora para liberar reserva (ejemplo: 9am):"); //pide la hora
        if (hora == null) {
            return; //si cancela
        }
        hora = hora.trim().toLowerCase(); //limpia el texto

        int pos = -1; //busca la posición de la hora
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equalsIgnoreCase(hora)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Hora inválida."); //si no la encuentra
            return;
        }

        if (!reservas[pos].isEmpty()) { //si hay una reserva
            reservas[pos] = ""; //la borra
            JOptionPane.showMessageDialog(null, "Reserva liberada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay reserva en esa hora."); //si ya estaba libre
        }
    }
}
