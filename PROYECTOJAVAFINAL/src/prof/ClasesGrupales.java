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
public class ClasesGrupales {

    private static ClaseGrupal[] clases = new ClaseGrupal[6]; //arreglo con 6 clases grupales, las que luego salen precargadas
    private static boolean precargado = false; //para saber si ya se cargaron las clases

    static class ClaseGrupal { //clase interna para representar una clase grupal

        String nombre;
        String horario;
        int capacidadMaxima;
        int cuposDisponibles;

        public ClaseGrupal(String nombre, String horario, int capacidadMaxima) { //constructor
            this.nombre = nombre;
            this.horario = horario;
            this.capacidadMaxima = capacidadMaxima;
            this.cuposDisponibles = capacidadMaxima;
        }

        public boolean reservar() { //metodo para reservar un espacio
            if (cuposDisponibles > 0) { //si aun hay espacio
                cuposDisponibles--; //reduce uno
                return true; //reserva exitosa
            }
            return false; //no se pudo reservar
        }

        public void modificar(String nuevoNombre, String nuevoHorario, int nuevaCapacidad) { //modifica clase
            int diferencia = nuevaCapacidad - this.capacidadMaxima; //ajusta los cupos disponibles
            this.nombre = nuevoNombre; //actualiza el nombre
            this.horario = nuevoHorario; //actualiza el horario
            this.capacidadMaxima = nuevaCapacidad; //actualiza la capacidad
            this.cuposDisponibles += diferencia; //ajusta los cupos disponibles
        }

        public String resumen() { //un return de info de la clase en texto
            return nombre + " (" + horario + ") - Cupos disponibles: " + cuposDisponibles;
        }
    }

    public void menuClases(Socio socio) { //menu principal para clases grupales
        if (!precargado) { //si no se han cargado las clases aun
            precargarClases(); //carga las clases por defecto
            precargado = true; //marca como ya cargadas
        }
        while (true) { //menu que se repite hasta que el usuario salga
            String opcion = JOptionPane.showInputDialog(
                    "Clases Grupales - Menú\n"
                    + "1) Ver clases disponibles\n"
                    + "2) Reservar clase\n"
                    + "3) Modificar clase\n"
                    + "4) Volver al menú principal"
            );
            if (opcion == null || opcion.equals("4")) { //salir del menu
                break;
            }

            switch (opcion) { //switch para elegir algo del submenu
                case "1":
                    mostrarClases(); //muestra las clases
                    break;
                case "2":
                    reservarClase(socio); //reservar clases
                    break;
                case "3":
                    modificarClase(); //modificar clases
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida"); //por si digita algo mal
            }
        }
    }

    private void precargarClases() { //carga las clases por defecto
        clases[0] = new ClaseGrupal("Yoga", "mañana", 10);
        clases[1] = new ClaseGrupal("Crossfit", "mañana", 10);
        clases[2] = new ClaseGrupal("Funcional", "mañana", 10);
        clases[3] = new ClaseGrupal("Pilates", "noche", 10);
        clases[4] = new ClaseGrupal("Zumba", "noche", 10);
        clases[5] = new ClaseGrupal("Cardio Dance", "noche", 10);
    }

    public void mostrarClases() { //muestra todas las clases disponibles
        StringBuilder mensaje = new StringBuilder("Clases Disponibles:\n"); //mensaje para mostrar clases
        for (int i = 0; i < clases.length; i++) {
            mensaje.append(i).append(". ").append(clases[i].resumen()).append("\n"); //número e info de cada clase
        }
        JOptionPane.showMessageDialog(null, mensaje.toString()); //muestra el mensaje
    }

    private void reservarClase(Socio socio) { //reservar una clase
        mostrarClases(); //muestra primero la lista
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase a reservar:"); //pide número
        if (input == null) {
            return; //si cancela, se sale
        }
        int opcion = -1;
        try {
            opcion = Integer.parseInt(input); //convierte la entrada a numero
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida"); //por si pone letras
            return;
        }

        if (opcion >= 0 && opcion < clases.length) { //valida que este dentro del rango
            if (clases[opcion].reservar()) { //intenta reservar
                JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "No hay cupos disponibles.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido.");
        }
    }

    private void modificarClase() { //modifica info de una clase existente
        mostrarClases(); //muestra la lista primero
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase a modificar:");
        if (input == null) {
            return;
        }

        int opcion = -1;
        try {
            opcion = Integer.parseInt(input); //convierte entrada a numero
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida"); //si digita algo incorrecto
            return;
        }

        if (opcion >= 0 && opcion < clases.length) { //verifica que sea valida
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
            String nuevoHorario = JOptionPane.showInputDialog("Nuevo horario (mañana/noche):");
            String capacidadStr = JOptionPane.showInputDialog("Nueva capacidad máxima:");
            if (nuevoNombre == null || nuevoHorario == null || capacidadStr == null) {
                return; //si cancela alguna, no hace nada
            }

            int nuevaCapacidad = 0;
            try {
                nuevaCapacidad = Integer.parseInt(capacidadStr); //convierte a numero
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Capacidad inválida"); //por si pone letras
                return;
            }

            clases[opcion].modificar(nuevoNombre, nuevoHorario, nuevaCapacidad); //modifica la clase
            JOptionPane.showMessageDialog(null, "Clase modificada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido.");
        }
    }

    public void liberarReserva() { //libera una reserva 
        mostrarClases(); //muestra la lista
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase para liberar reserva:");
        if (input == null) {
            return;
        }
        int opcion = -1;
        try {
            opcion = Integer.parseInt(input); //convierte la entrada
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida"); //si digita mal
            return;
        }

        if (opcion >= 0 && opcion < clases.length) { //verifica que este dentro del rango
            if (clases[opcion].cuposDisponibles < clases[opcion].capacidadMaxima) { //si hay algo reservado
                clases[opcion].cuposDisponibles++; //aumenta los cupos disponibles
                JOptionPane.showMessageDialog(null, "Reserva liberada exitosamente."); //mensaje de exito
            } else {
                JOptionPane.showMessageDialog(null, "No hay reservas para liberar en esa clase."); //ya estaba libre
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido."); //por si digita un numero invalido
        }
    }
}
