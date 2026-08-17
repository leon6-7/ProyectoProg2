package proyecto;

public class AlmacenJugadores implements Almacenamiento<Jugador> {
    private final Jugador[] datos;
    private int cantidad;

    public AlmacenJugadores(int capacidad) { datos = new Jugador[capacidad];
    }
    @Override public boolean agregar(Jugador dato) {
        if (dato == null || cantidad >= datos.length) return false;
        if (buscar(dato.getUsuario()) != null) return false;
        datos[cantidad++] = dato;
        return true;
    }

    public Jugador buscar(String clave) {
        return buscarRecursivo(clave, 0);
    }

    private Jugador buscarRecursivo(String clave, int indice) {
        if (indice >= cantidad) return null;
        if (datos[indice].getUsuario().equalsIgnoreCase(clave)) return datos[indice];
        return buscarRecursivo(clave, indice + 1);
    }
    @Override public Jugador[] obtenerTodos() {
        Jugador[] copia = new Jugador[cantidad];
        for (int i=0;i<cantidad;i++) copia[i]=datos[i];
        return copia;
    }
    @Override public int getCantidad() { return cantidad;
    }
}
