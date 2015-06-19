package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUtils {

	// public static final String FileUploadPath = "/usr/engine-plugin/upload";
	// public static final String FileUploadTemp = "/usr/engine-plugin/temp";

	public static final String FileUploadPath = "/home/user/upload";
	public static final String FileUploadTemp = "/home/user/temp";

	/**
	 * delete file
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * read file to String
	 */
	public String readString(String path) {
		try {
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			String str = new String(buffer);
			return str;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getUploadPath() {
		File file = new File(FileUploadPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		return FileUploadPath;
	}

	public String fileUpload(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();

		FileUploadStatus status = new FileUploadStatus();
		String id = request.getSession().getId();
		request.getSession().setAttribute("uploadStatus", status);
		FileUploadListener listener = new FileUploadListener(status);

		ServletContext sc = request.getSession().getServletContext();
		factory.setFileCleaningTracker(FileCleanerCleanup
				.getFileCleaningTracker(sc));
		factory.setSizeThreshold(1024 * 1024 * 10);

		File fileTemp = new File(FileUploadTemp);
		if (!fileTemp.exists() && !fileTemp.isDirectory()) {
			fileTemp.mkdir();
		}
		factory.setRepository(fileTemp);

		FileUtils fu = new FileUtils();
		String savePath = fu.getUploadPath();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8"); //$NON-NLS-1$
		upload.setProgressListener(listener);
		OutputStream os = null;
		List<?> items = null;
		String filename = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			return null;
		}
		Iterator<?> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			filename = item.getName();
			if (filename != "") {
				int pos = filename.lastIndexOf("\\") + 1; //$NON-NLS-1$
				File file = new File(savePath, filename.substring(pos));
				try {
					// item.write(file);
					os = new FileOutputStream(file);
					InputStream is = item.getInputStream();
					byte buf[] = new byte[1024];
					int length = 0;
					while ((length = is.read(buf)) > 0) {
						os.write(buf, 0, length);
					}
					os.flush();
					os.close();
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return filename;
	}
}
