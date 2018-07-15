package com.shosu.config;

public class Test {
	
	
	public static void main(String [] a) {
		Help x = new Help(1);
		change(x);
		System.out.println(x.getA());
		System.out.println(x.getA());
	}
	public  static void change(Help num) {
		 num.setA(4);
	}
	public static class Help{
		private int a;
		
		public Help() {
		}
		
		public Help(int a) {
			this.a = a;
		}

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}
		
	}
}
