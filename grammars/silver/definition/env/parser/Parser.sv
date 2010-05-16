grammar silver:definition:env:parser;

import silver:definition:env;
import silver:definition:type:anytype;
import silver:definition:type:io;
import silver:definition:regex;

import silver:definition:core only compiledGrammars;

lexer class C_0;
lexer class C_1 dominates C_0;

--langauge constants
ignore terminal ws /[\ \n\t]+/ lexer classes {C_0};
terminal lb '[';
terminal rb ']';
terminal comma ',';
terminal lp '(';
terminal rp ')';

terminal id /[\']([^\'\\]|[\\][\']|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\']/ lexer classes {C_0};
terminal number /[0-9]+/ lexer classes {C_0};

terminal RegExprDelim '/' lexer classes {C_0};

terminal DefaultTerm /default/ lexer classes {C_1};
terminal ProductionTerm /prod/ lexer classes {C_1};
terminal FunctionTerm /fun/ lexer classes {C_1};
terminal ValueTerm /value/ lexer classes {C_1};
terminal TypeTerm /type/ lexer classes {C_1};
terminal AttributeTerm /attr/ lexer classes {C_1};
terminal OccursTerm /@/ lexer classes {C_1};
terminal InheritedTerm /inh/ lexer classes {C_1};
terminal SynthesizedTerm /syn/ lexer classes {C_1};
terminal ThisTerm /this/ lexer classes {C_1};
terminal NameTerm /name/ lexer classes {C_1};
terminal ProductionAttributesTerm /pattrs/ lexer classes {C_1};
terminal IntegerTerm /int/ lexer classes {C_1};
terminal FloatTerm /float/ lexer classes {C_1};
terminal StringTerm /string/ lexer classes {C_1};
terminal BooleanTerm /bool/ lexer classes {C_1};
terminal TerminalTerm /term/ lexer classes {C_1};
terminal NonterminalTerm /nt/ lexer classes {C_1};
terminal DecoratedTerm /decorated/ lexer classes {C_1};
terminal AnyTypeTerm /anytype/ lexer classes {C_1};
terminal IOTerm /io/ lexer classes {C_1};
terminal TopTerm /top/ lexer classes {C_1};
terminal SignatureTerm /signature/ lexer classes {C_1};
terminal SignatureElementTerm /element/ lexer classes {C_1};

terminal DeclaredNameTerm /declaredName/ lexer classes {C_1};
terminal ModuleNamesTerm /moduleNames/ lexer classes {C_1};
terminal DefsTerm /defs/ lexer classes {C_1};
terminal ExportedGrammarsTerm /exportedGrammars/ lexer classes {C_1};
terminal CondBuildTerm 'condBuild' lexer classes {C_1};

synthesized attribute spec :: Decorated RootSpec;
synthesized attribute signature :: Decorated NamedSignature;
synthesized attribute elements :: [Decorated NamedSignatureElement];
synthesized attribute element :: Decorated NamedSignatureElement;
synthesized attribute typereps :: [Decorated TypeRep];
synthesized attribute names :: [String];

nonterminal aRootSpec with spec, compiledGrammars;
nonterminal aRootSpecPart with defs, exportedGrammars, condBuild, declaredName, moduleNames, compiledGrammars;
nonterminal aRootSpecParts with defs, exportedGrammars, condBuild, declaredName, moduleNames, compiledGrammars;
nonterminal aDefs with defs;
nonterminal aDefsInner with defs;
nonterminal aEnvItem with defs;
nonterminal aTypeRep with typerep;
nonterminal aTypeReps with typereps;
nonterminal aTypeRepsInner with typereps;
nonterminal aNamedSignature with signature;
nonterminal aNamedSignatureElement with element;
nonterminal aNamedSignatureElements with elements;
nonterminal aNamedSignatureElementsInner with elements;
nonterminal aNames with names;
nonterminal aNamesInner with names;

nonterminal Name with lexeme;
concrete production nnn
top::Name ::= i::id{
  top.lexeme = substring(1, length(i.lexeme)-1, i.lexeme);
}

abstract production parserRootSpec
top::RootSpec ::= p::aRootSpecParts cg::[Decorated RootSpec]{
  p.compiledGrammars = cg;

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
  top.declaredName = i.lexeme;
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
top::aDefsInner ::= d::aEnvItem {
  top.defs = d.defs;
}

concrete production aDefsInnerCons
top::aDefsInner ::= d1::aEnvItem ',' d2::aDefsInner {
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
  top.names = [d.lexeme];
}

concrete production aNamesInnerCons
top::aNamesInner ::= d1::Name ',' d2::aNamesInner {
  top.names = [d1.lexeme] ++ d2.names;
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

--The EnvItems
concrete production aEnvItemValue
top::aEnvItem ::= v::ValueTerm '(' n::Name ',' t::aTypeRep ')'{
  top.defs = addValueDcl(n.lexeme, t.typerep, emptyDefs());
}

concrete production aEnvItemType
top::aEnvItem ::= v::TypeTerm '(' n::Name ',' t::aTypeRep ')'{
  top.defs = addTypeDcl(n.lexeme, t.typerep, emptyDefs());
}

concrete production aEnvItemProduction
top::aEnvItem ::= v::ProductionTerm '(' s::aNamedSignature ')'{
  top.defs = addProductionDcl(s.signature, emptyDefs());
}

concrete production aEnvItemFunction
top::aEnvItem ::= v::FunctionTerm '(' s::aNamedSignature ')'{
  top.defs = addFunctionDcl(s.signature, emptyDefs());
}

concrete production aEnvItemAttribute
top::aEnvItem ::= v::AttributeTerm '(' n::Name ',' t::aTypeRep ')'{
  top.defs = addAttributeDcl(n.lexeme, t.typerep, emptyDefs());
}

concrete production aEnvItemOccurs
top::aEnvItem ::= v::OccursTerm '(' n1::Name ',' n2::Name ')'{
  top.defs = addOccursDcl( n1.lexeme, n2.lexeme, emptyDefs());
}

concrete production aEnvItemName
top::aEnvItem ::= v::NameTerm '(' n1::Name ',' n2::Name ')'{
  top.defs = addFullNameDcl( n1.lexeme, n2.lexeme, emptyDefs());
}

concrete production aEnvItemProductionAttributes
top::aEnvItem ::= v::ProductionAttributesTerm '(' n::Name ',' d::aDefs ')'{
  top.defs = addProductionAttributesDcl( n.lexeme, d.defs, emptyDefs());
}

concrete production aEnvItemDefault
top::aEnvItem ::= v::DefaultTerm {
  top.defs = newDefs(defaultEnvItem());
}


--The TypeReps
concrete production aTypeRepInteger
top::aTypeRep ::= t::IntegerTerm{
  top.typerep = integerTypeRep();
}

concrete production aTypeRepFloat
top::aTypeRep ::= t::FloatTerm{
  top.typerep = floatTypeRep();
}

concrete production aTypeRepString
top::aTypeRep ::= t::StringTerm{
  top.typerep = stringTypeRep();
}

concrete production aTypeRepBoolean
top::aTypeRep ::= t::BooleanTerm{
  top.typerep = booleanTypeRep();
}

concrete production aTypeRepTerminal
top::aTypeRep ::= t::TerminalTerm '(' n::Name ',' '/' r::Regex_R '/' ')' {
  top.typerep = termTypeRep(n.lexeme, r);
}

concrete production aTypeRepNonterminal
top::aTypeRep ::= t::NonterminalTerm '(' n::Name ')' {
  top.typerep = ntTypeRep(n.lexeme);
}

concrete production aTypeRepDecorated
top::aTypeRep ::= d::DecoratedTerm '(' t::aTypeRep ')' {
  top.typerep = refTypeRep(t.typerep);
}

concrete production aTypeRepProduction
top::aTypeRep ::= t::ProductionTerm '(' it::aTypeReps ','  ot::aTypeRep ')' {
  top.typerep = prodTypeRep(it.typereps, ot.typerep);
}

concrete production aTypeRepFunction
top::aTypeRep ::= t::FunctionTerm '(' it::aTypeReps ','  ot::aTypeRep ')' {
  top.typerep = funTypeRep(it.typereps, ot.typerep);
}

concrete production aTypeRepSyn
top::aTypeRep ::= 'syn' '(' t::aTypeRep ')'{
  top.typerep = synTypeRep(t.typerep);
}

concrete production aTypeRepInh
top::aTypeRep ::= 'inh' '(' t::aTypeRep ')'{
  top.typerep = inhTypeRep(t.typerep);
}

concrete production aTypeRepAnyType
top::aTypeRep ::= t::AnyTypeTerm {
  top.typerep = anyTypeTypeRep();
}

concrete production aTypeRepIO
top::aTypeRep ::= t::IOTerm {
  top.typerep = ioTypeRep();
}

concrete production aTypeRepTop 
top::aTypeRep ::= t::TopTerm {
  top.typerep = topTypeRep();
}

concrete production aTypeRepDefault
top::aTypeRep ::= t::DefaultTerm {
  top.typerep = defaultTypeRep();
}


--The NamedSignatures
concrete production aNamedSignatureDcl
top::aNamedSignature ::= s::SignatureTerm '(' fn::Name ',' i::aNamedSignatureElements ',' o::aNamedSignatureElement ')' {
  top.signature = namedSignatureDcl(fn.lexeme, i.elements, o.element);
}

concrete production aNamedSignatureDefault
top::aNamedSignature ::= s::SignatureTerm {
  top.signature = decorate namedSignatureDefault() with {};
}

concrete production aNamedSignatureElementDcl
top::aNamedSignatureElement ::= s::SignatureElementTerm '(' n::Name ',' t::aTypeRep ')' {
  top.element = namedSignatureElement(n.lexeme, t.typerep);
}

concrete production aNamedSignatureElementDclDefault
top::aNamedSignatureElement ::= s::SignatureElementTerm {
  top.element = decorate namedSignatureElementDefault() with {};
}
