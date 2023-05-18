package chat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class Mensaje_grupal implements Serializable{

    private Map<String, String> Clientes_grupo;

    private String Host_Server;

    private String Mensaje;
    private String Nombre_Grupo;

    public Mensaje_grupal(Map<String, String> Clientes_grupo, String Mensaje, String Nombre_Grupo, String Host_Server) {
        this.Clientes_grupo = Clientes_grupo;
        this.Host_Server = Host_Server;
        this.Mensaje = Mensaje;
        this.Nombre_Grupo = Nombre_Grupo;
    }
    

    public Map<String, String> getClientes_grupo() {
        return Clientes_grupo;
    }

    public void setClientes_grupo(Map<String, String> Clientes_grupo) {
        this.Clientes_grupo = Clientes_grupo;
    }

    public String getHost_Server() {
        return Host_Server;
    }

    public void setHost_Server(String Host_Server) {
        this.Host_Server = Host_Server;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getNombre_Grupo() {
        return Nombre_Grupo;
    }

    public void setNombre_Grupo(String Nombre_Grupo) {
        this.Nombre_Grupo = Nombre_Grupo;
    }
    

   
}
