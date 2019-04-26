grammar silver:extension:treesitter:atom;

aspect production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.jsonAtom =
    if h.jsonAtom == "" then
      t.jsonAtom
    else if t.jsonAtom == "" then
      h.jsonAtom
    else
      h.jsonAtom ++ ",\n  " ++ t.jsonAtom;
}

aspect production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.jsonAtom = "";
}

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  top.jsonAtom = "";
}

{--
 - The submits list for the lexer class. Either lexer classes or terminals.
 -}
aspect production lexerClassSubmits
top::SyntaxLexerClassModifier ::= sub::[String]
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
{--
 - The dominates list for the lexer class. Either lexer classes or terminals.
 -}
aspect production lexerClassDominates
top::SyntaxLexerClassModifier ::= dom::[String]
{
  local domsList :: [Decorated SyntaxDcl] = map(head, domRefs);
  local lexerClassDoms :: [Decorated SyntaxDcl] = filter(isLexerClass, domsList);
  local terminalDoms :: [Decorated SyntaxDcl] = filter(isTerminal, domsList);
  top.jsonAtom = s""" "dominates:" {
    "terminals": [
      ${implode(",", map(syntaxDclJsonName, terminalDoms))}
    ],
    "lexer_classes": [
      ${implode(",", map(syntaxDclJsonName, lexerClassDoms))}
    ]
  }""";
}

{--
 - A disambiguation function that should be created for the members of a lexer class.
 -}
aspect production lexerClassDisambiguate
top::SyntaxLexerClassModifier ::= acode::String
{
  top.jsonAtom = s""" "disambiguate": "${jsonify(acode)}" """;
}
