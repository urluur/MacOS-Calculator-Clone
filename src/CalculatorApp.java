import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculatorApp extends JFrame implements KeyListener {
    private static final Color orange = new Color(255, 149, 12),
            darkGray = new Color(80, 75, 75),
            midGray = new Color(91, 91, 91),
            lightGray = new Color(113, 107, 108);
    private final JLabel text = new JLabel("0");
    private CalculatorButton but_ac, but_negative, but_mod, but_divide,
                    but7, but8, but9, but_multiply,
                    but4, but5, but6, but_subtract,
                    but1, but2, but3, but_add,
                    but0, but_float, but_equals;
    private String arg1 = null, arg2 = null, operator = null;

    public static void main(String[] args) {
        new CalculatorApp();
    }

    public CalculatorApp() {
        prepareGUI();
        addButtonListeners();
    }

    /**
     * Creates a GUI for the calculator
     */
    public void prepareGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setBackground(darkGray);
        setSize(233, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("calc_icon.png");
        setIconImage(icon.getImage());

        // mainPanel will contain everything that shows up in the window
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.addKeyListener(this);
        mainPanel.setBackground(darkGray);

        // formats the output text on the top of screen
        text.setHorizontalAlignment(JLabel.RIGHT);
        text.setForeground(Color.WHITE);
        text.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        text.setFont(new Font("Arial", Font.PLAIN, 40));
        text.setFocusable(true);
        text.addKeyListener(this);
        mainPanel.add(text, BorderLayout.NORTH);

        // buttons panel will contain all clickable buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(darkGray);

        // creates all buttons and puts them on the buttonsPanel
        // 1. row
        but_ac = new CalculatorButton("AC", midGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(but_ac, gbc);

        but_negative = new CalculatorButton("+/−", midGray);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonsPanel.add(but_negative, gbc);

        but_mod = new CalculatorButton(" % ", midGray);
        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonsPanel.add(but_mod, gbc);

        but_divide = new CalculatorButton("  /  ", orange);
        gbc.gridx = 3;
        gbc.gridy = 0;
        buttonsPanel.add(but_divide, gbc);

        // 2. row
        but7 = new CalculatorButton("7", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(but7, gbc);

        but8 = new CalculatorButton("8", lightGray);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonsPanel.add(but8, gbc);

        but9 = new CalculatorButton("9", lightGray);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonsPanel.add(but9, gbc);

        but_multiply = new CalculatorButton("×", orange);
        gbc.gridx = 3;
        gbc.gridy = 1;
        buttonsPanel.add(but_multiply, gbc);

        // 3. row
        but4 = new CalculatorButton("4", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonsPanel.add(but4, gbc);

        but5 = new CalculatorButton("5", lightGray);
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonsPanel.add(but5, gbc);

        but6 = new CalculatorButton("6", lightGray);
        gbc.gridx = 2;
        gbc.gridy = 2;
        buttonsPanel.add(but6, gbc);

        but_subtract = new CalculatorButton("−", orange);
        gbc.gridx = 3;
        gbc.gridy = 2;
        buttonsPanel.add(but_subtract, gbc);

        // 4. row
        but1 = new CalculatorButton("1", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonsPanel.add(but1, gbc);

        but2 = new CalculatorButton("2", lightGray);
        gbc.gridx = 1;
        gbc.gridy = 3;
        buttonsPanel.add(but2, gbc);

        but3 = new CalculatorButton("3", lightGray);
        gbc.gridx = 2;
        gbc.gridy = 3;
        buttonsPanel.add(but3, gbc);

        but_add = new CalculatorButton("+", orange);
        gbc.gridx = 3;
        gbc.gridy = 3;
        buttonsPanel.add(but_add, gbc);

        // 5. row
        but0 = new CalculatorButton("0", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // 0 button takes up more horizontal space
        buttonsPanel.add(but0, gbc);

        but_float = new CalculatorButton(",", lightGray);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        buttonsPanel.add(but_float, gbc);

        but_equals = new CalculatorButton("=", orange);
        gbc.gridx = 3;
        gbc.gridy = 4;
        buttonsPanel.add(but_equals, gbc);


        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
//        this.setUndecorated(true); // TODO: custom window decorations
        this.pack();
        this.setVisible(true);
    }

    /**
     * Adds action listener and key listener to all buttons
     * so they work when they are clicked or key is pressed
     * on the keyboard
     */
    public void addButtonListeners() {
        but_ac.addActionListener(e -> butAcPressed());
        but_ac.addKeyListener(this);

        but_negative.addActionListener(e -> butNegativeClicked());
        but_negative.addKeyListener(this);

        but_mod.addActionListener(e -> resolveOperator("%"));
        but_mod.addKeyListener(this);

        but_divide.addActionListener(e -> resolveOperator("/"));
        but_divide.addKeyListener(this);

        but7.addActionListener(e -> pressed("7"));
        but7.addKeyListener(this);

        but8.addActionListener(e -> pressed("8"));
        but8.addKeyListener(this);

        but9.addActionListener(e -> pressed("9"));
        but9.addKeyListener(this);

        but_multiply.addActionListener(e -> resolveOperator("*"));
        but_multiply.addKeyListener(this);

        but4.addActionListener(e -> pressed("4"));
        but4.addKeyListener(this);

        but5.addActionListener(e -> pressed("5"));
        but5.addKeyListener(this);

        but6.addActionListener(e -> pressed("6"));
        but6.addKeyListener(this);

        but_subtract.addActionListener(e -> resolveOperator("-"));
        but_subtract.addKeyListener(this);

        but1.addActionListener(e -> pressed("1"));
        but1.addKeyListener(this);

        but2.addActionListener(e -> pressed("2"));
        but2.addKeyListener(this);

        but3.addActionListener(e -> pressed("3"));
        but3.addKeyListener(this);

        but_add.addActionListener(e -> resolveOperator("+"));
        but_add.addKeyListener(this);

        but0.addActionListener(e -> butZeroClicked());
        but0.addKeyListener(this);

        but_float.addActionListener(e -> butFloatClicked());
        but_float.addKeyListener(this);

        but_equals.addActionListener(e -> butEqualsClicked());
        but_equals.addKeyListener(this);
    }

    /**
     * Does the actual calculating
     * @param arg1 First argument in the operation
     * @param operator Valid operators: + - * / %
     * @param arg2 Second argument in the operation
     * @return result of the operation
     */
    public String solve(String arg1, String operator, String arg2) {
        double x = Double.parseDouble(arg1), y = Double.parseDouble(arg2), result = 0.0;
        switch (operator) {
            case "+" -> result = x + y;
            case "-" -> result = x - y;
            case "*" -> result = x * y;
            case "/" -> result = x / y;
            case "%" -> result = x % y;
            default -> System.out.println("Error: invalid operator.");
        }

        // easter egg joke
        if(arg1.equals("9") && operator.equals("+") && arg2.equals("10")) {
            System.out.println(21);
        }

        return result + "";
    }

    /**
     * Set an operator for future operation
     * Think of it as an black box, because it works magic with existing arguments
     * @param symbol Valid symbols for operator: + - * / %
     */
    public void resolveOperator(String symbol) {
        if (noArgNoOperator()) {
            arg1 = "0";
            operator = symbol;
        } else if (oneArgNoOperator()) {
            operator = symbol;
        } else if (oneArgAndOperator()) {
            operator = symbol;
        } else if (bothArgsAndOperator()) {
            arg1 = solve(arg1, operator, arg2);
            operator = symbol;
            arg2 = null;
            fSetText(arg1);
        } else if (bothArgsNoOperator()) {
            operator = symbol;
            arg2 = null;
            fSetText(arg1);
        }
        resetOperators();
        switch (symbol) {
            case "+" -> but_add.setSelected(true);
            case "-" -> but_subtract.setSelected(true);
            case "*" -> but_multiply.setSelected(true);
            case "/" -> but_divide.setSelected(true);
            case "%" -> but_mod.setSelected(true);
        }
    }

    public boolean noArgNoOperator() {
        return arg1 == null && operator == null && arg2 == null;
    }

    public boolean oneArgNoOperator() {
        return arg1 != null && operator == null && arg2 == null;
    }

    public boolean oneArgAndOperator() {
        return arg1 != null && operator != null && arg2 == null;
    }

    public boolean bothArgsAndOperator() {
        return arg1 != null && operator != null && arg2 != null;
    }

    public boolean bothArgsNoOperator() {
        return arg1 != null && operator == null && arg2 != null;
    }

    /**
     * Adds the negative prefix for the positive input string and vice versa
     * @param x String of numbers, can start with negative prefix
     * @return string of numbers with or without negative prefix
     */
    public String resolveNegative(String x) {
        if (x.startsWith("-")) {
            return x.substring(1);
        }
        else {
            return "-" + x;
        }
    }

    /**
     * Acts as if a person would type a number in a real calculator
     * @param fresh new string which must be a digit from 1 - 9
     */
    public void pressed(String fresh) {
        changeToC();
        if (noArgNoOperator()) {
            arg1 = fresh;
            fSetText(arg1);
        } else if (oneArgNoOperator()) {
            arg1 = arg1.concat(fresh);
            fSetText(arg1);
        } else if (oneArgAndOperator()) {
            arg2 = fresh;
            fSetText(arg2);
        } else if (bothArgsAndOperator()) {
            arg2 = arg2.concat(fresh);
            fSetText(arg2);
        } else if (bothArgsNoOperator()){
            arg1 = fresh;
            arg2 = null;
            fSetText(arg1);
        }
    }

    /**
     * Changes the label of C/AC button to "C"
     */
    public void changeToC() {
        but_ac.changeTextTo("C");
    }

    /**
     * Changes the label of C/AC button to "AC"
     */
    public void changeToAC() {
        but_ac.changeTextTo("AC");
    }

    /**
     * Formatted set text.
     * Sets the top label to input string
     * and removes the unnecessary ".0"
     * @param fresh string that gets printed
     */
    public void fSetText(String fresh) {
        fSetText(fresh, true);
    }

    /**
     * Formatted set text
     * Sets the top label to input string, but can skip formatting
     * @param fresh string that gets printed
     * @param removeDoubleZero removes ".0" if true, skips tampering with input string if false
     */
    public void fSetText(String fresh, boolean removeDoubleZero) {
        if (removeDoubleZero) {
            if(fresh.endsWith(".0")) {
                fresh = fresh.replace(".0", "");
            }
        }
        fresh = fresh.replace('.', ',');
        text.setText(fresh);
    }

    /**
     * Acts as if negative button was clicked on real calculator
     * Sets argument to -(argument)
     */
    public void butNegativeClicked() {
        if (oneArgNoOperator() || oneArgAndOperator()) {
            arg1 = resolveNegative(arg1);
            fSetText(arg1);
        } else if (bothArgsAndOperator()) {
            arg2 = resolveNegative(arg2);
            fSetText(arg2);
        } else if (bothArgsNoOperator()) {
            arg1 = resolveNegative(arg1);
            fSetText(arg1);
        }
    }

    /**
     * Acts as if equals button was clicked on real calculator
     */
    public void butEqualsClicked() {
        if (oneArgAndOperator()) {
            arg2 = arg1;
            arg1 = solve(arg1, operator, arg2);
            operator = null;
            resetOperators();
            arg2 = null;
            fSetText(arg1);
        } else if (bothArgsAndOperator()) {
            arg1 = solve(arg1, operator, arg2);
            operator = null;
            resetOperators();
            fSetText(arg1);
        }
    }

    /**
     * Acts as if number zero was clicked on a real calculator
     */
    public void butZeroClicked() {
        if (oneArgNoOperator()) {
            arg1 = arg1.concat("0");
            fSetText(arg1);
        } else if (bothArgsAndOperator()) {
            arg2 = arg2.concat("0");
            fSetText(arg2);
        }
    }

    /**
     * Acts as if floating point button was clicked on a real calculator
     */
    public void butFloatClicked() {
        if (noArgNoOperator()) {
            arg1 = "0.";
        } else if (oneArgNoOperator()) {
            if (!arg1.contains(".")) {
                arg1 = arg1.concat(".");
                fSetText(arg1);
            }
        } else if (oneArgAndOperator()) {
            arg2 = "0.";
            fSetText(arg2);
        } else if (bothArgsAndOperator()) {
            if(!arg2.contains(".")) {
                arg2 = arg2.concat(".");
                fSetText(arg2);
            }
        }
    }

    public static Color getOrange() {
        return orange;
    }

    public static Color getDarkGray() {
        return darkGray;
    }

    /**
     * Acts as if clear button was pressed on real calculator
     * Clears last done action, like entering an operator or an argument
     * but erases all digits if it has more than one digit
     */
    public void butAcPressed() {
        if (oneArgNoOperator()) {
            arg1 = null;
            changeToAC();
            fSetText("0");
        } else if (oneArgAndOperator()) {
            operator = null;
            resetOperators();
            changeToAC();
        } else if (bothArgsAndOperator()) {
            arg2 = null;
            changeToAC();
            fSetText("0");
        } else {
            arg1 = null;
            operator = null;
            arg2 = null;
            changeToAC();
            resetOperators();
            fSetText("0");
        }
    }

    /**
     * Removes the selected outline from operator buttons
     */
    public void resetOperators() {
        but_add.setSelected(false);
        but_subtract.setSelected(false);
        but_multiply.setSelected(false);
        but_divide.setSelected(false);
        but_mod.setSelected(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * Invoked when a key has been released.
     * Allows the physical keyboard to function as the virtual
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if(c >= '1' && c <= '9') {
            pressed(c + "");
            return;
        } else if ("+-*/%".contains(c + "")) {
            resolveOperator(c + "");
            return;
        }
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 78 -> butNegativeClicked();
            case 10 -> butEqualsClicked();
            case 48 -> butZeroClicked();
            case 27 -> butAcPressed();
            case 44 -> butFloatClicked();
        }
    }
}