
package proyecto;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class CrearCuenta extends JPanel{
private static Map<String, String> listadoCuentas = new HashMap<>();
private Font def = new Font("MS Gothic", Font.BOLD,20);

public CrearCuenta(Proyecto ventana){

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   
    //
    JLabel dispNombre = new JLabel("NOMBRE");
    dispNombre.setFont(def);
    dispNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //Textfield de nombre
    JTextField nombre = new JTextField(12);
    nombre.setMaximumSize(new Dimension(400,50));
    nombre.setPreferredSize(new Dimension(200, 50));
    nombre.setFont(def);
    nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
    
   //
    JLabel dispContra = new JLabel("Contraseña");
    dispContra.setFont(def);
    dispContra.setAlignmentX(Component.CENTER_ALIGNMENT);
    
   //Textfield de contra
    JTextField contra = new JTextField(10);
    contra.setMaximumSize(new Dimension(400,50));
    contra.setPreferredSize(new Dimension(200, 50));
    contra.setFont(def);
    contra.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //Label de confirmacion
    JLabel conf = new JLabel("");
    conf.setMaximumSize(new Dimension(400,50));
    conf.setPreferredSize(new Dimension(300, 50));
    conf.setFont(def);
    conf.setAlignmentX(Component.CENTER_ALIGNMENT);
    conf.setVisible(false);
    //boton enviar datos
    JButton enviar = new JButton("Enviar");
    enviar.setMaximumSize(new Dimension(400,50));
    enviar.setPreferredSize(new Dimension(300, 50));
    enviar.setFont(def);
    enviar.setAlignmentX(Component.CENTER_ALIGNMENT);
    enviar.addActionListener(e->    {
            if(agregarCuenta(contra.getText(), nombre.getText(), conf))
            {
        ventana.cambiarEscena(new Menu(ventana));
            
            
        }else{conf.setVisible(true);}});
    
    
    this.add(Box.createRigidArea(new Dimension(0,100)));
    this.add(dispNombre);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(nombre);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(dispContra);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(contra);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(enviar);
    this.add(Box.createRigidArea(new Dimension(0,50)));
    this.add(conf);

    add(Box.createVerticalGlue());
}   


public boolean agregarCuenta(String contra, String nombre, JLabel conf){
    for(String nom: listadoCuentas.values()){
        if(nom.contentEquals(nombre)){        
            conf.setText("Nombre ya existente");
           return false;
        }   
    }
    System.out.println("Cuenta añadida correctamente");
    listadoCuentas.put(contra,nombre);
    conf.setText("Cuenta añadida correctamente");
    return true;
}

public static Map<String, String> getListadoCuentas() {
        return listadoCuentas;
}


}