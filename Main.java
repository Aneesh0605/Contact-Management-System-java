import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

class ContactService {
    private TreeMap<String, Contact> contacts = new TreeMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getName())) {
            System.out.println("Contact already exists!");
            return;
        }
        contacts.put(contact.getName(), contact);
        System.out.println("Contact added successfully.");
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        for (Contact c : contacts.values()) {
            System.out.println(c);
        }
    }

    public void searchByName(String name) {
        Contact c = contacts.get(name);
        if (c == null) System.out.println("Contact not found.");
        else System.out.println(c);
    }

    public void searchByPhone(String phone) {
        boolean found = false;
        for (Contact c : contacts.values()) {
            if (c.getPhone().equals(phone)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("Contact not found.");
    }

    public void updateContact(String name, String phone, String email) {
        Contact c = contacts.get(name);
        if (c == null) {
            System.out.println("Contact not found.");
            return;
        }
        c.setPhone(phone);
        c.setEmail(email);
        System.out.println("Contact updated successfully.");
    }

    public void deleteContact(String name) {
        if (contacts.remove(name) != null)
            System.out.println("Contact deleted successfully.");
        else
            System.out.println("Contact not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactService service = new ContactService();

        while (true) {
            System.out.println("\n===== Contact Management System =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search by Name");
            System.out.println("4. Search by Phone");
            System.out.println("5. Update Contact");
            System.out.println("6. Delete Contact");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    service.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    service.viewContacts();
                    break;

                case 3:
                    System.out.print("Enter name: ");
                    service.searchByName(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter phone: ");
                    service.searchByPhone(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Name to update: ");
                    String uName = sc.nextLine();
                    System.out.print("New phone: ");
                    String uPhone = sc.nextLine();
                    System.out.print("New email: ");
                    String uEmail = sc.nextLine();
                    service.updateContact(uName, uPhone, uEmail);
                    break;

                case 6:
                    System.out.print("Name to delete: ");
                    service.deleteContact(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}