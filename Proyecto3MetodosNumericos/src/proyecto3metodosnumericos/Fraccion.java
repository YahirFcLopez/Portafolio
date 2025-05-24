/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3metodosnumericos;

/**
 *
 * @author yahir
 */
class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        // Ajustar el denominador a 1 si es 0
        if (denominador == 0) {
            System.out.println("El denominador no puede ser cero. Se ajustará a 1.");
            denominador = 1; // Cambiar el denominador a 1
        }

        int gcd = gcd(numerador, denominador);
        this.numerador = numerador / gcd;
        this.denominador = denominador / gcd;

        if (this.denominador < 0) { // Asegurar que el denominador siempre sea positivo
            this.numerador = -this.numerador;
            this.denominador = -this.denominador;
        }
    }

    // Método para obtener el máximo común divisor (gcd)
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    @Override
    public String toString() {
        if (denominador == 1) {
            return Integer.toString(numerador);
        } else {
            return numerador + "/" + denominador;
        }
    }

    // Métodos para obtener el numerador y el denominador
    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    // Métodos para multiplicar, dividir, sumar y restar fracciones
    public Fraccion multiplicar(Fraccion f) {
        return new Fraccion(this.numerador * f.numerador, this.denominador * f.denominador);
    }

    public Fraccion dividir(Fraccion f) {
        return new Fraccion(this.numerador * f.denominador, this.denominador * f.numerador);
    }

    public Fraccion sumar(Fraccion f) {
        return new Fraccion(this.numerador * f.denominador + f.numerador * this.denominador, this.denominador * f.denominador);
    }

    public Fraccion restar(Fraccion f) {
        return new Fraccion(this.numerador * f.denominador - f.numerador * this.denominador, this.denominador * f.denominador);
    }
}
