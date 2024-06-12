grammar silver:compiler:analysis:typechecking:core;

import silver:compiler:definition:type;

synthesized attribute leftpp :: String;
synthesized attribute rightpp :: String;
synthesized attribute typeerror :: Boolean;

nonterminal TypeCheck with upSubst, downSubst, finalSubst, leftpp, rightpp, typeerror;

-- TODO: we could probably change this to 'expected' and 'actual' and generate the error message here?
abstract production check
top::TypeCheck ::= l::Type r::Type
{
  top.upSubst = unifyCheck(new(l), new(r), top.downSubst);

  top.typeerror = top.upSubst.failure;

  local finleft :: Type = performSubstitution(new(l), top.finalSubst);

  local finright :: Type = performSubstitution(new(r), top.finalSubst);

  local fv :: [TyVar] = setUnionTyVars(finleft.freeVariables, finright.freeVariables);

  top.leftpp = prettyTypeWith(new(finleft), fv);
  top.rightpp = prettyTypeWith(new(finright), fv);
}

abstract production checkNonterminal
top::TypeCheck ::= e::Env allowDecorableSkolems::Boolean l::Type
{
  local refined :: Type =
    performSubstitution(new(l), top.downSubst);

  top.upSubst = composeSubst(ignoreFailure(top.downSubst), refined.unifyInstanceNonterminal);

  top.typeerror =
    case performSubstitution(new(refined), top.upSubst) of
    | skolemType(_) -> !allowDecorableSkolems || null(searchEnvTree(refined.typeName, e.occursTree))
    | _ -> top.upSubst.failure && !refined.isError
    end;

  top.leftpp = prettyType(performSubstitution(new(l), top.finalSubst));
  top.rightpp = "a nonterminal";
}
abstract production checkDecorated
top::TypeCheck ::= l::Type
{
  local refined :: Type =
    performSubstitution(new(l), top.downSubst);

  top.upSubst = composeSubst(ignoreFailure(top.downSubst), refined.unifyInstanceDecorated);

  top.typeerror = top.upSubst.failure && !refined.isError;

  top.leftpp = prettyType(performSubstitution(new(l), top.finalSubst));
  top.rightpp = "a decorated nonterminal";
}
abstract production checkDecorable
top::TypeCheck ::= e::Env l::Type
{
  local refined :: Type =
    performSubstitution(new(l), top.downSubst);

  top.upSubst = composeSubst(ignoreFailure(top.downSubst), refined.unifyInstanceDecorable);

  top.typeerror =
    case performSubstitution(new(refined), top.upSubst) of
    | skolemType(_) -> null(searchEnvTree(refined.typeName, e.occursTree))
    | _ -> top.upSubst.failure && !refined.isError
    end;

  top.leftpp = prettyType(performSubstitution(new(l), top.finalSubst));
  top.rightpp = "a decorable type";
}

-- Specialize the reference set to the empty set, if it is unspecialized.
-- This introduces substitutions during the second pass, but doesn't produce any type errors.
fun specializeRefSet Substitution ::= s::Substitution t::Type =
  case performSubstitution(t, s) of
  | decoratedType(_, varType(i)) -> composeSubst(s, subst(i, inhSetType([])))
  | _ -> s
  end;
