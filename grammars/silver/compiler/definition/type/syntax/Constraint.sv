grammar silver:compiler:definition:type:syntax;

autocopy attribute instanceHead::Maybe<Context>;
autocopy attribute constraintSigName::Maybe<String>;

nonterminal ConstraintList
  -- This grammar doesn't export silver:compiler:definition:core, so the type concrete
  -- syntax doesn't "know about" the core layout terminals.
  -- Thus we have to set the layout explicitly for the "root" nonterminal here.
  layout {BlockComments, Comments, WhiteSpace}
  with config, grammarName, env, flowEnv, location, unparse, errors, defs, contexts, lexicalTypeVariables, lexicalTyVarKinds, instanceHead, constraintSigName;
nonterminal Constraint with config, grammarName, env, flowEnv, location, unparse, errors, defs, contexts, lexicalTypeVariables, lexicalTyVarKinds, instanceHead, constraintSigName;

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
    case top.instanceHead of
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
    case top.instanceHead of
    | just(h) when h matches instContext(_, skolemType(_)) ->
      [err(top.location, s"The constraint ${top.unparse} is no smaller than the instance head ${prettyContext(h)}")]
    | _ -> []
    end;
  
  local instDcl::DclInfo =
    case top.constraintSigName, top.instanceHead of
    | just(sigfn), _ -> sigConstraintDcl(fName, t.typerep, sigfn, sourceGrammar=top.grammarName, sourceLocation=top.location)
    | nothing(), just(_) -> instConstraintDcl(fName, t.typerep, sourceGrammar=top.grammarName, sourceLocation=top.location)
    | _, _ -> instSuperDcl(
      fName,
      currentInstDcl(error("Class name shouldn't be needed"), t.typerep, sourceGrammar=top.grammarName, sourceLocation=top.location),
      t.typerep,
      sourceGrammar=top.grammarName, sourceLocation=top.location)
    end;
  top.defs <- [tcInstDef(instDcl)];
  top.defs <- transitiveSuperDefs(top.env, t.typerep, [], fName, instDcl);

  top.lexicalTyVarKinds <-
    case t of
    | typeVariableTypeExpr(tv) -> [pair(tv.lexeme, c.lookupType.typeScheme.monoType.kindArity)]
    | _ -> []
    end;
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

function sameSuperContext
Boolean ::= c1::Context c2::Context
{
  return
    case c1, c2 of
    | instContext(c1, _), instContext(c2, _) -> c1 == c2
    end;
}

function transitiveSuperDefs
[Def] ::= env::Decorated Env ty::Type seenClasses::[String] className::String instDcl::DclInfo
{
  local dcls::[DclInfo] = getTypeDcl(className, env);
  local dcl::DclInfo = head(dcls);
  dcl.givenInstanceType = ty;
  local superClassNames::[String] = catMaybes(map((.contextClassName), dcl.superContexts));
  local superInstDcl::DclInfo =
    instSuperDcl(className, instDcl, ty, sourceGrammar=instDcl.sourceGrammar, sourceLocation=instDcl.sourceLocation);
  return
    if null(dcls) || contains(dcl.fullName, seenClasses)
    then []
    else
      -- This might introduce duplicate defs in "diamond subclassing" cases,
      -- but that shouldn't actually be an issue besides the (minor) added lookup overhead.
      map(\ c::Context -> c.contextSuperDef(dcl.sourceGrammar, dcl.sourceLocation, instDcl), dcl.superContexts) ++
      concat(map(transitiveSuperDefs(env, ty, dcl.fullName :: seenClasses, _, superInstDcl), superClassNames));
}
