grammar silver:compiler:definition:env;

imports silver:compiler:definition:type;
imports silver:regex;

annotation sourceGrammar :: String;
annotation sourceLocation :: Location;
synthesized attribute fullName :: String;
synthesized attribute typeScheme :: PolyType;

-- types
synthesized attribute isType :: Boolean;
synthesized attribute isTypeAlias :: Boolean;
synthesized attribute isClass :: Boolean;
synthesized attribute classMembers :: [Pair<String Boolean>];

inherited attribute givenInstanceType :: Type;
synthesized attribute superContexts :: [Context];
synthesized attribute typerep2 :: Type; -- Used for binary constraint instances

-- values
synthesized attribute namedSignature :: NamedSignature;
synthesized attribute hasForward :: Boolean;

-- occurs
synthesized attribute attrOccurring :: String;
inherited attribute givenNonterminalType :: Type;

synthesized attribute isAnnotation :: Boolean; -- also "attrs"

-- attrs
synthesized attribute isSynthesized :: Boolean;
synthesized attribute isInherited :: Boolean;

-- production attribute
synthesized attribute prodDefs :: [Def];
-- production attribute substitutions
synthesized attribute substitutedDclInfo :: ValueDclInfo;
inherited attribute givenSubstitution :: Substitution;

nonterminal ValueDclInfo with sourceGrammar, sourceLocation, fullName, typeScheme, namedSignature, hasForward, substitutedDclInfo, givenSubstitution;

aspect default production
top::ValueDclInfo ::=
{
  -- Values that are not fun/prod have this valid default.
  top.namedSignature = bogusNamedSignature();
  top.hasForward = false;
  
  top.substitutedDclInfo = error("Internal compiler error: must be defined for all value declarations that are production attributes");
}

-- ValueDclInfos that can NEVER appear in interface files:
abstract production childDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
}
abstract production lhsDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
}

-- ValueDclInfos that CAN appear in interface files, but only via "production attributes:"
abstract production localDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  
  top.typeScheme = monoType(ty);
  
  top.substitutedDclInfo = localDcl( fn, performRenaming(ty, top.givenSubstitution), sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
}
abstract production forwardDcl
top::ValueDclInfo ::= ty::Type
{
  top.fullName = "forward";
  
  top.typeScheme = monoType(ty);
  
  top.substitutedDclInfo = forwardDcl( performRenaming(ty, top.givenSubstitution), sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
}

-- ValueDclInfos that DO appear in interface files:
abstract production prodDcl
top::ValueDclInfo ::= ns::NamedSignature hasForward::Boolean
{
  top.fullName = ns.fullName;
  
  top.namedSignature = ns;
  top.typeScheme = ns.typeScheme;
  top.hasForward = hasForward;
}
abstract production funDcl
top::ValueDclInfo ::= ns::NamedSignature
{
  top.fullName = ns.fullName;
  
  top.namedSignature = ns;
  top.typeScheme = ns.typeScheme;
  top.hasForward = false;
}
abstract production classMemberDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] clsHead::Context contexts::[Context] ty::Type
{
  top.fullName = fn;
  
  top.typeScheme = constraintType(bound, clsHead :: contexts, ty);
}
abstract production globalValueDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.fullName = fn;
  top.typeScheme = constraintType(bound, contexts, ty);
}
abstract production termIdDcl
top::ValueDclInfo ::= fn::String
{
  top.fullName = fn;

  top.typeScheme = monoType(terminalIdType());
}

nonterminal TypeDclInfo with sourceGrammar, sourceLocation, fullName, typeScheme, kindrep, givenNonterminalType, isType, isTypeAlias, isClass, classMembers, givenInstanceType, superContexts;

aspect default production
top::TypeDclInfo ::=
{
  top.kindrep = starKind();
  top.isType = false;
  top.isTypeAlias = false;
  top.isClass = false;
  top.classMembers = [];
  top.superContexts = [];
}

abstract production ntDcl
top::TypeDclInfo ::= fn::String ks::[Kind] closed::Boolean tracked::Boolean
{
  top.fullName = fn;

  top.typeScheme = monoType(nonterminalType(fn, ks, tracked));
  top.kindrep = foldr(arrowKind, starKind(), ks);
  top.isType = true;
}
abstract production termDcl
top::TypeDclInfo ::= fn::String regex::Regex easyName::Maybe<String>
{
  top.fullName = fn;

  top.typeScheme = monoType(terminalType(fn));
  top.isType = true;
}
abstract production lexTyVarDcl
top::TypeDclInfo ::= fn::String isAspect::Boolean tv::TyVar
{
  top.fullName = fn;

  -- Lexical type vars in aspects aren't skolemized, since they unify with the real (skolem) types.
  -- See comment in silver:compiler:definition:type:syntax:AspectDcl.sv
  top.typeScheme = monoType(if isAspect then varType(tv) else skolemType(tv));
  top.kindrep = tv.kindrep;
  top.isType = true;
}
abstract production typeAliasDcl
top::TypeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.isType = null(bound);
  top.isTypeAlias = true;
  top.typeScheme = if null(bound) then monoType(ty) else polyType(bound, ty);
  top.kindrep = foldr(arrowKind, ty.kindrep, map((.kindrep), bound)); 
}
abstract production clsDcl
top::TypeDclInfo ::= fn::String supers::[Context] tv::TyVar k::Kind members::[Pair<String Boolean>]
{
  top.fullName = fn;
  
  -- These are in the type namespace but shouldn't actually be used as such,
  -- this is only used to report the kind.
  top.typeScheme = monoType(varType(freshTyVar(k)));
  top.isClass = true;
  
  local tvSubst :: Substitution = subst(tv, top.givenInstanceType);
  top.superContexts = map(performContextRenaming(_, tvSubst), supers);
  top.classMembers = members;
}

nonterminal AttributeDclInfo with sourceGrammar, sourceLocation, fullName, typeScheme, isInherited, isSynthesized, isAnnotation;

aspect default production
top::AttributeDclInfo ::=
{
  top.isSynthesized = false;
  top.isInherited = false;
  top.isAnnotation = false;
}

abstract production synDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
}
abstract production inhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isInherited = true;
}
abstract production annoDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isAnnotation = true;
}

nonterminal ProductionAttrDclInfo with sourceGrammar, sourceLocation, fullName, prodDefs, namedSignature;

abstract production paDcl
top::ProductionAttrDclInfo ::= ns::NamedSignature{-fn::String outty::Type intys::[Type]-} dcls::[Def]
{
  top.fullName = ns.fullName;
  
  top.prodDefs = dcls;
  
  -- This is used by the function that computes the substituted defs.
  top.namedSignature = ns;
}

nonterminal OccursDclInfo with sourceGrammar, sourceLocation, fullName, typeScheme, givenNonterminalType, attrOccurring, isAnnotation;

aspect default production
top::OccursDclInfo ::=
{
  top.isAnnotation = false;
}

abstract production occursDcl
top::OccursDclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.fullName = fnnt;
  
  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local subst :: Substitution = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT

  top.typeScheme =
    if subst.failure
    then polyType(atty.freeVariables, atty) -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
    else monoType(performRenaming(atty, subst));
  
  top.attrOccurring = fnat;
}

abstract production occursInstConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type tvs::[TyVar]
{
  top.fullName = ntty.typeName;
  top.attrOccurring = fnat;
  
  top.typeScheme = monoType(atty);
  
  ntty.boundVariables = tvs;
}
abstract production occursSigConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type ns::NamedSignature
{
  top.fullName = ntty.typeName;
  top.attrOccurring = fnat;
  
  top.typeScheme = monoType(atty);
  
  ntty.boundVariables = ns.freeVariables;
}
abstract production occursSuperDcl
top::OccursDclInfo ::= fnat::String atty::Type baseDcl::InstDclInfo
{
  top.fullName = baseDcl.typeScheme.typerep.typeName;
  top.attrOccurring = fnat;
  
  top.typeScheme = constraintType(baseDcl.typeScheme.boundVars, baseDcl.typeScheme.contexts, atty);
}

abstract production annoInstanceDcl
top::OccursDclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.fullName = fnnt;
  
  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local subst :: Substitution = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT

  top.typeScheme =
    if subst.failure
    then polyType(atty.freeVariables, atty) -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
    else monoType(performRenaming(atty, subst));
  
  top.attrOccurring = fnat;

  -- UGH - bit of a short hand here...
  top.isAnnotation = true;
}

nonterminal InstDclInfo with sourceGrammar, sourceLocation, fullName, typeScheme, typerep2, isTypeError;

aspect default production
top::InstDclInfo ::=
{
  top.isTypeError := false;
  top.typerep2 = error("Internal compiler error: must be defined for all binary constraint instances");
}

-- Class instances
abstract production instDcl
top::InstDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.fullName = fn;
  
  top.typeScheme = constraintType(bound, contexts, ty);

  top.isTypeError := any(map((.isTypeError), contexts));
}
abstract production instConstraintDcl
top::InstDclInfo ::= fntc::String ty::Type tvs::[TyVar]
{
  top.fullName = fntc;
  
  top.typeScheme = monoType(ty);
}
abstract production sigConstraintDcl
top::InstDclInfo ::= fntc::String ty::Type ns::NamedSignature
{
  top.fullName = fntc;
  
  top.typeScheme = monoType(ty);
}
abstract production currentInstDcl
top::InstDclInfo ::= fntc::String ty::Type
{
  top.fullName = fntc;
  
  top.typeScheme = monoType(ty);
}
abstract production instSuperDcl
top::InstDclInfo ::= fntc::String baseDcl::InstDclInfo
{
  top.fullName = fntc;
  
  top.typeScheme = baseDcl.typeScheme;
}

-- typeable instances
abstract production typeableInstConstraintDcl
top::InstDclInfo ::= ty::Type tvs::[TyVar]
{
  top.fullName = "typeable";
  
  top.typeScheme = monoType(ty);
}
abstract production typeableSigConstraintDcl
top::InstDclInfo ::= ty::Type ns::NamedSignature
{
  top.fullName = "typeable";
  
  top.typeScheme = monoType(ty);
}
abstract production typeableSuperDcl
top::InstDclInfo ::= baseDcl::InstDclInfo
{
  top.fullName = "typeable";
  
  top.typeScheme = baseDcl.typeScheme;
}

-- inhSubset instances
abstract production inhSubsetInstConstraintDcl
top::InstDclInfo ::= i1::Type i2::Type tvs::[TyVar]
{
  top.fullName = "subset";
  
  top.typeScheme = monoType(i1);
  top.typerep2 = i2;
}
abstract production inhSubsetSigConstraintDcl
top::InstDclInfo ::= i1::Type i2::Type ns::NamedSignature
{
  top.fullName = "subset";
  
  top.typeScheme = monoType(i1);
  top.typerep2 = i2;
}

-- TODO: this should probably go elsewhere?
function determineAttributeType
Type ::= occursDclInfo::OccursDclInfo ntty::Type
{
  occursDclInfo.givenNonterminalType = ntty;
  return occursDclInfo.typeScheme.typerep;
}

-- Dealing with substitutions for production attributes.
function performSubstitutionDclInfo
ValueDclInfo ::= valueDclInfo::ValueDclInfo s::Substitution
{
  valueDclInfo.givenSubstitution = s;
  return valueDclInfo.substitutedDclInfo;
}

function defsFromPADcls
[Def] ::= valueDclInfos::[ValueDclInfo] s::NamedSignature
{
  -- We want to rewrite FROM the sig these PAs were declared with, TO the given sig
  local subst :: Substitution = unifyNamedSignature(head(valueDclInfos).namedSignature, s);
  
  return if null(valueDclInfos) then []
         else map(performSubstitutionDef(_, subst), head(valueDclInfos).prodDefs) ++ defsFromPADcls(tail(valueDclInfos), s);
}

