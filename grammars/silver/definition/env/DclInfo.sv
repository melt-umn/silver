grammar silver:definition:env;

imports silver:definition:type;

import silver:definition:regex;  -- soley for Terms. TODO : fix?

synthesized attribute sourceGrammar :: String;
synthesized attribute sourceLocation :: Location;
synthesized attribute fullName :: String;

-- types
synthesized attribute typerep :: Type;
synthesized attribute dclBoundVars :: [TyVar];

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
                         typerep, givenNonterminalType, -- types (gNT for occurs)
                         namedSignature, hasForward, -- values that are fun/prod
                         attrOccurring, isAnnotation, -- occurs
                         isInherited, isSynthesized, -- attrs
                         prodDefs, -- production attributes
                         dclBoundVars, -- Global values (where we have type schemes)
                         substitutedDclInfo, givenSubstitution -- type substitutions on dcls
                         ;

aspect default production
top::DclInfo ::=
{
  -- All dcls must provide sourceGrammar, sourceLocation, fullName

  -- All values must provide typerep.
  -- All attributes must provide typerep, bound.
  -- All types must provide typerep, bound.
  
  -- All production attributes must provide attrDcl.
  -- All values that may be production attributes must provide substitutedDclInfo
  -- All occurs must provide attrOccurring. (And now, typerep, which depends on givenNonterminalType)
  
  -- See silver:definition:core for more "musts"
  
  -- TODO: DESIGN PROBLEM:
  -- The following defaults are provided to account for this one type (dclinfo)
  -- being used, when there really SHOULD be different types.
  -- (The only reason we use one type right now is that we like to have
  -- e.g. fullName on all declarations, and we currently can't write a type
  -- like "anything with a fullName".)
  top.attrOccurring = error("Internal compiler error: must be defined for all occurs declarations");
  top.prodDefs = error("Internal compiler error: must be defined for all production attribute declarations");
  top.dclBoundVars = error("Internal compiler error: must be defined for all value declarations");
  top.substitutedDclInfo = error("Internal compiler error: must be defined for all value declarations that are production attributes");
  
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
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
}
abstract production lhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
}

-- ValueDclInfos that CAN appear in interface files, but only via "production attributes:"
abstract production localDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  top.typerep = ty;
  
  top.substitutedDclInfo = localDcl(sg,sl, fn, performRenaming(ty, top.givenSubstitution));
}
abstract production forwardDcl
top::DclInfo ::= sg::String sl::Location ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "forward";
  
  top.typerep = ty;
  
  top.substitutedDclInfo = forwardDcl(sg,sl, performRenaming(ty, top.givenSubstitution));
}

-- ValueDclInfos that DO appear in interface files:
abstract production prodDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature hasForward::Boolean
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;

  local boundvars :: [TyVar] = top.typerep.freeVariables;
  
  top.namedSignature = ns;
  top.typerep = ns.typerep;
  top.hasForward = hasForward;
}
abstract production funDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;
  
  local boundvars :: [TyVar] = top.typerep.freeVariables;
  
  top.namedSignature = ns;
  top.typerep = ns.typerep;
  top.hasForward = false;
}
abstract production globalValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
}

-- TypeDclInfos
abstract production ntDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type closed::Boolean
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
}
abstract production termDcl
top::DclInfo ::= sg::String sl::Location fn::String regex::Regex
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = terminalType(fn);
  top.dclBoundVars = [];
}
abstract production lexTyVarDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = [];
}

-- AttributeDclInfos
abstract production synDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
  top.isSynthesized = true;
}
abstract production inhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
  top.isInherited = true;
}
abstract production annoDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
  top.isAnnotation = true;
}

-- ProductionAttrDclInfo
abstract production paDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature{-fn::String outty::Type intys::[Type]-} dcls::[Def]
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;

  local boundvars :: [TyVar] = top.typerep.freeVariables;
  
  top.prodDefs = dcls;
  
  -- This is used by the function that computes the substituted defs.
  top.typerep = ns.typerep;
  -- We do have this now... any refactoring that should use it?
  --top.namedSignature = ns;
}

-- OccursDclInfo
abstract production occursDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fnnt;
  
  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local attribute subst :: Substitution;
  subst = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT
  
  top.typerep = if subst.failure
                then -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
                     freshenCompletely(atty)
                else performRenaming(atty, subst);
  
  top.attrOccurring = fnat;
}

abstract production annoInstanceDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fnnt;
  
  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local attribute subst :: Substitution;
  subst = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT
  
  top.typerep = if subst.failure
                then -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
                     freshenCompletely(atty)
                else performRenaming(atty, subst);
  
  top.attrOccurring = fnat;

  -- UGH - bit of a short hand here...
  top.isAnnotation = true;
}

-- TODO: this should probably go elsewhere?
function determineAttributeType
Type ::= occursDclInfo::DclInfo ntty::Type
{
  occursDclInfo.givenNonterminalType = ntty;
  return occursDclInfo.typerep;
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
  local subst :: Substitution =
    unifyDirectional(head(valueDclInfos).typerep, s.typerep);
  
  local useSubst :: Substitution =
    if !subst.failure then subst
    else errorSubstitution(head(valueDclInfos).typerep);
  
  return if null(valueDclInfos) then []
         else map(performSubstitutionDef(_, useSubst), head(valueDclInfos).prodDefs) ++ defsFromPADcls(tail(valueDclInfos), s);
}

