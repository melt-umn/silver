grammar silver:extension:strategyattr:construction;

imports silver:definition:core;
imports silver:extension:strategyattr;
imports silver:extension:silverconstruction;

imports silver:reflect;
imports silver:metatranslation;
imports silver:rewrite as s;
imports silver:langutil:pp;

terminal SilverStrategyExpr_t 'Silver_StrategyExpr' lexer classes {KEYWORD, RESERVED};
terminal AntiquoteStrategyExpr_t '$StrategyExpr' lexer classes {Antiquote, Strategy};
terminal AntiquoteStrategyQName_t '$strategyQName' lexer classes {Antiquote, Strategy};

concrete production quoteStrategyExpr
top::Expr ::= 'Silver_StrategyExpr' '(' genName::Expr ')' '{' cst::StrategyExpr_c '}'
{
  top.unparse = s"Silver_StrategyExpr (${genName.unparse}) {${cst.unparse}}";
  -- The meta-translation library directly translates all annotation values into
  -- static initialization code, however we want to specify genName at runtime.
  -- Solution: construct the term with "" as the base genName and translate it
  -- into an expression like normal, then use term rewriting to replace all all
  -- occurences of `genName=$e` with `genName=$genName ++ $e`.
  -- Confused yet?
  -- A "simpler" approach would be to handle this in the meta-translation library
  -- in one pass, but we want to keep that code as a generic library as much as possible.
  cst.givenGenName = "";
  forwards to
    rewriteWith(
      s:allTopDown(
        rule on AnnoExpr of
        | annoExpr(n, _, presentAppExpr(e), location=l) when n.name == "genName" ->
          annoExpr(n, '=', presentAppExpr(plusPlus(genName, '++', e, location=l), location=l), location=l)
        end),
        translate(top.location, reflect(cst.ast))).fromJust;
}

concrete production antiquoteStrategyExpr_c
top::StrategyExpr_c ::= '$StrategyExpr' '{' e::Expr '}'
{
  top.unparse = s"$$StrategyExpr{${e.unparse}}";
  top.ast = antiquoteStrategyExpr(e, genName=top.givenGenName, location=top.location);
}

concrete production antiquote_strategyQName
top::StrategyQName ::= '$strategyQName' '{' e::Expr '}'
{
  top.ast = antiquote_qName('$qName', $2, e, $4, location=top.location);
}

abstract production antiquoteStrategyExpr
top::StrategyExpr ::= e::Expr
{
  top.unparse = s"$$StrategyExpr{${e.unparse}}";
  forwards to error("no forward");
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:extension:strategyattr:construction:antiquoteStrategyExpr"];
}
