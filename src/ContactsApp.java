
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ContactsApp {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean toEnd = true;
        while (toEnd) {
            System.out.println("\n Welcome to theContactsApp!");
            System.out.println(ANSI_PURPLE+"------------Menu-------------"+ANSI_RESET);
            System.out.println(ANSI_WHITE_BACKGROUND+"(1)"+ANSI_RESET+" View all your contacts");
            System.out.println(ANSI_WHITE_BACKGROUND+"(2)"+ANSI_RESET+" Add new contact");
            System.out.println(ANSI_WHITE_BACKGROUND+"(3)"+ANSI_RESET+" Search for contact by name");
            System.out.println(ANSI_WHITE_BACKGROUND+"(4)"+ANSI_RESET+" Delete an existing contact");
            System.out.println(ANSI_WHITE_BACKGROUND+"(5)"+ANSI_RESET+" EXIT APPLICATION");

            Path filepath = Paths.get("data", "contacts.txt");
            Input in = new Input();
            List<String> lines;
            List<Contact> contacts = new ArrayList<>();


            slowPrint(ANSI_GREEN+"Enter your choice between (1) and (5): "+ANSI_RESET);
            int choice = scanner.nextInt();
            if (choice == 1) {
                try {
                    lines = Files.readAllLines(filepath);
                    lines.forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (choice == 2) {
                try {
                    lines = Files.readAllLines(filepath);
                    for (String line : lines) {
                        String[] splitStr = line.split(" \\| ");
                        contacts.add(new Contact(splitStr[0], splitStr[1]));
                    }
                    String contactName = in.getString("Whats the name of the contact you want to add?");
                    String contactNumber = in.getString("What's is the contacts number?");

                    String lineForFile = String.format("%s | %s", contactName, contactNumber);
                    Files.write(filepath, Collections.singletonList(lineForFile), StandardOpenOption.APPEND);
                    lines.add(lineForFile);
                    contacts.add(new Contact(contactName, contactNumber));
                    String str1 = String.format("%2$-20s |%1$-15s |", " Number", "Name");
                    System.out.println(str1);
                    System.out.println("---------------------------------------");
                    for (Contact contact : contacts) {
                        String str3 = String.format("%2$-20s | %1$-15s|%n", contact.getNumber(), contact.getName());
                        System.out.printf(str3);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        Files.createFile(filepath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (choice == 3) {
                System.out.println("Name of contact your looking for?");
                scanner.nextLine();
                String searchFor = scanner.nextLine();
                lines = Files.readAllLines(filepath);
                for (String line : lines) {
                    if (line.toLowerCase().contains(searchFor.toLowerCase())) {
                        slowPrint(ANSI_GREEN+"Contacts Info: " + line + "\n"+ANSI_RESET);
                    }
                }
            }
            ArrayList<String> holdNames = new ArrayList<>();
            if (choice == 4) {
                System.out.println("Enter the contact name you want to delete!");
                scanner.nextLine();
                String deleteContact = scanner.nextLine();
                lines = Files.readAllLines(filepath);
                for (String line : lines) {
                    if (line.contains(deleteContact)) {
                        Files.delete(filepath);
                        Files.createFile(filepath);
                    } else {
                        holdNames.add(line);
                    }
                }
                holdNames.forEach((del) -> {
                    try {
                        Files.write(filepath, List.of(del), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            if (choice == 5) {
               slowPrint(ANSI_YELLOW+"Exiting Application!....."+ANSI_RESET);
                toEnd = false;
            }
        }
    }
    static void slowPrint(String output) {
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
            }
        }
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
}
