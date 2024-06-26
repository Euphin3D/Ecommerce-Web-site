package com.ecommerce.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ecommerce.dao.UserDaoImp;
import com.ecommerce.pojo.User;

/**
 * Servlet implementation class UserOperation
 */
@WebServlet("/UserOperationServlet")
public class UserOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		
		if(action.equals("deleteUser"))
		{
			int userId=Integer.parseInt(request.getParameter("userId"));
			User user=new UserDaoImp().getUserByUserId(userId);
			String userImage=user.getUserPic();
			File f=new File("C:\\Users\\Monster\\OneDrive\\Masaüstü\\servlet2\\ecomm\\src\\main\\webapp\\userProfilePic\\"+userImage);
			
			  f.delete();
			 
			
			boolean flag=false;
			
			flag=new UserDaoImp().deleteUserById(userId);
			if(flag==true)
			{
				session.setAttribute("message","User Deleted succesfully!");
		        response.sendRedirect("index.jsp"); 	
			}
			
			else
			{
				session.setAttribute("message","Failed to Delete User..!");
		        response.sendRedirect("index.jsp"); 
			}
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
