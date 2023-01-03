import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorButton extends JButton {
    public CalculatorButton(String text, Color bg_color) {
        this.setText(text);
        this.setOpaque(true);
        this.setBackground(bg_color);
        this.setForeground(Color.WHITE);

        Border outside_border = BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(73, 73, 73));
        Border inside_border = (BorderFactory.createEmptyBorder(15, 20, 15, 20));
        this.setBorder(BorderFactory.createCompoundBorder(outside_border, inside_border));

        this.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public void changeColorTo(Color color) {
        this.setBackground(color);
    }

    public void changeTextTo(String text) {
        this.setText(text);
    }
}
