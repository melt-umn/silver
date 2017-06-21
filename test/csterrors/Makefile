rhsTest.jar: 
	silver --clean rhs

disambTest.jar: 
	silver --clean disamb

startMissingTest.jar:
	silver --clean startMissing

prodModTest.jar:
	silver --clean prodMod

mdaTest.jar:
	silver --clean mda

missingLHSTest.jar:
	silver --clean missingLHS

terminalLHSTest.jar:
	silver --clean terminalLHS

testfile.jar:
	silver --clean testfile
	java -jar testfile.jar
	rm -f *.jar *.xml *.html
	
clean:
	rm -f *.jar *.xml *.html