package main;

import client.Client;
import common.LoggerProxy;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;

public class Application {

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);
        new Client(calculator).go();
    }
}
