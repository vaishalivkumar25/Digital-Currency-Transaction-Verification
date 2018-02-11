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