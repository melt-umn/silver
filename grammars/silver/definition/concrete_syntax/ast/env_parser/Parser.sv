grammar silver:definition:concrete_syntax:ast:env_parser;

import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:core only grammarName, location, env;
import silver:definition:concrete_syntax only parserSpecs, parserSpec, syntaxAst;
import silver:definition:concrete_syntax:ast;
import silver:definition:regex hiding RegexRBrack_t, RegexLBrack_t, RegexLParen_t, RegexRParen_t; -- TODO: a bit of a hack?
import silver:driver:util only RootSpec, interfaceRootSpec;

terminal SyntaxTerm 'syntax' lexer classes {C_1};
terminal ParsersTerm 'parsers' lexer classes {C_1};
terminal ParserTerm 'parser' lexer classes {C_1};

-- syntax dcls
-- nt, term, prod  already taken care of
terminal LClassTerm 'lclass' lexer classes {C_1};
terminal ParAttr 'pattr' lexer classes {C_1};
terminal DisTerm 'disambig' lexer classes {C_1};

-- modifiers
terminal PrecTerm 'prec' lexer classes {C_1};
terminal OperTerm 'oper' lexer classes {C_1};
terminal AcodeTerm 'acode' lexer classes {C_1};
terminal LayoutTerm 'layout' lexer classes {C_1};
terminal IgnoreTerm 'ignore' lexer classes {C_1};
terminal AssocTerm 'assoc' lexer classes {C_1};
terminal ClassesTerm 'classes' lexer classes {C_1};
terminal SubTerm 'sub' lexer classes {C_1};
terminal DomTerm 'dom' lexer classes {C_1};


attribute syntaxAst, parserSpecs occurs on IRoot, IRootPart;

--------------- i don't know yet ------------------------
aspect production interfaceRootSpec
top::RootSpec ::= p::IRoot
{
  top.syntaxAst = p.syntaxAst;
  top.parserSpecs = p.parserSpecs;
}
---------------------------------------------------------

aspect production aRoot1
top::IRoot ::= r::IRootPart
{
  top.syntaxAst = r.syntaxAst;
  top.parserSpecs = r.parserSpecs;
}

aspect production aRoot2
top::IRoot ::= r1::IRootPart r2::IRoot
{
  top.syntaxAst = r1.syntaxAst ++ r2.syntaxAst;
  top.parserSpecs = r1.parserSpecs ++ r2.parserSpecs;
}

----

aspect default production
top::IRootPart ::=
{
  top.syntaxAst = [];
  top.parserSpecs = [];
}

concrete production aRootSyntax
top::IRootPart ::= 'syntax' s::ISyntax
{
  top.syntaxAst = s.syntaxAst;
}

concrete production aRootParsers
top::IRootPart ::= 'parsers' s::IParsers
{
  top.parserSpecs = s.parserSpecs;
}

nonterminal IParsers with parserSpecs;
nonterminal IParsersInner with parserSpecs;
nonterminal IParser with parserSpecs;

concrete production aParsersNone
top::IParsers ::= '[' ']'
{
  top.parserSpecs = [];
}
concrete production aParsersSome
top::IParsers ::= '[' l::IParsersInner ']'
{
  top.parserSpecs = l.parserSpecs;
}

concrete production aParsersOne
top::IParsersInner ::= l::IParser
{
  top.parserSpecs = l.parserSpecs;
}
concrete production aParsersCons
top::IParsersInner ::= l::IParsersInner ',' r::IParser
{
  top.parserSpecs = l.parserSpecs ++ r.parserSpecs;
}

concrete production aParser
top::IParser ::= 'parser' '(' l::ILocation ',' g::IName ',' n::IName ',' snt::IName ',' gr::INames ')'
{
  top.parserSpecs = [parserSpec(l.location, g.aname, n.aname, snt.aname, gr.names)];
}


--------------------------------------------------------------------------------
nonterminal ISyntax with syntaxAst, grammarName;
nonterminal ISyntaxInner with syntaxAst, grammarName;
nonterminal ISyntaxDcl with syntaxAst, grammarName;

concrete production aSyntaxNone
top::ISyntax ::= '[' ']'
{
  top.syntaxAst = [];
}
concrete production aSyntaxSome
top::ISyntax ::= '[' l::ISyntaxInner ']'
{
  top.syntaxAst = l.syntaxAst;
}

concrete production aSyntaxOne
top::ISyntaxInner ::= l::ISyntaxDcl
{
  top.syntaxAst = l.syntaxAst;
}
concrete production aSyntaxCons
top::ISyntaxInner ::= l::ISyntaxInner ',' r::ISyntaxDcl
{
  top.syntaxAst = l.syntaxAst ++ r.syntaxAst;
}


concrete production aSyntaxNt
top::ISyntaxDcl ::= 'nt' '(' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = toEnv(td.defs);
  
  top.syntaxAst = [syntaxNonterminal(t.typerep, nilSyntax())];
}
concrete production aSyntaxTerm
top::ISyntaxDcl ::= 'term' '(' n::IName ',' '/' r::Regex_R '/' ',' tm::ITerminalModifiers ')'
{
  top.syntaxAst = [syntaxTerminal(n.aname, r, foldr(consTerminalMod, nilTerminalMod(), tm.terminalModifiers))];
}
concrete production aSyntaxProd
top::ISyntaxDcl ::= 'prod' '(' n::IName ',' td::ITyVarDcls ',' l::ITypeRep ',' r::ITypeReps ',' pm::IProductionModifiers ')'
{
  l.env = toEnv(td.defs);
  r.env = l.env;
  
  top.syntaxAst = [syntaxProduction(n.aname, l.typerep, r.typereps, foldr(consProductionMod, nilProductionMod(), pm.productionModifiers))];
}
concrete production aSyntaxLclass
top::ISyntaxDcl ::= 'lclass' '(' n::IName ',' d::INames ',' s::INames ')'
{
  top.syntaxAst = [syntaxLexerClass(n.aname, d.names, s.names)];
}
concrete production aSyntaxPattr
top::ISyntaxDcl ::= 'pattr' '(' n::IName ',' t::ITypeRep ',' s::IString ')'
{
  t.env = emptyEnv();
  top.syntaxAst = [syntaxParserAttribute(n.aname, t.typerep, s.str)];
}
concrete production aSyntaxDisambig
top::ISyntaxDcl ::= 'disambig' '(' n::IName ',' ts::INames ',' s::IString ')'
{
  top.syntaxAst = [syntaxDisambiguationGroup(n.aname, ts.names, s.str)];
}


--------------------------------------------------------------------------------
nonterminal ITerminalModifiers with terminalModifiers;
nonterminal ITerminalModifiersInner with terminalModifiers;
nonterminal ITerminalModifier with terminalModifiers;

synthesized attribute terminalModifiers :: [SyntaxTerminalModifier];

concrete production aTerminalModifiersNone
top::ITerminalModifiers ::= '[' ']'
{
  top.terminalModifiers = [];
}
concrete production aTerminalModifiersOne
top::ITerminalModifiers ::= '[' d::ITerminalModifiersInner ']'
{
  top.terminalModifiers = d.terminalModifiers;
}

concrete production aTerminalModifiersInnerOne
top::ITerminalModifiersInner ::= d::ITerminalModifier
{
  top.terminalModifiers = d.terminalModifiers;
}
concrete production aTerminalModifierInnersCons
top::ITerminalModifiersInner ::= d1::ITerminalModifier ',' d2::ITerminalModifiersInner
{
  top.terminalModifiers = d1.terminalModifiers ++ d2.terminalModifiers;
}


concrete production aTerminalModifierIgnore
top::ITerminalModifier ::= 'ignore' '(' ')'
{
  top.terminalModifiers = [termIgnore()];
}
concrete production aTerminalModifierPrec
top::ITerminalModifier ::= 'prec' '(' n::Num_t ')'
{
  top.terminalModifiers = [termPrecedence(toInt(n.lexeme))];
}
concrete production aTerminalModifierAssoc
top::ITerminalModifier ::= 'assoc' '(' n::IName ')'
{
  top.terminalModifiers = [termAssociation(n.aname)];
}
concrete production aTerminalModifierClasses
top::ITerminalModifier ::= 'classes' '(' n::INames ')'
{
  top.terminalModifiers = [termClasses(n.names)];
}
concrete production aTerminalModifierSubmits
top::ITerminalModifier ::= 'sub' '(' n::INames ')'
{
  top.terminalModifiers = [termSubmits(n.names)];
}
concrete production aTerminalModifierDominates
top::ITerminalModifier ::= 'dom' '(' n::INames ')'
{
  top.terminalModifiers = [termDominates(n.names)];
}
concrete production aTerminalModifierAcode
top::ITerminalModifier ::= 'acode' '(' s::IString ')'
{
  top.terminalModifiers = [termAction(s.str)];
}


--------------------------------------------------------------------------------
nonterminal IProductionModifiers with productionModifiers;
nonterminal IProductionModifiersInner with productionModifiers;
nonterminal IProductionModifier with productionModifiers;

synthesized attribute productionModifiers :: [SyntaxProductionModifier];

concrete production aProductionModifiersNone
top::IProductionModifiers ::= '[' ']'
{
  top.productionModifiers = [];
}
concrete production aProductionModifiersOne
top::IProductionModifiers ::= '[' d::IProductionModifiersInner ']'
{
  top.productionModifiers = d.productionModifiers;
}

concrete production aProductionModifiersInnerOne
top::IProductionModifiersInner ::= d::IProductionModifier
{
  top.productionModifiers = d.productionModifiers;
}
concrete production aProductionModifierInnersCons
top::IProductionModifiersInner ::= d1::IProductionModifier ',' d2::IProductionModifiersInner
{
  top.productionModifiers = d1.productionModifiers ++ d2.productionModifiers;
}


concrete production aProductionModifierPrecedence
top::IProductionModifier ::= 'prec' '(' n::Num_t ')'
{
  top.productionModifiers = [prodPrecedence(toInt(n.lexeme))];
}
concrete production aProductionModifierOperator
top::IProductionModifier ::= 'oper' '(' n::IName ')'
{
  top.productionModifiers = [prodOperator(n.aname)];
}
concrete production aProductionModifierAcode
top::IProductionModifier ::= 'acode' '(' s::IString ')'
{
  top.productionModifiers = [prodAction(s.str)];
}
concrete production aProductionModifierLayout
top::IProductionModifier ::= 'layout' '(' n::INames ')'
{
  top.productionModifiers = [prodLayout(n.names)];
}


--------------------------------------------------------------------------------
nonterminal IString with str;

terminal EscapedStringTerm /"([^\"\\]|\\.)*"/ lexer classes {C_1};

synthesized attribute str :: String;

concrete production aString
top::IString ::= s::EscapedStringTerm
{
  top.str = substitute("\\\"", "\"", substring(1,length(s.lexeme)-1,s.lexeme));
}

