package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    double num1;
    double num2;
    double result;
    String operator;

    public BasicCalculator() {
        // Design Frame
        setTitle("Basis Calculator");
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

        if (command.equals("(-)") && !hasDecimal) {
            double temp = Double.parseDouble(display.getText());
            temp *= -1;
            display.setText(String.valueOf(temp));
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
            num1 = Double.parseDouble(currentDisplay);
            display.setText("");
            expressionDisplay.setText(num1 + " " + command);
            operator = command;
        }

        if (command.equals("=")) {
            num2 = Double.parseDouble(currentDisplay);

            switch(operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "x":
                    result = num1 * num2;
                    break;
                case "÷":
                    result = num1 / num2;
                    break;
            }

            display.setText(String.valueOf(result));
            expressionDisplay.setText(num1 + " " + operator + " " + num2 + " " + command);
            num1 = result;
        }
    }
}
