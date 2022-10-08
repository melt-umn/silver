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

-- flow analysis
imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:flow:driver only ProductionGraph, FlowType, prod, inhDepsForSyn, findProductionGraph, expandGraph, onlyLhsInh;

-- the modifications we need to be aware of
imports silver:compiler:modification:autocopyattr only isAutocopy;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:modification:copper only parserAttributeDefLHS;

function isOccursSynthesized
Boolean ::= occs::OccursDclInfo  e::Decorated Env
{
  return case getAttrDcl(occs.attrOccurring, e) of
  | at :: _ -> at.isSynthesized
  | _ -> false
  end;
}

-- TODO: this should probably not be a thing I have to write here
function isAutocopy
Boolean ::= attr::String  e::Decorated Env
{
  return case getAttrDclAll(attr, e) of
  | at :: _ -> at.isAutocopy
  | _ -> false
  end;
}
-- TODO: why is this a thing I have to write here. Sheesh. FIX THIS.
-- The real fix is for our vertexes to remember whether they are syn/inh.
function isInherited
Boolean ::= a::String  e::Decorated Env
{
  return case getAttrDclAll(a, e) of
  | at :: _ -> at.isInherited
  | _ -> false
  end;
}

function isLhsInh
Boolean ::= v::FlowVertex
{
  return case v of
  | lhsInhVertex(_) -> true
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


