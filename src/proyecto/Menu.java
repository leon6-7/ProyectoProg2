package proyecto;

import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {
    private final Font def=new Font("MS Gothic",Font.BOLD,20);
    private final Font tit=new Font("Vivaldi",Font.BOLD,65);
    private final Image fondo=new ImageIcon("C:\\Proyectos\\Programacion\\ProyectoProg2\\Proyecto\\src\\Imagenes\\bg.png").getImage();

    public Menu(Proyecto ventana){
        setLayout(null);
        titulo("Vampire Wargame");
        boton(ventana,"Iniciar Sesion",200,250,e->ventana.cambiarEscena(new Cuenta(ventana)));
        boton(ventana,"Crear Jugador",200,350,e->ventana.cambiarEscena(new CrearCuenta(ventana)));
        boton(ventana,"Salir",200,450,e->System.exit(0));
    }

    public Menu(Proyecto ventana,Jugador jugador){
        setLayout(null);
        titulo("Vampire Wargame");
        boton(ventana,"Jugar",200,210,e->ventana.iniciarPartida());
        boton(ventana,"Mi cuenta",200,290,e->ventana.cambiarEscena(new MiCuenta(ventana,jugador)));
        boton(ventana,"Cerrar sesion",200,450,e->{ventana.setJugadorSesion(null);ventana.cambiarEscena(new Menu(ventana));});
    }

    private void titulo(String t){JLabel l=new JLabel(t);
        l.setBounds(100,80,700,100);
        l.setFont(tit);
        l.setForeground(Color.WHITE);
        add(l);
    }

    private void boton(Proyecto v,String txt,int x,int y,java.awt.event.ActionListener a){JButton b=new JButton(txt);
        b.setBounds(x,y,300,55);
        b.setFont(def);
        b.setBackground(new Color(20,35,55));
        b.setForeground(new Color(165,190,215));
        b.addActionListener(a);
        add(b);
    }
    @Override protected void paintComponent(Graphics g){super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
    }
}
