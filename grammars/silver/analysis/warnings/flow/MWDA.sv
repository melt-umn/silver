grammar silver:analysis:warnings:flow;

-- data structures
imports silver:util;
imports silver:util:raw:treeset as set;

-- driver stuff
imports silver:util:cmdargs;
imports silver:driver only parseArgs;
imports silver:driver:util only isExportedBy;
imports silver:analysis:warnings;

-- silver
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;

-- flow analysis
imports silver:definition:flow:ast;
imports silver:definition:flow:driver only ProductionGraph, FlowType, flowVertexEq, prod, inhDepsForSyn, findProductionGraph, expandGraph, onlyLhsInh;

-- the modifications we need to be aware of
imports silver:modification:autocopyattr only isAutocopy;
imports silver:modification:collection;
imports silver:modification:defaultattr;
imports silver:modification:primitivepattern;
imports silver:modification:copper only parserAttributeDefLHS;

function isOccursSynthesized
Boolean ::= occs::DclInfo  e::Decorated Env
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


