grammar silver:extension:convenience;
imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:concrete_syntax;
imports silver:definition:type;
imports silver:definition:type:syntax;

concrete production multipleAttributionDclsManyMany
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNames2 ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, nts.qnames);
}
concrete production multipleAttributionDclsSingleMany
top::AGDcl ::= 'attribute' a::QName botl::BracketedOptTypeList 'occurs' 'on' nts::QNames2 ';' 
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, [qNameWithTL(a, botl)], nts.qnames);
}
concrete production multipleAttributionDclsManySingle
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNameWithTL ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, [nts]);
}


concrete production nonterminalWithDcl
top::AGDcl ::= 'nonterminal' id::Name botl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.pp = "nonterminal " ++ id.pp ++ botl.pp ++ " with " ++ attrs.pp ++ " ;";
  forwards to agDclAppend(nonterminalDcl($1, id, botl, $6),
                          makeOccursDcls($1.line, $1.column, attrs.qnames, [qNameWithTL(qNameId(id), botl)]) );
}



concrete production attributeDclInhMultiple
top::AGDcl ::= 'inherited' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.name ++ botl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclInh($1, $2, a, botl, $5, te, $10),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), botl), qs.qnames));
}

concrete production attributeDclSynMultiple
top::AGDcl ::= 'synthesized' 'attribute' a::Name botl::BracketedOptTypeList '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ botl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclSyn($1, $2, a, botl, $5, te, $10),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), botl), qs.qnames));
}




{- TEMPORARILY(?) DISABLED this aren't commonly used anyhow




concrete production nonterminalWithDcl3
top::AGDcl ::= 'nonterminal' id::Names2 ';'
{
  top.pp = "nonterminal " ++ id.pp ++ " ;" ;
  forwards to makeNTDcls($1.line, $1.column, id.ids) ;
}
concrete production attributeDclInhMultiple1
top::AGDcl ::= 'inherited' 'attribute' a::Names2 '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeInhDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}
concrete production attributeDclSynMultiple1
top::AGDcl ::= 'synthesized' 'attribute' a::Names2 '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeSynDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}



-}
