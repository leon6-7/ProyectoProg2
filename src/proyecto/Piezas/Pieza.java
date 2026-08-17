package proyecto.Piezas;


public abstract class Pieza {
    protected int vida;
    protected int ataque;
    protected int escudo;
    protected boolean piezaColor; 
    protected boolean enrango;
    protected EstadoPieza estado;
    protected String ruta;
    protected int fila;
    protected int columna;

    public Pieza(int posx, int posy) {
        this.fila = posx;
        this.columna = posy;
        this.estado = EstadoPieza.CONVIDA;
    }

    public abstract void habilidad(Pieza target);

    
    public void ataque(Pieza target) {
        if (target == null || target.color() == this.color() || !estaViva() || !target.estaViva()) return;
        aplicarDanio(target, this.ataque, false);
        target.checkMuerte();
    }

    protected final void aplicarDanio(Pieza target, int danio, boolean ignoraEscudo) {
        if (ignoraEscudo) {
            target.vida -= danio;
            return;
        }
        int absorbido = Math.min(target.escudo, danio);
        target.escudo -= absorbido;
        target.vida -= (danio - absorbido);
    }

    public final void checkMuerte() {
        if (vida <= 0) {
            vida = 0;
            estado = EstadoPieza.CAPTURADA;
        }
    }

    public final int getVida() { return vida; }
    public final int getAtaque() { return ataque; }
    public final int getEscudo() { return escudo; }
    public final boolean color() { return piezaColor; }
    public final boolean rango() { return enrango; }
    public final EstadoPieza getEstado() { return estado; }
    public final String getRuta() { return ruta; }
    public final int getFila() { return fila; }
    public final int getColumna() { return columna; }
    public final void setPosicion(int fila, int columna) { this.fila = fila; this.columna = columna; }
    public final void setBlanco() { piezaColor = true; }
    public final void setNegro() { piezaColor = false; }
    public final boolean estaViva() { return estado == EstadoPieza.CONVIDA; }
    public final String getColorTexto() { return piezaColor ? "Blanco" : "Negro"; }
    public abstract String getTipo();
}
