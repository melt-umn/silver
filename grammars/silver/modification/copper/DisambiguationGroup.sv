grammar silver:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c
{
  top.unparse = "disambiguate " ++ terms.unparse ++ " " ++ acode.unparse;

  top.errors := terms.errors ++ acode.errors;

  acode.env = newScopeEnv(disambiguationActionVars ++ acode.defs ++ terms.defs, top.env);

  -- Give the group a name, deterministically, based on line number
  production fName :: String = top.grammarName ++ ":__disam" ++ toString(top.location.line);
  
  acode.frame = disambiguationContext();

  top.syntaxAst = [syntaxDisambiguationGroup(fName, terms.termList, acode.actionCode)];
}

