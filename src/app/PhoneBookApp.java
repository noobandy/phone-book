package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import menumanager.MenuCommand;
import menumanager.MenuManager;
import phonebook.Contact;
import phonebook.PhoneBook;

/**
 * Phone Book application
 * 
 * @author anandm
 * 
 */
public class PhoneBookApp {

	private static final String DATA_FILE_NAME = "phonebook.dat";
	private static final String ERROR_FILE_NAME = "error.log";

	private static PhoneBook initializePhoneBook() {
		PhoneBook phoneBook = null;
		File phoneBookData = new File(DATA_FILE_NAME);

		if (phoneBookData.exists()) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream(phoneBookData));
				phoneBook = (PhoneBook) in.readObject();
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}

		if (phoneBook == null) {
			phoneBook = new PhoneBook();
		}

		return phoneBook;
	}

	private static void displayContacts(List<Contact> contacts) {

		int serialNumber = 1;
		System.out.format("%41s%n", "Contacts");
		for (Contact contact : contacts) {
			System.out.format("%4d %25s %10s%n", serialNumber++,
					contact.getName(), contact.getNumber());

		}
	}

	public static void main(String[] args) {
		try {
			System.setErr(new PrintStream(new File(ERROR_FILE_NAME)));
		} catch (FileNotFoundException e1) {
			System.out
					.println("unable to change error stream. default is console");
		}

		final PhoneBook phoneBook = initializePhoneBook();

		MenuManager menuManager = new MenuManager(new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {
				ObjectOutputStream out = null;
				try {
					out = new ObjectOutputStream(new FileOutputStream(new File(
							DATA_FILE_NAME)));
					out.writeObject(phoneBook);
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				} finally {
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							System.err.println(e.getMessage());
						}
					}
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
