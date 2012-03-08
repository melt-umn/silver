grammar silver:util;

function contains
Boolean ::= s::String sl::[String]
{
  return (!null(sl)) && (s == head(sl) || contains(s, tail(sl)));
}

function containsSet
Boolean ::= s::[String] sl::[[String]]
{
  return (!null(sl)) && (equals(s, head(sl)) || containsSet(s, tail(sl)));
}

function containsDuplicates
Boolean ::= s::[String]
{
  return (!null(s)) && (contains(head(s),tail(s)) || containsDuplicates(tail(s)));
}

function equals
Boolean ::= s1::[String] s2::[String]
{
  return length(s1) == length(s2) && containsAll(s1, s2);
}

-- all of s1 in s2?
function containsAll
Boolean ::= s1::[String] s2::[String]
{
  return null(s1) || (contains(head(s1), s2) && containsAll(tail(s1), s2));
}

-- any of s1 in s2?
function containsAny
Boolean ::= s1::[String] s2::[String]
{
  return !null(s1) && (contains(head(s1), s2) || containsAny(tail(s1), s2));
}

--takes in a list of strings and returns a set of strings.
function makeSet
[String] ::= list::[String]
{
  local attribute recurse :: [String];
  recurse = makeSet(tail(list));

  return if null(list) then []
         else if contains(head(list), recurse)
	      then recurse
	      else cons(head(list), recurse);
}

--removes the strings of the first list that appear in the second list.  returns the filtered list.
function rem
[String] ::= n::[String] seen::[String] --result = n - seem;
{
  return if null(n) then [] 
         else if contains(head(n), seen)
	      then rem(tail(n), seen)
	      else cons(head(n), rem(tail(n), seen));
}

function remove
[String] ::= n::String s::[String]
{
  return if null(s) 
	 then [] 
	 else if n == head(s) 
	      then remove(n, tail(s)) 
	      else [head(s)] ++ remove(n, tail(s));
}

function startsWithAny
Boolean ::= pre::[String] s::String{
  return !null(pre) && (startsWith(head(pre), s) || startsWithAny(tail(pre), s));
}

