/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author yahir
 */
public class Game extends javax.swing.JFrame {
 int posX, posY;

 private int currentBackgroundIndex = 0;
private String[] backgrounds = { "/recursos/fondo2.png", "/recursos/fondo3.png", "/recursos/fondo4.png", "/recursos/fondo5.png", "/recursos/fondo6.png"};

    Timer timer,time2;
    boolean arriba=true ;
    int gravity = 2;
    int jumpVelocity = -20; // Velocidad dez salto, ajusta según sea necesario
    int currentVelocity = 0;
     
    Meteoro objeto;
    URL rutaFondo;
     AudioClip musicaFondo;
    public Game() {
       
        initComponents();
        //Musica
        rutaFondo = getClass().getResource("/recursos/Rick.wav");
        musicaFondo = Applet.newAudioClip(rutaFondo);
        musicaFondo.loop();
          this.setLocationRelativeTo(null);
         //MOVIMIENTO METEORITOS 
       objeto = new Meteoro(Meteorito,Meteorito1, Nave, jPanel1);
        objeto.start();
        
this.setSize(520,600);
        // Ejemplo de cómo agregar meteoritos, ajusta según sea necesario
       
        Nave.setFocusable(true);
        Nave.requestFocus();
       
        
        //BOTON ACCION
        Nave.addKeyListener(new KeyAdapter() {
    @Override
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
           jump();
           
                
        }
    }
});

// Configuración del temporizador para las actualizaciones periódicas
timer = new Timer(30, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      updateShipPosition();
        
        updateBackground();
    }
});

// Iniciar el temporizador
timer.start();
   

    
}
    
     public void detenerJuego() {
    if (objeto != null) {
        objeto.detener();
        musicaFondo.stop();
    }
}
   
private void updateBackground() {
    currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
    Fondo.setIcon(new ImageIcon(getClass().getResource(backgrounds[currentBackgroundIndex])));
}
    private void jump() {
        // Al presionar la barra espaciadora, establecer la velocidad de salto
       currentVelocity = jumpVelocity;
   
        
    }

    public void updateShipPosition() {
          int maxY = jPanel1.getHeight() - Nave.getHeight();

    // Si la nave está en el suelo y ha saltado, comienza a caer
    if (posY >= maxY && currentVelocity > 0) {
        currentVelocity = 0; // Detener el salto
        posY = maxY;
    } else {
        posY += currentVelocity; // Aplicar la velocidad actual
        currentVelocity += gravity; // Aplicar gravedad

        // Limitar el movimiento de la nave dentro de los límites de la pantalla
        if (posY < 0) {
            posY = 0;
            currentVelocity = 1; // La nave no puede ir más arriba si alcanza el límite superior
        } else if (posY > maxY) {
            posY = maxY;
            currentVelocity = 0; // La nave no puede ir más abajo si alcanza el límite inferior
        }

        Nave.setLocation(posX, posY);  // Movemos la actualización de la posición aquí
    
    }

    //Nave.setLocation(posX, posY);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Meteorito = new javax.swing.JLabel();
        Meteorito1 = new javax.swing.JLabel();
        Nave = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setFocusCycleRoot(true);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Meteorito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Meteoro1.png"))); // NOI18N
        jPanel1.add(Meteorito, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, -1, 30));

        Meteorito1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Meteoro1.png"))); // NOI18N
        jPanel1.add(Meteorito1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, 30));

        Nave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Navemorty1.png"))); // NOI18N
        Nave.setText("jLabel2");
        Nave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NaveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NaveKeyReleased(evt);
            }
        });
        jPanel1.add(Nave, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 130, 70));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo1.png"))); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 530, 600));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fondo1.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NaveKeyPressed
   
    }//GEN-LAST:event_NaveKeyPressed

    private void NaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NaveKeyReleased
      
    }//GEN-LAST:event_NaveKeyReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Meteorito;
    private javax.swing.JLabel Meteorito1;
    private javax.swing.JLabel Nave;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
