package util;

import javax.servlet.http.HttpServletRequest;

import model.User;

public class SessionUtil {

	public static User getUser(HttpServletRequest req) {
		if(req.getSession() !=null && req.getSession().getAttribute("user")!=null) {
			return ((User)req.getSession().getAttribute("user"));
		}
		return null;
	}
		
	public static int getUserId(HttpServletRequest req) {
		if(req.getSession()!=null && req.getSession().getAttribute("user")!=null) {
			return ((User)req.getSession().getAttribute("user")).getId();
		}
		return -1;
	}
	
	public static String getUserType(HttpServletRequest req) {
		if(req.getSession()!=null && req.getSession().getAttribute("user")!=null) {
			return ((User)req.getSession().getAttribute("user")).getUserType();
		}
		return "DUMMY";
	}
		
		
}
