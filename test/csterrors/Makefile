all: recTyTest disambTest startMissingTest prodModTest rhsTest mdaTest recNtTest noCncTest

recTyTest: 
	silver --clean --warn-all -o recTy.jar recTy

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

recNtTest:
	silver --clean -o recNt.jar recNt

noCncTest:
	silver --clean -o noCnc.jar noConcrete

testfile:
	silver --clean parsers
	
clean:
	rm -f *.jar *.xml *.html