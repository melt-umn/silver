grammar silver:compiler:modification:copper;

-- hack for all uses of this stuff in this grammar. note s on imports
imports silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
imports silver:compiler:driver:util only RootSpec;
imports silver:compiler:definition:origins;

attribute permitActions, permitPluck occurs on BlockContext;

{--
 - Actions include parser attribute manipulation. print statement.
 -}
synthesized attribute permitActions :: Boolean;
synthesized attribute permitPluck :: Boolean;

aspect default production
top::BlockContext ::=
{
  top.permitActions = false;
  top.permitPluck = false;
}

{-- Terminal shift, parser attribute initialization -}
abstract production actionContext
top::BlockContext ::= g::ProductionGraph
{
  top.fullName = "__action__"; -- Used as part of naming locals... maybe we should fix that? TODO
  top.signature = bogusNamedSignature();
  top.flowGraph = g;
  top.lhsNtName = "::nolhs"; -- unfortunately this is sometimes accessed, and a dummy value works okay
  
  top.lazyApplication = false;
  top.permitActions = true;
  --top.permitProductionAttributes = false; -- denied by default
  top.permitLocalAttributes = true;
  -- TODO: signature? We DO have such info, but unclear what answer should be given...
  top.originsContextSource = useBogusInfo("PARSERACTION_CONTEXT");
}

{-- Disambiguation groups -}
abstract production disambiguationContext
top::BlockContext ::= g::ProductionGraph
{
  top.permitPluck = true;
  top.originsContextSource = useBogusInfo("PARSERACTION_CONTEXT");
  forwards to actionContext(g, sourceGrammar=top.sourceGrammar);
}

{-- Production reduce actions -}
abstract production reduceActionContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
  top.fullName = sig.fullName;
  top.signature = sig; -- TODO: figure out if this is ever used for actions?
  top.className = makeProdName(top.fullName); -- child references in production actions use it
  top.originsContextSource = useBogusInfo("PARSERACTION_CONTEXT");

  forwards to actionContext(g, sourceGrammar=top.sourceGrammar);
}

