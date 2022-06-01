grammar silver:compiler:extension:regex;

import silver:util:treeset as ts;
import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:metatranslation;
import silver:reflect;
import silver:regex:concrete_syntax;

terminal MatchesOp_t '=~' precedence = 9, association = left, lexer classes OP;

concrete production literalRegex
top::Expr ::= '/' reg::Regex '/'
layout {}
{
  top.unparse = s"/${reg.unparse}/";
  propagate freeVars;
  forwards to
    if null(getTypeDcl("silver:regex:Regex", top.env))
    then errorExpr([err(top.location, "Use of regexes requires import of silver:regex")], location=top.location)
    else translate(top.location, reflect(reg.ast));
}

concrete production matches
top::Expr ::= e::Expr '=~' r::Expr
{
  top.unparse = s"(${e.unparse}) =~ (${r.unparse})";
  propagate freeVars;
  forwards to
    if null(getValueDcl("silver:regex:matches", top.env))
    then errorExpr([err(top.location, "Use of regexes requires import of silver:regex")], location=top.location)
    else Silver_Expr { silver:regex:matches($Expr{r}, $Expr{e}) };
}

