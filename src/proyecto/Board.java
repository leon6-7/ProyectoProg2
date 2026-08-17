package proyecto;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import proyecto.Piezas.Pieza;
import proyecto.Piezas.Necromancer;
import proyecto.Piezas.Vampiro;

public class Board extends JPanel {
    private final Proyecto ventana;
    private final Partida partida;
    private final GestorJuego juego;
    private final JPanel[][] squares=new JPanel[6][6];
    private final JLabel[][] iconos=new JLabel[6][6];
    private int filaSeleccion=-1,colSeleccion=-1;
    private final JLabel resultado=new JLabel("Gire la ruleta para comenzar.",SwingConstants.CENTER);
    private final JLabel turno=new JLabel("",SwingConstants.CENTER);
    private final JButton girar=new JButton("GIRAR RULETA");
    private Ruleta ruleta;

    public Board(Proyecto ventana,Partida partida){
        this.ventana=ventana;
        this.partida=partida;
        this.juego=new GestorJuego(partida);
        setLayout(new BorderLayout(8,8));
        crearTablero();
        crearPanelLateral();
        actualizarVista();
    }

    private void crearTablero(){
        JPanel contenedor=new JPanel(new GridLayout(6,6));
        for(int f=0;f<6;f++)for(int c=0;c<6;c++){
            final int ff=f,cc=c;
            JPanel casilla=new JPanel(new BorderLayout());
            casilla.setBackground((f+c)%2==0?new Color(20,35,55):new Color(165,190,215));
            casilla.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){clickCasilla(ff,cc);}});
            squares[f][c]=casilla;
            contenedor.add(casilla);
        }
        add(contenedor,BorderLayout.CENTER);
    }

    private void crearPanelLateral(){
        JPanel lateral=new JPanel();
        lateral.setLayout(new BoxLayout(lateral,BoxLayout.Y_AXIS));
        lateral.setPreferredSize(new Dimension(300,0));
        turno.setFont(new Font("MS Gothic",Font.BOLD,17));
        resultado.setFont(new Font("MS Gothic",Font.BOLD,15));
        String[] nombres={"Necromante","Hombre Lobo","Vampiro"};
        String[] rutas={"/Imagenes/necromancer.png",
            "/Imagenes/werewolf.png",
            "/Imagenes/vampire.png"};
        ruleta=new Ruleta(nombres,rutas);
        ruleta.setRuletaListener((indice,nombre)->{
            if(tienePiezaDelTipo(nombre)){
                resultado.setText("Puedes mover a tu " + nombre + ".");
            }else{
                resultado.setText("No tienes " + nombre + ". Puedes volver a girar.");
                if(!juego.puedeGirar()) pasarTurno();
            }
            actualizarTurno();
        });

        lateral.add(Box.createVerticalStrut(15));
        lateral.add(turno);
        lateral.add(Box.createVerticalStrut(10));
        lateral.add(ruleta);
        lateral.add(girar);
        lateral.add(Box.createVerticalStrut(10));
        lateral.add(resultado);
        lateral.add(Box.createVerticalGlue());
        JButton retirarse=new JButton("RETIRARSE");
        retirarse.setAlignmentX(CENTER_ALIGNMENT);
        retirarse.addActionListener(e->retirarse());
        lateral.add(retirarse);
        lateral.add(Box.createVerticalStrut(10));
        girar.setAlignmentX(CENTER_ALIGNMENT);
        girar.addActionListener(e->girar());
        add(lateral,BorderLayout.EAST);
    }

    private void girar(){
        if(!juego.puedeGirar()){resultado.setText("No quedan giros. Pierdes el turno.");
            pasarTurno();
            return;
        }
        if(!ruleta.estaGirando()){String tipo=juego.girarRuleta();
            ruleta.girar(tipo);
        }
    }

    private boolean tienePiezaDelTipo(String tipo){
        for(Pieza[] fila:partida.getTablero())for(Pieza p:fila)if(p!=null&&juego.perteneceAlTurno(p)&&p.getTipo().equals(tipo))return true;
        return false;
    }

    private void clickCasilla(int f,int c){
        Pieza p=partida.getTablero()[f][c];
        if(filaSeleccion<0){
            if(p!=null&&juego.perteneceAlTurno(p)&&juego.esTipoPermitido(p)){filaSeleccion=f;
                colSeleccion=c;
                resaltar(f,c);
            }
            else resultado.setText("Seleccione a la pieza indicada por la ruleta.");
            return;
        }
        if(f==filaSeleccion&&c==colSeleccion){limpiarSeleccion();
            return;
        }
        ejecutarAccion(f,c);
    }

    private void ejecutarAccion(int df,int dc){
        Pieza atacante=partida.getTablero()[filaSeleccion][colSeleccion],objetivo=partida.getTablero()[df][dc];
        ResultadoAccion r;
        if(objetivo==null){
            r=juego.mover(filaSeleccion,colSeleccion,df,dc);
        }else if(objetivo.color()==atacante.color()){resultado.setText("No puede ir a una casilla suya.");
            limpiarSeleccion();
            return;
        }else{
            if(atacante instanceof Vampiro && esAdyacente(df,dc)){
                Object[] ops={"Ataque normal","Absorber sangre"};
                int op=JOptionPane.showOptionDialog(this,"Seleccione el ataque:","Vampiro",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ops,ops[0]);
                if(op==0)r=juego.atacarNormal(filaSeleccion,colSeleccion,df,dc);
                else if(op==1)r=juego.absorber(filaSeleccion,colSeleccion,df,dc);
                else return;
            }else if(atacante instanceof Necromancer){
                Object[] ops={"Ataque normal","Lanza (2 casillas)","Ataque a través de Zombie"};
                int op=JOptionPane.showOptionDialog(this,"Seleccione el ataque:","Necrómante",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ops,ops[0]);
                if(op==0)r=juego.atacarNormal(filaSeleccion,colSeleccion,df,dc);
                else if(op==1)r=juego.lanzarLanza(filaSeleccion,colSeleccion,df,dc);
                else if(op==2)r=juego.ataqueZombie(filaSeleccion,colSeleccion,df,dc);
                else return;
            }else r=juego.atacarNormal(filaSeleccion,colSeleccion,df,dc);
        }
        resultado.setText(r.getMensaje());
        limpiarSeleccion();
        actualizarVista();
        if(r.isExito()){
            if(juego.hayGanador()){finalizarVictoria(juego.ganador());
                return;
            }
            pasarTurno();
        }
    }

    private boolean esAdyacente(int f,int c){return Math.abs(f-filaSeleccion)<=1&&Math.abs(c-colSeleccion)<=1&&(f!=filaSeleccion||c!=colSeleccion);
    }

    private void pasarTurno(){juego.terminarTurno();
        actualizarTurno();
        actualizarVista();
        resultado.setText("Turno de "+partida.getJugadorActual().getUsuario()+". Gire la ruleta.");
    }

    private void actualizarTurno(){turno.setText("Turno: "+partida.getJugadorActual().getUsuario()+" ("+(partida.isTurnoBlanco()?"BLANCO":"NEGRO")+")");
    }

    private void actualizarVista(){
        for(int f=0;f<6;f++)for(int c=0;c<6;c++){squares[f][c].removeAll();
            Pieza p=partida.getTablero()[f][c];
            if(p!=null&&p.estaViva()){JLabel l=new JLabel(new ImageIcon(escalar(imagenPieza(p),80)));
                l.setHorizontalAlignment(SwingConstants.CENTER);
                final int ff=f,cc=c;
                l.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){clickCasilla(ff,cc);}});
                iconos[f][c]=l;
                squares[f][c].add(l,BorderLayout.CENTER);
            }squares[f][c].revalidate();
            squares[f][c].repaint();
        }
        actualizarTurno();
        revalidate();
        repaint();
    }

    private String imagenPieza(Pieza p){String n;
        if(p instanceof Necromancer)n="necromancer";
        else if(p instanceof Vampiro)n="vampire";
        else if(p.getTipo().equals("Hombre Lobo"))n="werewolf";
        else n="zombie";
        return "/Imagenes/"+n+(p.color()?"_w":"")+".png";
    }

    private Image escalar(String ruta,int tam){return new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(tam,tam,Image.SCALE_SMOOTH);
    }

    private void resaltar(int f,int c){limpiarMarcadores();
        for(int i=Math.max(0,f-2);i<=Math.min(5,f+2);i++)for(int j=Math.max(0,c-2);j<=Math.min(5,c+2);j++)if(i!=f||j!=c){JLabel m=new JLabel("•",SwingConstants.CENTER);
            m.setForeground(Color.WHITE);
            squares[i][j].add(m,BorderLayout.NORTH);
        }actualizarVistaSinPerderMarcadores();
    }

    private void actualizarVistaSinPerderMarcadores(){for(JPanel[] fila:squares)for(JPanel p:fila){p.revalidate();
            p.repaint();
        }}

    private void limpiarMarcadores(){for(int f=0;f<6;f++)for(int c=0;c<6;c++){Component[] cs=squares[f][c].getComponents();
            for(Component x:cs)if(x instanceof JLabel&&((JLabel)x).getText().equals("•"))squares[f][c].remove(x);
        }}

    private void limpiarSeleccion(){filaSeleccion=-1;
        colSeleccion=-1;
        limpiarMarcadores();
        actualizarVista();
    }

    private void retirarse(){if(JOptionPane.showConfirmDialog(this,"¿Confirma que desea retirarse?","Retirarse",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){Jugador ganador=partida.getOponente(),perdedor=partida.getJugadorActual();
            ganador.sumarPuntos(3);
            JOptionPane.showMessageDialog(this,perdedor.getUsuario()+" se ha retirado. "+ganador.getUsuario()+" gana 3 puntos.");
            ventana.volverMenuPrincipal();
        }}

    private void finalizarVictoria(Jugador ganador){Jugador perdedor=ganador==partida.getJugadorBlanco()?partida.getJugadorNegro():partida.getJugadorBlanco();
        ganador.sumarPuntos(3);
        partida.terminar();
        JOptionPane.showMessageDialog(this,ganador.getUsuario()+" ha ganado 3 puntos.");
        ventana.volverMenuPrincipal();
    }
}
