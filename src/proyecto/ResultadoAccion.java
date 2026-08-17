package proyecto;

public class ResultadoAccion {
    private final boolean exito;
    private final String mensaje;

    public ResultadoAccion(boolean exito,String mensaje){this.exito=exito;
        this.mensaje=mensaje;
    }

    public boolean isExito(){return exito;
    } public String getMensaje(){return mensaje;
    }
}
