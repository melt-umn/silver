grammar silver:util;

function folds
String ::= s::String c1::[String]
{
  return if null(c1) then "" else head(c1) ++ (if null(tail(c1)) then "" else s) ++ folds(s, tail(c1));
}

function foldss
String ::= s1::String s2::String sl::[[String]]{
  return if null(sl) then "" else folds(s2, head(sl)) ++ (if null(tail(sl)) then "" else s1) ++ foldss(s1, s2, tail(sl));
}

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
  return (!null(sl)) && (contains(head(s),tail(s)) || containsDuplicates(tail(s)));
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

  return if null(list) then list
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

function convert
[String] ::= sl::StringList
{
  return if sl.empty 
	 then []
	 else cons(sl.sh, convert(sl.st));
}

function split
[String] ::= s::String str::String
{
  local attribute i :: Integer;
  i = indexOf(s, str);

  return if i == -1
         then [str]
         else [substring(0, i, str)] ++ split(s, substring(i+1, length(str), str));
}

function substitute
String ::= s::String r::String str::String
{
  local attribute i :: Integer;
  i = indexOf(r, str);

  return if i == -1
         then str
         else substring(0, i, str) ++ s ++ substitute(s, r, substring(i+length(r), length(str), str));
}

function startsWithAny
Boolean ::= pre::[String] s::String{
  return !null(pre) && (startsWith(head(pre), s) || startsWithAny(tail(pre), s));
}

function last
String ::= s::[String]{
  return if null(s) then "" else if null(tail(s)) then head(s) else last(tail(s));
}

