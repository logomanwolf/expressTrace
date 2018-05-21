package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import po.Root;
import prepro.FileManipulation;

public class MakePoint2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MakePoint2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
		String path = new FileManipulation().readJson("Simplefinalpath.json", "utf-8");
		String address = new FileManipulation().readJson("SimplelngAndLat.json");
		/*
		 * ArrayList<Trace> data1=new ArrayList<Trace>(); for (int i = 0; i <
		 * 100; i++) { System.out.println(roots.get(i).getTraces().get(0)); }
		 */
		// System.out.println(JSON.toJSONString(roots));
		request.getSession().setAttribute("data", path);
		request.getSession().setAttribute("address", address);
		RequestDispatcher rd = request.getRequestDispatcher("example1.jsp");
		rd.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
