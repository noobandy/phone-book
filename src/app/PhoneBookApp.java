package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import menumanager.MenuCommand;
import menumanager.MenuManager;
import phonebook.Contact;
import phonebook.PhoneBook;
import phonebook.PhoneBookDAO;
import data.FilePhoneBookDAO;

/**
 * Phone Book application
 * 
 * @author anandm
 * 
 */
public class PhoneBookApp {

	private static final String ERROR_FILE_NAME = "error.log";

	private static void displayContacts(List<Contact> contacts) {

		int serialNumber = 1;
		System.out.format("%41s%n", "Contacts");
		for (Contact contact : contacts) {
			System.out.format("%4d %25s %10s%n", serialNumber++,
					contact.getName(), contact.getNumber());

		}
	}

	public static void main(String[] args) throws Exception {
		try {
			System.setErr(new PrintStream(new File(ERROR_FILE_NAME)));
		} catch (FileNotFoundException e1) {
			System.out
					.println("unable to change error stream. default is console");
		}
		final PhoneBookDAO phoneBookDAO = new FilePhoneBookDAO();

		final PhoneBook phoneBook  = phoneBookDAO.getPhoneBook();;
		

		MenuManager menuManager = new MenuManager(new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {
				try {
					phoneBookDAO.savePhoneBook(phoneBook);
				} catch (Exception e) {
					System.err.print(e);
				}

			}
		});

		menuManager.addMenu("Add Contact", new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {

				System.out.print("Enter name: ");
				String name = scanner.nextLine();

				System.out.print("Enter number: ");
				String number = scanner.nextLine();

				phoneBook.addContact(name, number);

				System.out.println("Contact added successfully.");

			}

		});

		menuManager.addMenu("List Contacts", new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {
				List<Contact> contacts = phoneBook.getAllContacts();
				displayContacts(contacts);
			}
		});

		menuManager.addMenu("Search Contact", new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {
				System.out.print("Enter search keyword: ");
				String searchText = scanner.nextLine();

				List<Contact> contacts = phoneBook.searchContact(searchText);
				if (contacts.isEmpty()) {
					System.out.println("No contact found");
				} else {
					displayContacts(contacts);
				}

			}

		});

		menuManager.start();

	}

}
