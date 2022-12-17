package com.isep.utils;

import java.util.Scanner;

public class ConsoleParser implements InputParser {

    public ConsoleParser() {}

    @Override
    public int promptWithIntParser(String prompt) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(prompt);
        String input = scanner.nextLine();

        return Integer.parseInt(input);
    }

    @Override
    public String prompt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);

        return scanner.nextLine();
    }
}
