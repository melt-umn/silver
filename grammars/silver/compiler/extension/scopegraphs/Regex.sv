grammar silver:compiler:extension:scopegraphs;


--

terminal SGRegexBacktick_t '`';
terminal SGRegexStar_t '*';
terminal SGRegexPlus_t '+';
terminal SGRegexQuestion_t '?';
terminal SGRegexOr_t '|';

--

synthesized attribute toExpr::Expr;

nonterminal SGRegex_c with toExpr;

concrete production regexEps_c
top::SGRegex_c ::= 
{
  top.toExpr = Silver_Expr {
    regexEpsilon()
  };
}

concrete production regexOr_c
top::SGRegex_c ::= l::SGRegexCat_c '|' r::SGRegex_c
{
  top.toExpr = Silver_Expr {
    regexOr(
      $Expr{l.toExpr},
      $Expr{r.toExpr}
    )
  };
}

concrete production regexCat_c
top::SGRegex_c ::= r::SGRegexCat_c
{
  top.toExpr = r.toExpr;
}

--

nonterminal SGRegexCat_c with toExpr;

concrete production regexCatCat_c
top::SGRegexCat_c ::= l::SGRegexCat_c r::SGRegexRepetition_c
{
  top.toExpr = Silver_Expr {
    regexCat(
      $Expr{l.toExpr},
      $Expr{r.toExpr}
    )
  };
}

concrete production regexCatRepetition_c
top::SGRegexCat_c ::= r::SGRegexRepetition_c
{
  top.toExpr = r.toExpr;
}

--

nonterminal SGRegexRepetition_c with toExpr;

concrete production regexRepetitionStar_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c '*'
{
  top.toExpr = Silver_Expr {
    regexStar($Expr{r.toExpr})
  };
}

concrete production regexRepetitionPlus_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c '+'
{
  top.toExpr = Silver_Expr {
    regexCat(r.toExpr, regexStar($Expr{r.toExpr}))
  };
}

concrete production regexRepetitionMaybe_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c '?'
{
  top.toExpr = Silver_Expr {
    regexMaybe(
      $Expr{r.toExpr}
    )
  };
}

concrete production regexRepetitionLabel_c
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
    regexLabel($Expr{
      applicationExpr(
        baseExpr(qName("label_" ++ lab.lexeme)),
        '(', emptyAppExprs(), ')')
    })
  };
}

concrete production parensSGRegex_c
top::SGRegexLabel_c ::= '(' r::SGRegex_c ')'
{
  top.toExpr = r.toExpr;
}
