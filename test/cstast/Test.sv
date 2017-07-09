grammar cstast;

imports silver:testing;
imports lib:extcore;
imports silver:definition:concrete_syntax only syntaxAst;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:concrete_syntax:ast:env_parser;
imports silver:definition:env:env_parser only WS, RegExprDelim;
imports silver:definition:type;
imports silver:definition:regex;
imports silver:definition:env;

mainTestSuite csttests;

disambiguate RegexChar_t, WS { pluck WS; }
disambiguate RegexChar_t, RegExprDelim { pluck RegExprDelim; }
parser syntaxInterfaceParser :: ISyntaxInner {
  cstast;
  silver:definition:concrete_syntax:ast:env_parser;
  silver:definition:env:env_parser;
  silver:definition:regex;
}


global obj::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), 
     [syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", regexLiteral("x"), nilTerminalMod()),
      syntaxProduction(
        namedSignature("foo", [],
          namedSignatureElement("asdf", nonterminalTypeExp("Foo", [])), []),
        nilProductionMod())
     ]), []);

equalityTest( obj.cstErrors, [], [String], csttests );
equalityTest( obj.unparse, "nt([], nt('Foo', [])),\n term('XTerm', /x/, []),\n prod([], signature('foo', [], element('asdf', nt('Foo', [])), []), [])", String, csttests );

global obj_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), 
      syntaxInterfaceParser(obj.unparse, "<>").parseTree.syntaxAst), []);

equalityTest( obj_again.xmlCopper, obj.xmlCopper, String, csttests );
equalityTest( obj_again.unparse, obj.unparse, String, csttests );

global obj3::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), [
      syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", regexLiteral("x"), 
        foldr(consTerminalMod, nilTerminalMod(), [
          termIgnore(),
          termPrecedence(3),
          termAssociation("left"),
          termClasses(["A","B"]),
          termSubmits(["XTerm"]),
          termDominates(["C"]),
          termAction("blah;")
        ])),
      syntaxProduction(
        namedSignature("foo", [],
          namedSignatureElement("asdf", nonterminalTypeExp("Foo", [])), []),
        foldr(consProductionMod, nilProductionMod(), [
          prodPrecedence(2),
          prodOperator("XTerm"),
          prodAction("asdf;"),
          prodLayout(["XTerm", "C"])
        ])),
      syntaxLexerClass("A", 
        foldr(consLexerClassMod, nilLexerClassMod(), [
          lexerClassDominates(["B"]),
          lexerClassSubmits(["C"])
        ])),
      syntaxLexerClass("B", nilLexerClassMod()),
      syntaxTerminal("C", regexLiteral("y"), nilTerminalMod()),
      syntaxParserAttribute("asdf", stringTypeExp(), "asdf = 'asfd';"),
      syntaxDisambiguationGroup("g23", ["XTerm", "C"], "return C;")
     ]), []);

global obj3_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(),
      syntaxInterfaceParser(obj3.unparse, "<>").parseTree.syntaxAst), []);

equalityTest( obj3.cstErrors, [], [String], csttests );
--equalityTest( obj3.unparse, "", String, csttests );

equalityTest( obj3_again.xmlCopper, obj3.xmlCopper, String, csttests );
equalityTest( obj3_again.unparse, obj3.unparse, String, csttests );

