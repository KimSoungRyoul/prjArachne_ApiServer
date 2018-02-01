package org.prj.arachne;

public class TestApiContorller {
	
	
	public String asdf() {
		
		
		return this.getClass().getSimpleName();
	
	}
	
	public static void main(String[] args) {
		
		TestApiContorller tt=new TestApiContorller();
		
		System.out.println(tt.asdf());
		
		System.out.println(tt.getClass().getSimpleName());
		
		
	}

}
