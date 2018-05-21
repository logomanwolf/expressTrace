package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.alibaba.fastjson.JSON;

import po.Root;
import po.RootAndPoint;
import po.SimpleStyle;
import po.Trace;
import prepro.FileManipulation;

public class MakePoint extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MakePoint() {
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

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String data = new FileManipulation().readJson("simplestep1.json","utf-8");
		List<SimpleStyle> roots = (ArrayList<SimpleStyle>) JSON.parseArray(data, SimpleStyle.class);
		roots =  roots.subList(50, 100);
		List<SimpleStyle> newSimpleRoot=new ArrayList<SimpleStyle>();
		for(int i=0;i<roots.size();i++){
			SimpleStyle simpleStyle=roots.get(i);
			SimpleStyle newSimple=new SimpleStyle();
			ArrayList<String> newAccpts=new ArrayList<String>();
			newAccpts.add(simpleStyle.getAcceptStations().get(0));
			for(int j=0;j<simpleStyle.getAcceptStations().size();j++){
				if(j+1<simpleStyle.getAcceptStations().size())
				if(!simpleStyle.getAcceptStations().get(j).equals(simpleStyle.getAcceptStations().get(j+1)))
				newAccpts.add(simpleStyle.getAcceptStations().get(j+1));	
			}
			newSimple.setAcceptStations(newAccpts);
			newSimpleRoot.add(newSimple);
		}
		
		
		
		String str=JSON.toJSONString(newSimpleRoot);
		new FileManipulation().WriteJson("less.json", str);
		/*ArrayList<Trace> data1=new ArrayList<Trace>();
		for (int i = 0; i < 100; i++) {
			System.out.println(roots.get(i).getTraces().get(0));
		}*/
	//	System.out.println(JSON.toJSONString(roots));
		request.getSession().setAttribute("data", str);
		RequestDispatcher rd = request.getRequestDispatcher("address.jsp");
		rd.forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
