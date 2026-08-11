
package proyecto;

public abstract class Pieza {
    protected int vida;
    protected int ataque;
    protected int escudo;
    protected boolean blanca;
    protected boolean enrango;
    public Pieza(int vida, int ataque, int escudo){
        this.vida=vida;
        this.ataque=ataque;
        this.escudo=escudo;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public boolean color() {
        return blanca;
    }
    public boolean rango(){
        return enrango;
    }
    public void setBlanco(){
        blanca = true;
    }
    
    public void setNegro(){
        blanca = false;
    }
    
    public void ataque(Pieza target){
    if(target.color()!=this.color()&&this.enrango){
        if(target.escudo==0){
            target.vida=target.vida-this.getAtaque();
            }
        }
    }    
    public abstract void habilidad(Pieza target);
    
    
    
}
