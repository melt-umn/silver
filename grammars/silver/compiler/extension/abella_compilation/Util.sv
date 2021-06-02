grammar silver:compiler:extension:abella_compilation;


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




--Make a name that isn't in usedNames, based on the type
function makeUniqueNameFromTy
String ::= ty::AbellaType usedNames::[String]
{
  local base::String =
        if tyIsNonterminal(ty)
        then substring(3, 4, ty.headTypeName.fromJust)
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




--Drop the qualifying names from the given name
function shortestName
String ::= name::String
{
  local index::Integer = lastIndexOf(":", name);
  return if index >= 0
         then substring(index + 1, length(name), name)
         else name;
}

