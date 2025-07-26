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
public class SistemaGimnasio {

    RegistroSocios registro = new RegistroSocios();
    Parqueo parqueo = new Parqueo();
    ClasesGrupales clase = new ClasesGrupales();
    CabinaInsonorizada cabina = new CabinaInsonorizada(); //Me dijo que era mejor no tirar un constructor vacio, pero solo con eso me sale aqui
    SalaPesas pesas = new SalaPesas();
    //Auditorio auditorio=new Auditori();
    EspacioRecreativo espacio = new EspacioRecreativo();
    
    boolean salir = false;

    public void iniciar() {
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "SISTEMA DE GIMNASIO\n\n"
                    + "1) Registro de socios\n"
                    + "2) Parqueo del Gimnasio\n"
                    + "3) Clases grupales\n"
                    + "4) Cabinas insonorizadas\n"
                    + "5) Sala de pesas\n"
                    + "6) Auditorio\n"
                    + "7) Espacios recreativos\n"
                    + "8) Ver todas las reservas\n"
                    + "9) Liberar reserva\n"
                    + "10) Salir"
            );

            if (opcion == null) {
                return; // Usuario cerró
            }
            switch (opcion) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Registro de Socios");
                    registro.iniciar();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Parqueo del Gimnasio");
                    parqueo.iniciar();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Clases Grupales");
                    clase.iniciar();
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Abriendo el modulo de Cabinas Insonorizadas");
                    //tuve problemas con este
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Sala de pesas");
                    pesas.iniciar();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Auditorio");
                    //Este es mejor tambien volverlo a hacer, parecido a los que estan aqui para que quede bonito
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Espacios Recreativos"); //Me parece que no esta restando
                    EspacioRecreativo.iniciar();
                    break;
                case "8": //Esto hay que arreglarlo
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Listado de Reservas");
                    int opcionReserva = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el área que desea ver:\n"
                            + "1. Ver parqueo\n"
                            + "2. Ver clases\n"
                            + "3. Ver auditorio\n"
                            + "4. Ver cabina\n"
                            + "5. Ver clases grupales\n"
                            + "6. Ver espacios recreativos"));
                    switch (opcionReserva) {
                        case 1:
                            parqueo.;
                            break;
                        case 2:
                            pesas(); // este método lo tienes que tener en tu sistema
                            break;
                        case 3:
                            Reserva1.mostrarLista(); // del auditorio
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, sistemaCabina.getResumenReservas());
                            break;
                        case 5:
                            ClasesGrupales.mostrarClases(); // ya existe
                            break;
                        case 6:
                            EspacioRecreativo.mostrarReservas(); // ya existe
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida");
                    }
                    break;

                case "9":
                    JOptionPane.showMessageDialog(null, "Abriendo modulo de Liberar Reserva");
                    break;
                case "10":
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema, vuelva pronto");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida, ingrese una opcion disponible");
            }
        }
    }
}
