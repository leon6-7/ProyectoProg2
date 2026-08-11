
package proyecto;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Proyecto extends JFrame {
    private JPanel escena1;
    public Proyecto(){
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarEscena(new Menu(this));
    }
    
    public void cambiarEscena(JPanel newScene){
     if (escena1 != null) {
            remove(escena1);
        }
        escena1 = newScene;
        add(escena1);
        
        // Refresh the UI layout
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
           SwingUtilities.invokeLater(() -> new Proyecto().setVisible(true));
    }
    
}
