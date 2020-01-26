grammar silver:extension:rewriting;

imports silver:rewrite;
imports silver:hostEmbedding;
imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;
imports silver:translation:java:core only finalType;
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
  top.unparse = s"rewriteWith(${s.unparse}, ${e.unparse})";

  local errCheckS::TypeCheck = check(s.typerep, nonterminalType("silver:rewrite:Strategy", []));
  errCheckS.finalSubst = top.finalSubst;
  
  local localErrors::[Message] =
    if errCheckS.typeerror
    then [err(top.location, "First argument to rewriteWith must be Strategy. Instead got " ++ errCheckS.leftpp)]
    else [];
  
  -- Can't use an error production here, unfourtunately, due to circular dependency issues.
  top.errors :=
    if !null(s.errors ++ e.errors ++ localErrors)
    then s.errors ++ e.errors ++ localErrors
    else forward.errors;
  
  -- TODO: Equation needed due to weirdness with lets auto-undecorating bindings.
  -- See comments in definition of lexicalLocalReference (grammars/silver/modification/let_fix/Let.sv)
  -- Actual syntax to exactly constrain the types of arbitrary expressions would be useful here.
  top.typerep = nonterminalType("core:Maybe", [e.typerep]);
  
  s.downSubst = top.downSubst;
  e.downSubst = s.upSubst;
  errCheckS.downSubst = e.upSubst;
  forward.downSubst = errCheckS.upSubst;
  
  forwards to
    Silver_Expr {
      case decorate $Expr{exprRef(s, location=builtin)}
           with {
             silver:rewrite:term = silver:reflect:reflect($Expr{exprRef(e, location=builtin)});
           }.silver:rewrite:result of
      | just(a) ->
        -- let needed to constrain the result type to be the same as e.
        let res :: $TypeExpr{typerepTypeExpr(e.typerep, location=builtin)} = reifyUnchecked(a)
        in just(res)
        end
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

  -- Pattern matching error checking (mostly) happens on what caseExpr forwards to,
  -- so we need to decorate one of those here.
  local checkExpr::Expr =
    caseExpr(
      [hackExprType(ty.typerep, location=builtin)],
      ml.wrappedMatchRuleList,
      errorExpr([], location=builtin),
      ty.typerep,
      location=builtin);
  checkExpr.env = top.env;
  checkExpr.flowEnv = top.flowEnv;
  checkExpr.downSubst = top.downSubst;
  checkExpr.finalSubst = checkExpr.upSubst; -- Not top.finalSubst to avoid circularity
  checkExpr.grammarName = top.grammarName;
  checkExpr.frame = top.frame;
  checkExpr.config = top.config;
  checkExpr.compiledGrammars = top.compiledGrammars;
  checkExpr.boundVars = [];
  
  ml.matchRulePatternSize = 1;
  ml.ruleIndex = 0;
  ml.decRuleExprsIn = checkExpr.decRuleExprs;
  
  -- Can't use an error production here, unfourtunately, due to circular dependency issues.
  top.errors :=
    if !null(ml.errors ++ checkExpr.errors)
    then ml.errors ++ checkExpr.errors
    else forward.errors;
  
  forward.downSubst = checkExpr.upSubst;
  
  local fwrd::Expr = translate(builtin, reflect(ml.transform));
  
  --forwards to unsafeTrace(fwrd, print(show(80, ml.transform.pp) ++ "\n\n\n", unsafeIO()));
  forwards to fwrd;
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
{
  top.pp = pp"antiquoteASTExpr {${text(e.unparse)}}";
  forwards to error("no forward");
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{ directAntiquoteProductions <- ["silver:extension:rewriting:antiquoteASTExpr"]; }

global builtin::Location = builtinLoc("rewriting");
