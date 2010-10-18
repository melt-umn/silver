grammar silver:definition:type;

-- DEPRECATED STUFF
attribute inputTypes, outputType, isDecorated, isProduction, isFunction, isDecorable, isTerminal, decoratedType occurs on TypeExp;

-- exists because we want to access both these and pattern matching can only extract one thing at a time (so far)
synthesized attribute inputTypes :: [TypeExp];
synthesized attribute outputType :: TypeExp;

-- Used by Expr, could possibly be replaced by pattern matching for decoratedTypeExp
-- Also used by 'new()'
synthesized attribute isDecorated :: Boolean;

-- Used by aspects, could possibly be replaced by pattern matching
synthesized attribute isProduction :: Boolean;
synthesized attribute isFunction :: Boolean;

-- Used for type checking 'decorate' and 'terminal()'
synthesized attribute isDecorable :: Boolean;
synthesized attribute isTerminal :: Boolean;

-- Used by 'new' and type-determination for attributes (NOT on regular nonterminals)
synthesized attribute decoratedType :: TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.inputTypes = [];
  top.outputType = errorType();
  
  top.isFunction = false;
  top.isProduction = false;
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
  top.isFunction = true;
  top.inputTypes = params;
  top.outputType = out;
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.isProduction = true;
  top.inputTypes = params;
  top.outputType = out;
}

