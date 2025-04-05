package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** TODO:
 * UPDATE: create possible arithmetic class to hold methods
 * */

public class BasicCalculator extends JFrame implements ActionListener {
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
    BigDecimal num1;
    BigDecimal num2;
    BigDecimal result;
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

    public BigDecimal add() {
        return num1.add(num2);
    }

    public BigDecimal multiply() {
        return num1.multiply(num2);
    }

    public BigDecimal subtract() {
        return num1.subtract(num2);
    }

    public BigDecimal divide(int scale) {
        return num1.divide(num2, scale, RoundingMode.HALF_UP);
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
            BigDecimal input = new BigDecimal(display.getText());
            BigDecimal negativeValue = input.negate();
            display.setText(negativeValue.toString());
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
            this.num1 = new BigDecimal(currentDisplay);
            display.setText("");
            expressionDisplay.setText(num1 + " " + command);
            operator = command;
        }

        if (command.equals("=")) {
            this.num2 = new BigDecimal(currentDisplay);

            switch(operator) {
                case "+":
                    result = this.add();
                    break;
                case "-":
                    result = this.subtract();
                    break;
                case "x":
                    result = this.multiply();
                    break;
                case "÷":
                    result = this.divide(5);
                    break;
            }

            display.setText(result.toString());
            expressionDisplay.setText(num1 + " " + operator + " " + num2 + " " + command);
            num1 = result;
        }
    }
}
