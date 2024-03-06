grammar silver:compiler:extension:patternmatching;

import silver:compiler:modification:list only LSqr_t, RSqr_t;

{--
 - The forms of syntactic patterns that are permissible in (nested) case expresssions.
 -}
tracked nonterminal Pattern with config, unparse, env, frame, errors, patternVars, patternVarEnv, patternIsVariable, patternVariableName, patternSubPatternList, patternNamedSubPatternList, patternSortKey, isPrimitivePattern, isBoolPattern, isListPattern, patternTypeName;
flowtype Pattern = unparse {};
propagate config, frame, env, errors on Pattern;

{--
 - The names of all var patterns in the pattern.
 -}
synthesized attribute patternVars :: [String];
{--
 - The names of all var patterns seen so far.
 -}
inherited attribute patternVarEnv :: [String];
{--
 - False if it actually matches anything specific, true if it's a variable/wildcard.
 -}
synthesized attribute patternIsVariable :: Boolean;
{--
 - The name of the variable, if any (e.g. wildcards!)
 -}
synthesized attribute patternVariableName :: Maybe<String>;
{--
 - Each positional child pattern below this one.
 -}
synthesized attribute patternSubPatternList :: [Decorated Pattern];
{--
 - Each named child pattern below this one.
 -}
synthesized attribute patternNamedSubPatternList :: [Pair<String Decorated Pattern>];
{--
 - The sort (and grouping) key of the pattern.
 - "~var" if patternIsVariable is true. (TODO: actually, we should call it undefined! It's not used.)
 - fullname if it's a production.
 - otherwise, it is type-dependent, but same values should be the same!
 -}
synthesized attribute patternSortKey :: String;


{-
  For completeness checking:
-}

--For non-nonterminal patterns, which generally require a default case for completeness
synthesized attribute isPrimitivePattern::Boolean;

--We need a special case for Booleans, since they can be complete
--   without variables but are not nonterminals
synthesized attribute isBoolPattern::Boolean;

--Lists occupy a place somewhere between nonterminals and primitives,
--   so we'll handle them specially
synthesized attribute isListPattern::Boolean;

--The fully-qualified name of the type being built
synthesized attribute patternTypeName::String;


-- These are the "canonical" patterns:

{--
 - Match on a production, extracting children as patterns, too.
 - The production name may be qualified.
 - TODO: if not qualified filter down to productions on scruntinee type
 -}
concrete production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.unparse = prod.unparse ++ "(" ++ ps.unparse ++ (if ps.count > 0 && nps.count > 0 then ", " else "") ++ nps.unparse ++ ")";

  local parms :: Integer = prod.lookupValue.typeScheme.arity;

  top.errors <-
    if null(prod.lookupValue.dcls) || length(ps.patternList) == parms then []
    else [errFromOrigin(prod, prod.name ++ " has " ++ toString(parms) ++ " parameters but " ++ toString(length(ps.patternList)) ++ " patterns were provided")];
  
  top.patternVars = ps.patternVars ++ nps.patternVars;
  ps.patternVarEnv = top.patternVarEnv;
  nps.patternVarEnv = ps.patternVarEnv ++ ps.patternVars;

  top.patternIsVariable = false;
  top.patternVariableName = nothing();
  top.patternSubPatternList = ps.patternList;
  top.patternNamedSubPatternList = nps.namedPatternList;
  top.patternSortKey = prod.lookupValue.fullName;

  top.isPrimitivePattern = false;
  top.isBoolPattern = false;
  top.isListPattern = false;
  top.patternTypeName = prod.lookupValue.typeScheme.typerep.outputType.baseType.typeName;
}

concrete production prodAppPattern
top::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  forwards to prodAppPattern_named(prod, '(', ps, ',', namedPatternList_nil(), ')');
}

concrete production propAppPattern_onlyNamed
top::Pattern ::= prod::QName '(' nps::NamedPatternList ')'
{
  forwards to prodAppPattern_named(prod, '(', patternList_nil(), ',', nps, ')');
}

{--
 - Match anything, and bind nothing.
 -}
concrete production wildcPattern
top::Pattern ::= '_'
{
  top.unparse = "_";

  top.patternVars = [];
  top.patternIsVariable = true;
  top.patternVariableName = nothing();
  top.patternSubPatternList = [];
  top.patternSortKey = "~var";

  --This might be used with these kinds of patterns, but it isn't one itself
  top.isPrimitivePattern = false;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

{--
 - Match anything, bind it to a name.
 - Note 1: must be lower case, to avoid newbie confusions "case (e::a) of Expr -> ..."
 - Note 2: must not shadow a production name, to avoid "forgot parens" confusion
 -   (e.g. "case maybe of nothing -> ..." vs "nothing()")
 -}
concrete production varPattern
top::Pattern ::= v::Name
{
  top.unparse = v.name;
  top.errors <-
    if contains(v.name, top.patternVarEnv)
    then [errFromOrigin(v, "Duplicate pattern variable " ++ v.name)]
    else [];
  top.errors <-
    if isUpper(substring(0,1,v.name))
    then [errFromOrigin(v, "Pattern variable names start with a lower case letter")]
    else [];
  top.errors <-
    case getValueDcl(v.name, top.env) of
    | prodDcl(_,_,_) :: _ ->
      [errFromOrigin(v, "Pattern variables should not share the name of a production. (Potential confusion between '" ++ v.name ++ "' and '" ++ v.name ++ "()')")]
    | _ -> []
    end;

  top.patternVars = [v.name];
  top.patternIsVariable = true;
  top.patternVariableName = just(v.name);
  top.patternSubPatternList = [];
  top.patternSortKey = "~var";

  --This might be used with these kinds of patterns, but it isn't one itself
  top.isPrimitivePattern = false;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

{--
 - For other extensions to pattern matching.  Basically acts like a wildcard.
 -}
abstract production errorPattern
top::Pattern ::= msg::[Message]
{
  top.unparse = s"{-${messagesToString(msg)}-}";
  top.errors <- msg;

  top.patternVars = [];
  top.patternIsVariable = true;
  top.patternVariableName = nothing();
  top.patternSubPatternList = [];
  top.patternSortKey = "error";

  top.isPrimitivePattern = false;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

aspect default production
top::Pattern ::=
{
  -- All other patterns should never set these to anything else, so let's default them.
  top.patternIsVariable = false;
  top.patternVariableName = nothing();
  top.patternNamedSubPatternList = [];

  --This should only be accessed on production patterns
  top.patternTypeName = "";
}

-- arbitrary nesting of patterns
concrete production nestedPatterns
top::Pattern ::= '(' p::Pattern ')'
{
  top.unparse = s"(${p.unparse})";
  forwards to p;
}

--------------------------------------------------------------------------------

-- Below are the non-canonical patterns, i.e. those for other types

concrete production intPattern
top::Pattern ::= num::Int_t
{
  top.unparse = num.lexeme;
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = num.lexeme;

  top.isPrimitivePattern = true;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

concrete production fltPattern
top::Pattern ::= num::Float_t
{
  top.unparse = num.lexeme;
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = num.lexeme;

  top.isPrimitivePattern = true;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

concrete production strPattern
top::Pattern ::= str::String_t
{
  top.unparse = str.lexeme;
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = str.lexeme;

  top.isPrimitivePattern = true;
  top.isBoolPattern = false;
  top.isListPattern = false;
}

concrete production truePattern
top::Pattern ::= 'true'
{
  top.unparse = "true";
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = "true";

  top.isPrimitivePattern = true;
  top.isBoolPattern = true;
  top.isListPattern = false;
}

concrete production falsePattern
top::Pattern ::= 'false'
{
  top.unparse = "false";
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = "false";

  top.isPrimitivePattern = true;
  top.isBoolPattern = true;
  top.isListPattern = false;
}

abstract production nilListPattern
top::Pattern ::= '[' ']'
{
  top.unparse = "[]";
  
  top.patternVars = [];
  top.patternSubPatternList = [];
  top.patternSortKey = "silver:core:nil";

  top.isPrimitivePattern = true;
  top.isBoolPattern = false;
  top.isListPattern = true;
}

concrete production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  top.unparse = hp.unparse ++ "::" ++ tp.unparse;
  
  top.patternVars = hp.patternVars ++ tp.patternVars;
  hp.patternVarEnv = top.patternVarEnv;
  tp.patternVarEnv = hp.patternVarEnv ++ hp.patternVars;
  
  top.patternSubPatternList = [hp, tp];
  top.patternSortKey = "silver:core:cons";

  top.isPrimitivePattern = true;
  top.isBoolPattern = false;
  top.isListPattern = true;
}

-- List literal patterns
concrete production listPattern
top::Pattern ::= '[' ps::PatternList ']'
{
  top.unparse = s"[${ps.unparse}]";
  forwards to ps.asListPattern;
}

synthesized attribute asListPattern::Pattern occurs on PatternList;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  top.asListPattern = 
    consListPattern(p, '::', nilListPattern('[', ']'));
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.asListPattern = consListPattern(p, '::', ps1.asListPattern);
}
aspect production patternList_nil
top::PatternList ::=
{
  top.asListPattern = nilListPattern('[', ']');
}

synthesized attribute namedPatternList::[Pair<String Decorated Pattern>];

tracked nonterminal NamedPatternList with config, unparse, count, frame, env, errors, patternVars, patternVarEnv, namedPatternList;
propagate config, frame, env, errors on NamedPatternList;

concrete production namedPatternList_one
top::NamedPatternList ::= p::NamedPattern
{
  top.unparse = p.unparse;
  top.count = 1;

  top.patternVars = p.patternVars;
  p.patternVarEnv = top.patternVarEnv;
  top.namedPatternList = p.namedPatternList;
}
concrete production namedPatternList_more
top::NamedPatternList ::= p::NamedPattern ',' ps::NamedPatternList
{
  top.unparse = p.unparse ++ ", " ++ ps.unparse;
  top.count = 1 + ps.count;

  top.patternVars = p.patternVars ++ ps.patternVars;
  p.patternVarEnv = top.patternVarEnv;
  ps.patternVarEnv = p.patternVarEnv ++ p.patternVars;
  top.namedPatternList = p.namedPatternList ++ ps.namedPatternList;
}

-- Abstract only to avoid parse conflict
abstract production namedPatternList_nil
top::NamedPatternList ::=
{
  top.unparse = "";
  top.count = 0;

  top.patternVars = [];
  top.namedPatternList = [];
}

tracked nonterminal NamedPattern with config, unparse, frame, env, errors, patternVars, patternVarEnv, namedPatternList;
propagate config, frame, env, patternVarEnv, errors on NamedPattern;

concrete production namedPattern
top::NamedPattern ::= qn::QName '=' p::Pattern
{
  top.unparse = s"${qn.unparse}=${p.unparse}";
  
  -- TODO: Error checking for annotation patterns is a bit broken.
  -- We can check that it is an annotation here, but any other
  -- errors will show up in the generated code, potentially in the wrong
  -- (or more than one) place.
  top.errors <-
    if qn.lookupAttribute.found && !qn.lookupAttribute.dcl.isAnnotation
    then [errFromOrigin(qn, s"${qn.name} is not an annotation")]
    else [];
  
  top.patternVars = p.patternVars;
  top.namedPatternList = [(qn.lookupAttribute.fullName, p)];
}

--helper function for building patternLists from lists of patterns
fun buildPatternList PatternList ::= plst::[Pattern] loc::Location =
  case plst of
  | [] -> patternList_nil()
  | h::t ->
    patternList_more(h, ',', buildPatternList(t, loc))
  end;

