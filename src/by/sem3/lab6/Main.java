package by.sem3.lab6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessLogger logger = new ProcessLogger();
            logger.runProcess();
        } catch (IOException e) {
            System.out.println("Input/Output error!");
        }
    }
}
