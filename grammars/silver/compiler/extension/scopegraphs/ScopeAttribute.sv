grammar silver:compiler:extension:scopegraphs;

--

production scopeAttribute
top::AGDcl ::= attr::QName sg::Maybe<String> loc::Location
{
  local sgName::String = fromMaybe("_Scope_Default", sg);

  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sgName, top.sgEnv) in
      case res of
      | h::[] -> (h.labels, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '"
                                      ++ sgName ++ "'")])
      end
    end;

  forwards to
    appendAGDcl(
      scopeSyns(sgName, attr.name, labs.1),
      undecScopeAttrDcl(attr.name)
    );

  top.errors := labs.2;

  top.scopeGraphDefs := [];

  local sortedLabs::[String] = sort(labs.1);

  top.defs <-
    if null(labs.2) 
    then
      [
        attrDef(defaultEnvItem(scopeInhDcl(
          attr.name,
          inhScopeType(top.grammarName, sortedLabs),
          sortedLabs,
          sourceGrammar=top.grammarName, sourceLocation=loc
        )))
      ]
    else [];
}
