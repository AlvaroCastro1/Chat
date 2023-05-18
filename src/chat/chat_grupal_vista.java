package chat;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro
 */
public class chat_grupal_vista extends javax.swing.JFrame implements Runnable {

    private final int puerto = 5000;
    private final int puerto2 = 9090;
    private final int p_com = 3030;



    private String host_server;
    private String Nombre_grupo;
    private Map<String, String> Clientes;

    public chat_grupal_vista(Solicitud_chat_grupal s) {
        initComponents();
        setLocationRelativeTo(null);

        this.host_server = s.getHost_server();
        this.Nombre_grupo = s.getNombre_grupo();
        this.Clientes = s.getClientes();


        jl_Titulo.setText(Nombre_grupo);
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_Titulo = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        txt_mensaje = new javax.swing.JTextField();
        btn_enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_chat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_Titulo.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_Titulo.setText("Chat Grupal");

        txt_mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mensajeKeyTyped(evt);
            }
        });

        btn_enviar.setText("Enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        area_chat.setColumns(20);
        area_chat.setRows(5);
        jScrollPane1.setViewportView(area_chat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_Titulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jl_Titulo)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enviar))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        try {   
            Socket miSocket = new Socket(host_server, puerto);

            String texto_mensaje = txt_mensaje.getText().trim();
            
            Mensaje_grupal m = new Mensaje_grupal(Clientes,txt_mensaje.getText(), jl_Titulo.getText(), host_server);

            ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
            paquete_datos.writeObject(m);
            paquete_datos.close();

        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        //limpiar al final
        txt_mensaje.setText("");
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void txt_mensajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mensajeKeyTyped
        char tecla_press = evt.getKeyChar();
        if (tecla_press == KeyEvent.VK_ENTER) {
            btn_enviarActionPerformed(null);
        }
    }//GEN-LAST:event_txt_mensajeKeyTyped

    public void run() {
        try {
            // System.out.println("escuchando");
            ServerSocket servidor = new ServerSocket(p_com);

            while (true) {
                Socket miSocket = servidor.accept();

                ObjectInputStream paquete_datos = new ObjectInputStream(miSocket.getInputStream());

                Object objeto_recibido = paquete_datos.readObject();
                if (objeto_recibido instanceof Mensaje_grupal) {
                    Mensaje_grupal paquete_mensaje = (Mensaje_grupal) objeto_recibido;
                    // verificamos que este sea el chat donde se debe mostrar el mensaje
                    
                    if (Nombre_grupo.equals(paquete_mensaje.getNombre_Grupo())) {
                        area_chat.append("\n"+paquete_mensaje.getMensaje());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_chat;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jl_Titulo;
    private javax.swing.JSeparator separador;
    private javax.swing.JTextField txt_mensaje;
    // End of variables declaration//GEN-END:variables
}
