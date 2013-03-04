grammar silver:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

-- TODO Separate 'TermPrecList'. That allows lexer classes which is nonsense here

concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.pp;

  top.errors := acode.errors ++ terms.errors;

  acode.env = newScopeEnv(disambigLexemeDef(top.grammarName, top.location) ::
                            acode.defs ++ terms.defs, top.env);

  -- Give the group a name, deterministically, based on line number
  production attribute fName :: String;
  fName = top.grammarName ++ ":__disam" ++ toString($1.line);
  
  acode.signature = namedNamedSignature(fName);
  acode.blockContext = disambiguationContext();

  top.syntaxAst = [syntaxDisambiguationGroup(fName,terms.precTermList,acode.actionCode)];
}


