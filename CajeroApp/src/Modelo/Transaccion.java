/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author yahir
 */

public class Transaccion {
    private double monto;
    private double comision;

    public Transaccion(double monto, double comision) {
        this.monto = monto;
        this.comision = comision;
    }

    public double getMonto() {
        return monto;
    }

    public double getComision() {
        return comision;
    }

    public double getTotal() {
        return monto + comision;
    }
}