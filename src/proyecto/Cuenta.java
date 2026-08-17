package proyecto;

import java.awt.*;
import javax.swing.*;

public class Cuenta extends JPanel {
    private final Proyecto ventana;
    private final Font def=new Font("MS Gothic",Font.BOLD,20);

    public Cuenta(Proyecto ventana){
        this.ventana=ventana;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel titulo=new JLabel("INICIAR SESION");
        titulo.setFont(def);
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JTextField nombre=new JTextField();
        nombre.setMaximumSize(new Dimension(400,45));
        nombre.setFont(def);
        JPasswordField contra=new JPasswordField();
        contra.setMaximumSize(new Dimension(400,45));
        contra.setFont(def);
        JLabel mensaje=new JLabel(" ");
        mensaje.setFont(def);
        mensaje.setForeground(Color.WHITE);
        mensaje.setAlignmentX(CENTER_ALIGNMENT);
        JButton entrar=new JButton("Entrar");
        entrar.setFont(def);
        entrar.setAlignmentX(CENTER_ALIGNMENT);
        entrar.addActionListener(e->{
            Jugador j=ventana.getJugadores().buscar(nombre.getText().trim());
            if(j!=null&&j.isActivo()&&j.getContrasena().equals(new String(contra.getPassword()))){ventana.setJugadorSesion(j);
                ventana.cambiarEscena(new Menu(ventana,j));
            }
            else mensaje.setText("Usuario o contraseña incorrectos.");
        });
        JButton salir=new JButton("Salir");
        salir.setFont(def);
        salir.setAlignmentX(CENTER_ALIGNMENT);
        salir.addActionListener(e->ventana.cambiarEscena(new Menu(ventana)));
        add(Box.createVerticalStrut(70));
        add(titulo);
        add(Box.createVerticalStrut(35));
        add(new JLabelBlanco("NOMBRE",def));
        add(nombre);
        add(Box.createVerticalStrut(25));
        add(new JLabelBlanco("CONTRASEÑA",def));
        add(contra);
        add(Box.createVerticalStrut(30));
        add(mensaje);
        add(entrar);
        add(Box.createVerticalStrut(15));
        add(salir);
        add(Box.createVerticalGlue());
    }

    private static class JLabelBlanco extends JLabel{JLabelBlanco(String s,Font f){super(s);
            setFont(f);
            setForeground(Color.WHITE);
            setAlignmentX(CENTER_ALIGNMENT);
        }}
    @Override protected void paintComponent(Graphics g){super.paintComponent(g);
        g.drawImage(new ImageIcon(getClass().getResource("/Imagenes/bg.png")).getImage(),0,0,getWidth(),getHeight(),this);
    }
}
