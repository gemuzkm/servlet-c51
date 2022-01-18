package service;

import validator.InputOperatorNumberValidation;

public class CalculatorService {
    private String operatorOne = "";
    private String operatorTwo = "";
    private String operator = "";
    private double result = 0.0;
    private double operatorFirstDouble = 0.0;
    private double operatorTwoDouble = 0.0;
    private boolean isValidOperation = false;

    private InputOperatorNumberValidation inputOperatorNumberValidation = new InputOperatorNumberValidation();


    public CalculatorService(String operatorOne, String operatorTwo, String operator) {
        this.operatorOne = operatorOne;
        this.operatorTwo = operatorTwo;
        this.operator = operator;
    }

    public String getResult() {


        return String.valueOf(result);
    }
}
