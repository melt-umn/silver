grammar silver:compiler:definition:type:syntax;

autocopy attribute constraintPos::ConstraintPosition;

nonterminal ConstraintList
  -- This grammar doesn't export silver:compiler:definition:core, so the type concrete
  -- syntax doesn't "know about" the core layout terminals.
  -- Thus we have to set the layout explicitly for the "root" nonterminal here.
  layout {BlockComments, Comments, WhiteSpace}
  with config, grammarName, env, flowEnv, location, unparse, errors, defs, contexts, lexicalTypeVariables, lexicalTyVarKinds, constraintPos;
nonterminal Constraint with config, grammarName, env, flowEnv, location, unparse, errors, defs, contexts, lexicalTypeVariables, lexicalTyVarKinds, constraintPos;

propagate errors, defs, lexicalTypeVariables, lexicalTyVarKinds on ConstraintList, Constraint;

concrete production consConstraint
top::ConstraintList ::= h::Constraint ',' t::ConstraintList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
  top.contexts = h.contexts ++ t.contexts;
}
concrete production oneConstraint
top::ConstraintList ::= h::Constraint
{
  top.unparse = h.unparse;
  top.contexts = h.contexts;
}
abstract production nilConstraint
top::ConstraintList ::=
{
  top.unparse = "";
  top.contexts = [];
}

concrete production classConstraint
top::Constraint ::= c::QNameType t::TypeExpr
{
  top.unparse = c.unparse ++ " " ++ t.unparse;
  top.contexts =
    case top.constraintPos.instanceHead of
    | just(instContext(_, skolemType(_))) -> [] -- Avoid a cycle in instance resolution checking
    | _ -> [instContext(fName, t.typerep)]
    end;
  
  production dcl::DclInfo = c.lookupType.dcl;
  production fName::String = c.lookupType.fullName;
  
  top.errors <- c.lookupType.errors;
  top.errors <-
    if c.lookupType.found && dcl.isClass then []
    else [err(c.location, c.name ++ " is not a type class.")];
  top.errors <- t.errorsTyVars;
  
  -- We essentially permit FlexibleInstances but not UndecidableInstnaces,
  -- so we need to check that there are no class constraints if instance head is a type variable.
  top.errors <-
    case top.constraintPos.instanceHead of
    | just(h) when h matches instContext(_, skolemType(_)) ->
      [err(top.location, s"The constraint ${top.unparse} is no smaller than the instance head ${prettyContext(h)}")]
    | _ -> []
    end;
  
  local instDcl::DclInfo = top.constraintPos.classInstDcl(fName, t.typerep, top.grammarName, top.location);
  top.defs <- [tcInstDef(instDcl)];
  top.defs <- transitiveSuperDefs(top.env, t.typerep, [], instDcl);

  top.lexicalTyVarKinds <-
    case t of
    | typeVariableTypeExpr(tv)
      -- Avoid circular inference if someone uses a class constraint within its own definition
      when top.constraintPos.classDefName != just(fName) ->
      [pair(tv.lexeme, c.lookupType.typeScheme.monoType.kindrep)]
    | _ -> []
    end;
}

concrete production typeableConstraint
top::Constraint ::= 'runtimeTypeable' t::TypeExpr
{
  top.unparse = "runtimeTypeable " ++ t.unparse;
  top.contexts = [typeableContext(t.typerep)];
  
  top.errors <- t.errorsTyVars;
  top.errors <- t.errorsKindStar;

  local instDcl::DclInfo = top.constraintPos.typeableInstDcl(t.typerep, top.grammarName, top.location);
  top.defs <- [tcInstDef(instDcl)];
}

concrete production inhSubsetConstraint
top::Constraint ::= i1::TypeExpr 'subset' i2::TypeExpr
{
  top.unparse = i1.unparse ++ " subset " ++ i2.unparse;
  top.contexts = [inhSubsetContext(i1.typerep, i2.typerep)];

  top.errors <-
    if i1.typerep.kindrep != inhSetKind()
    then [err(top.location, s"${top.unparse} has kind ${prettyKind(i1.typerep.kindrep)}, but kind InhSet is expected here")]
    else [];
  top.errors <-
    if i2.typerep.kindrep != inhSetKind()
    then [err(top.location, s"${top.unparse} has kind ${prettyKind(i2.typerep.kindrep)}, but kind InhSet is expected here")]
    else [];

  local instDcl::DclInfo = top.constraintPos.inhSubsetInstDcl(i1.typerep, i2.typerep, top.grammarName, top.location);
  top.defs <-
    case top.constraintPos of
    | classPos(_, _) -> []
    | _ -> [tcInstDef(instDcl)]
    end;
  top.errors <-
    case top.constraintPos of
    | classPos(_, _) -> [err(top.location, "subset constraint not permitted as superclass")]
    | _ -> []
    end;

  top.lexicalTyVarKinds <-
    case i1 of
    | typeVariableTypeExpr(tv) -> [pair(tv.lexeme, inhSetKind())]
    | _ -> []
    end;
  top.lexicalTyVarKinds <-
    case i2 of
    | typeVariableTypeExpr(tv) -> [pair(tv.lexeme, inhSetKind())]
    | _ -> []
    end;
}

synthesized attribute classInstDcl::(DclInfo ::= String Type String Location);
synthesized attribute typeableInstDcl::(DclInfo ::= Type String Location);
synthesized attribute inhSubsetInstDcl::(DclInfo ::= Type Type String Location);
synthesized attribute classDefName::Maybe<String>;
synthesized attribute instanceHead::Maybe<Context>;
nonterminal ConstraintPosition with classInstDcl, typeableInstDcl, inhSubsetInstDcl, classDefName, instanceHead;

aspect default production
top::ConstraintPosition ::=
{
  top.classDefName = nothing();
  top.instanceHead = nothing();
}
abstract production instancePos
top::ConstraintPosition ::= instHead::Context tvs::[TyVar]
{
  top.classInstDcl = instConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
  top.typeableInstDcl = typeableInstConstraintDcl(_, tvs, sourceGrammar=_, sourceLocation=_);
  top.inhSubsetInstDcl = inhSubsetInstConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
  top.instanceHead = just(instHead);
}
abstract production classPos
top::ConstraintPosition ::= className::String tvs::[TyVar]
{
  top.classInstDcl = \ fName::String t::Type g::String l::Location ->
    instSuperDcl(fName,
      currentInstDcl(error("Class name shouldn't be needed"), t, sourceGrammar=g, sourceLocation=l),
      sourceGrammar=g, sourceLocation=l);
  top.typeableInstDcl = \ t::Type g::String l::Location ->
    typeableSuperDcl(
      currentInstDcl(error("Class name shouldn't be needed"), t, sourceGrammar=g, sourceLocation=l),
      sourceGrammar=g, sourceLocation=l);
  top.inhSubsetInstDcl = error("subset constraint not permitted as superclass");
  top.classDefName = just(className);
}
abstract production classMemberPos
top::ConstraintPosition ::= className::String tvs::[TyVar]
{
  top.classInstDcl = instConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
  top.typeableInstDcl = typeableInstConstraintDcl(_, tvs, sourceGrammar=_, sourceLocation=_);
  top.inhSubsetInstDcl = inhSubsetInstConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
  top.classDefName = just(className);
  -- A bit strange, but class member constraints are sort of like instance constraints.
  -- However we don't know what the instance type actually is, and want to skip the
  -- decidability check, so just put errorType here for now.
  top.instanceHead = just(instContext(className, errorType()));
}
abstract production signaturePos
top::ConstraintPosition ::= sig::NamedSignature
{
  top.classInstDcl = sigConstraintDcl(_, _, sig, sourceGrammar=_, sourceLocation=_);
  top.typeableInstDcl = typeableSigConstraintDcl(_, sig, sourceGrammar=_, sourceLocation=_);
  top.inhSubsetInstDcl = inhSubsetSigConstraintDcl(_, _, sig, sourceGrammar=_, sourceLocation=_);
}
abstract production globalPos
top::ConstraintPosition ::= tvs::[TyVar]
{
  -- These are translated the same as instance constraints.
  top.classInstDcl = instConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
  top.typeableInstDcl = typeableInstConstraintDcl(_, tvs, sourceGrammar=_, sourceLocation=_);
  top.inhSubsetInstDcl = inhSubsetInstConstraintDcl(_, _, tvs, sourceGrammar=_, sourceLocation=_);
}

function transitiveSuperContexts
[Context] ::= env::Decorated Env ty::Type seenClasses::[String] className::String
{
  local dcls::[DclInfo] = getTypeDcl(className, env);
  local dcl::DclInfo = head(dcls);
  dcl.givenInstanceType = ty;
  local superClassNames::[String] = catMaybes(map((.contextClassName), dcl.superContexts));
  return
    if null(dcls) || contains(dcl.fullName, seenClasses)
    then []
    else unionsBy(
      sameSuperContext,
      dcl.superContexts ::
      map(transitiveSuperContexts(env, ty, dcl.fullName :: seenClasses, _), superClassNames));
}

-- TODO: Should be an equality attribute, maybe?
function sameSuperContext
Boolean ::= c1::Context c2::Context
{
  return
    case c1, c2 of
    | instContext(c1, _), instContext(c2, _) -> c1 == c2
    | typeableContext(_), typeableContext(_) -> true
    | _, _ -> false
    end;
}

function transitiveSuperDefs
[Def] ::= env::Decorated Env ty::Type seenClasses::[String] instDcl::DclInfo
{
  local dcls::[DclInfo] = getTypeDcl(instDcl.fullName, env);
  local dcl::DclInfo = head(dcls);
  dcl.givenInstanceType = ty;
  local superClassNames::[String] = catMaybes(map((.contextClassName), dcl.superContexts));
  local superInstDcls::[DclInfo] =
    map(
      instSuperDcl(_, instDcl, sourceGrammar=instDcl.sourceGrammar, sourceLocation=instDcl.sourceLocation),
      superClassNames);
  return
    if null(dcls) || contains(dcl.fullName, seenClasses)
    then []
    else
      -- This might introduce duplicate defs in "diamond subclassing" cases,
      -- but that shouldn't actually be an issue besides the (minor) added lookup overhead.
      map(\ c::Context -> tcInstDef(c.contextSuperDcl(instDcl, dcl.sourceGrammar, dcl.sourceLocation)), dcl.superContexts) ++
      flatMap(transitiveSuperDefs(env, ty, dcl.fullName :: seenClasses, _), superInstDcls);
}
