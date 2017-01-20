package common;

/**
 * Helper class for argument validation
 * 
 * @author anandm
 * 
 */
public class ValidationUtils {

	/**
	 * Validate that input is not <code>null</code>. If this validation fails a
	 * {@link IllegalArgumentException} is thrown with user specified message
	 * 
	 * @param input
	 * @param message
	 */
	public static void validateNotNull(Object input, String message) {
		if (input == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Validate that input has text. If this validation fails a
	 * {@link IllegalArgumentException} is thrown with user specified message
	 * 
	 * @param input
	 * @param message
	 */
	public static void validateNotEmpty(String input, String message) {
		validateNotNull(input, message);

		if (message.trim().isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Validate that input is a valid phone number. If this validation fails a
	 * {@link IllegalArgumentException} is thrown with user specified message
	 * 
	 * @param input
	 * @param message
	 */
	public static void validatePhoneNumber(String input, String message) {
		validateNotNull(input, message);
		validateNotEmpty(input, message);

		if (!input.matches("[0-9]{10}")) {
			throw new IllegalArgumentException(message);
		}
	}
}
