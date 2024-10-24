grammar silver:compiler:definition:concrete_syntax;

import silver:compiler:modification:copper only actionDefs;

inherited attribute productionSig :: NamedSignature;

concrete production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.unparse = "concrete production " ++ id.unparse ++ "\n" ++ ns.unparse ++ " " ++ pm.unparse ++ "\n" ++ body.unparse;
  propagate config, grammarName, compiledGrammars;

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;
  
  ns.signatureName = fName;
  ns.implementedSig = nothing();
  ns.env = newScopeEnv(ns.defs, top.env);
  pm.productionSig = ns.namedSignature;
  pm.env = newScopeEnv(ns.actionDefs, top.env);

  top.errors <- pm.errors;
  top.errors <- ns.concreteSyntaxTypeErrors;

  top.syntaxAst :=
    [ syntaxProduction(namedSig,
        foldr(consProductionMod, nilProductionMod(), pm.productionModifiers),
        location=getParsedOriginLocationOrFallback(top), sourceGrammar=top.grammarName)
    ];
  
  forwards to productionDcl('abstract', $2, @id, productionImplementsNone(), @ns, @body);
} action {
  insert semantic token IdFnProdDcl_t at id.nameLoc;
  sigNames = [];
}

tracked nonterminal ProductionModifiers with config, unparse, productionModifiers, errors, env, productionSig; -- 0 or some
tracked nonterminal ProductionModifierList with config, unparse, productionModifiers, errors, env, productionSig; -- 1 or more
closed tracked nonterminal ProductionModifier with config, unparse, productionModifiers, errors, env, productionSig; -- 1

monoid attribute productionModifiers :: [SyntaxProductionModifier];

propagate productionModifiers on ProductionModifiers, ProductionModifierList;
propagate config, errors, env, productionSig
  on ProductionModifiers, ProductionModifierList, ProductionModifier;

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

terminal Operator_kwd 'operator' lexer classes {MODIFIER,RESERVED};

concrete production productionModifierOperator
top::ProductionModifier ::= 'operator' '=' n::QNameType
{
  top.unparse = "operator = " ++ n.unparse;

  top.productionModifiers := [prodOperator(n.lookupType.fullName)];

  top.errors <- n.lookupType.errors ++
                if !n.lookupType.typeScheme.isTerminal
                then [errFromOrigin(n, n.unparse ++ " is not a terminal.")]
                else [];
}

--------------------------------------------------------------------------------
-- Type sanity checking on concrete productions

monoid attribute concreteSyntaxTypeErrors :: [Message];
attribute concreteSyntaxTypeErrors occurs on ProductionSignature, ProductionRHS, ProductionRHSElem;
propagate concreteSyntaxTypeErrors on ProductionSignature, ProductionRHS, ProductionRHSElem;

flowtype concreteSyntaxTypeErrors {grammarName, env, flowEnv, deterministicCount} on ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  local fstType :: Type = head(top.namedSignature.inputElements).typerep;
  local lstType :: Type = last(top.namedSignature.inputElements).typerep;
  
  local checkFirst :: Boolean =
    fstType.isTerminal || !null(getOccursDcl("silver:core:location", fstType.typeName, top.env)) || fstType.isTracked;
  local checkSecond :: Boolean =
    lstType.isTerminal || !null(getOccursDcl("silver:core:location", lstType.typeName, top.env)) || lstType.isTracked;
  local errFirst :: [Message] =
    if checkFirst then [] else [errFromOrigin(top, "Production has location annotation or is tracked, but first element of signature does not have location and is not tracked.")];
  local errSecond :: [Message] =
    if checkSecond then [] else [errFromOrigin(top, "Production has location annotation or is tracked, but last element of signature does not have location and is not tracked.")];
  
  local lhsHasLocation :: Boolean =
    case top.namedSignature.namedInputElements of
    | [namedSignatureElement("silver:core:location", _, _)] -> true
    | _ -> false
    end;
  local lhsHasOrigin :: Boolean = top.namedSignature.outputElement.typerep.isTracked;

  top.concreteSyntaxTypeErrors <-
    case top.namedSignature.namedInputElements of
    | [] -> []
    | [namedSignatureElement("silver:core:location", _, _)] -> []
    | _ -> [errFromOrigin(top, "Annotation(s) on this production are not handleable by the parser generator (only a single annotation, and only silver:core:location is supported.)")]
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
top::ProductionRHSElem ::= ms::MaybeShared id::Name '::' t::TypeExpr
{
  top.concreteSyntaxTypeErrors <-
    if t.typerep.permittedInConcreteSyntax then []
    else [errFromOrigin(t, t.unparse ++ " is not permitted on concrete productions.  Only terminals and nonterminals (without type variables) can appear here")];
  top.concreteSyntaxTypeErrors <-
    if ms.isShared then [errFromOrigin(ms, "Sharing is not permitted in concrete production signatures.")]
    else [];
}

synthesized attribute permittedInConcreteSyntax :: Boolean occurs on Type;

aspect default production
top::Type ::=
{
  top.permittedInConcreteSyntax = false;
}

aspect production nonterminalType
top::Type ::= fn::String ks::[Kind] data::Boolean tracked::Boolean
{
  top.permittedInConcreteSyntax = null(ks);
}

aspect production terminalType
top::Type ::= fn::String
{
  top.permittedInConcreteSyntax = true;
}

