grammar silver:compiler:extension:testing;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:flow:env;
import silver:compiler:analysis:uniqueness;

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
  propagate grammarName, grammarDependencies, compiledGrammars, config, flowEnv;
  
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
  propagate grammarName, grammarDependencies, compiledGrammars, config, env, flowEnv;
  
  top.errors := 
    if !containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 1, ags.errors)
    then [err(top.location, "Warn code did not raise a warning containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];
  
  forwards to makeAppendAGDclOfAGDcls(ags);
  -- Forward to the decls so that we can use the stuff declared with warnings in other tests
}

{--
 -Check that code does *NOT* include a particular type of warning.
 -This is used to ensure warnings are only generated on code where they should be generated.
 -We don't need a version for no errors, since errors will cause failures anyway.
 -
 - @param s  The string which should NOT be contained in any warning messages from this code block
 - @param ags  The code block which we are checking
-}
concrete production noWarnDecl
top::AGDcl ::= 'noWarnCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "noWarnCode " ++ s.lexeme ++ " {" ++ ags.unparse ++ "}";
  propagate grammarName, grammarDependencies, compiledGrammars, config, env, flowEnv;

  {-
    I think we want the errors from ags in any case.  This production
    is essentially requiring that the code is correct, so we want to
    know that the reason there is no warning is because the code was
    written correctly, not because it had a worse error in it.
  -}
  top.errors :=
    ags.errors ++
    if containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 1, ags.errors)
    then [err(top.location, "No-warn code raised a warning containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))]
    else [];

  forwards to makeAppendAGDclOfAGDcls(ags);
  -- Forward to the decls so that we can use the stuff declared without warnings in other tests
}

concrete production wrongFlowDecl
top::AGDcl ::= 'wrongFlowCode' s::String_t '{' ags::AGDcls '}'
{
  top.unparse = "wrongFlowCode" ++ s.lexeme ++ "{" ++ ags.unparse ++ "}";
  propagate grammarName, grammarDependencies, compiledGrammars, config, flowEnv, env;
  
  top.errors := 
    if !containsMessage(substring(1, length(s.lexeme) - 1, s.lexeme), 2, ags.errors)
    then [err(top.location, "Wrong code did not raise an error containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
    else [];
  
  -- These need to be passed up for the flow analysis to work:
  propagate defs, flowDefs, uniqueRefs;
  
  forwards to emptyAGDcl(location=top.location);
}

