grammar silver:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c
{
  top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.pp;

  top.errors := terms.errors;
  top.errors <- acode.errors;

  acode.env = newScopeEnv(disambigLexemeDef(top.grammarName, top.location) ::
                            acode.defs ++ terms.defs, top.env);

  -- Give the group a name, deterministically, based on line number
  production fName :: String = top.grammarName ++ ":__disam" ++ toString(top.location.line);
  
  acode.frame = disambiguationContext();

  top.syntaxAst = [syntaxDisambiguationGroup(fName,terms.termList,acode.actionCode)];
}


nonterminal TermList with config, grammarName, pp, location, termList, defs, errors, env;

synthesized attribute termList :: [String];

concrete production termListOne
terms::TermList ::= t::QName
{
   forwards to termList(t,termListNull(location=t.location), location=t.location);
}

concrete production termListCons
terms::TermList ::= t::QName ',' terms_tail::TermList
{
   forwards to termList(t,terms_tail,location=terms.location);
}


abstract production termList
top::TermList ::= h::QName t::TermList
{
  top.pp = if t.pp == ""
             then h.pp
             else h.pp ++ ", " ++ t.pp;

  production fName::String = h.lookupType.dcl.fullName;

  top.termList = [fName] ++ t.termList ;
  
  -- Itd be nice if we didnt need this guard
  top.defs = if null(h.lookupType.dcls) then t.defs 
             else pluckTermDef(top.grammarName, h.location, fName) :: t.defs;
  top.errors := t.errors;
  
  -- Since were looking it up in two ways, do the errors ourselves
  top.errors <- if null(h.lookupType.dcls)
                then [err(h.location, "Undeclared terminal '" ++ h.name ++ "'.")]
                else if length(h.lookupType.dcls) > 1
                then [err(h.location, "Ambiguous reference to terminal '" ++ h.name ++ "'. Possibilities are:\n" ++ printPossibilities(h.lookupType.dcls))]
                else [];
}

abstract production termListNull
top::TermList ::=
{
  top.termList = [];
  top.defs = [];
  top.pp = "";
  top.errors := [];
}
