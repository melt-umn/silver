grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:env;
import silver:translation:java:core;
import silver:translation:java:concrete_syntax;
import silver:translation:java:type;
import silver:analysis:typechecking:core;

import silver:util;

terminal Layout_kwd 'layout' lexer classes {KEYWORD};

synthesized attribute customLayout::[String] occurs on RHSSpec, ProductionModifierSpec;
synthesized attribute hasCustomLayout::Boolean occurs on RHSSpec, ProductionModifierSpec;

function layoutProductionModifierSpec
Decorated ProductionModifierSpec ::= s::[String]
{
  return decorate i_layoutProductionModifierSpec(s) with {};
}

abstract production i_layoutProductionModifierSpec
top::ProductionModifierSpec ::= s::[String]
{
  top.unparse = "layout [" ++ implode(", ",quoteStrings(s)) ++ "]";
  top.customLayout = s;
  top.hasCustomLayout = true;
  forwards to defaultProductionModifierSpec();
}

aspect production defaultProductionModifierSpec
top::ProductionModifierSpec ::={
  top.customLayout = [];
  top.hasCustomLayout = false;
}

aspect production i_rhsSpec
top::RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec]
{
  local attribute cl::[[String]];
  cl = findCustomLayout(pm);
  
  top.customLayout = if null(cl) then [] else head(cl);
  top.hasCustomLayout = !null(cl);
}

function findCustomLayout
[[String]] ::= l::[Decorated ProductionModifierSpec]{
  return if null(l) then [] else if head(l).hasCustomLayout then [head(l).customLayout] else findCustomLayout(tail(l));
}

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.pp = "layout {" ++ terms.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionModifiers = [layoutProductionModifierSpec(terms.precTermList)];
  top.errors := terms.errors;
}
