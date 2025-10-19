/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author yahir
 */

public class Cliente {
    private String nombre;
    private String cuenta;
    
    public Cliente(String nombre, String cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getUltimosDigitos() {
        return cuenta.substring(cuenta.length() - 4);
    }
}