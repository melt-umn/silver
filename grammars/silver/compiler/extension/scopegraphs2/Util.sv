grammar silver:compiler:extension:scopegraphs2;

--

fun mkLabelInhs AGDcl ::= sg::String labs::[String] =
  foldr(
    \lab::String acc::AGDcl ->
      appendAGDcl(
        Silver_AGDcl{
          inherited attribute
            $Name{name(lab)}::[$TypeExpr{scopeTypeExpr(sg)}]
          occurs on Scope;
        },
        acc
      ),
    emptyAGDcl(),
    labs
  );

fun scopeTypeExpr TypeExpr ::= sg::String =
  Silver_TypeExpr{
    Decorated Scope with
      $TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}
  };

fun labelProdName
Name ::= lab::String =
  name("label_" ++ lab);

fun qnScopeAttr QName ::= s::String l::String = 
  qName(s ++ "_" ++ l);

fun nScopeAttr Name ::= s::String l::String =
  name(s ++ "_" ++ l);