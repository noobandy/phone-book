package menumanager;

import java.util.Scanner;

public interface MenuCommand {
	/**
	 * 
	 * @param scanner for reading input from standard input stream
	 */
	void execute(Scanner scanner);
}
