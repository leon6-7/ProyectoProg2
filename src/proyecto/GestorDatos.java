package proyecto;

public final class GestorDatos {
    private static final AlmacenJugadores JUGADORES = new AlmacenJugadores(100);

    private GestorDatos() {}

    public static AlmacenJugadores jugadores(){ return JUGADORES;
    }
}
