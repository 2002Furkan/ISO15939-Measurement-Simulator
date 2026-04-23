package com.simulator;

public class Metric {
    String name;
    double coefficient;
    String direction;   
    String unit;
    double rawValue;    
    double calculatedScore;

    public Metric(String name, double coefficient, String direction, String unit) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.unit = unit;
    }
}