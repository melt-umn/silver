grammar silver:compiler:extension:abella_compilation:abella;


abstract production plusMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " + " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production minusMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " - " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production multiplyMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " * " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production divideMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " / " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production modulusMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " mod " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production negateMetaterm
top::Metaterm ::= t::Term result::Term
{
  top.unparse = "- " ++ t.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production lessMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " < " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production lessEqMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " <= " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production greaterMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " > " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production greaterEqMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " >= " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production appendMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " ++ " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production orBoolMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " || " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production andBoolMetaterm
top::Metaterm ::= t1::Term t2::Term result::Term
{
  top.unparse = t1.unparse ++ " && " ++ t2.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production notBoolMetaterm
top::Metaterm ::= t::Term result::Term
{
  top.unparse = "! " ++ t.unparse ++ " = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production funMetaterm
top::Metaterm ::= funName::String args::ParenthesizedArgs result::Term
{
  top.unparse = funName ++ "(" ++ args.unparse ++ ") = " ++ result.unparse;
  top.isAtomic = true;
}

abstract production attrAccessMetaterm
top::Metaterm ::= tree::String attr::String val::Term
{
  top.unparse = tree ++ "." ++ attr ++ " = " ++ val.unparse;
  top.isAtomic = true;
}

abstract production attrAccessEmptyMetaterm
top::Metaterm ::= tree::String attr::String
{
  top.unparse = tree ++ "." ++ attr ++ " = <no value>";
  top.isAtomic = true;
}


abstract production localAttrAccessMetaterm
top::Metaterm ::= tree::String attr::String val::Term
{
  top.unparse = "local " ++ tree ++ "." ++ attr ++ " = " ++ val.unparse;
  top.isAtomic = true;
}

abstract production localAttrAccessEmptyMetaterm
top::Metaterm ::= tree::String attr::String
{
  top.unparse = "local " ++ tree ++ "." ++ attr ++ " = <no value>";
  top.isAtomic = true;
}



--TERMS
abstract production intTerm
top::Term ::= i::Integer
{
  top.unparse = toString(i);
  top.isAtomic = true;
}

abstract production stringTerm
top::Term ::= contents::String
{
  top.unparse = "\"" ++ contents ++ "\"";
  top.isAtomic = true;
}

abstract production trueTerm
top::Term ::=
{
  top.unparse = "true";
  top.isAtomic = true;
}

abstract production falseTerm
top::Term ::=
{
  top.unparse = "false";
  top.isAtomic = true;
}

abstract production listTerm
top::Term ::= contents::ListContents
{
  top.unparse = "[" ++ contents.unparse ++ "]";
  top.isAtomic = true;
}

abstract production pairTerm
top::Term ::= contents::PairContents
{
  top.unparse = "(" ++ contents.unparse ++ ")";
  top.isAtomic = true;
}

abstract production charTerm
top::Term ::= char::String
{
  top.unparse = "\"" ++ char ++ "\"";
  top.isAtomic = true;
}

abstract production prodTerm
top::Term ::= prodName::String args::ParenthesizedArgs
{
  top.unparse = prodName ++ "(" ++ args.unparse ++ ")";
  top.isAtomic = true;
}




nonterminal ParenthesizedArgs with
   unparse,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;

abstract production emptyParenthesizedArgs
top::ParenthesizedArgs ::=
{
  top.unparse = "";
}

abstract production addParenthesizedArgs
top::ParenthesizedArgs ::= t::Term rest::ParenthesizedArgs
{
  top.unparse = t.unparse ++ (if rest.unparse == "" then "" else ", " ++ rest.unparse);
}




nonterminal ListContents with
   unparse,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;

abstract production emptyListContents
top::ListContents ::=
{
  top.unparse = "";
}

abstract production addListContents
top::ListContents ::= t::Term rest::ListContents
{
  top.unparse = t.unparse ++ (if rest.unparse == "" then "" else ", " ++ rest.unparse);
}




nonterminal PairContents with
   unparse,
   replaceTermVar, replaceTerm, replaced, replaceName, replacedName;

abstract production singlePairContents
top::PairContents ::= t::Term
{
  top.unparse = t.unparse;
}

abstract production addPairContents
top::PairContents ::= t::Term rest::PairContents
{
  top.unparse = t.unparse ++ ", " ++ rest.unparse;
}





{-
  This is only for parsing purposes.

  Our purpose here is essentially to ensure parentheses aren't lost.
  Whether it is actually intended to be a function/production or an
  Abella application can be determined by the interface once we get
  there, as long as we keep the parentheses around for when we print
  it into the interface file, so 
-}
abstract production disambiguateApplicationTerm
top::Term ::= f::Term args::TermList
{
  top.unparse = f.unparse ++ " " ++ args.unparse;

  local fwd::Term =
        case f, args of
        | nameTerm(prod),
          singleTermList(pairTerm(contents)) ->
          applicationTerm(f, args)
        | _, _ -> applicationTerm(f, args)
        end;
  forwards to fwd;
}

abstract production emptySilverApplicationTerm
top::Term ::= f::Term
{
  top.unparse = f.unparse ++ "()";
}

