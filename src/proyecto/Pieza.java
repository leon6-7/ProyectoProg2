
package proyecto;

public abstract class Pieza {
    protected int vida;
    protected int ataque;
    protected int escudo;
    protected boolean piezaColor; //true es blanco y false es negro
    protected boolean enrango;
    protected EstadoPieza estado;
    public Pieza(int vida, int ataque, int escudo){
        this.vida=vida;
        this.ataque=ataque;
        this.escudo=escudo;
        this.estado=EstadoPieza.CONVIDA;
    }
    public void checkMuerte(){
        if(vida<=0){
            estado=EstadoPieza.CAPTURADA;
        }          
    }
    
    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public boolean color() {
        return piezaColor;
    }
    public boolean rango(){
        return enrango;
    }
    public void setBlanco(){
        piezaColor = true;
    }
    
    public void setNegro(){
        piezaColor = false;
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
