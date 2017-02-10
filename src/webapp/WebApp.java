package webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebApp {
	
	void start();
	void stop();
	void service(HttpServletRequest request, HttpServletResponse response);
}
