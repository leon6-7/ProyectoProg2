package proyecto;

import java.awt.*;
import javax.swing.*;

public class CrearCuenta extends JPanel {
    private final Proyecto ventana;
    private final Font def=new Font("MS Gothic",Font.BOLD,20);
    private final Image fondo=new ImageIcon(getClass().getResource("/Imagenes/bg.png")).getImage();

    public CrearCuenta(Proyecto ventana){
        this.ventana=ventana;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel titulo=new JLabel("CREAR JUGADOR");
        titulo.setFont(def);
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        JTextField nombre=new JTextField();
        nombre.setMaximumSize(new Dimension(400,45));
        nombre.setFont(def);
        JLabel lNombre=new JLabel("Nombre de usuario");
        lNombre.setFont(def);
        lNombre.setForeground(Color.WHITE);
        lNombre.setAlignmentX(CENTER_ALIGNMENT);
        JPasswordField contra=new JPasswordField();
        contra.setMaximumSize(new Dimension(400,45));
        contra.setFont(def);
        JLabel lContra=new JLabel("Contraseña (exactamente 5 caracteres)");
        lContra.setFont(def);
        lContra.setForeground(Color.WHITE);
        lContra.setAlignmentX(CENTER_ALIGNMENT);
        JLabel mensaje=new JLabel(" ");
        mensaje.setFont(def);
        mensaje.setForeground(Color.WHITE);
        mensaje.setAlignmentX(CENTER_ALIGNMENT);
        JButton crear=new JButton("Crear cuenta");
        crear.setFont(def);
        crear.setAlignmentX(CENTER_ALIGNMENT);
        crear.addActionListener(e->{
            String n=nombre.getText().trim(), c=new String(contra.getPassword());
            if(n.isEmpty()){mensaje.setText("El usuario no puede estar vacio.");
                return;
            }
            if(c.length()!=5){mensaje.setText("La contraseña debe tener exactamente 5 caracteres.");
                return;
            }
            if(ventana.getJugadores().buscar(n)!=null){mensaje.setText("El usuario ya existe.");
                return;
            }
            Jugador j=new Jugador(n,c);
            ventana.getJugadores().agregar(j);
            ventana.setJugadorSesion(j);
            ventana.cambiarEscena(new Menu(ventana,j));
        });
        JButton salir=new JButton("Salir");
        salir.setFont(def);
        salir.setAlignmentX(CENTER_ALIGNMENT);
        salir.addActionListener(e->ventana.cambiarEscena(new Menu(ventana)));
        add(Box.createVerticalStrut(45));
        add(titulo);
        add(Box.createVerticalStrut(35));
        add(lNombre);
        add(nombre);
        add(Box.createVerticalStrut(25));
        add(lContra);
        add(contra);
        add(Box.createVerticalStrut(25));
        add(mensaje);
        add(Box.createVerticalStrut(20));
        add(crear);
        add(Box.createVerticalStrut(15));
        add(salir);
        add(Box.createVerticalGlue());
    }
    @Override protected void paintComponent(Graphics g){super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
    }
}
