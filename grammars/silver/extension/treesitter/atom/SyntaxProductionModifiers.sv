grammar silver:extension:treesitter:atom;

aspect production consProductionMod
top::SyntaxProductionModifiers ::= h::SyntaxProductionModifier t::SyntaxProductionModifiers
{
  top.jsonAtom =
    if h.jsonAtom == "" then
      t.jsonAtom 
    else if t.jsonAtom == "" then
      h.jsonAtom
    else
      h.jsonAtom ++ ",\n    " ++ t.jsonAtom;
}

aspect production nilProductionMod
top::SyntaxProductionModifiers ::= 
{
  top.jsonAtom = "";
}

aspect default production
top::SyntaxProductionModifier ::=
{
  top.jsonAtom = "";
}

{--
 - The precedence for the production. (Resolves reduce/reduce conflicts.)
 -}
aspect production prodPrecedence
top::SyntaxProductionModifier ::= lvl::Integer
{
  top.jsonAtom = s""" "production_precedence": "${toString(lvl)}" """;
}

{--
 - The terminal this production uses for shift/reduce conflict resolution.
 - By default, the last terminal in the production? TODO
 -}
aspect production prodOperator
top::SyntaxProductionModifier ::= term::String
{
  local termRef :: [Decorated SyntaxDcl] = searchEnvTree(term, top.cstEnv);
  top.jsonAtom = s""" "production_operator": ${syntaxDclJsonName(head(termRef))} """;
}

{--
 - The action to perform when this production is REDUCEd.
 -}
aspect production prodAction
top::SyntaxProductionModifier ::= acode::String
{
  top.jsonAtom = s""" "acode": "${jsonify(acode)}" """;
}

-- TODO : figure out what custom layout is and possibly add one for that
