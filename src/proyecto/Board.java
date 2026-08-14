
package proyecto;
import java.util.HashSet;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Dimension;

public class Board extends JPanel{
    
    public Board(Proyecto ventana){
        setLayout(new BorderLayout());
        //BOARD CENTRAL
        JPanel contenedor = new JPanel(new GridLayout(6,6));
        contenedor.setPreferredSize(new Dimension(700, 700));
        contenedor.setBackground(Color.DARK_GRAY);
        add(contenedor, BorderLayout.CENTER);
        
        for(int i =0;i<6;i++){
            for(int j=0;j<6;j++){
                JPanel square = new JPanel();
                if((i+j)%2!=0){
                    square.setBackground(new Color(240, 217, 181)); // Claro
                } else {
                    square.setBackground(new Color(181, 136, 99));  // Oscuro
                }
                            contenedor.add(square);

                }

            }
        
        
         //Historial
         JPanel vista_d = new JPanel(new BorderLayout());
         vista_d.setPreferredSize(new Dimension(300, 0));
         vista_d.setBackground(Color.GRAY);
         add(vista_d, BorderLayout.EAST);
    }
}
