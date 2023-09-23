grammar silver:compiler:extension:autoattr:convenience;

import silver:compiler:extension:autoattr;
import silver:compiler:extension:convenience;
import silver:compiler:modification:collection;
import silver:compiler:definition:core;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;
import silver:compiler:definition:env;

concrete production functorAttributeDclMultiple
top::AGDcl ::= 'functor' 'attribute' a::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "functor attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      functorAttributeDcl($1, $2, a, $7),
      makeOccursDclsHelp(qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}

concrete production monoidAttributeDclMultiple
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' q::NameOrBOperator 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "monoid attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ e.unparse ++ ", " ++ q.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      monoidAttributeDcl($1, $2, a, tl, $5, te, $7, e, $9, q, $14),
      makeOccursDclsHelp(qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}

concrete production tcMonoidAttributeDclMultiple
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "monoid attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      tcMonoidAttributeDcl($1, $2, a, tl, $5, te, $10),
      makeOccursDclsHelp(qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}

concrete production destructAttributeDclMultiple
top::AGDcl ::= 'destruct' 'attribute' a::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "destruct attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl($1, $2, a, ';'),
      makeOccursDclsHelp(qNameWithTL(qNameId(a), botlNone()), qs.qnames));
}

concrete production equalityAttributeDclMultiple
top::AGDcl ::= 'equality' 'attribute' syn::Name 'with' inh::QName 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "equality attribute " ++ syn.name ++ " with " ++ inh.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      equalityAttributeDcl($1, $2, syn, $4, inh, $9),
      makeOccursDclsHelp(qNameWithTL(qNameId(syn), botlNone()), qs.qnames));
}

concrete production orderingAttributeDclMultiple
top::AGDcl ::= 'ordering' 'attribute' keySyn::Name ',' syn::Name 'with' inh::QName 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "ordering attribute " ++ keySyn.name ++ ", " ++ syn.name ++ " with " ++ inh.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      orderingAttributeDcl($1, $2, keySyn, $4, syn, $6, inh, $11),
      appendAGDcl(
        makeOccursDclsHelp(qNameWithTL(qNameId(keySyn), botlNone()), qs.qnames),
        makeOccursDclsHelp(qNameWithTL(qNameId(syn), botlNone()), qs.qnames)));
}

-- Deprecate?  Eric suggested keeping this: https://github.com/melt-umn/silver/issues/431#issuecomment-760552226
concrete production destructEqualityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name ';'
{
  top.unparse = "equality attribute " ++ inh.unparse ++ ", " ++ syn.name ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl('destruct', $2, inh, $6),
      equalityAttributeDcl($1, $2, syn, 'with', qNameId(inh), $6));
}
concrete production destructEqualityAttributeDclMultiple
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "equality attribute " ++ inh.unparse ++ ", " ++ syn.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDclMultiple('destruct', $2, inh, $6, $7, qs, $9),
      equalityAttributeDclMultiple($1, $2, syn, 'with', qNameId(inh), $6, $7, qs, $9));
}
concrete production destructOrderingAttributeDcl
top::AGDcl ::= 'ordering' 'attribute' inh::Name ',' keySyn::Name ',' syn::Name ';'
{
  top.unparse = "ordering attribute " ++ inh.unparse ++ ", " ++ keySyn.name ++ ", " ++ syn.name ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl('destruct', $2, inh, $8),
      orderingAttributeDcl($1, $2, keySyn, $6, syn, 'with', qNameId(inh), $8));
}
concrete production destructOrderingAttributeDclMultiple
top::AGDcl ::= 'ordering' 'attribute' inh::Name ',' keySyn::Name ',' syn::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "ordering attribute " ++ inh.unparse ++ ", " ++ keySyn.name ++ ", " ++ syn.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDclMultiple('destruct', $2, inh, $8, $9, qs, $11),
      orderingAttributeDclMultiple($1, $2, keySyn, $6, syn, 'with', qNameId(inh), $8, $9, qs, $11));
}

concrete production biequalityAttributeDclMultiple
top::AGDcl ::= 'biequality' 'attribute' synPartial::Name ',' syn::Name 'with' inh::QName 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "biequality attribute " ++ synPartial.name ++ ", " ++ syn.name ++ " with " ++ inh.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      biequalityAttributeDcl($1, $2, synPartial, ',', syn, 'with', inh, ';'),
      appendAGDcl(
        makeOccursDclsHelp(qNameWithTL(qNameId(synPartial), botlNone()), qs.qnames),
        makeOccursDclsHelp(qNameWithTL(qNameId(syn), botlNone()), qs.qnames)));
}

concrete production threadedAttributeDclMultiple
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr  d::OptDirectionMod 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "threaded attribute " ++ inh.unparse ++ ", " ++ syn.name ++ tl.unparse ++ " :: " ++ te.unparse ++ d.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      threadedAttributeDcl($1, $2, inh, $4, syn, tl, $7, te, d, ';'),
      appendAGDcl(
        makeOccursDclsHelp(qNameWithTL(qNameId(inh), botlNone()), qs.qnames),
        makeOccursDclsHelp(qNameWithTL(qNameId(syn), botlNone()), qs.qnames)));
}

concrete production collectionThreadedAttributeDclMultiple
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator  d::OptDirectionMod 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "threaded attribute " ++ inh.unparse ++ ", " ++ syn.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ q.unparse ++ d.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      collectionThreadedAttributeDcl($1, $2, inh, $4, syn, tl, $7, te, 'with', q, d, ';'),
      appendAGDcl(
        makeOccursDclsHelp(qNameWithTL(qNameId(inh), botlNone()), qs.qnames),
        makeOccursDclsHelp(qNameWithTL(qNameId(syn), botlNone()), qs.qnames)));
}
