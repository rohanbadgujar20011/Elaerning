package com.elearning.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearning.AES.AES;
import com.elearning.SplitMerge.Merge;
 import com.elearning.database.DatabaseConnection;
import com.elearning.mail.SendMailBySite;
import com.elearning.shakey.ShaKey;
import com.elearning.util.Delete;

/**
 * Servlet implementation class TpaAuditServlet
 */
@WebServlet("/TpaAuditServlet")
public class TpaAuditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TpaAuditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		DatabaseConnection db = new DatabaseConnection();
		ResultSet rs = db
				.selectQuery("select distinct(filename),hashkey,id from filelog");
		ArrayList<String> fileNameArrayList = new ArrayList<String>();
		ArrayList<String> oldHashKey = new ArrayList<String>();
		ArrayList<String> alForIntegrity = new ArrayList<String>();
		ArrayList<String> fid = new ArrayList<String>();
		ArrayList<String> ifid = new ArrayList<String>();

		HttpSession s = request.getSession();
		ArrayList<String> alForUid = new ArrayList<String>();

		String docPath = request.getServletContext().getRealPath("/doc");
		try {
			while (rs.next()) {
				fileNameArrayList.add(rs.getString(1));
				oldHashKey.add(rs.getString(2));
				fid.add(rs.getString(3));
			}

			for (int i = 0; i < fileNameArrayList.size(); i++) {
				String fname = fileNameArrayList.get(i);
				String fidd = fid.get(i);
				String filePath = Merge.merge(docPath + "/" + fname);
				String filePath1 = docPath + "/" + fname;
				AES.decrypt(filePath);
				ShaKey m = new ShaKey();
				String newHashKey = m.checkSum(filePath);
				if (!oldHashKey.get(i).equals(newHashKey)) {
					System.out.println("fname :: " + fname);
					alForIntegrity.add(fname);
					ifid.add(fidd);
					String backupFile = request.getServletContext()
							.getRealPath("temp") + "/" + fname;

					/*
					 * Delete.delete(filePath1); Split1.split(backupFile,
					 * docPath+File.separator);
					 */
				}

				System.out.println("ifid : " + ifid);
				System.out.println("Merged file :: "
						+ new File(filePath).delete());
				s.setAttribute("result", alForIntegrity);
			}
			for (int j = 0; j < ifid.size(); j++) {
				String aa = ifid.get(j);
				System.out.println("aa :: " + aa);
				ResultSet rs1 = db
						.selectQuery("select uid,displayname from filelog where id='"
								+ aa + "'");
				if (rs1.next()) {
					String msg = "Your " + rs1.getString("displayname")
							+ " File got tempered. Successfully restored";
					System.out.println("msg : " + msg);
					ResultSet rs2 = db
							.selectQuery("select email from signup where id='"
									+ rs1.getString("uid") + "'");
					
					String email = null;
					if (rs2.next()) {
						email = rs2.getString("email");
						System.out.println("email : " + email);
					}
					SendMailBySite.sendMail(email, msg);

					/* alForUid.add(rs1.getString("uid")); */

				}
			}
			/*for (int j = 0; j < alForUid.size(); j++) {
				ResultSet rs1 = db
						.selectQuery("select email from filelog where id='"
								+ alForUid.get(j) + "'");
				if (rs1.next())
					alForUid.add(rs1.getString("uid"));
			}
			System.out.println("aaaac :: " + alForUid);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("TpaAudit.jsp");
	}

}
