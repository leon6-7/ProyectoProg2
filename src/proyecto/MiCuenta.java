package proyecto;

import java.awt.*;
import javax.swing.*;

public class MiCuenta extends JPanel {
    private final Proyecto ventana;
    private final Jugador jugador;
    private final Font f=new Font("MS Gothic",Font.BOLD,18);

    public MiCuenta(Proyecto ventana,Jugador jugador){
        this.ventana=ventana;
        this.jugador=jugador;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel titulo=new JLabel("MI CUENTA");
        titulo.setFont(new Font("MS Gothic",Font.BOLD,28));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel info=new JLabel("Usuario: "+jugador.getUsuario()+" | Puntos: "+jugador.getPuntos());
        info.setFont(f);
        info.setForeground(Color.WHITE);
        info.setAlignmentX(CENTER_ALIGNMENT);
        JPasswordField nueva=new JPasswordField();
        nueva.setMaximumSize(new Dimension(350,40));
        JLabel msg=new JLabel(" ");
        msg.setForeground(Color.WHITE);
        msg.setAlignmentX(CENTER_ALIGNMENT);
        msg.setFont(f);
        JButton cambiar=new JButton("Cambiar contraseña");
        cambiar.setFont(f);
        cambiar.setAlignmentX(CENTER_ALIGNMENT);
        cambiar.addActionListener(e->{String c=new String(nueva.getPassword());if(c.length()!=5)msg.setText("Debe tener exactamente 5 caracteres.");else{jugador.setContrasena(c);msg.setText("Contraseña actualizada.");}});
        JButton cerrar=new JButton("Cerrar mi cuenta");
        cerrar.setFont(f);
        cerrar.setAlignmentX(CENTER_ALIGNMENT);
        cerrar.addActionListener(e->{if(JOptionPane.showConfirmDialog(this,"¿Desea cerrar su cuenta?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){jugador.cerrarCuenta();ventana.setJugadorSesion(null);ventana.cambiarEscena(new Menu(ventana));}});
        JButton volver=new JButton("Volver");
        volver.setFont(f);
        volver.setAlignmentX(CENTER_ALIGNMENT);
        volver.addActionListener(e->ventana.volverMenuPrincipal());
        add(Box.createVerticalStrut(60));
        add(titulo);
        add(Box.createVerticalStrut(35));
        add(info);
        add(Box.createVerticalStrut(40));
        add(new JLabelBlanco("Nueva contraseña",f));
        add(nueva);
        add(Box.createVerticalStrut(15));
        add(msg);
        add(cambiar);
        add(Box.createVerticalStrut(20));
        add(cerrar);
        add(Box.createVerticalStrut(15));
        add(volver);
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
