grammar silver:compiler:extension:abella_compilation:abella;


monoid attribute freeVars::[(String, Integer)] with [], ++;
propagate freeVars on Metaterm, Term, TermList
   excluding varTerm;

--Replace variables
inherited attribute replaceTermVar::(String, Integer);
inherited attribute replaceTerm::Term;
functor attribute replaced;
propagate replaced on Metaterm, Term, TermList,
             ParenthesizedArgs, ListContents, PairContents
   excluding varTerm;

--Expand names to their fully-qualified version
inherited attribute replaceName::String;
functor attribute replacedName;
propagate replacedName on Metaterm, Term, TermList,
             ParenthesizedArgs, ListContents, PairContents
   excluding nameTerm;


nonterminal Metaterm with
   unparse, isAtomic, isAnd,
   freeVars,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;
propagate replaceTermVar, replaceTerm, replaceName on Metaterm;

aspect default production
top::Metaterm ::=
{
  top.isAnd = false;
}

abstract production termMetaterm
top::Metaterm ::= t::Term
{
  top.unparse = t.unparse;
  top.isAtomic = true;
}

abstract production trueMetaterm
top::Metaterm ::=
{
  top.unparse = "true";
  top.isAtomic = true;
}

abstract production falseMetaterm
top::Metaterm ::=
{
  top.unparse = "false";
  top.isAtomic = true;
}

abstract production eqMetaterm
top::Metaterm ::= t1::Term t2::Term
{
  top.unparse = t1.unparse ++ " = " ++ t2.unparse;
  top.isAtomic = true;
}

abstract production impliesMetaterm
top::Metaterm ::= t1::Metaterm t2::Metaterm
{
  top.unparse = (if t1.isAtomic
            then t1.unparse
            else "(" ++ t1.unparse ++ ")") ++ " -> " ++ t2.unparse;
  top.isAtomic = false;
}

abstract production orMetaterm
top::Metaterm ::= t1::Metaterm t2::Metaterm
{
  top.unparse =
    ( if t1.isAtomic
      then t1.unparse
      else "(" ++ t1.unparse ++ ")" ) ++ " \\/ " ++
    ( if t2.isAtomic
      then t2.unparse
      else "(" ++ t2.unparse ++ ")" );
  top.isAtomic = false;
}

abstract production andMetaterm
top::Metaterm ::= t1::Metaterm t2::Metaterm
{
  top.unparse =
    ( if t1.isAtomic || t1.isAnd
      then t1.unparse
      else "(" ++ t1.unparse ++ ")" ) ++
    --Since these are for definitions, these are usually better read
    --   by putting each conjunct on its own line
    " /\\\n      " ++
    ( if t2.isAtomic || t2.isAnd
      then t2.unparse
      else "(" ++ t2.unparse ++ ")" );
  top.isAtomic = false;
  top.isAnd = true;
}

abstract production bindingMetaterm
top::Metaterm ::= b::Binder
                  nameBindings::[Pair<String Maybe<AbellaType>>]
                  body::Metaterm
{
  local bindings::[String] =
        map(\ p::Pair<String Maybe<AbellaType>> ->
              case p of
              | (name, just(ty)) -> "(" ++ name ++ " : " ++ ty.unparse ++ ")"
              | (name, nothing()) -> name
              end,
            nameBindings);
  local bindingsString::String =
     if null(bindings)
     then error("Empty bindings not allowed; production bindingsMetaterm (" ++ body.unparse ++ ")")
     else foldr1(\ a::String b::String -> a ++ " " ++ b, bindings);
  top.unparse = b.unparse ++ " " ++ bindingsString ++ ",\n      " ++
                body.unparse;
  top.isAtomic = false;
}



nonterminal Binder with unparse;

abstract production forallBinder
top::Binder ::=
{
  top.unparse = "forall";
}

abstract production existsBinder
top::Binder ::=
{
  top.unparse = "exists";
}

abstract production nablaBinder
top::Binder::=
{
  top.unparse = "nabla";
}




nonterminal Term with
   unparse, isAtomic, freeVars,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;
propagate replaceTermVar, replaceTerm, replaceName on Term;

{-
  A varTerm is used to represent a name generated in encoding.  We
  want to distinguish between varTerms and nameTerms because nameTerms
  are set names which cannot change (e.g. productions) and varTerms
  are just placeholders which we might change in unification later.

  The base is the suggested base for name generation.  The index is
  for distinguishing between varTerms with the same suggested base.
-}
abstract production varTerm
top::Term ::= base::String index::Integer
{
  top.unparse = "var(" ++ base ++ ", " ++ toString(index) ++ ")";
  top.isAtomic = true;
  top.freeVars := [(base, index)];

  top.replaced =
      if top.replaceTermVar.1 == base && top.replaceTermVar.2 == index
      then top.replaceTerm
      else top;
}

abstract production nameTerm
top::Term ::= name::String
{
  top.unparse = name;
  top.isAtomic = true;

  top.replacedName =
      if top.replaceName == name
      then top.replaceTerm
      else top;
}

abstract production applicationTerm
top::Term ::= f::Term args::TermList
{
  top.unparse =
    ( if f.isAtomic
      then f.unparse
      else "(" ++ f.unparse ++ ")" ) ++ " " ++ args.unparse;
  top.isAtomic = false;
}

abstract production consTerm
top::Term ::= t1::Term t2::Term
{
  top.unparse =
    ( if t1.isAtomic
      then t1.unparse
      else "(" ++ t1.unparse ++ ")" ) ++ "::" ++
    ( if t2.isAtomic
      then t2.unparse
      else "(" ++ t2.unparse ++ ")" );
  top.isAtomic = false;
}

abstract production nilTerm
top::Term ::=
{
  top.unparse = "nil";
  top.isAtomic = true;
}




synthesized attribute argList::[Term];

nonterminal TermList with
   unparse, freeVars, argList,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;
propagate replaceTermVar, replaceTerm, replaceName on TermList;

abstract production nilTermList
top::TermList ::=
{
  top.unparse = "";
  top.argList = [];
}

abstract production singleTermList
top::TermList ::= t::Term
{
  top.unparse =
      if t.isAtomic
      then t.unparse
      else "(" ++ t.unparse ++ ")";
  top.argList = [t];
}

abstract production consTermList
top::TermList ::= t::Term rest::TermList
{
  top.unparse =
      ( if t.isAtomic
        then t.unparse
        else "(" ++ t.unparse ++ ")" ) ++
      " " ++ rest.unparse;
  top.argList = t::rest.argList;
}



--Helper functions for more easily replacing variables
function replaceVar
Metaterm ::= replaceTermVar::(String, Integer) replaceResult::Term
             replaceIn::Metaterm
{
  return
     decorate replaceIn with
     { replaceTermVar = replaceTermVar;
       replaceTerm = replaceResult; }.replaced;
}

function replaceVar_list
[Metaterm] ::= replaceTermVar::(String, Integer) replaceResult::Term
               replaceIn::[Metaterm]
{
  return map(replaceVar(replaceTermVar, replaceResult, _), replaceIn);
}

function replaceVar_Term
Term ::= replaceTermVar::(String, Integer) replaceResult::Term
         replaceIn::Term
{
  return
     decorate replaceIn with
     { replaceTermVar = replaceTermVar;
       replaceTerm = replaceResult; }.replaced;
}


--Helper functions for more easily replacing names
function replaceName
Metaterm ::= replaceName::String replaceResult::Term replaceIn::Metaterm
{
  return
     decorate replaceIn with
     { replaceName = replaceName;
       replaceTerm = replaceResult; }.replacedName;
}

function replaceNames
Metaterm ::= lst::[(String, Term)] replaceIn::Metaterm
{
  return
     case lst of
     | [] -> replaceIn
     | (name, term)::rest ->
       replaceNames(rest, replaceName(name, term, replaceIn))
     end;
}

