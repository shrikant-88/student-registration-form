package com.ims.registation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ims.registation.DBUtil.DButil;

@WebServlet("/registation")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SQL_INSERT = "insert into IMS_Student values(?,?,?,?,?,?)";
	public static final String SQL_SELECT = "select * from IMS_Student";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 
        PrintWriter out = response.getWriter();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
        	
        	connection = DButil.getDBConection();
        	
        	stmt = connection.createStatement();
        	rs = stmt.executeQuery(SQL_SELECT);
        	out.println("<!DOCTYPE html>\r\n"
        			+ "<html lang=\"en\">\r\n"
        			+ "  <head>\r\n"
        			+ "    <meta charset=\"UTF-8\" />\r\n"
        			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
        			+ "    <title>Student List</title>\r\n"
        			+ "    <style>\r\n"
        			+ "      body {\r\n"
        			+ "        text-align: center;\r\n"
        			+ "        display: flex;\r\n"
        			+ "        flex-direction: column;\r\n"
        			+ "        justify-content: center;\r\n"
        			+ "        align-items: center;\r\n"
        			+ "      }\r\n"
        			+ "      .list {\r\n"
        			+ "        width: 85%;\r\n"
        			+ "        height: auto;\r\n"
        			+ "        border: 1px black solid;\r\n"
        			+ "      }\r\n"
        			+ "      table {\r\n"
        			+ "        width: 100%;\r\n"
        			+ "        height: 100%;\r\n"
        			+ "      }\r\n"
        			+ "      th {\r\n"
        			+ "        background-color: aquamarine;\r\n"
        			+ "        border: 2px black solid;\r\n"
        			+ "        margin: 1%;\r\n"
        			+ "      }\r\n"
        			+ "      td {\r\n"
        			+ "        background-color: lightskyblue;\r\n"
        			+ "        border: 2px black solid;\r\n"
        			+ "        margin: 1%;\r\n"
        			+ "      }\r\n"
        			+ "      .action {\r\n"
        			+ "        display: flex;\r\n"
        			+ "        justify-content: center;\r\n"
        			+ "        align-items: center;\r\n"
        			+ "        margin: 5%;\r\n"
        			+ "        width: 90%;\r\n"
        			+ "        height: 60%;\r\n"
        			+ "      }\r\n"
        			+ "      .action a {\r\n"
        			+ "        text-decoration: none;\r\n"
        			+ "        border-radius: 10px;\r\n"
        			+ "        height: 30px;\r\n"
        			+ "        width: 80px;\r\n"
        			+ "        text-align: center;\r\n"
        			+ "        margin: 3%;\r\n"
        			+ "      }\r\n"
        			+ "      \r\n"
        			+ "      .get {\r\n"
        			+ "        background-color: rgb(135, 235, 153);\r\n"
        			+ "      }\r\n"
        			+ "      .register {\r\n"
        			+ "        background-color: rgb(244, 194, 86);\r\n"
        			+ "      }"
        			+ "    </style>\r\n"
        			+ "  </head>\r\n"
        			+ "  <body>\r\n"
        			+ "    <h1>Student List</h1>\r\n"
        			+ "    <div class=\"list\">\r\n"
        			+ "      <table>\r\n"
        			+ "        <tr>\r\n"
        			+ "          <th>name</th>\r\n"
        			+ "          <th>roll no</th>\r\n"
        			+ "          <th>age</th>\r\n"
        			+ "          <th>gender</th>\r\n"
        			+ "          <th>course</th>\r\n"
        			+ "          <th>e-mail</th>\r\n"
        			+ "        </tr>\r\n");
        	while(rs.next()) {
	        	String name1 = rs.getString(1);
	        	String rollNo = rs.getString(2); 	
	        	String age1 = rs.getString(3);
	        	String gender1 = rs.getString(4);
	        	String course1 = rs.getString(5);
	        	String Email = rs.getString(6);
	        	out.println("<tr>\r\n"
	        			+ "          <td>"+name1+"</td>\r\n"
	        			+ "          <td>"+rollNo+"</td>\r\n"
	        			+ "          <td>"+age1+"</td>\r\n"
	        			+ "          <td>"+gender1+"</td>\r\n"
	        			+ "          <td>"+course1+"</td>\r\n"
	        			+ "          <td>"+Email+"</td>\r\n"
	        			+ "        </tr>");
	        }
        	
	        out.println(" </table>\r\n"
	        		+ "    </div>\r\n"
	        		+ "    <div class=\"action\">\r\n"
	        		+ "        <a href=\"registationform.html\" class=\"register\">register</a>\r\n"
	        		+ "        <form method=\"get\" action=\"./registation\"></form>\r\n"
	        		+ "        <a type=\"submit\" href=\"./registation\" class=\"get\">Get List</a>\r\n"
	        		+ "    </div>"
	        		+ "  </body>\r\n"
	        		+ "</html>\r\n"
	        		+ "");
 
        }catch(Exception e) {
        	System.out.println(e);
        }
        finally {
        	DButil.cleanUpResources(rs, null, connection);
        }
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String roll = request.getParameter("ROLL");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String course = request.getParameter("course");
		String email = request.getParameter("email");
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		PrintWriter out = response.getWriter();
		
		try {
			conn = DButil.getDBConection();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setString(1, name);
			ps.setString(2, roll);
			ps.setString(3, age);
			ps.setString(4, gender);
			ps.setString(5, course);
			ps.setString(6, email);
			
			int rowCount = ps.executeUpdate();

            if (rowCount != 0) {
            	out.println("<!DOCTYPE html>\r\n"
            			+ "<html lang=\"en\">\r\n"
            			+ "  <head>\r\n"
            			+ "    <meta charset=\"UTF-8\" />\r\n"
            			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
            			+ "    <title>response</title>\r\n"
            			+ "    <style>\r\n"
            			+ "      .response {\r\n"
            			+ "        height: 100%;\r\n"
            			+ "        width: 100%;\r\n"
            			+ "        display: flex;\r\n"
            			+ "        flex-direction: column;\r\n"
            			+ "        justify-content: center;\r\n"
            			+ "        align-items: center;\r\n"
            			+ "        /* margin: 5%; */\r\n"
            			+ "        margin-top: 5%;\r\n"
            			+ "      }\r\n"
            			+ "\r\n"
            			+ "      h1 {\r\n"
            			+ "        color: green;\r\n"
            			+ "      }\r\n"
            			+ "      .action {\r\n"
            			+ "        display: flex;\r\n"
            			+ "        justify-content: center;\r\n"
            			+ "        align-items: center;\r\n"
            			+ "        margin: 5%;\r\n"
            			+ "        width: 90%;\r\n"
            			+ "        height: 60%;\r\n"
            			+ "      }\r\n"
            			+ "      .action a {\r\n"
            			+ "        text-decoration: none;\r\n"
            			+ "        border-radius: 10px;\r\n"
            			+ "        height: 30px;\r\n"
            			+ "        width: 80px;\r\n"
            			+ "        text-align: center;\r\n"
            			+ "        margin: 3%;\r\n"
            			+ "      }\r\n"
            			+ "\r\n"
            			+ "      .get {\r\n"
            			+ "        background-color: rgb(135, 235, 153);\r\n"
            			+ "      }\r\n"
            			+ "      .home {\r\n"
            			+ "        background-color: plum;\r\n"
            			+ "      }\r\n"
            			+ "      .register {\r\n"
            			+ "        background-color: rgb(244, 194, 86);\r\n"
            			+ "      }\r\n"
            			+ "    </style>\r\n"
            			+ "  </head>\r\n"
            			+ "  <body>\r\n"
            			+ "    <div class=\"response\">\r\n"
            			+ "      <marquee><h1>data inserted successfully</h1></marquee>\r\n"
            			+ "      <div class=\"action\">\r\n"
            			+ "        <a href=\"/com.ims.registation\" class=\"home\">Home</a>\r\n"
            			+ "        <a href=\"registationform.html\" class=\"register\">register</a>\r\n"
            			+ "        <form method=\"get\" action=\"./registation\"></form>\r\n"
            			+ "        <a type=\"submit\" href=\"./registation\" class=\"get\">Get List</a>\r\n"
            			+ "      </div>\r\n"
            			+ "    </div>\r\n"
            			+ "  </body>\r\n"
            			+ "</html>\r\n"
            			+ "");
            	System.out.println("data inserted successfully");
            } else {
                out.println("<!DOCTYPE html>\r\n"
                		+ "<html lang=\"en\">\r\n"
                		+ "  <head>\r\n"
                		+ "    <meta charset=\"UTF-8\" />\r\n"
                		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
                		+ "    <title>response</title>\r\n"
                		+ "    <style>\r\n"
                		+ "      .response {\r\n"
                		+ "        height: 100%;\r\n"
                		+ "        width: 100%;\r\n"
                		+ "        display: flex;\r\n"
                		+ "        flex-direction: column;\r\n"
                		+ "        justify-content: center;\r\n"
                		+ "        align-items: center;\r\n"
                		+ "        /* margin: 5%; */\r\n"
                		+ "        margin-top: 5%;\r\n"
                		+ "      }\r\n"
                		+ "\r\n"
                		+ "      h1 {\r\n"
                		+ "        color: green;\r\n"
                		+ "      }\r\n"
                		+ "      .action {\r\n"
                		+ "        display: flex;\r\n"
                		+ "        justify-content: center;\r\n"
                		+ "        align-items: center;\r\n"
                		+ "        margin: 5%;\r\n"
                		+ "        width: 90%;\r\n"
                		+ "        height: 60%;\r\n"
                		+ "      }\r\n"
                		+ "      .action a {\r\n"
                		+ "        text-decoration: none;\r\n"
                		+ "        border-radius: 10px;\r\n"
                		+ "        height: 30px;\r\n"
                		+ "        width: 80px;\r\n"
                		+ "        text-align: center;\r\n"
                		+ "        margin: 3%;\r\n"
                		+ "      }\r\n"
                		+ "\r\n"
                		+ "      .get {\r\n"
                		+ "        background-color: rgb(135, 235, 153);\r\n"
                		+ "      }\r\n"
                		+ "      .home {\r\n"
                		+ "        background-color: plum;\r\n"
                		+ "      }\r\n"
                		+ "      .register {\r\n"
                		+ "        background-color: rgb(244, 194, 86);\r\n"
                		+ "      }\r\n"
                		+ "    </style>\r\n"
                		+ "  </head>\r\n"
                		+ "  <body>\r\n"
                		+ "    <div class=\"response\">\r\n"
                		+ "      <marquee><h1>data inserted successfully</h1></marquee>\r\n"
                		+ "      <div class=\"action\">\r\n"
                		+ "        <a href=\"/\" class=\"home\">Home</a>\r\n"
                		+ "        <a href=\"registationform.html\" class=\"register\">register</a>\r\n"
                		+ "        <form method=\"get\" action=\"./registation\"></form>\r\n"
                		+ "        <a type=\"submit\" href=\"./registation\" class=\"get\">Get List</a>\r\n"
                		+ "      </div>\r\n"
                		+ "    </div>\r\n"
                		+ "  </body>\r\n"
                		+ "</html>\r\n"
                		+ "");
                System.out.println("Failed to insert data");
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
