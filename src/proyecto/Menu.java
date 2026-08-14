
package proyecto;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class Menu extends JPanel{
    public Font def = new Font("MS Gothic", Font.BOLD,20);
    private Font tit = new Font("Vivaldi", Font.PLAIN,60);
    public Menu(Proyecto ventana){
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        //TITULO DEL JUEGO
        JLabel titulo = new JLabel("Vampire Survivors");
        titulo.setBounds(200,100,500,100);
        titulo.setFont(tit);
        titulo.setForeground(Color.white);
        this.add(titulo);
        
        
        //Boton de Jugar(Cambi de ventana)
        JButton jugar = new JButton("Iniciar Sesion");
        jugar.setBounds(200, 250, 180, 50);
        jugar.setFont(def);
        this.add(jugar);
        jugar.addActionListener(e->ventana.cambiarEscena(new Cuenta(ventana)));
        
        //Boton de asdjk
        JButton cuenta = new JButton("Crear Cuenta");
        cuenta.setBounds(200, 350, 180, 50);
        cuenta.setFont(def);
        cuenta.addActionListener(e->ventana.cambiarEscena(new CrearCuenta(ventana)));
        
        this.add(cuenta);
        
        //Boton de Configuracion
        JButton config = new JButton("Configuracion");
        config.setBounds(200, 450, 180, 50);
        config.setFont(def);
        

        this.add(config);
        
    }
    
     public Menu(Proyecto ventana, String n_cuenta){
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        //TITULO DEL JUEGO
        JLabel titulo = new JLabel("Vampire Survivors");
        titulo.setBounds(200,100,500,100);
        titulo.setFont(tit);
        titulo.setForeground(Color.white);
        this.add(titulo);
        
        
        //Boton de Jugar(Cambi de ventana)
        JButton jugar = new JButton("Jugar");
        jugar.setBounds(200, 250, 180, 50);
        jugar.setFont(def);
        this.add(jugar);
        jugar.addActionListener(e->ventana.cambiarEscena(new Board(ventana)));
        
        //Boton de asdjk
        JButton cuenta = new JButton("Cerrar Sesion");
        cuenta.setBounds(200, 350, 180, 50);
        cuenta.setFont(def);
        cuenta.addActionListener(e->ventana.cambiarEscena(new Menu(ventana)));
        
        this.add(cuenta);
        
        //Boton de Configuracion
        JButton config = new JButton("Configuracion");
        config.setBounds(200, 450, 180, 50);
        config.setFont(def);
        

        this.add(config);
        
    }
}
    

