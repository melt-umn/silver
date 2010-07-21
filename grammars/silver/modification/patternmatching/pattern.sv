grammar silver:modification:patternmatching;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type:anytype;
import silver:definition:type:io;
import silver:analysis:typechecking:core;
import silver:modification:let_fix;
import silver:extension:list;

terminal Case_kwd 'case' lexer classes {KEYWORD};
terminal Of_kwd 'of' lexer classes {KEYWORD};
terminal arrow_kwd '->' precedence = 7 ;
terminal vbar_kwd '|' precedence = 3 ;

inherited attribute typerep_down :: Decorated TypeRep ;
inherited attribute typereps_down :: [ Decorated TypeRep ] ;
inherited attribute base_tree :: Expr ;
inherited attribute case_expr_type :: Decorated TypeRep ;

synthesized attribute translation_tree :: Expr ;
synthesized attribute cond_tree :: Expr ;
synthesized attribute then_tree :: Expr ;
synthesized attribute letAssigns_tree :: [ AssignExpr ] ;

nonterminal MRuleList with pp, grammarName, env, typerep, 
			    errors, typerep_down, typeErrors, location,
			    translation_tree, base_tree, file, case_expr_type ;
nonterminal MatchRule with pp, grammarName, env, typerep, 
                            errors, typerep_down, typeErrors, location,
			    cond_tree, then_tree, base_tree, file, case_expr_type ;
nonterminal Pattern with pp, grammarName, env, location, errors, defs,
			    typerep_down, typeErrors, cond_tree,
                            letAssigns_tree, base_tree, file, case_expr_type ;
nonterminal PatternList with pp, grammarName, env, errors, defs, 
			    typereps_down, typeErrors, location,
			    cond_tree, letAssigns_tree, base_tree, file, case_expr_type ;


concrete production caseExpr_c
top::Expr ::= 'case' e1::Expr 'of' ml::MRuleList 'end'
{ 
  top.pp = "case " ++ e1.pp ++ " of " ++ ml.pp ;  
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := e1.errors ++ ml.errors ;  

  -- type checking
  top.typerep = ml.typerep ; 


  ml.typerep_down = if e1.typerep.isDecorated
 		    then e1.typerep.decoratedType
		    else if e1.typerep.isNonTerminal
		    then e1.typerep
		    else topTypeRep() ;

  ml.case_expr_type = e1.typerep ;

  top.typeErrors = if e1.typerep.isDecorated || e1.typerep.isNonTerminal
		 then e1.typeErrors ++ ml.typeErrors 
		 else [err(top.location, "Only nonterminal typed expressions can be pattern matched.")]
			++ e1.typeErrors ++ ml.typeErrors ;

  -- translation
  ml.base_tree = if e1.typerep.isDecorated 
                 then e1 
                 else decorateExprWithEmpty(terminal(Decorate_kwd, "decorate", $1.line, $1.column),
 	     	               e1, 
	     	               terminal(With_kwd, "with", $1.line, $1.column),
	     	               terminal(LCurly_t, "{", $1.line, $1.column),
		               terminal(RCurly_t, "}", $1.line, $1.column));

  e1.expected = expected_default(); -- TODO: should be undecorated? Err... Should be decorated?
  forwards to ml.translation_tree ;
}

concrete production mRuleList_one
ml::MRuleList ::= m::MatchRule
{
  ml.pp = m.pp ;
  ml.errors := m.errors ;
  ml.location = m.location ;

  -- type checking
  ml.typerep = m.typerep;
  m.typerep_down = ml.typerep_down ;

  m.case_expr_type = ml.case_expr_type ;

  ml.typeErrors = m.typeErrors ;

  -- translation
  m.base_tree = ml.base_tree ;
  ml.translation_tree = ifThenElse('if', m.cond_tree, 'then', m.then_tree, 'else',
				   errorFunction('error', '(', stringConst(terminal(String_t, "\"Error: pattern match failed.\\n\"")), ')')) ;
}

concrete production mRuleList_cons
ml::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  ml.pp = h.pp ++ " | " ++ t.pp ;
  ml.errors := h.errors ++ t.errors ;
  ml.location = h.location ;  

  -- type checking
  ml.typerep = h.typerep;
  h.typerep_down = ml.typerep_down ;
  t.typerep_down = ml.typerep_down ;

  h.case_expr_type = ml.case_expr_type ;
  t.case_expr_type = ml.case_expr_type ;

  local attribute tr :: [Decorated Message] ;
  tr = if h.typerep.typeEquals(h.typerep, t.typerep).bValue
       then [] 
       else [err(h.location, "Rules of CASE expression do not have the same type.")] ;  -- TODO: print the types!
 
  ml.typeErrors = h.typeErrors ++ t.typeErrors ++ tr ;

  -- translation
  h.base_tree = ml.base_tree ;
  t.base_tree = ml.base_tree ;

  ml.translation_tree = ifThenElse(terminal(If_kwd, "if"),
 				   h.cond_tree,
				   terminal(Then_kwd, "then"),
				   h.then_tree,
 				   terminal(Else_kwd, "else"),
				   t.translation_tree) ;
}

concrete production matchRule
mr::MatchRule ::= pt::Pattern '->' e::Expr
{
  mr.pp = pt.pp ++ " => " ++ e.pp ;
  mr.errors := pt.errors ++ e.errors ;
  mr.location = e.location ;  

  -- environments
  local attribute newEnv::Decorated Env;
  newEnv = newScopeEnv(pt.defs, mr.env); 

  e.env = newEnv;
  pt.env = newEnv;

  -- type checking
  e.expected = expected_default(); -- TODO: why is this here?
  mr.typerep = e.typerep ;
  pt.typerep_down = mr.typerep_down ;

  pt.case_expr_type = mr.case_expr_type ;

  mr.typeErrors = pt.typeErrors ++ e.typeErrors ;

  -- translation
  mr.cond_tree = pt.cond_tree ;
  mr.then_tree = if null(pt.letAssigns_tree) then e
		 else letp(terminal(Let_kwd, "let"), 
                           toAssigns(pt.letAssigns_tree),
       		           terminal(In_kwd, "in"),
                           e,
                           terminal(End_kwd, "end")) ;

  pt.base_tree = mr.base_tree;
}


concrete production patternList_one
ps::PatternList ::= p::Pattern
{
  ps.pp = p.pp ;
  ps.errors := p.errors ;
  ps.defs = p.defs ;
  ps.location = p.location ;

  -- type checking
  p.typerep_down = if ! null(ps.typereps_down) 
                   then head(ps.typereps_down) 
                   else topTypeRep() ; 

  p.case_expr_type = ps.case_expr_type ;
  

  local attribute tr :: [Decorated Message] ;
  tr = if null(ps.typereps_down)
       then [err(ps.location, "Production call in pattern has too many arguments.")] 
       else if length(ps.typereps_down) > 1
	    then [err(ps.location, "Production call in pattern has too few arguments.")]
	    else [];

  ps.typeErrors = tr ++ p.typeErrors ;

  -- The type of the element in the child list is the decorated version of the rhs children.
  local attribute cType :: Decorated TypeRep;
  cType = if head(ps.typereps_down).isDecorated || !head(ps.typereps_down).isNonTerminal 
          then head(ps.typereps_down)          
	  else refTypeRep(head(ps.typereps_down)) ;

  p.base_tree = cast_t(headList(terminal(Head_t, "head"), terminal(LParen_t, "("), ps.base_tree, terminal(RParen_t, ")")), cType);

  ps.cond_tree = p.cond_tree ;
  ps.letAssigns_tree = p.letAssigns_tree ;
}

concrete production patternList_more
ps::PatternList ::= p::Pattern ',' ps1::PatternList
{
  ps.pp = ps1.pp ++ ", " ++ p.pp ;
  ps.errors := ps1.errors ++ p.errors ;
  ps.defs = appendDefs(ps1.defs, p.defs) ;
  ps.location = p.location ;

  -- type checking
  p.typerep_down = if ! null(ps.typereps_down)
                   then head(ps.typereps_down) 
                   else topTypeRep();
  ps1.typereps_down = if ! null(ps.typereps_down)
                      then tail(ps.typereps_down) 
                      else [] ;

  p.case_expr_type = ps.case_expr_type ;
  ps1.case_expr_type = ps.case_expr_type ;


  local attribute tr :: [Decorated Message];
  tr = if null(ps.typereps_down)
       then [err(ps.location, "production call in pattern has too many arguments.")]
       else [];

  ps.typeErrors = tr ++ p.typeErrors ++ ps1.typeErrors ;

  local attribute cType :: Decorated TypeRep;
  cType = if head(ps.typereps_down).isDecorated || !head(ps.typereps_down).isNonTerminal 
          then head(ps.typereps_down)
	  else refTypeRep(head(ps.typereps_down)) ;

  local attribute orig_base_tree :: Expr ;
  orig_base_tree = cast_t(headList(terminal(Head_t, "head"), terminal(LParen_t, "("), ps.base_tree, terminal(RParen_t, ")")), cType);

  p.base_tree = orig_base_tree ;

  ps1.base_tree = tailList(terminal(Tail_t, "tail"), terminal(LParen_t, "("), ps.base_tree, terminal(RParen_t, ")"));

  ps.cond_tree = and(p.cond_tree, terminal(And_t, "&&"), ps1.cond_tree) ;
  ps.letAssigns_tree = p.letAssigns_tree ++ ps1.letAssigns_tree ;

}


concrete production PatternList_nil
ps::PatternList ::= 
{
  ps.pp = "" ;
  ps.errors := [] ;
  ps.defs = emptyDefs() ; 
  ps.location = loc("PatternList_nill", -1, -1) ;

  -- type checking
  ps.typeErrors = if null(ps.typereps_down) then []
                  else [err(ps.location, "Expecting more arguments for production call pattern.")] ;

  -- translation
  ps.cond_tree = trueConst(terminal(True_kwd, "true")) ;
  ps.letAssigns_tree = [] ; 
}

concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;
  p.location = prod.location;

  p.defs = ps.defs ;
  p.errors := prod.lookupValue.errors ++ ps.errors ; 

  local attribute er2 :: [Decorated Message] ;
  er2 = if prod.lookupValue.typerep.isProduction 
        then (if prod.lookupValue.typerep.outputType.typeEquals(prod.lookupValue.typerep.outputType, p.typerep_down).bValue
              then  []
              else [err(prod.location, "Production '" ++ prod.name ++ "' has incorrect output type.\n" ++ 
		   "(pattern) " ++ prod.lookupValue.typerep.outputType.unparse ++ " <-> (expected) " ++ 
		   p.typerep_down.unparse)])
        else [err(prod.location, "'" ++ prod.name ++ "' is not a production name.")] ;


  ps.typereps_down  =  if prod.lookupValue.typerep.isProduction 
		       then prod.lookupValue.typerep.inputTypes 
		       else [] ;
  
  ps.case_expr_type = p.case_expr_type ;

  p.typeErrors = ps.typeErrors ++ er2 ;

  -- translation
  ps.base_tree = attributeAccess(p.base_tree, 
				 terminal(Dot_t, "."),
				 qNameId(nameId(terminal(Id_t, "patChildList")))) ;
 
  p.cond_tree = and(eqeq(attributeAccess(p.base_tree, 
				         terminal(Dot_t, "."), 
				         qNameId(nameId(terminal(Id_t, "patProdName")))),
		         terminal(EQEQ_t, "=="),
      		         stringConst(terminal(String_t, "\"" ++ prod.lookupValue.fullName ++ "\""))),
		    terminal(And_t, "&&"),
		    ps.cond_tree) ;

  p.letAssigns_tree = ps.letAssigns_tree ;		     
} 

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme ;
  p.location = loc(p.file, num.line, num.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.typeErrors = if p.typerep_down.typeEquals(p.typerep_down, integerTypeRep()).bValue
                 then []
                 else [err(p.location, "Expecting '" ++ p.typerep_down.typeName ++ "' type.")] ;

  -- translation
  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), intConst(num)) ;
  p.letAssigns_tree = [] ; 
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  p.location = loc(p.file, str.line, str.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.typeErrors = if p.typerep_down.typeEquals(p.typerep_down, stringTypeRep()).bValue
                 then []
                 else [err(p.location, "Expecting '" ++ p.typerep_down.typeName ++ "' type.")] ;

  -- translation
  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), stringConst(str)) ;
  p.letAssigns_tree = [] ;
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [];

  p.defs = emptyDefs() ;

  p.typeErrors = if p.typerep_down.typeEquals(p.typerep_down, booleanTypeRep()).bValue
                 then []
                 else [err(p.location, "Expecting '" ++ p.typerep_down.typeName ++ "' type.")] ;

  -- translation
  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), trueConst(terminal(True_kwd, "true"))) ;
  p.letAssigns_tree = [] ; 
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.typeErrors = if p.typerep_down.typeEquals(p.typerep_down, booleanTypeRep()).bValue
                 then []
                 else [err(p.location, "Expecting '" ++ p.typerep_down.typeName ++ "' type.")] ;

  -- translation
  p.cond_tree = eqeq(p.base_tree, terminal(EQEQ_t, "=="), falseConst(terminal(False_kwd, "false"))) ;
  p.letAssigns_tree = [] ;
}

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_" ;
  p.location = loc(p.file, $1.line, $1.column);
  p.errors := [] ;

  p.defs = emptyDefs();

  p.typeErrors = [];

  -- translation
  p.cond_tree = trueConst(terminal(True_kwd, "true")) ;
  p.letAssigns_tree = [] ;
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name ;
  p.location = v.location;
  p.errors := er ;

  local attribute var_type :: Decorated TypeRep ;
  var_type =  if   p.case_expr_type.isNonTerminal
                   then p.typerep_down   -- leave NTs as NTs, DecNTs as DecNTs, primitives as primitives 
                   else -- case expr is decorated - so NTs should be decorated
                        (if p.typerep_down.isDecorated || ! p.typerep_down.isNonTerminal
 	                 then p.typerep_down
	                 else refTypeRep(p.typerep_down) );
              
  p.defs = addLocalDcl(p.grammarName, v.location, v.name, var_type, emptyDefs());

  local attribute er :: [Decorated Message] ;
  er = if length(getValueDclAll(v.name, p.env)) > 1
        then [err(p.location, "Pattern variable '" ++ v.name ++ "' is already bound in this scope.")] 
        else [];


  p.typeErrors = [];

  local attribute btree :: Expr ; 
  btree = if p.case_expr_type.isNonTerminal && p.typerep_down.isNonTerminal
                     then newFunction('new', '(', p.base_tree, ')')
                     else p.base_tree;
                  

  -- translation
  p.cond_tree = trueConst('true') ;

  p.letAssigns_tree = [ assignExpr(v, '::', typerepType(var_type), '=', btree) ] ;

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


----------------------------------------------------
-- Aspects
----------------------------------------------------

inherited attribute prodName :: Name ;
attribute prodName occurs on ProductionBody ;

inherited attribute lhsName_down :: QName ;
attribute lhsName_down occurs on ProductionBody ;

inherited attribute rhsListExpr :: Expr ; 
attribute rhsListExpr occurs on ProductionBody ;

synthesized attribute lhsName_up :: QName ;
attribute lhsName_up occurs on ProductionSignature, ProductionLHS;

synthesized attribute genRhsListExpr :: Expr ;
attribute genRhsListExpr occurs on ProductionSignature, ProductionRHS, ProductionRHSElem ;

synthesized attribute rhsAnyType :: Boolean ;
attribute rhsAnyType occurs on ProductionRHSElem ;

synthesized attribute rhsTypeAllowed :: Boolean ;
attribute rhsTypeAllowed occurs on ProductionSignature, ProductionRHS, ProductionRHSElem ;

inherited attribute isAspect :: Boolean ;
attribute isAspect occurs on ProductionBody ;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
   body.prodName = id ;
   body.lhsName_down = ns.lhsName_up ;
   body.rhsListExpr = ns.genRhsListExpr ;
   body.isAspect = false ;
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature  body::ProductionBody
{
   body.isAspect = true ;
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
   body.isAspect = true ;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature  body::ProductionBody
{
   body.isAspect = true ;
}

aspect production productionSignatureEmptyRHS
top::ProductionSignature ::= lhs::ProductionLHS '::='
{
   top.lhsName_up = lhs.lhsName_up ;
   top.genRhsListExpr = emptyListWType('[', '::', anyType('AnyType'), ']');
}

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
   top.lhsName_up = lhs.lhsName_up;

--TODO
   top.genRhsListExpr = if ! rhs.rhsTypeAllowed 
			then errorFunction(terminal(Error_kwd, "error"),
					   terminal(LParen_t, "("),
				 	   stringConst(terminal(String_t, "\"Error: cannot pattern match on production or IO types.\\n\"")),
					   terminal(RParen_t, ")"))
			else rhs.genRhsListExpr ;
}

aspect production productionLHS
top::ProductionLHS ::=  id::Name '::' t::Type
{
   top.lhsName_up = qNameId(id) ;
}


aspect production productionRHSSingle
top::ProductionRHS ::= rhs::ProductionRHSElem
{

  top.genRhsListExpr = consList(terminal(Cons_t, "cons"),
                   terminal(LParen_t, "("),                   
                   rhs.genRhsListExpr,  terminal(Comma_t,","), emptyList('[',']'),
                   terminal(RParen_t, ")")
                  ) ;


   top.rhsTypeAllowed = rhs.rhsTypeAllowed ;
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{

   top.genRhsListExpr =  consList(terminal(Cons_t, "cons"),
                   terminal(LParen_t, "("),                   
                   h.genRhsListExpr,  terminal(Comma_t,","), t.genRhsListExpr,
                   terminal(RParen_t, ")")
                  ) ;

   top.rhsTypeAllowed = h.rhsTypeAllowed && t.rhsTypeAllowed ;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
   top.genRhsListExpr = if t.typerep.isAnyType 
			then baseExpr(qNameId(id)) 
			else cast_t(baseExpr(qNameId(id)), anyTypeTypeRep());    

   top.rhsAnyType = if t.typerep.isAnyType then true else false ;
   top.rhsTypeAllowed = if t.typerep.isProduction || t.typerep.isIO
			then false
			else true ;   
}


aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
   local attribute stmt1 :: ProductionStmt ;
   stmt1 = attributeDef(
	concreteDefLHS(top.lhsName_down), terminal(Dot_t, "."), qNameId(nameId(terminal(Id_t, "patProdName"))), '=',
        stringConst(terminal(String_t, "\"" ++ top.signature.fullName ++ "\"")), ';');
	

   local attribute stmt2 :: ProductionStmt ;
   stmt2 = attributeDef(
       	concreteDefLHS(top.lhsName_down), terminal(Dot_t, "."), qNameId(nameId(terminal(Id_t, "patChildList"))), '=',
        top.rhsListExpr, ';');

   local attribute addedStmts :: ProductionStmts ;
   addedStmts = if top.isAspect then productionStmtsNone() else
		productionStmtsCons(stmt1, productionStmts(stmt2)) ;

   extraStmts <- addedStmts ;
}

aspect production nonterminalDcl
top::AGDcl ::= 'nonterminal' id::Name ';'
{
   local attribute occurs1 :: AGDcl ;
   occurs1 = attributionDcl('attribute',
			    qNameId(nameId(terminal(Id_t, "patProdName"))),
			    terminal(Occurs_kwd, "occurs"),
			    terminal(On_kwd, "on"),
			    qNameId(id),
			    terminal(Semi_t, ";")) ;

   local attribute occurs2 :: AGDcl ;
   occurs2 = attributionDcl(terminal(Attribute_kwd, "attribute"),
			    qNameId(nameId(terminal(Id_t, "patChildList"))),
			    terminal(Occurs_kwd, "occurs"),
			    terminal(On_kwd, "on"),
			    qNameId(id),
			    terminal(Semi_t, ";")) ;
			    
   extraDcls <- occurs1;
   extraDcls <- occurs2 ;
} 

