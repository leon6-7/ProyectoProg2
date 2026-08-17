package proyecto;

import java.util.Random;
import proyecto.Piezas.Pieza;
import proyecto.Piezas.Necromancer;
import proyecto.Piezas.Vampiro;
import proyecto.Piezas.Zombie;
import proyecto.Piezas.HombreLobo;

public class GestorJuego {
    private final Partida partida;
    private final Random random=new Random();
    private String tipoPermitido;
    private int girosUsados;

    public GestorJuego(Partida partida){this.partida=partida;
    }

    public Partida getPartida(){return partida;
    }

    public String getTipoPermitido(){return tipoPermitido;
    }

    public int getGirosPermitidos(){
        int perdidas=6-partida.contarPiezasPrincipalesVivas(partida.isTurnoBlanco());
        if(perdidas>=4)return 3;
        if(perdidas>=2)return 2;
        return 1;
    }

    public boolean puedeGirar(){return girosUsados<getGirosPermitidos();
    }

    public String girarRuleta(){
        if(!puedeGirar()) return null;
        String[] tipos={"Necromante","Hombre Lobo","Vampiro"};
        tipoPermitido=tipos[random.nextInt(tipos.length)];
        girosUsados++;
        return tipoPermitido;
    }

    public void reiniciarGiros(){girosUsados=0;
        tipoPermitido=null;
    }

    public boolean hayGanador(){ return partida.contarPiezasVivas(true)==0 || partida.contarPiezasVivas(false)==0;
    }

    public Jugador ganador(){
        if(partida.contarPiezasVivas(true)==0)return partida.getJugadorNegro();
        if(partida.contarPiezasVivas(false)==0)return partida.getJugadorBlanco();
        return null;
    }

    public void terminarTurno(){ partida.cambiarTurno();
        reiniciarGiros();
    }

    public boolean perteneceAlTurno(Pieza p){return p!=null&&p.estaViva()&&p.color()==partida.isTurnoBlanco();
    }

    public boolean esTipoPermitido(Pieza p){return p!=null&&p.getTipo().equals(tipoPermitido);
    }

    public boolean puedeMover(int of,int oc,int df,int dc){
        Pieza p=partida.getTablero()[of][oc];
        if(!perteneceAlTurno(p)||!esTipoPermitido(p)||partida.getTablero()[df][dc]!=null)return false;
        int dx=Math.abs(df-of),dy=Math.abs(dc-oc);
        if(p instanceof Zombie)return false;
        if(p instanceof HombreLobo && (dx<=2&&dy<=2&&!(dx==0&&dy==0))){

            if(dx==2 && dy==0 && partida.getTablero()[(of+df)/2][oc]!=null)return false;
            if(dx==0 && dy==2 && partida.getTablero()[of][(oc+dc)/2]!=null)return false;
            if(dx==2 && dy==2 && partida.getTablero()[(of+df)/2][(oc+dc)/2]!=null)return false;
            return true;
        }
        return dx<=1&&dy<=1&&(dx+dy>0);
    }

    public ResultadoAccion mover(int of,int oc,int df,int dc){
        if(!puedeMover(of,oc,df,dc))return new ResultadoAccion(false,"Movimiento no valido.");
        Pieza p=partida.getTablero()[of][oc];
        partida.getTablero()[df][dc]=p;
        partida.getTablero()[of][oc]=null;
        p.setPosicion(df,dc);
        return new ResultadoAccion(true,"Movimiento realizado.");
    }

    private boolean adyacente(int a,int b,int c,int d){return Math.abs(a-c)<=1&&Math.abs(b-d)<=1&&(a!=c||b!=d);
    }

    private boolean enLineaDos(int a,int b,int c,int d){return (a==c&&Math.abs(b-d)==2)||(b==d&&Math.abs(a-c)==2);
    }

    public ResultadoAccion atacarNormal(int of,int oc,int df,int dc){
        Pieza atacante=partida.getTablero()[of][oc], objetivo=partida.getTablero()[df][dc];
        if(!validarAtaque(atacante,objetivo,of,oc,df,dc)||!adyacente(of,oc,df,dc))return new ResultadoAccion(false,"El ataque normal debe ser adyacente.");
        atacante.ataque(objetivo);
        return terminarAtaque(objetivo);
    }

    public ResultadoAccion absorber(int of,int oc,int df,int dc){
        Pieza atacante=partida.getTablero()[of][oc], objetivo=partida.getTablero()[df][dc];
        if(!(atacante instanceof Vampiro)||!validarAtaque(atacante,objetivo,of,oc,df,dc)||!adyacente(of,oc,df,dc))return new ResultadoAccion(false,"Absorcion no valida.");
        atacante.habilidad(objetivo);
        return terminarAtaque(objetivo);
    }

    public ResultadoAccion lanzarLanza(int of,int oc,int df,int dc){
        Pieza atacante=partida.getTablero()[of][oc], objetivo=partida.getTablero()[df][dc];
        if(!(atacante instanceof Necromancer)||!validarAtaque(atacante,objetivo,of,oc,df,dc)||!enLineaDos(of,oc,df,dc))return new ResultadoAccion(false,"La lanza requiere dos casillas en linea horizontal o vertical.");
        if(of==df && partida.getTablero()[of][(oc+dc)/2]!=null || oc==dc && partida.getTablero()[(of+df)/2][oc]!=null)return new ResultadoAccion(false,"La lanza no puede atravesar piezas.");
        ((Necromancer)atacante).atacarConLanza(objetivo);
        return terminarAtaque(objetivo);
    }

    public ResultadoAccion ataqueZombie(int of,int oc,int df,int dc){
        Pieza atacante=partida.getTablero()[of][oc], objetivo=partida.getTablero()[df][dc];
        if(!(atacante instanceof Necromancer)||!validarAtaque(atacante,objetivo,of,oc,df,dc))return new ResultadoAccion(false,"Ataque por Zombie no valido.");
        if(adyacente(of,oc,df,dc)||Math.max(Math.abs(of-df),Math.abs(oc-dc))<=2)return new ResultadoAccion(false,"El objetivo debe estar fuera del alcance directo del Necrómante.");
        boolean zombieValido=false;
        Pieza[][] t=partida.getTablero();
        for(int f=0;f<6;f++)for(int c=0;c<6;c++)if(t[f][c] instanceof Zombie&&t[f][c].color()==atacante.color()&&adyacente(f,c,df,dc))zombieValido=true;
        if(!zombieValido)return new ResultadoAccion(false,"No hay un Zombie propio adyacente al objetivo.");
        aplicarAtaqueZombie(objetivo);
        return terminarAtaque(objetivo);
    }

    private void aplicarAtaqueZombie(Pieza objetivo){

        if(objetivo.getEscudo()>0){  }
        Zombie atacante=new Zombie(-1,-1);
        if(objetivo.color())atacante.setNegro();
        else atacante.setBlanco();
        atacante.ataque(objetivo);
    }

    private boolean validarAtaque(Pieza a,Pieza o,int of,int oc,int df,int dc){return a!=null&&o!=null&&perteneceAlTurno(a)&&esTipoPermitido(a)&&o.estaViva()&&a.color()!=o.color();
    }

    private ResultadoAccion terminarAtaque(Pieza objetivo){
        if(!objetivo.estaViva()){
            String tipo=objetivo.getTipo();
            int f=objetivo.getFila(),c=objetivo.getColumna();
            partida.retirarPieza(f,c);
            return new ResultadoAccion(true,"Se destruyó la pieza "+tipo+" del jugador "+partida.getOponente().getUsuario()+".");
        }
        return new ResultadoAccion(true,"Se atacó la pieza "+objetivo.getTipo()+" y le quedan "+objetivo.getEscudo()+" puntos de escudo y "+objetivo.getVida()+" de vida.");
    }
}
