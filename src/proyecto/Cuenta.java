package proyecto;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;

public class Cuenta extends JPanel{
    public Font def = new Font("MS Gothic", Font.BOLD,20);

    public Cuenta(Proyecto Ventana){
   
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    //
    
    JLabel dispNombre = new JLabel("NOMBRE");
    dispNombre.setFont(def);
    dispNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

    JTextField nombre = new JTextField(12);
    nombre.setMaximumSize(new Dimension(400,150));
    nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    
    JLabel dispContra = new JLabel("Contraseña");
    dispContra.setFont(def);
    dispContra.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    
    JTextField contra = new JTextField(10);
    contra.setMaximumSize(new Dimension(400,150));
    contra.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    
    JButton enviar = new JButton("Enviar");
    enviar.setMaximumSize(new Dimension(400,100));
    enviar.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    this.add(Box.createRigidArea(new Dimension(0,100)));
    this.add(dispNombre);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(nombre);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(dispContra);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(contra);
    this.add(Box.createRigidArea(new Dimension(0,100)));
    this.add(enviar);
    add(Box.createVerticalGlue());

            
        
    
    }
}
