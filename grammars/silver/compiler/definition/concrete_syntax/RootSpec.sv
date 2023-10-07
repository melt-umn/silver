grammar silver:compiler:definition:concrete_syntax;

import silver:compiler:driver:util;

attribute syntaxAst, parserSpecs occurs on RootSpec, ModuleExportedDefs, Grammar;
propagate syntaxAst, parserSpecs on RootSpec, Grammar;

monoid attribute hasSyntaxAst::Boolean with false, ||;
monoid attribute hasParserSpecs::Boolean with false, ||;
attribute syntaxAst, parserSpecs, hasSyntaxAst, hasParserSpecs occurs on InterfaceItems, InterfaceItem;
propagate syntaxAst, parserSpecs, hasSyntaxAst, hasParserSpecs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.hasSyntaxAst then ["Missing item syntaxAst"] else [];
  top.interfaceErrors <- if !top.hasParserSpecs then ["Missing item parserSpecs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate syntaxAst, parserSpecs, hasSyntaxAst, hasParserSpecs;
}

abstract production syntaxAstInterfaceItem
top::InterfaceItem ::= val::[SyntaxDcl]
{
  propagate isEqual;
  top.syntaxAst <- val;
  top.hasSyntaxAst <- true;
}

abstract production parserSpecsInterfaceItem
top::InterfaceItem ::= val::[ParserSpec]
{
  propagate isEqual;
  top.parserSpecs <- val;
  top.hasParserSpecs <- true;
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [syntaxAstInterfaceItem(r.syntaxAst)];
  interfaceItems <- [parserSpecsInterfaceItem(r.parserSpecs)];
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= compiled::EnvTree<Decorated RootSpec>  grammarDependencies::[String]  need::[String]  seen::[String]
{
  top.syntaxAst := if null(need) || null(rs) then [] else (head(rs).syntaxAst ++ recurse.syntaxAst);
  top.parserSpecs := if null(need) || null(rs) then [] else (head(rs).parserSpecs ++ recurse.parserSpecs);
}

