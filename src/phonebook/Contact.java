package phonebook;

import java.io.Serializable;

import common.ValidationUtils;
/**
 * Conatct
 * @author anandm
 *
 */
public class Contact implements Serializable {

	/*
	 * Increment any time class is modified
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String number;

	public Contact(String name, String number) {
		super();
		ValidationUtils.validateNotEmpty(name, "name can not be empty");
		ValidationUtils.validatePhoneNumber(number,
				"number must be a 10 digit number");

		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

}
