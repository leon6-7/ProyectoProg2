package proyecto.Piezas;


public class Zombie extends Pieza {
    public Zombie(int posx, int posy) {
        super(posx, posy);
        vida = 1;
        escudo = 0;
        ataque = 1;
        ruta = "C:\\Proyectos\\Programacion\\ProyectoProg2\\Proyecto\\src\\Imagenes\\zombie_w.png";
    }

    @Override
    public void habilidad(Pieza target) {
        if (target == null || target.color() == this.color() || !estaViva()) return;
        aplicarDanio(target, 1, false);
        target.checkMuerte();
    }

    @Override public String getTipo() { return "Zombie"; }
}
