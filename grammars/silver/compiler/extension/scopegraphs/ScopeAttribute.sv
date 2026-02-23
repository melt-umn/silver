grammar silver:compiler:extension:scopegraphs;

--

production scopeAttribute
top::AGDcl ::= sg::String attr::QName loc::Location
{
  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sg, top.sgEnv) in
      case res of
      | h::[] -> (h.labelSet, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '" ++ sg ++ "'")])
      end
    end;

  forwards to
    appendAGDcl(
      scopeSyns(sg, attr.name, labs.1),
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

--

fun scopeInh AGDcl ::= sg::String attr::String =
  Silver_AGDcl {
    inherited attribute $Name{name(attr)}::$TypeExpr{scopeTypeExpr(sg)};
  };

fun scopeSyns AGDcl ::= sg::String attr::String labs::[String] =
  let
    oneSyn::(AGDcl ::= String) = \lab::String ->
      Silver_AGDcl {
        synthesized attribute 
          $Name{nScopeAttr(attr, lab)}::[$TypeExpr{scopeTypeExpr(sg)}]
        with ++;
      }
  in
    foldrLastElem(
      \lab::String acc::AGDcl -> appendAGDcl(oneSyn(lab), acc),
      \lab::String -> oneSyn(lab),
      labs
    )
  end;

fun inhScopeType Type ::= grammarName::String labs::[String] =
  decoratedType(
    nonterminalType(
      "silver:compiler:extension:scopegraphs:Scope",
      [], false, false
    ),
    inhSetType(map(\lab::String ->grammarName ++ ":" ++ lab, labs))
  )
;

fun undecScopeAttrDcl AGDcl ::= attr::String =
  Silver_AGDcl {
    synthesized attribute
      $Name{nScopeAttr(attr, "undec")}::[Scope]
    with ++;
  };