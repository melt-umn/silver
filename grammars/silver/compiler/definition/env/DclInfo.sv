grammar silver:compiler:definition:env;

imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax only mentionedAliases;
imports silver:regex;

-- Some of these nonterminals are closed, but the dispatch attributes are
-- defined in silver:compiler:definition:core, and we don't want to have defaults for those:
option silver:compiler:definition:core;

annotation sourceGrammar :: String;
annotation sourceLocation :: Location;
synthesized attribute fullName :: String;
synthesized attribute typeScheme :: PolyType;

-- types
synthesized attribute isType :: Boolean;
synthesized attribute isTypeAlias :: Boolean;
synthesized attribute isClass :: Boolean;
synthesized attribute classMembers :: [Pair<String Boolean>];

-- instances
inherited attribute givenInstanceType :: Type;
synthesized attribute superContexts :: [Context];
synthesized attribute typerep2 :: Type; -- Used for binary constraint instances
synthesized attribute definedMembers :: [String];

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

closed nonterminal ValueDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, isEqual,
  typeScheme, namedSignature, hasForward, substitutedDclInfo, givenSubstitution;
propagate isEqual on ValueDclInfo excluding globalValueDcl, classMemberDcl;

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

  top.isEqual =
    case top.compareTo of
    | classMemberDcl(fn2, _, _, _, _) -> fn == fn2 && top.typeScheme == top.compareTo.typeScheme
    | _ -> false
    end;
}
abstract production globalValueDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.fullName = fn;
  top.typeScheme = constraintType(bound, contexts, ty);

  top.isEqual =
    case top.compareTo of
    | globalValueDcl(fn2, _, _, _) -> fn == fn2 && top.typeScheme == top.compareTo.typeScheme
    | _ -> false
    end;
}
abstract production termIdDcl
top::ValueDclInfo ::= fn::String
{
  top.fullName = fn;

  top.typeScheme = monoType(terminalIdType());
}

closed nonterminal TypeDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, isEqual,
  typeScheme, kindrep, givenNonterminalType, isType, isTypeAlias, mentionedAliases, isClass, classMembers, givenInstanceType, superContexts;
propagate isEqual, compareTo on TypeDclInfo excluding typeAliasDcl, clsDcl;

aspect default production
top::TypeDclInfo ::=
{
  top.kindrep = starKind();
  top.isType = false;
  top.isTypeAlias = false;
  top.mentionedAliases := [];
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
top::TypeDclInfo ::= fn::String regex::Regex easyName::Maybe<String> genRepeatProb::Maybe<Float>
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
top::TypeDclInfo ::= fn::String mentionedAliases::[String] bound::[TyVar] ty::Type
{
  top.fullName = fn;
  top.isEqual =
    case top.compareTo of
    | typeAliasDcl(fn2, ma2, _, _) ->
      fn == fn2 && mentionedAliases == ma2 && top.typeScheme == top.compareTo.typeScheme
    | _ -> false
    end;

  top.isType = null(bound);
  top.isTypeAlias = true;
  top.mentionedAliases := mentionedAliases;
  top.typeScheme = if null(bound) then monoType(ty) else polyType(bound, ty);
  top.kindrep = foldr(arrowKind, ty.kindrep, map((.kindrep), bound)); 
}
abstract production clsDcl
top::TypeDclInfo ::= fn::String supers::[Context] tv::TyVar k::Kind members::[Pair<String Boolean>]
{
  top.fullName = fn;
  top.isEqual =
    case top.compareTo of
    | clsDcl(fn2, s2, tv2, k2, m2) ->
      fn == fn2 && k == k2 && supers == map(performContextRenaming(_, subst(tv2, skolemType(tv))), s2) && members == m2
    | _ -> false
    end;
  
  -- These are in the type namespace but shouldn't actually be used as such,
  -- this is only used to report the kind.
  top.typeScheme = monoType(varType(freshTyVar(k)));
  top.isClass = true;
  
  local tvSubst :: Substitution = subst(tv, top.givenInstanceType);
  top.superContexts = map(performContextRenaming(_, tvSubst), supers);
  top.classMembers = members;
}

closed nonterminal AttributeDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, compareKey, isEqual,
  typeScheme, isInherited, isSynthesized, isAnnotation;
propagate compareKey on AttributeDclInfo;

aspect default production
top::AttributeDclInfo ::=
{
  top.isEqual =
    top.compareKey == top.compareTo.compareKey &&
    top.fullName == top.compareTo.fullName &&
    top.typeScheme == top.compareTo.typeScheme;

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

nonterminal ProductionAttrDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, isEqual, prodDefs, namedSignature;

abstract production paDcl
top::ProductionAttrDclInfo ::= ns::NamedSignature{-fn::String outty::Type intys::[Type]-} dcls::[Def]
{
  top.fullName = ns.fullName;
  top.isEqual = ns == top.compareTo.namedSignature && dcls == defsFromPADcls([new(top.compareTo)], ns);
  
  top.prodDefs = dcls;
  
  -- This is used by the function that computes the substituted defs.
  top.namedSignature = ns;
}

nonterminal OccursDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, isEqual,
  typeScheme, givenNonterminalType, attrOccurring, isAnnotation;
propagate compareTo, isEqual on OccursDclInfo excluding occursDcl;

aspect default production
top::OccursDclInfo ::=
{
  top.isAnnotation = false;
}

abstract production occursDcl
top::OccursDclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.fullName = fnnt;
  top.isEqual =
    case top.compareTo of
    | occursDcl(fnnt2, fnat2, ntty2, atty2) ->
      fnnt == fnnt2 && fnat == fnat2 &&
      polyType(ntty.freeVariables, ntty) == polyType(ntty2.freeVariables, ntty2) &&
      polyType(ntty.freeVariables, atty) == polyType(ntty2.freeVariables, atty2)
    | _ -> false
    end;
  
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
abstract production annoInstConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type tvs::[TyVar]
{
  top.fullName = ntty.typeName;
  top.attrOccurring = fnat;
  top.isAnnotation = true;
  
  top.typeScheme = monoType(atty);
  
  ntty.boundVariables = tvs;
}
abstract production annoSigConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type ns::NamedSignature
{
  top.fullName = ntty.typeName;
  top.attrOccurring = fnat;
  top.isAnnotation = true;
  
  top.typeScheme = monoType(atty);
  
  ntty.boundVariables = ns.freeVariables;
}
abstract production annoSuperDcl
top::OccursDclInfo ::= fnat::String atty::Type baseDcl::InstDclInfo
{
  top.fullName = baseDcl.typeScheme.typerep.typeName;
  top.attrOccurring = fnat;
  top.isAnnotation = true;
  
  top.typeScheme = constraintType(baseDcl.typeScheme.boundVars, baseDcl.typeScheme.contexts, atty);
}

nonterminal InstDclInfo with
  sourceGrammar, sourceLocation, fullName, compareTo, isEqual,
  typeScheme, typerep2, isTypeError, definedMembers;

aspect default production
top::InstDclInfo ::=
{
  top.isTypeError := false;
  top.definedMembers = [];
  top.typerep2 = error("Internal compiler error: must be defined for all binary constraint instances");
  top.isEqual =
    top.fullName == top.compareTo.fullName &&
    top.typeScheme == top.compareTo.typeScheme &&
    top.definedMembers == top.compareTo.definedMembers;
}

-- Class instances
abstract production instDcl
top::InstDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type definedMembers::[String]
{
  top.fullName = fn;
  
  top.typeScheme = constraintType(bound, contexts, ty);

  top.isTypeError := any(map((.isTypeError), contexts));
  top.definedMembers = definedMembers;
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
[Def] ::= dcls::[ProductionAttrDclInfo] s::NamedSignature
{
  -- We want to rewrite FROM the sig these PAs were declared with, TO the given sig
  local subst :: Substitution = unifyNamedSignature(head(dcls).namedSignature, s);
  
  return if null(dcls) then []
         else map(performSubstitutionDef(_, subst), head(dcls).prodDefs) ++ defsFromPADcls(tail(dcls), s);
}

