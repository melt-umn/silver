grammar silver:extension:polymorphism:functions;

import core;
import silver:base;
import silver:analysis:typechecking;
import silver:extension:functions;
import silver:extension:polymorphism;
import silver:extension:list;


concrete production plmFnDclParams
 d::AGDcl ::=
   f::Function_kwd id::Id_t ps::TypeParams
   s::FunctionSignature body::ProductionBody 
{
  d.location = "[line: " ++ toString(f.line) ++ ", column: " ++ toString(f.column) ++ "] ";  

  production attribute fName :: String;
  fName = d.grammarName.name ++ ":" ++ id.lexeme;
  
  production attribute ns :: ProductionSignature;
  ns = s.namedsignature;
  ns.env = appendDefsEnv(ps.defs, d.env);
  ns.grammarName = d.grammarName;
  s.pname = id.lexeme;

  local attribute type :: Decorated TypeRep ;
  type = quantifyType(ps.prodVars, functionTypeRep(id.lexeme, 
		toTypeRepsWithProdVars(tail(ns.sigTypes)), 
		toTypeRepWithProdVars(head(ns.sigTypes))));

  d.defs = addValueDcl(fName, toMonoTypeRep(type), 
	     addPlmProdDcl(fName, ps.size, head(ns.sigNames), tail(ns.sigNames), type, 
	     addFullNameDcl(id.lexeme, fName,
	     addProdAttsDcl(fName, body.prodDefs, extraDefs))));

  d.globalDefs = addValueDcl(fName, toMonoTypeRep(type),
	         addPlmProdDcl(fName, ps.size, head(ns.sigNames), tail(ns.sigNames), type, 
	         extraGlobalDefs)); 
	     
  local attribute er1 :: String;
  er1 = if length(getFullNameDcl(id.lexeme, d.env)) > 1
       then d.location ++ "Error: Name '" ++ id.lexeme ++ "' is already bound.\n"
       else "";	

  local attribute er2 :: String;
  er2 = if length(getValueDcl(fName, d.env)) > 1
       then d.location ++ "Error: Value '" ++ fName ++ "' is already bound.\n"
       else "";	

  d.errors = er1 ++ er2 ++ ps.errors ++ ns.errors ++ body.errors;
  d.typeErrors = body.typeErrors ;
 

  body.env = appendDefsEnv(
	appendDefs(ps.defs, 
		appendDefs(body.defs, 
			appendDefs(ns.defs, addThisDcl(fName, emptyDefs())))), 
	d.env);

  body.signatureEnv = toEnv(ns.defs);
  body.localsEnv = toEnv(body.defs);

  body.signatureNames = ns.sigNames;
  body.signatureTypes = ns.sigTypes;
  body.inPlmProd = true;
  
  ----------------------------------------------------
  local attribute ntdcl :: AGDcl;
  ntdcl = nonterminalDcl(terminal(NonTerminal_kwd, "nonterminal", f.line, f.column), 
	terminal(Id_t, "GENERATED__" ++ id.lexeme), terminal(Semi_t, ";"));


--  local attribute pdcl :: AGDcl;
--  pdcl = aFunctionDcl(id, s.monoFnSig, convertToMonoBody(body''));

  local attribute adcl :: AGDcl;
  adcl = attributeDclSyn(
			terminal(Synthesized_kwd, "synthesized", f.line, f.column),
			terminal(Attribute_kwd, "attribute"),
			terminal(Id_t, "generated__" ++ id.lexeme),
			terminal(HasType_t, "::"),
			s.lhstype,
			terminal(Semi_t, ";")
			);

  local attribute odcl :: AGDcl;
  odcl = attributionDcl(
			terminal(Attribute_kwd, "attribute", f.line, f.column),
			qNameId(terminal(Id_t, "generated__" ++ id.lexeme)),
			terminal(Occurs_kwd, "occurs"),
			terminal(On_kwd, "on"),
			qNameId(terminal(Id_t, "GENERATED__" ++ id.lexeme)),
			terminal(Semi_t, ";")
			);


  local attribute newDcl :: Decorated AGDcl;
  newDcl = decorate agDclSeq(adcl'', agDclSeq(ntdcl'', odcl''))
	with {env = d.env;
	      globalEnv = d.globalEnv; 
  	      grammarName = d.grammarName; };
  
  production attribute extraDefs :: Decorated Defs;
  extraDefs = newDcl.defs;

  production attribute extraGlobalDefs :: Decorated Defs;
  extraGlobalDefs = newDcl.globalDefs;

   forwards to agDclSeq(adcl'', agDclSeq(ntdcl'', agDclSeq(odcl'', 
		productionDcl(
			terminal(Abstract_kwd, "abstract", id.line, id.column),
		        terminal(Production_kwd, "production"),
 		        id,
			ns'',
			body''))))
	with {env = d.env;
	      globalEnv = d.globalEnv;} ;  
 
}




----------------------------------------------------------------- 

autocopy attribute inPlmProd :: Boolean;
attribute inPlmProd occurs on ProductionBody, ProductionStmts, ProductionStmt;


aspect production productionDcl
top::AGDcl ::=
   a::Abstract_kwd p::Production_kwd id::Id_t ns::ProductionSignature
   body::ProductionBody
{
  body.inPlmProd = false;
}

concrete production plmReturnDef
d::ProductionStmt ::= r::Return_kwd e1::Expr s::Semi_t
{
  forwards to if d.inPlmProd then absPlmReturnDef(e1) else returnDef(r, e1, s); 
}


abstract production absPlmReturnDef
d::ProductionStmt ::= e1::Expr
{
  local attribute n :: String;
  n = head(getThisDcl(d.env)).itemName;

  local attribute p :: [Decorated EnvItem] ;
  p = getPlmProdDcl(n, d.env);

  local attribute rType :: Decorated TypeRep;
  rType = head(p).typerep.outputType;

  local attribute er1 :: String;
  er1 = if e1.typerep.typeEquals(e1.typerep, rType).bValue
	then ""
	else e1.location ++ "Error: incorrect function return type:\n" ++
		"(expected) " ++  rType.pp ++ " <-> " ++ "(actual) " ++ 
		e1.typerep.pp ++ "\n";

  d.errors = e1.errors;
  d.typeErrors = er1 ++ e1.typeErrors;

  e.expected = expected_type(rType);
  e1.resolvedType = emptyListTypeRep();

  forwards to returnDef(terminal(Return_kwd, ""), 
			e1, 
			terminal(Semi_t, ""));
}

aspect production functionApplicationDispatcher
d::Expr ::= e::Expr es::Exprs
{
  d.pp = e.pp ++ "(" ++ es.pp ++ ")";

  d.errors = e.errors ++ es.errors;
  -- d.typerep = e.typerep.outputType;

  
  local attribute list_error :: String;
  list_error = if  genListEquals(e.typerep.inputTypes, es.inputTypes) then ""
	       else d.location ++ "Error: Parameters given do not match the signature of the function.\n" ++
		 	"(expected)\n   " ++ printTypes(e.typerep.inputTypes) ++ "(actual)\n   " ++ printTypes(es.inputTypes) ++ "\n";
  
  d.typeErrors = list_error ++ e.typeErrors ++ es.typeErrors;

  es.expectedInputTypes = e.typerep.inputTypes;
}


abstract production plmFnApp
e::Expr ::= q::QName args::TypeArgs es::Exprs
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDclOne(q.name, e.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else "FULL_NAME_NOT_DEFINED";

  production attribute prods :: [Decorated EnvItem];
  prods = getPlmProdDcl(fName, e.env);

  local attribute esRw :: Exprs ;
  esRw = rewriteExprsWithCast(es'', 
		es.inputTypes, 
		dequantifyType(head(prods).typerep).inputTypes);
  
  forwards to functionApplicationDispatcher(productionReference(q''),  
		esRw'');
--	with {env = convertToMonoEnv(e.env);
--	      globalEnv = convertToMonoEnv(e.globalEnv);
--	      localsEnv = convertToMonoEnv(e.localsEnv);
--	      signatureEnv = convertToMonoEnv(e.signatureEnv);} ;
}

aspect production internal_functionTypeRep
top::TypeRep ::= fn::String it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.plmAppDispatcher = plmFnApp;
}
