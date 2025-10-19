/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cajeroapp;

/**
 *
 * @author yahir
 */
import Controller.CajeroController;
import Modelo.*;
import Vista.CajeroVista;

public class CajeroApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Cliente cliente = new Cliente("Yahir", "1234567890123456");
        Cuenta cuenta = new Cuenta(1500.00);
        CajeroController controller = new CajeroController(cliente, cuenta);
        CajeroVista Vista = new CajeroVista(controller);
                Vista.mostrarMontos();

    }
    
}
