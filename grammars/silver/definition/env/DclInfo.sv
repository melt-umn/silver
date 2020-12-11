grammar silver:definition:env;

imports silver:definition:type;

import silver:definition:regex;  -- soley for Terms. TODO : fix?

annotation sourceGrammar :: String;
annotation sourceLocation :: Location;
synthesized attribute fullName :: String;

-- types
synthesized attribute typeScheme :: PolyType;
synthesized attribute isType :: Boolean;
synthesized attribute isClass :: Boolean;
synthesized attribute classMembers :: [String];

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
inherited attribute givenSignatureForDefs :: NamedSignature;
synthesized attribute prodDefs :: [Def];
-- production attribute substitutions
synthesized attribute substitutedDclInfo :: DclInfo; -- really ValueDclInfo
inherited attribute givenSubstitution :: Substitution;


{--
 - DclInfo SHOULD be several different types: TypeDclInfo, Value, Attribute,
 - Occurs, ProductionAttr, etc.
 -
 - The reason it's not is we lack the ability to abstract over different types
 - with "the same" interface (need typeclasses tia): this is necessary for some
 - things that make use of e.g. fullName.
 -
 - hmm, unparsing could probably be fixed...
 -}
closed nonterminal DclInfo with sourceGrammar, sourceLocation, fullName, -- everyone
                         typeScheme, givenNonterminalType, isType, isClass, classMembers, -- types (gNT for occurs)
                         namedSignature, hasForward, -- values that are fun/prod
                         attrOccurring, isAnnotation, -- occurs
                         isInherited, isSynthesized, -- attrs
                         prodDefs, -- production attributes
                         substitutedDclInfo, givenSubstitution -- type substitutions on dcls
                         ;

aspect default production
top::DclInfo ::=
{
  -- All Dcls must provide fullName

  -- All values must provide typeScheme.
  -- All attributes must provide typeScheme.
  -- All types must provide typeScheme.
  
  -- All production attributes must provide attrDcl.
  -- All values that may be production attributes must provide substitutedDclInfo
  -- All occurs must provide attrOccurring. (And now, typeScheme, which depends on givenNonterminalType)
  
  -- See silver:definition:core for more "musts"
  
  -- TODO: DESIGN PROBLEM:
  -- The following defaults are provided to account for this one type (dclinfo)
  -- being used, when there really SHOULD be different types.
  -- (The only reason we use one type right now is that we like to have
  -- e.g. fullName on all declarations, and we currently can't write a type
  -- like "anything with a fullName".)
  top.attrOccurring = error("Internal compiler error: must be defined for all occurs declarations");
  top.prodDefs = error("Internal compiler error: must be defined for all production attribute declarations");
  top.substitutedDclInfo = error("Internal compiler error: must be defined for all value declarations that are production attributes");
  
  -- types
  top.isType = false;
  top.isClass = false;
  top.classMembers = [];
  
  -- Values that are not fun/prod have this valid default.
  top.namedSignature = bogusNamedSignature();
  top.hasForward = false;

  -- On Occurs declarations and attrs
  top.isAnnotation = false;
  
  -- attrs
  top.isSynthesized = false;
  top.isInherited = false;
}

-- ValueDclInfos that can NEVER appear in interface files:
abstract production childDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
}
abstract production lhsDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);
}

-- ValueDclInfos that CAN appear in interface files, but only via "production attributes:"
abstract production localDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  
  top.typeScheme = monoType(ty);
  
  top.substitutedDclInfo = localDcl( fn, performRenaming(ty, top.givenSubstitution), sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
}
abstract production forwardDcl
top::DclInfo ::= ty::Type
{
  top.fullName = "forward";
  
  top.typeScheme = monoType(ty);
  
  top.substitutedDclInfo = forwardDcl( performRenaming(ty, top.givenSubstitution), sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
}

-- ValueDclInfos that DO appear in interface files:
abstract production prodDcl
top::DclInfo ::= ns::NamedSignature hasForward::Boolean
{
  top.fullName = ns.fullName;
  
  top.namedSignature = ns;
  top.typeScheme = ns.typeScheme;
  top.hasForward = hasForward;
}
abstract production funDcl
top::DclInfo ::= ns::NamedSignature
{
  top.fullName = ns.fullName;
  
  top.namedSignature = ns;
  top.typeScheme = ns.typeScheme;
  top.hasForward = false;
}
abstract production globalValueDcl
top::DclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(ty.freeVariables, ty);
}
abstract production classMemberDcl
top::DclInfo ::= fn::String bound::[TyVar] context::Context ty::Type
{
  top.fullName = fn;
  
  top.typeScheme = constraintType(bound, [context], ty);
}

-- TypeDclInfos
abstract production ntDcl
top::DclInfo ::= fn::String arity::Integer closed::Boolean
{
  top.fullName = fn;

  local tvs::[TyVar] = freshTyVars(arity);
  top.typeScheme = polyType(tvs, nonterminalType(fn, map(varType, tvs)));
  top.isType = true;
}
abstract production termDcl
top::DclInfo ::= fn::String regex::Regex
{
  top.fullName = fn;

  top.typeScheme = monoType(terminalType(fn));
  top.isType = true;
}
abstract production lexTyVarDcl
top::DclInfo ::= fn::String isAspect::Boolean tv::TyVar
{
  top.fullName = fn;

  -- Lexical type vars in aspects aren't skolemized, since they unify with the real (skolem) types.
  -- See comment in silver:definition:type:syntax:AspectDcl.sv
  top.typeScheme = monoType(if isAspect then varType(tv) else skolemType(tv));
  top.isType = true;
}
abstract production clsDcl
top::DclInfo ::= fn::String bound::[TyVar] supers::[Context] tv::TyVar members::[String]
{
  top.fullName = fn;
  
  -- These are in the type namespace but shouldn't actually be used as such
  top.typeScheme = monoType(errorType()); -- TODO: distinguish class vs. type by giving these a type?
  top.isClass = true;
}

-- AttributeDclInfos
abstract production synDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
}
abstract production inhDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isInherited = true;
}
abstract production annoDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
  top.isAnnotation = true;
}

-- ProductionAttrDclInfo
abstract production paDcl
top::DclInfo ::= ns::NamedSignature{-fn::String outty::Type intys::[Type]-} dcls::[Def]
{
  top.fullName = ns.fullName;
  
  top.prodDefs = dcls;
  
  top.typeScheme = error("typeScheme not defined for production attributes");
  
  -- This is used by the function that computes the substituted defs.
  top.namedSignature = ns;
}

-- OccursDclInfo
abstract production occursDcl
top::DclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
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

abstract production annoInstanceDcl
top::DclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
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

-- InstDclInfos
abstract production instDcl
top::DclInfo ::= fntc::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.fullName = fntc;
  
  top.typeScheme = constraintType(bound, contexts, ty);
}


-- TODO: this should probably go elsewhere?
function determineAttributeType
Type ::= occursDclInfo::DclInfo ntty::Type
{
  occursDclInfo.givenNonterminalType = ntty;
  return occursDclInfo.typeScheme.typerep;
}

-- Dealing with substitutions for production attributes. Really ValueDclInfos
function performSubstitutionDclInfo
DclInfo ::= valueDclInfo::DclInfo s::Substitution
{
  valueDclInfo.givenSubstitution = s;
  return valueDclInfo.substitutedDclInfo;
}

-- This function really takes a list of ValueDclInfos
function defsFromPADcls
[Def] ::= valueDclInfos::[DclInfo] s::NamedSignature
{
  -- We want to rewrite FROM the sig these PAs were declared with, TO the given sig
  local subst :: Substitution = unifyNamedSignature(head(valueDclInfos).namedSignature, s);
  
  return if null(valueDclInfos) then []
         else map(performSubstitutionDef(_, subst), head(valueDclInfos).prodDefs) ++ defsFromPADcls(tail(valueDclInfos), s);
}

