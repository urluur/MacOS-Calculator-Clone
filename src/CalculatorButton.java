import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorButton extends JButton {

    private final Color pressedBackgroundColor;
    private final Border basicBorder, basicBorderC, selectedBorder;

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

        // creates dark gray gird lines between buttons
        Border outside_border = BorderFactory.createMatteBorder(
                1,
                1,
                0,
                0,
                CalculatorApp.getDarkGray()
        );

        int borderY = 14;
        int borderX = 17;
        // creates some space between grid lines and button labels
        Border inside_border = BorderFactory.createEmptyBorder(
                borderY,
                borderX,
                borderY,
                borderX
        );

        int fixLeft = borderX + 6;
        int fixRight = borderX + 5;
        // creates a bit bigger space for button C/AC, when label is "C" (because it uses less horizontal space)
        Border inside_border_c = BorderFactory.createEmptyBorder(
                borderY,
                fixLeft,
                borderY,
                fixRight
        );

        // creates a smaller space, because selected button has additional outline
        Border inside_border_smaller = BorderFactory.createEmptyBorder(
                borderY - 1,
                borderX - 1,
                borderY - 1,
                borderX - 1
        );

        // creates outline for selected buttons
        Border outline = BorderFactory.createLineBorder(
                CalculatorApp.getDarkGray().darker(),
                1
        );

        // creates default border for all buttons
        basicBorder = BorderFactory.createCompoundBorder(
                outside_border,
                inside_border
        );

        // creates border for button C/AC when the label is "C"
        basicBorderC = BorderFactory.createCompoundBorder(
                outside_border,
                inside_border_c
        );

        // creates a inner part of border for selected button
        Border basicBorderSmaller = BorderFactory.createCompoundBorder(
                outside_border,
                inside_border_smaller
        );

        // creates border for selected buttons with outline
        selectedBorder = BorderFactory.createCompoundBorder(
                outline,
                basicBorderSmaller
        );
        setBorder(basicBorder);

        setFont(new Font("Arial", Font.PLAIN, 16));
        super.setContentAreaFilled(false); // allows us to change color of buttons
    }

    /**
     * Changes text displayed on the button
     * In case of changing text of C/AC button, also changes the border
     * @param text Text to display on button
     */
    public void changeTextTo(String text) {
        switch (text) {
            case "AC" -> setBorder(basicBorder);
            case "C" -> setBorder(basicBorderC);
        }
        setText(text);
    }

    /**
     * Adds an outline to the border
     * @param selected  true if the button is selected, otherwise false
     */
    public void setSelected(boolean selected) {
        if(selected) {
            setBorder(selectedBorder);
        } else {
            setBorder(basicBorder);
        }
    }

    /**
     * Changes the button color when clicked
     * source: <a href="https://stackoverflow.com/questions/14627223/how-to-change-a-jbutton-color-on-mouse-pressed">Stackoverflow</a>
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        super.setFocusPainted(false); // removes a line around text in focused button
    }
}
