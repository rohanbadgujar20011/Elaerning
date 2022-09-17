package com.elearning.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
 import com.dropbox.core.v2.DbxUsers.SpaceUsage;

/**
 * Servlet implementation class demo
 */
@WebServlet("/demo")
public class demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public demo() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ACCESS_TOKEN = "8odRS2AfRMAAAAAAAAAAB4wK56rJ-YqLiFr8pS_UTNsxEbcAhMztQTP3p42W-kRH";

		System.out.println(ACCESS_TOKEN + "=access-token");
		DbxRequestConfig config = new DbxRequestConfig(
				"dropbox/java-tutorial", "en_US");
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		try {
			//client.files.delete("/"+"(1)(21-03-2016)(11_34_46AM)#Acoustic-Cuts-BLUSKREEN.jpg");
		 
			SpaceUsage m=client.users.getSpaceUsage();
			System.out.println(m);
           String usageDetail=m.toString();
           String usedSpace=usageDetail.substring(usageDetail.indexOf(':')+1,usageDetail.indexOf(','));
           System.out.println(usedSpace);
           
           
           
           
		//	client.getUsersManager().getCurrentUser(null).getSpaceUsed();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
