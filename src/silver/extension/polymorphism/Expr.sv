grammar silver:extension:polymorphism;

import core;
import silver:base;
import silver:analysis:typechecking;


-----------------------------
-- Polymorphic Expressions
-----------------------------

concrete production plmApp
e::Expr ::= q::QName args::TypeArgs l::LParen_t es::Exprs r::RParen_t
{
  e.pp = q.pp ++ args.pp ++ "(" ++ es.pp ++ ")"; 
  e.location = q.location;
  
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(q.name, e.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDclOne(fName, e.env);

  production attribute prods :: [Decorated EnvItem];
  prods = getPlmProdDcl(fName, e.env);

  production attribute type :: Decorated TypeRep;
  type = if !null(prods) && head(prods).arity == args.size
	 then typeApplication(head(prods).typerep, args.typereps)
	 else topTypeRep() ;

  production attribute psMatch :: Boolean ;
  psMatch = genListEquals(type.inputTypes, es.inputTypes);

  local attribute er1 :: String ;
  er1 = if null(vals)
 	then e.location ++ "Error: Value '" ++ q.name ++ "' is not declared.\n" 
	else "";

  local attribute er2 :: String ;
  er2 = if null(vals)
	then ""
	else if head(vals).typerep.isProduction
	then ""
	else e.location ++ "Error: Value '" ++ q.name ++ "' is not a production or function.\n";

  local attribute er3 :: String ;
  er3 = if null(prods)
	then e.location ++ "Error: '" ++ q.name ++ "' is not a polymorphic production or function.\n"
	else "";

  local attribute er4 :: String ;
  er4 = if !null(prods) && head(prods).arity != args.size
	then e.location ++ "Error: Incorrect number of type arguments for production or function'" ++ q.name ++ "'.\n"
	else "";

  local attribute er5 :: String;
  er5 = if psMatch then ""
	else e.location ++ "Error: Parameters given do not match the signature of the production or function.\n" ++
		 	"(expected)\n   " ++ printTypes(type.inputTypes) ++ "(actual)\n   " ++ printTypes(es.inputTypes) ++ "\n";


  e.errors = er1 ++ er2 ++ es.errors ;
  e.typeErrors = er3 ++ er4 ++ er5 ++ es.typeErrors ;

  es.expectedInputTypes = type.inputTypes;


  forwards to type.plmAppDispatcher(q'', args'', es'');
}


-- polymorphic production calls
abstract production plmProdApp
e::Expr ::= q::QName args::TypeArgs es::Exprs
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(q.name, e.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDclOne(fName, e.env);

  production attribute prods :: [Decorated EnvItem];
  prods = getPlmProdDcl(fName, e.env);

  production attribute type :: Decorated TypeRep;
  type = if !null(prods) && head(prods).arity == args.size
	 then typeApplication(head(prods).typerep, args.typereps)
	 else topTypeRep() ;

  e.typerep = if type.isProduction 
	      then type.outputType
	      else topTypeRep();

  
  -- translation to base, add cast to AnyType for production arguments if necessary
  local attribute esRw :: Exprs ;
  esRw = rewriteExprsWithCast(es'', 
		es.inputTypes, 
		dequantifyType(head(prods).typerep).inputTypes);
  
  forwards to productionApp(productionReference(q''), 
		terminal(LParen_t, "("), 
		esRw'', 
		terminal(RParen_t, ")"));
--	with {env = convertToMonoEnv(e.env);
--	      globalEnv = convertToMonoEnv(e.globalEnv);
--	      localsEnv = convertToMonoEnv(e.localsEnv);
--	      signatureEnv = convertToMonoEnv(e.signatureEnv);} ;
}

-- polymorphic production calls without arguments
concrete production plmAppEmpty
e::Expr ::= q::QName args::TypeArgs l::LParen_t r::RParen_t
{
  forwards to plmApp(q'', args'', l, exprsEmpty(), r);
}


-- polymorphic attribute access
concrete production plmAttrAccess
e::Expr ::= e1::Expr d::Dot_t q::QName args::TypeArgs
{
  e.pp = e1.pp ++ "." ++ q.pp ++ args.pp ;
  e.location = "[line: " ++ toString(d.line) ++ ", column: " ++ toString(d.column) ++ "] ";


  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(q.name, e.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute vals :: [Decorated EnvItem] ;
  vals = getPlmAttrDcl(fName, e.env);

  production attribute ntType :: Decorated TypeRep;
  ntType = if e1.typerep.isDecorated
	   then e1.typerep.decoratedType
	   else e1.typerep;

  production attribute occursOn :: Boolean;
  occursOn = if !null(fNames) && ntType.isConstructedType
      	     then plmDoesOccurOn(fName, args.typereps, ntType, e.env)
             else false;	

  production attribute type :: Decorated TypeRep ;
  type = if !null(vals) then head(vals).typerep else topTypeRep();

  e.typerep = if !null(vals) && head(vals).arity == args.size
	      then typeApplication(type, args.typereps)
	      else topTypeRep();

  local attribute er1 :: String ;
  er1 = if null(fNames) || null(vals) 
	then e.location ++ "Error: Polymorphic attribute '" ++ q.name ++ "' is not declared.\n"
	else "";


  local attribute er2 :: String ;
  er2 = if !occursOn
	then e.location ++ "Error: Attribute '" ++ q.name ++ args.pp ++ "' does not"
		++ " decorate type '" ++ ntType.pp ++ "'.\n"
	else "";

  local attribute er3 :: String ;
  er3 = if !null(vals) && head(vals).arity != args.size
	then e.location ++ "Error: Incorrect number of type arguments for polymorphic"
		++ " attribute'" ++ q.name ++ "'.\n"
	else "";

  e.errors = er1 ;
  e.typeErrors = er2 ++ er3 ;


  -- translation to base, and cast out of AnyType if resolve to a specific type
  local attribute exprRw :: Expr ;
  exprRw = if dequantifyType(type).isTypeVar && !e.typerep.isTypeVar
	   then cast_t(terminal(Cast_kwd, ""), 
		       attributeAccess(e1'', d, q''),
		       e.typerep)
	   else attributeAccess(e1'', d, q'');

  forwards to exprRw'' 
	with {env = convertToMonoEnv(e.env);
	      globalEnv = convertToMonoEnv(e.globalEnv);
	      localsEnv = convertToMonoEnv(e.localsEnv);
	      signatureEnv = convertToMonoEnv(e.signatureEnv);} ;
}


-------------------------------------
-- Auxilliary functions
-------------------------------------

function rewriteExprsWithCast
Exprs ::= es::Exprs ts1::[Decorated TypeRep] ts2::[Decorated TypeRep]
{
  return case (es) of
		exprsEmpty() => es''     |
		exprsSingle(x) => 
			if head(ts2).isTypeVar && !(head(ts1).isTypeVar)
			then exprsSingle(
				cast_t(terminal(Cast_kwd, ""), 
				       new(x),
				       anyTypeTypeRep()))
			else es''        |
		exprsCons(h, _, t) =>  
			if head(ts2).isTypeVar && ! (head(ts1).isTypeVar)
			then exprsCons(cast_t(terminal(Cast_kwd, ""),
					      new(h),
					      anyTypeTypeRep()),
				       terminal(Comma_t, ""),
				       rewriteExprsWithCast(new(t), tail(ts1), tail(ts2)))
			else exprsCons(new(h), terminal(Comma_t, ""),
				rewriteExprsWithCast(new(t), tail(ts1), tail(ts2)))
	 end;
}
