grammar silver:definition:type:syntax;

autocopy attribute instanceHead::Maybe<Context>;
autocopy attribute constraintSigName::Maybe<String>;

nonterminal OptConstraintList with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables, instanceHead, constraintSigName;
nonterminal ConstraintList with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables, instanceHead, constraintSigName;
nonterminal Constraint with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables, instanceHead, constraintSigName;

propagate errors, defs, lexicalTypeVariables on OptConstraintList, ConstraintList, Constraint;

concrete production someConstraintList
top::OptConstraintList ::= cl::ConstraintList '=>'
{
  top.unparse = cl.unparse ++ " =>";
  top.contexts = cl.contexts;
}
concrete production noConstraintList
top::OptConstraintList ::=
{
  top.unparse = "";
  top.contexts = [];
}

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
top::Constraint ::= c::QName t::TypeExpr
{
  top.contexts =
    case top.instanceHead of
    | just(instContext(_, skolemType(_))) -> [] -- Avoid a cycle in instance resolution checking
    | _ -> [instContext(dcl.fullName, t.typerep)]
    end;
  
  production dcl::DclInfo = c.lookupType.dcl;
  
  top.errors <- c.lookupType.errors;
  top.errors <-
    if dcl.isClass then []
    else [err(c.location, c.name ++ " is not a type class.")];
  top.errors <- t.errorsTyVars;
  
  -- We essentially permit FlexibleInstances but not UndecidableInstnaces,
  -- so we need to check that there are no class constraints if instance head is a type variable.
  top.errors <-
    case top.instanceHead of
    | just(h) when h matches instContext(_, skolemType(_)) ->
      [err(top.location, s"The constraint ${top.unparse} is no smaller than the instance head ${prettyContext(h)}")]
    | nothing() -> []
    end;
  
  top.defs <-
    case top.constraintSigName of
    | just(sigfn) -> [sigConstraintDef(top.grammarName, top.location, dcl.fullName, t.typerep, sigfn)]
    | nothing() -> [instConstraintDef(top.grammarName, top.location, dcl.fullName, t.typerep)]
    end;
  top.defs <- transitiveSuperDefs(top.env, t.typerep, [], dcl.fullName);
}

function transitiveSuperContexts
[Context] ::= env::Decorated Env ty::Type seenClasses::[String] className::String
{
  local dcls::[DclInfo] = getTypeDcl(className, env);
  local dcl::DclInfo = head(dcls);
  dcl.givenInstanceType = ty;
  local superClassNames::[String] = catMaybes(map((.contextClassName), dcl.superContexts));
  return
    if null(dcls) || containsBy(stringEq, dcl.fullName, seenClasses)
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
[Def] ::= env::Decorated Env ty::Type seenClasses::[String] className::String
{
  local dcls::[DclInfo] = getTypeDcl(className, env);
  local dcl::DclInfo = head(dcls);
  dcl.givenInstanceType = ty;
  local superClassNames::[String] = catMaybes(map((.contextClassName), dcl.superContexts));
  return
    if null(dcls) || containsBy(stringEq, dcl.fullName, seenClasses)
    then []
    else
      -- This might introduce duplicate defs in "diamond subclassing" cases,
      -- but that shouldn't actually be an issue besides the (minor) added lookup overhead.
      map(\ c::Context -> c.contextSuperDef(dcl.sourceGrammar, dcl.sourceLocation, dcl), dcl.superContexts) ++
      concat(map(transitiveSuperDefs(env, ty, dcl.fullName :: seenClasses, _), superClassNames));
}
