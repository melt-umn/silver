grammar silver:extension:treesitter:atom;

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  top.jsonAtom =
    if h.jsonAtom == "" then
      t.jsonAtom
    else if t.jsonAtom == "" then
      h.jsonAtom
    else
      h.jsonAtom ++ ",\n    " ++ t.jsonAtom;
}

aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::= 
{
  top.jsonAtom = "";
}


{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxTerminalModifier ::=
{
  top.jsonAtom = "";
}

{--
 - If present, it's an ignore terminal, otherwise ordinary terminal.
 - Copper has no notion of an ignore terminal, this is translated away.
 -}
aspect production termIgnore
top::SyntaxTerminalModifier ::=
{
  top.jsonAtom = s""" "ignored": "true" """;
}
{--
 - If present, this is a Marking terminal. In the default translation,
 - this does nothing.
 -}
aspect production termMarking
top::SyntaxTerminalModifier ::=
{
  top.jsonAtom = s""" "marking": "true" """;
}
{--
 - The terminal's precedence. (Resolves shift/reduce conflicts)
 -}
aspect production termPrecedence
top::SyntaxTerminalModifier ::= lvl::Integer
{
  top.jsonAtom = s""" "precedence": "${toString(lvl)}" """;
}
{--
 - The terminal's association. Either left, right, or nonassoc. TODO: a type?
 -}
aspect production termAssociation
top::SyntaxTerminalModifier ::= direction::String
{
  top.jsonAtom = s""" "association": "${direction}" """;
}

aspect production termPrettyName
top::SyntaxTerminalModifier ::= prettyName::String
{
  top.jsonAtom = s""" "prettyName": "${prettyName}" """;
}

aspect production termAction
top::SyntaxTerminalModifier ::= acode::String
{
  top.jsonAtom = s""" "acode": "${jsonify(acode)}" """;
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.jsonAtom = s""" "lexer_classes": [
    ${implode(",", map(addQuotes, cls))}
  ]""";
}

aspect production termDominates
top::SyntaxTerminalModifier ::= dom::[String]
{
  local domsList :: [Decorated SyntaxDcl] = map(head, domRefs);
  local lexerClassDoms :: [Decorated SyntaxDcl] = filter(isLexerClass, domsList);
  local terminalDoms :: [Decorated SyntaxDcl] = filter(isTerminal, domsList);
  top.jsonAtom = s""" "dominates:" {
    "terminals": [
      ${implode(",", map(syntaxDclJsonName, terminalDoms))}
    ],
    lexer_classes: [
      ${implode(",", map(syntaxDclJsonName, lexerClassDoms))}
    ]
  }""";
}

aspect production termSubmits
top::SyntaxTerminalModifier ::= sub::[String]
{
  local subsList :: [Decorated SyntaxDcl] = map(head, subRefs);
  local lexerClassSubs :: [Decorated SyntaxDcl] = filter(isLexerClass, subsList);
  local terminalSubs :: [Decorated SyntaxDcl] = filter(isTerminal, subsList);
  top.jsonAtom = s""" "submits": {
    "terminals": [
      ${implode(",", map(syntaxDclJsonName, terminalSubs))}
    ],
    "lexer_classes": [
      ${implode(",", map(syntaxDclJsonName, lexerClassSubs))}
    ]
  }""";
}
