grammar silver:compiler:extension:abella_compilation:abella;


function findAssociated
Maybe<a> ::= key::String container::[Pair<String a>]
{
  return case container of
         | [] -> nothing()
         | pair(a, b)::tl -> if key == a
                             then just(b)
                             else findAssociated(key, tl)
         end;
}


function replaceAssociated
Maybe<[(String, a)]> ::= key::String newVal::a container::[(String, a)]
{
  return case container of
         | [] -> nothing()
         | (a, b)::tl ->
           if key == a
           then just((a, newVal)::tl)
           else case replaceAssociated(key, newVal, tl) of
                | just(newtl) -> just((a, b)::newtl)
                | nothing() -> nothing()
                end
         end;
}



--Create all pairs of elements in a list
function allPairs
[(a, a)] ::= a::[a]
{
  return
     case a of
     | [] -> []
     | x::rest ->
       map(pair(x, _), rest) ++ allPairs(rest)
     end;
}


--Get all the combinations of bodies from the different lists
--e.g. [ [A, B], [C, D] ] => [ A ++ C, B ++ C, A ++ D, B ++ D ]
function combineBodies
[[Metaterm]] ::= bodies::[ [[Metaterm]] ]
{
  return
     case bodies of
     | [] -> []
     | h::rest ->
       combineBodies_head(h, rest) ++
       combineBodies(rest)
     end;
}
function combineBodies_head
[[Metaterm]] ::= current::[[Metaterm]] rest::[ [[Metaterm]] ]
{
  return
     case current of
     | [] -> []
     | hd::tl ->
       foldr(\ l::[[Metaterm]] rest::[[Metaterm]] ->
               map(\ x::[Metaterm] -> hd ++ x, l),
             [], rest) ++ combineBodies_head(tl, rest)
     end;
}




function buildApplication
Term ::= fun::Term args::[Term]
{
  return if null(args)
         then fun
         else applicationTerm(fun, buildApplicationArgs(args));
}

function buildApplicationArgs
TermList ::= args::[Term]
{
  return
     case args of
     | [] ->
       error("Should not call buildApplicationArgs with an empty list")
     | [x] -> singleTermList(x)
     | h::t -> consTermList(h, buildApplicationArgs(t))
     end;
}




function capitalize
String ::= s::String
{
    return
     if s == ""
     then ""
     else case substring(0, 1, s) of
          | "a" -> "A" | "b" -> "B" | "c" -> "C" | "d" -> "D" | "e" -> "E"
          | "f" -> "F" | "g" -> "G" | "h" -> "H" | "i" -> "I" | "j" -> "J"
          | "k" -> "K" | "l" -> "L" | "m" -> "M" | "n" -> "N" | "o" -> "O"
          | "p" -> "P" | "q" -> "Q" | "r" -> "R" | "s" -> "S" | "t" -> "T"
          | "u" -> "U" | "v" -> "V" | "w" -> "W" | "x" -> "X" | "y" -> "Y"
          | "z" -> "Z" |  _  -> substring(0, 1, s)
          end ++ substring(1, length(s), s);
}



--Get the root node from a node tree term
function nodetreeToNode
Term ::= ntr::Term
{
  return
     case ntr of
     | applicationTerm(_, consTermList(node, _)) -> node
     | _ -> error("Impossible nodetree structure")
     end;
}



--Make a name that isn't in usedNames, based on the type
function makeUniqueNameFromTy
String ::= ty::AbellaType usedNames::[String]
{
  local base::String =
        if tyIsNonterminal(ty)
        then splitEncodedName(ty.headTypeName.fromJust).2
        else case ty.headTypeName of
             | nothing() -> "A"
             | just("integer") -> "N"
             | just(str) ->
               if isAlpha(substring(0, 1, str))
               then --capitalize the first character
                    charsToString([head(stringToChars(substring(0, 1, str))) - 32])
               else substring(0, 1, str)
             end;
  return
     if contains(base, usedNames)
     then makeUniqueName(base, 1, usedNames)
     else base;
}

--Make anem that isn't in usedNames, starting with the given base
function makeUniqueNameFromBase
String ::= base::String usedNames::[String]
{
  return
     if contains(base, usedNames)
     then makeUniqueName(base, 1, usedNames)
     else base;
}

--Make a name starting with base that isn't in usedNames
function makeUniqueName
String ::= base::String index::Integer usedNames::[String]
{
  return
     if contains(base ++ toString(index), usedNames)
     then makeUniqueName(base, index + 1, usedNames)
     else base ++ toString(index);
}


--Generate n different names from the given base
function generateNames_n
[String] ::= base::String n::Integer
{
  return if n == 0
         then []
         else (base ++ toString(n))::generateNames_n(base, n - 1);
}




function termsEqual
Boolean ::= tm1::Term tm2::Term
{
  return
     case tm1, tm2 of
     | varTerm(s1, i1), varTerm(s2, i2) -> s1 == s2 && i1 == i2
     | nameTerm(s1), nameTerm(s2) -> s1 == s2
     | applicationTerm(f1, args1), applicationTerm(f2, args2) ->
       termsEqual(f1, f2) &&
       length(args1.argList) == length(args2.argList) &&
       foldr(\ p::(Term, Term) rest::Boolean ->
               rest && termsEqual(p.1, p.2),
             true, zipWith(pair(_, _), args1.argList, args2.argList))
     | consTerm(t11, t12), consTerm(t21, t22) ->
       termsEqual(t11, t21) && termsEqual(t12, t22)
     | nilTerm(), nilTerm() -> true
     | _, _ -> false
     end;
}


--Drop the qualifying names from the given name
function shortestName
String ::= name::String
{
  local index::Integer = lastIndexOf(":", name);
  return if index >= 0
         then substring(index + 1, length(name), name)
         else name;
}






{-
  Replace all varTerms with nameTerms with unique names
  @param hd The head term of the definition clauses
  @param bodies The bodies of the separate definition clauses
  @returns The head and bodies of the clauses with the varTerms
           replaced by unique names and all free names from each body
           bound over the top of it
-}
function fillVars
(Term, [Metaterm]) ::= hd::Term bodies::[Metaterm]
{
  local headNames::[String] = generateNamesFromVars(hd.freeVars, []);
  local zippedHeadNames::[((String, Integer), String)] =
        zipWith(pair(_, _), hd.freeVars, headNames);
  local filledHead::Term =
        foldr(\ p::((String, Integer), String) rest::Term ->
                replaceVar_Term(p.1, nameTerm(p.2), rest),
              hd, zippedHeadNames);
  local cleanedBodies::[Metaterm] =
        foldr(\ p::((String, Integer), String) rest::[Metaterm] ->
                map(replaceVar(p.1, nameTerm(p.2), _), rest),
              bodies, zippedHeadNames);
  local filledBodies::[Metaterm] =
        fillVarsBodies(cleanedBodies, headNames);
  return ( filledHead, filledBodies );
}

--Replace all varTerms in each clause body with a nameTerm and add
--   bindings for the names
function fillVarsBodies
[Metaterm] ::= bodies::[Metaterm] usedNames::[String]
{
  local body::Metaterm = head(bodies);
  local freevars::[(String, Integer)] = nub(body.freeVars);
  local newNames::[String] =
        generateNamesFromVars(freevars, usedNames);
  local filledBody::Metaterm =
        foldr(\ p::((String, Integer), String) rest::Metaterm ->
                replaceVar(p.1, nameTerm(p.2), rest),
              body, zipWith(pair(_, _), freevars, newNames));
  local boundBody::Metaterm =
        if null(newNames)
        then filledBody
        else bindingMetaterm(existsBinder(),
                map(\ x::String -> (x, nothing()), newNames),
                filledBody);
  return
     case bodies of
     | [] -> []
     | _::rest -> boundBody::fillVarsBodies(rest, usedNames)
     end;
}

--Returns the new names in order corresponding to the vars
function generateNamesFromVars
[String] ::= vars::[(String, Integer)] usedNames::[String]
{
  return
     case vars of
     | [] -> []
     | (name, _)::rest ->
       let newName::String = makeUniqueNameFromBase(name, usedNames)
       in
         newName::generateNamesFromVars(rest, newName::usedNames)
       end
     end;
}



{-
  Turn the short name, defined in the current grammar, into a
  fully-qualified name.
-}
function buildEncodedName
String ::= grammarName::String shortName::String
{
  return substitute(":", "$*$", grammarName) ++ "$*$" ++ shortName;
}


function encodeName
String ::= q::String
{
  return substitute(":", "$*$", q);
}


--(grammar, short name)
function splitEncodedName
(String, String) ::= name::String
{
  local lastInd::Integer = lastIndexOf("$*$", name);
  return
     if lastInd < 0
     then error("Not an encoded name (" ++ name ++ ")")
     else ( substring(0, lastInd, name),
            substring(lastInd + 3, length(name), name) );
}

