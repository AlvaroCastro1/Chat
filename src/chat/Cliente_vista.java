package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alvaro
 */
public class Cliente_vista extends javax.swing.JFrame implements Runnable {

    private final int puerto = 5000;
    private final int puerto2 = 9090;
    //private final String host_server = "localhost";
    private final String host_server = "192.168.1.100";
    private String mi_nombre = "";
    private String mi_ip = "";
    private Timer timer;

    public void reloj() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
        timer.start();
    }

    private void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        jl_reloj.setText(dateTime);
    }

    public Cliente_vista() {
        initComponents();
        reloj();
        setLocationRelativeTo(null);
        mi_nombre = JOptionPane.showInputDialog(null, "¿Cual es tu nombre de usuario?");
        jl_nombre.setText("Cliente: " + mi_nombre);
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_Titulo = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jl_reloj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_contactos = new javax.swing.JTable();
        btn_individual = new javax.swing.JButton();
        btn_grupal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jl_Titulo.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_Titulo.setText("Cliente");

        jl_nombre.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_nombre.setText("Nombre:");

        jl_reloj.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_reloj.setText("hora");

        tabla_contactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "IP"
            }
        ));
        jScrollPane1.setViewportView(tabla_contactos);

        btn_individual.setText("Chat Individual");
        btn_individual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_individualActionPerformed(evt);
            }
        });

        btn_grupal.setText("Chat Grupal");
        btn_grupal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grupalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_individual, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_grupal, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jl_Titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jl_Titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jl_reloj))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_grupal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btn_individual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            
            // Obtener la dirección IP de localhost
            InetAddress localhost = InetAddress.getLocalHost();
            mi_ip = localhost.getHostAddress();
            System.out.println(mi_ip);

            Cliente_conectado datos = new Cliente_conectado(mi_nombre, mi_ip, null);

            try (Socket misocket = new Socket(host_server, puerto); ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream())) {

                paquete_datos.writeObject(datos);
            }
        } catch (Exception e2) {
            System.out.println("Error " + e2);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btn_individualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_individualActionPerformed
        int selectedRow = tabla_contactos.getSelectedRow();

        if (selectedRow != -1) { // Si hay una fila seleccionada
            // Obtener los datos de la fila seleccionada
            String nombre_s = tabla_contactos.getValueAt(selectedRow, 0).toString();
            String ip_s = tabla_contactos.getValueAt(selectedRow, 1).toString();

            try {
                Socket miSocket = new Socket(host_server, puerto);

                // Mostrar el chat
                Solicitud_chat_individual sl = new Solicitud_chat_individual(mi_nombre, mi_ip, nombre_s, ip_s, host_server);
                chat_vista nuevo = new chat_vista(sl);
                nuevo.setVisible(true);
                // enviar "solicitud" chat al server
                ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
                paquete_datos.writeObject(sl);
                paquete_datos.close();

            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun Contacto.", "Ocurrió un error.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_individualActionPerformed

    private void btn_grupalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grupalActionPerformed
        Map<String, String> Clientes_actuales = new HashMap<>();

        // Obtener los datos de la tabla y agregarlos al HashMap
        for (int i = 0; i < tabla_contactos.getRowCount(); i++) {
            String nombre = tabla_contactos.getValueAt(i, 0).toString();
            String ip = tabla_contactos.getValueAt(i, 1).toString();
            Clientes_actuales.put(ip, nombre);
        }

        // Crear un array de checkboxes con el mismo tamaño que el HashMap
        JCheckBox[] checkBoxes = new JCheckBox[Clientes_actuales.size()];

        // Crear los checkboxes y asignarles el texto del HashMap
        int index = 0;
        for (Map.Entry<String, String> entry : Clientes_actuales.entrySet()) {
            checkBoxes[index] = new JCheckBox(entry.getValue());
            index++;
        }

        // Mostrar el JOptionPane con los checkboxes
        int option = JOptionPane.showOptionDialog(null, checkBoxes, "Selecciona los elementos",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        // Array contacto que seran del grupo
        Map<String, String> Clientes_del_grupo = new HashMap<>();
        // Imprimir los elementos seleccionados
        if (option == JOptionPane.OK_OPTION) {
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    String nombre = checkBox.getText();
                    String ip = "";
                    // Recorrer el HashMap para encontrar la llave correspondiente al valor deseado
                    for (Map.Entry<String, String> entry : Clientes_actuales.entrySet()) {
                        if (entry.getValue().equals(nombre)) {
                            ip = entry.getKey();
                            break; // Salir del bucle al encontrar la primera coincidencia
                        }
                    }
                    Clientes_del_grupo.put(ip, nombre);
                }
            }
        }

        String nombre_grupo = JOptionPane.showInputDialog("Ingresa el nombre del Grupo:");
        // Verificar si el String está vacío
        if (nombre_grupo != null && !nombre_grupo.isEmpty()) {
            //enviar solicitud al servidor
            try {
                Socket miSocket = new Socket(host_server, puerto);

                Solicitud_chat_grupal scg = new Solicitud_chat_grupal(host_server, nombre_grupo, Clientes_del_grupo);
                // enviar "solicitud" chat al server
                ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
                paquete_datos.writeObject(scg);
                paquete_datos.close();

            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            }
            Solicitud_chat_grupal scg = new Solicitud_chat_grupal(host_server, nombre_grupo, Clientes_del_grupo);

        } else {
            System.out.println("No se ingresó ningún texto.");
        }
    }//GEN-LAST:event_btn_grupalActionPerformed

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
                if ("Linux".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_vista().setVisible(true);
            }
        });
    }

    public void run() {
        try (ServerSocket servidor = new ServerSocket(puerto2)) {
            while (true) {
                try (Socket miSocket = servidor.accept(); ObjectInputStream paquete_datos = new ObjectInputStream(miSocket.getInputStream())) {

                    Object objeto_recibido = paquete_datos.readObject();

                    if (objeto_recibido instanceof Cliente_conectado) {
                        Cliente_conectado cc = (Cliente_conectado) objeto_recibido;

                        DefaultTableModel model = (DefaultTableModel) tabla_contactos.getModel();
                        model.setRowCount(0);

                        for (Map.Entry<String, String> entry : cc.getClientes().entrySet()) {
                            String nombre = entry.getValue();
                            String ip = entry.getKey();
                            model.addRow(new Object[]{nombre, ip});
                        }

                        tabla_contactos.setModel(model);
                    } else if (objeto_recibido instanceof Solicitud_chat_individual) {
                        Solicitud_chat_individual datos_chat_ind = (Solicitud_chat_individual) objeto_recibido;

                        Solicitud_chat_individual sl = new Solicitud_chat_individual(
                                datos_chat_ind.getDestinatario_nombre(),
                                datos_chat_ind.getDestinatario_ip(),
                                datos_chat_ind.getMi_nombre(),
                                datos_chat_ind.getMi_ip(),
                                host_server);
                        chat_vista nuevo = new chat_vista(sl);
                        nuevo.setVisible(true);
                    } else if (objeto_recibido instanceof Mensaje_ind) {
                        System.out.println("Se recibió un mensaje individual");
                    } else if (objeto_recibido instanceof Solicitud_chat_grupal) {
                        Solicitud_chat_grupal datos_chat_grup = (Solicitud_chat_grupal) objeto_recibido;
                        chat_grupal_vista chv = new chat_grupal_vista(datos_chat_grup, mi_nombre);
                        chv.setVisible(true);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_grupal;
    private javax.swing.JButton btn_individual;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jl_Titulo;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_reloj;
    private javax.swing.JTable tabla_contactos;
    // End of variables declaration//GEN-END:variables
}
