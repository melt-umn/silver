grammar silver:extension:polymorphism;

import core;
import silver:base;
import silver:analysis:typechecking;



-- local attribute declaration with polymorphic types
concrete production plmLocalAttrDcl
p::ProductionStmt ::= l::Local_kwd at::Attribute_kwd a::Id_t h::HasType_t 
	t::Type s::Semi_t
{
  p.pp = "local attribute " ++ a.lexeme ++ " :: " ++ t.pp ++ " ;" ;
  p.location = "[line: " ++ toString(l.line) ++ ", column: " ++ toString(l.column) ++ "] ";

  production attribute fName :: String;
  fName = a.lexeme;

  p.prodDefs = emptyDefs();

  p.defs = addValueDcl(fName, t.typerep,    ---toMonoTypeRep(t.typerep), 
	     addFullNameDcl(a.lexeme, fName,  emptyDefs()));

  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(fName, p.env)) > 1
       then p.location ++ "Error: Value '" ++ a.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getValueDcl(fName, p.env)) > 1
       then p.location ++ "Error: Value '" ++ fName ++ "' is already bound.\n"
       else "";	

  p.errors = er1 ++ er2 ++ t.errors;
  p.typeErrors = "";
  p.stmtList = [localDclStmtRep(a.lexeme, fName, toMonoTypeRep(t.typerep))];

  forwards to attributeDclLocal(l, at, a, h, toMonoType(t), s)
	with {env = convertToMonoEnv(p.env);
	      globalEnv = convertToMonoEnv(p.globalEnv);
	      localsEnv = convertToMonoEnv(p.localsEnv);
	      signatureEnv = convertToMonoEnv(p.signatureEnv);}  ; 
}

concrete production plmLhsExprTwo
e::LHSExpr ::= id::Id_t d::Dot_t q::QName args::TypeArgs
{
  e.pp = id.lexeme ++ " . " ++ q.pp ++ args.pp ;
  e.location = "[line: " ++ toString(d.line) ++ ", column: " ++ toString(id.column) ++ "] ";

  production attribute fNames1 :: [Decorated EnvItem];
  fNames1 = getFullNameDclOne(id.lexeme, e.env);

  production attribute fName1 :: String;
  fName1 = if !null(fNames1) then head(fNames1).fullName else "FULL_NAME_NOT_DEFINED";

  --whether it is bound to a value
  production attribute vals1 :: [Decorated EnvItem];
  vals1 = getValueDclOne(fName1, e.env);

  local attribute er1 :: String;
  er1 = if null(fNames1) || null(vals1)
	then e.location ++ "Error: Value '" ++ id.lexeme ++ "' is not declared.\n" 
  	else "";

  production attribute fNames2 :: [Decorated EnvItem];
  fNames2 = getFullNameDclOne(q.name, e.env);

  production attribute fName2 :: String;
  fName2 = if !null(fNames2) then head(fNames2).fullName else "FULL_NAME_NOT_DEFINED";

  --whether it is bound to a value
  production attribute vals2 :: [Decorated EnvItem];
  vals2 = getPlmAttrDcl(fName2, e.env);

  production attribute ntType :: Decorated TypeRep;
  ntType = if !null(vals1)
	   then (if head(vals1).typerep.isDecorated
		 then head(vals1).typerep.decoratedType
		 else head(vals1).typerep)
	   else topTypeRep();

  production attribute occursOn :: Boolean;
  occursOn = if !null(fNames2) && ntType.isConstructedType
      	     then plmDoesOccurOn(fName2, args.typereps, ntType, e.env)
             else false;	

  e.typerep = if !null(vals2) && head(vals2).arity == args.size 
	      then typeApplication(head(vals2).typerep, args.typereps)
	      else topTypeRep();


  local attribute er2 :: String;
  er2 = if null(fNames2) || null(vals2)
	then e.location ++ "Error: Polymorphic attribute '" ++ q.name ++ "' is not declared.\n" 
        else "";

  local attribute er3 :: String ;
  er3 = if !occursOn
	then e.location ++ "Error: Attribute '" ++ q.name ++ args.pp ++ "' does not"
		++ " decorate type '" ++ ntType.pp ++ "'.\n"
	else "";

  local attribute er4 :: String ;
  er4 = if !null(vals2) && head(vals2).arity != args.size
	then e.location ++ "Error: Incorrect number of type arguments for polymorphic"
		++ " attribute'" ++ q.name ++ "'.\n"
	else "";

  e.errors = er1 ++ er2 ++ args.errors;
  e.typeErrors = er3 ++ er4 ;


  -- translation to base, and cast out of AnyType if resolve to a specific type
  local attribute exprRw :: LHSExpr ;
  exprRw = lhsExprTwo(id, d, q);

  forwards to exprRw'' 
	with {env = convertToMonoEnv(e.env);
	      localsEnv = convertToMonoEnv(e.localsEnv);
	      signatureEnv = convertToMonoEnv(e.signatureEnv);} ;
}

