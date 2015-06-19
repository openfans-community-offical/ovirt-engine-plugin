package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4711652847880846576L;

	@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        saveUploadFile(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        saveUploadFile(request, response);
    }

    public void saveUploadFile(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        boolean flag = true;
        String kind = request.getParameter("kind");
        if (kind != null && !kind.equals("") && kind.equals("uploadVmTemplate")) {
            HandleVmTemplateUploder handleVmTemplateUploder = new HandleVmTemplateUploder();
            String res = handleVmTemplateUploder.vmTemplateUploder(request);
            writer.append(res);
        }
    }

}
