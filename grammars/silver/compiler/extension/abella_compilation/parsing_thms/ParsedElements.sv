grammar silver:compiler:extension:abella_compilation:parsing_thms;


{-
  These are the things we pick out of the interfaces and theorems
  files we parse.
-}
nonterminal ParsedElement with encode;


synthesized attribute encode::String;


abstract production extensibleMutualTheoremGroup
top::ParsedElement ::=
   --[(thm name, thm statement, induction tree)]
   newThms::[(String, Metaterm, String)]
   --thms introduced with a statement elsewhere
   oldThms::[String]
{
  top.encode =
      implode(",",
              map(\ p::(String, Metaterm, String) ->
                    p.1 ++ "&" ++ p.2.unparse ++ "&" ++ p.3,
                  newThms)) ++ ". ";
}


--Non-extensible mutuals are written all in one
abstract production nonextensibleTheorem
top::ParsedElement ::= name::String stmt::Metaterm
{
  top.encode = name ++ "&" ++ stmt.unparse ++ ". ";
}


abstract production splitElement
top::ParsedElement ::= toSplit::String newNames::[String]
{
  top.encode =
      "$Spl " ++ toSplit ++ " " ++ implode(",", newNames) ++ ". ";
}





nonterminal DefinitionElement with encode;

abstract production defineElement
top::DefinitionElement ::= defines::[(String, AbellaType)]
                           --Some clauses don't have bodies, so Maybe
                           clauses::[(Metaterm, Maybe<Metaterm>)]
{
  top.encode =
      "$Def " ++
      implode(",", map(\ p::(String, AbellaType) ->
                         p.1 ++ " : " ++ p.2.unparse,
                       defines)) ++
      implode(";", map(\ p::(Metaterm, Maybe<Metaterm>) ->
                         case p.2 of
                         | nothing() ->
                           p.1.unparse
                         | just(p2) ->
                           p.1.unparse ++ "," ++ p2.unparse
                         end, clauses)) ++ ". ";
}


function definitionElements_eq
Boolean ::= d1::DefinitionElement d2::DefinitionElement
{
  return
     case d1, d2 of
     | defineElement(l1, _), defineElement(l2, _) ->
       map(fst, l1) == map(fst, l2)
     end;
}

