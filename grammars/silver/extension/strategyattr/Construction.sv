grammar silver:extension:strategyattr;

import silver:reflect;
import silver:metatranslation;
import silver:rewrite as s;

terminal SilverStrategyExpr_t 'Silver_StrategyExpr' lexer classes {KEYWORD, RESERVED};
terminal AntiquoteStrategyExpr_t '$StrategyExpr' lexer classes {Antiquote, Strategy};

concrete production quoteStrategyExprOld
top::Expr ::= 'Silver_StrategyExpr' genName::Name '{' cst::StrategyExpr_c '}'
{
  top.unparse = s"Silver_StrategyExpr {${cst.unparse}}";
  cst.givenGenName = genName.name ++ "_" ++ toString(genInt());
  forwards to translate(top.location, reflect(cst.ast));
}

concrete production quoteStrategyExpr
top::Expr ::= 'Silver_StrategyExpr' '(' genName::Expr ')' '{' cst::StrategyExpr_c '}'
{
  top.unparse = s"Silver_StrategyExpr {${cst.unparse}}";
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
    ["silver:extension:strategyattr:antiquoteStrategyExpr"];
}
