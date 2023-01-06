import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorButton extends JButton {

    private final Color pressedBackgroundColor;
    private final Border basicBorder, selectedBorder;

    public CalculatorButton(String text, Color bg_color) {
        super(text);
        setOpaque(true);
        setBackground(bg_color);
        if(bg_color.equals(CalculatorApp.getOrange())){
            pressedBackgroundColor = bg_color.darker();
        } else {
            pressedBackgroundColor = bg_color.brighter();
        }
        setForeground(Color.WHITE);

        Border outside_border = BorderFactory.createMatteBorder(1, 1, 0, 0, CalculatorApp.getDarkGray());
        Border inside_border = BorderFactory.createEmptyBorder(15, 20, 15, 20);
        Border inside_border_smaller = BorderFactory.createEmptyBorder(14, 19, 14, 19);
        Border outline = BorderFactory.createLineBorder(CalculatorApp.getDarkGray().darker(), 1);
        basicBorder = BorderFactory.createCompoundBorder(outside_border, inside_border);
        Border basicBorderSmaller = BorderFactory.createCompoundBorder(outside_border, inside_border_smaller);
        selectedBorder = BorderFactory.createCompoundBorder(outline, basicBorderSmaller);
        setBorder(basicBorder);

        setFont(new Font("Arial", Font.PLAIN, 16));
        super.setContentAreaFilled(false);
    }

    public void changeTextTo(String text) {
        setText(text);
    }

    public void setSelected(boolean selected) {
        if(selected) {
            setBorder(selectedBorder);
        } else {
            setBorder(basicBorder);
        }
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
