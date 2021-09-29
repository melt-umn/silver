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
      functorAttributeDcl($1, $2, a, $7, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production monoidAttributeDclMultiple
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' q::NameOrBOperator 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "monoid attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " with " ++ e.unparse ++ ", " ++ q.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      monoidAttributeDcl($1, $2, a, tl, $5, te, $7, e, $9, q, $14, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production tcMonoidAttributeDclMultiple
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "monoid attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      tcMonoidAttributeDcl($1, $2, a, tl, $5, te, $10, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production destructAttributeDclMultiple
top::AGDcl ::= 'destruct' 'attribute' a::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "destruct attribute " ++ a.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl($1, $2, a, $7, location=a.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(a, location=a.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production equalityAttributeDclMultiple
top::AGDcl ::= 'equality' 'attribute' syn::Name 'with' inh::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "equality attribute " ++ syn.name ++ " with " ++ inh.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      equalityAttributeDcl($1, $2, syn, $4, inh, $9, location=top.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(syn, location=syn.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

concrete production orderingAttributeDclMultiple
top::AGDcl ::= 'ordering' 'attribute' syn::Name 'with' inh::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "ordering attribute " ++ syn.name ++ " with " ++ inh.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      orderingAttributeDcl($1, $2, syn, $4, inh, $9, location=top.location),
      makeOccursDclsHelp($1.location, qNameWithTL(qNameId(syn, location=syn.location), botlNone(location=top.location)), qs.qnames),
      location=top.location);
}

-- Deprecate?  Eric suggested keeping this: https://github.com/melt-umn/silver/issues/431#issuecomment-760552226
concrete production destructEqualityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name ';'
{
  top.unparse = "equality attribute " ++ inh.unparse ++ ", " ++ syn.name ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl('destruct', $2, inh, $6, location=top.location),
      equalityAttributeDcl($1, $2, syn, 'with', inh, $6, location=top.location),
      location=top.location);
}
concrete production destructEqualityAttributeDclMultiple
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "equality attribute " ++ inh.unparse ++ ", " ++ syn.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDclMultiple('destruct', $2, inh, $6, $7, qs, $9, location=top.location),
      equalityAttributeDclMultiple($1, $2, syn, 'with', inh, $6, $7, qs, $9, location=top.location),
      location=top.location);
}
concrete production destructOrderingAttributeDcl
top::AGDcl ::= 'ordering' 'attribute' inh::Name ',' syn::Name ';'
{
  top.unparse = "ordering attribute " ++ inh.unparse ++ ", " ++ syn.name ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDcl('destruct', $2, inh, $6, location=top.location),
      orderingAttributeDcl($1, $2, syn, 'with', inh, $6, location=top.location),
      location=top.location);
}
concrete production destructOrderingAttributeDclMultiple
top::AGDcl ::= 'ordering' 'attribute' inh::Name ',' syn::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "ordering attribute " ++ inh.unparse ++ ", " ++ syn.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      destructAttributeDclMultiple('destruct', $2, inh, $6, $7, qs, $9, location=top.location),
      orderingAttributeDclMultiple($1, $2, syn, 'with', inh, $6, $7, qs, $9, location=top.location),
      location=top.location);
}

concrete production unificationAttributeDclMultiple
top::AGDcl ::= 'unification' 'attribute' inh::Name i::TypeExpr ',' synPartial::Name ',' syn::Name 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "equality attribute " ++ inh.unparse ++ " " ++ i.unparse ++ ", " ++ synPartial.name ++ ", " ++ syn.name ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      unificationAttributeDcl($1, $2, inh, i, $5, syn, $7, synPartial, $12, location=top.location),
      appendAGDcl(
        makeOccursDclsHelp($1.location, qNameWithTL(qNameId(inh, location=inh.location), botlNone(location=top.location)), qs.qnames),
        appendAGDcl(
          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(synPartial, location=synPartial.location), botlNone(location=top.location)), qs.qnames),
          makeOccursDclsHelp($1.location, qNameWithTL(qNameId(syn, location=syn.location), botlNone(location=top.location)), qs.qnames),
          location=top.location),
        location=top.location),
      location=top.location);
}

concrete production threadedAttributeDclMultiple
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "threaded attribute " ++ inh.unparse ++ ", " ++ syn.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";";
  forwards to
    appendAGDcl(
      threadedAttributeDcl($1, $2, inh, $4, syn, tl, $7, te, $12, location=top.location),
      appendAGDcl(
        makeOccursDclsHelp($1.location, qNameWithTL(qNameId(inh, location=inh.location), botlNone(location=top.location)), qs.qnames),
        makeOccursDclsHelp($1.location, qNameWithTL(qNameId(syn, location=syn.location), botlNone(location=top.location)), qs.qnames),
        location=top.location),
      location=top.location);
}
