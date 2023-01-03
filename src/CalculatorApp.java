import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {

    private Border debugBorder = BorderFactory.createLineBorder(Color.GREEN, 5);
    private Color orange = new Color(255, 149, 12),
            darkGray = new Color(70, 60, 60),
            midGray = new Color(92, 92, 92),
            lightGray = new Color(113, 107, 108);
    private JLabel text = new JLabel("0");
    private CalculatorButton but_ac, but_negative, but_mod, but_divide,
                    but7, but8, but9, but_multiply,
                    but4, but5, but6, but_subtract,
                    but1, but2, but3, but_sum,
                    but0, but_float, but_equals;

    private String arg1 = "0", arg2 = "0";
    private char operator = '+';
    private boolean cleared = true;

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
        mainPanel.setBackground(new Color(83, 83, 83));

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

        but_sum = new CalculatorButton("+", orange);
        gbc.gridx = 3;
        gbc.gridy = 3;
        buttonsPanel.add(but_sum, gbc);


        but0 = new CalculatorButton("0", lightGray);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        buttonsPanel.add(but0, gbc);

        but_float = new CalculatorButton(".", lightGray);
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
        this.pack();
        this.setVisible(true);
    }

    public void addButtonListeners() {
        but_ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cleared) {
                    allClear();
                }
                else {
                    clear();
                }
            }
        });

        but_negative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arg2 == "0") {
                    return;
                }
                if (arg2.startsWith("-")) {
                    arg2.substring(1);
                } else {
                    arg2 = "-" + arg2;
                }
                text.setText(noDoubleZero(arg2));
            }
        });

        but_mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = '%';
                resolveOperator();
            }
        });

        but_divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = '/';
                resolveOperator();
            }
        });

        but7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("7");
            }
        });

        but8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("8");
            }
        });

        but9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("9");
            }
        });

        but_multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = '*';
                resolveOperator();
            }
        });

        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("4");
            }
        });

        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("5");
            }
        });

        but6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("6");
            }
        });

        but_subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = '-';
                resolveOperator();
            }
        });

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("1");
            }
        });

        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("2");
            }
        });

        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("3");
            }
        });

        but_sum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = '+';
                resolveOperator();
            }
        });

        but0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText("0");
            }
        });

        but_float.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!arg2.contains(".")) {
                    arg2 = arg2.concat(".");
                }
            }
        });

        but_equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void clear() {
        arg2 = "0";
        but_ac.changeTextTo("AC");
        text.setText(noDoubleZero(arg2));
    }

    public void allClear() {
        cleared = true;
        arg1 = "0";
        clear();
    }

    public void resolveOperator() {
        if (cleared) {
            arg1 = arg2;
        } else {
            double dArg1 = Double.parseDouble(arg1), dArg2 = Double.parseDouble(arg2), result = 0;

            switch (operator) {
                case '+':
                    result = dArg1 + dArg2;
                    break;
                case '-':
                    result = dArg1 - dArg2;
                    break;
                case '*':
                    result = dArg1 * dArg2;
                    break;
                case '/':
                    result = dArg1 / dArg2;
                    break;
                case '%':
                    result = dArg1 % dArg2;

            }

            arg1 = result + "";
            text.setText(noDoubleZero(arg1));
        }
        arg2 = "0";
    }

    public void addText(String fresh) {
        cleared = false;
        but_ac.setText(" C "); // TODO: no whitespaces
        if(arg2.equals("0")) {
            arg2 = fresh;
        } else {
            arg2 = arg2.concat(fresh);
        }

        text.setText(arg2);
    }

    public String noDoubleZero(String in) {
        if (in.endsWith(".0")) {
            return in.substring(0, in.indexOf("."));
        }
        else return in;
    }
    public static void main(String[] args) {
        new CalculatorApp();
    }
}