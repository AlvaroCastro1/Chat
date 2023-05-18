package chat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class Cliente_conectado implements Serializable{
    private String Nombre;
    private String Ip;
    private Map<String, String> Clientes;

    public Cliente_conectado(String Nombre, String Ip, Map<String, String> Clientes) {
        this.Nombre = Nombre;
        this.Ip = Ip;
        this.Clientes = Clientes;
    }

    

    public Map<String, String> getClientes() {
        return Clientes;
    }

    public void setClientes(Map<String, String> Clientes) {
        this.Clientes = Clientes;
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
