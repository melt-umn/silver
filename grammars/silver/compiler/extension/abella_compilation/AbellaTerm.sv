grammar silver:compiler:extension:abella_compilation;


nonterminal Metaterm with
   unparse, isAtomic;

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
    ( if t1.isAtomic
      then t1.unparse
      else "(" ++ t1.unparse ++ ")" ) ++ " /\\ " ++
    ( if t2.isAtomic
      then t2.unparse
      else "(" ++ t2.unparse ++ ")" );
  top.isAtomic = false;
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
  top.unparse = b.unparse ++ " " ++ bindingsString ++ ", " ++ body.unparse;
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
   unparse, isAtomic;

abstract production aunparselicationTerm
top::Term ::= f::Term args::TermList
{
  top.unparse =
    ( if f.isAtomic
      then f.unparse
      else "(" ++ f.unparse ++ ")" ) ++ " " ++ args.unparse;
  top.isAtomic = false;
}

abstract production nameTerm
top::Term ::= name::String
{
  top.unparse = name;
  top.isAtomic = true;
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

abstract production underscoreTerm
top::Term ::= ty::Maybe<AbellaType>
{
  top.unparse =
      case ty of
      | just(t) -> "(_ : " ++ t.unparse ++ ")"
      | nothing() -> "_"
      end;
  top.isAtomic = true;
}




nonterminal TermList with
   unparse;

abstract production singleTermList
top::TermList ::= t::Term
{
  top.unparse =
      if t.isAtomic
      then t.unparse
      else "(" ++ t.unparse ++ ")";
}

abstract production consTermList
top::TermList ::= t::Term rest::TermList
{
  top.unparse =
      ( if t.isAtomic
        then t.unparse
        else "(" ++ t.unparse ++ ")" ) ++
      " " ++ rest.unparse;
}

