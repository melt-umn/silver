grammar silver:compiler:analysis:uniqueness;

inherited attribute exprDecSite::Maybe<ExprDecSite>;

nonterminal ExprDecSite with compareTo, isEqual, compareKey, compare;
propagate compareTo, isEqual, compareKey, compare on ExprDecSite;

production localDecSite
top::ExprDecSite ::= fName::String
{}

production forwardDecSite
top::ExprDecSite ::=
{}

production anonDecSite
top::ExprDecSite ::= x::String
{}

production subtermDecSite
top::ExprDecSite ::= parent::ExprDecSite prodName::String sigName::String
{}

function getChildDecSite
Maybe<ExprDecSite> ::= prodName::String sigName::String flowEnv::FlowEnv
{
  return
    case getUniqueRefs(prodName ++ ":" ++ sigName, flowEnv) of
    | r :: _ -> r.decSite  -- Duplicates should already be an error, anyway
    | [] -> nothing()
    end;
}
function getLocalDecSite
Maybe<ExprDecSite> ::= fName::String flowEnv::FlowEnv
{
  return
    case getUniqueRefs(fName, flowEnv) of
    | r :: _ -> r.decSite  -- Duplicates should already be an error, anyway
    | [] -> nothing()
    end;
}
