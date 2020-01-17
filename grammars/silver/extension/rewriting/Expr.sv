grammar silver:extension:rewriting;

imports silver:reflect;
imports silver:rewrite;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:env;
imports silver:extension:patternmatching;
imports silver:extension:reflection;
imports silver:modification:let_fix;

terminal RewriteWith_t 'rewriteWith' lexer classes {KEYWORD, RESERVED};

concrete production rewriteExpr
top::Expr ::= 'rewriteWith' '(' s::Expr ',' e::Expr ')'
{
  -- TODO: Proper error checking here
  forwards to
    Silver_Expr {
      case decorate $Expr{s} with { term = silver:reflect:reflect($Expr{e}); }.result of
      | just(a) -> just(reifyUnchecked(a))
      | nothing() -> nothing()
      end
    };
}

-- Note that these being infix operators means that this wouldn't pass the MDA,
-- despite being a Silver "extension".  This could be fixed by refactoring the
-- Silver Expr grammar into an "ETF" style.
terminal Sequence_t '<*'  precedence = 12, association = left; -- Same as *
terminal Choice_t   '<+'  precedence = 11, association = left; -- Sane as +

concrete production sequenceOperator
top::Expr ::= s1::Expr '<*' s2::Expr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  
  forwards to Silver_Expr { silver:rewrite:sequence($Expr{s1}, $Expr{s2}) };
}

concrete production choiceOperator
top::Expr ::= s1::Expr '<+' s2::Expr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  
  forwards to Silver_Expr { silver:rewrite:choice($Expr{s1}, $Expr{s2}) };
}

global builtin::Location = builtinLoc("rewriting");
