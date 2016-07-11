package cn.dreampie.utils;

/**
 * @author Xiang
 *
 */
public class MyLogger {
	public static String yourname = "zhoux";
	
	public static void log(String meg){
		System.out.println("========"+yourname+"========="+meg+"===========");
	}
	
	public static void logImpor(String meg){
		System.err.println("========"+yourname+"========="+meg+"===========");
	}
}
