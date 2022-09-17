package com.elearning.servlets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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
 * encrypt data at server side
 * 
 * @author Uday
 * @since 06/10/2015
 * @see HttpServlet
 * 
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String log = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private String UPLOAD_DIRECTORY = "";

	public UploadServlet() {
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

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param key
	 *            of 16bit
	 * @param filePath
	 * 
	 *            this method uploads the file and checks that if file already
	 *            uploaded than access previous file
	 * 
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Upload Servlet");
		float filesize;
		HttpSession s = request.getSession();
		String result = "success";
		int a = 0;
		int status = 0;
		String shaKEY= null;
		String fileName="";
		String f11=null;
		String f12=null;
		String f13=null;
		String oname=null;
		InputStream is1 = null,is2= null,is3= null;
		String uid=s.getAttribute("uid").toString();
		{
			String relativePath = "/temp/";
			System.out.println(relativePath);
			// getting path of doc folder
			// creating date format to append with splited file names
			Date d = new Date();

			// setting date format in form of day month year and time
			SimpleDateFormat df = new SimpleDateFormat("(dd-MM-yyyy)(h_m_sa)");

			 

			try {
				// creating list of fileitem
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);

				// loop taking files coming from multiparts
				for (FileItem file : multiparts) {
					String str = file.getFieldName();
					if (str.equalsIgnoreCase("result")) {
						System.out.println(file.getString());
					}

					if (!file.isFormField()) {
						a++;
						
						String path = new File(file.getName()).getName();
					
						String f1 = df.format(d) + "#" + path;
						System.out.println(path);
						try {
							setUPLOAD_DIRECTORY(getServletContext().getRealPath(relativePath));
							// creating file by name which is to be uploade in
							// doc folder
							File originalfile = new File(getUPLOAD_DIRECTORY()
									+ File.separator + f1);
							
							// writing data into file
							file.write(originalfile);
							// gererating md5 key for file data
							ShaKey shaKey= new ShaKey();
							FileInputStream  Stream = new FileInputStream(
									originalfile);
							shaKEY = shaKey.checkSum(Stream);
							System.out.println(shaKEY);

							// comparing new md5 key with old md5 in database
							{
								DatabaseConnection db=new DatabaseConnection();
							ResultSet rs=	db.selectQuery( " select * from filelog  where md5key='"
												+ shaKEY + "' and delstatus='"+0+"'");
							String uname=null;
								if (rs.next()) {
									status = 1;
									uname=rs.getString(2);
									
 									s.setAttribute("result",
											"file already exist with same content with name:="+uname.substring(uname.lastIndexOf('#')+1));
									s.setAttribute("filePath","");
									Stream.close();
									System.out.println("old="+ originalfile.delete());
								}
								else{
									uname=f1;
								}
								
							}
							// if keys matches than same file is already there.
							// so it will not upload it again
							if (status == 0) {
								System.out.println("Hello");
								String relativePath1 = "/doc/";
								System.out.println(relativePath1);
								setUPLOAD_DIRECTORY(getServletContext()
										.getRealPath(relativePath1));
								
								String originalfile1=getUPLOAD_DIRECTORY() + File.separator
										 + File.separator;
							 	System.out.println("file uploded at"+getUPLOAD_DIRECTORY()
										+ File.separator + path);
								 
									
									AES.encrypt(originalfile.getAbsolutePath());
									
								//	fileName=Split.split(originalfile.getAbsolutePath(),originalfile1);
									 
									// here we calculating file size
									filesize = ((float) originalfile.length() / (1024 * 1024));
									double size = filesize;
									
									// insert values into filelog1 table
									try{
										 {
	 											File inputFile = new File(originalfile.getAbsolutePath());
												// appending date,time and numbers with split file names
												  f11 = originalfile1 + "(1)" + inputFile.getName();
												  f12 = originalfile1 + "(2)" + inputFile.getName();
												  f13 = originalfile1 + "(3)" + inputFile.getName();
												  is1=new FileInputStream(f11);
												  is2=new FileInputStream(f12);
												  is3=new FileInputStream(f13);
												
												String ACCESS_TOKEN = "MqJ3td0XUpYAAAAAAAAAAafUXercEQINUdD8CAXZTAuffZWT_gdnw2PUZyTuXOXC";
												 
													System.out.println(ACCESS_TOKEN+"access-token");
													DbxRequestConfig config = new DbxRequestConfig(
															"dropbox/java-tutorial", "en_US");
													DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

													 
													client.files.uploadBuilder("/" +"(1)" + inputFile.getName()).run(is1);
													client.files.uploadBuilder("/" +"(2)" + inputFile.getName()).run(is2);
													client.files.uploadBuilder("/" +"(3)" + inputFile.getName()).run(is3);
												 
												}
										DecimalFormat dff = new DecimalFormat("#.##");
										DatabaseConnection db=new DatabaseConnection();
										
									int i=	db.updateQuery( "insert into filelog(filename,filesize,hashkey,uid) values ('"
											+ fileName
											+ "','"
											+ dff.format(size)
											+ "','"
											+ shaKEY
											+ "','"
											+ s.getAttribute("uid") + "')");

									
 									
									s.setAttribute("result",
											"File successfully uploaded");
								 	s.setAttribute("filePath","And file location is:="+originalfile1+path);
									db.closeConnection();
								 	Stream.close();
									System.out.println("ShaKey:= "+shaKEY);
									//System.out.println("Temp file delete="+ originalfile.delete());
								
								} catch (Exception ex) {
									System.out.println("problem");
									is1.close();
									is2.close();
									is3.close();
									
									new File(f11).delete();
									new File(f12).delete();
									new File(f13).delete();
									
									
									originalfile.delete();
									s.setAttribute("result",
											"problem,file not uploaded");
									ex.printStackTrace();
								}
									
							}
							status=0;
						} catch (Exception e) {
							 
							e.printStackTrace();
						}
						
					}
				}
				if (a == 0)
					result = "fail";
				PrintWriter pw = response.getWriter();
				pw.println(result);
 			} catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
			response.sendRedirect("UploadDocs.jsp");
			response.setContentType("application/json");
 		}
		
	}

	private void setUPLOAD_DIRECTORY(String realPath) {
		// TODO Auto-generated method stub
		this.UPLOAD_DIRECTORY = realPath;
		System.out.println("upload Direct"+realPath);
	}

	private String getUPLOAD_DIRECTORY() {
		// TODO Auto-generated method stub
		
		return UPLOAD_DIRECTORY;
	}

}