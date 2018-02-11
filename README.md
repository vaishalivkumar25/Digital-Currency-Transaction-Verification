# Digital-Currency-Transaction-Verification
Program to interact with a ledger and verify the digital currency transactions. 

Follow the given instructions to run the program:

1. JAVA jdk DETAILS
	JAVA_VERSION="1.8.0_101"
	OS_NAME="Windows"
	OS_VERSION="5.2"
	OS_ARCH="amd64"
	SOURCE=" .:e983a19c6439 corba:2bb2aec4b3e5 deploy:2390a2618e98 hotspot:77df35b662ed hotspot/make/closed:40ee8a558775 hotspot/src/closed:710cffeb3c01 hotspot/test/closed:d6cfbcb20a1e install:68eb511e9151 jaxp:8ee36eca2124 jaxws:287f9e9d45cc jdk:827b2350d7f8 jdk/make/closed:53a5d48a69b0 jdk/src/closed:06c649fef4a8 jdk/test/closed:556c76f337b9 langtools:8dc8f71216bf nashorn:44e4e6cbe15b pubs:388b7b93b2c0 sponsors:1b72bbdb30d6"
	BUILD_TYPE="commercial"
2. Enter the command "make" on the terminal window.
3. The main class is Ledger.java. To run enter the command
		java Ledger
4. The program starts in Non interactive mode. Use one of the following commands to start
	[F/f] File input
	[T/t] Transaction input
	[B/b] Check Balance
	[P/p] Print the successful ledgers
	[D/d] Dump into a file
	[W/w] Wipe all ledgers and start from the beginning
	[E/e] Exit the program
	[I/i] Interactive toggle mode
	[V/v] Verbose toggle mode
	[H/h] Help
	
	The menu will contain:

1.	[F]ile:  Supply filename:<infilename>.  Read in a file of transactions. Any invalid transaction shall be identified with an error message to stderr, but not stored. Print an error message to stderr if the input file named cannot be opened. The message shall be “Error: file <infilename> cannot be opened for reading” on a single line, where <infilename> is the name provided as additional command input.  

2.	[T]ransaction: Supply Transaction:<see format below>   Read in a single transaction in the format shown below.  It shall be checked for validity against the ledger, and added if it is valid. If it is not valid, then do not add it to the ledger and print a message to stderr with the transaction number followed by a colon, a space, and the reason it is invalid on a single line.

3.	[E]xit:  Quit the program

4.	[P]rint:  Print current ledger (all transactions in the order they were added) to stdout in the transaction format given below, one transaction per line.

5.	[H]elp:  Command Summary

6.	[D]ump:  Supply filename:<outfilename>.  Dump ledger to the named file. Print an error message to stderr if the output file named cannot be opened. The message shall be “Error: file <outfilename> cannot be opened for writing” on a single line, where <outfilename> is the name provided as additional command input. 

7.	[W]ipe:  Wipe the entire ledger to start fresh.

8.	[I]nteractive: Toggle interactive mode. Start in non-interactive mode, where no command prompts are printed. Print command prompts and prompts for additional input in interactive mode, starting immediately (i.e., print a command prompt following the I command).

9.	[V]erbose: Toggle verbose mode. Start in non-verbose mode. In verbose mode, print additional diagnostic information as you wish. At all times, output each transaction number as it is read in, followed by a colon, a space, and the result (“good” or “bad”). 

10.	[B]alance:  Supply username:  (e.g. Alice).  This command prints the current balance of a user.   


Format of Transactions:
<TransID>; M; (<TransID>, <vout>)^M; N; (<AcctID>, <amount>)^N 
Items in angle brackets are parameters, M and N are whole numbers, and caret M (or N) indicates M (or N) repetitions of the parenthesized pairs. 

Example Transaction:
4787df35; 1;(f2cea539, 0);3; (Bob, 150)(Alice, 845)(Gopesh, 5)

<TransID> refers to a 32-bit transaction identifier given in hexadecimal format. For now, it will just be given as input (later it will be calculated). 

M is the number of UTXOs that are inputs to this transaction. The genesis transaction is the only transaction allowed to have zero input UTXOs, and must be the first transaction in any ledger. 

<vout> refers to the value out (vout) index of the UTXO, where the first index is zero.  For example, vout 0 in transaction f2cea539 refers to (Alice, 1000). 

Following the field with the number M of input UTXOs is the field that lists those UTXOs as a sequence of M pairs, each pair in parentheses, elements separated by a comma, consisting of a transaction ID and a vout from that transaction.

N is the number of transaction outputs. N is always positive. 

<AcctID> refers to the alphanumeric account identifier (for now, we will just provide these). 

<amount> is a natural number of satoshis that is credited to the account named in the pair by this transaction. 

File format version of example ledger:
f2cea539; 0; ; 1; (Alice, 1000)
4787df35; 1; (f2cea539, 0); 3; (Bob, 150)(Alice, 845)(Gopesh, 5)
40671f57; 1; (4787df35, 0); 3; (Gopesh, 100)(Bob, 45)(Bob, 5)
84dfb9b3; 1; (40671f57, 0); 2; (Bob, 100)(Gopesh, 5)
F6847ad8; 1; (40671f57, 1); 3; (Alice, 5)(Bob, 35)(Bob, 5)

This is both the format for input transactions and for the dumped ledger. 

