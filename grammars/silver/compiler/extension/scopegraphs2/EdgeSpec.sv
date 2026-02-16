grammar silver:compiler:extension:scopegraphs2;

--

production edgeSpecNoType
top::AGDcl ::= label::String
{
  local moo::Boolean = length(
    lookupGraphDcl("MyGraph", top.sgEnv)
  ) == 0;

  forwards to if moo then emptyAGDcl() else emptyAGDcl();

  top.scopeLabelDefs := [scopeLabelDef(defaultEnvItem(labelDcl(
    label,
    nothing()
  )))];

  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
  top.defs := [];

  top.errors := 
    let 
      lookup::[ScopeLabelDclInfo] =
            lookupLabelDcl(label, top.sgEnv)
    in
      case lookup of
      | h::[] -> []
      | _ -> [errFromOrigin(top, "duplicate declaration of label '" ++ label ++ "'")]
      end
    end
  ;

}

production edgeSpecWithType
top::AGDcl ::= label::String te::TypeExpr
{
  forwards to emptyAGDcl();

  top.scopeLabelDefs := [scopeLabelDef(defaultEnvItem(labelDcl(
    label,
    just(^te)
  )))];

  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
  top.defs := [];

  top.errors := 
    let 
      lookup::[ScopeLabelDclInfo] =
            lookupLabelDcl(label, top.sgEnv)
    in
      case lookup of
      | h::[] -> []
      | _ -> [errFromOrigin(top, "duplicate declaration of label '" ++ label ++ "'")]
      end
    end
  ;
}