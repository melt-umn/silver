import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:flow:driver;

data ContextOriginInfoSource
  = useContextLhsAndRules
  | useRuntimePassedInfo
  | useBogusInfo  name::String;

{--
 - Does code generated in this block need to reference originsCtx, or can it
 - get information from the frame's left-hand-side and rules? Effectively, is
 - this code in a function or a production?
 -}
synthesized attribute originsContextSource :: ContextOriginInfoSource occurs on BlockContext;

aspect production functionContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
	top.originsContextSource = useRuntimePassedInfo();
}

aspect production inLambdaContext
top::BlockContext ::= containingContext::BlockContext
{
  top.originsContextSource = useRuntimePassedInfo();
  -- Code in lambda blocks needs to respect the OI it is passed, or notes &c won't flow into
  --  lambdas in the same way that they flow into functions.
}

aspect production productionContext
top::BlockContext ::= sig::NamedSignature  g::ProductionGraph
{
	top.originsContextSource = useContextLhsAndRules();
}

aspect production globalExprContext
top::BlockContext ::= _ _ _ _
{
	top.originsContextSource = useBogusInfo("GLOBAL_CONTEXT"); --This is a java implementation detail but...
}