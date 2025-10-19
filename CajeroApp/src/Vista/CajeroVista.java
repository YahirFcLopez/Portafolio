/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controller.CajeroController;
import Modelo.*;
/**
 *
 * @author yahir
 */
import java.util.Scanner;

public class CajeroVista {
    private CajeroController controller;
    private Scanner scanner;

    public CajeroVista(CajeroController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

  public void mostrarMontos() {
    System.out.println("\nMontos disponibles para retiro:");
    double[] montos = {200, 400, 600, 800, 1000};

    for (int i = 0; i < montos.length; i++) {
        System.out.printf("%d. $%.2f\n", i + 1, montos[i]);
    }

    System.out.print("Seleccione un monto: ");
    int opcion = scanner.nextInt();
    double montoSeleccionado = montos[opcion - 1];

    System.out.println("\n--- Estado de Cuenta ---");
    System.out.println(controller.generarEstadoCuenta(montoSeleccionado));

    System.out.print("\n¿Desea retirar efectivo? (s/n): ");
    String confirmar = scanner.next();

    if (confirmar.equalsIgnoreCase("s")) {
        System.out.println("\n--- Retiro ---");
        System.out.println(controller.retirar(montoSeleccionado));
    } else {
        System.out.println("Operación cancelada.\n");
    }
}
}