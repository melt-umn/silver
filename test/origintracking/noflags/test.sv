imports common;

mainTestSuite oitests;

equalityTest(s(s(s(z()))).plusOne.val, 4, Integer, oitests);

global three :: Nat = s(s(s(z())));
global four :: Nat = three.plusOne;
global five :: Nat = four.plusOne;
global two :: Nat = three.minusOne;

equalityTest(identityHashCode(three), identityHashCode(three), Integer, oitests);

equalityTest(
	case getOriginInfo(four) of
    | just(originOriginInfo(_, link, _, _)) -> identityHashCode(link)
    | _ -> -1
    end,
	identityHashCode(three), Integer, oitests);

equalityTest(
	case getOriginInfo(five) of
    | just(originOriginInfo(_, link, _, _)) -> identityHashCode(link)
    | _ -> -1
    end,
	identityHashCode(four), Integer, oitests);

equalityTest(
	case getOriginInfoChain(five) of
	| _::originOriginInfo(_, link, _, _)::_ -> identityHashCode(link)
	| _ -> -1
	end,
	identityHashCode(three), Integer, oitests);

equalityTest(
	case getOriginInfo(three) of
	| just(otherOriginInfo(setInGlobalOIT(), _, _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);

equalityTest(
	case getOriginInfo(two) of
	| just(originAndRedexOriginInfo(_, _, _, r, _, _)) -> identityHashCode(r)
	| _ -> -1
	end,
	identityHashCode(three), Integer, oitests);

equalityTest(
	case getOriginInfo(two) of
	| just(originAndRedexOriginInfo(setAtNewOIT(), _, _, _, _, _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);

equalityTest(
	case getOriginInfo(four) of
	| just(originOriginInfo(setAtConstructionOIT(), _, _, _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);

global reflectedThree :: AST = reflect(three);

equalityTest(
	case getOriginInfo(reflectedThree) of
	| just(originOriginInfo(setFromReflectionOIT(), _, _, _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);

equalityTest(
	case getOriginInfo(reflectedThree) of
	| just(originOriginInfo(_, o, _, _)) -> identityHashCode(o)
	| _ -> -1
	end,
	identityHashCode(three), Integer, oitests);

global reifiedThree :: Nat = reify(reflectedThree).fromRight;

equalityTest(
	case getOriginInfo(reifiedThree) of
	| just(originOriginInfo(setFromReificationOIT(), _, _, _)) -> "OK"
	| _ -> "NO"
	end,
	"OK", String, oitests);

equalityTest(
	case getOriginInfo(reifiedThree) of
	| just(originOriginInfo(_, o, _, _)) -> identityHashCode(o)
	| _ -> -1
	end,
	identityHashCode(reflectedThree), Integer, oitests);

-- global fourWithNote :: Nat = callWithListOfNotes([dbgNote("foo")], (\->three.plusOne));

-- equalityTest(
-- 	case getOriginInfo(fourWithNote) of
-- 	| just(originOriginInfo(_, _, [dbgNote("foo")], _)) -> "OK"
-- 	| _ -> "NO"
-- 	end,
-- 	"OK", String, oitests);

-- notes not supported on stuff in global context