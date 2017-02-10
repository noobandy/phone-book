package webapp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import phonebook.Contact;
import phonebook.PhoneBook;
import phonebook.PhoneBookDAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PhoneWebApp implements WebApp {

	public enum RequestMethod {
		GET, POST;
	}

	public static class Route {
		private String path;
		private RequestMethod method;

		public Route(String path, RequestMethod method) {
			super();
			this.path = path;
			this.method = method;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((method == null) ? 0 : method.hashCode());
			result = prime * result + ((path == null) ? 0 : path.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Route other = (Route) obj;
			if (method != other.method)
				return false;
			if (path == null) {
				if (other.path != null)
					return false;
			} else if (!path.equals(other.path))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Route [path=" + path + ", method=" + method + "]";
		}

	}

	private Map<Route, Handler> routeMapping = new HashMap<Route, Handler>();
	private PhoneBook phoneBook = null;
	private PhoneBookDAO phoneBookDAO = null;
	private Gson gson;

	private boolean running;

	public PhoneWebApp(PhoneBookDAO phoneBookDAO) {
		super();
		this.phoneBookDAO = phoneBookDAO;
		this.running = false;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	@Override
	public void start() {
		if (!running) {
			running = true;
			try {
				phoneBook = phoneBookDAO.getPhoneBook();
			} catch (Exception e) {
				System.err.println(e);
			}
			routeMapping.put(new Route("/contacts", RequestMethod.GET),
					new Handler() {

						@Override
						public void handleRequest(HttpServletRequest request,
								HttpServletResponse response) {
							String searchText = request.getParameter("q");
							List<Contact> contacts = Collections.emptyList();
							if(searchText != null && !searchText.trim().isEmpty()) {
								contacts = phoneBook.searchContact(searchText.trim());
							} else {
								contacts = phoneBook.getAllContacts();
							}
							
							response.setStatus(200);
							response.addHeader("Content-Type", "Application/json");
							OutputStream responseStream = null;
							try {
								responseStream = response.getOutputStream();
								responseStream.write(gson.toJson(contacts).getBytes());
							} catch (Exception e) {
								System.err.println(e);
								try {
									response.sendError(500);
								} catch (IOException e1) {
									System.err.println(e1);
								}
							} finally {
								if(responseStream != null) {
									try {
										responseStream.close();
									} catch (IOException e) {
										System.err.println(e);
										try {
											response.sendError(500);
										} catch (IOException e1) {
											System.err.println(e1);
										}
									}
								}
							}
							
						}
					});

			routeMapping.put(new Route("/contacts", RequestMethod.POST),
					new Handler() {

						@Override
						public void handleRequest(HttpServletRequest request,
								HttpServletResponse response) {
							phoneBook.addContact(request.getParameter("name"),
									request.getParameter("number"));
							response.setStatus(201);
							try {
								response.getWriter().close();
							} catch (IOException e) {
								System.err.println(e);
							}
						}
					});

		}

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		String requestMethod = request.getMethod();
		String[] uriTokens = request.getRequestURI().split("/");
		StringBuilder pathBuilder = new StringBuilder();
		
		for(int i = 2; i < uriTokens.length; i++) {
			pathBuilder.append("/");
			pathBuilder.append(uriTokens[i]);
		}
		if(pathBuilder.length() == 0) {
			pathBuilder.append("/");
		}
		
		String requestPath = pathBuilder.toString();
		Route route = new Route(requestPath,
				RequestMethod.valueOf(requestMethod));
		Handler handler = routeMapping.get(route);
		if (handler == null) {
			try {
				response.sendError(404, "not found");
			} catch (IOException e) {
				System.err.println(e);
				try {
					response.sendError(500);
				} catch (IOException e1) {
					System.err.println(e1);
				}
			}
		} else {
			handler.handleRequest(request, response);
		}
	}

	@Override
	public void stop() {
		if (running) {
			running = false;
			routeMapping.clear();
			try {
				phoneBookDAO.savePhoneBook(phoneBook);
			} catch (Exception e) {
				System.err.println(e);
			}
			phoneBook = null;
		}
	}

}
