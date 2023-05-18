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

    public Solicitud_chat_individual(String mi_nombre, String mi_ip, String destinatario_nombre, String destinatario_ip, String host_server) {
        this.host_server = host_server;
        this.mi_nombre = mi_nombre;
        this.mi_ip = mi_ip;
        this.destinatario_nombre = destinatario_nombre;
        this.destinatario_ip = destinatario_ip;
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
