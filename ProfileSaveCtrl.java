

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/profileSaveAction")
public class ProfileSaveCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profile profile = new Profile();
		profile.setUserid(request.getParameter("userid"));
		profile.setName(request.getParameter("name"));
		profile.setEmail(request.getParameter("email"));
		profile.setMobile(Long.parseLong(request.getParameter("mobile")));
		// save in database
		int res = new ProfileServlet().save(profile);
		System.out.println(res);
		PrintWriter out=response.getWriter();
		String userid = null;
		out.println("userid:"+userid);
		String name = null;
		out.println("name:"+name);

		String email;
		email=null;
		out.println("email:"+email);
		String mobile;
		mobile=null;
		out.println("mobile:"+mobile);

		request.setAttribute("RES", res);
		request.getRequestDispatcher("/profileViewAction").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
