grammar silver:compiler:extension:scopegraphs2;

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

  forwards to --appendAGDcl(
    --scopeInh(sg, attr),
    scopeSyns(sg, attr.name, labs.1);
  --);

  top.errors := labs.2;

  top.scopeGraphDefs := [];

  top.defs <-
    if null(labs.2) 
    then
      [
        attrDef(defaultEnvItem(scopeInhDcl(
          attr.name,
          inhScopeType(labs.1),
          labs.1,
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

fun inhScopeType Type ::= labs::[String] =
  decoratedType(
    nonterminalType(
      "silver:compiler:extension:scopegraphs2:Scope",
      [], false, false
    ),
    inhSetType(labs)
  )
;