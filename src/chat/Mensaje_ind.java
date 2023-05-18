package chat;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 */
public class Mensaje_ind implements Serializable{
    private String Remitente_nombre;
    private String Remitente_ip;
    private String Destinatario_nombre;
    private String Destinatario_ip;
    private String Host_Server;
    private String Mensaje;

    public Mensaje_ind(String Remitente_nombre, String Remitente_ip, String Destinatario_nombre, String Destinatario_ip, String Host_Server, String Mensaje) {
        this.Remitente_nombre = Remitente_nombre;
        this.Remitente_ip = Remitente_ip;
        this.Destinatario_nombre = Destinatario_nombre;
        this.Destinatario_ip = Destinatario_ip;
        this.Host_Server = Host_Server;
        this.Mensaje = Mensaje;
    }
    
    

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getDestinatario_nombre() {
        return Destinatario_nombre;
    }

    public void setDestinatario_nombre(String Destinatario_nombre) {
        this.Destinatario_nombre = Destinatario_nombre;
    }

    public String getDestinatario_ip() {
        return Destinatario_ip;
    }

    public void setDestinatario_ip(String Destinatario_ip) {
        this.Destinatario_ip = Destinatario_ip;
    }

    public String getHost_Server() {
        return Host_Server;
    }

    public void setHost_Server(String Host_Server) {
        this.Host_Server = Host_Server;
    }

    public String getRemitente_nombre() {
        return Remitente_nombre;
    }

    public void setRemitente_nombre(String Remitente_nombre) {
        this.Remitente_nombre = Remitente_nombre;
    }

    public String getRemitente_ip() {
        return Remitente_ip;
    }

    public void setRemitente_ip(String Remitente_ip) {
        this.Remitente_ip = Remitente_ip;
    }
}
