/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beta;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.*;
import java.io.*;
import java.net.*;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class ScreenClient {
    public static void main(String[] args) {
        String serverAddress = "189.204.155.98";  // Tu IP pública
        int port = 12345;  // Puerto redirigido en el router

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Conectado al servidor.");

            // Flujo de salida para enviar los datos de la imagen
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Crear un JFrame para mostrar la pantalla capturada
            JFrame frame = new JFrame("Pantalla del cliente");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);  // Tamaño de la ventana
            JLabel label = new JLabel();
            frame.getContentPane().add(label);
            frame.setVisible(true);

            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            while (true) {
                // Capturar la pantalla
                BufferedImage screenshot = robot.createScreenCapture(screenRect);

                // Enviar el tamaño de la imagen
                int width = screenshot.getWidth();
                int height = screenshot.getHeight();
                dataOutputStream.writeInt(width);
                dataOutputStream.writeInt(height);

                // Convertir la imagen a un array de bytes
                byte[] imageData = ((DataBufferByte) screenshot.getData().getDataBuffer()).getData();
                dataOutputStream.write(imageData);

                // Mostrar la imagen en el JFrame del cliente
                ImageIcon icon = new ImageIcon(screenshot);
                label.setIcon(icon);
                frame.revalidate();
                frame.repaint();

                // Pausa para no saturar la red (ajustar según necesidad)
                Thread.sleep(100);  // 10 FPS (puedes ajustar el tiempo de espera)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


