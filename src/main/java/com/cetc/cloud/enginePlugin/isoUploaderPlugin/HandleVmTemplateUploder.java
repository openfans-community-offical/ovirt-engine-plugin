package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;

public class HandleVmTemplateUploder {

	private String lineAskingPassword = "Please provide the REST API password for the admin@internal oVirt Engine user (CTRL+D to abort):";
	
	public HandleVmTemplateUploder() {

	}

	public String vmTemplateUploder(HttpServletRequest request) {
		FileUtils fu = new FileUtils();
		String fileName = fu.fileUpload(request);
		String userName = request.getParameter("un");
		String pwd = request.getParameter("pwd");
		String export = request.getParameter("ex");
		if (fileName != null && pwd != null && userName != null && export != null) {
			String localPath = fu.FileUploadPath + "/" + fileName;
			String command = "engine-image-uploader -u " + userName + " -p " + pwd + " -e " + export + " upload " + localPath; 
//			CommandUtils ec = new CommandUtils(command);
//			Thread t = new Thread(ec);
//			t.start();
			return "success";
		}
		return null;
	}
}
