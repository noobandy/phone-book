package webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

	void handleRequest(HttpServletRequest request, HttpServletResponse response);
}
