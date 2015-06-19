package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FileUploadStatusServlet extends HttpServlet {

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
		// 此处不是创建session 而是去取已经创建的session
		HttpSession session = request.getSession();
		// 如果已经取到，说明已经登录
		if (session != null) {
			String id = session.getId();
			System.out.print(id);
			FileUploadStatus uploadStatus = (FileUploadStatus) session
					.getAttribute("uploadStatus"); //$NON-NLS-1$
			if (uploadStatus != null) {
				String status = uploadStatus.getPercent();// 获取上传进度百分比
				out.println(status);// 响应
			}
		}
	}

}
