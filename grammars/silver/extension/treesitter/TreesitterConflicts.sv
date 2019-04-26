grammar silver:extension:treesitter;

synthesized attribute terminalConflicts :: [Pair<String [String]>] occurs on Syntax;

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.terminalConflicts =
    case s1 of
    | syntaxDisambiguationGroup(name, terms, _, _) -> pair(name, terms) :: s2.terminalConflicts
    | _ -> s2.terminalConflicts
    end;
}

aspect production nilSyntax
top::Syntax ::=
{
  top.terminalConflicts = [];
}

function createModifiedGrammar
Syntax ::= oldGrammar::Syntax terminalConflicts :: [Pair<String [String]>]
{
  return
  case terminalConflicts of
  | [] -> oldGrammar
  | hd::tl -> createModifiedGrammar(modifyGrammarForAConflict(oldGrammar, fst(hd), snd(hd)), tl)
  end;
}

function modifyGrammarForAConflict
Syntax ::= oldGrammar::Syntax conflictName::String termsInConflict::[String] {
  return
  case oldGrammar of
  |  nilSyntax() ->
      consSyntax(syntaxTerminal(conflictName, regexLiteral(conflictName), nilTerminalMod()),
                 nilSyntax())
  | consSyntax(sDcl, rest) ->
      foldr(consSyntax, modifyGrammarForAConflict(rest, conflictName, termsInConflict), 
            modifySyntaxDclForAConflict(sDcl, conflictName, termsInConflict))
  end;
}

function modifySyntaxDclForAConflict
[SyntaxDcl] ::= sDcl::SyntaxDcl conflictName::String termsInConflict::[String] {
  return 
  case sDcl of 
  | syntaxTerminal(n, reg, mod) ->
      if containsBy(stringEq, n, termsInConflict) then
        createNonterminalFromTerminal(n, conflictName)
      else
        [sDcl]
  | syntaxNonterminal(t, subdcl) ->
      -- terminal already convereted to nonterminal in conflict
      -- add new production for it
      if containsBy(stringEq, t.typeName, termsInConflict) then
        addNewProductionForNonterminal(t.typeName, conflictName) :: sDcl :: []
      -- regular nonterminal no modification necessary
      else
        [sDcl]
  | syntaxProduction(ns, mod) -> [syntaxProduction(modifyProductionForConflict(ns, termsInConflict), mod)]
  | syntaxLexerClass(_, _) -> [sDcl]
  -- remove disambiguation groups
  | syntaxDisambiguationGroup(_, _, _, _) -> []
  | _ -> []
  end;
}

function modifyProductionForConflict
NamedSignature ::= ns::NamedSignature termsInConflict::[String]
{
  return 
  case termsInConflict of 
  | [] -> ns
  | hd::tl -> modifyProductionForConflict(modifyProductionForTermInConflict(ns, hd), tl)
  end;
}

function modifyProductionForTermInConflict
NamedSignature ::= ns::NamedSignature term::String
{
  return namedSignature(ns.fullName, map(modifyProductionInputElementForConflict(term, _), ns.inputElements),
    ns.outputElement, ns.namedInputElements);
}

function modifyProductionInputElementForConflict
NamedSignatureElement ::= term::String inputElem::NamedSignatureElement
{
  return
  if stringEq(inputElem.typerep.typeName, term) then
    namedSignatureElement(inputElem.elementName, nonterminalType(term, []))
  else
    inputElem;
}

function addNewProductionForNonterminal
SyntaxDcl ::= ntName::String conflictName::String {
  local outputElem :: NamedSignatureElement = namedSignatureElement(ntName, nonterminalType(ntName, []));
  local inputElem :: NamedSignatureElement = namedSignatureElement(conflictName, terminalType(conflictName));
  local namedSig :: NamedSignature = namedSignature(ntName ++ conflictName, [inputElem], outputElem, []);
  return syntaxProduction(namedSig, nilProductionMod());
}

function createNonterminalFromTerminal
[SyntaxDcl] ::= termName::String conflictName::String {
  local outputElem :: NamedSignatureElement = namedSignatureElement(termName, nonterminalType(termName, []));
  local inputElem :: NamedSignatureElement = namedSignatureElement(conflictName, terminalType(conflictName));
  local namedSig :: NamedSignature = namedSignature(termName ++ conflictName, [inputElem], outputElem, []);
  local newProd :: SyntaxDcl = syntaxProduction(namedSig, nilProductionMod());
  local newNonTerm :: SyntaxDcl = syntaxNonterminal(nonterminalType(termName, []), nilSyntax());
  return newProd :: newNonTerm :: [];
}
