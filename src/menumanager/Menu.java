package menumanager;

public class Menu {

	private int serialNumber;
	private String name;
	private MenuCommand menuCommand;

	/**
	 * 
	 * @param serialNumber
	 *            serial no. of the menu. <br>
	 *            Menues are displayed in order specified by their serial number
	 * @param name
	 *            Name of the menu.
	 * @param menuCommand
	 *            Menu command to execute when this menu is selected
	 */
	public Menu(int serialNumber, String name, MenuCommand menuCommand) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.menuCommand = menuCommand;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public String getName() {
		return name;
	}

	public MenuCommand getMenuCommand() {
		return menuCommand;
	}

}
