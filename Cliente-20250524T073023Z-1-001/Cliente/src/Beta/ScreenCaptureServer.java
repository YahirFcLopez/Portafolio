/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beta;

/**
 *
 * @author yahir
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.*;

public class ScreenCaptureServer {
    private static final int PORT = 12345; // Puerto para el servidor
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream out;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor de captura de pantalla iniciado en el puerto " + PORT);

            // Esperar a que los clientes se conecten
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            // Obtener el flujo de salida para enviar la imagen
            out = new DataOutputStream(clientSocket.getOutputStream());

            // Crear un hilo para capturar la pantalla continuamente y enviarla
            new Thread(() -> captureAndSendScreen()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void captureAndSendScreen() {
        try {
            Robot robot = new Robot();
            while (true) {
                // Capturar la pantalla completa
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage screenshot = robot.createScreenCapture(screenRect);

                // Comprimir la imagen a formato JPEG
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(screenshot, "jpg", byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                // Enviar la longitud de la imagen primero
                out.writeInt(imageBytes.length);

                // Enviar la imagen
                out.write(imageBytes);
                out.flush();

                // Pausar brevemente entre las capturas para no sobrecargar la red
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ScreenCaptureServer().startServer();
    }
}
