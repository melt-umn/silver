grammar silver:compiler:extension:scopegraphs2;


--

terminal RegexSlash_t '`';
terminal RegexStar_t '*' precedence = 30, association = left;
terminal RegexQuestion_t '?' precedence = 30, association = left;

--

concrete production sgRegex_c
top::Expr ::= RegexSlash_t r::SGRegex RegexSlash_t  '::' sg::IdUpper_t
{ forwards to r.regexApp; }

--

nonterminal SGRegex;

synthesized attribute regexApp::Expr occurs on SGRegex;

concrete production regexLabel_c
top::SGRegex ::= lab::IdLower_t
{
  top.regexApp = applicationExpr(
    baseExpr(qName("regexLabel")),
    '(',
    oneAppExprs(
      presentAppExpr(
        applicationExpr(
          baseExpr(qName("label_" ++ lab.lexeme)), '(', emptyAppExprs(), ')'
        )
      )
    ),
    ')'
  );
}

concrete production regexStar_c
top::SGRegex ::= r::SGRegex RegexStar_t
{
  top.regexApp = Silver_Expr{
    regexStar($Expr{r.regexApp})
  };
}

concrete production regexCat_c
top::SGRegex ::= l::SGRegex Dot_t r::SGRegex
{
  top.regexApp = Silver_Expr{
    regexCat($Expr{l.regexApp}, $Expr{r.regexApp})
  };
}

concrete production regexMaybe_c
top::SGRegex ::= r::SGRegex RegexQuestion_t
{
  top.regexApp = Silver_Expr{
    regexMaybe($Expr{r.regexApp})
  };
}