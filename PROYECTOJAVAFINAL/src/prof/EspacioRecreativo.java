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
public class EspacioRecreativo {

    static Recreativo[] espacios = new Recreativo[7]; //arreglo de 7 espacios recreativos
    static boolean precargado = false; //para saber si ya se precargo

    public EspacioRecreativo() { //constructor vacio
    }

    static void precargar() { //carga los espacios recreativos por defecto
        espacios[0] = new Recreativo("Ping-pong", "Mesa 1", 2); //mesa de ping pong, 2 cupos
        espacios[1] = new Recreativo("Billar", "Mesa 2", 2); //mesa de billar, 2 cupos
        espacios[2] = new Recreativo("Futbol", "Cancha 1", 12); //cancha de futbol, 12 cupos
        espacios[3] = new Recreativo("Futbol", "Cancha 2", 12); //otra cancha de futbol, 12 cupos
        espacios[4] = new Recreativo("Baloncesto", "Cancha Principal", 10); //cancha de baloncesto, 10 cupos
        espacios[5] = new Recreativo("Tenis", "Cancha 1", 2); //cancha de tenis, 2 cupos
        espacios[6] = new Recreativo("Tenis", "Cancha 2", 2); //segunda cancha de tenis, 2 cupos
    }

    static class Recreativo { //clase para representar cada espacio

        String tipo; //tipo de actividad
        String nombre; //nombre del espacio
        int capacidad; //capacidad total del espacio
        int disponibles; //cupo disponible

        public Recreativo(String tipo, String nombre, int capacidad) { //constructor
            this.tipo = tipo;
            this.nombre = nombre;
            this.capacidad = capacidad;
            this.disponibles = capacidad;
        }

        public boolean reservar(int cantidad) { //reserva x cantidad de cupos
            if (disponibles >= cantidad) { //si hay espacio suficiente
                disponibles -= cantidad; //descuenta los cupos
                return true; //reserva exitosa
            }
            return false; //no se puede reservar
        }

        public void liberar(int cantidad) { //libera cierta cantidad de cupos
            disponibles = Math.min(disponibles + cantidad, capacidad); //no se pasa de la capacidad total
        }

        public String resumen() { //devuelve un resumen en texto
            return tipo + " - " + nombre + " | Cupos: " + disponibles;
        }

        public int reservados() { //retorna cuantos cupos ya se usaron
            return capacidad - disponibles;
        }
    }

    public void menuEspacioRecreativo() { //menu principal para espacios recreativos
        if (!precargado) { //si no se ha precargado
            precargar(); //carga los espacios
            precargado = true; //marca como ya precargado
        }

        String op;
        do {
            op = JOptionPane.showInputDialog("Espacios Recreativos\n1) Ver\n2) Reservar\n3) Volver"); //menu
            if (op == null || op.equals("3")) { //salir
                break;
            }

            if (op.equals("1")) { //ver espacios
                mostrarEspacios();
            } else if (op.equals("2")) { //reservar espacio
                reservar();
            } else {
                JOptionPane.showMessageDialog(null, "Opcion invalida."); //mensaje si digita algo incorrecto
            }
        } while (true); //bucle del menu
    }

    public void mostrarEspacios() { //muestra todos los espacios y sus cupos
        String msg = "Espacios disponibles:\n";
        for (int i = 0; i < espacios.length; i++) {
            msg += i + ". " + espacios[i].resumen() + "\n"; //agrega cada espacio con su numero
        }
        JOptionPane.showMessageDialog(null, msg); //muestra el mensaje
    }

    public void mostrarReservas() { //muestra los espacios que tienen reservas
        String mensaje = "Espacios Con Reservas:\n\n";
        boolean hay = false; //para saber si hay alguno reservado
        for (Recreativo r : espacios) {
            int res = r.reservados(); //cuantos cupos usados
            if (res > 0) {
                mensaje += r.tipo + " - " + r.nombre + " | Reservados: " + res + "\n"; //muestra info
                hay = true; //se encontro al menos uno
            }
        }
        if (hay) {
            JOptionPane.showMessageDialog(null, mensaje); //muestra los reservados
        } else {
            JOptionPane.showMessageDialog(null, "No hay reservas realizadas."); //si no hay ninguno
        }
    }

    public void reservar() { //permite reservar un espacio
        mostrarEspacios(); //muestra primero
        try {
            String id = JOptionPane.showInputDialog("ID de socio:"); //pide id del socio
            int esp = Integer.parseInt(JOptionPane.showInputDialog("Espacio a reservar (numero):")); //numero del espacio
            int cant = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas personas usaran el espacio?")); //cuantos cupos

            if (esp >= 0 && esp < espacios.length) { //valida el numero del espacio
                if (espacios[esp].reservar(cant)) { //intenta reservar
                    JOptionPane.showMessageDialog(null, "Reserva hecha para socio " + id); //exito
                } else {
                    JOptionPane.showMessageDialog(null, "No hay cupos suficientes."); //no hay espacio
                }
            } else {
                JOptionPane.showMessageDialog(null, "Numero de espacio invalido."); //fuera del rango
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida."); //si pone algo que no es numero
        }
    }

    public void liberarReserva() { //libera cupos en un espacio
        mostrarEspacios(); //muestra los espacios
        try {
            int esp = Integer.parseInt(JOptionPane.showInputDialog("Espacio a liberar (numero):")); //pide el numero
            int cant = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos cupos desea liberar?")); //cantidad a liberar

            if (esp >= 0 && esp < espacios.length) { //valida el numero
                espacios[esp].liberar(cant); //libera los cupos
                JOptionPane.showMessageDialog(null, "Reserva liberada."); //mensaje de exito
            } else {
                JOptionPane.showMessageDialog(null, "Numero invalido."); //fuera de rango
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida."); //si no digita numeros validos
        }
    }
}
