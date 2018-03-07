package org.prj.arachne.util.mail;

import java.io.File;

public class TestMailhtml {

	
	
	public static void main(String[] args) {
		
		File file=new File("classpath://templates/mailform.html");
		
		System.out.println(file.exists());
		
		
	}
}
