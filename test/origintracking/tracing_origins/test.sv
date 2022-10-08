imports common;

mainTestSuite oitests;

global three :: Nat = s(s(s(z())));

function constructFour
Nat ::=
{
	return three.plusOne;
}

equalityTest(
	case getOriginInfo(constructFour()) of
	| just(originAndRedexOriginInfo(_, _,
		[traceNote("nat.sv:18:16")], _, 
		[traceNote("test.sv:14:27"), traceNote("test.sv:10:15")], _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);
