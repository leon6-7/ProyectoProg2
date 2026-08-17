package proyecto;

public interface Almacenamiento<T> {
    boolean agregar(T dato);
    T[] obtenerTodos();
    int getCantidad();
}
