grammar silver:modification:copper:env_parser;

import silver:definition:env:env_parser;
--import silver:definition:concrete_syntax:env_parser;

import silver:modification:copper hiding Disambiguation_kwd, Submits_t, Dominates_t, Action_kwd, Layout_kwd; -- TODO: hiding here is a hack of sorts...

import silver:definition:env;
import silver:definition:concrete_syntax;

import silver:definition:core only grammarName, location, env;

import silver:util;

--------------------------------------------------------------------------------
-- DclInfos

terminal LexerClassTerm 'lexer_class' lexer classes {C_1};
terminal ParseAttrTerm 'parse_attr' lexer classes {C_1};

concrete production aDclInfoLexerClass
top::IDclInfo ::= 'lexer_class' '(' l::ILocation ',' fn::IName ')'
{
  top.defs = [lexerClassDef(top.grammarName, l.alocation, fn.aname)];
}

concrete production aDclInfoParseAttr
top::IDclInfo ::= 'parse_attr' '(' l::ILocation ',' fn::IName ',' t::ITypeRep ')'
{
  top.defs = [parserAttrDef(top.grammarName, l.alocation, fn.aname, t.typerep)];
}


