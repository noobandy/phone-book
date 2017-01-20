package menumanager;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class MenuManager {
	private Integer nextMenuNumber = 0;

	private Map<Integer, Menu> menus = new TreeMap<Integer, Menu>();
	private boolean running = false;
	private Scanner scanner;

	/**
	 * 
	 * @param exitCommand
	 *            {@link MenuCommand} to execute before {@link MenuManager}
	 *            exits.
	 */
	public MenuManager(final MenuCommand exitCommand) {
		super();
		Menu exitMenu = new Menu(nextMenuNumber, "Exit", new MenuCommand() {

			@Override
			public void execute(Scanner scanner) {
				running = false;
				if (exitCommand != null) {
					exitCommand.execute(scanner);
				}
				scanner.close();
			}

		});

		menus.put(exitMenu.getSerialNumber(), exitMenu);

		nextMenuNumber++;

		scanner = new Scanner(System.in);
	}

	/**
	 * 
	 * @param name
	 *            {@link Menu} name
	 * @param command
	 *            {@link MenuCommand} to execute when this menu is selected
	 */
	public void addMenu(String name, MenuCommand command) {
		Menu menu = new Menu(nextMenuNumber, name, command);
		menus.put(menu.getSerialNumber(), menu);
		nextMenuNumber++;
	}

	/**
	 * Start menu manager <br>
	 * Once started {@link MenuManager} displays all the menus and waits for
	 * user to select an option. Once user selects an option menu command for
	 * that menu is executed.
	 */
	public void start() {
		// 0 for exit
		if (!running) {
			running = true;
			while (running) {
				displayMenus();
				int selectedMenu = scanner.nextInt();
				scanner.nextLine();
				Menu menu = menus.get(selectedMenu);

				if (menu != null) {
					menu.getMenuCommand().execute(scanner);
				} else {
					System.out.println("Unkonwn option");
				}
			}
		}

	}

	private void displayMenus() {
		for (Entry<Integer, Menu> entry : menus.entrySet()) {
			System.out.format("(%2d ) %s%n",
					entry.getValue().getSerialNumber(), entry.getValue()
							.getName());
		}
		System.out.print("Select any one of the above options: ");
	}

}
