package proyecto;

import proyecto.Piezas.Pieza;
import proyecto.Piezas.Zombie;
import proyecto.Piezas.Vampiro;
import proyecto.Piezas.Necromancer;
import proyecto.Piezas.HombreLobo;

public class Partida {
    private final Jugador jugadorBlanco;
    private final Jugador jugadorNegro;
    private final Pieza[][] tablero = new Pieza[6][6];
    private boolean turnoBlanco = true;
    private boolean terminada;

    public Partida(Jugador blanco, Jugador negro){
        jugadorBlanco=blanco;
        jugadorNegro=negro;
        inicializarTablero();
    }

    private void inicializarTablero(){

        tablero[0][0]=new HombreLobo(0,0);
        tablero[0][1]=new Vampiro(0,1);
        tablero[0][2]=new Necromancer(0,2);
        tablero[0][3]=new Necromancer(0,3);
        tablero[0][4]=new Vampiro(0,4);
        tablero[0][5]=new HombreLobo(0,5);
        tablero[5][0]=new HombreLobo(5,0);
        tablero[5][1]=new Vampiro(5,1);
        tablero[5][2]=new Necromancer(5,2);
        tablero[5][3]=new Necromancer(5,3);
        tablero[5][4]=new Vampiro(5,4);
        tablero[5][5]=new HombreLobo(5,5);
        for(int c=0;c<6;c++){ tablero[0][c].setNegro();
            tablero[5][c].setBlanco();
        }
    }

    public Pieza[][] getTablero(){ return tablero;
    }

    public Jugador getJugadorActual(){return turnoBlanco?jugadorBlanco:jugadorNegro;
    }

    public Jugador getOponente(){return turnoBlanco?jugadorNegro:jugadorBlanco;
    }

    public Jugador getJugadorBlanco(){return jugadorBlanco;
    } public Jugador getJugadorNegro(){return jugadorNegro;
    }

    public boolean isTurnoBlanco(){return turnoBlanco;
    } public boolean isTerminada(){return terminada;
    }

    public void cambiarTurno(){turnoBlanco=!turnoBlanco;
    }

    public void terminar(){terminada=true;
    }

    public int contarPiezasVivas(boolean blanco){ return contarRecursivo(0, blanco);
    }

    public int contarPiezasPrincipalesVivas(boolean blanco){ return contarPrincipalesRecursivo(0, blanco);
    }

    private int contarPrincipalesRecursivo(int indice, boolean blanco){
        if(indice>=36) return 0;
        Pieza p=tablero[indice/6][indice%6];
        int actual=(p!=null && p.estaViva() && !(p instanceof Zombie) && p.color()==blanco)?1:0;
        return actual+contarPrincipalesRecursivo(indice+1,blanco);
    }

    private int contarRecursivo(int indice, boolean blanco){
        if(indice>=36) return 0;
        Pieza p=tablero[indice/6][indice%6];
        int actual=(p!=null && p.estaViva() && p.color()==blanco)?1:0;
        return actual+contarRecursivo(indice+1,blanco);
    }

    public void retirarPieza(int f,int c){ if(tablero[f][c]!=null && !tablero[f][c].estaViva()) tablero[f][c]=null;
    }

    public boolean puedeInvocar(int f,int c){ return f>=0&&f<6&&c>=0&&c<6&&tablero[f][c]==null;
    }

    public void invocarZombie(int f,int c, boolean blanco){
        Zombie z=new Zombie(f,c);
        if(blanco)z.setBlanco();
        else z.setNegro();
        tablero[f][c]=z;
    }
}
