package proyecto.Piezas;


public class Necromancer extends Pieza {
    public Necromancer(int posx, int posy) {
        super(posx, posy);
        ataque = 4;
        vida = 3;
        escudo = 1;
        ruta = "C:\\Proyectos\\Programacion\\ProyectoProg2\\Proyecto\\src\\Imagenes\\necromancer_w.png";
    }

    
    @Override
    public void habilidad(Pieza target) {
        if (target == null || target.color() == this.color() || !estaViva() || !target.estaViva()) return;
        aplicarDanio(target, ataque, false);
        target.checkMuerte();
    }

    
    public void atacarConLanza(Pieza target) {
        if (target == null || target.color() == this.color()) return;
        aplicarDanio(target, 2, true);
        target.checkMuerte();
    }

    @Override public String getTipo() { return "Necromante"; }
}
