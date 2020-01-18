grammar silver:extension:rewriting;

imports silver:rewrite;
imports silver:hostEmbedding;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;
imports silver:extension:patternmatching;
imports silver:extension:reflection;
imports silver:extension:list;
imports silver:modification:primitivepattern;
imports silver:modification:lambda_fn;
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
terminal Choice_t   '<+'  precedence = 11, association = left; -- Same as +

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


terminal Rule_t 'rule' lexer classes {KEYWORD, RESERVED};

concrete production ruleExpr
top::Expr ::= 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";

  -- Pattern matching error checking is a mess.
  -- Easiest just to decorate caseExpr here.
  local checkExpr::Expr =
    caseExpr(
      [hackExprType(ty.typerep, location=builtin)],
      ml.matchRuleList,
      errorExpr([], location=builtin),
      ty.typerep,
      location=builtin);
  checkExpr.env = top.env;
  checkExpr.flowEnv = top.flowEnv;
  checkExpr.downSubst = top.downSubst;
  checkExpr.finalSubst = top.finalSubst;
  checkExpr.grammarName = top.grammarName;
  checkExpr.frame = top.frame;
  checkExpr.config = top.config;
  checkExpr.compiledGrammars = top.compiledGrammars;
  
  ml.matchRulePatternSize = 1;
  ml.downSubst = top.downSubst;
  
  local localErrors::[Message] = ml.errors ++ checkExpr.errors;
  
  forwards to
    if !null(localErrors)
    then errorExpr(localErrors, location=builtin)
    else translate(builtin, reflect(ml.transform));
}

-- Hack dummy expr with a given type
abstract production hackExprType
top::Expr ::= t::Type
{
  top.typerep = t;
  forwards to errorExpr([], location=builtin);
}

abstract production antiquoteASTExpr
top::ASTExpr ::= e::Expr
{ forwards to error("no forward"); }

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{ directAntiquoteProductions <- ["silver:extension:rewriting:antiquoteASTExpr"]; }

global builtin::Location = builtinLoc("rewriting");
