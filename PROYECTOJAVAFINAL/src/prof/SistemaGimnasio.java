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
public class SistemaGimnasio {

    static Socio socioActual = null; //guarda el socio que se registro
    static Parqueo parqueo = new Parqueo(); //objeto modulo parqueo
    static Auditorio auditorio = new Auditorio(); //objeto modulo auditorio
    static Cabina cabina = new Cabina(); //objeto modulo cabinas
    static SalaDePesas salaDePesas = new SalaDePesas(); //objeto modulo sala de pesas
    static ClasesGrupales clasesGrupales = new ClasesGrupales(); //objeto modulo clases grupales
    static EspacioRecreativo espacioRecreativo = new EspacioRecreativo(); //objeto modulo espacios recreativos

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al Sistema del Gimnasio"); 
        socioActual = Socio.registrarSocio(); //se registra el socio
        JOptionPane.showMessageDialog(null, "Usted es socio con ID: " + socioActual.getId()); //muestra el id

        boolean salir = false; //controla si se sale del sistema

        while (!salir) {
            String opcion = JOptionPane.showInputDialog( //menu principal
                    "Menú principal:\n"
                    + "1. Parqueo\n"
                    + "2. Reservar Auditorio\n"
                    + "3. Cabinas\n"
                    + "4. Sala de Pesas\n"
                    + "5. Clases Grupales\n"
                    + "6. Espacios Recreativos\n"
                    + "7. Listado de Reservas\n"
                    + "8. Liberar Reserva\n"
                    + "9. Salir"
            );

            if (opcion == null) {
                break; //si el usuario cancela, sale
            }

            switch (opcion) {
                case "1":
                    parqueo.menuParqueo(socioActual); //abre menu del parqueo
                    break;
                case "2":
                    auditorio.menuAuditorio(socioActual); //abre menu del auditorio
                    break;
                case "3":
                    cabina.menuCabina(socioActual); //abre menu de cabinas
                    break;
                case "4":
                    salaDePesas.menuSala(socioActual); //abre menu de sala de pesas
                    break;
                case "5":
                    clasesGrupales.menuClases(socioActual); //abre menu de clases grupales
                    break;
                case "6":
                    espacioRecreativo.menuEspacioRecreativo(); //abre menu de espacios recreativos
                    break;
                case "7":
                    mostrarListadoReservas(); //muestra reservas actuales
                    break;
                case "8":
                    liberarReserva(); //permite liberar una reserva
                    break;
                case "9":
                    salir = true; //activa la salida del sistema
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema, vuelva pronto"); 
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida, intente de nuevo"); //mensaje si no pone una opcion valida
            }
        }
    }

    private static void mostrarListadoReservas() { //muestra lo que esta reservado en cada area
        String opcion = JOptionPane.showInputDialog(
                "Seleccione el área que desea ver:\n"
                + "1. Ver parqueo\n"
                + "2. Ver sala de pesas\n"
                + "3. Ver auditorio\n"
                + "4. Ver cabina\n"
                + "5. Ver clases grupales\n"
                + "6. Ver espacios recreativos"
        );
        if (opcion == null) {
            return; //si cancela, se sale
        }
        switch (opcion) {
            case "1":
                parqueo.mostrarParqueo(); //ver parqueo
                break;
            case "2":
                salaDePesas.mostrarReservasSala(); //ver sala
                break;
            case "3":
                auditorio.mostrarListaParticipantes(); //ver auditorio
                break;
            case "4":
                cabina.mostrarResumenReservas(); //ver cabinas
                break;
            case "5":
                clasesGrupales.mostrarClases(); //ver clases
                break;
            case "6":
                espacioRecreativo.mostrarReservas(); //ver espacios recreativos
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida"); //opcion no valida
        }
    }

    private static void liberarReserva() { //libera alguna reserva segun el area
        String area = JOptionPane.showInputDialog(
                "Seleccione área para liberar reserva:\n"
                + "1. Parqueo\n"
                + "2. Sala de Pesas\n"
                + "3. Auditorio\n"
                + "4. Cabina\n"
                + "5. Clases Grupales\n"
                + "6. Espacios Recreativos"
        );
        if (area == null) {
            return; //si cancela
        }

        switch (area) {
            case "1":
                parqueo.liberarEspacio(); //libera espacio de parqueo
                break;
            case "2":
                salaDePesas.liberarReserva(); //libera sala de pesas
                break;
            case "3":
                auditorio.liberarParticipante(); //libera en auditorio
                break;
            case "4":
                cabina.liberarReserva(); //libera cabina
                break;
            case "5":
                clasesGrupales.liberarReserva(); //libera clase
                break;
            case "6":
                espacioRecreativo.liberarReserva(); //libera espacio recreativo
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida"); //mensaje si no pone una opcion valida
        }
    }
}
