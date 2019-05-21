
{-- SYNTAX LEXER CLASS MODIFIERS --}
-- a list of conflicts for treesitter that exist on lexer classes
-- the first element in the pair is the name of the disambiguation function
-- the second element is the terms involved
attribute terminalConflicts occurs on SyntaxLexerClassModifier, SyntaxLexerClassModifiers;
aspect production lexerClassDisambiguate
top::SyntaxLexerClassModifier ::= acode::String
{
  top.terminalConflicts = [pair(funName, terms)];
}

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  top.terminalConflicts = [];
}

aspect production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.terminalConflicts = h.terminalConflicts ++ t.terminalConflicts;
}

aspect production nilLexerClassMod
top::SyntaxLexerClassModifiers ::=
{
  top.terminalConflicts = [];
}

synthesized attribute treesitterProductionPrec :: Maybe<Integer> occurs on SyntaxProductionModifiers, SyntaxProductionModifier;

aspect production consProductionMod
top::SyntaxProductionModifiers ::= h::SyntaxProductionModifier  t::SyntaxProductionModifiers
{
  top.treesitterProductionPrec = orElse(h.treesitterProductionPrec, t.treesitterProductionPrec);
}

aspect production nilProductionMod
top::SyntaxProductionModifiers ::=
{
  top.treesitterProductionPrec = nothing();
}

aspect default production
top::SyntaxProductionModifier ::=
{
  top.treesitterProductionPrec = nothing();
}

aspect production prodOperator
top::SyntaxProductionModifier ::= term::String
{
  -- in the abstract production
  local termRef :: [Decorated SyntaxDcl] = searchEnvTree(term, top.cstEnv);
  top.treesitterProductionPrec = 
    case head(termRef) of 
    | syntaxTerminal(_, _, mods) -> just( (mods.opPrecedence).fromJust )
    | _ -> nothing()
    end;

}
