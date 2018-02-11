JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Ledger.java \
		Interactive.java \
		NonInteractive.java \
		Transaction.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
