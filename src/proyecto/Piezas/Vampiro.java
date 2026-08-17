package proyecto.Piezas;


public class Vampiro extends Pieza {
    public Vampiro(int posx, int posy) {
        super(posx, posy);
        ataque = 3;
        vida = 4;
        escudo = 5;
        ruta = "/Imagenes/vampire_w.png";
    }

    @Override
    public void habilidad(Pieza target) {
        if (target == null || target.color() == this.color() || !estaViva() || !target.estaViva()) return;
        aplicarDanio(target, 1, false);
        this.vida += 1;
        target.checkMuerte();
    }

    @Override public String getTipo() { return "Vampiro"; }
}
