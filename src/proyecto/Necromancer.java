
package proyecto;

public class Necromancer extends Pieza{
    public Necromancer(){
    super(5,5,2);
    
    }
    
    @Override
    public void habilidad(Pieza target){
    if(target.color()!=this.color()&&this.enrango){
        if(target.escudo==0){
            target.vida=target.vida-this.getAtaque();
            }
        }
    }    
    
}

