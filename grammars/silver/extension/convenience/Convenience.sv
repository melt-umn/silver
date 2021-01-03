grammar silver:extension:convenience;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:concrete_syntax;
imports silver:definition:type;
imports silver:definition:type:syntax;
import silver:modification:collection;

-- Multiple attribute occurs on statements
concrete production multipleAttributionDclsManyMany
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNames2 ';'
{
  top.unparse = "attribute " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, a.qnames, nts.qnames);
}
concrete production multipleAttributionDclsSingleMany
top::AGDcl ::= 'attribute' a::QName tl::BracketedOptTypeExprs 'occurs' 'on' nts::QNames2 ';' 
{
  top.unparse = "attribute " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, [qNameWithTL(a, tl)], nts.qnames);
}
concrete production multipleAttributionDclsManySingle
top::AGDcl ::= 'attribute' a::QNames2 'occurs' 'on' nts::QNameWithTL ';'
{
  top.unparse = "attribute " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, a.qnames, [nts]);
}

-- Multiple annotation occurs on statements
concrete production multipleAnnotationDclsManyMany
top::AGDcl ::= 'annotation' a::QNames2 'occurs' 'on' nts::QNames2 ';'
{
  top.unparse = "annotation " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, a.qnames, nts.qnames);
}
concrete production multipleAnnotationDclsSingleMany
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs 'occurs' 'on' nts::QNames2 ';' 
{
  top.unparse = "annotation " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, [qNameWithTL(a, tl)], nts.qnames);
}
concrete production multipleAnnotationDclsManySingle
top::AGDcl ::= 'annotation' a::QNames2 'occurs' 'on' nts::QNameWithTL ';'
{
  top.unparse = "annotation " ++ a.unparse ++ " occurs on " ++ nts.unparse ++ " ;" ;
  forwards to makeOccursDcls(top.location, a.qnames, [nts]);
}


concrete production nonterminalWithDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers 'with' attrs::QNames ';'
{
  top.unparse = "nonterminal " ++ id.unparse ++ tl.unparse ++ " " ++ nm.unparse ++ " with " ++ attrs.unparse ++ " ;";
  forwards to appendAGDcl(
    nonterminalDcl(quals, $2, id, tl, nm, $8, location=top.location),
    makeOccursDcls(top.location, attrs.qnames, [qNameWithTL(qNameId(id, location=id.location), tl)]),
    location=top.location);
}


concrete production attributeDclInhMultiple
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "inherited attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(
    attributeDclInh($1, $2, a, tl, $5, te, $10, location=top.location),
    makeOccursDclsHelp(top.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames),
    location=top.location);
}

concrete production attributeDclSynMultiple
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "synthesized attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(
    attributeDclSyn($1, $2, a, tl, $5, te, $10, location=top.location),
    makeOccursDclsHelp(top.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames),
    location=top.location);
}

concrete production collectionAttributeDclInhMultiple
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "inherited attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ " ;" ;
  forwards to appendAGDcl(
    collectionAttributeDclInh($1, $2, a, tl, $5, te, $7, q, $12, location=top.location),
    makeOccursDclsHelp(top.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames),
    location=top.location);
}

concrete production collectionAttributeDclSynMultiple
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "synthesized attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ " ;" ;
  forwards to appendAGDcl(
    collectionAttributeDclSyn($1, $2, a, tl, $5, te, $7, q, $12, location=top.location),
    makeOccursDclsHelp(top.location, qNameWithTL(qNameId(a, location=a.location), tl), qs.qnames),
    location=top.location);
}




{- TEMPORARILY(?) DISABLED this aren't commonly used anyhow




concrete production nonterminalWithDcl3
top::AGDcl ::= 'nonterminal' id::Names2 ';'
{
  top.unparse = "nonterminal " ++ id.unparse ++ " ;" ;
  forwards to makeNTDcls($1.line, $1.column, id.ids) ;
}
concrete production attributeDclInhMultiple1
top::AGDcl ::= 'inherited' 'attribute' a::Names2 '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "inherited attribute " ++ a.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(makeInhDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}
concrete production attributeDclSynMultiple1
top::AGDcl ::= 'synthesized' 'attribute' a::Names2 '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "synthesized attribute " ++ a.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(makeSynDcls($1.line, $1.column, te, a.ids), makeOccursDcls($1.line, $1.column, qualifyNames(a.ids), qs.qnames));
}



-}
