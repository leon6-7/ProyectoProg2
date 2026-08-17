package proyecto;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Ruleta extends JPanel {
    public interface RuletaListener { void onResultado(int indice,String nombre);
    }
    private final String[] nombres;
    private final ImageIcon[] iconos;
    private final JLabel etiquetaImagen;
    private final Random random=new Random();
    private RuletaListener listener;
    private Timer timer;
    private boolean girando=false;

    public Ruleta(String[] nombres,String[] rutasImagenes){
        this.nombres=nombres;
        iconos=new ImageIcon[rutasImagenes.length];
        for(int i=0;i<rutasImagenes.length;i++){Image im=new ImageIcon(getClass().getResource(rutasImagenes[i])).getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
            iconos[i]=new ImageIcon(im);
        }
        setLayout(new BorderLayout());
        etiquetaImagen=new JLabel(iconos[0],SwingConstants.CENTER);
        add(etiquetaImagen,BorderLayout.CENTER);
        setPreferredSize(new Dimension(160,160));
    }

    public void setRuletaListener(RuletaListener listener){this.listener=listener;
    }

    public boolean estaGirando(){return girando;
    }

    public void girar(){girar(null);
    }

    public void girar(String resultadoForzado){
        if(girando)return;
        girando=true;
        int total=12+random.nextInt(6);
        final int resultado=resultadoForzado==null?random.nextInt(nombres.length):indiceDe(resultadoForzado);
        timer=new Timer(100,null);
        final int[] contador={0};
        timer.addActionListener(e->{etiquetaImagen.setIcon(iconos[random.nextInt(nombres.length)]);contador[0]++;if(contador[0]>total-4)timer.setDelay(timer.getDelay()+60);if(contador[0]>=total){timer.stop();etiquetaImagen.setIcon(iconos[resultado]);girando=false;if(listener!=null)listener.onResultado(resultado,nombres[resultado]);}});
        timer.start();
    }

    private int indiceDe(String nombre){for(int i=0;i<nombres.length;i++)if(nombres[i].equals(nombre))return i;
        return 0;
    }
}
