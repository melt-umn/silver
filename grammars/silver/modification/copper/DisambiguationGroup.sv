grammar silver:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

synthesized attribute disambiguationGroupDcls :: [Decorated DisambiguationGroupSpec];
attribute disambiguationGroupDcls occurs on Root, RootSpec, AGDcls, AGDcl;

-- Separate 'TermPrecList' syntax if proper type-checking is made possible.
concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermPrecList acode::ActionCode_c
{
  top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.actionCode;
  top.location = loc(top.file, $1.line, $1.column);

  top.warnings := acode.warnings;
  top.errors := acode.errors ++ terms.errors;

  acode.env = newScopeEnv(addDisambigLexemeDcl(top.grammarName, top.location,
                                               appendDefs(acode.defs,terms.defs)),
                          top.env);

  -- Give the group a name, deterministically, based on line number
  acode.signature = namedNamedSignature(top.grammarName ++ ":__disam" ++ toString($1.line));

  acode.blockContext = disambiguationContext();

  top.disambiguationGroupDcls = [disambiguationGroupSpec(terms.precTermList,acode.actionCode)];
  
  forwards to agDclDefault();
}


