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

-- Determines whether a type is a data nonterminal type
synthesized attribute isData :: Boolean;

-- Determines whether a type is tracked
synthesized attribute isTracked :: Boolean;

-- Used for type checking by 'terminal()'
synthesized attribute isTerminal :: Boolean;

-- Used by 'new' and type-determination for attributes (NOT on regular nonterminals)
synthesized attribute decoratedType :: Type;

-- The uniqueness tag for a decorated type
synthesized attribute uniqueness :: Type;

-- The reference set for a decorated type
synthesized attribute refSet :: Type;

-- Freshens a nonterminal PolyType into a decorated nonterminal Type
synthesized attribute asDecoratedType :: Type;

-- Specialize some kinds of remaining flexible type vars after inference is complete
functor attribute defaultSpecialization;

attribute
  arity, isError, isDecorated, isUniqueDecorated, isNonterminal, isData, isTerminal, asDecoratedType,
  compareTo, isEqual occurs on PolyType;

aspect production monoType
top::PolyType ::= ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isData = ty.isData;
  top.isTerminal = ty.isTerminal;
  top.asDecoratedType = ty.asDecoratedType;

  top.isEqual =
    top.compareTo.boundVars == [] &&
    top.compareTo.contexts == [] &&
    top.compareTo.typerep == new(ty);
}

aspect production polyType
top::PolyType ::= bound::[TyVar] ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isData = ty.isData;
  top.isTerminal = ty.isTerminal;
  top.asDecoratedType = error("Only mono types should be possibly-decorated");

  local eqSub::Substitution =
    zipVarsIntoSubstitution(bound, top.compareTo.boundVars);
  top.isEqual =
    top.compareTo.contexts == [] &&
    top.compareTo.typerep == performRenaming(new(ty), eqSub);
}

aspect production constraintType
top::PolyType ::= bound::[TyVar] contexts::[Context] ty::Type
{
  top.arity = ty.arity;
  top.isError = ty.isError;
  top.isDecorated = ty.isDecorated;
  top.isUniqueDecorated = ty.isUniqueDecorated;
  top.isNonterminal = ty.isNonterminal;
  top.isData = ty.isData;
  top.isTerminal = ty.isTerminal;
  top.asDecoratedType = error("Only mono types should be possibly-decorated");

  local eqSub::Substitution =
    zipVarsIntoSubstitution(bound, top.compareTo.boundVars);
  top.isEqual =
    top.compareTo.contexts == map(performContextRenaming(_, eqSub), contexts) &&
    top.compareTo.typerep == performRenaming(new(ty), eqSub);
}

attribute
  isError, inputTypes, outputType, namedTypes, arity, baseType, argTypes,
  isDecorated, isUniqueDecorated, isNonterminal, isData, isTracked, isTerminal, isApplicable,
  decoratedType, uniqueness, refSet, asDecoratedType, defaultSpecialization, inhSetMembers,
  freeSkolemVars, freeFlexibleVars occurs on Type;

propagate freeSkolemVars, freeFlexibleVars on Type;
propagate defaultSpecialization on Type excluding varType;

aspect default production
top::Type ::=
{
  top.inputTypes = [];
  top.outputType = errorType();
  top.namedTypes = [];
  top.arity = 0;
  top.baseType = new(top);
  top.argTypes = [];
  top.inhSetMembers = [];
  
  top.isDecorated = false;
  top.isUniqueDecorated = false;
  top.isNonterminal = false;
  top.isData = false;
  top.isTracked = false;
  top.isTerminal = false;
  top.isError = false;
  top.isApplicable = false;
  
  top.decoratedType = errorType();
  top.uniqueness = varType(freshTyVar(uniquenessKind()));
  top.refSet = freshInhSet();
  top.asDecoratedType = errorType();
  top.defaultSpecialization = new(top);
}

aspect production varType
top::Type ::= tv::TyVar
{
  top.freeFlexibleVars <- [tv];
  top.defaultSpecialization =
    case tv.kind of
    | uniquenessKind() -> nonUniqueType()
    | inhSetKind() -> inhSetType([])
    | _ -> new(top)
    end;
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.freeSkolemVars <- [tv];

  -- Skolems with occurs-on contexts act like nonterminals, so use that behavior in unification
  top.asDecoratedType = freshDecoratedType(new(top));
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.baseType = c.baseType;
  top.argTypes = c.argTypes ++ [new(a)];
  top.isDecorated = c.isDecorated;
  top.isUniqueDecorated =
    case c of
    | appType(appType(decoratedType(), uniqueType()), _) -> true
    | _ -> false
    end;
  top.isNonterminal = c.isNonterminal;
  top.isData = c.isData;
  top.isTracked = c.isTracked;
  top.decoratedType =
    case c of
    | appType(appType(decoratedType(), _), _) -> new(a)
    | _ -> errorType()
    end;
  top.uniqueness =
    case c of
    | appType(appType(decoratedType(), u), _) -> new(u)
    | _ -> varType(freshTyVar(uniquenessKind()))
    end;
  top.refSet =
    case c of
    | appType(appType(decoratedType(), _), i) -> new(i)
    | _ -> freshInhSet()
    end;
  top.asDecoratedType =
    if top.isDecorated
    then freshDecoratedType(top.decoratedType)
    else freshDecoratedType(new(top));  -- c.baseType should be a nonterminal or skolem
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
    | functionType(_, nps) -> zip(nps, drop(top.arity, top.argTypes))
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
top::Type ::= fn::String ks::[Kind] data::Boolean tracked::Boolean
{
  top.isNonterminal = true;
  top.isData = data;
  top.isTracked = tracked;
  top.asDecoratedType = if data then new(top) else freshDecoratedType(new(top));
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
top::Type ::=
{
  top.isDecorated = true;
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

attribute compareTo, isEqual, defaultSpecialization occurs on Context;
propagate compareTo, isEqual, defaultSpecialization on Context;
