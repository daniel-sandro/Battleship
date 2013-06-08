package controller;

public final class Utils {

    private Utils() {}
	
	public static void output(String s) {
		System.out.println(s);
	}
	
	public static Utils initUtils () {
		Utils u = new Utils();
		return u;
	}
}
