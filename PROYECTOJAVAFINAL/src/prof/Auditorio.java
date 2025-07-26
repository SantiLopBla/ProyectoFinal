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
public class Auditorio {

    private String[] participantes10am = new String[30]; //Guarda 30 participantes de las 10am
    private String[] participantes3pm = new String[30];  //Guarda 30 participantes de las 3pm
    private int contador10am = 0; //Contador para personas a las 10am
    private int contador3pm = 0;  //Contador para personas a las 3pm

    public void menuAuditorio(Socio socio) { // Menú principal del auditorio
        while (true) { //Ciclo para repetir el menú hasta que el usuario salga
            String opcion = JOptionPane.showInputDialog(
                    "Auditorio - Menú\n"
                    + "1) Inscribir participante\n"
                    + "2) Mostrar lista de inscritos\n"
                    + "3) Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) { //Si elige salir o cierra la ventana
                break;
            }

            switch (opcion) { //Switch para elegir una opcion
                case "1":
                    inscribir(socio); //Llama metodo para inscribir al socio
                    break;
                case "2":
                    mostrarListaParticipantes(); //Llama metodo mostrar los inscritos
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida"); //Si digita algo no válido
            }
        }
    }

    private void inscribir(Socio socio) { //Metodo para inscribir socio a x hora
        String horario = JOptionPane.showInputDialog("Ingrese horario (10am o 3pm):"); //Pide el horario
        if (horario == null) {
            return; //Si cancela, se sale
        }

        horario = horario.trim().toLowerCase(); //Quita espacios y convierte a minúsculas
        if (!horario.equals("10am") && !horario.equals("3pm")) { //Verifica que sea 10am o 3pm
            JOptionPane.showMessageDialog(null, "Horario inválido");
            return;
        }

        if (horario.equals("10am")) { // Si es 10am
            if (contador10am < participantes10am.length) { //Si hay espacio
                participantes10am[contador10am++] = socio.getId(); //Guarda el ID y aumenta el contador
                JOptionPane.showMessageDialog(null, "Inscripción exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Aforo lleno para 10am"); //Si ya está lleno
            }
        } else {
            if (contador3pm < participantes3pm.length) { //Si hay espacio para las 3pm
                participantes3pm[contador3pm++] = socio.getId(); //Guarda el ID y aumenta el contador
                JOptionPane.showMessageDialog(null, "Inscripción exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Aforo lleno para 3pm"); //Si ya está lleno
            }
        }
    }

    public void mostrarListaParticipantes() { //Muestra los inscritos de ambas horas
        StringBuilder sb = new StringBuilder("Participantes 10am:\n"); //Primer print con los de las 10am
        if (contador10am == 0) {
            sb.append("No hay inscritos.\n"); // Si no hay inscritos
        } else {
            for (int i = 0; i < contador10am; i++) {
                sb.append("- ").append(participantes10am[i]).append("\n"); // Agrega cada ID
            }
        }
        sb.append("\nParticipantes 3pm:\n"); //Segundo print con los de las 3pm
        if (contador3pm == 0) {
            sb.append("No hay inscritos.\n"); //Si no hay inscritos
        } else {
            for (int i = 0; i < contador3pm; i++) {
                sb.append("- ").append(participantes3pm[i]).append("\n"); //Agrega cada ID
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString()); //Muestra todo el texto en pantalla con el toString
    }

    public void liberarParticipante() { //Metodo quitar a un participante
        String horario = JOptionPane.showInputDialog("Ingrese horario para liberar (10am o 3pm):"); // Pide hora
        if (horario == null) {
            return; // Si cancela
        }

        horario = horario.trim().toLowerCase(); //Limpia el texto, quitando espacios y poniendolo en minuscula
        String id = JOptionPane.showInputDialog("Ingrese ID para liberar:"); //Pide el ID
        if (id == null) {
            return; //Si cancela
        }
        boolean encontrado = false; //Marca si el ID fue encontrado

        if (horario.equals("10am")) { //Revisa si es de la lista de 10am
            for (int i = 0; i < contador10am; i++) {
                if (participantes10am[i].equals(id)) { //Si encuentra el ID
                    participantes10am[i] = participantes10am[contador10am - 1]; //Reemplaza con el último
                    participantes10am[contador10am - 1] = null; //Elimina el último
                    contador10am--; //Resta 1 al contador
                    encontrado = true; //Lo encontró
                    break;
                }
            }
        } else if (horario.equals("3pm")) { //Revisa si es de la lista de 3pm
            for (int i = 0; i < contador3pm; i++) {
                if (participantes3pm[i].equals(id)) {
                    participantes3pm[i] = participantes3pm[contador3pm - 1]; //Reemplaza con el último
                    participantes3pm[contador3pm - 1] = null; //Elimina el último
                    contador3pm--; //Resta 1 al contador
                    encontrado = true; //Lo encontró
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Participante liberado correctamente."); //Si se liberó
        } else {
            JOptionPane.showMessageDialog(null, "ID no encontrado."); //Si no se encontró
        }
    }
}
