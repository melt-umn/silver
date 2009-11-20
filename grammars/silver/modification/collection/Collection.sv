grammar silver:modification:collection;
import silver:definition:core;
import silver:definition:env;

nonterminal NameOrBOperator with location, grammarName, file, warnings, errors, env, defs, pp, operation;
nonterminal BOperator with location, grammarName, file, warnings, errors, env, defs, pp, operation;

synthesized attribute operation :: Operation;
nonterminal Operation with unparse;


concrete production nameOperator
top::NameOrBOperator ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.operation = nameOperation(fn); 

  local attribute fns :: [Decorated EnvItem];
  fns = getFullNameDcl(q.name, top.env);

  local attribute fn :: String;
  fn = if null(fns) then error(top.location.pp) else head(fns).fullName;
}

concrete production binaryOperator
top::NameOrBOperator ::= q::BOperator
{
  top.pp = q.pp;
  top.location = q.location;

  top.operation = q.operation;
}

concrete production bOperatorPlusPlus
top::BOperator ::= '++'
{
  top.pp = "++";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = plusPlusOperation();
}

abstract production nameOperation
top::Operation ::= s::String{
  top.unparse = "'" ++ s ++ "'";
}

abstract production noOperation
top::Operation ::= {
  top.unparse = "noOperation";
}

abstract production plusPlusOperation
top::Operation ::= {
  top.unparse = "++";
}

concrete production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, collectionTypeRep(q.operation, te.typerep), 
             addSynthesizedDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];

  forwards to attributeDclSyn($1, $2, a, $4, te, $8);
}

concrete production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "inherited attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addAttributeDcl(fName, collectionTypeRep(q.operation, te.typerep), 
             addInheritedDcl(fName, 
	     addFullNameDcl(a.name, fName,  emptyDefs())));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];

  forwards to attributeDclInh($1, $2, a, $4, te, $8);
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "production attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = toString(genInt()) ++ ":" ++ a.name;

  top.defs = addValueDcl(fName, collectionTypeRep(q.operation, te.typerep), 
	     addFullNameDcl(a.name, fName,  emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.name ++ "' is already bound.")]
        else [];

  local attribute er2 :: [Decorated Message];
  er2 = if length(getValueDclOne(fName, top.env)) > 1
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8);
}

terminal Contains_t   '<-'   lexer classes {KEYWORD};
concrete production attrContains
top::ProductionStmt ::= lhs::LHSExpr '<-' e::Expr ';'
{
  e.expected = expected_type(lhs.typerep);
  forwards to attributeDef(lhs, '=', e, ';');
}

terminal BaseContains_t   ':='   lexer classes {KEYWORD};
concrete production attrContainsBase
top::ProductionStmt ::= lhs::LHSExpr ':=' e::Expr ';'
{
  e.expected = expected_type(lhs.typerep);
  forwards to attributeDef(lhs, '=', e, ';');
}

synthesized attribute isCollection :: Boolean;
attribute isCollection occurs on TypeRep;

attribute operation occurs on TypeRep;

function collectionTypeRep
Decorated TypeRep ::= o::Operation t::Decorated TypeRep
{
  return decorate i_collectionTypeRep(o, t) with {}; 
}

abstract production i_collectionTypeRep
top::TypeRep ::= o::Operation t::Decorated TypeRep
{
  top.operation = o;
  top.isCollection = true;
  top.unparse = "collection(" ++ o.unparse ++ "," ++ t.unparse ++ ")";
  forwards to new(t);
}

aspect production i_defaultTypeRep
top::TypeRep ::= {
  top.isCollection = false;
  top.operation = noOperation();
}
