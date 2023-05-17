package chat;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 */
public class Cliente_conectado implements Serializable{
    private String Nombre;
    private String Ip;

    public Cliente_conectado(String Nombre, String Ip) {
        this.Nombre = Nombre;
        this.Ip = Ip;
    }

    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    @Override
    public String toString() {
        return "Cliente conectado! \n" + "\tNombre: " + Nombre + ", Ip: " + Ip;
    }
    
    
}
