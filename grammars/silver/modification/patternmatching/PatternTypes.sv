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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
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
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_more(hp,',',patternList_one(tp)) with {}; -- TODO hahahahahahahahahahaha
  -- I can't wait to run into a bug and come find this sitting here someday!!
}


