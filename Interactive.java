import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class Interactive {

	
	public static void main(String[] args) throws IOException {
		//end here
	}
	
	public void interact(String option) throws IOException, NoSuchAlgorithmException {
		
		System.out.println("[F]ile");
		System.out.println("[T]ransaction");
		System.out.println("[P]rint");
		System.out.println("[H]elp");
		System.out.println("[D]ump");
		System.out.println("[W]ipe");
		System.out.println("[I]nteractive");
		System.out.println("[V]erbose");
		System.out.println("[B]alance");
		System.out.println("[E]xit");
		
		
		
		while(!(option.equals("E") || option.equals("e"))) {
			System.out.println("Select a command:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			option = br.readLine();
		
			//System.out.println("The option is" +option);
			
			switch(option) {
			
			case "F": case "f":
				
				System.out.println("Supply FileName: ");
				String fileName = br.readLine();
				try {
					File file = new File(fileName);
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					//StringBuffer stringBuffer = new StringBuffer();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						Transaction f = new Transaction();
						f.transact(line);
					}
					fileReader.close();
					System.out.println("Finished Reading File");
					//System.out.println(stringBuffer.toString());
					//fileTransact(stringBuffer.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case "T": case "t":
				System.out.println("Supply Transaction: ");
				Transaction a = new Transaction();
				String s = br.readLine();
				a.transact(s);
				break;
			case "P": case "p":
				Transaction b = new Transaction();
				String printInput[]= b.newID.split("#");
				for(int i = 0; i < printInput.length; i++) {
					System.out.println(printInput[i]);
				}
				break;
			case "H": case "h":
				System.out.println("[F]ile: To take input in the form of a file");
				System.out.println("[T]ransaction: Supply Transaction:<TransactionDetails> To take ledger input in the form of transactions");
				System.out.println("[P]rint: Printing the successful transactions occured");
				System.out.println("[H]elp: Details of the commands");
				System.out.println("[D]ump: Supply filename:<outfilename>.  Dump ledger to the named file");
				System.out.println("[W]ipe: Wipe the entire ledger to start fresh");
				System.out.println("[I]nteractive: Toggle to turn off and on interactive mode");
				System.out.println("[V]erbose: Print additional diagnostic information");
				System.out.println("[B]alance: Supply username: <UserName> Check balance of a user");
				System.out.println("[E]xit: Exit the program");
				break;
			case "D": case "d":
				System.out.println("Supply FileName: ");
				String outFile = br.readLine();
				Transaction g = new Transaction();
				try{
					File file = new File(outFile);
					FileWriter fileWriter = new FileWriter(file);
					String dumpInput[]= g.newID.split("#");
					for(int i = 0; i < dumpInput.length; i++) {
						fileWriter.write(dumpInput[i] + System.getProperty( "line.separator" ));
						//fileWriter.write("\n"); 
					}
					fileWriter.flush();
					fileWriter.close();
				}
				catch (IOException e) {
					System.out.println("The error is" +e);
					System.err.println("The error is" +e);
				}
				break;
			case "W": case "w":
			{
				Transaction d = new Transaction();
				d.balanceMap.clear();
				d.transactionMap.clear();
				d.newID="";
			}	break;
			case "I": case "i":
				NonInteractive ni = new NonInteractive();
				ni.interact();
				Ledger l = new Ledger();
				if(l.iFlag == false)
					option = "E";
				break;
			case "V": case "v":
				Transaction v = new Transaction();
				if(v.verbose == true)
					v.verbose = false;
				else if(v.verbose == false)
					v.verbose = true;
				break;
			case "B": case "b":
				System.out.println("Supply Username: ");
				String name = br.readLine();
				Transaction c = new Transaction();
				if(c.balanceMap.containsKey(name)) {
					int bal = c.balanceMap.get(name);
					System.out.println(name +" has "+bal);
				}
				break;
			case "E": case "e":
				Ledger le = new Ledger();
				le.iFlag = false;
				System.out.println("Exit");
				break;
			default:
				System.out.println("ENTER A VALID OPTION");
				break;
			
			}
			
		
		} // end of while loop
		System.out.println("Exit loop");
	}
	
	
}
