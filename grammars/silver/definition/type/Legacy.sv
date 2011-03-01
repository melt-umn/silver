grammar silver:definition:type;

-- DEPRECATED STUFF
attribute inputTypes, outputType, isDecorated, isDecorable, isTerminal, decoratedType occurs on TypeExp;

-- exists because we want to access both these and pattern matching can only extract one thing at a time (so far)
synthesized attribute inputTypes :: [TypeExp];
synthesized attribute outputType :: TypeExp;

-- Used by Expr, could possibly be replaced by pattern matching for decoratedTypeExp
-- Also used by 'new()'
synthesized attribute isDecorated :: Boolean;

-- Determines whether a type is automatically promoted to a decorated type
-- and whether a type may be supplied with inherited attributes.
-- Used by expression (id refs), decorate type checking, and translations.
synthesized attribute isDecorable :: Boolean;

-- Used for type checking by 'terminal()'
synthesized attribute isTerminal :: Boolean;

-- Used by 'new' and type-determination for attributes (NOT on regular nonterminals)
synthesized attribute decoratedType :: TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.inputTypes = [];
  top.outputType = errorType();
  
  top.isDecorated = false;
  top.isDecorable = false;
  top.isTerminal = false;
  
  top.decoratedType = errorType();
}

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
}

aspect production intTypeExp
top::TypeExp ::=
{
}

aspect production boolTypeExp
top::TypeExp ::=
{
}

aspect production floatTypeExp
top::TypeExp ::=
{
}

aspect production stringTypeExp
top::TypeExp ::=
{
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.isDecorable = true;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.isTerminal = true;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.isDecorated = true;
  top.decoratedType = te;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.inputTypes = params;
  top.outputType = out;
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.inputTypes = params;
  top.outputType = out;
}

