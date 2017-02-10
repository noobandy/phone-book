package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import phonebook.PhoneBook;
import phonebook.PhoneBookDAO;

public class FilePhoneBookDAO implements PhoneBookDAO {
	private static final String DATA_FILE_NAME = "D:\\Office Workspace\\phone-book\\phonebook.dat";

	public FilePhoneBookDAO() {
		super();
	}

	@Override
	public PhoneBook getPhoneBook() {

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

	@Override
	public void savePhoneBook(PhoneBook phoneBook) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(
					DATA_FILE_NAME)));
			out.writeObject(phoneBook);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			if (out != null) {
				out.close();

			}
		}
	}

}
