grammar silver:modification:patternmatching;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;
import silver:modification:let_fix;
import silver:extension:list; -- Oh no, this is a hack! TODO

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal Arrow_kwd '->' precedence = 7 ;
terminal Vbar_kwd '|' precedence = 3 ;

{--
 - The type this pattern is matching on.
 - Invariant: if it's a nonterminal type, then it's decorated. Always.
 -}
inherited attribute patternType :: TypeExp ;
{--
 - For pattern lists.
 - @see patternType
 -}
autocopy attribute patternListTypes :: [ TypeExp ] ;

-- How to access the thing we're testing in Pattern
inherited attribute patternScrutinee :: Expr ;
-- ditto, pattern lists
autocopy attribute patternListScrutinees :: [Expr];

-- Built up result of how to do the pattern match
synthesized attribute translation_tree :: Expr ;
-- How to test if patternScrutinee matches this pattern
synthesized attribute cond_tree :: Expr ;
-- What to evaluate to if it does
synthesized attribute then_tree :: Expr ;
-- variables to bind to lets in a pattern list
synthesized attribute letAssigns_tree :: [ AssignExpr ] ;

-- MR | ...
nonterminal MRuleList with pp, grammarName, env, file, location, typerep, errors, signature, upSubst, downSubst, finalSubst,
                           patternListTypes, translation_tree, patternListScrutinees, blockContext ;
-- P -> E
nonterminal MatchRule with pp, grammarName, env, file, location, typerep, errors, signature, upSubst, downSubst, finalSubst,
                           patternListTypes, cond_tree, then_tree, patternListScrutinees, blockContext ;

-- prod(PL) | int | string | bool | ...
nonterminal Pattern with pp, grammarName, env, file, location, defs, errors, signature, upSubst, downSubst, finalSubst,
                         patternType, cond_tree, letAssigns_tree, patternScrutinee, blockContext ;
-- P , ...
nonterminal PatternList with pp, grammarName, env, file, location, defs, errors, signature, upSubst, downSubst, finalSubst,
                             patternListTypes, cond_tree, letAssigns_tree, patternListScrutinees, blockContext ;

concrete production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' ml::MRuleList 'end'
{ 
  top.pp = "case " ++ es.pp ++ " of " ++ ml.pp ++ " end";  
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = ml.typerep;

  top.errors := if null(ml.errors)
                then forward.errors
                else es.errors ++ ml.errors;

  ml.patternListTypes = map(ensureDecoratedType, getTypesExprs(es.exprs));
  ml.patternListScrutinees = map(ensureDecoratedExpr, es.exprs);
  
  forwards to ml.translation_tree;
  
  -- propagate typing information :
  es.downSubst = top.downSubst;
  ml.downSubst = es.upSubst;
  forward.downSubst = ml.upSubst;
  -- top.upSubst = forward.upSubst; -- implied by forwarding...
}

concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.pp = m.pp;
  top.errors := m.errors;
  top.location = m.location;
  top.typerep = m.typerep;

  top.translation_tree = ifThenElse('if', m.cond_tree,
                                   'then', m.then_tree,
                                   'else', productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t,"core:error")))), '(', exprsSingle(stringConst(terminal(String_t, "\"Error: pattern match failed.\\n\""))), ')')) ;

  m.downSubst = top.downSubst;
  top.upSubst = m.upSubst;
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.pp = h.pp ++ " | " ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.location = h.location;
  top.typerep = h.typerep;

  top.translation_tree = ifThenElse('if', h.cond_tree,
                                   'then', h.then_tree,
                                   'else', t.translation_tree) ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  h.downSubst = top.downSubst;
  t.downSubst = h.upSubst;
  errCheck1.downSubst = t.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(h.typerep, t.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Pattern matching case type mismatch. Rule has type " ++ errCheck1.leftpp ++ " while remaining rules are type " ++ errCheck1.rightpp)]
       else [];
}

concrete production matchRule
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.pp = pt.pp ++ " -> " ++ e.pp;
  top.errors := pt.errors ++ e.errors;
  top.location = e.location;
  top.typerep = e.typerep;

  local attribute newEnv::Decorated Env;
  newEnv = newScopeEnv(pt.defs, top.env); 

  e.env = newEnv;
  pt.env = newEnv;

  top.cond_tree = pt.cond_tree ;
  top.then_tree = if null(pt.letAssigns_tree)
                  then e
                  else letp('let', toAssigns(pt.letAssigns_tree),
                            'in', e, 'end');

  pt.downSubst = top.downSubst;
  e.downSubst = pt.upSubst;
  top.upSubst = e.upSubst;
}


concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.pp = p.pp;
  top.errors := p.errors;
  top.defs = p.defs;
  top.location = p.location;

  p.patternType = if !null(top.patternListTypes) 
                  then head(top.patternListTypes) 
                  else errorType();
  p.patternScrutinee = if !null(top.patternListScrutinees)
                       then head(top.patternListScrutinees)
                       else baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "no_such_value")))); -- TODO: eh... maybe this is okay?

  top.errors <-
       if null(top.patternListTypes)
       then [err(top.location, "More patterns than expected in pattern list")] 
       else if length(top.patternListTypes) > 1
       then [err(top.location, "Fewer patterns than expected in pattern list")]
       else [];

  top.cond_tree = p.cond_tree;
  top.letAssigns_tree = p.letAssigns_tree;
  
  p.downSubst = top.downSubst;
  top.upSubst = p.upSubst;
}

concrete production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.pp = ps1.pp ++ ", " ++ p.pp;
  top.errors := p.errors ++ ps1.errors;
  top.defs = appendDefs(ps1.defs, p.defs);
  top.location = p.location;

  p.patternType = if !null(top.patternListTypes) 
                  then head(top.patternListTypes) 
                  else errorType();
  p.patternScrutinee = if !null(top.patternListScrutinees)
                       then head(top.patternListScrutinees)
                       else baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "no_such_value")))); -- TODO: eh... maybe this is okay?

  ps1.patternListTypes = if !null(top.patternListTypes)
                         then tail(top.patternListTypes) 
                         else [];
  ps1.patternListScrutinees = if !null(top.patternListScrutinees)
                              then tail(top.patternListScrutinees)
                              else [];

  top.cond_tree = and(p.cond_tree, terminal(And_t, "&&"), ps1.cond_tree) ;
  top.letAssigns_tree = p.letAssigns_tree ++ ps1.letAssigns_tree ;

  p.downSubst = top.downSubst;
  ps1.downSubst = p.upSubst;
  top.upSubst = ps1.upSubst;
}

-- lol, dangling comma bug TODO
concrete production PatternList_nil
top::PatternList ::= 
{
  top.pp = "";
  top.errors := [];
  top.defs = emptyDefs();
  top.location = loc("PatternList_nill", -1, -1);

  top.errors <- if null(top.patternListTypes)
                then []
                else [err(top.location, "Fewer patterns than expected in pattern list")];  -- TODO BUG: no location

  top.cond_tree = trueConst(terminal(True_kwd, "true"));
  top.letAssigns_tree = [];

  top.upSubst = top.downSubst;
}

--------------------------------------------------------------------------------


concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;
  p.location = prod.location;
  p.defs = ps.defs ;
  p.errors := prod.lookupValue.errors ++ ps.errors ; 

  local attribute prod_type :: TypeExp;
  prod_type = freshenCompletely(prod.lookupValue.typerep);
  
  -- Perform subst after unifying result type, then perform decoration.
  ps.patternListTypes = map(ensureDecoratedType, mapSubst(prod_type.inputTypes, errCheck1.upSubst));
  -- See this function for more info, but summary: 3 cases:
  --  case 2: sig nt.      here nt     = fetch-decorated
  --  case 3: sig non-nt.  here non-nt = fetch-asis
  --  case 4: sign non-nt. here nt     = fetch-asis AND THEN DECORATE
  ps.patternListScrutinees = generatePLSFromValNType(p.patternScrutinee, 0, prod_type.inputTypes, errCheck1.upSubst);
 
  p.cond_tree = and(patternMatchRuntimeIsProd(p.patternScrutinee, prod.lookupValue.fullName),
                    '&&', ps.cond_tree);

  p.letAssigns_tree = ps.letAssigns_tree ;
  
  -- Output is right type.  and its a production
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  ps.downSubst = errCheck1.upSubst;
  p.upSubst = ps.upSubst;
  
  errCheck1 = check(decoratedTypeExp(prod_type.outputType), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(prod.location, prod.pp ++ " constructs type " ++ errCheck1.leftpp ++ " but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];

  p.errors <- case prod.lookupValue.typerep of
                productionTypeExp(_,_) -> []
              | _ -> [err(prod.location, prod.pp ++ " is type " ++ prettyType(prod.lookupValue.typerep) ++ " and is not a production.")]
              end;
  
} 

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme ;
  p.location = loc(p.file, num.line, num.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = eqeq(p.patternScrutinee, terminal(EQEQ_t, "=="), intConst(num)) ;
  p.letAssigns_tree = [] ; 

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(intTypeExp(), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Integer value appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  p.location = loc(p.file, str.line, str.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = eqeq(p.patternScrutinee, terminal(EQEQ_t, "=="), stringConst(str)) ;
  p.letAssigns_tree = [] ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(stringTypeExp(), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "String value appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [];

  p.defs = emptyDefs() ;

  p.cond_tree = eqeq(p.patternScrutinee, terminal(EQEQ_t, "=="), trueConst(terminal(True_kwd, "true"))) ;
  p.letAssigns_tree = [] ; 

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(boolTypeExp(), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Boolean type constructor appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = eqeq(p.patternScrutinee, terminal(EQEQ_t, "=="), falseConst(terminal(False_kwd, "false"))) ;
  p.letAssigns_tree = [] ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(boolTypeExp(), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Boolean type constructor appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production nilListPattern
p::Pattern ::= '[' ']'
{
  p.pp = "[]";
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:null")))),
                    '(', exprsSingle(p.patternScrutinee), ')');
  p.letAssigns_tree = [] ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(listTypeExp(errorType()), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "List type constructor appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production consListPattern
p::Pattern ::= hp::Pattern '::' tp::Pattern
{
  p.pp = hp.pp ++ "::" ++ tp.pp;
  p.location = loc(p.file, $2.line, $2.column);
  p.errors := hp.errors ++ tp.errors ;

  p.defs = appendDefs(hp.defs, tp.defs) ;

  p.cond_tree = not('!', productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:null")))),
                    '(', exprsSingle(p.patternScrutinee), ')'));
  p.letAssigns_tree = [] ;

  hp.patternType = freeVar; -- unified below...
  tp.patternType = p.patternType;
  
  hp.patternScrutinee = productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:head")))),
                    '(', exprsSingle(p.patternScrutinee), ')');
  tp.patternScrutinee = productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:tail")))),
                    '(', exprsSingle(p.patternScrutinee), ')');

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;
  local attribute freeVar :: TypeExp; freeVar = errorType();

  errCheck1.downSubst = p.downSubst;
  hp.downSubst = errCheck1.upSubst;
  tp.downSubst = hp.upSubst;
  p.upSubst = tp.upSubst;
  
  errCheck1 = check(listTypeExp(freeVar), p.patternType);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "List type constructor appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_" ;
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = trueConst(terminal(True_kwd, "true")) ;
  p.letAssigns_tree = [] ;

  p.upSubst = p.downSubst;
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name;
  p.location = v.location;

  p.defs = addLexicalLocalDcl(p.grammarName, v.location, v.name, p.patternType, emptyDefs());

  p.errors :=
        if length(getValueDclAll(v.name, p.env)) > 1
        then [err(p.location, "Pattern variable '" ++ v.name ++ "' is already bound in this scope.")] 
        else [];

  p.cond_tree = trueConst('true') ;

  p.letAssigns_tree = [ assignExpr(v, '::', typerepType(p.patternType), '=', p.patternScrutinee) ] ;

  p.upSubst = p.downSubst;
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------

function toAssigns
LetAssigns ::= ls1::[AssignExpr]
{
  return if length(ls1) == 1 then assignListSingle(head(ls1))
         else assigns(head(ls1), terminal(Comma_t, ","), toAssigns(tail(ls1))) ;
}

function ensureDecoratedExpr
Expr ::= e::Decorated Expr
{
  local attribute et :: TypeExp;
  et = performSubstitution(e.typerep, e.upSubst);

  return if et.isDecorable
         then decorateExprWithEmpty('decorate', new(e), 'with', '{', '}')
         else new(e);
}
function ensureDecoratedType
TypeExp ::= t::TypeExp
{
  return if t.isDecorable
         then decoratedTypeExp(t)
         else t;
}

function generatePLSFromValNType
[Expr] ::= e::Expr i::Integer t::[TypeExp] s::Substitution
{
  -- If the type is not an NT, but we learn it's an NT from type info,
  -- THEN decorate the result, but fetch it with its original type
  -- ELSE fetch normally.
  return if !null(t)
         then if !head(t).isDecorable && performSubstitution(head(t),s).isDecorable
              then decorateExprWithEmpty('decorate', patternMatchRuntimeGetChild(e, i, head(t)), 'with', '{', '}') :: generatePLSFromValNType(e,i+1,tail(t),s)
              else patternMatchRuntimeGetChild(e, i, head(t)) :: generatePLSFromValNType(e,i+1,tail(t),s)
         else [];
}
--------------------------------------------------------------------------------

-- The work horses

abstract production patternMatchRuntimeIsProd
top::Expr ::= e::Expr t::String
{
  top.pp = "<INTERNAL-IS " ++ t ++ "> " ++ e.pp;
  top.location = e.location;

  top.errors := e.errors;
  top.typerep = boolTypeExp();

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = checkDecorated(e.typerep);
  -- MAJOR ASSUMPTION: this error, if it happens, is already caught. Ignore it.
}

{--
 - Fetch the child. Will decorate if it's an undecorated child.
 - Will NOT decorate if it's a pattern variable. Must be decorated elsewhere for pattern matching
 -
 - @param t The type of the child *AS IT APPEARS IN THE PRODUCTION SIGNATURE*
 -}
abstract production patternMatchRuntimeGetChild
top::Expr ::= e::Expr c::Integer t::TypeExp
{
  top.pp = "<INTERNAL-CHILD " ++ toString(c) ++ "::" ++ prettyType(t) ++ "> " ++ e.pp;
  top.location = e.location;

  top.errors := e.errors;
  top.typerep =
       if t.isDecorable
       then decoratedTypeExp(t)
       else t;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = checkDecorated(e.typerep);
  -- MAJOR ASSUMPTION: this error, if it happens, is already caught. Ignore it.
}

