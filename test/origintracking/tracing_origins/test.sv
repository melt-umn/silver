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
		[traceNote("tracing_origins:test.sv:10:15"), traceNote("tracing_origins:test.sv:14:27")], _,
		originNotes=[traceNote("common:nat.sv:18:16")])) -> "OK"
	| ou -> hackUnparse(ou)
	end,
	"OK", String, oitests);
