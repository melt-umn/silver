grammar flow;

-- An equation in an extension production for a host attribute is suspect: its dependencies enter
-- the production's graph only within the attribute's flow type (see findAdmissibleEdges).  A direct
-- dependency on an inherited attribute of the LHS enters when that flow type has the attribute, and
-- then what is projected from the graph needs the attribute.

inherited attribute saI::Integer;
inherited attribute saK::String;
synthesized attribute saS::String;
synthesized attribute saT::String;
nonterminal SAE with saI, saK, saS, saT;
flowtype SAE = decorate {}, forward {}, saS {saI}, saT {saK};

production saLit
top::SAE ::=
{
  top.saS = "";
  top.saT = top.saK;
}

-- The match binds c, the child of saExt (see ext/SuspectAdmission.sv).  saExt supplies c.saK from
-- its own saS, which needs saI, so saV needs saMI, which this production supplies to e as saI.
inherited attribute saMI::Integer;
synthesized attribute saV::String;
nonterminal SAM with saMI, saV;
production saMatch
top::SAM ::= e::SAE
{
  e.saI = top.saMI;
  e.saK = "";
  top.saV = case e of saExt(c) -> c.saT | _ -> "" end;
}

synthesized attribute saW::String;
nonterminal SAR with saW;
warnCode "Access of synthesized attribute saV on x requires missing inherited attribute(s) flow:saMI to be supplied to child x of production flow:saUse" {
production saUse
top::SAR ::= x::SAM
{
  top.saW = x.saV;
}
}
