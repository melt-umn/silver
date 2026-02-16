grammar silver:compiler:extension:scopegraphs2;

--

fun scopeTypeExpr TypeExpr ::= datum::TypeExpr sg::String =
  Silver_TypeExpr{
    Decorated Scope<$TypeExpr{datum}>
    with $TypeExpr{nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, sg)))}
  };

fun mkLabelInhs AGDcl ::= allDatumTe::TypeExpr labs::[String] sg::String =
  foldr(
    \lab::String acc::AGDcl ->
      appendAGDcl(
        Silver_AGDcl{
          inherited attribute $Name{name(lab)}::[$TypeExpr{scopeTypeExpr(allDatumTe, sg)}]
          occurs on Scope<d>;
        },
        acc
      ),
    emptyAGDcl(),
    labs
  );
