
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class ContactsApp {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Welcome to theContactsApp!");
        System.out.println("------------Menu-------------");
        System.out.println("(1) View all your contacts");
        System.out.println("(2) Add new contact");
        System.out.println("(3) Search for contact by name");
        System.out.println("(4) Delete an existing contact");
        System.out.println("(5) EXIT APPLICATION");

        Path filepath = Paths.get("data", "contacts.txt");
        Input in = new Input();
        List<String> lines;
        List<Contact> contacts = new ArrayList<>();


        System.out.println("Enter your choice between (1) and (5)");
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
                    System.out.println("Contacts Info: " + line + "\n");
                }
            }
        }
    }
}

