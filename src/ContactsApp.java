
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleFunction;

public class ContactsApp {

    public static void main(String[] args) {
        Path filepath = Paths.get("data", "contacts.txt");
        Input in = new Input();
        List<String> lines;
        List<Contact> contacts = new ArrayList<>();
        try {
            lines = Files.readAllLines(filepath);
            for (String line : lines) {
                String[] splitStr = line.split(" \\| ");
                contacts.add(new Contact(splitStr[0], splitStr[1]));
            }
            String contactName = in.getString("Whats the name of the contact you want to add?");
            String contactNumber = in.getString("What's is the contacts number?");

            String lineForFile = String.format("%s | %s", contactName, contactNumber);
            Files.write(filepath, Arrays.asList(lineForFile), StandardOpenOption.APPEND);
            lines.add(lineForFile);
            contacts.add(new Contact(contactName, contactNumber));
            for (Contact contact : contacts) {
                System.out.println("Name | Phone Number");
                System.out.print("-------------------");
                String str3 = String.format("%s | %s.%n", contact.getName(), contact.getNumber());
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
}