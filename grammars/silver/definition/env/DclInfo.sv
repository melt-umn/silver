grammar silver:definition:env;

import silver:definition:regex;  -- soley for Terms. TODO : fix?
import silver:definition:type;

synthesized attribute sourceGrammar :: String;
synthesized attribute sourceLocation :: Location;
synthesized attribute fullName :: String;

-- types
synthesized attribute typerep :: TypeExp;
synthesized attribute dclBoundVars :: [TyVar];


synthesized attribute namedSignature :: Decorated NamedSignature;

{-
-- values
synthesized attribute refDispatcher :: Production(Expr ::= Decorated QName);
synthesized attribute defDispatcher :: Production(ProductionStmt ::= Decorated QName  Equal_t  Expr);
synthesized attribute defLHSDispatcher :: Production (DefLHS ::= Decorated QName);

-- attributes
synthesized attribute attrAccessDispatcher :: Production (Expr ::= Decorated Expr '.' Decorated QName);
synthesized attribute attrDefDispatcher :: Production (ProductionStmt ::= DefLHS '.' Decorated QName Equal_t Expr);
-}

-- occurs
synthesized attribute attrOccurring :: String;
inherited attribute givenNonterminalType :: TypeExp;

-- production attribute
inherited attribute givenSignatureForDefs :: Decorated NamedSignature;
synthesized attribute prodDefs :: Defs;
-- production attribute substitutions
synthesized attribute substitutedDclInfo :: DclInfo;
inherited attribute givenSubstitution :: Substitution;


-- on TYPEREP:
-- synthesized attribute applicationDispatcher :: Production (Expr ::= Decorated Expr Exprs);
-- synthesized attribute accessDispatcher :: Production (Expr ::= Decorated Expr '.' Decorated QName);

{- Algorithms:

  Expr.QName     accessDispatcher on Expr.typerep.  NT will dispatch on QName.attrAccessDispatcher.
  
  Expr(Exprs)    applicationDispatcher on Expr.typerep.
  
  QName          refDispatcher on QName
  
  QName = Expr   defDispatcher on QName
  
  DefLHS . QName = Expr   attrDefDispatcher. Give isInherited/isSynthesized to DefLHS (which is gotten via defLHSDispatcher)
  
-}

nonterminal DclInfo with sourceGrammar, sourceLocation, fullName, -- everyone
                         unparse, boundVariables, -- unparsing to interface files
                         typerep, givenNonterminalType, -- types (gNT for occurs)
                         namedSignature, -- values that are fun/prod
                         attrOccurring, -- occurs
                         prodDefs, -- production attributes
                         dclBoundVars, -- Global values (where we have type schemes)
                         substitutedDclInfo, givenSubstitution -- type substitutions on dcls
                         ;

abstract production defaultDcl
top::DclInfo ::=
{
  -- This space intentionally left blank.
  -- All dcls must provide sourceGrammar, sourceLocation, fullName
  -- All dcls that appear in interface files must provide unparse
  
  -- All values must provide typerep.
  -- All attributes must provide typerep, bound.
  -- All types must provide typerep, bound.
  
  -- All production attributes must provide attrDcl.
  -- All values that may be production attributes must provide substitutedDclInfo
  -- All occurs must provide attrOccurring. (And now, typerep, which depends on givenNonterminalType)
  
  -- See silver:definition:core for more "musts"
  
  -- this exists because extensions/modifications MUST not add any more musts.
  -- And then you need defaulting.  See collection attributes for an example.
  
  top.namedSignature = decorate namedSignatureDefault() with {};
}

-- -- non-interface values
abstract production childDcl
top::DclInfo ::= sg::String sl:: Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = error("child values should never appear in interace files.");
  
  top.typerep = ty;
  forwards to defaultDcl();
}
abstract production lhsDcl
top::DclInfo ::= sg::String sl:: Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = error("lhs values should never appear in interace files.");
  
  top.typerep = ty;
  forwards to defaultDcl();
}
abstract production localDcl
top::DclInfo ::= sg::String sl:: Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't
  top.unparse = "loc(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
  
  top.substitutedDclInfo = localDcl(sg,sl, fn, performSubstitution(ty, top.givenSubstitution));
}
-- let ( possibly replacement? problem: caching result )
-- NEW shadowed syn attributes? or inh?
-- NEW specific production type?

-- -- interface values
abstract production prodDcl
top::DclInfo ::= sg::String sl:: Location ns::Decorated NamedSignature
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;

  local attribute boundvars :: [TyVar];
  boundvars = top.typerep.freeVariables;
  
  local attribute upns :: NamedSignature;
  upns = new(ns);
  upns.boundVariables = top.boundVariables ++ boundvars;
  
  top.unparse = "prod(" ++ sl.unparse ++ ", " ++ unparseTyVars(boundvars, upns.boundVariables) ++ ", " ++ upns.unparse ++ ")";

  top.namedSignature = ns;  
  top.typerep = productionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));
  forwards to defaultDcl();
}
abstract production funDcl
top::DclInfo ::= sg::String sl:: Location ns::Decorated NamedSignature
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = ns.fullName;
  
  local attribute boundvars :: [TyVar];
  boundvars = top.typerep.freeVariables;
  
  local attribute upns :: NamedSignature;
  upns = new(ns);
  upns.boundVariables = top.boundVariables ++ boundvars;
  
  top.unparse = "fun(" ++ sl.unparse ++ ", " ++ unparseTyVars(boundvars, upns.boundVariables) ++ ", " ++ upns.unparse ++ ")";

  top.namedSignature = ns;  
  top.typerep = functionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements));
  forwards to defaultDcl();
}
abstract production globalValueDcl
top::DclInfo ::= sg::String sl:: Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't
  top.unparse = "glob(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ")";

  top.typerep = ty;
  forwards to defaultDcl();
}

-- -- interface types
abstract production ntDcl
top::DclInfo ::= sg::String sl:: Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "nt(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;
  forwards to defaultDcl();
}
abstract production termDcl
top::DclInfo ::= sg::String sl:: Location fn::String regex::Regex_R
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = "term(" ++ sl.unparse ++ ", '" ++ fn ++ "', /" ++ regex.regString ++ "/)";
  
  top.typerep = terminalTypeExp(fn);
  top.dclBoundVars = [];
  forwards to defaultDcl();
}
abstract production lexTyVarDcl
top::DclInfo ::= sg::String sl:: Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Lexical type variables dcls should never appear in interface files. (This is the DclInfo complaining here.)");
  
  top.typerep = ty;
  top.dclBoundVars = [];
  forwards to defaultDcl();
}

-- -- interface Attributes
abstract production synDcl
top::DclInfo ::= sg::String sl:: Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "syn(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;
  forwards to defaultDcl();
}
abstract production inhDcl
top::DclInfo ::= sg::String sl:: Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "inh(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;
  forwards to defaultDcl();
}

-- -- interface Production attr (values)
abstract production paDcl
top::DclInfo ::= sg::String sl:: Location fn::String outty::TypeExp intys::[TypeExp] dcls::Defs
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  
  local attribute newboundvars :: [TyVar];
  newboundvars = productionTypeExp(outty, intys).freeVariables;
  local attribute boundvars :: [TyVar];
  boundvars = top.boundVariables ++ newboundvars;
  
  outty.boundVariables = boundvars;
  top.unparse = "p@(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(newboundvars, boundvars) ++ ", " ++ outty.unparse ++ " ::= " ++ unparseTypes(intys, boundvars) ++ ", [" ++ unparseDefs(dcls, boundvars) ++ "])";
  
  top.prodDefs = dcls;
  top.typerep = productionTypeExp(outty, intys); -- Using 'production' here, despite also working on 'function's
  forwards to defaultDcl();
}
abstract production forwardDcl
top::DclInfo ::= sg::String sl:: Location ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = "forward";
  
  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't  
  top.unparse = "fwd(" ++ sl.unparse ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  forwards to defaultDcl();
  
  top.substitutedDclInfo = forwardDcl(sg,sl, performSubstitution(ty, top.givenSubstitution));
}

-- -- interface other
abstract production occursDcl
top::DclInfo ::= sg::String sl:: Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fnnt;
  
  ntty.boundVariables = top.boundVariables ++ ntty.freeVariables;
  atty.boundVariables = ntty.boundVariables;
  top.unparse = "@(" ++ sl.unparse ++ ", '" ++ fnnt ++ "', '" ++ fnat ++ "', " ++ 
                       unparseTyVars(ntty.freeVariables, ntty.boundVariables) ++ ", " ++
                       ntty.unparse ++ ", " ++ 
                       atty.unparse ++ ")";

  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local attribute subst :: Substitution;
  subst = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT
  
  top.typerep = if subst.failure
                then -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
                     freshenCompletely(atty)
                else performSubstitution(atty, subst);
  
  top.attrOccurring = fnat;
  forwards to defaultDcl();
}

-- TODO: this should probably go elsewhere?
function determineAttributeType
TypeExp ::= occursDclInfo::Decorated DclInfo ntty::TypeExp
{
  return decorate new(occursDclInfo) with { givenNonterminalType = ntty; } . typerep;
}

-- Dealing with substitutions for production attributes
function performSubstitutionDclInfo
Decorated DclInfo ::= d::Decorated DclInfo s::Substitution
{
  local attribute dcl :: DclInfo;
  dcl = new(d);
  dcl.givenSubstitution = s;
  
  return decorate dcl.substitutedDclInfo with {};
}

function defsFromPADcls
Defs ::= d::[Decorated DclInfo] s::Decorated NamedSignature
{
  -- We want to rewrite FROM the sig these PAs were declared with, TO the given sig
  local attribute subst :: Substitution;
  subst = unifyDirectional( head(d).typerep,  productionTypeExp(s.outputElement.typerep, getTypesSignature(s.inputElements)));
  
  
  return if null(d) then emptyDefs()
         else if subst.failure
              then defsFromPADcls(tail(d), s) -- this can happen if the aspect sig is wrong. Error already reported. error("INTERNAL ERROR: PA subst unify error")
              else appendDefs( substitutedDefs( head(d).prodDefs, subst ), defsFromPADcls(tail(d), s));
}
