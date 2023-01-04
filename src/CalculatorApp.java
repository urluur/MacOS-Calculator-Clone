import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame {
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

    public CalculatorApp() {
        prepareGUI();
        addButtonListeners();
    }

    public void prepareGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setBackground(darkGray);
        setSize(230, 320);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("calc_icon.png");
        this.setIconImage(icon.getImage());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(darkGray);

        text.setHorizontalAlignment(JLabel.RIGHT);
        text.setForeground(Color.WHITE);
        text.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        text.setFont(new Font("Arial", Font.PLAIN, 50));
        mainPanel.add(text, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(darkGray);


        but_ac = new CalculatorButton("AC", midGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(but_ac, gbc);

        but_negative = new CalculatorButton("+/−", midGray);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonsPanel.add(but_negative, gbc);

        but_mod = new CalculatorButton("%", midGray);
        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonsPanel.add(but_mod, gbc);

        but_divide = new CalculatorButton("/", orange);
        gbc.gridx = 3;
        gbc.gridy = 0;
        buttonsPanel.add(but_divide, gbc);


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


        but0 = new CalculatorButton("0", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
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

    public void addButtonListeners() {
        but_ac.addActionListener(e -> {
            if (oneArgNoOperator()) {
                arg1 = null;
                changeToAC();
                text.setText("0");
            } else if (oneArgAndOperator()) {
                operator = null;
                changeToAC();
            } else if (bothArgsAndOperator()) {
                arg2 = null;
                changeToAC();
                text.setText("0");
            }
        });

        but_negative.addActionListener(e -> {
            if (oneArgNoOperator() || oneArgAndOperator()) {
                arg1 = resolveNegative(arg1);
                text.setText(arg1);
            } else if (bothArgsAndOperator()) {
                arg2 = resolveNegative(arg2);
                text.setText(arg2);
            }
        });

        but_mod.addActionListener(e -> resolveOperator("%"));

        but_divide.addActionListener(e -> resolveOperator("/"));

        but7.addActionListener(e -> pressed("7"));

        but8.addActionListener(e -> pressed("8"));

        but9.addActionListener(e -> pressed("9"));

        but_multiply.addActionListener(e -> resolveOperator("*"));

        but4.addActionListener(e -> pressed("4"));

        but5.addActionListener(e -> pressed("5"));

        but6.addActionListener(e -> pressed("6"));

        but_subtract.addActionListener(e -> resolveOperator("-"));

        but1.addActionListener(e -> pressed("1"));

        but2.addActionListener(e -> pressed("2"));

        but3.addActionListener(e -> pressed("3"));

        but_add.addActionListener(e -> resolveOperator("+"));

        but0.addActionListener(e -> {
            if (oneArgNoOperator()) {
                arg1 = arg1.concat("0");
                text.setText(arg1);
            } else if (bothArgsAndOperator()) {
                arg2 = arg2.concat("0");
                text.setText(arg2);
            }
        });

        but_float.addActionListener(e -> {
            if (noArgNoOperator()) {
                arg1 = "0.";
            } else if (oneArgNoOperator()) {
                if (!arg1.contains(".")) {
                    arg1 = arg1.concat(".");
                    text.setText(arg1);
                }
            } else if (oneArgAndOperator()) {
                arg2 = "0.";
                text.setText(arg2);
            } else if (bothArgsAndOperator()) {
                if(!arg2.contains(".")) {
                    arg2 = arg2.concat(".");
                    text.setText(arg2);
                }
            }
        });

        but_equals.addActionListener(e -> {
            if (oneArgAndOperator()) {
                arg2 = arg1;
                arg1 = solve(arg1, operator, arg2);
                operator = null;
                arg2 = null;
                fSetText(arg1);
            } else if (bothArgsAndOperator()) {
                arg1 = solve(arg1, operator, arg2);
                operator = null;
                arg2 = null;
                fSetText(arg1);
            }
        });
    }

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
        return result + "";
    }

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

    public String resolveNegative(String x) {
        if (x.startsWith("-")) {
            return x.substring(1);
        }
        else {
            return "-" + x;
        }
    }

    public void pressed(String fresh) {
        changeToC();
        if (noArgNoOperator()) {
            arg1 = fresh;
            text.setText(arg1);
        } else if (oneArgNoOperator()) {
            arg1 = arg1.concat(fresh);
            text.setText(arg1);
        } else if (oneArgAndOperator()) {
            arg2 = fresh;
            text.setText(arg2);
        } else if (bothArgsAndOperator()) {
            arg2 = arg2.concat(fresh);
            text.setText(arg2);
        }
    }

    public void changeToC() {
        but_ac.changeTextTo(" C "); // TODO: without whitespaces
    }

    public void changeToAC() {
        but_ac.changeTextTo("AC");
    }

    public void fSetText(String fresh) {
        // TODO: print doubles without 0.0
        text.setText(fresh);
    }

    public static Color getOrange() {
        return orange;
    }

    public static Color getDarkGray() {
        return darkGray;
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}