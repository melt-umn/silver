grammar silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;

concrete production multipleAttributionDclsManyMany
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNames2 ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, nts.qnames);
}
concrete production multipleAttributionDclsSingleMany
top::AGDcl ::= 'attribute' a::QNameWithTL 'occurs' 'on' nts::QNames2 ';' 
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, [a], nts.qnames);
}
concrete production multipleAttributionDclsManySingle
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNameWithTL ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, [nts]);
}


concrete production nonterminalWithDclNoTL
top::AGDcl ::= 'nonterminal' id::Name 'with' attrs::QNames ';'
{
  top.pp = "nonterminal " ++ id.pp ++ " with " ++ attrs.pp ++ " ;";
  forwards to agDclAppend(nonterminalDclEmpty($1, id, $5),
                          makeOccursDcls($1.line, $1.column, attrs.qnames, [qNameWithoutTL(qNameId(id))]) );
}
concrete production nonterminalWithDclWithTL
top::AGDcl ::= 'nonterminal' id::Name '<' tl::TypeList '>' 'with' attrs::QNames ';'
{
  top.pp = "nonterminal " ++ id.pp ++ "<" ++ tl.pp ++ "> with " ++ attrs.pp ++ " ;";
  forwards to agDclAppend(nonterminalDcl($1, id, $3, tl, $5, $8),
                          makeOccursDcls($1.line, $1.column, attrs.qnames, [qNameWithTL(qNameId(id), $3, tl, $5)]) );
}



concrete production attributeDclInhMultipleNoTL
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclInhEmpty($1, $2, a, $4, te, $9),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithoutTL(qNameId(a)), qs.qnames));
}
concrete production attributeDclInhMultipleWithTL
top::AGDcl ::= 'inherited' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclInh($1, $2, a, $4, tl, $6, $7, te, $12),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), $4, tl, $6), qs.qnames));
}

concrete production attributeDclSynMultipleNoTL
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclSynEmpty($1, $2, a, $4, te, $9),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithoutTL(qNameId(a)), qs.qnames));
}
concrete production attributeDclSynMultipleWithTL
top::AGDcl ::= 'synthesized' 'attribute' a::Name '<' tl::TypeList '>' '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(attributeDclSyn($1, $2, a, $4, tl, $6, $7, te, $12),
                          makeOccursDclsHelp($1.line, $1.column, qNameWithTL(qNameId(a), $4, tl, $6), qs.qnames));
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
