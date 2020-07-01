grammar silver:definition:type;

-- DEPRECATED STUFF
attribute isError, inputTypes, outputType, namedTypes, isDecorated, isDecorable, isTerminal, decoratedType, unifyInstanceNonterminal, unifyInstanceDecorated occurs on Type;

-- Quick check to see if an error message should be suppressed
synthesized attribute isError :: Boolean;

-- exists because we want to access both these and pattern matching can only extract one thing at a time (so far)
synthesized attribute inputTypes :: [Type];
synthesized attribute outputType :: Type;
synthesized attribute namedTypes :: [NamedArgType];

-- Used by Expr, could possibly be replaced by pattern matching for decoratedType
-- Also used by 'new()'
synthesized attribute isDecorated :: Boolean;

-- Determines whether a type is automatically promoted to a decorated type
-- and whether a type may be supplied with inherited attributes.
-- Used by expression (id refs), decorate type checking, and translations.
synthesized attribute isDecorable :: Boolean;

-- Used for type checking by 'terminal()'
synthesized attribute isTerminal :: Boolean;

-- Used by 'new' and type-determination for attributes (NOT on regular nonterminals)
synthesized attribute decoratedType :: Type;

-- Used instead of unify() when we want to just know its decorated or undecorated
synthesized attribute unifyInstanceNonterminal :: Substitution;
synthesized attribute unifyInstanceDecorated :: Substitution;

aspect default production
top::Type ::=
{
  top.inputTypes = [];
  top.outputType = errorType();
  top.namedTypes = [];
  
  top.isDecorated = false;
  top.isDecorable = false;
  top.isTerminal = false;
  top.isError = false;
  
  top.decoratedType = errorType();
  
  top.unifyInstanceNonterminal = errorSubst("not nt");
  top.unifyInstanceDecorated = errorSubst("not dec");
}

aspect production varType
top::Type ::= tv::TyVar
{
}

aspect production skolemType
top::Type ::= tv::TyVar
{
}

aspect production errorType
top::Type ::=
{
  top.isError = true;
}

aspect production intType
top::Type ::=
{
}

aspect production boolType
top::Type ::=
{
}

aspect production floatType
top::Type ::=
{
}

aspect production stringType
top::Type ::=
{
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]  tracked::Boolean
{
  top.isDecorable = true;
  top.unifyInstanceNonterminal = emptySubst();
}

aspect production terminalType
top::Type ::= fn::String
{
  top.isTerminal = true;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.isDecorated = true;
  top.decoratedType = te;
  top.unifyInstanceDecorated = emptySubst();
}

aspect production ntOrDecType
top::Type ::= nt::Type  hidden::Type
{
  top.unifyInstanceNonterminal = unify(hidden, nt);
  top.unifyInstanceDecorated = unify(hidden, decoratedType(nt));
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.inputTypes = params;
  top.outputType = out;
  top.namedTypes = namedParams;
}

