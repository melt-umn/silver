grammar silver:compiler:extension:regex;

import silver:util:treeset as ts;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:env;
import silver:compiler:metatranslation;
import silver:reflect;
import silver:regex:concrete_syntax;

terminal MatchesOp_t '=~' precedence = 9, association = left, lexer classes OP;

concrete production literalRegex
top::Expr ::= RegexSlash_t reg::Regex RegexSlash_t
layout {}
{
  top.unparse = s"/${reg.unparse}/";
  propagate freeVars;
  forwards to
    if null(getTypeDcl("silver:regex:Regex", top.env))
    then errorExpr([errFromOrigin(top, "Use of regexes requires import of silver:regex")])
    else translate(reflect(reg.ast));
}

concrete production matches
top::Expr ::= e::Expr '=~' r::Expr
{
  top.unparse = s"(${e.unparse}) =~ (${r.unparse})";
  propagate frame, freeVars;
  forwards to
    if null(getValueDcl("silver:regex:matches", top.env))
    then errorExpr([errFromOrigin(top, "Use of regexes requires import of silver:regex")])
    else Silver_Expr { silver:regex:matches($Expr{@r}, $Expr{@e}) };
}

