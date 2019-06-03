grammar silver:extension:treesitter;

function newNonterminalsForBridgeProductions
Syntax ::= s::Syntax env::EnvTree<Decorated SyntaxDcl>
{
  local attribute modifiedSyntax :: Syntax = modifyNonterminalsForBridgeProductions(s, env);
  return mergeDuplicateNonterminalsCreated(syntaxToDclList(modifiedSyntax));
}

-- this currently leaves redundancy in the RHS of the original productions that
-- can produce the same nonterminal.
function mergeDuplicateNonterminalsCreated
Syntax ::= dcls::[SyntaxDcl]
{
  local attribute dcl :: SyntaxDcl = head(dcls);
  local attribute splitDcls :: Pair<[SyntaxDcl] [SyntaxDcl]> = partition(dclsHaveSameName(dcl, _), dcls);
  local attribute matchingDcls :: [SyntaxDcl] = splitDcls.fst;
  local attribute restDcls :: [SyntaxDcl] = splitDcls.snd;

  return
  if null(dcls) 
  then nilSyntax()
  else consSyntax(mergeDcls(matchingDcls), mergeDuplicateNonterminalsCreated(restDcls));
}

function mergeDcls
SyntaxDcl ::= dcls::[SyntaxDcl]
{
  -- if 0 dcls error. if 1 dcl return that otherwise merge the first two
  -- declarations then merge the rest
  return
  if null(dcls) then error("Cannot merge together 0 declarations")
  else if null(tail(dcls)) then head(dcls)
  else
    mergeDcls(mergeTwoDcls(head(dcls), head(tail(dcls))) :: tail(tail(dcls)));
}

function mergeTwoDcls
SyntaxDcl ::= dcl1::SyntaxDcl dcl2::SyntaxDcl
{
  return
  case dcl1 of
  | syntaxNonterminal(n1, prods1) ->
    case dcl2 of
    -- names are the same
    | syntaxNonterminal(n2, prods2) -> syntaxNonterminal(n1, appendSyntax(prods1, prods2))
    | _ -> error("Syntax declaration merging only can occur on nonterminals")
    end
  | _ -> error("Syntax declaration merging only can occur on nonterminals")
  end;
}

function modifyNonterminalsForBridgeProductions
Syntax ::= s::Syntax env::EnvTree<Decorated SyntaxDcl>
{
  return
  case s of
  | nilSyntax() -> s
  | consSyntax(sDcl, rest) -> foldr(
      consSyntax,
      newNonterminalsForBridgeProductions(rest, env),
      handleDclForBridgeProduction(sDcl, env))
  end;
}

function handleDclForBridgeProduction
[SyntaxDcl] ::= sDcl::SyntaxDcl env::EnvTree<Decorated SyntaxDcl>
{
  return
  case sDcl of 
  | syntaxNonterminal(n, prods) -> 
      syntaxNonterminal(n, removeBridgeProductions(prods, env)) ::
      createNewNtsForBridgeProductions(prods, env)
  | _ -> [sDcl]
  end;
}


function createNewNtsForBridgeProductions
[SyntaxDcl] ::= prods::Syntax env::EnvTree<Decorated SyntaxDcl>
{
  return 
  case prods of
  | nilSyntax() -> []
  | consSyntax(s1, s2) ->
      if isBridgeProduction(s1, env) 
      then createNonterminal(s1) :: createNewNtsForBridgeProductions(s2, env)
      else createNewNtsForBridgeProductions(s2, env)
  end;
}

function createNonterminal
SyntaxDcl ::= prod::SyntaxDcl
{
  return
  case prod of
  | syntaxProduction(ns, mods) -> syntaxNonterminal(
      nonterminalType(nameNonterminalFromProduction(ns), []), 
                      consSyntax(syntaxProduction(ns, mods), nilSyntax()))
  | _ -> prod
  end;
}

function nameNonterminalFromProduction
String ::= sig::NamedSignature
{
  local attribute markingTerminalName :: String = head(sig.inputElements).typerep.typeName;
  local attribute outputNtName :: String = sig.outputElement.typerep.typeName;
  local attribute lastColonMarkTerminal :: Integer = 
    lastIndexOf(":", markingTerminalName);
  local attribute lastColonOutputNt :: Integer = 
    lastIndexOf(":", outputNtName);
  -- concatenate the original grammar with the name of the nonterminal in the
  -- original grammar
  return 
    substring(0, lastColonMarkTerminal+ 1,markingTerminalName)  ++
    substring(lastColonOutputNt + 1, length(outputNtName), outputNtName);
        
}

function removeBridgeProductions
Syntax ::= prods::Syntax env::EnvTree<Decorated SyntaxDcl>
{
  return
  case prods of
  | nilSyntax() -> prods
  | consSyntax(s1, s2) ->
      if isBridgeProduction(s1, env) 
      then consSyntax(newProductionFromBridgeProduction(s1), removeBridgeProductions(s2, env))
      else consSyntax(s1, removeBridgeProductions(s2, env))
  end;
}

function newProductionFromBridgeProduction
SyntaxDcl ::= prod::SyntaxDcl
{
  
  return case prod of
  | syntaxProduction(ns, mods) -> 
      syntaxProduction(
        namedSignature(ns.fullName,
          [namedSignatureElement(nameNonterminalFromProduction(ns),
            nonterminalType(nameNonterminalFromProduction(ns), []))],
          ns.outputElement, [])
      , nilProductionMod()) -- production modifiers
  | _ -> prod
  end;
}

function isBridgeProduction
Boolean ::= prod::SyntaxDcl env::EnvTree<Decorated SyntaxDcl>
{
  return
  case prod of 
  | syntaxProduction(ns, mods) -> 
      if null(ns.inputElements) then false 
      else
        isMarkingTerminal( (head(ns.inputElements)).typerep.typeName, env)
  | _ -> false
  end;
}

function isMarkingTerminal
Boolean ::= name::String env::EnvTree<Decorated SyntaxDcl> 
{
  local attribute term :: [Decorated SyntaxDcl] = searchEnvTree(name, env);
  return
  if null(term) then error(s"""Terminal with name ${name} referenced in grammar but was not found""")
  else 
  case head(term) of
  | syntaxTerminal(_, _, mods) -> mods.marking
  | _ -> false
  end;
}

