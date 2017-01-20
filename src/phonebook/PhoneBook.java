package phonebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Phone book
 * 
 * @author anandm
 * 
 */
public class PhoneBook implements Serializable {

	/*
	 * Increment any time class is modified
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Contact> contacts = new TreeMap<String, Contact>();

	public PhoneBook() {
		super();
	}

	/**
	 * Add a new contact
	 * 
	 * @param name
	 *            name of the contact person
	 * @param number
	 *            number of the contact person
	 */
	public void addContact(String name, String number) {
		Contact contact = new Contact(name, number);
		contacts.put(name, contact);
	}

	/**
	 * Get a contact by name
	 * 
	 * @param name
	 * @return
	 */
	public Contact getContact(String name) {
		Contact contact = contacts.get(name);
		return contact;
	}

	/**
	 * Get All contacts
	 * 
	 * @return
	 */
	public List<Contact> getAllContacts() {
		return new ArrayList<Contact>(contacts.values());
	}

	/**
	 * Search contact
	 * 
	 * @param searchtext
	 * @return all contacts whose name or number contains searchText
	 */
	public List<Contact> searchContact(String searchtext) {
		List<Contact> searchResult = new ArrayList<Contact>();

		for (Entry<String, Contact> entry : contacts.entrySet()) {

			if (entry.getValue().getName().toLowerCase()
					.contains(searchtext.toLowerCase())
					|| entry.getValue().getNumber().contains(searchtext)) {
				searchResult.add(entry.getValue());
			}
		}

		return searchResult;
	}
}
