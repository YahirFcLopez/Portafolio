/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author yahir
 */

public class Cuenta {
    private double saldo;

    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean puedeRetirar(double monto) {
        return saldo >= monto + calcularComision(monto);
    }

    public double calcularComision(double monto) {
        return monto * 0.02;
    }

    public void retirar(double monto) {
        saldo -= monto + calcularComision(monto);
    }
}