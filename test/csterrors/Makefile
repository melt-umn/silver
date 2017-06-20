all: disambTest startMissingTest prodModTest rhsTest mdaTest 

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

testfile:
	silver --clean parsers
	
clean:
	rm -f *.jar *.xml *.html