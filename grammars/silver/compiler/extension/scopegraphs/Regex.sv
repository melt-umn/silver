grammar silver:compiler:extension:scopegraphs;


--

terminal SGRegexBacktick_t '`';
terminal SGRegexStar_t '*';
terminal SGRegexPlus_t '+';
terminal SGRegexQuestion_t '?';
terminal SGRegexOr_t '|';

--

synthesized attribute toExpr::Expr;
synthesized attribute toExpr2::Expr;

--

nonterminal SGRegexRoot_c with errors, toExpr, toExpr2, possibleLabs, grammarName;
propagate errors, possibleLabs, grammarName on SGRegexRoot_c;

concrete production sgRegexRoot_c
top::SGRegexRoot_c ::= rx::SGRegex_c
{
  top.toExpr =
    if null(rx.errors)
    then rx.toExpr
    else Silver_Expr { error("Should never be demanded!") };

  top.toExpr2 =
    if null(rx.errors)
    then rx.toExpr2
    else Silver_Expr { error("Should never be demanded!") };
}

--

nonterminal SGRegex_c with errors, toExpr, toExpr2, possibleLabs, grammarName;
propagate errors, possibleLabs, grammarName on SGRegex_c;

concrete production regexEps_c
top::SGRegex_c ::= 
{
  top.toExpr = Silver_Expr {
    regexEpsilon()
  };

  top.toExpr2 = Silver_Expr {
    regexEpsilonFun()
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

  top.toExpr2 = Silver_Expr {
    regexOrFun(
      $Expr{l.toExpr2},
      $Expr{r.toExpr2}
    )
  };
}

concrete production regexCat_c
top::SGRegex_c ::= r::SGRegexCat_c
{
  top.toExpr = r.toExpr;

  top.toExpr2 = r.toExpr2;
}

--

nonterminal SGRegexCat_c with toExpr, toExpr2, errors, possibleLabs, grammarName;
propagate errors, possibleLabs, grammarName on SGRegexCat_c;

concrete production regexCatCat_c
top::SGRegexCat_c ::= l::SGRegexCat_c r::SGRegexRepetition_c
{
  top.toExpr = Silver_Expr {
    regexCat(
      $Expr{l.toExpr},
      $Expr{r.toExpr}
    )
  };

  top.toExpr2 = Silver_Expr {
    regexCatFun(
      $Expr{l.toExpr2},
      $Expr{r.toExpr2}
    )
  };
}

concrete production regexCatRepetition_c
top::SGRegexCat_c ::= r::SGRegexRepetition_c
{
  top.toExpr = r.toExpr;

  top.toExpr2 = r.toExpr2;
}

--

nonterminal SGRegexRepetition_c with toExpr, toExpr2, errors, possibleLabs, grammarName;
propagate errors, possibleLabs, grammarName on SGRegexRepetition_c;

concrete production regexRepetitionStar_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c '*'
{
  top.toExpr = Silver_Expr {
    regexStar($Expr{r.toExpr})
  };

  top.toExpr2 = Silver_Expr {
    regexStarFun($Expr{r.toExpr2})
  };
}

concrete production regexRepetitionPlus_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c '+'
{
  top.toExpr = Silver_Expr {
    regexCat($Expr{r.toExpr}, regexStar($Expr{r.toExpr}))
  };

  top.toExpr2 = Silver_Expr {
    regexPlusFun($Expr{r.toExpr2})
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

  top.toExpr2 = Silver_Expr {
    regexMaybeFun(
      $Expr{r.toExpr2}
    )
  };
}

concrete production regexRepetitionLabel_c
top::SGRegexRepetition_c ::= r::SGRegexLabel_c
{
  top.toExpr = r.toExpr;

  top.toExpr2 = r.toExpr2;
}

--

nonterminal SGRegexLabel_c with toExpr, toExpr2, errors, possibleLabs, grammarName;
propagate errors, possibleLabs, grammarName on SGRegexLabel_c excluding regexLabel_c;

concrete production regexLabel_c
top::SGRegexLabel_c ::= SGRegexBacktick_t l::Name
{
  top.toExpr = Silver_Expr {
    regexLabel($Expr{
      applicationExpr(
        baseExpr(qName("label_" ++ l.name)),
        '(', emptyAppExprs(), ')')
    })
  };

  {-top.toExpr2 = Silver_Expr {
    regexLabelFun(\s::$TypeExpr{typerepTypeExpr(inhScopeType(top.grammarName, top.possibleLabs))} -> s.$QName{qName(l.name)})
  };-}

  top.toExpr2 = applicationExpr(
                  baseExpr(qName("regexLabelFun_" ++ l.name)),
                  '(', emptyAppExprs(), ')');

  top.errors :=
    if !contains(l.name, top.possibleLabs)
    then [errFromOrigin(l, "Unknown label '`" ++ l.name ++ "' in query path regex.")]
    else [];

}

concrete production parensSGRegex_c
top::SGRegexLabel_c ::= '(' r::SGRegex_c ')'
{
  top.toExpr = r.toExpr;

  top.toExpr2 = r.toExpr2;
}
