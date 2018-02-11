import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;

public class Ledger {
	
	static boolean iFlag = true; 

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		//System.setErr(new PrintStream("C:/Users/Vaishali Vijaykumar/eclipse-workspace/Digital Currency/bin"));
		NonInteractive ni = new NonInteractive();
		ni.interact();

	} // end of main method
	
	public static boolean checkHex(String in) {
		 boolean ret;
	        try {
	            // try to parse the string to an integer, using 16 as radix
	            int t = Integer.parseInt(in, 16);
	            // parsing succeeded, string is valid hex number
	            ret = true;
	        } catch (NumberFormatException e) {
	            // parsing failed, string is not a valid hex number
	            ret = false;
	        }
	        return (ret);
	}
	

}
