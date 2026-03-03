grammar silver:compiler:extension:scopegraphs;

--

production graphSpec
top::AGDcl ::= sgId::Maybe<String> qns::LabelNames labsId::String
{
  -- alias of the set of inherited attributes corresponding to graph label set
  nondecorated local alias::AGDcl = Silver_AGDcl {
    type $Name{name(labsId)} =
      $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), qns.fsInhs, '}')};
  };

  forwards to appendAGDcl(
    alias,
    labelsAGDcls(labsId, qns.names)
  );

  local sgName::String = fromMaybe("_Scope_Default", sgId);

  -- 'graph definition' consisting of an identifier and constituent labels
  top.scopeGraphDefs := [scopeGraphDef(defaultEnvItem(graphDcl(sgName, qns.names)))];
}

--

-- Generate inherited attribute and production declarations for each label
fun labelsAGDcls AGDcl ::= sg::String labs::[String] =
  let labelAGDcls::(AGDcl ::= String) = 
    \lab::String -> appendAGDcl(labelInh(sg, lab), labelProd(sg, lab))
  in
    if null(labs) then error("Scope graph label list cannot be empty!") else
      foldrLastElem(
        \lab::String acc::AGDcl -> appendAGDcl(labelAGDcls(lab), acc),
        \lab::String -> labelAGDcls(lab),
        labs
      )
  end;

-- Generate a Label production declaration for a given label
fun labelProd AGDcl ::= sg::String lab::String =
  Silver_AGDcl {
    production $Name{labelProdName(lab)}
    top::Label<$TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}> ::=
    {
      top.name = $Expr{stringConst(terminal(String_t, "\"" ++ lab ++ "\""))};
      top.demand = \s::$TypeExpr{scopeTypeExpr(sg)} -> s.$QName{qName(lab)};
    }
  };

-- Generate an inherited attribute declaration for a given label
fun labelInh AGDcl ::= sg::String lab::String =
  Silver_AGDcl {
    inherited attribute $Name{name(lab)}::[$TypeExpr{scopeTypeExpr(sg)}] with ++
    occurs on Scope;
  };
