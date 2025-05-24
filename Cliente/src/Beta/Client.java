package Beta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Client {
    private static final String SERVER_ADDRESS = "192.168.84.18";
    private static final int PORT = 12345;
    private JLabel imageLabel;
    private Socket socket;
    private PrintWriter out;

    public Client() {
        // Inicia la conexión al servidor en un hilo separado
        new Thread(this::connectToServer).start();
    }

    private void createGUI() {
        // Configura la interfaz gráfica principal del cliente
        JFrame frame = new JFrame("Cliente de Juego");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(580, 500);
frame.setLayout(new FlowLayout());

// Centrar el JFrame en la pantalla
frame.setLocationRelativeTo(null);

frame.setVisible(true);
        // JComboBox para seleccionar el jugador
        String[] jugadores = {"Jugador 1", "Jugador 2", "Jugador 3", "Jugador 4"};
        JComboBox<String> comboBox = new JComboBox<>(jugadores);
        frame.add(comboBox);

        // Botón para enviar el comando
        JButton avanzarButton = new JButton("AVANZAR");
        frame.add(avanzarButton);

        // Área de texto para mostrar mensajes
        imageLabel = new JLabel();
        frame.add(imageLabel);

        // Acción del botón
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jugadorSeleccionado = comboBox.getSelectedIndex() + 1; // 1-4
                enviarComando(jugadorSeleccionado);
            }
        });

        frame.setVisible(true);
    }

    private void connectToServer() {
        try {
            // Establecer conexión con el servidor
            socket = new Socket(SERVER_ADDRESS, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);

            // Después de conectarse, iniciar la GUI
            SwingUtilities.invokeLater(this::createGUI);

            // Escuchar respuestas del servidor
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String response;
                while ((response = in.readLine()) != null) {
                    if (response.startsWith("IMAGE:")) {
                        String base64Image = response.substring(6);

                        // Decodificar la imagen base64
                        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                        BufferedImage image = ImageIO.read(bis);

                        // Mostrar la imagen en el label
                        ImageIcon icon = new ImageIcon(image);
                        SwingUtilities.invokeLater(() -> {
                            imageLabel.setIcon(icon);
                            imageLabel.repaint();
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
 public void manejarMensajes(String message) {
        if (message.startsWith("ESPERAR")) {
            // Extraemos el mensaje que contiene la indicación de espera
            String[] partes = message.split(":");
            if (partes.length == 2) {
                // Mostramos un JOptionPane con el mensaje
                JOptionPane.showMessageDialog(null, partes[1], "Turno del Jugador", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void enviarComando(int jugador) {
        if (out != null) {
            String comando = "PLAYER:" + jugador + ":AVANZAR";
            out.println(comando);
            System.out.println("Comando enviado: " + comando);
        } else {
            System.out.println("No hay conexión activa con el servidor.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}
