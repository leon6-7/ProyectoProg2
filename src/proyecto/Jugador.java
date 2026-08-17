package proyecto;

public class Jugador {
    private final String usuario;
    private String contrasena;
    private int puntos;
    private boolean activo;

    public Jugador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.puntos = 0;
        this.activo = true;
    }

    public String getUsuario() { return usuario;
    }

    public String getContrasena() { return contrasena;
    }

    public void setContrasena(String contrasena) { this.contrasena = contrasena;
    }

    public int getPuntos() { return puntos;
    }

    public void sumarPuntos(int puntos) { this.puntos += puntos;
    }

    public boolean isActivo() { return activo;
    }

    public void cerrarCuenta() { activo = false;
    }
}
