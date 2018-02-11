import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
		
	String transactionId;
	//static int m;
	//static int n;
	int value;
	String person;
	static boolean genesis = false;
	static boolean verbose = false;
	//static int index = 0;
	static String newID = "";
	
	Transaction[] transArray = new Transaction[10];
	
	static HashMap<String, Integer> balanceMap = new HashMap<String, Integer>();
	static HashMap<String, Transaction[]> transactionMap = new HashMap<String, Transaction[]>();
	
	public void transact(String s) throws IOException, NoSuchAlgorithmException {
		String vString="";
		for(int i=0; i<transArray.length; i++)
		{
			transArray[i]=new Transaction();
		}
		
		String input[]= s.split(";");
		String checkInput = s.substring(10, s.length());
		String checkInputHex = checkInput + "\n";
		transactionId = input[0];
		if(verbose == true) {
			vString =transactionId +": ";
		}
		String q= encryptPassword(checkInputHex).substring(0, 8);
		if(!(q.equals(transactionId))) {
			transactionId = q;
			s=q+"; "+checkInput;
			if(verbose == true) {
				System.out.println(vString+"BAD");
			}
			System.err.println("Transaction ID wrong. Change has been made in the transactionID\n");
		}
		else {
			if(verbose == true) {
				System.out.println(vString+"GOOD");
			}
		}
		
		//boolean isHex = checkHex(transactionId); // check substring

			if(input[1].equals(" 0") || input[1].equals("0") ) {
				if(genesis == false)
				{	
					genesis = true;
					if(verbose == true) {
						System.out.println("GENESIS TRANSACTION");
					}
					String[] t= input[4].split(",");
					t[0] = t[0].substring(2);
					t[1]= t[1].substring(1, t[1].length()-1);
					transArray[0].person = t[0];
					transArray[0].value = Integer.parseInt(t[1]);
					
					transactionMap.put(transactionId, transArray );
					balanceMap.put(transArray[0].person, transArray[0].value);
					//System.out.println("Person: " +transArray[0].person +"Value: "+balanceMap.get(transArray[0].person));
					newID = newID + s;
				}
				else {
					
					System.err.println("Invalid input.. Genesis input not valid now");
				}
			}
			else if(genesis == true){
				
				if(verbose == true) {
					System.out.println("OTHER TRANSACTIONS");
				}
				String[] r= input[2].split(",");
				String prevTransId = r[0].substring(2);
				r[1]= r[1].substring(1, r[1].length()-1);
				int senderIndex = Integer.parseInt(r[1]);  // senderIndex is the index of sender
				
				input[1] = input[1].substring(1);
				input[3] = input[3].substring(1);
				int m = Integer.parseInt(input[1]);			
				int n = Integer.parseInt(input[3]);			// number of people between whom it is split
				
				if(transactionMap.containsKey(prevTransId)) {
					
					int prevLength = transactionMap.get(prevTransId).length; 
					
					if(senderIndex <= prevLength) {
						
						input[4]=  input[4].substring(1);
						String[] tran= input[4].split("\\)");
						for(int i =0; i<n; i++) {
							String[] t = tran[i].split(",");
							t[0] = t[0].substring(1);
							transArray[i].person = t[0];
							t[1]=  t[1].substring(1);
							transArray[i].value = Integer.parseInt(t[1]);
						}
						transactionMap.put(transactionId, transArray );
						
						//validate
						boolean check = validate(transactionId, prevTransId, senderIndex, n);
						
						
						if(verbose == true) {
							System.out.println("VALIDATED \nAMOUNT MATCH IS "+check);
						}
						if(check) { 
							Transaction[] prevTransArray = new Transaction[n];
							prevTransArray = transactionMap.get(prevTransId);
							balanceMap.put(prevTransArray[senderIndex].person, 0);
							System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+prevTransArray[senderIndex].person);
							
							newID = newID + "#" + s;
							//System.out.println("String is "+newID);
							//check balance
							//balanceMap.put(key, value);
							for(int i =0; i<n; i++) {
								
								balanceMap.put(transArray[i].person, balanceMap.getOrDefault(transArray[i].person, 0)+transArray[i].value);
								//System.out.println("Person: " +transArray[i].person +"Value: "+balanceMap.get(transArray[i].person));
							}
							
							//check balance
							
						} 
						else {	
							System.err.println("Amount in the account does not match");
							//System.out.println("Amount in the account does not match");
						}
					}
					else {
						System.err.println("Transaction Unavailable");
					}
					
				}
				else
					System.err.println("Transaction not available");
			}
			else
				System.err.println("Error !! not enough money");
		
	}
		
		
	
	private boolean validate(String transactionId2, String prevTransId, int senderIndex, int n) {
		 
		int sum = 0;
		Transaction[] currTransArray = new Transaction[n];
		Transaction[] prevTransArray = new Transaction[n];
		currTransArray = transactionMap.get(transactionId2);
		for(int i = 0; i<n; i++) {
			sum += currTransArray[i].value;
		}
		//System.out.println("Sum " +sum);
		
		prevTransArray = transactionMap.get(prevTransId);
		//System.out.println("prevTransArray is " +prevTransArray[senderIndex].person);
		int accBalance = prevTransArray[senderIndex].value;
		//System.out.println("Account Balance " +accBalance);
		
		if(sum == accBalance) 
			return true;
		else
			return false;
	}

	private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	    crypt.reset();
	    crypt.update(password.getBytes("UTF-8"));

	    return new BigInteger(1, crypt.digest()).toString(16);
	}
	
}
