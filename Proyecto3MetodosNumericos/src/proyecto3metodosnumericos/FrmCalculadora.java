/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3metodosnumericos;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author yahir
 */
public class FrmCalculadora extends javax.swing.JFrame {

    /**
     * Creates new form FrmCalculadora
     */
    public FrmCalculadora() {
        initComponents();
    }

      //METODOS:
   private void compararMetodos(Fraccion[][] matriz) {
      // Nombres de los métodos
    String[] methodNames = {"Eliminación Gaussiana", "Eliminación Gauss-Jordan", "Inversión de Matrices"};
    ResultadoArea.setText(""); // Limpiar el área de resultados

    // Comparar resultados
    for (String methodName : methodNames) {
        long startTime = System.nanoTime();
        Fraccion[][] resultado = null; // Para almacenar el resultado de cada método

        if (methodName.equals("Eliminación Gaussiana")) {
            resultado = eliminacionGaussiana(matriz);
        } else if (methodName.equals("Eliminación Gauss-Jordan")) {
            resultado = eliminacionGaussJordan(matriz);
        } else if (methodName.equals("Inversión de Matrices")) {
            resultado = invertirMatriz(matriz);
        }

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e6; // Tiempo en milisegundos

        // Mostrar el resultado de la matriz y el tiempo de ejecución en el área de texto
        ResultadoArea.append(methodName + ":\n");
        mostrarMatriz(resultado); // Muestra la matriz resultante
        ResultadoArea.append("Tiempo de ejecución: " + executionTime + " ms\n\n");
    }
}
private void mostrarMatriz(Fraccion[][] matriz) {
    if (matriz != null) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                ResultadoArea.append(matriz[i][j].toString() + "\t");
            }
            ResultadoArea.append("\n");
        }
    } else {
        ResultadoArea.append("No se obtuvo resultado.\n");
    }
}

    private void mostrarMatrizYResultado(Fraccion[][] matriz, Fraccion[] solution, double executionTime) {
    // Mostrar la matriz
    ResultadoArea.setText("Matriz:\n");
    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[i].length; j++) {
            ResultadoArea.append(matriz[i][j].toString() + "\t");
        }
        ResultadoArea.append("\n");
    }

    // Mostrar la solución
    ResultadoArea.append("Tiempo de ejecución: " + executionTime + " ms\n");
    
}
//LEERMATRIZ 
     private Fraccion[][] leerMatrizDesdeCampos() {
           if (subíndice1.getText().isEmpty() || subíndice2.getText().isEmpty() || subíndice3.getText().isEmpty() ||
        subíndice4.getText().isEmpty() || subíndice5.getText().isEmpty() || subíndice6.getText().isEmpty() ||
        subíndice7.getText().isEmpty() || subíndice8.getText().isEmpty() || subíndice9.getText().isEmpty()) {
        
        JOptionPane.showMessageDialog(null, "Favor de insertar valores en todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
        return null; // Retornar null si faltan valores
    }
    Fraccion[][] matriz = new Fraccion[3][3];
    matriz[0][0] = new Fraccion(Integer.parseInt(subíndice1.getText()), 1);
    matriz[0][1] = new Fraccion(Integer.parseInt(subíndice2.getText()), 1);
    matriz[0][2] = new Fraccion(Integer.parseInt(subíndice3.getText()), 1);
    matriz[1][0] = new Fraccion(Integer.parseInt(subíndice4.getText()), 1);
    matriz[1][1] = new Fraccion(Integer.parseInt(subíndice5.getText()), 1);
    matriz[1][2] = new Fraccion(Integer.parseInt(subíndice6.getText()), 1);
    matriz[2][0] = new Fraccion(Integer.parseInt(subíndice7.getText()), 1);
    matriz[2][1] = new Fraccion(Integer.parseInt(subíndice8.getText()), 1);
    matriz[2][2] = new Fraccion(Integer.parseInt(subíndice9.getText()), 1);
    return matriz;
}

    //METODO DE ELIMINAICON GAUSSIANA
       
private Fraccion[][] eliminacionGaussiana(Fraccion[][] matriz) {
    int n = matriz.length;
    Fraccion[][] resultado = new Fraccion[n][n];

    // Crear una copia de la matriz original
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            resultado[i][j] = matriz[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        Fraccion pivot = resultado[i][i];
        for (int j = 0; j < n; j++) {
            resultado[i][j] = resultado[i][j].dividir(pivot);
        }

        for (int k = i + 1; k < n; k++) {
            Fraccion factor = resultado[k][i];
            for (int j = 0; j < n; j++) {
                resultado[k][j] = resultado[k][j].restar(factor.multiplicar(resultado[i][j]));
            }
        }
    }
    return resultado;
}


         //METODO DE ELIMINACION DE GAUSSJORDAN
        private Fraccion[][] eliminacionGaussJordan(Fraccion[][] matriz) {
    int n = matriz.length;
    Fraccion[][] resultado = new Fraccion[n][n];

    // Copiar la matriz original
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            resultado[i][j] = matriz[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        Fraccion pivot = resultado[i][i];
        for (int j = 0; j < n; j++) {
            resultado[i][j] = resultado[i][j].dividir(pivot);
        }

        for (int k = 0; k < n; k++) {
            if (i != k) {
                Fraccion factor = resultado[k][i];
                for (int j = 0; j < n; j++) {
                    resultado[k][j] = resultado[k][j].restar(factor.multiplicar(resultado[i][j]));
                }
            }
        }
    }
    return resultado;
}

    //METODO PARA INVERTIR UNA MATRIZ
      private Fraccion[][] invertirMatriz(Fraccion[][] matriz) {
    int n = matriz.length;
    Fraccion[][] identidad = new Fraccion[n][n];
    Fraccion[][] resultado = new Fraccion[n][n];

    // Crear la matriz identidad
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                identidad[i][j] = new Fraccion(1, 1);
            } else {
                identidad[i][j] = new Fraccion(0, 1);
            }
        }
    }

    // Crear una copia de la matriz original
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            resultado[i][j] = matriz[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        Fraccion pivot = resultado[i][i];
        for (int j = 0; j < n; j++) {
            resultado[i][j] = resultado[i][j].dividir(pivot);
            identidad[i][j] = identidad[i][j].dividir(pivot);
        }

        for (int k = 0; k < n; k++) {
            if (i != k) {
                Fraccion factor = resultado[k][i];
                for (int j = 0; j < n; j++) {
                    resultado[k][j] = resultado[k][j].restar(factor.multiplicar(resultado[i][j]));
                    identidad[k][j] = identidad[k][j].restar(factor.multiplicar(identidad[i][j]));
                }
            }
        }
    }
    return identidad;
    
        
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        subíndice2 = new javax.swing.JTextField();
        subíndice3 = new javax.swing.JTextField();
        subíndice4 = new javax.swing.JTextField();
        subíndice1 = new javax.swing.JTextField();
        subíndice5 = new javax.swing.JTextField();
        subíndice7 = new javax.swing.JTextField();
        subíndice6 = new javax.swing.JTextField();
        subíndice9 = new javax.swing.JTextField();
        subíndice8 = new javax.swing.JTextField();
        gaussiana = new javax.swing.JRadioButton();
        gaussJordan = new javax.swing.JRadioButton();
        inversion = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultadoArea = new javax.swing.JTextArea();
        BtnCalcular = new javax.swing.JButton();
        BtnComparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        subíndice1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        subíndice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice1ActionPerformed(evt);
            }
        });

        subíndice5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice5ActionPerformed(evt);
            }
        });

        subíndice7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice7ActionPerformed(evt);
            }
        });

        subíndice6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice6ActionPerformed(evt);
            }
        });

        subíndice9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice9ActionPerformed(evt);
            }
        });

        subíndice8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subíndice8ActionPerformed(evt);
            }
        });

        buttonGroup2.add(gaussiana);
        gaussiana.setText("Eliminacion Gaussiana");
        gaussiana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussianaActionPerformed(evt);
            }
        });

        buttonGroup2.add(gaussJordan);
        gaussJordan.setText("ELimacion Gauss jordan");

        buttonGroup2.add(inversion);
        inversion.setText("Inversion de matrices");

        ResultadoArea.setColumns(20);
        ResultadoArea.setRows(5);
        jScrollPane1.setViewportView(ResultadoArea);

        BtnCalcular.setText("Calcular");
        BtnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularActionPerformed(evt);
            }
        });

        BtnComparar.setText("Comparar");
        BtnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCompararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(subíndice7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subíndice4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subíndice1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subíndice2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subíndice3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subíndice8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subíndice9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subíndice5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subíndice6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gaussJordan)
                            .addComponent(gaussiana)
                            .addComponent(inversion)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnComparar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subíndice2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subíndice3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subíndice1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gaussiana)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gaussJordan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inversion))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subíndice6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(subíndice5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(subíndice4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subíndice7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subíndice9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnComparar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(subíndice8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void subíndice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice1ActionPerformed

    private void subíndice5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice5ActionPerformed

    private void subíndice7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice7ActionPerformed

    private void subíndice6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice6ActionPerformed

    private void subíndice9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice9ActionPerformed

    private void subíndice8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subíndice8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subíndice8ActionPerformed

    private void gaussianaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussianaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gaussianaActionPerformed

    private void BtnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularActionPerformed
           Fraccion[][] matriz = leerMatrizDesdeCampos();
    
    // Salir del método si no se pudo leer la matriz correctamente (por falta de valores)
    if (matriz == null) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los valores necesarios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Verificar que se haya seleccionado un método
    if (!gaussiana.isSelected() && !gaussJordan.isSelected() && !inversion.isSelected()) {
     //   JOptionPane.showMessageDialog(null, "Por favor, seleccione un método antes de calcular.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; // Salir del método si no se ha seleccionado ningún método
    }

    long startTime = System.nanoTime(); // Iniciar temporizador
    Fraccion[] solucion = null; // Variable para almacenar la solución

    // Determinar qué método se ha seleccionado y calcular la solución
     if (gaussiana.isSelected()) {
               Fraccion[][] resultado = eliminacionGaussiana(matriz);
    } else if (gaussJordan.isSelected()) {
               Fraccion[][] resultado = eliminacionGaussJordan(matriz);
    } else if (inversion.isSelected()) {
               Fraccion[][] resultado = invertirMatriz(matriz);
    }
    long endTime = System.nanoTime(); // Finalizar temporizador
    double executionTime = (endTime - startTime) / 1e6; // Tiempo en milisegundos

    // Mostrar la matriz y el resultado
    mostrarMatrizYResultado(matriz, solucion, executionTime);

        
    }//GEN-LAST:event_BtnCalcularActionPerformed

    private void BtnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCompararActionPerformed
          Fraccion[][] matriz = leerMatrizDesdeCampos();
    
    // Salir del método si no se pudo leer la matriz correctamente (por falta de valores)
    if (matriz == null) {
       // JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los valores necesarios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Llamar al método de comparación
    compararMetodos(matriz);
    
    }//GEN-LAST:event_BtnCompararActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCalculadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcular;
    private javax.swing.JButton BtnComparar;
    private javax.swing.JTextArea ResultadoArea;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton gaussJordan;
    private javax.swing.JRadioButton gaussiana;
    private javax.swing.JRadioButton inversion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField subíndice1;
    private javax.swing.JTextField subíndice2;
    private javax.swing.JTextField subíndice3;
    private javax.swing.JTextField subíndice4;
    private javax.swing.JTextField subíndice5;
    private javax.swing.JTextField subíndice6;
    private javax.swing.JTextField subíndice7;
    private javax.swing.JTextField subíndice8;
    private javax.swing.JTextField subíndice9;
    // End of variables declaration//GEN-END:variables
}
