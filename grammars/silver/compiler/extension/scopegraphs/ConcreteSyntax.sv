grammar silver:compiler:extension:scopegraphs;

--

terminal Scope_t 'scope' lexer classes {KEYWORD, RESERVED};

terminal Arrow_t '->';

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production scopeAssertion
top::ProductionStmt ::= 'scope' a::Name ';'
{
  top.unparse = "scope " ++ a.unparse ++ ";";

  forwards to
    productionStmtAppend(
      productionAttributeDcl(
        -- production attribute or local?
        'production', 'attribute', ^a,
        '::', nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Scope"))), ';'
      ),
      valueEq(
        qNameId(^a), '=', Silver_Expr { absScopeAssertion() }, ';' 
      )
    )
  ;
}

concrete production scopeAssertionDatum
top::ProductionStmt ::= 'scope' a::Name '->' e::Expr ';'
{
  top.unparse = "scope " ++ a.unparse ++ " -> " ++ e.unparse ++ ";";

  forwards to
    productionStmtAppend(
      productionAttributeDcl(
        -- production attribute or local?
        'production', 'attribute', ^a,
        '::', nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Scope"))), ';'
      ),
      valueEq(
        qNameId(^a), '=', Silver_Expr { absScopeAssertionDatum($Expr{^e}) }, ';' 
      )
    )
  ;
}


--

concrete production edgeAssertion
top::ProductionStmt ::= d::DefLHS '-[' lab::IdUpper_t ']->' e::Expr ';'
{
  top.unparse = d.unparse ++ " -[ " ++ lab.lexeme ++ " ]-> " ++ e.unparse ++ ";";

  -- todo: check that e1 is a reference to a Decorated Scope [with i]
  --       and is either a production attribute or inherited scope

  -- todo: check that e2 is a reference to a Decorated Scope [with i]

  -- if this ProductionStmt is in a production with signature top::SomeNt ::= ...,
  -- then prodRootName will be "top"
  local prodRootName::String = top.frame.signature.outputElement.elementName;

  forwards to
    -- dummy forward for the time being
    Silver_ProductionStmt {
      local attribute foo::Integer;
    }
  ;
}