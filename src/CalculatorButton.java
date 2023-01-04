import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorButton extends JButton {

    private Color pressedBackgroundColor;
    private final Border basicBorder;

    public CalculatorButton(String text, Color bg_color) {
        super(text);
        this.setOpaque(true);
        this.setBackground(bg_color);
        if(bg_color.equals(CalculatorApp.getOrange())){
            pressedBackgroundColor = bg_color.darker();
        } else {
            pressedBackgroundColor = bg_color.brighter();
        }
        this.setForeground(Color.WHITE);

        Border outside_border = BorderFactory.createMatteBorder(1, 1, 0, 0, CalculatorApp.getDarkGray());
        Border inside_border = BorderFactory.createEmptyBorder(15, 20, 15, 20);
        basicBorder = BorderFactory.createCompoundBorder(outside_border, inside_border);
        this.setBorder(basicBorder);

        this.setFont(new Font("Arial", Font.PLAIN, 16));
        super.setContentAreaFilled(false);
    }



    public void changeColorTo(Color color) {
        this.setBackground(color);
    }

    public void changeTextTo(String text) {
        this.setText(text);
    }

    // source: https://stackoverflow.com/questions/14627223/how-to-change-a-jbutton-color-on-mouse-pressed
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
