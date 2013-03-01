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
  forwards to makeOccursDcls($1.location, a.qnames, nts.qnames);
}
concrete production multipleAttributionDclsSingleMany
top::AGDcl ::= 'attribute' a::QName tl::BracketedOptTypeList 'occurs' 'on' nts::QNames2 ';' 
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.location, [qNameWithTL(a, tl)], nts.qnames);
}
concrete production multipleAttributionDclsManySingle
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNameWithTL ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.location, a.qnames, [nts]);
}


concrete production nonterminalWithDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.pp = "nonterminal " ++ id.pp ++ tl.pp ++ " with " ++ attrs.pp ++ " ;";
  forwards to appendAGDcl(nonterminalDcl(cl, $2, id, tl, $7),
                          makeOccursDcls($2.location, attrs.qnames, [qNameWithTL(qNameId(id), tl)]) );
}



concrete production attributeDclInhMultiple
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.name ++ tl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to appendAGDcl(attributeDclInh($1, $2, a, tl, $5, te, $10),
                          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a), tl), qs.qnames));
}

concrete production attributeDclSynMultiple
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeList '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ tl.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to appendAGDcl(attributeDclSyn($1, $2, a, tl, $5, te, $10),
                          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a), tl), qs.qnames));
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
  forwards to appendAGDcl(makeInhDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}
concrete production attributeDclSynMultiple1
top::AGDcl ::= 'synthesized' 'attribute' a::Names2 '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to appendAGDcl(makeSynDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}



-}
