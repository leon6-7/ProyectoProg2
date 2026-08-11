
package proyecto;

public class Vampiro extends Pieza{
    
    public Vampiro(){
    super(4,3,5);
    
    }
    
    @Override 
    public void habilidad(Pieza target){
    if(target.color()!=this.color()&&this.enrango){
        if(target.escudo==0){
            target.vida=target.vida-1;
            this.vida+=1;
            }
        }
    }    
}

