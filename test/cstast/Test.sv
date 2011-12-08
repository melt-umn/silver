grammar cstast;

imports silver:testing;
imports lib:extcore;
imports silver:definition:concrete_syntax only syntaxAst;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:concrete_syntax:ast:env_parser;
imports silver:definition:env:env_parser only WS, RegExprDelim;
imports silver:definition:type;
imports silver:definition:regex;
imports silver:definition:env only unparse;

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
    foldr_p(consSyntax, nilSyntax(), 
     [syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", literalRegex("x"), nilTerminalMod()),
      syntaxProduction("foo", nonterminalTypeExp("Foo", []), [], nilProductionMod())
     ]));

equalityTest( obj.cstErrors, [], [String], csttests );
--equalityTest( substitute("silver:definition:concrete_syntax:ast:", "", hackUnparse(obj)), "", String, csttests );
--equalityTest( substitute("silver:definition:concrete_syntax:ast:", "", hackUnparse(obj.cstNormal)), "", String, csttests );
equalityTest( obj.xmlCopper, "<?xml version=\"1.0\"?>\n\n<copperspec id=\"lol\" type=\"LALR1\" version=\"1.1\">\n  <preamble>\n     <code><![CDATA[\nimport edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;\n     ]]></code>\n  </preamble>\n  <attribute id=\"context\" type=\"common.DecoratedNode\">\n    <code>context = common.TopNode.singleton;</code>\n  </attribute>\n\n  <start>\n    <nonterm id=\"Foo\"/>\n    <layout></layout>\n  </start>\n\n  <term id=\"XTerm\">\n    <code><![CDATA[\nRESULT = new common.TerminalRecord(lexeme,virtualLocation.getFileName(),virtualLocation.getLine(),virtualLocation.getColumn());\n    ]]></code>\n    <classes></classes>\n    <regex><string>x</string></regex>\n    <dominates></dominates>\n    <submits></submits>\n  </term>\n\n  <nonterm id=\"Foo\" />\n  <prod id=\"foo\" class=\"main\" precedence=\"0\">\n    <code><![CDATA[\nRESULT = new foo(_children);\n    ]]></code>\n    <lhs><nonterm id=\"Foo\"/></lhs>\n    <rhs></rhs>\n    <layout></layout>\n  </prod>\n</copperspec>\n", String, csttests );
equalityTest( obj.unparse, "nt([], nt('Foo', [])),\n term('XTerm', /x/, []),\n prod('foo',[],nt('Foo', []),[],[])", String, csttests );

global obj_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr_p(consSyntax, nilSyntax(), 
      syntaxInterfaceParser(obj.unparse, "<>").parseTree.syntaxAst));

equalityTest( obj_again.xmlCopper, obj.xmlCopper, String, csttests );
equalityTest( obj_again.unparse, obj.unparse, String, csttests );


global obj2::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr_p(consSyntax, nilSyntax(), 
     [syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", literalRegex("x"), nilTerminalMod()),
      syntaxProduction("foo", nonterminalTypeExp("Oops", []), [], nilProductionMod())
     ]));

equalityTest( obj2.cstErrors, ["Lookup error with LHS nonterminal Oops"], [String], csttests );



global obj3::SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr_p(consSyntax, nilSyntax(), [
      syntaxNonterminal(nonterminalTypeExp("Foo", []), nilSyntax()),
      syntaxTerminal("XTerm", literalRegex("x"), 
        foldr_p(consTerminalMod, nilTerminalMod(), [
          termIgnore(),
          termPrecedence(3),
          termAssociation("left"),
          termClasses(["A","B"]),
          termSubmits(["XTerm"]),
          termDominates(["C"]),
          termAction("blah;")
        ])),
      syntaxProduction("foo", nonterminalTypeExp("Foo", []), [],
        foldr_p(consProductionMod, nilProductionMod(), [
          prodPrecedence(2),
          prodOperator("XTerm"),
          prodAction("asdf;"),
          prodLayout(["XTerm", "C"])
        ])),
      syntaxLexerClass("A", ["B"], ["C"]),
      syntaxLexerClass("B", [], []),
      syntaxTerminal("C", literalRegex("y"), nilTerminalMod()),
      syntaxParserAttribute("asdf", stringTypeExp(), "asdf = 'asfd';"),
      syntaxDisambiguationGroup("g23", ["XTerm", "C"], "return C;")
     ]));

global obj3_again :: SyntaxRoot =
  cstRoot("lol", "Foo",
    foldr_p(consSyntax, nilSyntax(),
      syntaxInterfaceParser(obj3.unparse, "<>").parseTree.syntaxAst));

equalityTest( obj3.cstErrors, [], [String], csttests );
--equalityTest( obj3.unparse, "", String, csttests );

equalityTest( obj3_again.xmlCopper, obj3.xmlCopper, String, csttests );
equalityTest( obj3_again.unparse, obj3.unparse, String, csttests );

