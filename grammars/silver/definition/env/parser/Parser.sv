grammar silver:definition:env:parser;

import silver:definition:env;
import silver:definition:type:io;
import silver:definition:regex hiding RegexRBrack_t, RegexLBrack_t, RegexLParen_t, RegexRParen_t; -- TODO: a bit of a hack?
import silver:definition:type;

-- TODO: why is compiledGrammars here?
import silver:definition:core only compiledGrammars, grammarName, location;

lexer class C_0;
lexer class C_1 dominates C_0;

ignore terminal ws /[\ \n\t]+/ lexer classes {C_0};

terminal lb    '[';
terminal rb    ']';
terminal comma ',';
terminal lp    '(';
terminal rp    ')';
terminal RegExprDelim '/' lexer classes {C_0};

terminal id /[\']([^\'\\]|[\\][\']|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\']/ lexer classes {C_0};
terminal number /\-?[0-9]+/ lexer classes {C_0};

terminal DefaultTerm  'default' lexer classes {C_1};

-- Dcls
terminal LocalTerm       'loc'  lexer classes {C_1};
terminal ProductionTerm  'prod' lexer classes {C_1};
terminal FunctionTerm    'fun'  lexer classes {C_1};
--terminal NontermTerm     'nt'   lexer classes {C_1};
--terminal TermTerm        'term' lexer classes {C_1};
terminal InheritedTerm   'inh'  lexer classes {C_1};
terminal SynthesizedTerm 'syn'  lexer classes {C_1};
terminal OccursTerm      '@'    lexer classes {C_1};
terminal ProdAttrTerm    'p@'   lexer classes {C_1};
terminal ForwardTerm     'fwd'  lexer classes {C_1};
terminal GlobTerm     'glob' lexer classes {C_1};

--terminal NameTerm 'name' lexer classes {C_1};

-- Types
terminal IntegerTerm     'int'       lexer classes {C_1};
terminal FloatTerm       'float'     lexer classes {C_1};
terminal StringTerm      'string'    lexer classes {C_1};
terminal BooleanTerm     'bool'      lexer classes {C_1};
terminal TerminalTerm    'term'      lexer classes {C_1};
terminal NonterminalTerm 'nt'        lexer classes {C_1};
terminal DecoratedTerm   'decorated' lexer classes {C_1};
terminal IOTerm          'io'        lexer classes {C_1};
terminal TopTerm         'top'       lexer classes {C_1};

-- signatures
terminal SignatureTerm      'signature' lexer classes {C_1};
terminal SignatureElementTerm 'element' lexer classes {C_1};

-- top level, root spec parts
terminal DeclaredNameTerm     'declaredName'     lexer classes {C_1};
terminal ModuleNamesTerm      'moduleNames'      lexer classes {C_1};
terminal DefsTerm             'defs'             lexer classes {C_1};
terminal ExportedGrammarsTerm 'exportedGrammars' lexer classes {C_1};
terminal CondBuildTerm        'condBuild'        lexer classes {C_1};


synthesized attribute spec :: Decorated RootSpec;
synthesized attribute signature :: Decorated NamedSignature;
synthesized attribute elements :: [Decorated NamedSignatureElement];
synthesized attribute element :: Decorated NamedSignatureElement;
synthesized attribute typereps :: [TypeExp];
synthesized attribute names :: [String];
synthesized attribute aname :: String;

nonterminal aRootSpec with spec, compiledGrammars;
nonterminal aRootSpecPart with defs, exportedGrammars, condBuild, declaredName, moduleNames, compiledGrammars, grammarName;
nonterminal aRootSpecParts with defs, exportedGrammars, condBuild, declaredName, moduleNames, compiledGrammars, grammarName;
nonterminal aDefs with defs, grammarName;
nonterminal aDefsInner with defs, grammarName;
nonterminal aDclInfo with defs, grammarName;
nonterminal aTypeRep with typerep;
nonterminal aTypeReps with typereps;
nonterminal aTypeRepsInner with typereps;
nonterminal aNamedSignature with signature;
nonterminal aNamedSignatureElement with element;
nonterminal aNamedSignatureElements with elements;
nonterminal aNamedSignatureElementsInner with elements;
nonterminal aNames with names;
nonterminal aNamesInner with names;
nonterminal aLocation with location;

nonterminal Name with aname;

-- a few simple utilities

concrete production quoted_name
top::Name ::= i::id
{
  top.aname = substring(1, length(i.lexeme)-1, i.lexeme);
}

concrete production aLocationInfo
top::aLocation ::= filename::Name ',' line::number ',' column::number
{
  top.location = loc(filename.aname, toInt(line.lexeme), toInt(column.lexeme));
}

-- Exposing the interface to the outside world

abstract production parserRootSpec
top::RootSpec ::= p::aRootSpecParts cg::[Decorated RootSpec]
{
  p.compiledGrammars = cg;
  p.grammarName = p.declaredName;

  top.unparse = unparseRootSpec(top).unparse;

  top.declaredName = p.declaredName; 
  top.moduleNames = p.moduleNames;
  top.defs = p.defs;
  top.exportedGrammars = p.exportedGrammars;
  top.condBuild = p.condBuild;

  forwards to i_emptyRootSpec();
}


--The Grammar 

concrete production aRootFull
top::aRootSpec ::= r::aRootSpecParts{
  top.spec = decorate parserRootSpec(r, top.compiledGrammars) with { };
}

concrete production aRoot1
top::aRootSpecParts ::= r::aRootSpecPart{
  top.declaredName = r.declaredName; 
  top.defs = r.defs;
  top.moduleNames = [];
  top.exportedGrammars = r.exportedGrammars;
  top.condBuild = r.condBuild;  
}

concrete production aRoot2
top::aRootSpecParts ::= r1::aRootSpecPart r2::aRootSpecParts{
  top.declaredName = if r1.declaredName == "" then r2.declaredName else r1.declaredName; 
  top.defs = appendDefs(r1.defs, r2.defs);
  top.moduleNames = r1.moduleNames ++ r2.moduleNames;
  top.exportedGrammars = r1.exportedGrammars ++ r2.exportedGrammars;
  top.condBuild = r1.condBuild ++ r2.condBuild;
}

--The pieces
abstract production aRootSpecDefault
top::aRootSpecPart ::= {
  top.declaredName = "";
  top.moduleNames = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production aRootDeclaredName
top::aRootSpecPart ::= n::DeclaredNameTerm i::Name{
  top.declaredName = i.aname;
  forwards to aRootSpecDefault();
}

concrete production aRootModuleNames
top::aRootSpecPart ::= n::ModuleNamesTerm i::aNames{
  top.moduleNames = i.names;
  forwards to aRootSpecDefault();
}

concrete production aRootDefs
top::aRootSpecPart ::= n::DefsTerm i::aDefs{
  top.defs = i.defs;
  forwards to aRootSpecDefault();
}

concrete production aRootExportedGrammars
top::aRootSpecPart ::= n::ExportedGrammarsTerm i::aNames{
  top.exportedGrammars = i.names;
  forwards to aRootSpecDefault();
}

concrete production aRootCondBuilds
top::aRootSpecPart ::= n::CondBuildTerm i::aNames{
  top.condBuild = unfoldCB(i.names);
  forwards to aRootSpecDefault();
}

function unfoldCB
[[String]] ::= lst::[String]
{
  return if null(lst) then [] else cons( [head(lst), head(tail(lst))], unfoldCB(tail(tail(lst))));
}

--The lists
concrete production aDefsNone
top::aDefs ::= '[' ']' {
  top.defs = emptyDefs();
}

concrete production aDefsOne
top::aDefs ::= '[' d::aDefsInner ']' {
  top.defs = d.defs;
}

concrete production aDefsInnerOne
top::aDefsInner ::= d::aDclInfo {
  top.defs = d.defs;
}

concrete production aDefsInnerCons
top::aDefsInner ::= d1::aDclInfo ',' d2::aDefsInner {
  top.defs = appendDefs(d1.defs, d2.defs);
}

concrete production aNamesNone
top::aNames ::= '[' ']' {
  top.names = [];
}

concrete production aNamesOne
top::aNames ::= '[' d::aNamesInner ']' {
  top.names = d.names;
}

concrete production aNamesInnerOne
top::aNamesInner ::= d::Name {
  top.names = [d.aname];
}

concrete production aNamesInnerCons
top::aNamesInner ::= d1::Name ',' d2::aNamesInner {
  top.names = [d1.aname] ++ d2.names;
}

concrete production aTypeRepsNone
top::aTypeReps ::= '[' ']' {
  top.typereps = [];
}

concrete production aTypeRepsOne
top::aTypeReps ::= '[' t::aTypeRepsInner ']' {
  top.typereps = t.typereps;
}

concrete production aTypeRepsInnerOne
top::aTypeRepsInner ::= t::aTypeRep{
  top.typereps = [t.typerep];
}

concrete production aTypeRepsInnerCons
top::aTypeRepsInner ::= t1::aTypeRep ',' t2::aTypeRepsInner {
  top.typereps = [t1.typerep] ++ t2.typereps;
}

concrete production aNamedSignatureElementsNone
top::aNamedSignatureElements ::= '['']'{
  top.elements = [];
}

concrete production aNamedSignatureElementsOne
top::aNamedSignatureElements ::= '[' t::aNamedSignatureElementsInner ']'{
  top.elements = t.elements;
}

concrete production aNamedSignatureElementsInnerOne
top::aNamedSignatureElementsInner ::= t::aNamedSignatureElement{
  top.elements = [t.element];
}

concrete production aNamedSignatureElementsInnerCons
top::aNamedSignatureElementsInner ::= t1::aNamedSignatureElement ',' t2::aNamedSignatureElementsInner {
  top.elements = [t1.element] ++ t2.elements;
}

--The DclInfos

concrete production aDclInfoLocal
top::aDclInfo ::= 'loc' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ')'
{
  top.defs = addLocalDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

concrete production aDclInfoProduction
top::aDclInfo ::= 'prod' '(' l::aLocation ',' s::aNamedSignature ')'
{
  top.defs = addProdDcl(top.grammarName, l.location, s.signature, emptyDefs());
}

concrete production aDclInfoFunction
top::aDclInfo ::= 'fun' '(' l::aLocation ',' s::aNamedSignature ')'
{
  top.defs = addFunDcl(top.grammarName, l.location, s.signature, emptyDefs());
}

concrete production aDclInfoNonterminal
top::aDclInfo ::= 'nt' '(' l::aLocation ',' s::Name ')'
{
  top.defs = addNtDcl(top.grammarName, l.location, s.aname, [], errorType(), emptyDefs()); -- TODO SFA#@$%$@
}

concrete production aDclInfoTerminal
top::aDclInfo ::= 'term' '(' l::aLocation ',' n::Name ',' '/' r::Regex_R '/' ')'
{
  top.defs = addTermDcl(top.grammarName, l.location, n.aname, r, emptyDefs());
}

concrete production aDclInfoSynthesized
top::aDclInfo ::= 'syn' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ')'
{
  top.defs = addSynDcl(top.grammarName, l.location, fn.aname, [], t.typerep, emptyDefs()); -- TODO @#@$%#$#
}

concrete production aDclInfoInherited
top::aDclInfo ::= 'inh' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ')'
{
  top.defs = addInhDcl(top.grammarName, l.location, fn.aname, [], t.typerep, emptyDefs()); -- TODO @$@#%#
}

concrete production aDclInfoProdAttr
top::aDclInfo ::= 'p@' '(' l::aLocation ',' fn::Name ',' t::aDclInfo ')'
{
  -- TODO: this reaches into the defs structure a bit.  kinda ugly?
  top.defs = addPaDcl(top.grammarName, l.location, fn.aname, new(head(t.defs.valueList).dcl), emptyDefs());
}

concrete production aDclInfoForward
top::aDclInfo ::= 'fwd' '(' l::aLocation ',' t::aTypeRep ')'
{
  top.defs = addForwardDcl(top.grammarName, l.location, t.typerep, emptyDefs());
}

concrete production aDclInfoOccurs
top::aDclInfo ::= '@' '(' l::aLocation ',' fnnt::Name ',' fnat::Name ')'{
  top.defs = addOccursDcl( top.grammarName, l.location, fnnt.aname, fnat.aname, errorType(), errorType(), emptyDefs()); -- TODO @%#%@^$^@$&@
}

concrete production aDclInfoGlobalValue
top::aDclInfo ::= 'glob' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ')'
{
  top.defs = addGlobalValueDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

--The TypeReps
concrete production aTypeRepInteger
top::aTypeRep ::= t::IntegerTerm{
  top.typerep = intTypeExp();
}

concrete production aTypeRepFloat
top::aTypeRep ::= t::FloatTerm{
  top.typerep = floatTypeExp();
}

concrete production aTypeRepString
top::aTypeRep ::= t::StringTerm{
  top.typerep = stringTypeExp();
}

concrete production aTypeRepBoolean
top::aTypeRep ::= t::BooleanTerm{
  top.typerep = boolTypeExp();
}

concrete production aTypeRepTerminal
top::aTypeRep ::= t::TerminalTerm '(' n::Name ')' {
  top.typerep = terminalTypeExp(n.aname);
}

concrete production aTypeRepNonterminal
top::aTypeRep ::= t::NonterminalTerm '(' n::Name ')' {
  top.typerep = nonterminalTypeExp(n.aname,[]); -- TODO BUG BROKEN
}

concrete production aTypeRepDecorated
top::aTypeRep ::= d::DecoratedTerm '(' t::aTypeRep ')' {
  top.typerep = decoratedTypeExp(t.typerep);
}

concrete production aTypeRepProduction
top::aTypeRep ::= t::ProductionTerm '(' it::aTypeReps ','  ot::aTypeRep ')' {
  top.typerep = productionTypeExp(ot.typerep, it.typereps);
}

concrete production aTypeRepFunction
top::aTypeRep ::= t::FunctionTerm '(' it::aTypeReps ','  ot::aTypeRep ')' {
  top.typerep = functionTypeExp(ot.typerep, it.typereps);
}

concrete production aTypeRepIO
top::aTypeRep ::= t::IOTerm {
  top.typerep = ioTypeExp();
}

--The NamedSignatures
concrete production aNamedSignatureDcl
top::aNamedSignature ::= s::SignatureTerm '(' fn::Name ',' i::aNamedSignatureElements ',' o::aNamedSignatureElement ')' {
  top.signature = namedSignatureDcl(fn.aname, i.elements, o.element);
}

concrete production aNamedSignatureDefault
top::aNamedSignature ::= s::SignatureTerm {
  top.signature = decorate namedSignatureDefault() with {};
}

concrete production aNamedSignatureElementDcl
top::aNamedSignatureElement ::= s::SignatureElementTerm '(' n::Name ',' t::aTypeRep ')' {
  top.element = namedSignatureElement(n.aname, t.typerep);
}

concrete production aNamedSignatureElementDclDefault
top::aNamedSignatureElement ::= s::SignatureElementTerm {
  top.element = decorate namedSignatureElementDefault() with {};
}
