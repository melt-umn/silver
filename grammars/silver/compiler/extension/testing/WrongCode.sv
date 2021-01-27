grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;

terminal WrongCode_kwd 'wrongCode' lexer classes {KEYWORD};
terminal WarnCode_kwd 'warnCode' lexer classes {KEYWORD};
terminal NoWarnCode_kwd 'noWarnCode' lexer classes {KEYWORD};
terminal WrongFlowCode_kwd 'wrongFlowCode' lexer classes {KEYWORD};

function containsMessage
Boolean ::= text::String severity::Integer msgs::[Message]
{
  return any(map((\x::Message -> x.severity==severity && indexOf(text, x.output)!=-1), msgs));
}

concrete production wrongDecl
top::AGDcl ::= 'wrongCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "wrongCode" ++ s.lexeme ++ "{" ++ ags.unparse ++ "}";
  
  top.errors := 
    if !containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 2, ags.errors)
    then [err(top.location, "Wrong code did not raise an error containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];
  
  -- do extend its environment with its defs
  ags.env = occursEnv(ags.occursDefs, newScopeEnv(ags.defs, top.env));
  
  forwards to emptyAGDcl(location=top.location);
}

concrete production warnDecl
top::AGDcl ::= 'warnCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "warnCode" ++ s.lexeme ++ "{" ++ ags.unparse ++ "}";
  
  top.errors := 
    if !containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 1, ags.errors)
    then [err(top.location, "Warn code did not raise a warning containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];
  
  forwards to makeAppendAGDclOfAGDcls(ags);
  -- Forward to the decls so that we can use the stuff declared with warnings in other tests
}

--Check that code does *NOT* include a particular type of warning
--Used to ensure warnings are only generated on code where they are relevant
--We don't need a version for errors, since they will cause failures anyway
concrete production noWarnDecl
top::AGDcl ::= 'noWarnCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "noWarnCode " ++ s.lexeme ++ " {" ++ ags.unparse ++ "}";

  top.errors :=
    if containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 1, ags.errors)
    then [err(top.location, "No-warn code raised a warning containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];

  forwards to makeAppendAGDclOfAGDcls(ags);
  -- Forward to the decls so that we can use the stuff declared without warnings in other tests
}

concrete production wrongFlowDecl
top::AGDcl ::= 'wrongFlowCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "wrongFlowCode" ++ s.lexeme ++ "{" ++ ags.unparse ++ "}";
  
  top.errors := 
    if !containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 2, ags.errors)
    then [err(top.location, "Wrong code did not raise an error containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];
  
  -- do extend its environment with its defs
  ags.env = occursEnv(ags.occursDefs, newScopeEnv(ags.defs, top.env));
  
  -- let's ALSO propagate up flow info, so these kinds of errors are checked/caught
  top.flowDefs := ags.flowDefs;
  
  forwards to emptyAGDcl(location=top.location);
}

