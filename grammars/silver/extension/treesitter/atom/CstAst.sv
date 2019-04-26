grammar silver:extension:treesitter:atom;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

imports silver:extension:treesitter;

attribute jsonAtom occurs on SyntaxRoot;


aspect default production
top::SyntaxRoot ::=
{
  top.jsonAtom = error("Shouldn't happen");
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{
  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.
  top.jsonAtom =
  s"""
    "terminals": [ ${implode(",\n    ", s2.jsonTerminalList)}
    ],

    "nonterminals": [ ${implode(",\n    ", s2.jsonNonterminalList)}
    ],

    "productions": [ ${implode(",\n    ", s2.jsonProductionList)}
    ],

    "lexer_classes": [ ${implode(",\n    ", s2.jsonLexerClassList)}
    ],

    "disambiguation_groups": [ ${implode(",\n    ", s2.jsonDisambiguationGroupList)}
    ],
  """;
}

