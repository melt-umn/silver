all: disambTest startMissingTest prodModTest rhsTest mdaTest missingLHSTest terminalLHSTest

rhsTest: 
	silver --clean --warn-all -o rhs.jar rhs

disambTest: 
	silver --clean --warn-all -o disamb.jar disamb

startMissingTest:
	silver --clean --warn-all -o startMissing.jar startMissing

prodModTest:
	silver --clean --warn-all -o prodMod.jar prodMod

mdaTest:
	silver --clean -o mda.jar mda

missingLHSTest:
	silver --clean -o missingLHS.jar missingLHS

terminalLHSTest:
	silver --clean -o terminalLHS.jar terminalLHS

testfile:
	silver --clean parsers
	
clean:
	rm -f *.jar *.xml *.html