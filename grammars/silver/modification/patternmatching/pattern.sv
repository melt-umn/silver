grammar silver:modification:patternmatching;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;
import silver:modification:let_fix;
import silver:extension:list;

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal Arrow_kwd '->' precedence = 7 ;
terminal Vbar_kwd '|' precedence = 3 ;

-- The type of the things that should appear in the patterns of matchrules
autocopy attribute typerep_down :: TypeExp ;
-- list of the above for pattern lists
inherited attribute typereps_down :: [ TypeExp ] ;
-- How to access the thing we're testing in Pattern
inherited attribute base_tree :: Expr ;
-- What child # are we (for PLs)
inherited attribute child_number :: Integer;

-- Built up result of how to do the pattern match
synthesized attribute translation_tree :: Expr ;
-- How to test if base_tree matches this pattern
synthesized attribute cond_tree :: Expr ;
-- What to evaluate to if it does
synthesized attribute then_tree :: Expr ;
-- variables to bind to lets in a pattern list
synthesized attribute letAssigns_tree :: [ AssignExpr ] ;

-- MR | ...
nonterminal MRuleList with pp, grammarName, env, file, location, typerep, errors, signature, upSubst, downSubst, finalSubst,
                           typerep_down, translation_tree, base_tree, blockContext ;
-- P -> E
nonterminal MatchRule with pp, grammarName, env, file, location, typerep, errors, signature, upSubst, downSubst, finalSubst,
                           typerep_down, cond_tree, then_tree, base_tree, blockContext ;

-- prod(PL) | int | string | bool | ...
nonterminal Pattern with pp, grammarName, env, file, location, defs, errors, signature, upSubst, downSubst, finalSubst,
                         typerep_down, cond_tree, letAssigns_tree, base_tree, blockContext ;
-- P , ...
nonterminal PatternList with pp, grammarName, env, file, location, defs, errors, signature, upSubst, downSubst, finalSubst,
                             typereps_down, cond_tree, letAssigns_tree, base_tree, child_number, blockContext ;

concrete production caseExpr_c
top::Expr ::= 'case' e1::Expr 'of' ml::MRuleList 'end'
{ 
  top.pp = "case " ++ e1.pp ++ " of " ++ ml.pp ;  
  top.location = loc(top.file, $1.line, $1.column);

  -- type checking
  top.typerep = ml.typerep;
  
  local attribute caseExpressionType :: TypeExp;
  caseExpressionType = performSubstitution(e1.typerep, e1.upSubst);

  ml.typerep_down = if caseExpressionType.isDecorated
                    then caseExpressionType.decoratedType
                    else caseExpressionType;

  -- NOTE THAT WE'RE HIDING ERRORS HERE! TODO FIXME NOW WE'RE NOT I GUESS?
  top.errors <- e1.errors ++ ml.errors;
  
  -- TODO: we should 'let something = e1' and pass down 'something' as the base_tree, for efficiency reasons!!

  -- The object in question. If it's undecorated, go decorate it for consistency.
  ml.base_tree = if caseExpressionType.isDecorable
                 then decorateExprWithEmpty('decorate', e1, 'with', '{', '}')
                 else e1;

  forwards to ml.translation_tree;
  
  e1.downSubst = top.downSubst;
  ml.downSubst = e1.upSubst;
  forward.downSubst = ml.upSubst;
  -- top.upSubst = forward.upSubst; -- implied by forwarding...
}

concrete production mRuleList_one
ml::MRuleList ::= m::MatchRule
{
  ml.pp = m.pp ;
  ml.errors := m.errors ;
  ml.location = m.location ;
  ml.typerep = m.typerep;

  m.base_tree = ml.base_tree ;

  ml.translation_tree = ifThenElse('if', m.cond_tree,
                                   'then', m.then_tree,
                                   'else', productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t,"core:error")))), '(', exprsSingle(stringConst(terminal(String_t, "\"Error: pattern match failed.\\n\""))), ')')) ;
                                   
                                   

  m.downSubst = ml.downSubst;
  ml.upSubst = m.upSubst;
}

concrete production mRuleList_cons
ml::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  ml.pp = h.pp ++ " | " ++ t.pp ;
  ml.errors := h.errors ++ t.errors ;
  ml.location = h.location ;  
  ml.typerep = h.typerep;

  -- The thing we're examining doesn't change
  h.base_tree = ml.base_tree ;
  t.base_tree = ml.base_tree ;
  
  ml.translation_tree = ifThenElse('if', h.cond_tree,
                                   'then', h.then_tree,
                                   'else', t.translation_tree) ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = ml.finalSubst;

  h.downSubst = ml.downSubst;
  t.downSubst = h.upSubst;
  errCheck1.downSubst = t.upSubst;
  ml.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(h.typerep, t.typerep);
  ml.errors <-
       if errCheck1.typeerror
       then [err(ml.location, "Pattern matching case type mismatch. Rule has type " ++ errCheck1.leftpp ++ " while remaining rules are type " ++ errCheck1.rightpp)]
       else [];
}

concrete production matchRule
mr::MatchRule ::= pt::Pattern '->' e::Expr
{
  mr.pp = pt.pp ++ " -> " ++ e.pp ;
  mr.errors := pt.errors ++ e.errors ;
  mr.location = e.location ;  
  mr.typerep = e.typerep ;

  local attribute newEnv::Decorated Env;
  newEnv = newScopeEnv(pt.defs, mr.env); 

  e.env = newEnv;
  pt.env = newEnv;

  pt.base_tree = mr.base_tree;

  mr.cond_tree = pt.cond_tree ;
  mr.then_tree = if null(pt.letAssigns_tree) then e
                 else letp('let', toAssigns(pt.letAssigns_tree),
                           'in', e, 'end');

  pt.downSubst = mr.downSubst;
  e.downSubst = pt.upSubst;
  mr.upSubst = e.upSubst;
}



concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;
  p.location = prod.location;
  p.defs = ps.defs ;
  p.errors := prod.lookupValue.errors ++ ps.errors ; 

  local attribute prod_type :: TypeExp;
  prod_type = freshenCompletely(prod.lookupValue.typerep);
  
  ps.typereps_down = prod_type.inputTypes;

  ps.base_tree = p.base_tree;
  ps.child_number = 0;
 
  p.cond_tree = and(patternMatchRuntimeIsProd(p.base_tree, prod.lookupValue.fullName),
                    '&&',
                    ps.cond_tree) ;

  p.letAssigns_tree = ps.letAssigns_tree ;
  
  -- Output is right type.  and its a production
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  ps.downSubst = errCheck1.upSubst;
  p.upSubst = ps.upSubst;
  
  errCheck1 = check(prod_type.outputType, p.typerep_down);
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

  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), intConst(num)) ;
  p.letAssigns_tree = [] ; 

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(intTypeExp(), p.typerep_down);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Integer appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  p.location = loc(p.file, str.line, str.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), stringConst(str)) ;
  p.letAssigns_tree = [] ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(stringTypeExp(), p.typerep_down);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "String appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [];

  p.defs = emptyDefs() ;

  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), trueConst(terminal(True_kwd, "true"))) ;
  p.letAssigns_tree = [] ; 

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(boolTypeExp(), p.typerep_down);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Boolean appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
              else [];
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), falseConst(terminal(False_kwd, "false"))) ;
  p.letAssigns_tree = [] ;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = p.finalSubst;

  errCheck1.downSubst = p.downSubst;
  p.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(boolTypeExp(), p.typerep_down);
  p.errors <- if errCheck1.typeerror
              then [err(p.location, "Boolean appears, but the pattern matching is on type " ++ errCheck1.rightpp)]
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
  p.pp = v.name ;
  p.location = v.location;

-- TODO: need to autocopy down a boolean that says "should I be decorated or undecorated?"
-- Since if we're pattern matching on something that's undecorated, we probably shouldn't produce decorated results here
-- This is a huge corner case though, so meh.

  local attribute proto_type :: TypeExp; -- no pun intended
  proto_type = performSubstitution(p.typerep_down, p.downSubst);
  
  local attribute var_type :: TypeExp ;
  var_type = if proto_type.isDecorable
             then decoratedTypeExp(proto_type)
             else proto_type;
  
  -- Notice that var_type is Decorated. This means that
  -- foo(v)  produces a binding like
  -- let v :: Decorated Bar = ....
  
  -- lets now have auto-undec behavior so that we don't need to pointlessly 'new' things
  
  p.defs = addLexicalLocalDcl(p.grammarName, v.location, v.name, var_type, emptyDefs());

  p.errors :=
        if length(getValueDclAll(v.name, p.env)) > 1
        then [err(p.location, "Pattern variable '" ++ v.name ++ "' is already bound in this scope.")] 
        else [];

  p.cond_tree = trueConst('true') ;

  p.letAssigns_tree = [ assignExpr(v, '::', typerepType(var_type), '=', p.base_tree) ] ;

  p.upSubst = p.downSubst;
}


concrete production patternList_one
ps::PatternList ::= p::Pattern
{
  ps.pp = p.pp ;
  ps.errors := p.errors ;
  ps.defs = p.defs ;
  ps.location = p.location ;

  p.typerep_down = if ! null(ps.typereps_down) 
                   then head(ps.typereps_down) 
                   else errorType() ; 

  ps.errors <-
       if null(ps.typereps_down)
       then [err(ps.location, "Production call in pattern has too many arguments.")] 
       else if length(ps.typereps_down) > 1
       then [err(ps.location, "Production call in pattern has too few arguments.")]
       else [];

  p.base_tree = patternMatchRuntimeGetChild(ps.base_tree, ps.child_number, performSubstitution(p.typerep_down, ps.upSubst));

  ps.cond_tree = p.cond_tree ;
  ps.letAssigns_tree = p.letAssigns_tree ;
  
  p.downSubst = ps.downSubst;
  ps.upSubst = p.upSubst;
}

concrete production patternList_more
ps::PatternList ::= p::Pattern ',' ps1::PatternList
{
  ps.pp = ps1.pp ++ ", " ++ p.pp ;
  ps.errors := p.errors ++ ps1.errors ;
  ps.defs = appendDefs(ps1.defs, p.defs) ;
  ps.location = p.location ;

  p.typerep_down = if ! null(ps.typereps_down)
                   then head(ps.typereps_down) 
                   else errorType();
  ps1.typereps_down = if ! null(ps.typereps_down)
                      then tail(ps.typereps_down) 
                      else [] ;

  ps.errors <-
       if null(ps.typereps_down)
       then [err(ps.location, "Production call in pattern has too many arguments.")]
       else [];

  p.base_tree = patternMatchRuntimeGetChild(ps.base_tree, ps.child_number, performSubstitution(p.typerep_down, ps.upSubst));
  ps1.base_tree = ps.base_tree;
  ps1.child_number = ps.child_number + 1;

  ps.cond_tree = and(p.cond_tree, terminal(And_t, "&&"), ps1.cond_tree) ;
  ps.letAssigns_tree = p.letAssigns_tree ++ ps1.letAssigns_tree ;

  p.downSubst = ps.downSubst;
  ps1.downSubst = p.upSubst;
  ps.upSubst = ps1.upSubst;
}


concrete production PatternList_nil
ps::PatternList ::= 
{
  ps.pp = "" ;
  ps.errors := [] ;
  ps.defs = emptyDefs() ; 
  ps.location = loc("PatternList_nill", -1, -1) ;

  ps.errors <- if null(ps.typereps_down)
               then []
               else [err(ps.location, "Expecting more arguments for production call pattern.")] ;

  ps.cond_tree = trueConst(terminal(True_kwd, "true")) ;
  ps.letAssigns_tree = [] ; 

  ps.upSubst = ps.downSubst;
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

