package proyecto.Piezas;


public class HombreLobo extends Pieza {
    public HombreLobo(int posx, int posy) {
        super(posx, posy);
        ataque = 5;
        vida = 4;
        escudo = 4;
        ruta = ("/Imagenes/werewolf_w.png");
    }

    @Override
    public void habilidad(Pieza target) {
        
        ataque(target);
    }

    @Override public String getTipo() { return "Hombre Lobo"; }
}
