package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro
 */
public class Servidor_vista extends javax.swing.JFrame implements Runnable {

    private final int puerto = 5000;
    private final int puerto2 = 9090;
    //aqui se empezaran a crear los chat
    private int puerto_inicial = 49152;
    HashMap<String, String> Clientes_conectados = new HashMap<>();

    public Servidor_vista() {
        mostrar_Ip();
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public void mostrar_Ip() {
        try {
            // Obtener la dirección IP local
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();

            // Mostrar la dirección IP en un JOptionPane
            JOptionPane.showMessageDialog(null, "La dirección IP del Servidor es: " + ipAddress, "COMPARTELA", JOptionPane.INFORMATION_MESSAGE);

        } catch (UnknownHostException e) {
            // Manejar posibles errores al obtener la dirección IP
            JOptionPane.showMessageDialog(null, "No se pudo obtener la dirección IP de tu computadora.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        area_texto = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        area_texto.setColumns(20);
        area_texto.setRows(5);
        jScrollPane1.setViewportView(area_texto);

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel1.setText("Servidor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Servidor_vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor_vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor_vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor_vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor_vista().setVisible(true);
            }
        });
    }

    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);

            while (true) {
                System.out.println("Escuchando");
                Socket miSocket = servidor.accept();
                ObjectInputStream paquete_datos = new ObjectInputStream(miSocket.getInputStream());
                Object objeto_recibido = paquete_datos.readObject();

                if (objeto_recibido instanceof Cliente_conectado) {
                    Cliente_conectado cc = (Cliente_conectado) objeto_recibido;
                    area_texto.append("\n" + cc.toString());
                    Clientes_conectados.put(cc.getIp(), cc.getNombre());
                    cc.setClientes(Clientes_conectados);

                    for (Map.Entry<String, String> entry : Clientes_conectados.entrySet()) {
                        String ipAddress = entry.getKey();
                        String username = entry.getValue();

                        try {
                            Socket socket = new Socket(ipAddress, 9090);

                            // Configurar el objeto como desees antes de enviarlo
                            // Enviar el objeto a través del socket
                            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                            outputStream.writeObject(cc);

                            // Cerrar el socket después de enviar el objeto
                            socket.close();

                            System.out.println("Objeto enviado a " + username + " (" + ipAddress + ")");
                        } catch (IOException e) {
                            // Manejar cualquier excepción que pueda ocurrir durante el envío del objeto
                            System.err.println("Error al enviar el objeto a " + username + " (" + ipAddress + "): " + e.getMessage());
                        }
                    }
                } else if (objeto_recibido instanceof Solicitud_chat_individual) {
                    
                    Solicitud_chat_individual solicitud = (Solicitud_chat_individual) objeto_recibido;
                    area_texto.append("\nChat ind entre " + solicitud.getMi_nombre() + " y " + solicitud.getDestinatario_nombre());
                    // primero cambiamos el puerto
                    solicitud.setPuerto_chat(puerto_inicial);
                    //añadimos +1 para que no se repita
                    puerto_inicial++;
                    
                    //enviar solicitud al Destino y que lo abra
                    Socket enviaDestinatario = new Socket(solicitud.getMi_ip(), puerto2);
                    ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                    System.out.println("enviare al destino " + solicitud.toString());
                    paqueteReenvio.writeObject(solicitud);
                    
                    //cerrar streams
                    enviaDestinatario.close();
                    paqueteReenvio.close();
                    
                    //datos para al origen
                    Solicitud_chat_individual solicitud_regresar = solicitud;
                    String nombreDT = solicitud_regresar.getDestinatario_nombre();
                    String ipDT = solicitud_regresar.getDestinatario_ip();
                    String nombreOT = solicitud_regresar.getMi_nombre();
                    String ipOT = solicitud_regresar.getMi_ip();
                    
                    solicitud_regresar.setDestinatario_ip(ipOT);
                    solicitud_regresar.setDestinatario_nombre(nombreOT);
                    solicitud_regresar.setMi_ip(ipDT);
                    solicitud_regresar.setMi_nombre(nombreDT);
                    System.out.println("enviare al origen " + solicitud_regresar.toString());

                    //responder al origen
                    try {
                        Socket socket = new Socket(ipDT, puerto2);

                        // Configurar el objeto como desees antes de enviarlo
                        // Enviar el objeto a través del socket
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        outputStream.writeObject(solicitud);

                        // Cerrar el socket después de enviar el objeto
                        socket.close();
                        outputStream.close();

                    } catch (IOException e) {
                        // Manejar cualquier excepción que pueda ocurrir durante el envío del objeto
                        System.err.println("Error al enviar respuesta a " + ipOT + "): " + e.getMessage());
                    }

                } else if (objeto_recibido instanceof Mensaje_ind) {
//                    Mensaje_ind msj_i = (Mensaje_ind) objeto_recibido;
//                    area_texto.append("\nmensaje de " + msj_i.getRemitente_nombre() + " para " + msj_i.getDestinatario_nombre());
//
//                    Socket enviaDestinatario = new Socket(msj_i.getDestinatario_ip(), p_com, );
//                    ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
//                    paqueteReenvio.writeObject(msj_i);
//                    enviaDestinatario.close();
//                    paqueteReenvio.close();
                } else if (objeto_recibido instanceof Solicitud_chat_grupal) {
                    Solicitud_chat_grupal solicitud = (Solicitud_chat_grupal) objeto_recibido;
                    area_texto.append("\nNuevo Chat grupal " + solicitud.getNombre_grupo());
                    area_texto.append(solicitud.getClientes().toString());

                    enviar_objeto(solicitud, solicitud.getClientes());
                } else if (objeto_recibido instanceof Mensaje_grupal) {
//                    Mensaje_grupal msj_i = (Mensaje_grupal) objeto_recibido;
//                    area_texto.append("\nmensaje del Grupo " + msj_i.getNombre_Grupo());
//
//                    for (Map.Entry<String, String> entry : msj_i.getClientes_grupo().entrySet()) {
//                        String ip_t = entry.getKey();
//                        Socket enviaDestinatario = new Socket(ip_t, p_com_g);
//                        ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
//                        paqueteReenvio.writeObject(msj_i);
//                        enviaDestinatario.close();
//                        paqueteReenvio.close();
//                    }
                }
                miSocket.close();
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void enviar_objeto(Object objeto, Map<String, String> clientes) {
        for (Map.Entry<String, String> entry : clientes.entrySet()) {
            String nombre = entry.getValue();
            String ip = entry.getKey();

            try {
                Socket miSocket = new Socket(ip, puerto2);

                // enviar "solicitud" chat al cliente
                ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
                paquete_datos.writeObject(objeto);
                paquete_datos.close();

            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_texto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
