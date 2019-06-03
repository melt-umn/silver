grammar silver:extension:patternmatching;

import silver:extension:list only LSqr_t, RSqr_t, listType;

{--
 - The forms of syntactic patterns that are permissible in (nested) case expresssions.
 -}
nonterminal Pattern with location, config, unparse, env, errors, patternIsVariable, patternVariableName, patternSubPatternList, patternSortKey, patternType;

{--
 - False if it actually matches anything specific, true if it's a variable/wildcard.
 -}
synthesized attribute patternIsVariable :: Boolean;
{--
 - The name of the variable, if any (e.g. wildcards!)
 -}
synthesized attribute patternVariableName :: Maybe<String>;
{--
 - Each child pattern below this one.
 -}
synthesized attribute patternSubPatternList :: [Decorated Pattern];
{--
 - The sort (and grouping) key of the pattern.
 - "~var" if patternIsVariable is true. (TODO: actually, we should call it undefined! It's not used.)
 - fullname if it's a production.
 - otherwise, it is type-depedent, but same values should be the same!
 -}
synthesized attribute patternSortKey :: String;


-- These are the "canonical" patterns:

{--
 - Match on a production, extracting children as patterns, too.
 - The production name may be qualified.
 - TODO: if not qualified filter down to productions on scruntinee type
 -}
concrete production prodAppPattern
top::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  top.unparse = prod.unparse ++ "(" ++ ps.unparse ++ ")";
  top.errors := ps.errors;

  local parms :: Integer = length(prod.lookupValue.typerep.inputTypes);

  top.errors <-
    if null(prod.lookupValue.dcls) || length(ps.patternList) == parms then []
    else [err(prod.location, prod.name ++ " has " ++ toString(parms) ++ " parameters but " ++ toString(length(ps.patternList)) ++ " patterns were provided")];

  top.patternIsVariable = false;
  top.patternVariableName = nothing();
  top.patternSubPatternList = ps.patternList;
  top.patternSortKey = prod.lookupValue.fullName;

  top.patternType = case prod.lookupValue.typerep of
                    | functionType(out, _, _) -> out
                    | _ -> prod.lookupValue.typerep
                    end;
} 

{--
 - Match anything, and bind nothing.
 -}
concrete production wildcPattern
top::Pattern ::= '_'
{
  top.unparse = "_";
  top.errors := [];

  top.patternIsVariable = true;
  top.patternVariableName = nothing();
  top.patternSubPatternList = [];
  top.patternSortKey = "~var";

  top.patternType = freshType();
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
  top.errors := 
    (if isUpper(substring(0,1,v.name))
     then [err(v.location, "Pattern variable names start with a lower case letter")]
     else []) ++
    (case getValueDcl(v.name, top.env) of
     | prodDcl(_,_,_) :: _ ->
         [err(v.location, "Pattern variables should not share the name of a production. (Potential confusion between '" ++ v.name ++ "' and '" ++ v.name ++ "()')")]
     | _ -> []
     end);

  top.patternIsVariable = true;
  top.patternVariableName = just(v.name);
  top.patternSubPatternList = [];
  top.patternSortKey = "~var";

  top.patternType = freshType();
}

{--
 - For other extensions to pattern matching.  Basically acts like a wildcard.
 -}
abstract production errorPattern
top::Pattern ::= msg::[Message]
{
  top.unparse = s"{-${messagesToString(msg)}-}";
  top.errors := msg;

  top.patternIsVariable = true;
  top.patternVariableName = nothing();
  top.patternSubPatternList = [];
  top.patternSortKey = "error";

  top.patternType = errorType();
}

aspect default production
top::Pattern ::=
{
  -- All other patterns should never set these to anything else, so let's default them.
  top.patternIsVariable = false;
  top.patternVariableName = nothing();
}

--------------------------------------------------------------------------------

-- Below are the non-canonical patterns, i.e. those for other types

concrete production intPattern
top::Pattern ::= num::Int_t
{
  top.unparse = num.lexeme;
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = num.lexeme;

  top.patternType = intType();
}

concrete production fltPattern
top::Pattern ::= num::Float_t
{
  top.unparse = num.lexeme;
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = num.lexeme;

  top.patternType = floatType();
}

concrete production strPattern
top::Pattern ::= str::String_t
{
  top.unparse = str.lexeme;
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = str.lexeme;

  top.patternType = stringType();
}

concrete production truePattern
top::Pattern ::= 'true'
{
  top.unparse = "true";
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = "true";

  top.patternType = boolType();
}

concrete production falsePattern
top::Pattern ::= 'false'
{
  top.unparse = "false";
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = "false";

  top.patternType = boolType();
}

abstract production nilListPattern
top::Pattern ::= '[' ']'
{
  top.unparse = "[]";
  top.errors := [];
  
  top.patternSubPatternList = [];
  top.patternSortKey = "core:nil";

  top.patternType = listType(freshType());
}

concrete production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  top.unparse = hp.unparse ++ "::" ++ tp.unparse;
  top.errors := hp.errors ++ tp.errors;
  
  top.patternSubPatternList = [hp, tp];
  top.patternSortKey = "core:cons";

  top.patternType = listType(hp.patternType);
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
    consListPattern(p, '::', nilListPattern('[', ']', location=top.location), location=top.location);
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.asListPattern = consListPattern(p, '::', ps1.asListPattern, location=top.location);
}
aspect production patternList_nil
top::PatternList ::=
{
  top.asListPattern = nilListPattern('[', ']', location=top.location);
}
