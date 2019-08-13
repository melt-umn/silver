grammar silver:definition:origins:runtime;
import silver:definition:origins:runtime:impl;

function getOriginChain
[OriginInfo] ::= l::OriginInfo
{
	return case l.mOriginLink of
		| just(o) -> case o.nextOrigin of
			| just(n) -> n :: getOriginChain(n)
			| nothing() -> []
		end
		| nothing() -> []
	end;
}

function getOrigin
OriginInfo ::= arg::a
{
	return case javaGetOrigin(arg) of
		| just(x) -> x
		| nothing() -> bogusOriginInfo()
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