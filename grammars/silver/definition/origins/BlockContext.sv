import silver:definition:core;
import silver:definition:env;
import silver:definition:flow:driver;

{--
 - Does code generated in this block need to reference originsCtx, or can it
 - get information from the frame's left-hand-side and rules? Effectively, is
 - this code in a function or a production?
 -}
synthesized attribute originsContextSource :: ContextOriginInfoSource occurs on BlockContext;

nonterminal ContextOriginInfoSource;

abstract production useContextLhsAndRules
top::ContextOriginInfoSource ::=
{
	
}

abstract production useRuntimePassedInfo
top::ContextOriginInfoSource ::=
{
	
}

abstract production useBogusInfo
top::ContextOriginInfoSource ::= name::String
{
	
}

aspect production functionContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
	top.originsContextSource = useRuntimePassedInfo();
}

aspect production productionContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
	top.originsContextSource = useContextLhsAndRules();
}

aspect production globalExprContext
top::BlockContext ::= g::ProductionGraph
{
	top.originsContextSource = useBogusInfo("GLOBAL_CONTEXT");
}