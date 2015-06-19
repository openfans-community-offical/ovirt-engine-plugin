package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5448347862150822030L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); //$NON-NLS-1$
		PrintWriter out = response.getWriter();
		String command = request.getParameter("cmd"); 
		String file = request.getParameter("file");
		CommandUtils ec = new CommandUtils(command,file);
		Thread t = new Thread(ec);
		t.start();
		out.println("success");
	}

}
