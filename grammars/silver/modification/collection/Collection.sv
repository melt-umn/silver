grammar silver:modification:collection;
import silver:definition:core;
import silver:definition:env;

nonterminal NameOrBOperator with location, grammarName, file, warnings, errors, env, pp, operation;
nonterminal BOperator with location, grammarName, file, warnings, errors, env, pp, operation;

synthesized attribute operation :: Operation;
nonterminal Operation with unparse;


concrete production nameOperator
top::NameOrBOperator ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.operation = nameOperation(q.lookupValue.fullName); 

  top.errors := q.lookupValue.errors;
  top.warnings := [];
}

concrete production binaryOperator
top::NameOrBOperator ::= q::BOperator
{
  top.pp = q.pp;
  top.location = q.location;

  top.operation = q.operation;
  top.errors := q.errors;
  top.warnings := [];
}

concrete production bOperatorPlusPlus
top::BOperator ::= '++'
{
  top.pp = "++";
  top.location = loc(top.file, $1.line, $1.column);
  top.operation = plusPlusOperation();
  top.errors := []; -- check string/list somehow?
  top.warnings := [];
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

  top.defs = addAttributeDcl(fName, collectionTypeRep(q.operation, synTypeRep(te.typerep)),
	     addFullNameDcl(a.name, fName, emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors ++ q.errors;
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

  top.defs = addAttributeDcl(fName, collectionTypeRep(q.operation, inhTypeRep(te.typerep)),
	     addFullNameDcl(a.name, fName, emptyDefs()));

  local attribute er1 :: [Decorated Message];
  er1 = if length(getFullNameDclOne(a.name, top.env)) > 1
        then [err(top.location, "Name '" ++ a.pp ++ "' is already bound.")]
        else [];	

  local attribute er2 :: [Decorated Message];
  er2 = if length(getAttributeDclOne(fName, top.env)) > 1
        then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := er1 ++ er2 ++ te.errors ++ q.errors;
  top.warnings := [];

  forwards to attributeDclInh($1, $2, a, $4, te, $8);
}


concrete production collectionAttributeDclProd
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type 'with' q::NameOrBOperator ';'
{
  top.pp = "production attribute " ++ a.name ++ " :: " ++ te.pp ++ " with " ++ q.pp ++ " ;" ;
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":l_" ++ a.name;

  top.productionAttributes = top.defs;
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

  top.errors := er1 ++ er2 ++ te.errors ++ q.errors;
  top.warnings := [];
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8);
}

terminal Contains_t   '<-';
terminal BaseContains_t   ':=';

concrete production attrContainsAppend
top::ProductionStmt ::= val::QName '.' attr::QName '<-' e::Expr ';'
{
  e.expected = expected_type(attr.lookupAttribute.typerep);

  top.errors <- if !null(attr.lookupAttribute.envItems) && !attr.lookupAttribute.typerep.isCollection
                then [err(attr.location, "'<-' operator can only be performed on collections. Attribute " ++ attr.pp ++ " has type " ++ attr.lookupAttribute.typerep.unparse)]
                else [];
                
  forwards to attributeDef(val, $2, attr, terminal(Equal_t, "<-", $4), e, $6);
}

concrete production attrContainsBase
top::ProductionStmt ::= val::QName '.' attr::QName ':=' e::Expr ';'
{
  e.expected = expected_type(attr.lookupAttribute.typerep);

  top.errors <- if !null(attr.lookupAttribute.envItems) && !attr.lookupAttribute.typerep.isCollection
                then [err(attr.location, "':=' operator can only be performed on collections. Attribute " ++ attr.pp ++ " has type " ++ attr.lookupAttribute.typerep.unparse)]
                else [];
                
  forwards to attributeDef(val, $2, attr, terminal(Equal_t, ":=", $4), e, $6);
}

aspect production attributeDef
top::ProductionStmt ::= val::QName '.' attr::QName '=' e::Expr ';'
{
  top.errors <- if $4.lexeme == "=" && attr.lookupAttribute.typerep.isCollection
                then [err(attr.location, "'=' operator cannot be performed on collections. Use '<-' or ':='. " ++ attr.pp ++ " has type " ++ attr.lookupAttribute.typerep.unparse)]
                else [];
}



concrete production valContainsAppend
top::ProductionStmt ::= val::QName '<-' e::Expr ';'
{
  e.expected = expected_type(val.lookupValue.typerep);
  
  top.errors <- if !null(val.lookupValue.envItems) && !val.lookupValue.typerep.isCollection
                then [err(val.location, "'<-' operator can only be performed on collections. Value " ++ val.pp ++ " has type " ++ val.lookupValue.typerep.unparse)]
                else [];
                
  forwards to valueDef(val, terminal(Equal_t, "<-", $2), e, $4);
}

concrete production valContainsBase
top::ProductionStmt ::= val::QName ':=' e::Expr ';'
{
  e.expected = expected_type(val.lookupValue.typerep);
  
  top.errors <- if !null(val.lookupValue.envItems) && !val.lookupValue.typerep.isCollection
                then [err(val.location, "':=' operator can only be performed on collections. Value " ++ val.pp ++ " has type " ++ val.lookupValue.typerep.unparse)]
                else [];
                
  forwards to valueDef(val, terminal(Equal_t, ":=", $2), e, $4);
}


aspect production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.errors <- if $2.lexeme == "=" && val.lookupValue.typerep.isCollection
                then [err(val.location, "'=' operator cannot be performed on collections. Use '<-' or ':='. " ++ val.pp ++ " has type " ++ val.lookupValue.typerep.unparse)]
                else [];
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
