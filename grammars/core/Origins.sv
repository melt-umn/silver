grammar core;
import core:originsimpl;

-- WARNING: Many of the nonterminals and productions in this file are runtime- and/or compiler-blessed.
--  Don't change their names, grammar locations, or parameters unless you know what your doing
--  (and have made the appropriate runtime and compiler changes!)

nonterminal OriginInfo;
nonterminal OriginInfoType;
nonterminal OriginNote;

synthesized attribute notepp :: String occurs on OriginNote;

synthesized attribute isNewlyConstructed :: Boolean occurs on OriginInfo;
synthesized attribute originNotes :: [OriginNote] occurs on OriginInfo;
synthesized attribute mOwnRedexNotes :: Maybe<[OriginNote]> occurs on OriginInfo;
synthesized attribute originType :: OriginInfoType occurs on OriginInfo;

synthesized attribute isBogus :: Boolean occurs on OriginInfoType;

abstract production setAtConstructionOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setAtNewOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setAtForwardingOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setAtAccessOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setFromParserOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setFromParserActionOIT
top::OriginInfoType ::=
{
	top.isBogus = true;
}

abstract production setFromFFIOIT
top::OriginInfoType ::=
{
	top.isBogus = true;
}

abstract production setFromReflectionOIT
top::OriginInfoType ::=
{
	top.isBogus = true;
}

abstract production setFromReificationOIT
top::OriginInfoType ::=
{
	top.isBogus = true;
}

abstract production setFromEntryOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production setInGlobalOIT
top::OriginInfoType ::=
{
	top.isBogus = false;
}

abstract production otherBogusOIT
top::OriginInfoType ::=
{
	top.isBogus = true;
}

abstract production otherOriginInfo
top::OriginInfo ::= typ::OriginInfoType source::String notes::[OriginNote]
{
	top.isNewlyConstructed = true;
	top.originNotes = notes;
	top.mOwnRedexNotes = nothing();
	top.originType = typ;
}

abstract production parsedOriginInfo
top::OriginInfo ::= typ::OriginInfoType source::Location notes::[OriginNote]
{
	top.isNewlyConstructed = true;
	top.originNotes = notes;
	top.mOwnRedexNotes = nothing();
	top.originType = typ;
}

abstract production originOriginInfo
top::OriginInfo ::= typ::OriginInfoType 
				 origin :: a
				 originNotes :: [OriginNote]
				 newlyConstructed :: Boolean
{
	top.isNewlyConstructed = newlyConstructed;
	top.originNotes = originNotes;
	top.mOwnRedexNotes = nothing();
	top.originType = typ;
}

abstract production originAndRedexOriginInfo
top::OriginInfo ::= typ::OriginInfoType 
				 origin :: a
				 originNotes :: [OriginNote]
				 redex :: a
				 redexNotes :: [OriginNote]
				 newlyConstructed :: Boolean
{
	top.isNewlyConstructed = newlyConstructed;
	top.originNotes = originNotes;
	top.mOwnRedexNotes = just(redexNotes);
	top.originType = typ;
}

-- function getOriginLink
-- Maybe<a> ::= oi::OriginInfo
-- {
-- 	return case oi of
-- 		| originOriginInfo(_, o, _, _) -> just(o)
-- 		| originAndRedexOriginInfo(_, o, _, _, _, _) -> just(o)
-- 		| _ -> nothing()
-- 	end;
-- }

-- function getRedexLink
-- Maybe<a> ::= oi::OriginInfo
-- {
-- 	return case oi of
-- 		| originAndRedexOriginInfo(_, _, _, r, _, _) -> just(r)
-- 		| _ -> nothing()
-- 	end;
-- }

aspect default production
top::OriginNote ::=
{
	top.notepp = "<" ++ hackUnparse(top) ++ ">";
}

abstract production originDbgNote
top::OriginNote ::= string::String
{
	
}

abstract production dbgNote
top::OriginNote ::= string::String
{
	
}

abstract production ruleLocNote
top::OriginNote ::= attributeName::String sourceGrammar::String prod::String nt::String sourceLocation::Location
{
	
}

function getOriginChain
[OriginInfo] ::= l::OriginInfo
{
	return case javaGetOriginLink(l) of
		| just(o) -> case javaGetOrigin(o) of
			| just(n) -> n :: getOriginChain(n)
			| nothing() -> []
		end
		| nothing() -> []
	end;
}

function getOrigin
Maybe<OriginInfo> ::= arg::a
{
	return javaGetOrigin(arg);
}

function getUrOrigin
Maybe<OriginInfo> ::= arg::a
{
	return case getOrigin(arg) of
		| just(o) -> case getOriginChain(o) of
			| [] -> nothing()
			| l -> just(last(l))
		end
		| nothing() -> nothing()
	end;
}

function getParsedOriginLocation
Maybe<Location> ::= arg::a
{
	return case getUrOrigin(arg) of
		| just(parsedOriginInfo(_, l, _)) -> just(l)
		| _ -> nothing()
	end;
}

function printObjectPairForOriginsViz
IO ::= start::a stop::b io::IO
{
	return print(
		"\n\n\n---SVDRAW2 START---" ++
		"\n" ++ sexprify(start) ++
        "\n" ++ sexprify(stop) ++
        "\n" ++ "---SVDRAW2 END---\n\n\n", io);
}