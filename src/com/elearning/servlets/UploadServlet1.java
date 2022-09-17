package com.elearning.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.elearning.AES.AES;
import com.elearning.SplitMerge.Split;
import com.elearning.database.DatabaseConnection;
import com.elearning.shakey.ShaKey;

/**
 * Servlet implementation class UploadServlet1
 */
@WebServlet("/UploadServlet1")


public class UploadServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// creating date format to append with splited file names
		System.out.println("in upload1");
		String hashkey = null;
		String filename = "";
		String displayname = "";
		String uid = "";
		String filesize = "";
		
		String year=request.getParameter("year");
		   System.out.println(year);
		   
		   String branch=request.getParameter("branch");
		   System.out.println(branch);
		
		  String target= ".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/";
			String replacement= "";
		 	 
			String location=this.getServletContext().getRealPath("/doc");
			String docpath = location;
		     //request.getServletContext().getRealPath("doc");
		
		//String backuppath = request.getServletContext().getRealPath("/temp");
	 	int k = 0;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH.mm.ss");
		String currentTime = sdf.format(dt);
		HttpSession s = request.getSession();
		try {
			// creating list of fileitem
			List<FileItem> multiparts = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);

			// loop taking files coming from multiparts
			for (FileItem file : multiparts) {

				
				
				   
				   
				InputStream is = file.getInputStream();
				displayname = "(" + currentTime + ")" + file.getName();
				System.out.println("display name :: " + displayname);
				hashkey = new ShaKey().checkSum1(is);
				float sz = file.getSize() / (1024);
				DatabaseConnection db = new DatabaseConnection();
				ResultSet rs = db
						.selectQuery("select * from filelog where hashkey='"
								+ hashkey + "'");
				{
					if (rs.next()) {
						if (rs.getString("uid").equals(s.getAttribute("uid"))) {
							k = 1;
							s.setAttribute(
									"dedup",
									"File is already exists with name :: "
											+ rs.getString("displayname"));
						}
						filename = rs.getString("filename");
					} else {
						filename = displayname;
						File originalfile = new File(docpath + File.separator
								+ displayname);
	 					// writing data into file
						file.write(originalfile);
	 
						/*
						 * displayname="(" + currentTime + ")" + displayname;
						 * File originalfile1 = new File(docpath +
						 * File.separator + displayname);
						 * System.out.println(originalfile1.getAbsolutePath());
						 * 
						 * originalfile.renameTo(originalfile1);
						 */
						AES.encrypt(docpath + File.separator + displayname);
	 					Split.split(docpath + File.separator + displayname,
								docpath + File.separator);
	 					String f11 = docpath + File.separator + "(1)"
								+ displayname;
						String f12 = docpath + File.separator + "(2)"
								+ displayname;
						String f13 = docpath + File.separator + "(3)"
								+ displayname;
						InputStream is1 = new FileInputStream(f11);
						InputStream is2 = new FileInputStream(f12);
						InputStream is3 = new FileInputStream(f13);

						String ACCESS_TOKEN = "MqJ3td0XUpYAAAAAAAAAAafUXercEQINUdD8CAXZTAuffZWT_gdnw2PUZyTuXOXC";

						System.out.println(ACCESS_TOKEN + "access-token");
						DbxRequestConfig config = new DbxRequestConfig(
								"dropbox/java-tutorial", "en_US");
						DbxClientV2 client = new DbxClientV2(config,
								ACCESS_TOKEN);

						client.files.uploadBuilder(
								"/DriveOne/" + "(1)" + displayname).run(is1);
						client.files.uploadBuilder(
								"/DriveSecond/" + "(2)" + displayname).run(is2);
						client.files.uploadBuilder(
								"/DriveThird/" + "(3)" + displayname).run(is3);
						is1.close();
						is2.close();
						is3.close();
					}
					int i = 0;
					if (k == 0) {
						
						
						i = db.updateQuery("insert into filelog(filename,filesize,hashkey,uid,displayname,year,branch) values('"
								+ filename
								+ "','"
								+ sz
								+ "','"
								+ hashkey
								+ "','"
								+ s.getAttribute("uid")
								+ "','"
								+ displayname + "','"+year+"','"+branch+"','");
					
					}	
					if (i == 1) {
						s.setAttribute("flag", "Fail");
					}
				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
			s.setAttribute("flag", "Fail");

		}
		response.sendRedirect("UploadDocs.jsp");
	}
}
