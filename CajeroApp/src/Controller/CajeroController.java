/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import  Modelo.*;
/** *
 * @author yahir
 */
public class CajeroController {
    private Cliente cliente;
    private Cuenta cuenta;

    public CajeroController(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
    }

    public String generarEstadoCuenta(double monto) {
        double comision = cuenta.calcularComision(monto);
        double total = monto + comision;
        double saldoFinal = cuenta.getSaldo() - total;

        return String.format(
            "Nombre: %s\nSaldo Actual: $%.2f\nMonto a Retirar: $%.2f\nComisión: $%.2f\nSaldo Final: $%.2f",
            cliente.getNombre(), cuenta.getSaldo(), monto, comision, saldoFinal
        );
    }

    public String retirar(double monto) {
        if (!cuenta.puedeRetirar(monto)) {
            return "Fondos insuficientes.";
        }

        cuenta.retirar(monto);
        Transaccion t = new Transaccion(monto, cuenta.calcularComision(monto));

        return String.format(
            "RECIBO DE RETIRO\nCuenta: ****%s\nNombre: %s\nRetiro: $%.2f\nComisión: $%.2f",
            cliente.getUltimosDigitos(), cliente.getNombre(), t.getMonto(), t.getComision()
        );
    }
}