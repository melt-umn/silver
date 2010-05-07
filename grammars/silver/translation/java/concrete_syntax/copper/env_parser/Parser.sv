grammar silver:translation:java:concrete_syntax:copper:env_parser;

import silver:translation:java:concrete_syntax:copper;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:env:parser;
import silver:definition:concrete_syntax:env_parser;

import silver:util;

terminal LexerClassTerm 'lexer_class' lexer classes {C_1};

concrete production aEnvItemLexerClass
top::aEnvItem ::= v::LexerClassTerm '(' n::Name ',' s::aNames ',' d::aNames ')'{
  top.defs = addLexerClassDcl(n.lexeme, s.names, d.names, emptyDefs());
}

concrete production aTerminalModifierSpecLexerClasses
top::aTerminalModifierSpec ::= l::LexerClassTerm n::aNames {
  top.terminalModifiers = [lexerClassesTerminalModifierSpec(n.names)];
}

terminal SubmitsTerm 'submits' lexer classes {C_1};
concrete production aTerminalModifierSpecSubmits
top::aTerminalModifierSpec ::= l::SubmitsTerm n::aNames {
  top.terminalModifiers = [submitsToTerminalModifierSpec(n.names)];
}

terminal DominatesTerm 'dominates' lexer classes {C_1};
concrete production aTerminalModifierSpecDominates
top::aTerminalModifierSpec ::= l::DominatesTerm n::aNames {
  top.terminalModifiers = [dominatesTerminalModifierSpec(n.names)];
}

attribute disambiguationGroupDcls occurs on aRootSpecParts, aRootSpecPart;
attribute parserAttrDcls occurs on aRootSpecParts, aRootSpecPart;
aspect production parserRootSpec
top::RootSpec ::= p::aRootSpecParts _
{
  top.disambiguationGroupDcls = p.disambiguationGroupDcls;
  top.parserAttrDcls = p.parserAttrDcls;
}

aspect production aRoot1
top::aRootSpecParts ::= r::aRootSpecPart
{
  top.disambiguationGroupDcls = r.disambiguationGroupDcls;
  top.parserAttrDcls = r.parserAttrDcls;
}

aspect production aRoot2
top::aRootSpecParts ::= r1::aRootSpecPart r2::aRootSpecParts
{
  top.disambiguationGroupDcls = r1.disambiguationGroupDcls ++ r2.disambiguationGroupDcls;
  top.parserAttrDcls = r1.parserAttrDcls ++ r2.parserAttrDcls;
}

aspect production aRootSpecDefault
top::aRootSpecPart ::= {
  top.disambiguationGroupDcls = [];
  top.parserAttrDcls = [];
}

terminal DisambiguationTerm /disambiguate/ lexer classes {C_1};
terminal EscapedStringTerm /"([^\"\\]|\\.)*"/ lexer classes {C_1};

function decodeEscapedStringTerm
String ::= s::String
{
  return unescapeString(substring(1,length(s)-1,s));
}

concrete production aDisambiguationGroup
top::aRootSpecPart ::= DisambiguationTerm '[' n::aNames ',' s::EscapedStringTerm ']'
{
  top.disambiguationGroupDcls = [disambiguationGroupSpec(n.names, decodeEscapedStringTerm(s.lexeme))];
  
  forwards to aRootSpecDefault();
}

concrete production aParserAttribute
top::aRootSpecPart ::= ParserAttrTerm '[' n::Name ',' t::aTypeRep ',' s::EscapedStringTerm ']'
{
  top.parserAttrDcls = [parserAttrSpec(n.lexeme, t.typerep, decodeEscapedStringTerm(s.lexeme))];
  
  forwards to aRootSpecDefault();
}

terminal ActionTerm 'action' lexer classes {C_1};
terminal LayoutTerm 'layout' lexer classes {C_1};

concrete production aTerminalModifierSpecAction
top::aTerminalModifierSpec ::= 'action' s::EscapedStringTerm {
  top.terminalModifiers = [actionCodeTerminalModifierSpec(decodeEscapedStringTerm(s.lexeme))];
}


concrete production aProductionModifierSpecAction
top::aProductionModifierSpec ::= 'action' s::EscapedStringTerm {
  top.productionModifiers = [actionProductionModifierSpec(decodeEscapedStringTerm(s.lexeme))];
}

concrete production aProductionModifierSpecLayout
top::aProductionModifierSpec ::= 'layout' n::aNames {
  top.productionModifiers = [layoutProductionModifierSpec(n.names)];
}

terminal ParserAttrTerm 'parserAttr' lexer classes {C_1};
concrete production aEnvItemParserAttr
top::aEnvItem ::= 'parserAttr' '(' n::Name ')'{
  top.defs = addParserAttrDcl( n.lexeme, emptyDefs());
}

