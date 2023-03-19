grammar silver:compiler:definition:type;

-- Quick check to see if an error message should be suppressed
synthesized attribute isError :: Boolean;

-- Check for whether the type can be applied
synthesized attribute isApplicable :: Boolean;

synthesized attribute inputTypes :: [Type];
synthesized attribute outputType :: Type;
synthesized attribute namedTypes :: [Pair<String Type>];
synthesized attribute arity :: Integer;
synthesized attribute baseType :: Type;
synthesized attribute argTypes :: [Type];
synthesized attribute inhSetMembers :: [String];
monoid attribute freeSkolemVars :: [TyVar] with [], setUnionTyVars;
monoid attribute freeFlexibleVars :: [TyVar] with [], setUnionTyVars;

-- Used by Expr, could possibly be replaced by pattern matching for decoratedType
-- Also used by 'new()'
synthesized attribute isDecorated :: Boolean;

-- Determines whether a type is a unique decorated nonterminal type
-- Used in determining whether a type may be supplied with inherited attributes.
synthesized attribute isUniqueDecorated :: Boolean;

-- Determines whether a type is an (undecorated) nonterminal type
-- Used in determining whether a type may be supplied with inherited attributes.
synthesized attribute isNonterminal :: Boolean;

-- Used for type checking by 'terminal()'
synthesized attribute isTerminal :: Boolean;

-- Used by 'new' and type-determination for attributes (NOT on regular nonterminals)
synthesized attribute decoratedType :: Type;

-- Freshens a nonterminal PolyType into a possibly-decorated nonterminal Type
synthesized attribute asNtOrDecType :: Type;

-- The decorated type that this type forwards to, if it is an ntOrDecType
synthesized attribute defaultSpecialization :: Type;

-- Used instead of unify() when we want to just know its decorated or undecorated
synthesized attribute unifyInstanceNonterminal :: Substitution;
synthesized attribute unifyInstanceDecorated :: Substitution;
synthesized attribute unifyInstanceDecorable :: Substitution;  -- NT or unique decorated

attribute arity, isError, isDecorated, isUniqueDecorated, isNonterminal, isTerminal, asNtOrDecType, compareTo, isEqual occurs on PolyType;

aspect production monoType
top::PolyType ::= ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isTerminal = ty.isTerminal;
  top.asNtOrDecType = ty.asNtOrDecType;

  top.isEqual =
    top.compareTo.boundVars == [] &&
    top.compareTo.contexts == [] &&
    top.compareTo.typerep == ty;
}

aspect production polyType
top::PolyType ::= bound::[TyVar] ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isTerminal = ty.isTerminal;
  top.asNtOrDecType = error("Only mono types should be possibly-decorated");

  local eqSub::Substitution =
    zipVarsIntoSubstitution(bound, top.compareTo.boundVars);
  top.isEqual =
    top.compareTo.contexts == [] &&
    top.compareTo.typerep == performRenaming(ty, eqSub);
}

aspect production constraintType
top::PolyType ::= bound::[TyVar] contexts::[Context] ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isTerminal = ty.isTerminal;
  top.asNtOrDecType = error("Only mono types should be possibly-decorated");

  local eqSub::Substitution =
    zipVarsIntoSubstitution(bound, top.compareTo.boundVars);
  top.isEqual =
    top.compareTo.contexts == map(performContextRenaming(_, eqSub), contexts) &&
    top.compareTo.typerep == performRenaming(ty, eqSub);
}

attribute isError, inputTypes, outputType, namedTypes, arity, baseType, argTypes, isDecorated, isUniqueDecorated, isNonterminal, isTerminal, isApplicable, decoratedType, asNtOrDecType, defaultSpecialization, inhSetMembers, freeSkolemVars, freeFlexibleVars, unifyInstanceNonterminal, unifyInstanceDecorated, unifyInstanceDecorable occurs on Type;

propagate freeSkolemVars, freeFlexibleVars on Type;

aspect default production
top::Type ::=
{
  top.inputTypes = [];
  top.outputType = errorType();
  top.namedTypes = [];
  top.arity = 0;
  top.baseType = top;
  top.argTypes = [];
  top.inhSetMembers = [];
  
  top.isDecorated = false;
  top.isUniqueDecorated = false;
  top.isNonterminal = false;
  top.isTerminal = false;
  top.isError = false;
  top.isApplicable = false;
  
  top.decoratedType = errorType();
  top.asNtOrDecType = errorType();
  top.defaultSpecialization = top;
  
  top.unifyInstanceNonterminal = errorSubst("not nt");
  top.unifyInstanceDecorated = errorSubst("not dec");
  top.unifyInstanceDecorable = errorSubst("not dec");
}

aspect production varType
top::Type ::= tv::TyVar
{
  top.freeFlexibleVars <- [tv];
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.freeSkolemVars <- [tv];

  -- Skolems with occurs-on contexts act like nonterminals, so use that behavior in unification
  top.asNtOrDecType = ntOrDecType(top, freshInhSet(), freshType());
  top.unifyInstanceNonterminal = emptySubst();
  top.unifyInstanceDecorable = emptySubst();
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.baseType = c.baseType;
  top.argTypes = c.argTypes ++ [a];
  top.isNonterminal = c.isNonterminal;
  top.asNtOrDecType = ntOrDecType(top, freshInhSet(), freshType());  -- c.baseType should be a nonterminal or skolem
  top.unifyInstanceNonterminal = c.unifyInstanceNonterminal;
  top.unifyInstanceDecorable = c.unifyInstanceDecorable;
  top.arity = c.arity;
  top.isApplicable = c.isApplicable;
  
  top.inputTypes = take(top.arity, top.argTypes);
  top.outputType =
    case top.baseType of
    | functionType(_, _) -> last(top.argTypes)
    | _ -> errorType()
    end;
  top.namedTypes =
    case top.baseType of
    | functionType(_, nps) -> zipWith(pair, nps, drop(top.arity, top.argTypes))
    | _ -> []
    end;
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
top::Type ::= fn::String _ _
{
  top.isNonterminal = true;
  top.asNtOrDecType = ntOrDecType(top, freshInhSet(), freshType());
  top.unifyInstanceNonterminal = emptySubst();
  top.unifyInstanceDecorable = emptySubst();
}

aspect production terminalType
top::Type ::= fn::String
{
  top.isTerminal = true;
}

aspect production inhSetType
top::Type ::= inhs::[String]
{
  top.inhSetMembers = inhs;
}

aspect production decoratedType
top::Type ::= te::Type i::Type
{
  top.isDecorated = true;
  top.decoratedType = te;
  top.inhSetMembers = i.inhSetMembers;
  top.unifyInstanceDecorated = emptySubst();
}

aspect production uniqueDecoratedType
top::Type ::= te::Type i::Type
{
  top.isDecorated = true;
  top.isUniqueDecorated = true;
  top.decoratedType = te;
  top.asNtOrDecType = ntOrDecType(te, freshInhSet(), freshType());
  top.inhSetMembers = i.inhSetMembers;
  top.unifyInstanceDecorated = emptySubst();
  top.unifyInstanceDecorable = emptySubst();
}

aspect production ntOrDecType
top::Type ::= nt::Type inhs::Type hidden::Type
{
  top.baseType = top;
  top.argTypes = [];

  top.asNtOrDecType = top;
  top.unifyInstanceNonterminal = unify(hidden, nt);
  top.unifyInstanceDecorated = unify(hidden, decoratedType(nt, inhs));
  top.unifyInstanceDecorable = unify(hidden, nt);
}

aspect production functionType
top::Type ::= params::Integer namedParams::[String]
{
  top.arity = params;
  top.isApplicable = true;
}

-- Strict type equality, assuming all type vars are skolemized
instance Eq Type {
  eq = \ t1::Type t2::Type -> !unifyDirectional(t1, t2).failure;
}

attribute compareTo, isEqual occurs on Context;
propagate compareTo, isEqual on Context;
