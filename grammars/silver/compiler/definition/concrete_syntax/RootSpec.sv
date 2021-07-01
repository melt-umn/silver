grammar silver:compiler:definition:concrete_syntax;

import silver:compiler:driver:util;

attribute syntaxAst, parserSpecs occurs on RootSpec, ModuleExportedDefs, Grammar;
propagate syntaxAst, parserSpecs on Grammar;

monoid attribute maybeSyntaxAst::Maybe<[SyntaxDcl]> with nothing(), orElse;
monoid attribute maybeParserSpecs::Maybe<[ParserSpec]> with nothing(), orElse;
attribute maybeSyntaxAst, maybeParserSpecs occurs on InterfaceItems, InterfaceItem;
propagate maybeSyntaxAst, maybeParserSpecs on InterfaceItems;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.maybeSyntaxAst.isJust then ["Missing item syntaxAst"] else [];
  top.interfaceErrors <- if !top.maybeParserSpecs.isJust then ["Missing item parserSpecs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  top.maybeSyntaxAst := nothing();
  top.maybeParserSpecs := nothing();
}

abstract production syntaxAstInterfaceItem
top::InterfaceItem ::= val::[SyntaxDcl]
{
  top.maybeSyntaxAst := just(val);
}

abstract production parserSpecsInterfaceItem
top::InterfaceItem ::= val::[ParserSpec]
{
  top.maybeParserSpecs := just(val);
}

aspect function unparseRootSpec
ByteArray ::= r::Decorated RootSpec
{
  interfaceItems <- [syntaxAstInterfaceItem(r.syntaxAst)];
  interfaceItems <- [parserSpecsInterfaceItem(r.parserSpecs)];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  propagate syntaxAst, parserSpecs;
}

aspect production grammarRootSpec
top::RootSpec ::= c1::Grammar  _ _ _ _
{
  propagate syntaxAst, parserSpecs;
}

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer _
{
  top.syntaxAst := i.maybeSyntaxAst.fromJust;
  top.parserSpecs := i.maybeParserSpecs.fromJust;
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= l::Location  compiled::EnvTree<Decorated RootSpec>  grammarDependencies::[String]  need::[String]  seen::[String]
{
  top.syntaxAst := if null(need) || null(rs) then [] else (head(rs).syntaxAst ++ recurse.syntaxAst);
  top.parserSpecs := if null(need) || null(rs) then [] else (head(rs).parserSpecs ++ recurse.parserSpecs);
}

