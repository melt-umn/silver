grammar silver:definition:concrete_syntax;

import silver:driver:util;

attribute syntaxAst, parserSpecs occurs on RootSpec, ModuleExportedDefs, Grammar, GrammarProperties;

aspect production consGrammarProperties
top::GrammarProperties ::= h::GrammarProperty t::GrammarProperties
{
  top.syntaxAst = fromMaybe(t.syntaxAst, h.maybeSyntaxAst);
  top.parserSpecs = fromMaybe(t.parserSpecs, h.maybeParserSpecs);
}

aspect production nilGrammarProperties
top::GrammarProperties ::=
{
  top.syntaxAst = error("Grammar property syntaxAst missing from interface file");
  top.parserSpecs = error("Grammar property parserSpecs missing from interface file");
}

synthesized attribute maybeSyntaxAst::Maybe<[SyntaxDcl]> occurs on GrammarProperty;
synthesized attribute maybeParserSpecs::Maybe<[ParserSpec]> occurs on GrammarProperty;

aspect default production
top::GrammarProperty ::=
{
  top.maybeSyntaxAst = nothing();
  top.maybeParserSpecs = nothing();
}

abstract production syntaxAstGrammarProperty
top::GrammarProperty ::= val::[SyntaxDcl]
{
  top.maybeSyntaxAst = just(val);
}

abstract production parserSpecsGrammarProperty
top::GrammarProperty ::= val::[ParserSpec]
{
  top.maybeParserSpecs = just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  grammarProperties <- [syntaxAstGrammarProperty(r.syntaxAst)];
  grammarProperties <- [parserSpecsGrammarProperty(r.parserSpecs)];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}

aspect production grammarRootSpec
top::RootSpec ::= c1::Grammar  _ _ _
{
  top.syntaxAst = c1.syntaxAst;
  top.parserSpecs = c1.parserSpecs;
}

aspect production interfaceRootSpec
top::RootSpec ::= p::GrammarProperties  interfaceTime::Integer
{
  top.syntaxAst = p.syntaxAst;
  top.parserSpecs = p.parserSpecs;
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

