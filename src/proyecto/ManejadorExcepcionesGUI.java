package proyecto;

import javax.swing.*;

public final class ManejadorExcepcionesGUI implements Thread.UncaughtExceptionHandler {
    @Override public void uncaughtException(Thread t, Throwable e) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null,
        "Ocurrio un error controlado: " + (e.getMessage()==null?e.getClass().getSimpleName():e.getMessage()),
        "Error", JOptionPane.ERROR_MESSAGE));
    }
}
