package proyecto;

import java.awt.*;
import javax.swing.*;

public class Proyecto extends JFrame {
    private JPanel escena1;
    private Jugador jugadorSesion;

    public Proyecto(){setSize(1000,700);
        setTitle("Vampire Wargame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarEscena(new Menu(this));
    }

    public void cambiarEscena(JPanel nueva){if(escena1!=null)remove(escena1);
        escena1=nueva;
        add(escena1);
        revalidate();
        repaint();
    }

    public AlmacenJugadores getJugadores(){return GestorDatos.jugadores();
    }

    public Jugador getJugadorSesion(){return jugadorSesion;
    }

    public void setJugadorSesion(Jugador j){jugadorSesion=j;
    }

    public void iniciarPartida(){
        if(jugadorSesion==null)return;
        Jugador rival=null;
        Jugador[] todos=getJugadores().obtenerTodos();

        for(Jugador j:todos){
            if(j!=null&&j.isActivo()&&!j.getUsuario().equals(jugadorSesion.getUsuario())){
                rival=j;
                break;
            }
        }

        if(rival==null) rival=jugadorSesion;

        cambiarEscena(new Board(this,new Partida(jugadorSesion,rival)));
    }

    public void volverMenuPrincipal(){cambiarEscena(new Menu(this,jugadorSesion));
    }

    public static void main(String[] args){Thread.setDefaultUncaughtExceptionHandler(new ManejadorExcepcionesGUI());
        SwingUtilities.invokeLater(()->new Proyecto().setVisible(true));
    }
}
