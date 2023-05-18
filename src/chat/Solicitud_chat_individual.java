package chat;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 */
public class Solicitud_chat_individual implements Serializable{

    private String host_server;

    private String mi_nombre;
    private String mi_ip;

    private String destinatario_nombre;
    private String destinatario_ip;
    
    private int puerto_chat;

    @Override
    public String toString() {
        return "Solicitud_chat_individual{" + "host_server=" + host_server + ", mi_nombre=" + mi_nombre + ", mi_ip=" + mi_ip + ", destinatario_nombre=" + destinatario_nombre + ", destinatario_ip=" + destinatario_ip + ", puerto_chat=" + puerto_chat + '}';
    }

    public Solicitud_chat_individual(String mi_nombre, String mi_ip, String destinatario_nombre, String destinatario_ip, String host_server, int puerto_chat) {
        this.host_server = host_server;
        this.mi_nombre = mi_nombre;
        this.mi_ip = mi_ip;
        this.destinatario_nombre = destinatario_nombre;
        this.destinatario_ip = destinatario_ip;
        this.puerto_chat = puerto_chat;
    }

    public int getPuerto_chat() {
        return puerto_chat;
    }

    public void setPuerto_chat(int puerto_chat) {
        this.puerto_chat = puerto_chat;
    }

    
    public String getHost_server() {
        return host_server;
    }

    public void setHost_server(String host_server) {
        this.host_server = host_server;
    }

    public String getMi_nombre() {
        return mi_nombre;
    }

    public void setMi_nombre(String mi_nombre) {
        this.mi_nombre = mi_nombre;
    }

    public String getMi_ip() {
        return mi_ip;
    }

    public void setMi_ip(String mi_ip) {
        this.mi_ip = mi_ip;
    }

    public String getDestinatario_nombre() {
        return destinatario_nombre;
    }

    public void setDestinatario_nombre(String destinatario_nombre) {
        this.destinatario_nombre = destinatario_nombre;
    }

    public String getDestinatario_ip() {
        return destinatario_ip;
    }

    public void setDestinatario_ip(String destinatario_ip) {
        this.destinatario_ip = destinatario_ip;
    }
}
