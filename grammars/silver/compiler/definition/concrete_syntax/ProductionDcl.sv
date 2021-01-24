grammar silver:compiler:definition:concrete_syntax;

autocopy attribute productionName :: String;

concrete production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.unparse = "concrete production " ++ id.unparse ++ "\n" ++ ns.unparse ++ " " ++ pm.unparse ++ "\n" ++ body.unparse; 

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;
  
  pm.productionName = fName;
  ns.signatureName = fName;
  ns.env = newScopeEnv(ns.defs, top.env);

  top.errors <- pm.errors;
  top.errors <- ns.concreteSyntaxTypeErrors;

  -- TODO: we should CHANGE syntaxProduction so it just plain takes a NamedSignature!
  top.syntaxAst := [
    syntaxProduction(namedSig,
      foldr(consProductionMod, nilProductionMod(), pm.productionModifiers))];
  
  forwards to productionDcl('abstract', $2, id, ns, body, location=top.location);
}

nonterminal ProductionModifiers with config, location, unparse, productionModifiers, errors, env, productionName; -- 0 or some
nonterminal ProductionModifierList with config, location, unparse, productionModifiers, errors, env, productionName; -- 1 or more
closed nonterminal ProductionModifier with config, location, unparse, productionModifiers, errors, env, productionName; -- 1

monoid attribute productionModifiers :: [SyntaxProductionModifier];

propagate productionModifiers on ProductionModifiers, ProductionModifierList;
propagate errors on ProductionModifiers, ProductionModifierList, ProductionModifier;

concrete production productionModifiersNone
top::ProductionModifiers ::=
{
  top.unparse = "";
}
concrete production productionModifierSome
top::ProductionModifiers ::= pm::ProductionModifierList
{
  top.unparse = pm.unparse;
}

concrete production productionModifierSingle
top::ProductionModifierList ::= pm::ProductionModifier
{
  top.unparse = pm.unparse;
}
concrete production productionModifiersCons
top::ProductionModifierList ::= h::ProductionModifier ',' t::ProductionModifierList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
}


concrete production productionModifierPrecedence
top::ProductionModifier ::= 'precedence' '=' i::Int_t
{
  top.unparse = "precedence = " ++ i.lexeme;

  top.productionModifiers := [prodPrecedence(toInteger(i.lexeme))];
}

terminal Operator_kwd 'operator' lexer classes {KEYWORD,RESERVED};

concrete production productionModifierOperator
top::ProductionModifier ::= 'operator' '=' n::QName
{
  top.unparse = "operator = " ++ n.unparse;

  top.productionModifiers := [prodOperator(n.lookupType.fullName)];

  top.errors <- n.lookupType.errors ++
                if !n.lookupType.typeScheme.isTerminal
                then [err(n.location, n.unparse ++ " is not a terminal.")]
                else [];
}

--------------------------------------------------------------------------------
-- Type sanity checking on concrete productions

monoid attribute concreteSyntaxTypeErrors :: [Message];
attribute concreteSyntaxTypeErrors occurs on ProductionSignature, ProductionRHS, ProductionRHSElem;
propagate concreteSyntaxTypeErrors on ProductionSignature, ProductionRHS, ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  local fstType :: Type = head(top.namedSignature.inputElements).typerep;
  local lstType :: Type = last(top.namedSignature.inputElements).typerep;
  
  local checkFirst :: Boolean =
    fstType.isTerminal || !null(getOccursDcl("silver:core:location", fstType.typeName, top.env)) || fstType.tracked;
  local checkSecond :: Boolean =
    lstType.isTerminal || !null(getOccursDcl("silver:core:location", lstType.typeName, top.env)) || lstType.tracked;
  local errFirst :: [Message] =
    if checkFirst then [] else [err(top.location, "Production has location annotation or is tracked, but first element of signature does not have location and is not tracked.")];
  local errSecond :: [Message] =
    if checkSecond then [] else [err(top.location, "Production has location annotation or is tracked, but last element of signature does not have location and is not tracked.")];
  
  local lhsHasLocation :: Boolean =
    case top.namedSignature.namedInputElements of
    | [namedSignatureElement("silver:core:location", _)] -> true
    | _ -> false
    end;
  local lhsHasOrigin :: Boolean = top.namedSignature.outputElement.typerep.tracked;

  top.concreteSyntaxTypeErrors <-
    case top.namedSignature.namedInputElements of
    | [] -> []
    | [namedSignatureElement("silver:core:location", _)] -> []
    | _ -> [err(top.location, "Annotation(s) on this production are not handleable by the parser generator (only a single annotation, and only silver:core:location is supported.)")]
    end;

  top.concreteSyntaxTypeErrors <-
    if lhsHasLocation || lhsHasOrigin
    then case length(top.namedSignature.inputElements) of
         | 0 -> [] -- OK
         | 1 -> errFirst
         | _ -> errFirst ++ errSecond
         end
    else [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.concreteSyntaxTypeErrors <-
    if t.typerep.permittedInConcreteSyntax then []
    else [err(t.location, t.unparse ++ " is not permitted on concrete productions.  Only terminals and nonterminals (without type variables) can appear here")];
}

synthesized attribute permittedInConcreteSyntax :: Boolean occurs on Type;

aspect default production
top::Type ::=
{
  top.permittedInConcreteSyntax = false;
}

aspect production nonterminalType
top::Type ::= fn::String k::Integer tracked::Boolean
{
  top.permittedInConcreteSyntax = k == 0;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.permittedInConcreteSyntax = true;
}

