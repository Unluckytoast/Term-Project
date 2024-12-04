import java.awt.event.*;
import javax.swing.*;

public interface KeyboardFunction 
{
    public static void bindEscapeKey(JComponent component) 
    {
        // Add key binding for the Escape key
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeApp");
        component.getActionMap()
            .put("closeApp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); // Exit the application when Escape is pressed
                }
            });
    }
}
