package util;

import java.util.Scanner;

public class Input {


    private Scanner scanner = new Scanner(System.in);



    public String getString() {
        System.out.println("Input string...");
        return this.scanner.nextLine();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        return this.scanner.nextLine();
    }

    public boolean yesNo() {
        System.out.println("Do you want to create another circle? [y] or [n]");
        String userInput = scanner.next();
        if (userInput.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public int getInt(int min, int max) {
        System.out.println("Please enter an integer between 1 and 10!");
        int enterInt = scanner.nextInt();
        while (enterInt < min || enterInt > max) {
            System.out.println("Please try again!");
            return getInt();
        }
        return enterInt;
    }

    int thisInt;
    public int getInt() {
        System.out.println("Please enter an integer!");
        try {
            Integer.valueOf(getString());
        } catch (NumberFormatException e) {
            System.out.println(thisInt + " This should be a number!");
            e.printStackTrace();
        }
        return thisInt;
    }


    public double getDouble(double min, double max) {
        System.out.println("Please enter an integer between 1 and 10 with a decimal!");
        double enterDbl = scanner.nextDouble();
        while (enterDbl < min || enterDbl > max) {
            System.out.println("Please try again!");
            return getDouble();
        }
        return enterDbl;
    }

    Double thisDub;
    public double getDouble() {
        System.out.println("Please enter a integer!");
            try {
                Integer.valueOf(getString());
            } catch (NumberFormatException e) {
                System.out.println(thisDub + " This here should be a number!");
                e.printStackTrace();
            }
            return thisDub;
        }
    }








