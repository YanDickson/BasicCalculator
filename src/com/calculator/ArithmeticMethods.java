package com.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticMethods {
    public ArithmeticMethods() {
    }

    private BigDecimal convert(String str) {
        return new BigDecimal(str);
    }

    public String add(String input1, String input2) {
        BigDecimal result = this.convert(input1).add(this.convert(input2));
        return String.valueOf(result);
    }

    public String multiply(String input1, String input2) {
        BigDecimal result = this.convert(input1).multiply(this.convert(input2));
        return String.valueOf(result);
    }

    public String subtract(String input1, String input2) {
        BigDecimal result = this.convert(input1).subtract(this.convert(input2));
        return String.valueOf(result);
    }

    public String divide(String input1, String input2, int scale) {
        BigDecimal result =  this.convert(input1).divide(this.convert(input2), scale, RoundingMode.HALF_UP);
        return String.valueOf(result);
    }

    public String negate(String input) {
        BigDecimal result = this.convert(input).negate();
        return String.valueOf(result);
    }

    public String getResult(String operator, String... inputs) {
        return switch (operator) {
            case "+" -> this.add(inputs[0], inputs[1]);
            case "-" -> this.subtract(inputs[0], inputs[1]);
            case "x" -> this.multiply(inputs[0], inputs[1]);
            case "÷" -> this.divide(inputs[0], inputs[1], 5);
            default -> "";
        };
    }
}
