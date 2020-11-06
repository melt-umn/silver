grammar silver:extension:rewriting;

imports silver:rewrite;
imports silver:metatranslation;
imports silver:langutil:pp;
imports silver:util;

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
    s.errors ++ e.errors ++ 
    (if errCheckS.typeerror
     then [err(top.location, "First argument to rewriteWith must be Strategy. Instead got " ++ errCheckS.leftpp)]
     else []) ++
    (if null(getTypeDcl("silver:rewrite:Strategy", top.env))
     then [err(top.location, "Term rewriting requires import of silver:rewrite")]
     else []);
  
  -- Can't use an error production here, unfourtunately, due to circular dependency issues.
  top.errors := if !null(localErrors) then localErrors else forward.errors;
  
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
-- Silver Expr grammar into an "ETF" style with seperate operator nonterminals.
terminal Sequence_t '<*'  precedence = 12, association = left; -- Same as *
terminal Choice_t   '<+'  precedence = 11, association = left; -- Same as +

concrete production sequenceOperator
top::Expr ::= s1::Expr '<*' s2::Expr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  forwards to mkStrFunctionInvocation(top.location, "silver:rewrite:sequence", [s1, s2]);
}

concrete production choiceOperator
top::Expr ::= s1::Expr '<+' s2::Expr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  forwards to mkStrFunctionInvocation(top.location, "silver:rewrite:choice", [s1, s2]);
}


terminal Traverse_t 'traverse' lexer classes {KEYWORD, RESERVED};

concrete production traverseProdExprAnno
top::Expr ::= 'traverse' n::QName '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  top.unparse = s"traverse ${n.name}(${es.unparse}, ${anns.unparse})";
  
  local numChildren::Integer = length(n.lookupValue.typerep.inputTypes);
  local annotations::[String] = map((.argName), n.lookupValue.typerep.namedTypes);
  es.appExprTypereps = repeat(nonterminalType("silver:rewrite:Strategy", []), numChildren);
  es.appExprApplied = n.unparse;
  anns.appExprApplied = n.unparse;
  anns.funcAnnotations =
    map(namedArgType(_, nonterminalType("silver:rewrite:Strategy", [])), annotations);
  anns.remainingFuncAnnotations = anns.funcAnnotations;
 
  local localErrors::[Message] =
    es.errors ++ anns.traverseErrors ++
    if null(getTypeDcl("silver:rewrite:Strategy", top.env))
    then [err(top.location, "Term rewriting requires import of silver:rewrite")]
    else [];

  es.downSubst = top.downSubst;
  anns.downSubst = es.upSubst;
  forward.downSubst = es.downSubst;
  
  local transform::Strategy =
    traversal(n.lookupValue.fullName, es.traverseTransform, anns.traverseTransform);
  local fwrd::Expr = translate(builtin, reflect(new(transform)));
  
  forwards to if !null(localErrors) then errorExpr(localErrors, location=builtin) else fwrd;
}
concrete production traverseProdAnno
top::Expr ::= 'traverse' n::QName '(' anns::AnnoAppExprs ')'
{
  forwards to traverseProdExprAnno($1, n, $3, emptyAppExprs(location=$3.location), ',', anns, $5, location=top.location);
}
concrete production traverseProdExpr
top::Expr ::= 'traverse' n::QName '(' es::AppExprs ')'
{
  forwards to traverseProdExprAnno($1, n, $3, es, ',', emptyAnnoAppExprs(location=$4.location), $5, location=top.location);
}
concrete production traverseProdEmpty
top::Expr ::= 'traverse' n::QName '(' ')'
{
  forwards to traverseProdExprAnno($1, n, $3, emptyAppExprs(location=$3.location), ',', emptyAnnoAppExprs(location=$4.location), $4, location=top.location);
}

abstract production traverseConsList
top::Expr ::= 'traverse' '(' h::AppExpr '::' t::AppExpr ')'
{
  top.unparse = s"traverse (${h.unparse} :: ${t.unparse})";
  
  local transform::Strategy = consListCongruence(h.traverseTransform, t.traverseTransform);
  forwards to translate(top.location, reflect(new(transform)));
}
concrete production traverseConsListFirstMissing
top::Expr ::= 'traverse' '(' h::'_' '::' t::AppExpr ')'
{
  forwards to traverseConsList($1, $2, missingAppExpr(h, location=h.location), $4, t, $6, location=top.location);
}
concrete production traverseConsListFirstPresent
top::Expr ::= 'traverse' '(' h::Expr '::' t::AppExpr ')'
{
  forwards to traverseConsList($1, $2, presentAppExpr(h, location=h.location), $4, t, $6, location=top.location);
}

concrete production traverseNilList
top::Expr ::= 'traverse' '[' ']'
{
  top.unparse = s"traverse []";
  
  local transform::Strategy = nilListCongruence();
  forwards to translate(top.location, reflect(new(transform)));
}

concrete production traverseList
top::Expr ::= 'traverse' '[' es::AppExprs ']'
{
  top.unparse = s"traverse [${es.unparse}]";
  
  local transform::Strategy = foldr(consListCongruence, nilListCongruence(), es.traverseTransform);
  forwards to translate(top.location, reflect(new(transform)));
}

-- Compute our own errors on AnnoAppExprs, since we want to ignore missing annotations (like in patterns)
synthesized attribute traverseErrors::[Message] occurs on AnnoAppExprs, AnnoExpr;
synthesized attribute traverseTransform<a>::a;
attribute traverseTransform<Strategy> occurs on AppExpr;
attribute traverseTransform<Pair<String Strategy>> occurs on AnnoExpr;
attribute traverseTransform<[Strategy]> occurs on AppExprs;
attribute traverseTransform<[Pair<String Strategy>]> occurs on AnnoAppExprs;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.traverseTransform = id();
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.traverseTransform = antiquoteStrategy(e);
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.traverseTransform = es.traverseTransform ++ [e.traverseTransform];
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.traverseTransform = [e.traverseTransform];
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  top.traverseTransform = [];
}

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.traverseErrors =
    e.errors ++
    if !extractNamedArg(qn.name, top.funcAnnotations).fst.isJust
    then [err(qn.location, "Named parameter '" ++ qn.name ++ "' is not appropriate for '" ++ top.appExprApplied ++ "'")]
    else [];
  top.traverseTransform = pair(qn.lookupAttribute.fullName, e.traverseTransform);
}

aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  top.traverseErrors = es.traverseErrors ++ e.traverseErrors;
  top.traverseTransform = es.traverseTransform ++ [e.traverseTransform];
}
aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.traverseErrors = e.traverseErrors;
  top.traverseTransform = [e.traverseTransform];
}
aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.traverseErrors = [];
  top.traverseTransform = [];
}

terminal Rule_t 'rule' lexer classes {KEYWORD, RESERVED};

concrete production ruleExpr
top::Expr ::= 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  
  -- Find the free type variables (i.e. lacking a definition) to add as skolem constants
  local freeTyVars::[String] =
    filter(\ tv::String -> null(getTypeDcl(tv, top.env)), makeSet(ty.lexicalTypeVariables));
  ty.env = newScopeEnv(addNewLexicalTyVars(top.grammarName, ty.location, freeTyVars), top.env);

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
  checkExpr.finalSubst = checkExpr.upSubst; -- Not top.finalSubst to avoid circularity
  checkExpr.grammarName = top.grammarName;
  checkExpr.frame = top.frame;
  checkExpr.config = top.config;
  checkExpr.compiledGrammars = top.compiledGrammars;
  checkExpr.boundVars = [];
  
  ml.matchRulePatternSize = 1;
  ml.ruleIndex = 0;
  ml.decRuleExprsIn = checkExpr.decRuleExprs;
  
  local localErrors::[Message] =
    ty.errors ++ ml.errors ++ checkExpr.errors ++
    if null(getTypeDcl("silver:rewrite:Strategy", top.env))
    then [err(top.location, "Term rewriting requires import of silver:rewrite")]
    else [];
  
  -- Can't use an error production here, unfourtunately, due to circular dependency issues.
  top.errors := if !null(localErrors) then localErrors else forward.errors;
  
  checkExpr.downSubst = top.downSubst;
  forward.downSubst = checkExpr.upSubst;
  
  local finalRuleType::Type =
    freshenType(
      performSubstitution(ty.typerep, checkExpr.upSubst),
      ty.typerep.freeVariables);
  local transform::Strategy =
    if ml.isPolymorphic
    then requireType(antiquoteASTExpr(
      Silver_Expr {
        silver:rewrite:anyASTExpr(
          \ $TypeExpr{typerepTypeExpr(finalRuleType, location=builtin)} -> unit())
      })) <* ml.transform
    else ml.transform;
  
  local fwrd::Expr = translate(top.location, reflect(new(transform)));
  
  --forwards to unsafeTrace(fwrd, print(top.location.unparse ++ ": " ++ show(80, transform.pp) ++ "\n\n\n", unsafeIO()));
  forwards to fwrd;
}

-- Hack dummy expr with a given type
abstract production hackExprType
top::Expr ::= t::Type
{
  top.typerep = t;
  forwards to errorExpr([], location=builtin);
}

-- Strategy meta-translation
abstract production antiquoteASTExpr
top::ASTExpr ::= e::Expr
{
  top.pp = pp"antiquoteASTExpr {${text(e.unparse)}}";
  forwards to error("no forward");
}

abstract production antiquoteStrategy
top::Strategy ::= e::Expr
{
  top.pp = pp"antiquoteStrategy {${text(e.unparse)}}";
  forwards to error("no forward");
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:extension:rewriting:antiquoteASTExpr",
     "silver:extension:rewriting:antiquoteStrategy"];
}

global builtin::Location = builtinLoc("rewriting");
