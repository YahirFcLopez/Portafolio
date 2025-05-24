/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Meteoro extends Thread {
    private Game game;
    private final JLabel Meteorito;
    private final JLabel Meteorito1; // Nuevo JLabel para el segundo meteorito
    private JLabel Nave;
    private JPanel JPanel1;
    private final Monitormeteoro M;

    private volatile boolean running = true;
 private final String[] meteoritoImages = {"/recursos/Meteoro2.png", "/recursos/Meteoro3.png", "/recursos/Meteoro4.png"};
    private int currentMeteoritoIndex = 0;
    public Meteoro(JLabel Meteorito, JLabel Meteorito1, JLabel Nave, JPanel JPanel1) {
        this.Meteorito = Meteorito;
        this.Meteorito1 = Meteorito1;
        this.Nave = Nave;
        this.JPanel1 = JPanel1;
        this.M = new Monitormeteoro();  // Asegúrate de que esto esté inicializado adecuadamente
    }

    @Override
    public void run() {
        
        try {
            while (running) {
                synchronized (M) {
                    while (M.ocuped) {
                        M.wait();
                    }

                    System.out.println("Meteoro: " + Thread.currentThread().getName());
                    M.notifyAll();
                }
               
                mover();
                 
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            // Manejar la interrupción adecuadamente, si es necesario.
        }
    }

    public void detener() {
        running = false;
    }

    public void iniciar() {
        running = true;
    }

    private void mover() throws InterruptedException {
    int i = 470;  // Posición inicial en x para el primer meteorito
    int i2 = generateRandomY();
    int j = 470;  // Posición inicial en x para el segundo meteorito
    int j2 = generateRandomY();

    while (running) {
        Thread.sleep(6);

        synchronized (Meteorito) {
            int xMeteorito = Meteorito.getX();
            int yMeteorito = Meteorito.getY();
            int xMeteorito1 = Meteorito1.getX();
            int yMeteorito1 = Meteorito1.getY();
            int xNave = Nave.getX();
            int yNave = Nave.getY();

            // Verifica la colisión con el primer meteorito
            if (xMeteorito  < xNave + Nave.getWidth() &&
                    xMeteorito  + Meteorito.getWidth() > xNave &&
                    yMeteorito  < yNave + Nave.getHeight() &&
                    yMeteorito  + Meteorito.getHeight() > yNave) {
                System.out.println("¡METEORO 1 tocó a NAVE!");
                ((JFrame) SwingUtilities.getWindowAncestor(JPanel1)).dispose();
               
              
               desaparecerMeteoro();
                detener();
                Game game = new Game();
                game.musicaFondo.stop();
                // Abre el menú
                Menu menu = new Menu();
                menu.setVisible(true);

                break;
            }

            // Verifica la colisión con el segundo meteorito
            if (xMeteorito1 < xNave + Nave.getWidth() &&
                    xMeteorito1 + Meteorito1.getWidth() > xNave &&
                    yMeteorito1 < yNave + Nave.getHeight() &&
                    yMeteorito1 + Meteorito1.getHeight() > yNave) {
                System.out.println("¡METEORO 2 tocó a NAVE!");
                ((JFrame) SwingUtilities.getWindowAncestor(JPanel1)).dispose();
                 desaparecerMeteoro();
                detener();
                Game game = new Game();
                game.musicaFondo.stop();
                // Abre el menú
                Menu menu = new Menu();
                menu.setVisible(true);

                break;
            }

            // Mueve el primer meteorito
            Meteorito.setLocation(i, i2);
            i--;

            if (i == 0) {
                i = 470;
                i2 = generateRandomY();
                Meteorito.setLocation(i, i2);
            }

            // Mueve el segundo meteorito
            Meteorito1.setLocation(j, j2);
            j--;

            if (j == 0) {
                j = 470;
                j2 = generateRandomY();
                Meteorito1.setLocation(j, j2);
            }
        }
    }
}

    private void desaparecerMeteoro() {
        Meteorito.setVisible(false);
        Meteorito1.setVisible(false);
        // Generar una nueva posición aleatoria para el próximo meteorito
        Meteorito.setLocation(generateRandomX(), generateRandomY());
        Meteorito1.setLocation(generateRandomX(), generateRandomY());
        Meteorito.setVisible(true);
        Meteorito1.setVisible(true);
    }

    private int generateRandomX() {
        Random random = new Random();
        return random.nextInt(500); // Ajusta el rango según sea necesario
    }

    private int generateRandomY() {
        Random random2 = new Random();
        return random2.nextInt(500); // Ajusta el rango según sea necesario
    }
}
   
