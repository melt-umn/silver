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

  top.errors := er1 ++ er2 ++ te.errors;
  top.warnings := [];
 
  forwards to productionAttributeDcl($1, $2, a, $4, te, $8);
}

terminal Contains_t   '<-';
terminal BaseContains_t   ':=';

concrete production attrContainsAppend
top::ProductionStmt ::= val::QName '.' attr::QName '<-' e::Expr ';'
{
  production attribute fNames1 :: [Decorated EnvItem];
  fNames1 = getFullNameDcl(val.name, top.env);

  production attribute fName1 :: String;
  fName1 = if !null(fNames1) then head(fNames1).fullName else val.name;

  production attribute fNames2 :: [Decorated EnvItem];
  fNames2 = getFullNameDcl(attr.name, top.env);

  production attribute fName2 :: String;
  fName2 = if !null(fNames2) then head(fNames2).fullName else attr.name;

  production attribute attrs :: [Decorated EnvItem];
  attrs = getAttributeDcl(fName2, top.env);
  
  production attribute type :: Decorated TypeRep;
  type = if !null(attrs) then head(attrs).typerep else topTypeRep();

  e.expected = expected_type(type);

  top.errors <- if !null(attrs) && !type.isCollection
                then [err(attr.location, "'<-' operator can only be performed on collections. Attribute " ++ attr.pp ++ " has type " ++ type.unparse)]
                else [];
                
  forwards to attributeDef(val, $2, attr, terminal(Equal_t, "<-", $4), e, $6);
}

concrete production attrContainsBase
top::ProductionStmt ::= val::QName '.' attr::QName ':=' e::Expr ';'
{
  production attribute fNames1 :: [Decorated EnvItem];
  fNames1 = getFullNameDcl(val.name, top.env);

  production attribute fName1 :: String;
  fName1 = if !null(fNames1) then head(fNames1).fullName else val.name;

  production attribute fNames2 :: [Decorated EnvItem];
  fNames2 = getFullNameDcl(attr.name, top.env);

  production attribute fName2 :: String;
  fName2 = if !null(fNames2) then head(fNames2).fullName else attr.name;

  production attribute attrs :: [Decorated EnvItem];
  attrs = getAttributeDcl(fName2, top.env);
  
  production attribute type :: Decorated TypeRep;
  type = if !null(attrs) then head(attrs).typerep else topTypeRep();

  e.expected = expected_type(type);

  top.errors <- if !null(attrs) && !type.isCollection
                then [err(attr.location, "':=' operator can only be performed on collections. Attribute " ++ attr.pp ++ " has type " ++ type.unparse)]
                else [];
                
  forwards to attributeDef(val, $2, attr, terminal(Equal_t, ":=", $4), e, $6);
}

aspect production attributeDef
top::ProductionStmt ::= val::QName '.' attr::QName '=' e::Expr ';'
{
  top.errors <- if $4.lexeme == "=" && type.isCollection
                then [err(attr.location, "'=' operator cannot be performed on collections. Use '<-' or ':='. " ++ attr.pp ++ " has type " ++ type.unparse)]
                else [];
}











concrete production valContainsAppend
top::ProductionStmt ::= val::QName '<-' e::Expr ';'
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(val.name, top.env);

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else val.name;

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  production attribute type :: Decorated TypeRep;
  type = if !null(vals) then head(vals).typerep else topTypeRep();

  e.expected = expected_type(type);
  
  top.errors <- if !null(vals) && !type.isCollection
                then [err(val.location, "'<-' operator can only be performed on collections. Value " ++ val.pp ++ " has type " ++ type.unparse)]
                else [];
                
  forwards to valueDef(val, terminal(Equal_t, "<-", $2), e, $4);
}

concrete production valContainsBase
top::ProductionStmt ::= val::QName ':=' e::Expr ';'
{
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(val.name, top.env);

  production attribute fName :: String;
  fName = if !null(fNames) then head(fNames).fullName else val.name;

  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);
  
  production attribute type :: Decorated TypeRep;
  type = if !null(vals) then head(vals).typerep else topTypeRep();

  e.expected = expected_type(type);
  
  top.errors <- if !null(vals) && !type.isCollection
                then [err(val.location, "':=' operator can only be performed on collections. Value " ++ val.pp ++ " has type " ++ type.unparse)]
                else [];
                
  forwards to valueDef(val, terminal(Equal_t, ":=", $2), e, $4);
}


aspect production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.errors <- if $2.lexeme == "=" && type.isCollection
                then [err(val.location, "'=' operator cannot be performed on collections. Use '<-' or ':='. " ++ val.pp ++ " has type " ++ type.unparse)]
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
