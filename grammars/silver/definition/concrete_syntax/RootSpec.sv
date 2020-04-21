grammar silver:definition:concrete_syntax;

import silver:driver:util;

attribute syntaxAst, parserSpecs occurs on RootSpec, ModuleExportedDefs, Grammar;

synthesized attribute maybeSyntaxAst::Maybe<[SyntaxDcl]> occurs on InterfaceItems, InterfaceItem;
synthesized attribute maybeParserSpecs::Maybe<[ParserSpec]> occurs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.maybeSyntaxAst = orElse(t.maybeSyntaxAst, h.maybeSyntaxAst);
  top.maybeParserSpecs = orElse(t.maybeParserSpecs, h.maybeParserSpecs);
  top.interfaceErrors <- if !top.maybeSyntaxAst.isJust then ["Missing item syntaxAst"] else [];
  top.interfaceErrors <- if !top.maybeParserSpecs.isJust then ["Missing item parserSpecs"] else [];
}

aspect production nilInterfaceItem
top::InterfaceItems ::=
{
  top.maybeSyntaxAst = nothing();
  top.maybeParserSpecs = nothing();
}

aspect default production
top::InterfaceItem ::=
{
  top.maybeSyntaxAst = nothing();
  top.maybeParserSpecs = nothing();
}

abstract production syntaxAstInterfaceItem
top::InterfaceItem ::= val::[SyntaxDcl]
{
  top.maybeSyntaxAst = just(val);
}

abstract production parserSpecsInterfaceItem
top::InterfaceItem ::= val::[ParserSpec]
{
  top.maybeParserSpecs = just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  interfaceItems <- [syntaxAstInterfaceItem(r.syntaxAst)];
  interfaceItems <- [parserSpecsInterfaceItem(r.parserSpecs)];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}

aspect production grammarRootSpec
top::RootSpec ::= c1::Grammar  _ _ _ _
{
  top.syntaxAst = c1.syntaxAst;
  top.parserSpecs = c1.parserSpecs;
}

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer _
{
  top.syntaxAst = i.maybeSyntaxAst.fromJust;
  top.parserSpecs = i.maybeParserSpecs.fromJust;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.syntaxAst = c1.syntaxAst ++ c2.syntaxAst;
  top.parserSpecs = c1.parserSpecs ++ c2.parserSpecs;
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= l::Location  compiled::EnvTree<Decorated RootSpec>  grammarDependencies::[String]  need::[String]  seen::[String]
{
  top.syntaxAst = if null(need) || null(rs) then [] else (head(rs).syntaxAst ++ recurse.syntaxAst);
  top.parserSpecs = if null(need) || null(rs) then [] else (head(rs).parserSpecs ++ recurse.parserSpecs);
}

