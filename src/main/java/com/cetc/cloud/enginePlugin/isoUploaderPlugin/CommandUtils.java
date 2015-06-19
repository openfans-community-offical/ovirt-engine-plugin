package com.cetc.cloud.enginePlugin.isoUploaderPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CommandUtils implements Runnable {

	private String commond;
	private String file;

	public CommandUtils(String commond, String file) {
		this.commond = commond;
		this.file = file;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		executeCommand(commond);
	}

	private String executeCommand(String command) {

		System.out.println("Executing command: " + command);

		String[] commands = new String[] { "/bin/sh", "-c", command };

		StringBuffer outputBuf = new StringBuffer();

		try {
			ProcessBuilder builder = new ProcessBuilder(commands);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					p.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";

			line = reader.readLine();
			System.out.println(line);
			FileUtils fu = new FileUtils();
			fu.deleteFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Output: " + outputBuf.toString().trim());
		return outputBuf.toString().trim();
	}
}
