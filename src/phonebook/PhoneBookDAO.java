package phonebook;


public interface PhoneBookDAO {

	PhoneBook getPhoneBook() throws Exception;
	
	void savePhoneBook(PhoneBook phoneBook) throws Exception;
}
