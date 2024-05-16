grammar silver:compiler:extension:data;

concrete production dataDcl
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors ';'
{
  top.unparse = s"data ${id.unparse}${tl.unparse} = ${ctors.unparse};";
  ctors.ntName = id.name;
  ctors.ntTypeArgs = tl;
  forwards to appendAGDcl(
    nonterminalDcl(
      dataNTQualifier($1, nilNTQualifier()),
      'nonterminal', id, tl, nonterminalModifiersNone(), ';'),
    ctors.ctorDcls);
}

concrete production dataDclWith
top::AGDcl ::= 'data' id::Name tl::BracketedOptTypeExprs '=' ctors::DataConstructors  'with' attrs::QNames ';'
{
  top.unparse = s"data ${id.unparse}${tl.unparse} = ${ctors.unparse};";
  ctors.ntName = id.name;
  ctors.ntTypeArgs = tl;
  forwards to appendAGDcl(
    nonterminalWithDcl(
      dataNTQualifier($1, nilNTQualifier()),
      'nonterminal', id, tl, nonterminalModifiersNone(), 'with', attrs, ';'),
    ctors.ctorDcls);
}

synthesized attribute ctorDcls::AGDcl;
inherited attribute ntName::String;
inherited attribute ntTypeArgs::BracketedOptTypeExprs;

terminal DataConstructorOr_t '|' lexer classes {SPECOP};

tracked nonterminal DataConstructors with unparse, grammarName, config, ctorDcls, ntName, ntTypeArgs;
propagate grammarName, config, ntName, ntTypeArgs on DataConstructors;

concrete production consDataConstructor
top::DataConstructors ::= h::DataConstructor '|' t::DataConstructors
{
  top.unparse = s"${h.unparse} | ${t.unparse}";
  top.ctorDcls = appendAGDcl(h.ctorDcls, t.ctorDcls);
}

concrete production oneDataConstructor
top::DataConstructors ::= h::DataConstructor
{
  top.unparse = s"${h.unparse}";
  top.ctorDcls = h.ctorDcls;
}

concrete production nilDataConstructor
top::DataConstructors ::=
{
  top.unparse = "";
  top.ctorDcls = emptyAGDcl();
}

tracked nonterminal DataConstructor with unparse, grammarName, config, ctorDcls, ntName, ntTypeArgs;
propagate grammarName, config on DataConstructor;

concrete production dataConstructor
top::DataConstructor ::= id::Name rhs::ProductionRHS
{
  top.unparse = s"${id.unparse} ${rhs.unparse}";

  nondecorated local ntBaseType::TypeExpr =
    nominalTypeExpr(
      qNameTypeId(terminal(IdUpper_t, top.ntName, id.nameLoc)));
  nondecorated local ntType::TypeExpr =
    case top.ntTypeArgs of
    | botlNone() -> ntBaseType
    | botlSome(btl) -> appTypeExpr(ntBaseType, btl)
    end;
  top.ctorDcls = Silver_AGDcl {
    abstract production $Name{id}
    top::$TypeExpr{ntType} ::= $ProductionRHS{rhs}
    {}
  };
}
