package webapp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phonebook.PhoneBookDAO;
import data.FilePhoneBookDAO;

public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PhoneBookDAO phoneBookDAO;

	private WebApp webApp;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		webApp.service(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		webApp.service(req, resp);
	}

	@Override
	public void destroy() {

		super.destroy();
		phoneBookDAO = null;
		webApp.stop();
		webApp = null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		phoneBookDAO = new FilePhoneBookDAO();
		webApp = new PhoneWebApp(phoneBookDAO);
		webApp.start();
	}

}
