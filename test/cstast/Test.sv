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
      syntaxTerminal("XTerm", literalRegex("x"), nilTerminalMod()),
      syntaxProduction(
        namedSignature("foo", [],
          namedSignatureElement("asdf", nonterminalTypeExp("Foo", [])), []),
        nilProductionMod())
     ]));

equalityTest( obj.cstErrors, [], [String], csttests );
--equalityTest( substitute("silver:definition:concrete_syntax:ast:", "", hackUnparse(obj)), "", String, csttests );
--equalityTest( substitute("silver:definition:concrete_syntax:ast:", "", hackUnparse(obj.cstNormal)), "", String, csttests );
-- This test is super fragile...
equalityTest( obj.xmlCopper, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n  <Parser id=\"lol\" isUnitary=\"true\">\n    <PP>lol</PP>\n    <Grammars><GrammarRef id=\"host\"/></Grammars>\n    <StartSymbol><NonterminalRef id=\"Foo\" grammar=\"host\" /></StartSymbol>\n    <StartLayout></StartLayout>\n  </Parser>\n\n  <Grammar id=\"host\">\n\n    <PP>host</PP>\n\n    <Layout></Layout>\n\n    <Declarations>\n      <ParserAttribute id=\"context\">\n        <Type><![CDATA[common.DecoratedNode]]></Type>\n        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n      </ParserAttribute>\n  <Terminal id=\"XTerm\">\n    <PP>XTerm</PP>\n    <Regex><CharacterSet><SingleCharacter char=\"x\"/></CharacterSet></Regex>\n    <Type>common.TerminalRecord</Type>\n    <Code><![CDATA[\nRESULT = new common.TerminalRecord(lexeme,virtualLocation,Integer.valueOf((int)getStartRealLocation().getPos()),Integer.valueOf((int)getEndRealLocation().getPos()));\n]]></Code>\n    <InClasses></InClasses>\n    <Submits></Submits>\n    <Dominates></Dominates>\n  </Terminal>\n\n  <Nonterminal id=\"Foo\">\n    <PP>Foo</PP>\n    <Type><![CDATA[Foo]]></Type>\n  </Nonterminal>\n  <Production id=\"foo\">\n    <Code><![CDATA[\nRESULT = new foo(_children, null);\n]]></Code>\n    <LHS><NonterminalRef id=\"Foo\" grammar=\"host\" /></LHS>\n    <RHS></RHS>\n  </Production>\n    </Declarations>\n  </Grammar>\n</CopperSpec>\n", String, csttests );
equalityTest( obj.unparse, "nt([], nt('Foo', [])),\n term('XTerm', /x/, []),\n prod([], signature('foo', [], element('asdf', nt('Foo', [])), []), [])", String, csttests );

global obj_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), 
      syntaxInterfaceParser(obj.unparse, "<>").parseTree.syntaxAst));

equalityTest( obj_again.xmlCopper, obj.xmlCopper, String, csttests );
equalityTest( obj_again.unparse, obj.unparse, String, csttests );


global obj2::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), 
     [syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", literalRegex("x"), nilTerminalMod()),
      syntaxProduction(
        namedSignature("foo", [],
          namedSignatureElement("asdf", nonterminalTypeExp("Oops", [])), []),
        nilProductionMod())
     ]));

equalityTest( obj2.cstErrors, ["Lookup error with LHS nonterminal Oops"], [String], csttests );



global obj3::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(), [
      syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", literalRegex("x"), 
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
      syntaxTerminal("C", literalRegex("y"), nilTerminalMod()),
      syntaxParserAttribute("asdf", stringTypeExp(), "asdf = 'asfd';"),
      syntaxDisambiguationGroup("g23", ["XTerm", "C"], "return C;")
     ]));

global obj3_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr(consSyntax, nilSyntax(),
      syntaxInterfaceParser(obj3.unparse, "<>").parseTree.syntaxAst));

equalityTest( obj3.cstErrors, [], [String], csttests );
--equalityTest( obj3.unparse, "", String, csttests );

equalityTest( obj3_again.xmlCopper, obj3.xmlCopper, String, csttests );
equalityTest( obj3_again.unparse, obj3.unparse, String, csttests );

