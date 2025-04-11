package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends JFrame implements ActionListener {
    ArithmeticMethods arithmetic = new ArithmeticMethods();

    // Components
    JPanel northPanel;
    JTextField display;
    JTextField expressionDisplay;
    JButton[] buttons;
    String[] buttonLabels = {
            "7", "8", "9", "÷",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "0", ".", "(-)", "+",
            "CA", "DEL", "="
    };

    // Inputs and results
    String input1;
    String input2;
    String result;
    String operator;

    public BasicCalculator() {
        // Design Frame
        setTitle("Basic Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display - create and add to the top
        display = new JTextField();
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Expression display - create and add to top of input display
        expressionDisplay = new JTextField();
        expressionDisplay.setEditable(false);
        expressionDisplay.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // North panel to hold displays
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(expressionDisplay);
        northPanel.add(display);
        add(northPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(); // grid where buttons are displayed
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        buttons = new JButton[buttonLabels.length]; // create empty array
        for(int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        String currentDisplay = display.getText();
        boolean hasDecimal = currentDisplay.contains(".");

        for(int i = 0; i < 10; i++) {
            if (command.equals(String.valueOf(i))) {
                display.setText(currentDisplay.concat(String.valueOf(i)));
            }
        }

        if (command.equals(".") && !hasDecimal) {
            display.setText(currentDisplay.concat("."));
        }

        if (command.equals("(-)")) {
            String input = display.getText();
            display.setText(arithmetic.negate(input));
        }

        if (command.equals("CA")) {
            display.setText("");
            expressionDisplay.setText("");
        }

        if (command.equals("DEL") && !currentDisplay.isEmpty()) {
            String newText = currentDisplay.substring(0, currentDisplay.length()-1);
            display.setText(newText);
        }

        if (command.equals("+") ||
                command.equals("-") ||
                command.equals("x") ||
                command.equals("÷")
        ) {
            this.input1 = currentDisplay;
            display.setText("");
            expressionDisplay.setText((input1 + " " + command));
            operator = command;
        }

        if (command.equals("=")) {
            this.input2 = currentDisplay;

            result = arithmetic.getResult(operator, input1, input2);

            display.setText(result);
            expressionDisplay.setText(input1 + " " + operator + " " + input2 + " " + command);
            input1 = result;
        }
    }
}
