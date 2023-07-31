grammar silver:compiler:analysis:warnings:flow;

-- data structures
imports silver:util:treeset as set;

-- driver stuff
imports silver:util:cmdargs;
imports silver:compiler:driver only parseArgs;
imports silver:compiler:driver:util only isExportedBy;
imports silver:compiler:analysis:warnings;

-- silver
imports silver:compiler:definition:core;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:env;

-- type checking
imports silver:compiler:analysis:typechecking:core;

-- flow analysis
imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:flow:env;
imports silver:compiler:definition:flow:driver only ProductionGraph, FlowType, prod, inhDepsForSyn, findProductionGraph, expandGraph, onlyLhsInh;

-- uniqueness analysis
imports silver:compiler:analysis:uniqueness;

-- the modifications we need to be aware of
imports silver:compiler:modification:collection;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:modification:copper only parserAttributeDefLHS;

function isLhsInh
Boolean ::= v::FlowVertex
{
  return case v of
  | lhsInhVertex(_) -> true
  | _ -> false
  end;
}

function isForwardProdAttr
Boolean ::= a::String  e::Decorated Env
{
  return case getValueDclAll(a, e) of
  | d :: _ -> d.hasForward
  | _ -> false
  end;
}



-- TODO: better way of generating warnings. We ad-hoc check for errors before
-- raising these warnings, but this is inherently fragile and results in crash
-- bugs when running the MWDA on erroneous grammars.
-- (easily fixed by running regular build first, but still.)
-- (Possible solution approach: raise these with a different attribute than
--  `errors`, but we'd probably want "monoid attributes" to make that ergonomic.)

-- TODO: are we ever checking the flow types for default equations?
-- These shouldn't need checking as part of inference, but default equations can
-- exceed *explicit* flow types, and I don't think anything is checking that yet.


