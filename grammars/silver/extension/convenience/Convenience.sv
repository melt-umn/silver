grammar silver:extension:convenience;
import silver:definition:core;
import silver:definition:concrete_syntax;

concrete production multipleAttributionDcls1
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNames2 ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, nts.qnames);
}

concrete production multipleAttributionDcls2
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nts::QNames2 ';' 
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, [a], nts.qnames);
}

concrete production multipleAttributionDcls3
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QName ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;" ;
  forwards to makeOccursDcls($1.line, $1.column, a.qnames, [nts]);
}

concrete production nonterminalWithDcl2
top::AGDcl ::= 'nonterminal' id::Names 'with' attrs::QNames ';'
{
  top.pp = "nonterminal " ++ id.pp ++ " with { " ++ attrs.pp ++ " };";
  forwards to agDclAppend(makeNTDcls($1.line, $1.column, id.ids), makeOccursDcls($1.line, $1.column, attrs.qnames, qualifyNames(id.ids)));
}

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

concrete production attributeDclInhMultiple2
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "inherited attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeInhDcls($1.line, $1.column, te, [a]), makeOccursDcls($1.line, $1.column, qualifyNames([a]), qs.qnames));
}

concrete production attributeDclSynMultiple1
top::AGDcl ::= 'synthesized' 'attribute' a::Names2 '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.pp ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeSynDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}

concrete production attributeDclSynMultiple2
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type 'occurs' 'on' qs::QNames ';'
{
  top.pp = "synthesized attribute " ++ a.name ++ " :: " ++ te.pp ++ " occurs on " ++ qs.pp ++ ";" ;
  forwards to agDclAppend(makeSynDcls($1.line, $1.column, te, [a]), makeOccursDcls($1.line, $1.column, qualifyNames([a]), qs.qnames));
}

