grammar silver:compiler:extension:scopegraphs;


--

terminal SGRegexBacktick_t '`';
terminal SGRegexStar_t '*';
terminal SGRegexQuestion_t '?';

{-
nonterminal SGSGRegex;

synthesized attribute regexApp::Expr occurs on SGSGRegex;

concrete production regexLabel_c
top::SGSGRegex ::= lab::IdLower_t
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
top::SGSGRegex ::= r::SGSGRegex SGRegexStar_t
{
  top.regexApp = Silver_Expr{
    regexStar($Expr{r.regexApp})
  };
}

concrete production regexCat_c
top::SGSGRegex ::= l::SGSGRegex Dot_t r::SGSGRegex
{
  top.regexApp = Silver_Expr{
    regexCat($Expr{l.regexApp}, $Expr{r.regexApp})
  };
}

concrete production regexMaybe_c
top::SGSGRegex ::= r::SGSGRegex SGRegexQuestion_t
{
  top.regexApp = Silver_Expr{
    regexMaybe($Expr{r.regexApp})
  };
}
-}

synthesized attribute toExpr::Expr;

nonterminal SGRegex_c with toExpr;

concrete production regexCat_c
top::SGRegex_c ::= l::SGRegex_c r::SGRegexRepetition_c
{
  top.toExpr = Silver_Expr {
    regexCat(
      $Expr{l.toExpr},
      $Expr{r.toExpr}
    )
  };
}

concrete production regexRepetition_c
top::SGRegex_c ::= r::SGRegexRepetition_c
{
  top.toExpr = r.toExpr;
}

--

nonterminal SGRegexRepetition_c with toExpr;

concrete production regexStar_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c SGRegexStar_t
{
  top.toExpr = Silver_Expr {
    regexStar($Expr{r.toExpr})
  };
}

concrete production regexMaybe_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c SGRegexQuestion_t
{
  top.toExpr = Silver_Expr {
    regexMaybe(
      $Expr{r.toExpr}
    )
  };
}

concrete production regexLabelItem_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c
{
  top.toExpr = r.toExpr;
}

--

nonterminal SGRegexLabel_c with toExpr;

concrete production regexLabel_c
top::SGRegexLabel_c ::= SGRegexBacktick_t lab::IdLower_t
{
  top.toExpr = Silver_Expr {
    regexLabel($Expr{applicationExpr(baseExpr(qName("label_" ++ lab.lexeme)), '(', emptyAppExprs(), ')')})
  };
}

concrete production parensSGRegex_c
top::SGRegexLabel_c ::= '(' r::SGRegex_c ')'
{
  top.toExpr = r.toExpr;
}